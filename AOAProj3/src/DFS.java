import java.util.ArrayList;
//import java.util.Arrays;

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
      while(currNode.visited){
        currNode = pop(toVisitStack);
        if(currNode == null){
          return count;
        }
      }
      count += 1;
      currNode.visited = true;
      //System.out.println("Currently At " + currNode.id);
      int[] edgeWeights = edges.getedges(currNode.id);
      int size = edgeWeights.length;

      //Check every possible edge to see if an edge exists between every vertice
      // and the current one
      int reverseIndex = size - 1;
      for(int i = 0; i < size; i++){
          //System.out.println("Currently At Edge " + i + "Weight: " + edgeWeights[i]);
          //Might be redundant... but just incase
          // i for this check currNode.id != i should be allVerts[i] for
          // readability

          //If the current Node Isnt itself, the weight between allVerts[i] and
          // allVerts[i] is not queued or visited. then add the edge to the
          // toVisitStack.
          if(currNode.id != reverseIndex &&
             edgeWeights[reverseIndex] != 0 &&
             !allVerts[reverseIndex].visited)
             //!allVerts[reverseIndex].queued)
             {
            push(toVisitStack, allVerts[reverseIndex]);
            //allVerts[reverseIndex].queued = true;//This node will be visited
            allVerts[reverseIndex].pred = currNode.id;//This node has a predecessor
          }
          reverseIndex--;
          //System.out.println(Arrays.toString(edgeWeights));

      }

  }while(toVisitStack.size() != 0);

  return count;

  }



//Put a new element in its correct position
  private static void push(ArrayList<Vertex> stack, Vertex element){
    if(stack.size() == 0){
      stack.add(0,element);
    //  System.out.println("Pushing " + element.id);
      return;
    }
  //  System.out.println("Pushing " + element.id);
    stack.add(0,element);
    return;


  }
  //Remove the front element from the Arraylist
  private static Vertex pop(ArrayList<Vertex> stack){
    if(stack.size() == 0){
      return null;
    }
    Vertex element = stack.get(0);
    stack.remove(0);
    return element;
  }




}
