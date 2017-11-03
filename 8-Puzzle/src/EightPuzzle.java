import java.util.*;

/*	function general-search(problem, QUEUEING-FUNCTION)  
nodes = MAKE-QUEUE(MAKE-NODE(problem.INITIAL-STATE)) 
loop do
 if EMPTY(nodes) then return "failure" (we have proved there is no solution!)
   node = REMOVE-FRONT(nodes) 
 if problem.GOAL-TEST(node.STATE) succeeds then return node
    nodes = QUEUEING-FUNCTION(nodes, EXPAND(node, problem.OPERATORS))  
 end
*/


public class EightPuzzle {

static Scanner t = new Scanner(System.in);
	
	private static void makeBoard(int[][] board)
	{
		System.out.println("Initializing 8-Puzzle Board...");
		System.out.println("Enter 1 to use default puzzle or 2 to enter your own.");
		int choice  = t.nextInt();
		
		switch(choice)
		{
			case 1:
			{
				board[0][0] = 1; board[0][1] = 2; board[0][2] = 3; 
				board[1][0] = 4; board[1][1] = 0; board[1][2] = 6;
				board[2][0] = 7; board[2][1] = 5; board[2][2] = 8;
				
				break;		
				
			}
			case 2:
			{
				System.out.println("Please enter your board one row at a time with space or tab separated elements.");
				System.out.println("Enter first row -> ");
				for(int j=0; j<board.length;j++) 
						board[0][j] = t.nextInt();
				System.out.println("Enter second row -> ");
				for(int j=0; j<board.length;j++) 
						board[1][j] = t.nextInt();
				System.out.println("Enter third row -> ");
				for(int j=0; j<board.length;j++) 
						board[2][j] = t.nextInt();			
				
				break;
			}
			default:
				System.out.println("Oops, please enter the right choice.");
		}
		
		
	}
	

	private static void printBoard(int[][] board) {
	// TODO Auto-generated method stub
		
		for(int i=0; i<board.length;i++) {
			for(int j=0; j<board.length;j++)
				System.out.print(" "+board[i][j]);
			System.out.println();
		
		}
}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		int board[][] = new int[3][3];
		int[][] goal_state = {{1,2,3},{4,5,6},{7,8,0}};
		
		makeBoard(board);
		
		
		
		int[][] root = new int[board.length][board.length];
		root = board;
		
		System.out.println("This is root.");
		printBoard(root);
		
		System.out.println("Enter choice of Algorithm:");
    	System.out.println("1.	Uniform Cost Search");
    	System.out.println("2.	A* with the Misplaced Tile heuristic.");
    	System.out.println("3.	A* with the Manhattan distance heuristic.");
    	int choice = t.nextInt();
		
        if(choice == 1)
    	{
    		long startTime = System.currentTimeMillis();
            SearchTree searchTree = new SearchTree(new Vertex(root),goal_state);
           
           
            searchTree.uniformCostSearch();
            
            long finishTime = System.currentTimeMillis();
            long totalTime = finishTime - startTime;
            System.out.println("Time  :" + totalTime);
    	}
    	else if(choice == 2)
    	{
    		long startTime = System.currentTimeMillis();
            SearchTree searchTree = new SearchTree(new Vertex(root), goal_state);
           
            searchTree.aStarSearch(choice);
           
            
            long finishTime = System.currentTimeMillis();
            long totalTime = finishTime - startTime;
            System.out.println("Time  :" + totalTime);
    	}
    	else if(choice == 3)
    	{
    		long startTime = System.currentTimeMillis();
            SearchTree searchTree = new SearchTree(new Vertex(root), goal_state);
           
            searchTree.aStarSearch(choice);
            
            
            long finishTime = System.currentTimeMillis();
            long totalTime = finishTime - startTime;
            System.out.println("Time  :" + totalTime);
    	}
    	else
    	{
    		System.out.println("Wrong choice of Algorithm!");
    	}

        
        
        
		

	}

	

}
