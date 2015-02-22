/**
 * 
 */
package James;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

/**
 * @author Romas James
 *
 */
public class PowerSets{
	
	/**
	 * Defining an object of class RandomInput
	 */
	int myAdjMatrix[][];
	int NumNodes;
	int FileCounterForeverLoop;
	int FileCounterLoopCase;
	int FileCounterAllZeroCase;
	int FileCounterAllZeroAndNegativeCase;
	int FileCounterHigherSideZeroAndNegativeCase;
	int FileCounterAllZeroExceptOneCase;
	
	int MaxFileCount = 100;
	ArrayList<GraphNode> myNodeList = new ArrayList<GraphNode>();
	
	public PowerSets() {
		// Initializing or clearing Adjacent Matrix
		NumNodes = 4;
		FileCounterForeverLoop = 0;
		FileCounterLoopCase = 0;
		FileCounterAllZeroCase = 0;
		FileCounterAllZeroAndNegativeCase = 0;
		FileCounterHigherSideZeroAndNegativeCase = 0;
		
		myAdjMatrix = new int[NumNodes][NumNodes];
		initializedAdjMatrix(); 
	}
	
	public PowerSets(int NumNodes) {
		this();
		this.NumNodes = NumNodes;
		myAdjMatrix = new int[NumNodes][NumNodes];
		initializedAdjMatrix();
		
	}
	
	public void initializedAdjMatrix(){
		for(int i = 0; i < NumNodes; i++){
		   for(int j = 0; j < NumNodes; j++){
			   if(i == j){ // diagonal matrix is zero because a node cannot have an edge connected to itself
				  myAdjMatrix[i][j] = 0;
			   }else{
			     myAdjMatrix[i][j] = 1;
			   }
		   }
		}
	}
	/**
	 * @param args
	 */
	
	public LinkedHashSet<LinkedHashSet<String>> generatePowerSets(String[] set) {
		     
	   //create the empty power set
	   LinkedHashSet<LinkedHashSet<String>> power = new LinkedHashSet<LinkedHashSet<String>>();
	 
	   //get the number of elements in the set
	   int elements = set.length;
	 
	   //the number of members of a power set is 2^n
	   // To avoid exponential effect of 2^elements
	   int powerElements;
	   if(elements < 16){
		   powerElements = (int) Math.pow(2,elements);
	  }else{
		  powerElements = 300000;
	  }
	   //run a binary counter for the number of power elements
	   for (int i = 0; i < powerElements; i++) { 
		     
		   //convert the binary number to a string containing n digits
		   String binary = intToBinary(i, elements);
		 
		   //create a new set
		   LinkedHashSet<String> innerSet = new LinkedHashSet<String>();
		 
		   //convert each digit in the current binary number to the corresponding element
		   //in the given set
		   for (int j = 0; j < binary.length(); j++) {
		       if (binary.charAt(j) == '1')
		           innerSet.add(set[j]);
		   }
		 
		   //add the new set to the power set
		   power.add(innerSet);
	         
	   }
	     
	   return power;
	}
	 
	   /**
	 * Converts the given integer to a String representing a binary number
	 * with the specified number of digits
	 * For example when using 4 digits the binary 1 is 0001
	 * @param binary int
	 * @param digits int
	 * @return String
	 */
	   
   public String intToBinary(int binary, int digits) {
     
       String temp = Integer.toBinaryString(binary);
       int foundDigits = temp.length();
       String returner = temp;
       for (int i = foundDigits; i < digits; i++) {
           returner = "0" + returner;
       }
     
       return returner;
   } 
   
   public void clearMatrix(){
		for(int i = 0; i < NumNodes; i++){
			   for(int j = 0; j < NumNodes; j++){
				     myAdjMatrix[i][j] = 0;
			   }
		}
	}
		
	public void printMatrix(){
		for(int i = 0; i < NumNodes; i++){
			   for(int j = 0; j < NumNodes; j++){
				    System.out.print(" " + myAdjMatrix[i][j]);
		   }
		   System.out.println();
		}
		System.out.println("\n\n");
	}
	
