import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


public class Prim {
	
	private static boolean[] Visited;
	
	private static primHeap theHeap;
	
	
	private final static int LEFT_INDEX = 0;
	private final static int RIGHT_INDEX = 1;
	private final static int WEIGHT_INDEX = 2;
	
	
	
	
	public static ArrayList<int[]>  doPrimStuff(ArrayList<int[]> edges, int numNodes){
		ArrayList<int[]> theEdges = formatEdges(edges);
		ArrayList<int[]> mstEdges = new ArrayList<int[]>();
		Visited = new boolean[numNodes];
		theHeap = new primHeap();
		int current = 0;
		addEdges(theEdges,current);
		Visited[0] = true;
		while(!theHeap.isEmpty()){
			//System.out.println(theHeap.toString());
			int[] currElem = theHeap.heapPOP();
			if(isVisited(currElem)){
				continue;
			}
			//System.out.println("Current: " +Arrays.toString(currElem));
			Visited[currElem[LEFT_INDEX]] = true;
			int foreignEdge = currElem[RIGHT_INDEX];
			addEdges(theEdges,foreignEdge);
			Visited[currElem[RIGHT_INDEX]] = true;
			mstEdges.add(currElem);
			
		};
	
		return mstEdges;
	}
	
	

	/*public static ArrayList<int[]> doPrimStuff(Edges edges){
		ArrayList<int[]> theEdges = formatEdges(edges.getAllEdges());
		int numNodes = edges.getNumNodes();
		Visited = new boolean[numNodes];
		theHeap = new primHeap();
		int current = 0;
		addEdges(theEdges,current);
		Visited[0] = true;
		while(!theHeap.isEmpty()){
			//System.out.println(theHeap.toString());
			int[] currElem = theHeap.heapPOP();
			if(isVisited(currElem)){
				continue;
			}
			//System.out.println("Current: " +Arrays.toString(currElem));
			Visited[currElem[LEFT_INDEX]] = true;
			int foreignEdge = currElem[RIGHT_INDEX];
			addEdges(theEdges,foreignEdge);
			Visited[currElem[RIGHT_INDEX]] = true;
			mstEdges.add(currElem);
			
		};
	
		return mstEdges;
	}
	*/
	
	
	public static void addEdges(ArrayList<int[]> edges,int currNode){
		for(int i = 0; i < edges.size();i++){
			int[] currElem = edges.get(i);
			if(currElem[LEFT_INDEX] == currNode){
				//System.out.println("Adding: " + Arrays.toString(currElem));
				theHeap.insert(currElem);
	
			}
		}
	}
	
	private static ArrayList<int[]> formatEdges(ArrayList<int[]> edges){
		int currSize = edges.size();
		ArrayList<int[]> newEdges = new ArrayList<int[]>();
		
		for(int i = 0; i < currSize; i++){
			int[] edge = edges.get(i);
			newEdges.add(edges.get(i));
			newEdges.add(new int[]{edge[1],edge[0],edge[2]});
		}
		return newEdges;
		
	}
	
	public static LinkedList<int[]> sortedPlace(LinkedList<int[]> list,int[] element){
		if(list.size() == 0){
			list.add(element);
			return list;
		}
		
		for(int i = 0; i < list.size(); i++){
			if(element[WEIGHT_INDEX] < list.get(i)[WEIGHT_INDEX]){
				list.add(i, element);
				return list;
			}
		}
		list.add(element);
		return list;
		
	}
	
	private static boolean isVisited(int[] element){
		if(Visited[element[LEFT_INDEX]]){
			if(Visited[element[RIGHT_INDEX]]){
				return true;
			}
		}
		return false;
	}

}
