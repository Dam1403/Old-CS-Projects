import java.util.ArrayList;

public class DFS{
  private static int numNodes;
  private static int[][] predators;

  public static int countDFS(Edges edges, Vertex[] allVerts){

    predators = new int[allVerts.length][];


    //Not truly a stack, Its more like an ordered queue
    //But this helps me think about dfs.
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

      //Check every possible edge to see if an edge exists between every vertice
      // and the current one

      for(int i = 0; i < size; i++){
          //Might be redundant... but just incase
          // i for this check currNode.id != i should be allVerts[i] for
          // readability

          //If the current Node Isnt itself, the weight between allVerts[i] and
          // allVerts[i] is not queued or visited. then add the edge to the
          // toVisitStack.
          if(currNode.id != i &&
             edgeWeights[i] != 0 &&
             !allVerts[i].visited &&
             !allVerts[i].queued)
             {
            push(toVisitStack, allVerts[i]);
            allVerts[i].queued = true;//This node will be visited
            allVerts[i].pred = currNode.id;//This node has a predecessor
          }

      }

  }while(toVisitStack.size() != 0);

  return count;

  }



//Put a new element in its correct position
  private static void push(ArrayList<Vertex> stack, Vertex element){
    if(stack.size() == 0){
      stack.add(0,element);
      return;
    }

    //Loop through the list until you find
    //the correct position for the element
    for(int i = 0; i < stack.size(); i++){
      if(element.id < stack.get(i).id){
        stack.add(i,element);
        return;
      }
    }
    stack.add(element);
    return;


  }
  //Remove the front element from the Arraylist
  private static Vertex pop(ArrayList<Vertex> stack){
    Vertex element = stack.get(0);
    stack.remove(0);
    return element;
  }




}