	//returns true if all vertices have at least one associated edge
	// if any row has all zeros return false
	public boolean checkZeroRowsInMatrix(){
		boolean RowsWithAllZero = false;
		int ZeroCount = 0;
		for(int i = 0; i < NumNodes; i++){
		   for(int j = 0; j < NumNodes; j++){
			    if(myAdjMatrix[i][j] == 0){
			    	ZeroCount++;
			    	
			    }
			    
		   }
		   if(ZeroCount == NumNodes){
			    return true;
		   }
		   else
			   ZeroCount = 0; // reset
		}
		
		return RowsWithAllZero;
		//JOptionPane.showMessageDialog(null, "Same as Initialized Matrix? " + status);
	}
	
	public void getFilteredPowerSets(){
	
		int MaxPossibleEdges = (NumNodes * (NumNodes - 1)) / 2;
		
		String SetOfEdges[] = new String[MaxPossibleEdges];
	       
			
		int EdgeCount = 0;
		// Generating a set of edges --> {E1, E2, E3, ...}
		for(int m = 0; m < NumNodes; m++){
			   
		   for(int n = 0; n < NumNodes; n++){
			   	   if(m < n && m != n){ // Avoiding duplicate edges defined in adjacency matrix
					   	SetOfEdges[EdgeCount++] = m + "," + n; // E1 -> V1,V2  
				   	 	System.out.println(SetOfEdges[EdgeCount-1]);
				   	 	
				   }
			   }
		   }
		
			   
  
	   //form the power set
	   LinkedHashSet<LinkedHashSet<String>> PowerSetsOfEdges = generatePowerSets(SetOfEdges);
	 
	   //display the power set
	   Iterator<LinkedHashSet<String>> myIter = PowerSetsOfEdges.iterator();
	   int SetCounts = 0;
	   int realSetCounts = 0;
	   while(myIter.hasNext()){
		 
		   LinkedHashSet<String> Edge = myIter.next();
		   Object[] StringArray = Edge.toArray();
		   if(StringArray.length >= NumNodes - 1){ // Minimum Spanning Tree Length = TotalNodes - 1, filtering sub sets
			   clearMatrix();
			   // JOptionPane.showMessageDialog(null, "Set: " + Edge.toString() + " Array: " + StringArray.length);
			   for(int i = 0; i < StringArray.length; i++){
				  
				   	//System.out.print(" " + StringArray[i]);
			   	    String temp[] = ((String) StringArray[i]).split(",");
			   	    //JOptionPane.showMessageDialog(null, "Temp[0]: " + temp[0] + " Temp[1]: " + temp[1]);
			   	    int m = Integer.parseInt(temp[0]);
			   	    int n = Integer.parseInt(temp[1]);
			   		myAdjMatrix[m][n] = 1;
			   		myAdjMatrix[n][m] = 1;
			   	 
			   	    
			   }
			   //printMatrix();
			   
			   if(!checkZeroRowsInMatrix()){
				   //printMatrix();
				   
				   // Converts myAdjMatrix into List of Graph Nodes
				   assignServer();
				   BalancedPartition myPartition = new BalancedPartition(myNodeList);
				   myPartition.FindPartition();
				   
				   /*
				   if(BalancedPartition.isForeverLoop){
					   writeIntoFile("ForeverLoop_NumNodes_" + this.NumNodes + "_" + (++FileCounter) + ".txt");  	
					   BalancedPartition.isForeverLoop = false;
				   }
				    */
				   if(BalancedPartition.isLoopCase && FileCounterLoopCase < MaxFileCount){
					   writeIntoFile("LoopCases", "LoopCase_NumNodes_" + this.NumNodes + "_" + (++FileCounterLoopCase) + ".txt");  	
					   BalancedPartition.isLoopCase = false;
				   }
				   
				   if(BalancedPartition.isForeverLoop && FileCounterForeverLoop < MaxFileCount){
					   writeIntoFile("ForeverLoopCases", "ForeverLoop_NumNodes_" + this.NumNodes + "_" + (++FileCounterForeverLoop) + ".txt");  	
					   BalancedPartition.isForeverLoop = false;
				   }
				   
				   
				   if(BalancedPartition.isAllZeroCase && FileCounterAllZeroCase < MaxFileCount){
					   
					   writeIntoFile("AllZeroCases", "AllZeroCase_NumNodes_" + this.NumNodes + "_" + (++FileCounterAllZeroCase) + ".txt");  	
					   BalancedPartition.isAllZeroCase = false;
					   
				   }else if(BalancedPartition.isAllZeroExceptOne && FileCounterAllZeroExceptOneCase < MaxFileCount){
					   
					   writeIntoFile("AllZeroExceptOneCase", "AllZeroExceptOneCase_NumNodes_" + this.NumNodes + "_" + (++FileCounterAllZeroExceptOneCase) + ".txt");  	
					   BalancedPartition.isAllZeroExceptOne = false;
					   
				   }else if(BalancedPartition.isAllSideZeroAndNegativeCase && FileCounterAllZeroAndNegativeCase < MaxFileCount){
					   
					   writeIntoFile("AllZeroAndNegativeCases","AllZeroAndNegativeCase_NumNodes_" + this.NumNodes + "_" + (++FileCounterAllZeroAndNegativeCase) + ".txt");  	
					   BalancedPartition.isAllSideZeroAndNegativeCase = false;
				   
				   }else if(BalancedPartition.isHigherSideZeroAndNegativeCase && FileCounterHigherSideZeroAndNegativeCase < MaxFileCount){
					
					   writeIntoFile("HigherSideZeroAndNegativeCases","HigherSideZeroAndNegativeCase_NumNodes_" + this.NumNodes + "_" + (++FileCounterHigherSideZeroAndNegativeCase) + ".txt");  	
					   BalancedPartition.isHigherSideZeroAndNegativeCase = false;
				   
				   }else{
					   // do nothing
				   }
				   
				   SetCounts++;
				   //sleep(1);
			   }
			  
		   }
		  
		   realSetCounts++;
		   //clearMatrix();
		   
	   }
	   
	     
	   System.out.println("\nTotal Possible Edge Combinations (2^EdgeCounts): " + realSetCounts + "\n\nPossible Sets: " + SetCounts);
	}
	
	
	
