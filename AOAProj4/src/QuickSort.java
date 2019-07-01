import java.util.ArrayList;
import java.util.Arrays;

public class QuickSort {

  public static int partition(ArrayList<int[]> vertList, int left, int right)
    {
          int[] pivot = vertList.get(left);    
          int leftRet = left;
          for(int i = left + 1; i <= right; i++){
            if(Compare.lessThan(vertList.get(i),pivot)){
                leftRet = leftRet + 1;
                swap(vertList,i,leftRet);
            }
          }
          swap(vertList,left,leftRet);
          return leftRet;
          /*

          */
    }

  public static void sort(ArrayList<int[]> vertList, int left, int right) {
        if(left < right){
          int pivot = partition(vertList, left, right);
          sort(vertList,left,pivot);
          sort(vertList,pivot + 1, right);
        }
  }

  private static void swap(ArrayList<int[]> vertList, int a, int b){
    int[] tmp = vertList.get(a);
    vertList.set(a,vertList.get(b));
    vertList.set(b,tmp);
  }






}
