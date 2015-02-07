package James;

import java.io.FileNotFoundException;
import java.util.Random;
//import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit;


public class Main {
	
	public static void main(String args[]){
		//int NewAlgorithmEfficientCnt = 0;
		//int OldAlgorithmEfficientCnt = 0;
		//int SameOutputCount = 0;
		//RandomInput myInput = new RandomInput(5, "Examples//CounterExample_PreviousCase.txt"); // Loop Count = 3; Replicas = 4
		//RandomInput myInput = new RandomInput(5, "Examples//CounterExample_Board.txt"); // Loop Count = 0; Replicas = 3
		//RandomInput myInput = new RandomInput(50, "Examples//CounterExample_New_Fixed.txt"); // Loop Count = 0; Replicas = 31 (34)
		//RandomInput myInput = new RandomInput(5, "Examples//CounterExample_All_PositiveSCBs.txt"); // Loop Count = 0; Replicas = 3
	    //RandomInput myInput = new RandomInput(6, "Examples//CounterExample_All_PositiveSCBs_2.txt"); // Loop Count = 1; Replicas = 3
		//RandomInput myInput = new RandomInput(7, "Examples//CounterExample_All_PositiveSCBs_3.txt"); // Loop Count = 0; Replicas = 4
	    //RandomInput myInput = new RandomInput(10, "Examples//Example_SigcommPaper.txt"); // Loop Count = 0; Replicas = 5
		//RandomInput myInput = new RandomInput(7, "Examples//InputInfiniteLoop_14_NodeCount_7.txt"); // Loop Count = 0; Replicas = 5
		//RandomInput myInput = new RandomInput(20, "Examples//Example_19_NodeCount_20.txt"); // Loop Count = 0; Replicas = 11 (12)
		//RandomInput myInput = new RandomInput(6, "Examples//PresentationExampleDegree6.txt"); // Loop Count = 1; Replicas = 3
		//RandomInput myInput = new RandomInput(7, "Examples//PresentationExampleDegree7.txt"); // Loop Count = 0; Replicas = 4
		//RandomInput myInput = new RandomInput(320, "Examples//Input_420.txt"); // Loop Count = 0; Replicas = 319
		//RandomInput myInput = new RandomInput(10, "Examples//Input (1).txt"); // Loop Count = 1; Replicas = 8
		//RandomInput myInput = new RandomInput(9, "Examples//Input (2).txt"); // Loop Count = 3; Replicas = 8
		//RandomInput myInput = new RandomInput(5, "Examples//Input (3).txt"); // Loop Count = 1; Replicas = 4 
		//RandomInputNew myInput = new RandomInputNew(9, "Examples//Input (4).txt"); // Loop Count = 4; Replicas = 8 
		//RandomInput myInput = new RandomInput(5, "Examples//Input (5).txt"); // Loop Count = Infinity; Replicas = 5 
		//RandomInput myInput = new RandomInput(5, "Examples//Input (6).txt"); // Loop Count = 1; Replicas = 4
		//RandomInput myInput = new RandomInput(5, "Examples//Input (7).txt"); // Loop Count = 1; Replicas = 4 
		//RandomInput myInput = new RandomInput(8, "Examples//Input (8).txt"); // Loop Count = 0; Replicas = 5
		//RandomInput myInput = new RandomInput(6, "Examples//Input (9).txt"); // Loop Count = 0; Replicas = 5 
		RandomInput myInput = new RandomInput(7, "Examples//Input (10)"); // Loop Count = 0; Replicas = 4 
		
		
  
	          
		try {
			myInput.readInputFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		BalancedPartition myPartition = new BalancedPartition(myInput.getNodeList());
		myPartition.FindPartition();

		
//		Random rand = new Random();
//		for(int i = 0; i < 100; i++){
//			int TotalNodes = rand.nextInt(10);
//			if(TotalNodes < 5){
//				TotalNodes = 5;
//			}
//			
//			int probability = rand.nextInt(50);
//			if(probability < 20){
//				probability = 20;
//			}
//		
//		
//			RandomInput myInput = new RandomInput(TotalNodes, probability);
//			myInput.generateNodeList();
//		
//			BalancedPartition myNewPartition = new BalancedPartition(myInput.getNodeList());
//			myNewPartition.FindPartition();
//			
//			System.out.println("------------------------New Algorithm--------------------------------:");
//			System.out.println("Total Replicas New Algorithm: " + myNewPartition.replicaCount());
//			
//			try {
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			RandomInput myInputs = new RandomInput(TotalNodes, "Examples//Input.txt"); 
//			try {
//				myInputs.readInputFile();
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
			
			//BalancedPartitionOld myOldPartition = new BalancedPartitionOld(myInput.getNodeList());
			//myOldPartition.FindPartition();
//			System.out.println("------------------------Old Algorithm--------------------------------:");
//			System.out.println("Total Replicas Old Algorithm: " + myOldPartition.replicaCount());
//			
//			if(myOldPartition.replicaCount() != 0 || myNewPartition.replicaCount() != 0){
//				
//				int replicaDiff = myOldPartition.replicaCount() -  myNewPartition.replicaCount();
//				if(replicaDiff == 0){ // Both algorithm has same input
//					SameOutputCount++;
//				}
//				else if(replicaDiff > 0){
//					NewAlgorithmEfficientCnt++;
//				}
//				else if(replicaDiff < 0){
//					OldAlgorithmEfficientCnt++;
//				}
//			}
		
			
//			try {
//				TimeUnit.SECONDS.sleep(5);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		//}
		
//		System.out.println("-----------------------------------------------------------------:");
//		System.out.println("New Algorithm Efficiency Count: " + NewAlgorithmEfficientCnt);
//		System.out.println("-----------------------------------------------------------------:");
//		System.out.println("Old Algorithm Efficiency Count: " + OldAlgorithmEfficientCnt);
//		System.out.println("-----------------------------------------------------------------:");
//		System.out.println("Both of Them Have Same Input: " + SameOutputCount);
//		System.out.println("-----------------------------------------------------------------:");
//	
	}

}


		
/*	try {
		TimeUnit.SECONDS.sleep(1);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

*/
