import java.util.ArrayList;

public class Vertex{


  public int id;
  public int pred = -1;
  public boolean visited = false;
  public boolean queued = false;
  private int edgesSize = 0;

  public Vertex(int id){
    this.id = id;
  }

  public String toString(){
    String str = "Vertex " + id;

    return str;

  }

  public static boolean isEqual(Vertex vert1,Vertex vert2){
    return vert1.id == vert2.id;
  }


}
