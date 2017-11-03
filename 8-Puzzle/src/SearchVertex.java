
public class SearchVertex {
	
	int[][] current_state;
	SearchVertex parent;
	
	int total_cost, cost, heuristic_cost;
	
	
	public int[][] getCurrent_state() {
		return current_state;
	}
	public void setCurrent_state(int[][] current_state) {
		this.current_state = current_state;
	}
	public SearchVertex getParent() {
		return parent;
	}
	public void setParent(SearchVertex parent) {
		this.parent = parent;
	}
	
	public int getTotal_cost() {
		return total_cost;
	}
	public void setTotal_cost(int total_cost) {
		this.total_cost = total_cost;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getHeuristic_cost() {
		return heuristic_cost;
	}
	public void setHeuristic_cost(int heuristic_cost) {
		this.heuristic_cost = heuristic_cost;
	}
	public SearchVertex(int[][] vertex) {
		// TODO Auto-generated constructor stub
		
		current_state = vertex;
		parent = null;
		
		total_cost = 0;
		cost = 0;
		heuristic_cost = 0;
		
	}
	
	public SearchVertex(SearchVertex v, int[][] arr, int cst, int heurCst) {
		// TODO Auto-generated constructor stub
		
		parent = v;
		current_state = arr;
		cost = cst;
		heuristic_cost = heurCst;
		
	}

}
