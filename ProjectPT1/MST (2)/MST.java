import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;


import java.io.File;
import java.io.FileNotFoundException;

import java.lang.Integer;

public class MST{
	private static Edges edges;
	private static Vertex[] allVerts;

	public static void main(String[] args){

		if(args.length != 1){
			System.out.println("Usage: java MST <filename>" );
			return;
		}
		String filename = args[0];

		Scanner input = null;
		int n = 0;
		double p = 0.0;
		Long seed = 0L;

		try {
				 input = new Scanner(new File(filename));
				 System.out.println("\n");
				 n = input.nextInt();
		 		 seed = input.nextLong();
		}
		catch(FileNotFoundException e){
				System.out.println("Input file not found");
				return;
		}
		catch(InputMismatchException e){
			  System.out.println("n and seed must be integers");
				return;
		}

		if(n < 2){
			System.out.println("n must be greater than 1");
			return;
		}
		try{
			p = input.nextDouble();
		}
		catch(InputMismatchException e){
			System.out.println("p must be a real number");
			return;
		}

		if(p > 1 | p < 0){
			System.out.println("p must be between 0 and 1");
			return;
		}


		Random randGen = new Random(seed);
    Random weightGen = new Random(seed*2);
		System.out.println("TEST: " + "n="+n + ", seed="+seed + ", p="+p);



		long startTime = System.currentTimeMillis();
    do{
			edges = new Edges(n);
			allVerts = null;
			allVerts = genGraph.genGraph(n,p,seed,edges,allVerts,randGen,weightGen);
		}while(DFS.countDFS(edges,allVerts) != n);
		long endTime = System.currentTimeMillis();

		long elasped = endTime - startTime;
		System.out.println("Time to generate the graph: "+ elasped + " milliseconds\n");
		printAdjacencyMatrix(n);
		printAdjacencyList(n);
		if(n > 10){return;}
		System.out.println("Depth-First Search:\n");
		String vertices = "Vertices:  \n";
		String preds = "Predecessors: \n";
	  for(int i = 0; i < allVerts.length; i++){
	    vertices += " " + i;
			preds += allVerts[i].pred + " ";
	  }

		System.out.println(vertices);
		System.out.println(preds);


	}

	public static void printAdjacencyMatrix(int n){
		if(n > 10){return;}
		System.out.println("The graph as an adjacency matrix:\n");
		for(int i = 0; i< n; i++){
			System.out.println("");
			String line = "";
			for(int j = 0; j < n; j++){
				int weight = edges.getedge(i,j);
				line +=  " " + weight + " ";
			}
			System.out.println(line);

		}
		System.out.println("");
	}
	public static void printAdjacencyList(int n){
		if(n > 10){return;}
		System.out.println("The graph as an adjacency list:");
		String line = "";
		for(int i = 0; i < n; i++){
			line += i + "->";
			for(int j = 0; j < n;j++){
				int weight = edges.getedge(i,j);
				if(weight != 0){
						line += " "+ j + "(" + weight + ")";
				}
			}
			line += "\n";
		}
		System.out.print(line);


	}

}
