import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vertex {
	
	boolean visited;
    int[][] state;
    int cost;
    int estimatedCostToGoal;
    int totalCost;
    int depth;
	
    ArrayList<Vertex> children;
    Vertex parent;
    
    public Vertex(int[][] state) {
		// TODO Auto-generated constructor stub
    	
    	this.state = state;
    	this.children = new ArrayList<Vertex>();
    	
	}
    
    public int[][] getState() {
    	//System.out.println("THIS IS GET STATE");
    	//System.out.println(state[0][0]);
    	return state;
    }

    public ArrayList<Vertex> getChildren() {
        return children;
    }
     
    public void addChildren(Vertex c) {
        children.add(c);
    }
    
	public boolean isVisited() {
		return visited;
	}

	public int getCost() {
		return cost;
	}

	public int getEstimatedCostToGoal() {
		return estimatedCostToGoal;
	}

	public int getTotalCost() {
		return totalCost;
	}
	
	public void setTotalCost(int cost, int estimatedCost) {
        this.totalCost = cost + estimatedCost;
    }

	public int getDepth() {
		return depth;
	}

	public Vertex getParent() {
		return parent;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public void setState(int[][] state) {
		this.state = state;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setEstimatedCostToGoal(int estimatedCostToGoal) {
		this.estimatedCostToGoal = estimatedCostToGoal;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public void setChildren(ArrayList<Vertex> children) {
		this.children = children;
	}

	public void setParent(Vertex parent) {
		this.parent = parent;
	}
    
	private static int[] getIndexOfZero(int[][] c) {
		// TODO Auto-generated method stub
		//int parent[][] = current_vertex.getState();
		int index[] = new int[2];
		
		//System.out.println("888888888"+c.length);
		
		for(int i=0; i<c.length; i++)
			for(int j=0; j<c.length; j++)
			{
				if(c[i][j] == 0) {
					index[0] = i;
					index[1] = j;
				}
			}	
		//System.out.println(index[0]+" "+index[1]);
		
		return index;
	}

	private static int[][] swap(int A0, int A1, int B0, int B1, int[][] state) {
		// TODO Auto-generated method stub
		
		//int s[][] = state.getState();
		
//		System.out.println("In swap, got this state to work with---");
//		printBoard(state);
		//int[] indexA = new int[2];
		//int[] indexB = new int[2];
		
//		String a0 = Character.toString(A.charAt(0));
//		String a1 = Character.toString(A.charAt(1));
//		String b0 = Character.toString(B.charAt(0));
//		String b1 = Character.toString(B.charAt(0));
//		
//		int A0 = Integer.parseInt(a0);
//		int A1 = Integer.parseInt(a1);
//		int B0 = Integer.parseInt(b0);
//		int B1 = Integer.parseInt(b1);
		
		//A = "00" B = "01"
//		indexA[0] = A.charAt(0); //0
//		indexA[1] = A.charAt(1); //0
//		indexB[0] = B.charAt(0); //0
//		indexB[1] = B.charAt(1); //1
		
		//System.out.println(indexA[0]+"-"+indexA[1]);
		//System.out.println(indexA[0]+"-"+indexA[1]);
		
		int temp;
		int[][] swappedState = new int[3][3];
		
		//swappedState = state;
		for(int i=0; i<state.length; i++)
			for(int j=0; j<state.length; j++)
				swappedState[i][j] = state[i][j];
			
//		System.out.println("Copy of state:");
//		printBoard(swappedState);
		
//		temp = state[indexA[0]][indexA[1]];
//		state[indexA[0]][indexA[1]] = state[indexB[0]][indexB[1]];
//		state[indexB[0]][indexB[1]] = temp;
			
		temp = swappedState[A0][A1];
		swappedState[A0][A1] = swappedState[B0][B1];
		swappedState[B0][B1] = temp;
			
//		System.out.println("Swapped state i.e. the successor...");
//		printBoard(swappedState);
//		
		return swappedState;
	}
	
	
	public static List<int[][]> getSuccessors(int[][] state) {
        List<int[][]> successors = new ArrayList<int[][]>();
      //write how to get successors for different cases
        //int[][] s = state.getState();
        int length = state.length;
        int index[] = new int[2];
        index = getIndexOfZero(state);
        
        String ind = Arrays.toString(index);
        //System.out.println("HELLO INsis");
        //System.out.println(ind);
        
//        System.out.println("This is state for which i need successors");
//        printBoard(state);
        
        
        switch(ind)
        {
        	case "[0, 0]":
//        				successors.add(swap("00","01",state));
//        				successors.add(swap("00","10",state));
        		
//        				int temp;
//        				
//        				temp = state[0][0];
//        				state[0][0] = state[0][1];
//        				
        		
        		
        		
        				successors.add(swap(0,0,0,1,state));
        				successors.add(swap(0,0,1,0,state));
        				break;
        				
        	case "[0, 2]":
//						successors.add(swap("02","01",state));
//						successors.add(swap("02","12",state));
						
						successors.add(swap(0,2,0,1,state));
						successors.add(swap(0,2,1,2,state));
						
						break;	
        	
        	case "[2, 0]":
//						successors.add(swap("20","10",state));
//						successors.add(swap("20","21",state));
//						
						successors.add(swap(2,0,1,0,state));
						successors.add(swap(2,0,2,1,state));
						
						
						break;
        	
        	case "[2, 2]":
//						successors.add(swap("22","12",state));
//						successors.add(swap("22","21",state));
//						
						successors.add(swap(2,2,1,2,state));
						successors.add(swap(2,2,2,1,state));
						
						break;
						
        	case "[1, 1]":
//		        		successors.add(swap("11","01",state));
//						successors.add(swap("11","10",state));
//						successors.add(swap("11","21",state));
//						successors.add(swap("11","12",state));
						
//        		int[][] temp = swap(1,1,0,1,state);
//        		successors.add(temp);
//        		System.out.println("@@@");
//        		printBoard(temp);
        		
        		
						successors.add(swap(1,1,0,1,state));
//						System.out.println("&&&&");
//						printBoard(successors.get(0));
						
						successors.add(swap(1,1,1,0,state));
						successors.add(swap(1,1,2,1,state));
						successors.add(swap(1,1,1,2,state));
						
						
						break;
        	
        	case "[1, 0]":
//		        		successors.add(swap("10","00",state));
//						successors.add(swap("10","11",state));
//						successors.add(swap("10","20",state));
						
						successors.add(swap(1,0,0,0,state));
						successors.add(swap(1,0,1,1,state));
						successors.add(swap(1,0,2,0,state));
						
						
						break;
		        		
        	case "[0, 1]":
//		        		successors.add(swap("01","00",state));
//						successors.add(swap("01","11",state));
//						successors.add(swap("01","02",state));	
						
						successors.add(swap(0,1,0,0,state));
						successors.add(swap(0,1,1,1,state));
						successors.add(swap(0,1,0,2,state));	
						
						
						break;
        		
        	case "[1, 2]":
//		        		successors.add(swap("12","02",state));
//						successors.add(swap("12","11",state));
//						successors.add(swap("12","22",state));	
						
						successors.add(swap(1,2,0,2,state));
						successors.add(swap(1,2,1,1,state));
						successors.add(swap(1,2,2,2,state));	
						
						break;
        		
        	case "[2, 1]":
//		        		successors.add(swap("21","20",state));
//						successors.add(swap("21","11",state));
//						successors.add(swap("21","22",state));	
						
						successors.add(swap(2,1,2,0,state));
						successors.add(swap(2,1,1,1,state));
						successors.add(swap(2,1,2,2,state));	
						
						
						break;
        	
        	
        }
//        System.out.println("This is the thing's address"+successors.get(1));
//        printBoard(successors.get(0));
//        System.out.println("########");
//        printBoard(successors.get(1));
        return successors;

    
    }

	private static void printBoard(int[][] board) {
		// TODO Auto-generated method stub
			System.out.println("Printing board:");
			for(int i=0; i<board.length;i++) {
				for(int j=0; j<board.length;j++)
					System.out.print(" "+board[i][j]);
				System.out.println();
			
			}
	}
	
	
	
	

}
