import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class SearchTree {
	
	Scanner t = new Scanner(System.in);
	
	Vertex root;
	int[][] goal_state;
	
	public SearchTree(Vertex root, int[][] goal_state) {
		// TODO Auto-generated constructor stub
		
		this.root = root;
		this.goal_state = goal_state;
		
	}

	public Vertex getRoot() {
		return root;
	}

	public void setRoot(Vertex root) {
		this.root = root;
	}

	public int[][] getGoal_state() {
		return goal_state;
	}

	public void setGoal_state(int[][] goal_state) {
		this.goal_state = goal_state;
	}
	
	
	
	
	//Search Algorithms
	
	public void uniformCostSearch() {
        
        ArrayList<int[][]> states = new ArrayList<int[][]>();
        
        int totalCost = 0;
        int count = 0;
        
        Vertex vertex = new Vertex(root.getState());
        
        vertex.setCost(0);
        
        VertexComparator vertexCompare = new VertexComparator();
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>(10, vertexCompare);
        Vertex current_vertex = vertex;
        
        boolean check = checkIfEqual(current_vertex.getState(),goal_state);
        
       
        while(check != true) {        	
        	states.add(current_vertex.getState());
        	
        	List<int[][]> successorsOfVertex = Vertex.getSuccessors(current_vertex.getState());
        	
        	for(int[][] successor : successorsOfVertex)
        	{
        		
        		if(states.contains(successor))
        			continue;
        		states.add(successor);
        		Vertex child = new Vertex(successor);
        		current_vertex.addChildren(child);
        		child.setParent(current_vertex);
        		int value; 
        		
                 //So, I need the value in child for the index for which the parent has 0 stored;
        		//So, search a 2d array of parent for index of 0
        		//Then return value in child for that index?
        		
        		int index[] = new int [2];
        		index = getIndexOfZeroFromParent(current_vertex);
        		
        		int c[][] = child.getState();
        		value = c[index[0]][index[1]]; 
        		
        		child.setTotalCost((current_vertex.getTotalCost()+value),0); //hardcoded estimatedCost as 0 for uniform cost search
        		vertexQueue.add(child);	
        		
        	}
        	current_vertex = vertexQueue.poll();
        	//check if this is goal
        	check = checkIfEqual(current_vertex.getState(),goal_state); 	
        	count += 1;

		}
        
        
        printSolution(current_vertex,states,root,count);
        //prints solution   
        //prints step by step board state after ever move
        //print no. of nodes popped from queue
	}

	

	public void aStarSearch(int choice) {
		
        ArrayList<int[][]> states = new ArrayList<int[][]>();
        int totalCost = 0;
        int count = 0;
        
        Vertex vertex = new Vertex(root.getState());
        vertex.setCost(0);
        
        VertexComparator vertexCompare = new VertexComparator();
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>(10, vertexCompare);
        Vertex current_vertex = vertex;
         
        boolean check = checkIfEqual(current_vertex.getState(),goal_state);
        
        while(check != true) {
			states.add(current_vertex.getState());
		List<int[][]> successorsOfVertex = Vertex.getSuccessors(current_vertex.getState());
		
			for(int[][] successor : successorsOfVertex)
	    	{
	    		if(states.contains(successor))
	    			continue;
	    		states.add(successor);
	    		Vertex child = new Vertex(successor);
	    		current_vertex.addChildren(child);
	    		child.setParent(current_vertex);
	    		
	    		int value;
	    		
	    		int index[] = new int [2];
	    		index = getIndexOfZeroFromParent(current_vertex);
	    		
	    		int c[][] = child.getState();
	    		value = c[index[0]][index[1]];
	    		
	    		if(choice == 2)
	    			child.setTotalCost((current_vertex.getTotalCost()+value),misplacedTilesHeuristic(child.getState(),goal_state)); 
	    		else if(choice == 3)
	    			child.setTotalCost((current_vertex.getTotalCost()+value),manhattanDistanceHeuristic(child.getState(),goal_state)); 
	    		vertexQueue.add(child);		
	    		
	    	}	
		
			current_vertex = vertexQueue.poll(); 
			check = checkIfEqual(current_vertex.getState(),goal_state); 
			count += 1;	
		}
		
		printSolution(current_vertex,states,root,count);
		//print solution   
        //print step by step board state after ever move
        //print no. of nodes popped from queue
		
	}
	
	private boolean checkIfEqual(int[][] state, int[][] goal_state2) {
		// TODO Auto-generated method stub
		
		
		for(int i = 0; i<state.length; i++)
			for(int j = 0; j<state.length; j++)
				if(state[i][j] != goal_state2[i][j])
					return false;
		return true;
	}
	
	
	private void printSolution(Vertex goalVertex, ArrayList<int[][]> visitedStates, Vertex root2, int count) {
		// TODO Auto-generated method stub
		int cost = 0;
		int totalCost = 0;
		

        Stack<Vertex> solutionStack = new Stack<Vertex>();
        solutionStack.push(goalVertex);
        while (!goalVertex.getState().equals(root2.getState())) {
            solutionStack.push(goalVertex.getParent());
            goalVertex = goalVertex.getParent();
        }
		int[][] source = root2.getState();
		int[][] destination = new int[source.length][source.length];
		int[][] ss = new int[source.length][source.length];
		int[] index = new int[2];
		 for (int i = solutionStack.size() - 1; i >= 0; i--)
		 {
			 destination = solutionStack.get(i).getState();
			 if(Arrays.deepEquals(source, destination))
			 {
				 index = getIndexOfZeroFromParent(root2);
				 cost = destination[index[0]][index[1]];
				 totalCost += cost;
			 }
			 source = destination;
			 //System.out.println("Cost: "+cost);
			 System.out.println("---------");
			 ss = solutionStack.get(i).getState();
			 for(int j=0; j<ss.length; j++) {
				 for(int k=0; k<ss.length; k++)
					 System.out.print(ss[j][k]+" ");
				 System.out.println();
			 }
			 
        
		 }
		 
		 System.out.println("Number of visited states:  " + (visitedStates.size()));
		 System.out.println("Number of transitions:  " + (solutionStack.size() - 1));
		 System.out.println("Total cost: " + totalCost);
		 System.out.println("Number of Nodes poped out of the queue: " + count);
		 
	}

	private int manhattanDistanceHeuristic(int[][] state, int[][] goal_state2) {
		// TODO Auto-generated method stub
	    int difference = 0;
	    int n = state.length;
	    for(int i=0; i<n; i++)
	    	for(int j=0; j<n; j++)
	    	{
	    		for(int m=0; m<n; m++)
	    			for(int l=0; l<n; l++)
	    				if(state[i][j] == goal_state2[m][l])
	    					difference += Math.abs(i-m) + Math.abs(j-l);
	    	}
		return difference;
	}

	private int misplacedTilesHeuristic(int[][] state, int[][] goal_state2) {
		// TODO Auto-generated method stub
		int difference = 0;
        for(int i=0; i<state.length; i++)
        	for(int j=0; j<state.length; j++)
        		if(state[i][j] != goal_state2[i][j])
        			difference += 1;
        return difference;
	}

	private int[] getIndexOfZeroFromParent(Vertex current_vertex) {
		// TODO Auto-generated method stub
		int parent[][] = current_vertex.getState();
		int index[] = new int[2];
		for(int i=0; i<parent.length; i++)
			for(int j=0; j<parent.length; j++)
			{
				if(parent[i][j] == 0)
				{
					index[0] = i;
					index[1] = j;
				}	
			}	
		return index;
	}
	
	private static void printBoard(int[][] board) {
		// TODO Auto-generated method stub
		System.out.println("Printing board...");	
			for(int i=0; i<board.length;i++) {
				for(int j=0; j<board.length;j++)
					System.out.print(" "+board[i][j]);
				System.out.println();
			
			}
	}

	
	
	

}
