import java.util.ArrayList;

public class DFS{
  private static int numNodes;
  private static int[][] predators;

  public static int countDFS(Edges edges, Vertex[] allVerts){

    predators = new int[allVerts.length][];


    //ArrayList<Vertex> Histstack = new ArrayList<Integer>();
    ArrayList<Vertex> toVisitStack = new ArrayList<Vertex>();
    int count = 0;



    Vertex currNode;
    push(toVisitStack,allVerts[0]);

    do{
      currNode = pop(toVisitStack);
      count += 1;
      currNode.visited = true;

      int[] edgeWeights = edges.getedges(currNode.id);
      int size = edgeWeights.length;

      for(int i = 0; i < size; i++){
          //Might be redundant... but just incase
          if(currNode.id != i && edgeWeights[i] != 0 && !allVerts[i].visited && !allVerts[i].queued){
            push(toVisitStack, allVerts[i]);
            allVerts[i].queued = true;
            allVerts[i].pred = currNode.id;
          }

      }

  }while(toVisitStack.size() != 0);

  return count;

  }

  private static void push(ArrayList<Vertex> stack, Vertex element){
    if(stack.size() == 0){
      stack.add(0,element);
      return;
    }


    for(int i = 0; i < stack.size(); i++){
      if(element.id < stack.get(i).id){
        stack.add(i,element);
        return;
      }
    }
    stack.add(element);
    return;


  }

  private static Vertex pop(ArrayList<Vertex> stack){
    Vertex element = stack.get(0);
    stack.remove(0);
    return element;
  }




}
