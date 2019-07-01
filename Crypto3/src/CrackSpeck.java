import java.util.ArrayList;
import java.util.Arrays;
import edu.rit.util.Hex;

/**
 * @author Douglas May
 * 
 * This class implements a known plaintext attack against  3 round SPECK48/96
 *
 */
public class CrackSpeck{
	private static int currSubKey = 0;
	private static ArrayList<String> plainText = new ArrayList<String>();
	private static ArrayList<String> cipherText = new ArrayList<String>();

	private static byte[][] solkeys = new byte[3][];
	


	public static void main(String[] args){
		
		if(args.length == 0 || (args.length & 1) != 0){
			System.out.println("Usage: java CrackSpeck <pt1> <ct1> [<pt2> <ct2> ...]");
			return;
		}
		
		
		
		
		solkeys[0] = null;
		boolean isCipherText = false;
		for(int i = 0; i < args.length;i++){
			if(args[i].length() != 12){
				System.out.println("CrackSpeck: All arguments must be 12 hex digits");
				System.out.println("Usage: java CrackSpeck <pt1> <ct1> [<pt2> <ct2> ...]");
				return;
			}
			
			
			
			
			if(isCipherText){
				cipherText.add(args[i]);
				isCipherText = false;
				continue;
			}
			plainText.add(args[i]);
			isCipherText = true;

		}

		byte[] subKey1 = new byte[3];
		byte[] subKey2 = new byte[3];
		byte[] subKey3 = new byte[3];

		byte[] thePT;
		byte[] theCT;
		byte[] pt1 = new byte[3];
		byte[] pt2 = new byte[3];
		byte[] ct1 = new byte[3];
		byte[] ct2 = new byte[3];


		byte[] pt1R2;
		byte[] pt2R2;
		byte[] pt1R3;
		byte[] pt2R3;
		byte[] pt1Fin;
		byte[] pt2Fin;

		byte[] tmp1;
		
		
		//Loop Through all possible subkey value
		for(;currSubKey < 16777216; currSubKey++){
			thePT = Hex.toByteArray(plainText.get(0));
			theCT = Hex.toByteArray(cipherText.get(0));

			//Set pt1 the first half of the plain text
			pt1[0] = thePT[0];
			pt1[1] = thePT[1];
			pt1[2] = thePT[2];

			//Set pt2 the second half of the plain text
			pt2[0] = thePT[3];
			pt2[1] = thePT[4];
			pt2[2] = thePT[5];

			//Set ct1 the first half of the cipher text
			ct1[0] = theCT[0];
			ct1[1] = theCT[1];
			ct1[2] = theCT[2];

			//Set ct2 the second half of the cipher text
			ct2[0] = theCT[3];
			ct2[1] = theCT[4];
			ct2[2] = theCT[5];

			//resolve the second half of the "plaintext" going into round 3
			pt2R3 = rotateR3(xOR(ct1,ct2));
			
			//Turn the current subkey into a byte array
			subKey1[0] = (byte)(currSubKey >>> 16);
			subKey1[1] = (byte)(currSubKey >>> 8);
			subKey1[2] = (byte)(currSubKey);
			
			//Resolve the first half of the "plaintext" for round 2
			tmp1 = theAddition(rotateR8(pt1),pt2);
			pt1R2 =  xOR(subKey1,tmp1);

			//Resolve the second half of the "plaintext" for round 2
			pt2R2 = xOR(rotateL3(pt2), pt1R2);

			//Resolve the first half of the "plaintext" for round 3
			tmp1 = theAddition(rotateR8(pt1R2), pt2R2);
			pt1R3 = xOR(rotateL3(pt2R2),pt2R3);
			
			//Resolve the second subkey
			subKey2 = xOR(tmp1,pt1R3);
			
			//MAKE MORE MEMORY EFFIECIENATE BY TESTING SUBKEY PAIRS AS THEY COME OUT
			//resolve The third subkey
			tmp1 = theAddition(rotateR8(pt1R3),pt2R3);
			subKey3 = xOR(tmp1,ct1);
			
			//Now that the subkeys are generated Test them to see if they are valid for the rest of the 
			//plaintext ciphertext pairs
			boolean didBreak = false;
			for(int i = 0; i < plainText.size(); i++){
				thePT = Hex.toByteArray(plainText.get(i));
				theCT = Hex.toByteArray(cipherText.get(i));

				pt1[0] = thePT[0];
				pt1[1] = thePT[1];
				pt1[2] = thePT[2];

				pt2[0] = thePT[3];
				pt2[1] = thePT[4];
				pt2[2] = thePT[5];

				ct1[0] = theCT[0];
				ct1[1] = theCT[1];
				ct1[2] = theCT[2];

				ct2[0] = theCT[3];
				ct2[1] = theCT[4];
				ct2[2] = theCT[5];

				pt2R3 = rotateR3(xOR(ct1,ct2));

				

				pt1R2 = xOR(theAddition(rotateR8(pt1), pt2),subKey1);
				pt2R2 = xOR(pt1R2,rotateL3(pt2));

				pt1R3 = xOR(theAddition(rotateR8(pt1R2), pt2R2),subKey2);
				pt2R3 = xOR(pt1R3,rotateL3(pt2R2));

				pt1Fin = xOR(theAddition(rotateR8(pt1R3), pt2R3),subKey3);
				pt2Fin = xOR(pt1Fin,rotateL3(pt2R3));
				
				//Does the result == the cipherText?
				//If not break and generate the next subkeys
				if(!Arrays.equals(pt1Fin,ct1) || !Arrays.equals(pt2Fin,ct2)){
					didBreak = true;
					break;
				}			
			}
			if(!didBreak){
				if(solkeys[0] != null){
					System.out.println("Multiple subkeys found");
					return;
				}
				solkeys[0] = subKey1.clone();
				solkeys[1] = subKey2;
				solkeys[2] = subKey3;

			}
			didBreak = false;
			
		}
		if(solkeys[0] == null){
			System.out.println("No subkeys found");
			return;
		}
		else{
			System.out.println(Hex.toString(solkeys[0]));
			System.out.println(Hex.toString(solkeys[1]));
			System.out.println(Hex.toString(solkeys[2]));
		}

	}


