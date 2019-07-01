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









}
