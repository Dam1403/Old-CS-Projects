import java.math.BigInteger;
/*
 * CrackRSA uses pollard's rho algorithm to find the two primes and private exponent 
 * given the rsa public key(an exponent and a small modulus)
 */
public class CrackRsa {
	private static BigInteger exponent;
	private static BigInteger modulusN;
	public static void main(String args[]){
		if(args.length != 2){
			System.out.println("Usage: CrackRsa <exponent> <modulusN>");
			return;
		}
		try{
			exponent = new BigInteger(args[0]);
			modulusN = new BigInteger(args[1]);
		}
		catch(NumberFormatException e){
			System.out.println("One of these is not an integer " + args[0] + " " + args[1]);
			return;
		}
		
		//REMEMBER THAT D HERE ISNT THE PRIVATE KEY
		//D HERE IS ONE OF THE PRIMES OF N
		BigInteger d = new BigInteger("-1");
		BigInteger ONE = new BigInteger("1");
		BigInteger a = new BigInteger("2");
		BigInteger b = new BigInteger("2");
		
		boolean foundD = false;
		for(int i = 0;d.compareTo(modulusN) != 0;i++){
			a = a.pow(2).mod(modulusN).add(ONE);
			b = b.pow(2).mod(modulusN).add(ONE);
			b = b.pow(2).mod(modulusN).add(ONE);
			
			//System.out.println(a.toString() + "  " + b.toString());
			
			d = a.subtract(b).gcd(modulusN);
			
			if(d.compareTo(ONE) == 1){
				foundD = true;
				break;
			}
			
		}
		if(!foundD){
			System.out.println("Failure");
			return;
		}
		BigInteger p = d;
		BigInteger q = modulusN.divide(d);
		
		int compared = p.compareTo(q);
		if( compared == 1 || compared == 0){
			System.out.println(q.toString());
			System.out.println(p.toString());
		}
		else{
			System.out.println(p.toString());
			System.out.println(q.toString());		
		}
		
		BigInteger TotN = p.subtract(ONE).multiply(q.subtract(ONE));
		BigInteger privD = exponent.modInverse(TotN);
		System.out.println(privD.toString());
		
	}

}
