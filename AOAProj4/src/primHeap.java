import java.lang.reflect.Array;
import java.util.Arrays;

public class primHeap {
	private final static int WEIGHT_INDEX = 2;
	
	
	
	private int[][] heap;
	private int size = 0;
	
	
	public primHeap(){
		heap = new int[64][];
	}
	
	public void insert(int[] node){
		if(size == heap.length - 1){
			growHeap();
		}
		
		heap[size] = node;
		size += 1;
		bubbleUP();
	}
	
	private void growHeap() {
		// TODO Auto-generated method stub
		this.heap = Arrays.copyOf(heap, heap.length * 2);
		
	}
	
	public int[] top(){
		return heap[0];
	}
	public int[] heapPOP(){
		int[] top = heap[0].clone();
		heap[0] = heap[size - 1].clone();
		heap[size - 1] = null;
		size -= 1;
		bubbleDOWN();
		
		return top;
		
	}
	
	
	public void swap(int index1, int index2){
		int[] ind1 = heap[index1].clone();
		heap[index1] = heap[index2];
		heap[index2] = ind1;
	}
	public void bubbleUP(){
		if(size == 0 || size == 1){
			return;
		}
		
		int[] currElement = heap[size];
		int parent;
		int index = size - 1;
		if((index & 1) == 0){//function??
			parent = (index - 2) >>> 1;
		}
		else{
			parent = (index - 1) >>> 1;
		}
		while(index > 0 && heap[index][WEIGHT_INDEX] < heap[parent][WEIGHT_INDEX]){
			
			swap(index,parent);
			index = parent;
			if((index & 1) == 0){//EVEN
				parent = (index - 2) >>> 1;
			}
			else{
				parent = (index - 1) >>> 1;
			}
			
			
		}
		
	}

	
	public String toString(){
		String theString = "Heap\n";
		for(int i = 0 ; i < size; i++){
			theString += Arrays.toString(heap[i]) + "\n";
		}
		return theString;
	}
	
	private int vertComp(int[] vert1, int[] vert2){
		if(vert1[WEIGHT_INDEX] > vert2[WEIGHT_INDEX]){
			return 1;
		}
		else if(vert1[WEIGHT_INDEX] < vert2[WEIGHT_INDEX]){
			return -1;
		}
		return 0;
		
	}
	public void bubbleDOWN(){
		int index = 0;
		int chIndL = (index << 1) + 1;
		int chIndR = (index << 1) + 2;
		int smaller;

		while(chIndL< size){
			smaller = chIndL;
			if(!(chIndR >= size)){
				if(vertComp(heap[chIndL],heap[chIndR]) > 0){
					smaller = chIndR;
				}
				if(vertComp(heap[chIndL],heap[chIndR]) == 0){
					smaller = chIndL;
				}
			}
			
			if(vertComp(heap[index], heap[smaller]) > 0){
				swap(index,smaller);
			}
			else{
				break;
			}
			index = smaller;
			chIndL = (index << 1) + 1;
			chIndR = (index << 1) + 2;
			
			
		}
	}
	public boolean isEmpty(){
		return size == 0;
	}
		
	
	
	
}
