import java.util.ArrayList;

public class Edges{


  private int[][] edgeTable;


  public Edges(int n){
    edgeTable = new int[n][n];
  }


  public void addedge(int vert1, int vert2,int weight){
    if(edgeTable[vert1][vert2] != 0){
      return;
    }
    edgeTable[vert1][vert2] = weight;
  }
  public int getedge(int vert1, int vert2){
    return edgeTable[vert1][vert2];
  }

  //return the edgeweights for a particular vertice
  //an edgeweight of zero means no edge exists
  // the indices represent the other vertices
  public int[] getedges(int vert){
    int[] edges = new int[edgeTable.length];
    for(int i = 0; i < edgeTable.length; i++){
      int edgeWeight = edgeTable[vert][i];
      if(edgeWeight != 0){
        edges[i] = edgeWeight;
      }
    }
    return edges;
  }

  public ArrayList<int[]> getAllEdges(){
    ArrayList<int[]> allEdges = new ArrayList<int[]>();
    for(int i = 0; i < edgeTable.length; i++){
      for(int j = i + 1; j < edgeTable.length; j++){
        if(edgeTable[i][j] != 0){
            allEdges.add(new int[]{i,j,edgeTable[i][j]});
        }
      }

    }
    return allEdges;
  }
  
  public int getNumNodes(){
	  return edgeTable.length;
  }








}
