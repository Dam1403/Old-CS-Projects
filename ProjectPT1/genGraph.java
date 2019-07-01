import java.util.ArrayList;
import java.util.Random;

public class genGraph{

  private static int numOfNodes;
  private static double probability;
  private static long seed;


  public static Vertex[] genGraph(
    int n,
    double p,
    long s,
    Edges edges,
    Vertex[] allVerts,
    Random randGen,
    Random weightGen)
    {


    numOfNodes = n;
    probability = p;
    seed = s;
    allVerts = new Vertex[n];
    ArrayList<Vertex> vertex = new ArrayList<Vertex>();


    for(int i = 0; i < n; i++){
      Vertex newV = allVerts[i];
      if(newV == null){
        //here you can tell if the graph is connected
        //optimize later
        newV = new Vertex(i);
        allVerts[i] = newV;
      }

      for(int j = i + 1; j < n; j++){
        //Are these two nodes connected?
        if(randGen.nextDouble() <= probability){

          //Grab the edge from the vertice master list
          Vertex newEdge = allVerts[j];

          //If this edge hasn't been grabbed yet
          //Create it
          if(newEdge == null){
            newEdge = new Vertex(j);
            allVerts[j] = newEdge;
          }

          //If this edge doesnt have a weight yet generate it
          //Redundant!
          if(edges.getedge(allVerts[i].id, allVerts[j].id) == 0){
            int rand = weightGen.nextInt(n)+1;
            edges.addedge(allVerts[i].id, allVerts[j].id, rand);
            edges.addedge(allVerts[j].id, allVerts[i].id, rand);
          }

        }

      }

    }
    //return the master list
    return allVerts;


  }
}
