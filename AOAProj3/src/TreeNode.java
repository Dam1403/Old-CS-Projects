public class TreeNode{
  private static int VERTEX_LEFT_INDEX = 0;
  private static int VERTEX_RIGHT_INDEX = 1;
  private static int WEIGHT_INDEX = 2;


  public int id;
  public int root;
  public int rank;




  public TreeNode(int nID){
    id = nID;
    root = nID;
    rank = 0;

  }

  public TreeNode(){
  }

  public static boolean isEqual(TreeNode node1, TreeNode node2){
    return node1.id == node2.id;
  }
  
  public static TreeNode find(TreeNode node,TreeNode[] allNodes){
	  
	  TreeNode origNode = node;
	  while(node.id != node.root){
		  node = allNodes[node.root];
	  }
	  origNode.root = node.id;
	  return node;
  }
  
  public String toString(){
	  return "ID: " + this.id + " rank: " + this.rank + " root: " + this.root;
  }

}