	/**xOR this function xor's two byte arrays together 
	 * @param left - the first byte array. It's size is 3;
	 * @param right - the second byte array. It's size is 3;
	 * @return the result of the xOR
	 */
	private static byte[] xOR(byte[] left,byte[] right){

		return (new byte[]{(byte) (left[0] ^ right[0]),
				(byte) (left[1] ^ right[1]),
				(byte) (left[2] ^ right[2])});
	}
	
	/**rotate R8 This function rotates a byte array to the Right 3 places
	 * @param bytes - the byte array. It's size is 3
	 * @return the rotated array
	 */
	private static byte[] rotateR8(byte[] bytes){
		byte[] result = new byte[3];
		result[0] = bytes[2];
		result[1] = bytes[0];
		result[2] = bytes[1];
		return result;

	}


	/**rotateL3 This function rotates a byte array to the Left 3 places 
	 * @param bytes - the byte array. It's size is 3
	 * @return the rotated array
	 */
	private static byte[] rotateL3(byte[] bytes){
		int fInt = (bytes[0] << 24)&0xff000000|
				(bytes[0] << 16)&0x00ff0000| 
				(bytes[1] << 8)&0x0000ff00|
				(bytes[2] << 0)&0x000000ff;
		fInt = Integer.rotateLeft(fInt, 3);
		byte[] result = new byte[3];
		result[0] = (byte)(fInt >>> 16);
		result[1] = (byte)(fInt >>> 8);
		result[2] = (byte)(fInt);

		return result;
	}

	/**
	 * rotateR3 this function rotates an Array of bytes to the right by 3
	 * @param bytes - the byte array. Its size is 3
	 * @return the resulting array
	 */
	private static byte[] rotateR3(byte[] bytes){
		int fInt = (bytes[0] << 24)&0xff000000|
				(bytes[1] << 16)&0x00ff0000| 
				(bytes[2] << 8)&0x0000ff00|
				(bytes[2] << 0)&0x000000ff;
		fInt = Integer.rotateRight(fInt, 3);
		
		byte[] result = new byte[3];
		result[0] = (byte)(fInt >>> 24);
		result[1] = (byte)(fInt >>> 16);
		result[2] = (byte)(fInt >>> 8);
		return result;
	}
	
	/**
	 * theAddition This function adds two 24bit integers and mods the result
	 * by 24
	 * 
	 * @param int1 - The first integer as a byte[] of size 3
	 * @param int2 - The second integer as a byte[] of size 3
	 * @return the result
	 */
	private static byte[] theAddition(byte[] int1, byte[] int2){
		int theInt1 = (int1[0] << 16 &0x00ff0000|
				int1[1] << 8 &0x0000ff00| 
				int1[2] << 0&0x000000ff);
		
		int theInt2 = (int2[0] << 16 &0x00ff0000|
				int2[1] << 8 &0x0000ff00|
				int2[2] << 0&0x000000ff);

		int sum = theInt1 + theInt2;

		sum = sum & 16777215;//Modulo! dont forget this

		byte[] bytes = new byte[3];
		bytes[0] = (byte)(sum >>> 16);
		bytes[1] = (byte)(sum >>> 8);
		bytes[2] = (byte)(sum);
		return bytes;


	}
}