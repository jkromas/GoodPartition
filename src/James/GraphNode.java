package James;

import java.util.Enumeration;
import java.util.Hashtable;


public class GraphNode {

	// Properties of a Node
	int NodeID;
	int ServerID;
	int SCB; // Server Change Benefit
	int Penalty; 
	int Bonus;
	int PSSN; // Pure Same Side Neighbor Count
	int PDSN; // Pure Different Side Neighbor Count
	int SSNC; // Same Side Neighbor Count
	int DSNC; // Different Side Neighbor Count
	boolean Marker; // to mark the node which is just been selected as a node with highest SCB
	boolean ReplicaCounted; // True if it's replica is being considered
	boolean isNodeVisited = false;
	
	Hashtable <Integer, GraphNode> myNeighbors = new Hashtable<Integer, GraphNode>();
	
	public GraphNode(){
		
		this.PSSN = 0;
		this.PDSN = 0;
		this.SSNC = 0;
		this.DSNC = 0;
		this.Penalty = 0;
		this.Bonus = 0;
		this.SCB = 0;
		this.Marker = false;
		ReplicaCounted = false;
	}
	
	public GraphNode(int _ID, int _ServerID){
		this.setNodeID(_ID);
		this.setServerID(_ServerID);
	}

public void setNeigbors(GraphNode Neighbor){
	
	myNeighbors.put(Neighbor.getNodeID(), Neighbor);
	
	if(Neighbor.getServerID() != this.getServerID()){
		// if neighbor is from different server
		this.setDSNC();
	}
	else{
		this.setSSNC();
	}
}	

public void printMyDetails(){
	System.out.println("NodeID: " + this.getNodeID());
	System.out.println("ServerID: " + this.getServerID());
	System.out.println("SSNC: " + this.getSSNC());
	System.out.println("DSNC: " + this.getDSNC());
	System.out.println("PSSN: " + this.getPSSN());
	System.out.println("PDSN: " + this.getPDSN());
	System.out.println("Bonus: " + this.getBonus());
	System.out.println("Penalty: " + this.getPenalty());
	System.out.println("SCB: " + this.getSCB());
	System.out.println();
}
public void printNodeDetails(GraphNode myNode){
	System.out.println("NodeID: " + myNode.getNodeID());
	System.out.println("ServerID: " + myNode.getServerID());
	System.out.println("SCB: " + myNode.getSCB());
	System.out.println("SSNC: " + myNode.getSSNC());
	System.out.println("DSNC: " + myNode.getDSNC());
	System.out.println("PSSN: " + myNode.getPSSN());
	System.out.println("PDSN: " + myNode.getPDSN());
	System.out.println("Bonus: " + myNode.getBonus());
	System.out.println("Penalty: " + myNode.getPenalty());
	System.out.println("\n");
}


public void printNeighbors(){
	System.out.println("I am Node " + this.getNodeID() + " My Neighbors are: " + "\n");
	
	Enumeration<Integer> enumKey = myNeighbors.keys();
	while(enumKey.hasMoreElements()) {
	    int key = enumKey.nextElement();
	    GraphNode myNodes = myNeighbors.get(key);
	    myNodes.printNodeDetails(myNodes);
	}
	   
} 



public void setNodeID(int _NodeID){
	this.NodeID = _NodeID;
}

public void setServerID(int _ServerID){
	this.ServerID = _ServerID;
}

public void setPenalty(){
	// Penalty = -1 if DSNC = 0 else Penalty = 0;
	if(this.DSNC ==0){
		
		this.Penalty = -1;
	}
	else{
		this.Penalty = 0;
	}

}

public void setBonus(){
	// Bonus = 1 if SSNC = 0 else Bonus = 0;
	if(this.SSNC == 0){
		
		this.Bonus = 1;
	}
	else{
		this.Bonus = 0;
	}

}
public void setSSNC(){
	this.SSNC++;
}

public void setSSNC(int NewSSNC){
	this.SSNC = NewSSNC;
}

public void setDSNC(){
	this.DSNC++;
}

public void setDSNC(int NewDSNC){
	this.DSNC = NewDSNC;
}

public void setPSSN(){
	this.PSSN++;
}

public void setPSSN(int NewPSSN){
	this.PSSN = NewPSSN;
}

public void setPDSN(){
	this.PDSN++;
}

public void setPDSN(int NewPDSN){
	this.PDSN = NewPDSN;
}

public void setSCB(int _SCB){
	this.SCB = _SCB;
}

public void setMarker(boolean mark){ // if true then this node has been recently chosen as max node
	this.Marker = mark;
}

public void setReplicaCounted(boolean Counted){
	this.ReplicaCounted = Counted;
}

public int getNodeID(){
	return this.NodeID;
}

public int getServerID(){
	return this.ServerID;
}

public int getSSNC(){
	return this.SSNC;
}

public int getDSNC(){
	return this.DSNC;
}

public int getPSSN(){
	return this.PSSN;
}

public int getPDSN(){
	return this.PDSN;
}

public int getBonus(){
	return this.Bonus;
}

public int getPenalty(){
	return this.Penalty;
}

public int getSCB(){
	return this.SCB;
}

public Hashtable<Integer, GraphNode> getMyNeighbors(){
	return this.myNeighbors;
}

public boolean getMarker(){
	return this.Marker;
}

public boolean getReplicaCounted(){
	return this.ReplicaCounted;
}

public void computeSCB(){
	// SCB =  PDSN – PSSN + Penalty + Bonus;
	this.setBonus();
	this.setPenalty();
	
	int SCB = this.PDSN - this.PSSN + this.getPenalty() + this.getBonus();
	this.setSCB(SCB);
}

public void resetValues(){
	this.setPDSN(0);
	this.setPSSN(0);
}

public void computeMetrics(){ // Computing Same Side and Pure Same Side Neighbor Count

	this.resetValues(); // reset PDSN and PSSN 
	
	Enumeration<Integer> enumKey = myNeighbors.keys();
	while(enumKey.hasMoreElements()) {
	    int key = enumKey.nextElement();
	    GraphNode myNode = myNeighbors.get(key);
	    
	    // Compute PSSN, and DSSN 
	    if((myNode.getServerID() == this.getServerID()) && myNode.getDSNC() == 0){ 
	    	// If the same server side neighbor does not have any 
	    	// connection with Nodes from Different Server.
	    	this.setPSSN();
	    }
	    else if((myNode.getServerID() != this.getServerID()) && myNode.getDSNC() == 1){
	    	// Which means if the neighbor is from different server
	    	// then the neighbor is counted as pure different neighbor 
	    	// if that neighbor has only one different neighbor which is current node itself
	    	this.setPDSN();
	    }
	    
	}
}

public boolean isNeighbor(GraphNode myNode){
	
	if(this.myNeighbors.containsKey(myNode)){
		return true;
	}else return false;
	
}

public boolean isTwoHopNeighbor(GraphNode myNode){
	
	for(int i = 0; i < this.myNeighbors.size(); i++){
		if(this.myNeighbors.containsKey(myNode)){
			return true;
		}
	}
	
	return false;
}

public boolean isNodeVisited(){
	return isNodeVisited;
}

public void setNodeVisited(boolean visited){
	isNodeVisited = visited;
}

}

