package James;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class myInput {
	ArrayList<GraphNode> myNodeList = new ArrayList<GraphNode>();	
	int[][] AdjacencyMatrix = new int[100][100];
	int NumNodes = AdjacencyMatrix.length;
	static int StepCounter = 0;
	public myInput(){
		// Creating a graph with all nodes connected to all other nodes in the graph
		// Max number of edges = n * (n-1)/2
		
		for(int i = 0; i < AdjacencyMatrix.length; i++){
			for(int j = 0; j < AdjacencyMatrix.length; j++){
				if(i != j)	
					AdjacencyMatrix[i][j] = 1;
				else
					AdjacencyMatrix[i][j] = 0;
			}
		}
	
	}
	
	public myInput(int NumNodes){
		
		// Creating a graph with all nodes connected to all other nodes in the graph
		// Max number of edges = n * (n-1)/2
		if(NumNodes < this.NumNodes){
			this.NumNodes = NumNodes;	
		}
		for(int i = 0; i < this.NumNodes; i++){
			for(int j = 0; j < this.NumNodes; j++){
				if(i != j)	
					AdjacencyMatrix[i][j] = 1;
				else
					AdjacencyMatrix[i][j] = 0;
			}
		}
		
		
	
	}
	
public int[][] getInput(){
	return this.AdjacencyMatrix;
}

public void printMatrixInfo(int[][] myMatrix){
	System.out.println("Edge Count: " + countEdges(myMatrix));
	for(int i = 0; i < NumNodes; i++){
		for(int j = 0; j < NumNodes; j++){
			System.out.print(" " + myMatrix[i][j]);
		}
		System.out.println();
	}
}

// Counts the numbers of neighbors for that specific row or node
public int getNeighborsCount(int[][] myGraph, int NodeId){
	int NumNeighbors = 0;
	for(int j = 0; j < NumNodes; j++){
			if(NodeId != j && myGraph[NodeId][j] == 1){
				NumNeighbors++;
			}
	}
	
	return NumNeighbors;
}

public int countEdges(int[][] myGraph){
	int EdgeCount = 0;
	for(int i = 0; i < NumNodes; i++){
		for(int j = 0; j < NumNodes; j++){
			if(i != j && myGraph[i][j] == 1){
				EdgeCount++;
			}
		}
	}
	
	return EdgeCount/2;
}

public void timer(int timers){
	try {
		TimeUnit.SECONDS.sleep(timers);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void generateGraph(int[][] myGraph, int Starter){
	int[][] myGraphs = myGraph;
		
	for(int i = Starter; i < NumNodes; i++){
	
		for(int j = 0; j < NumNodes; j++){
			
			if(i != j && getNeighborsCount(myGraphs, i) > 1 && getNeighborsCount(myGraphs, j) > 1){
				// remove edge between Node i and Node j
				myGraph[i][j] = myGraphs[j][i] = 0;
				
				
				System.out.println("-----------------------------------------------------------");
				System.out.println("Next Graph: " + ++StepCounter);
				System.out.println("-----------------------------------------------------------");
				printMatrixInfo(myGraphs);
				System.out.println("-----------------------------------------------------------");
				
				assignServer(myGraphs);
				BalancedPartition myPartition = new BalancedPartition(this.myNodeList);
				//timer(1);
				myPartition.FindPartition();
				
				
			}
		}
	}
	
	
	//return myGraph;
	
}

public void assignServer(int[][] myGraph){
	this.myNodeList.clear();
	for(int i = 0; i < this.NumNodes; i++){
		GraphNode temp = new GraphNode(i+1, 0);
		this.myNodeList.add(temp);
	}
	
	for(int m = 0; m < this.NumNodes; m++){
		for(int n = m; n < this.NumNodes; n++){
			if(m != n && myGraph[m][n] == 1){ // if they are connected
				GraphNode myNode = this.myNodeList.get(m);
				GraphNode myNeighbor = this.myNodeList.get(n);
				myNode.setNeigbors(myNeighbor);
				myNeighbor.setNeigbors(myNode);
			}
		}
	}
}

public static void main(String args[]){
	int NumNodes = 4;
	
	for(int i = 0; i < NumNodes; i++){
		myInput meroInput= new myInput(NumNodes);
		int[][] myGraphs = meroInput.getInput();
		meroInput.generateGraph(myGraphs, i);
	
	}
	System.out.println("Number of Graphs: " + StepCounter);
	
}

}
