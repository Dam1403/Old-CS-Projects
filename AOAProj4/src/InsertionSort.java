import java.util.ArrayList;

public class InsertionSort{



  public static void sort(ArrayList<int[]> vertList){
    int VERTEX_LEFT_INDEX = 0;
    int VERTEX_RIGHT_INDEX = 1;
    int WEIGHT_INDEX = 2;


      for(int i = 0; i < vertList.size() - 1; i++){
          int j = i + 1;
          int[] curr = vertList.get(j);

          while(j > 0 && Compare.lessThan(curr,vertList.get(j-1))){
              swap(vertList, j , j -1);
              j--;
          }

      }
    return;

  }

  private static void swap(ArrayList<int[]> vertList, int a, int b){
    int[] tmp = vertList.get(a);
    vertList.set(a,vertList.get(b));
    vertList.set(b,tmp);
  }








}
