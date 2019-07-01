import java.lang.Integer;


public class Compare{


  public static boolean lessThan(int[] vert1, int[] vert2){
      int VERTEX_LEFT_INDEX = 0;
      int VERTEX_RIGHT_INDEX = 1;
      int WEIGHT_INDEX = 2;

      if(vert1[WEIGHT_INDEX] < vert2[WEIGHT_INDEX]){
          return true;
      }
      else if(vert1[WEIGHT_INDEX] == vert2[WEIGHT_INDEX]){
        if(vert1[VERTEX_LEFT_INDEX] < vert2[VERTEX_LEFT_INDEX]){
            return true;
        }
        else if(vert1[VERTEX_LEFT_INDEX] == vert2[VERTEX_LEFT_INDEX]){
            if(vert1[VERTEX_RIGHT_INDEX] < vert2[VERTEX_RIGHT_INDEX]){
                return true;
            }
        }
      }
      return false;



  }



}
