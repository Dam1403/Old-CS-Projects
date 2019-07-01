import java.util.LinkedList;

public class Queue{

  private LinkedList<int[]> array;
  private int size;

  public Queue(){
    array = new LinkedList<int[]>();
    size = 0;
  }


  public void queue(int[] element){
    if(array == null){
      array = new LinkedList<int[]>();
      size = 0;
    }
    array.add(element);
    size++;

  }
  public int[] dequeue(){
    size--;
    return array.remove();
  }

  public int size(){
    return size;
  }



}
