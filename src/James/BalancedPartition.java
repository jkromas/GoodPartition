package James;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class BalancedPartition{
	// Hash table to store Node and it's corresponding nodes
	NodeNew NodeWithMaxSCB = new NodeNew();
	ArrayList<NodeNew> NodeList = new ArrayList<NodeNew>();	
	int NodeSize;
	int MaxDiff = 1;
	int turnForServer = -1;
	int NumNodeServer0 = 0;
	int NumNodeServer1 = 0;
	private int totalReplicaCount;
	


	public BalancedPartition(){
		this.NodeSize = 0;
	}
	
	public BalancedPartition(ArrayList<NodeNew> myArrayList){
		this();
		this.NodeList = myArrayList;
	}
	
	public void setNumNodeServer0(int NumNodeServer0){

		this.NumNodeServer0 = NumNodeServer0;
		
	}
	
	public void setNumNodeServer1(int NumNodeServer1){

		this.NumNodeServer1 = NumNodeServer1;
		
	}
	
	public int getNumNodeServer0(){
		
		return this.NumNodeServer0;
	}

	public int getNumNodeServer1(){
		
		return this.NumNodeServer1;
	}
	
	public void setMaxDiff(int TotalNodes){
		if(TotalNodes%2 == 0){
			this.MaxDiff = 2;
		} // Even
		else this.MaxDiff = 1;
	}
	
	public void FindPartition(){
		//Node.printAllNodeDetails(NodeList);
		System.out.println( "Total Replicas Before Applying Algo: " + returnReplicas(NodeList));
		NodeSize = NodeList.size();
				
		//int NumNodeS1 = getNodeCounts(NodeList);
		//int NumNodeS0 = NodeSize - NumNodeS1;
		//System.out.println("Total Node Size 0: " + NumNodeS0);
		//int MaxTolerance = 2; // Max Difference between population in two servers
		
		setNumNodeServer1(getNodeCounts(NodeList));
		setNumNodeServer0(NodeSize - getNumNodeServer1());
		System.out.println("Total Node Size in Server 0: " + getNumNodeServer0() + " Total Node Size in Server 1: " + getNumNodeServer1());
		
		int NumNodesDiff = 0;
		
		for(int j = 0; j < NodeSize/2; j++){
			this.FindNodeWithMaxSCB(NodeList, 0);
			this.RunAlgorithm();
			this.printAllNodeDetails(NodeList);
			setNumNodeServer1(getNumNodeServer1() + 1);
			setNumNodeServer0(getNumNodeServer0() - 1);
		
			NumNodesDiff = Math.abs(getNumNodeServer1() - getNumNodeServer0());
			System.out.println("Node Difference: " + NumNodesDiff);
			System.out.println("Num Replicas: " + this.returnReplicas(NodeList));
		}
		
		System.out.println("Total Node Size: " + NodeSize);
		System.out.println("Total Node Size 0: " + getNumNodeServer0());
		System.out.println("Total Node Size 1: " + getNumNodeServer1());
		
		turnForServer = 0;
		
		this.setMaxDiff(NodeSize);
		// to find out how many times the algorithm goes through the while loop
		int LoopCnt = 0;
		
		while(this.DetermineFinalState(NodeList) != true){
			LoopCnt++;
			NumNodesDiff = Math.abs(getNumNodeServer1() - getNumNodeServer0());
			System.out.println("Node Difference Inside Loop: " + NumNodesDiff);
			// To switch turn for finding max SCB nodes	
			if(NumNodesDiff <= this.MaxDiff){
				if(turnForServer == 1){
					this.FindNodeWithMaxSCB(NodeList, 1);
					this.RunAlgorithm();
					this.printAllNodeDetails(NodeList);
					setNumNodeServer1(getNumNodeServer1() - 1); 
					setNumNodeServer0(getNumNodeServer0() + 1); 
					NumNodesDiff = Math.abs(getNumNodeServer1() - getNumNodeServer0());
					turnForServer = 0;
					
				}else{
					this.FindNodeWithMaxSCB(NodeList, 0);
					this.RunAlgorithm();
					this.printAllNodeDetails(NodeList);
					setNumNodeServer0(getNumNodeServer0() - 1); 
					setNumNodeServer1(getNumNodeServer1() + 1); 
					NumNodesDiff = Math.abs(getNumNodeServer1() - getNumNodeServer0());
					turnForServer = 1;
				}
				System.out.println("Server 0 Size:" + getNumNodeServer0() + " Num Replicas: " + this.returnReplicas(NodeList));
				//JOptionPane.showMessageDialog(null, "TurnServer: " + TurnForServer);
			}		
			// To make sure there is no infinite loop inside this while loop
			if(LoopCnt >= 3){
				
				JOptionPane.showMessageDialog(null, "Alert! Loop Count: " + LoopCnt);
				
			}
		}
		
		System.out.println("Total Node Size: " + NodeSize + " Node Size Server 0: " + getNumNodeServer0() + " Node Size Server 1: " + getNumNodeServer1());
		System.out.println( "Total Replicas After Applying Algorithm: " + this.returnReplicas(NodeList) + " Total Loop Counts: " + LoopCnt);
		
//		if(this.returnReplicas(NodeList) <= 4){
//			JOptionPane.showMessageDialog(null, this.returnReplicas(NodeList));
//		}

		this.totalReplicaCount = this.returnReplicas(NodeList);
	}
	
	public int replicaCount(){
		return this.totalReplicaCount;
	}
	
	public void FindNodeWithMaxSCB(ArrayList<NodeNew> myNodeList, int ServerID){
		//NodeWithMaxSCB = null;
		NodeNew tempNode = null;
		
		for(int i=0; i < myNodeList.size(); i++){
			NodeNew tempNodes = myNodeList.get(i);
			tempNodes.computeMetrics();
		}

		int MaxSCB = -999;
		for(int i = 0; i < myNodeList.size(); i++){
			
			tempNode = myNodeList.get(i);
			if(tempNode.getMarker() == false && tempNode.getServerID() == ServerID){
				tempNode.computeSCB();
				int tempSCB = tempNode.getSCB();
				if(tempSCB > MaxSCB){
					MaxSCB = tempSCB; // Find Maximum
					NodeWithMaxSCB = tempNode;
				}
				
				//tempNode.printMyDetails();
			}else{
				tempNode.setMarker(false); // remove marker
			}
			
		}
		
	
		//JOptionPane.showMessageDialog(null, NodeWithMaxSCB.getServerID());
		NodeWithMaxSCB.setMarker(true); // set marker in order to prevent it to be included in next iteration
		System.out.println("I am Max SCB Node With Node ID " + NodeWithMaxSCB.getNodeID() + " SCB: " + MaxSCB);
	}

	public void UpdateMyNeighbors(NodeNew MyNode, boolean TargetNode){
		Hashtable<Integer, NodeNew> MyNeighbors = new Hashtable<Integer, NodeNew>();
		
		MyNeighbors = MyNode.getMyNeighbors();
		
		Enumeration<Integer> enumKey = MyNeighbors.keys();
		while(enumKey.hasMoreElements()){
		    int key = enumKey.nextElement();
		    NodeNew NeighborNode = MyNeighbors.get(key);
		    
		    if(TargetNode){// if this is the 1 hop neighbor of the target node
		    	if(MyNode.getServerID() == NeighborNode.getServerID()){
		    		// if they are from same server after changing
		    		NeighborNode.setSSNC(NeighborNode.getSSNC() + 1);
		    		NeighborNode.setDSNC(NeighborNode.getDSNC() - 1);
		    	}
		    	
		    	else{ // if the target node is different server 1 hop neighbor
		    		NeighborNode.setSSNC(NeighborNode.getSSNC() - 1);
		    		NeighborNode.setDSNC(NeighborNode.getDSNC() + 1);
		    	}
		    } else{
		    	
		    	Hashtable<Integer, NodeNew> myTwoHopNeighbors = NeighborNode.getMyNeighbors(); // My Two hop neighbors 
		    	Enumeration<Integer> enumKeys = myTwoHopNeighbors.keys();
		    	while(enumKeys.hasMoreElements()) {
		    	    int keys = enumKeys.nextElement();
		    	    NodeNew TwoHopNeighbor = myTwoHopNeighbors.get(keys);
		    	    // Update remaining metrics for 1 hop or 2 hop neighbors
		    	    TwoHopNeighbor.computeMetrics();
		    	    TwoHopNeighbor.computeSCB();
		    	    	
		    	}
		    }
		}
		
		// After all neighbors SSNC and DSNC are updated now we can change the remaining metrics
		Enumeration<Integer> enumKeys = MyNeighbors.keys();
		while(enumKeys.hasMoreElements()) {
		    int key = enumKeys.nextElement();
		    NodeNew NeighborNode = MyNeighbors.get(key);
		     	// Update remaining metrics for 1 hop or 2 hop neighbors
				NeighborNode.computeMetrics();
				NeighborNode.computeSCB();
		    	
		}
	}

	public void RunAlgorithm(){
		// Change the server of the Node with Maximum SCB
		if(NodeWithMaxSCB.getServerID() == 0){
			NodeWithMaxSCB.setServerID(1);
			//JOptionPane.showMessageDialog(null, "Run " + NodeWithMaxSCB.getNodeID() + " SCB: " + NodeWithMaxSCB.getSCB());
		}
		else{
			NodeWithMaxSCB.setServerID(0);
			//JOptionPane.showMessageDialog(null, "Run " + NodeWithMaxSCB.getNodeID());
		}
		// Exchange the value of SSNC and DSNC
		int tempSSNC = NodeWithMaxSCB.getSSNC();
		NodeWithMaxSCB.setSSNC(NodeWithMaxSCB.getDSNC());
		NodeWithMaxSCB.setDSNC(tempSSNC);
		// Exchange the value of PSSN and PDSN
		int tempPSSN = NodeWithMaxSCB.getPSSN();
		NodeWithMaxSCB.setPSSN(NodeWithMaxSCB.getPDSN());
		NodeWithMaxSCB.setPDSN(tempPSSN);
		
		// Set New SCB after Server Change
		int TargetNodeSCB = -1 * NodeWithMaxSCB.getSCB();
		//JOptionPane.showMessageDialog(null, "Test 1: " + NodeWithMaxSCB.getSCB());
		NodeWithMaxSCB.setSCB(TargetNodeSCB);
	
		//Update SSNC, DSNC, PSSN, PDSN, and SCB of Every Two Hop Neighbors of NodeWithMaxSCB
		this.UpdateMyNeighbors(NodeWithMaxSCB, true); // main node true
		this.UpdateMyNeighbors(NodeWithMaxSCB, false); // main node false --> Neighbor Nodes
		
		
	}
	
	
	// If any node has positive node gain but has all neighbor nodes with -ve or zero SCB then we will count it as contributing nodes of the final
	// state of the algorithm
	// If the node is not eligible then returns true else returns false
	public boolean CheckSCBNeighbors(NodeNew myNode){
		Hashtable<Integer, NodeNew> MyNeighbors = new Hashtable<Integer, NodeNew>();
		
		MyNeighbors = myNode.getMyNeighbors();
		
		Enumeration<Integer> enumKey = MyNeighbors.keys();
		if(MyNeighbors.size() == 1){ // If the particular node has only one neighbor then in that case we can neighbor with accept zero SCB
			
		    int key = enumKey.nextElement();
		    NodeNew NeighborNode = MyNeighbors.get(key);
		    if(NeighborNode.getSCB() > 0){
		    	return true;
		    }
		    else {
		    	return false;
		    }			
		}else{
			while(enumKey.hasMoreElements()){
			    int key = enumKey.nextElement();
			    NodeNew NeighborNode = MyNeighbors.get(key);
			    if(NeighborNode.getSCB() >= 0){
			    	return true;
			    }
			}
		}
		
		return false;
	}
	
	public boolean checkForZeroAndNegativeSCB(ArrayList<NodeNew> myNodeList){
		int NegativeSCBCounts = 0;
		int AllSideNegSCBCounts = 0;
		// returns true if all the nodes from server defined by turnForServer have negative or zero SCB 
		for(int i = 0; i < myNodeList.size(); i++){
			
			if(myNodeList.get(i).getSCB() <= 0){ // if all of them are -ve or zeros
				AllSideNegSCBCounts++;
				if(AllSideNegSCBCounts == myNodeList.size()){
					//JOptionPane.showMessageDialog(null, "This is all SCBs either zero or negative case !");
					return true;
				}
			}
			
			if(myNodeList.get(i).getServerID() == this.turnForServer && myNodeList.get(i).getSCB() > 0){
				
				//JOptionPane.showMessageDialog(null, "This is Server" + this.turnForServer + " and this is not the final state!");
				return false;	
			}else if(myNodeList.get(i).getServerID() == this.turnForServer && myNodeList.get(i).getSCB() < 0){
				NegativeSCBCounts++;
				//JOptionPane.showMessageDialog(null, "This is Server" + this.turnForServer + " and this is all zero and negative case with atleast " + NegativeSCBCounts + " negative SCB counts!");
				
			}
		}	
		
		if(NegativeSCBCounts == 0){ // There should be at least one negative SCB.
			//JOptionPane.showMessageDialog(null, "This is Server" + this.turnForServer + " and this is all zero case but not the final state!");
			return false;
		}
		else return true;
		
	}
	
	/*While inspecting one particular example it is concluded that
	 * the final state of the algorithm will end with zero or -ve SCB of the server
	 * whose one of the nodes is going to be changed in next step */
	public boolean DetermineFinalState(ArrayList<NodeNew> myNodeList){ 
	//public boolean DetermineFinalState(ArrayList<Node> myNodeList, int ServerTurn){ 
		// If all the nodes has zero or negative SCB then we declare the end state of the algorithm

		//JOptionPane.showMessageDialog(null, "Testing Loops");
		
		if(this.turnForServer == 0 && getNumNodeServer0() >= getNumNodeServer1()){
			
			return(checkForZeroAndNegativeSCB(myNodeList));	
			
		}else if(this.turnForServer == 1 && getNumNodeServer1() >= getNumNodeServer0()){
			
			return(checkForZeroAndNegativeSCB(myNodeList));
		}
		
		return false;
	}



	/*public boolean DetermineFinalState(ArrayList<Node> myNodeList){ 
	//public boolean DetermineFinalState(ArrayList<Node> myNodeList, int ServerTurn){ 
		// If all the nodes has zero or negative SCB then we declare the end state of the algorithm
		int Count = 0;
		for(int i = 0; i < myNodeList.size(); i++){
			if(myNodeList.get(i).getServerID() == turnForServer){
				if(myNodeList.get(i).getSCB() >= 0){
					if(myNodeList.get(i).getSCB() == 0){
						Count++;
					}
					else{
						return false; // if one of the nodes has positive gain then that is not the final state
					}
				}			
			}
			if(Count > 1){ // do not tolerate more than one zero from this server side --> This side of server is the one with highest nodes among two servers 
				return false;
			}
		}
				
		return true;	
	}
*/
	public int getNodeCounts(ArrayList<NodeNew> myNodeList){
		int NodeCnt = 0;
		for(int i = 0; i < myNodeList.size(); i++){
			if(myNodeList.get(i).getServerID() == 1){
				NodeCnt++;
			}
		}
		
		System.out.println("Node Count on Server S1: " + NodeCnt);
		return NodeCnt;
	}

	public void resetReplicas(ArrayList<NodeNew> NodeList){
		// mark false it replicaCounted is true
		for(int i = 0; i < NodeList.size(); i++){
			if(NodeList.get(i).getReplicaCounted()){
				NodeList.get(i).setReplicaCounted(false);
			}
		}
	}

	public int returnReplicas(ArrayList<NodeNew> NodeList){
		this.resetReplicas(NodeList);
		int TotalReplicas = 0;
		for(int i = 0; i < NodeList.size(); i++){
			NodeNew currentNode = NodeList.get(i);
			Hashtable<Integer, NodeNew> NeighborsList = new Hashtable<Integer, NodeNew>();
			NeighborsList = currentNode.getMyNeighbors();
			
			Enumeration<Integer> enumKeys = NeighborsList.keys();
			while(enumKeys.hasMoreElements()) {
			    int key = enumKeys.nextElement();
			    NodeNew NeighborNode = NeighborsList.get(key);
			    if(currentNode.getServerID() != NeighborNode.getServerID() && NeighborNode.ReplicaCounted == false){
			    	// if they are from different servers then it will contribute to two replicas
			    	TotalReplicas++;
			    	// mark them as counted
			    	NeighborNode.setReplicaCounted(true);
			    } 			    	
			}
		}
		return TotalReplicas;
	}
	
	public void printAllNodeDetails(ArrayList<NodeNew> NodeList){
		int NodeListSize = NodeList.size();
		System.out.println("Printing Node Details: ");
		for(int i = 0; i < NodeListSize; i++ ){
			System.out.println("NodeID: " + NodeList.get(i).getNodeID());
			System.out.println("ServerID: " + NodeList.get(i).getServerID());
			System.out.println("SCB: " + NodeList.get(i).getSCB());
			System.out.println("SSNC: " + NodeList.get(i).getSSNC());
			System.out.println("DSNC: " + NodeList.get(i).getDSNC());
			System.out.println("PSSN: " + NodeList.get(i).getPSSN());
			System.out.println("PDSN: " + NodeList.get(i).getPDSN());
			System.out.println("Bonus: " + NodeList.get(i).getBonus());
			System.out.println("Penalty: " + NodeList.get(i).getPenalty());
			System.out.println("\n");
		}
			
		
		
	}

}
