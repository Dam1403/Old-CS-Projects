import java.util.ArrayList;
import java.util.Arrays;

public class CountSort{


  public static ArrayList<int[]> sort(ArrayList<int[]>vertList,int n){

    int VERTEX_LEFT_INDEX = 0;
    int VERTEX_RIGHT_INDEX = 1;
    int WEIGHT_INDEX = 2;

    //int[] countArr = new int[n];
    Queue[] orderStorage = new Queue[n];
    for(int i = 0; i < n; i++){
      orderStorage[i] = new Queue();
    }
    ArrayList<int[]> result = new ArrayList<int[]>();

    for(int i = 0; i < vertList.size(); i++){
      int[] vert = vertList.get(i);
      //countArr[vert[WEIGHT_INDEX] - 1] += 1;
      orderStorage[vert[WEIGHT_INDEX] - 1].queue(vert);
    }

    for(int i = 0; i < n; i++){
      while(orderStorage[i].size() > 0){
        int[] vert = orderStorage[i].dequeue();
        result.add(vert);
      }
      //System.out.println(orderStorage[i]);
    }
    return result;










  }







}
