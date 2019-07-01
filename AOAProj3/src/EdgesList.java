import java.util.ArrayList;


public class EdgesList{

  private static ArrayList<int[]> edges = new ArrayList<int[]>();



  public static void addedge(int vert1, int vert2,int weight){
	int[] newEntry = new int[] {vert1,vert2,weight};
	if(edges.contains(newEntry)){
		return;
	}
    edges.add(new int[] {vert1,vert2,weight});
  }


  public static int getedge(int vert1, int vert2){
    for(int i = 0; i < edges.size();i++){
      int[] vertAr = edges.get(i);
      if(vertAr[0] == vert1 && vertAr[1] == vert2){
        return vertAr[2];
      }
    }
    return -1;
  }

  public static ArrayList<int[]> getedges(int vert){
    ArrayList<int[]> result = new ArrayList<int[]>();
    for(int i = 0; i < edges.size(); i++){
      int[] vertAr = edges.get(i);
      if(vertAr[0] == vert){
        result.add(vertAr);
      }
    }
    return result;
  }

  public static ArrayList<int[]> getAllEdges(){
    int VERTEX_LEFT_INDEX = 0;
    int VERTEX_RIGHT_INDEX = 1;
    int WEIGHT_INDEX = 2;

    ArrayList<int[]> edgecopy = new ArrayList<int[]>();
    for(int i = 0; i < edges.size();i++){
        int[] vert = edges.get(i);
        int left = vert[VERTEX_LEFT_INDEX];
        int right = vert[VERTEX_RIGHT_INDEX];
        int weight = vert[WEIGHT_INDEX];
        edgecopy.add(new int[]{left,right,weight});
    }
    return edgecopy;
  }
  
  public static void purgeEdges(){
	  edges = new ArrayList<int[]>();
  }








}
