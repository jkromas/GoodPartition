package James;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

public class RandomInputNew {
	int NumNodes;
	Random rand = new Random();
	int[][] AdjacencyMatrix = new int[1000][1000];
	ArrayList<GraphNode> myNodeList = new ArrayList<GraphNode>();
	String FileName = "input.txt";
	int myProbability;
	
	public RandomInputNew(){ // define probability for node distribution
		myProbability = 40;
	}
	
	public RandomInputNew (int NumNodes, String FileName){
	
		this.NumNodes = NumNodes;
		this.FileName = FileName;
		this.initializeAdjacentMatrix();
	}
	
	
	public RandomInputNew(int NumNodes, int myProbability){
		this.myProbability = myProbability;
		this.NumNodes = NumNodes;
		this.initializeAdjacentMatrix();
	}
	
	public void initializeAdjacentMatrix(){
		if(this.NumNodes > 1000){
			this.NumNodes = 1000;
		}
		
		for(int i=0; i < this.NumNodes; i++){
			for(int j = 0; j < this.NumNodes; j++){
				AdjacencyMatrix[i][j] = -1; //initialize
			}
		}
	}
	
	public ArrayList<GraphNode> getNodeList(){
		return this.myNodeList;
	}
	

	
	public void generateNodeList(){
	
		this.generateRandomNodes();
		this.assignServer();
		/*for(int i = 0; i < NumNodes; i++){
			for(int j = i; j < NumNodes; j++){
				if(i != j && AdjacencyMatrix[i][j] == 1){ // if they are connected
					Node myNode = this.myNodeList.get(i);
					Node myNeighbor = this.myNodeList.get(j);
					myNode.setNeigbors(myNeighbor);
					myNeighbor.setNeigbors(myNode);
				}
			}
		}
		*/
	}
	
	public void assignServer(){
		for(int i = 0; i < this.NumNodes; i++){
			GraphNode temp = new GraphNode(i+1, 0);
			this.myNodeList.add(temp);
		}
		
		for(int m = 0; m < this.NumNodes; m++){
			for(int n = m; n < this.NumNodes; n++){
				if(m != n && this.AdjacencyMatrix[m][n] == 1){ // if they are connected
					GraphNode myNode = this.myNodeList.get(m);
					GraphNode myNeighbor = this.myNodeList.get(n);
					myNode.setNeigbors(myNeighbor);
					myNeighbor.setNeigbors(myNode);
				}
			}
		}
	}
	
	public void printAdjacencyMatrix(){
		for(int k = 0; k < this.NumNodes; k++){
			for(int l = 0; l < this.NumNodes; l++){
				System.out.print(" " + this.AdjacencyMatrix[k][l]);
			}
			System.out.println();
		}
	}
	
	public void generateRandomNodes(){
		
		for(int i = 0; i < NumNodes; i++){
			for(int j = i; j < NumNodes; j++){
				if(i != j && AdjacencyMatrix[i][j] == -1){
					int random = rand.nextInt(100);
					if(random <= myProbability){ // probabilty
						AdjacencyMatrix[i][j] = AdjacencyMatrix[j][i] = 1;
						
					}
					else{
						AdjacencyMatrix[i][j] = AdjacencyMatrix[j][i] = 0;
					}
	
				}
				else{
					AdjacencyMatrix[i][j] = 0;
				}
			}
			//System.out.println("\n");
		}
		
		PrintWriter writer = null;
		
		try {
			writer = new PrintWriter("Examples\\Input.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//writer.println("The first line");
		//writer.println("The second line");
		
		for(int i = 0; i < this.NumNodes; i++){
			for(int j = 0; j < this.NumNodes; j++){
				System.out.print(" " + AdjacencyMatrix[i][j]);
				writer.print(" " + AdjacencyMatrix[i][j]);
			}
			writer.println();
			System.out.println("\n");
		}
		writer.close();
		
	}
	
	public void readInputFile() throws FileNotFoundException{
		char myInput;
		int i = 0;
		int j = 0;
		FileInputStream myInputStream = new FileInputStream(new File(this.FileName));
		try {
			while(myInputStream.available() > 0){
				myInput = (char) myInputStream.read();
				if(myInput == '\n'){
					i++;
					//JOptionPane.showMessageDialog(null, i);
					j = 0;
				}
				else{
					if(myInput == '1'){
						this.AdjacencyMatrix[i][j] = 1;
						j++;
						//JOptionPane.showMessageDialog(null, myInput);			
					}
					else if(myInput == '0'){
						this.AdjacencyMatrix[i][j] = 0;
						//JOptionPane.showMessageDialog(null, myInput);	
						j++;
					}		
				}
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.printAdjacencyMatrix();
		this.assignServer();
		
	
	
		
	}
	

}