	public void sleep(int time){
		 try {
				TimeUnit.SECONDS.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void writeIntoFile(){
		PrintWriter writer = null;
		
		try {
			writer = new PrintWriter("Examples\\Input.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		for(int i = 0; i < this.NumNodes; i++){
			for(int j = 0; j < this.NumNodes; j++){
				System.out.print(" " + myAdjMatrix[i][j]);
				writer.print(" " + myAdjMatrix[i][j]);
			}
			writer.println();
			System.out.println("\n");
		}
		writer.close();
	}
	
	public void writeIntoFile(String DirectoryName, String FileName){
		PrintWriter writer = null;
		// to create a new directory
		File file = new File(DirectoryName);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}
		
		try {
			writer = new PrintWriter(DirectoryName + "\\" + FileName, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		for(int i = 0; i < this.NumNodes; i++){
			for(int j = 0; j < this.NumNodes; j++){
				System.out.print(" " + myAdjMatrix[i][j]);
				writer.print(" " + myAdjMatrix[i][j]);
			}
			writer.println();
			System.out.println("\n");
		}
		writer.close();
	}
	
	// Generating nodes and edges and assigning them to one server
	public void assignServer(){
		this.myNodeList.clear();
		for(int i = 0; i < this.NumNodes; i++){
			GraphNode temp = new GraphNode(i+1, 0);
			this.myNodeList.add(temp);
		}
		
		for(int m = 0; m < this.NumNodes; m++){
			for(int n = m; n < this.NumNodes; n++){
				if(m != n && this.myAdjMatrix[m][n] == 1){ // if they are connected
					GraphNode myNode = this.myNodeList.get(m);
					GraphNode myNeighbor = this.myNodeList.get(n);
					myNode.setNeigbors(myNeighbor);
					myNeighbor.setNeigbors(myNode);
				}
			}
		}
	}
	

	public static void main(String[] args) {
	
//		PowerSets myPowerSets_4 = new PowerSets(4);
//		myPowerSets_4.getFilteredPowerSets();
//		
//		PowerSets myPowerSets_5 = new PowerSets(5);
//		myPowerSets_5.getFilteredPowerSets();
		
		PowerSets myPowerSets_6 = new PowerSets(6);
		myPowerSets_6.getFilteredPowerSets();
//		
//		PowerSets myPowerSets_7 = new PowerSets(7);
//		myPowerSets_7.getFilteredPowerSets();
	}

}
