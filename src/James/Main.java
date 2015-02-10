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
		//RandomInput myInput = new RandomInput(10, "Examples//Input (1).txt"); // Loop Count = 1; Replicas = 8 or 9 based on random node
		RandomInput myInput = new RandomInput(9, "Examples//Input (2).txt"); // Loop Count = 2; Replicas = 8
		//RandomInput myInput = new RandomInput(5, "Examples//Input (3).txt"); // Loop Count = 1; Replicas = 4 or 5 based on random node chosen
		//RandomInputNew myInput = new RandomInputNew(9, "Examples//Input (4).txt"); // Loop Count = 0; Replicas = 8 
		//RandomInput myInput = new RandomInput(5, "Examples//Input (5).txt"); // Loop Count = 1; Replicas = 5 
		//RandomInput myInput = new RandomInput(5, "Examples//Input (6).txt"); // Loop Count = 1; Replicas = 4
		//RandomInput myInput = new RandomInput(5, "Examples//Input (7).txt"); // Loop Count = 1; Replicas = 4 
		//RandomInput myInput = new RandomInput(8, "Examples//Input (8).txt"); // Loop Count = 0; Replicas = 5
		//RandomInput myInput = new RandomInput(6, "Examples//Input (9).txt"); // Loop Count = 0; Replicas = 5 
		//RandomInput myInput = new RandomInput(7, "Examples//Input (10)"); // Loop Count = 0; Replicas = 4 
		//RandomInput myInput = new RandomInput(9, "Examples//Example_9_Slide.txt"); // Loop Count = 0; Replicas = 8
		//RandomInput myInput = new RandomInput(6, "Examples//Example_Loop_Count_9.txt"); // Loop Count = 0; Replicas = 5 (if 4:2) else 6 (3:3)
		//RandomInput myInput = new RandomInput(5, "Examples//Example_InfiniteLoop.txt"); // Loop Count = 2; Replicas = 5
		//BalancedPartition.MaxLoopCount = 105;
		
		
		//RandomInput myInput = new RandomInput(5, "ForeverLoopCases//ForeverLoop_NumNodes_5_15.txt"); // Loop Count = 0; Replicas = 5
		try {
			myInput.readInputFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		BalancedPartition myPartition = new BalancedPartition(myInput.getNodeList());
		myPartition.FindPartition();
		
		
//		for(int i = 1; i < 16; i++){
//			RandomInput myInput = new RandomInput(5, "ForeverLoopCases//ForeverLoop_NumNodes_5_" + i + ".txt"); // Loop Count = 0; Replicas = 5
//			try {
//				myInput.readInputFile();
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		
//			BalancedPartition myPartition = new BalancedPartition(myInput.getNodeList());
//			myPartition.FindPartition();
//		}
		
	}

}


		
/*	try {
		TimeUnit.SECONDS.sleep(1);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

*/
