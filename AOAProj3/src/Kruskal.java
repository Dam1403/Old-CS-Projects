import java.util.ArrayList;
public class Kruskal{

  private static int VERTEX_LEFT_INDEX = 0;
  private static int VERTEX_RIGHT_INDEX = 1;
  private static int WEIGHT_INDEX = 2;
  private static TreeNode[] nodeStorage;
  private static int mstEdgeCount = 0;
  private static int mstEdgeSum = 0;




  public static void initNodes(int numVerts){
    nodeStorage = new TreeNode[numVerts];
    for(int i = 0; i < numVerts; i++){
      TreeNode newNode = new TreeNode(i);
      nodeStorage[i] = newNode;
    }

  }

  private static boolean Union(TreeNode node1, TreeNode node2){
    //System.out.println("NODES:\n" + node1 + "\n" + node2);
    node1 = TreeNode.find(node1, nodeStorage);
    node2 = TreeNode.find(node2, nodeStorage);
    //System.out.println("ROOTS:\n" + node1 + "\n" + node2 + "\n");
    
    if(node1.root == node2.root){
    	return false;
    }
    if(node1.rank == node2.rank){
      //PICK THE LEFT ONE
      node2.root = node1.root;
      node1.rank += 1;
      
      
    }
    else if(node1.rank > node2.rank){
      node2.root = node1.root;
    }
    else{
      node1.root = node2.root;
    }
    mstEdgeCount++;
    return true;

  }

  public static void printRoots(){
    for(int i = 0; i < nodeStorage.length;i++){
      TreeNode node = nodeStorage[i];
      System.out.println("NODE: " + i + " rank: " + node.rank + " root: " + node.root);
    }
  }

  public static ArrayList<int[]> returnMST(ArrayList<int[]> edges){
    int index = 0;
    ArrayList<int[]> result = new ArrayList<int[]>();
    while(mstEdgeCount < nodeStorage.length - 1){
      int[] currEdge = edges.get(index);
      TreeNode leftNode = nodeStorage[currEdge[VERTEX_LEFT_INDEX]];
      TreeNode rightNode = nodeStorage[currEdge[VERTEX_RIGHT_INDEX]];
      
      if(Union(leftNode,rightNode)){
        mstEdgeSum += currEdge[WEIGHT_INDEX];
        result.add(currEdge);
        
      }
      index++;

    }
    
    result.add(new int[]{mstEdgeSum});
    mstEdgeCount = 0;
    mstEdgeSum = 0;
    return result;
  }







}
