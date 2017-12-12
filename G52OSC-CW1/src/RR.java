import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class RR {
	static Queue<Process> processesQueue = new LinkedList<>();
	static LinkedList<Process> processesSlot = new LinkedList<>();
	static Process[] processesArray;
	
	public static void main(String[] args) throws Exception{
		
		int n=6;				//number of processes
		int timeQuantum = 2;	//time quantum
		
		InputStreamReader isr = new InputStreamReader(System.in); // bytes to char
		BufferedReader br = new BufferedReader(isr); 
		
		/*
		System.out.print("Enter number of processes: ");
		n = Integer.parseInt(br.readLine()); // char to int
		*/
		
		allProcesses(n);
		
		/*----------round robin algorithm----------*/
		int currentTime = 0;
		
		
		Process outStandingProcess = null;
		int i=1;
		//loop through all processes till all processes's BT == 0
		
		int next=0;	
		while(notdone()) {
			for(int l=0; l<n; l++) {
				System.out.println("\n\nRound robin starting "+i);
				System.out.println("Current time is "+currentTime);

				
					System.out.println("Next is: "+next);
					//add processes to queue
					for(int j=next; j<n; j++) {
						if(processesArray[j].happenedWithin(currentTime)) {
							processesQueue.add(processesArray[j]);
							next++;
							System.out.println("Process added to queue is "+processesArray[j].name);
						}
					}
				
				
				
				System.out.println("Queue finish populated");
				printPQ();
				
				if(outStandingProcess!=null) {
					System.out.println("Outstanding process from prev round was "+outStandingProcess.name);
					processesQueue.add(outStandingProcess);	
					printPQ();
				}
				
				//add head of queue to working processes
				Process workingProcess = processesQueue.poll();
				processesSlot.add(workingProcess);
				
				//updating BT of current process being worked on
				if(!workingProcess.finishProcessing()) {
					workingProcess.processing(timeQuantum);	
					
					//current process being worked on becomes
					//outstanding process is BT != 0
					if(!workingProcess.finishProcessing()) {
						outStandingProcess = workingProcess;
						System.out.println("Outstanding process for this round is "+outStandingProcess.name);
					}else {
						outStandingProcess = null;
					}
				}
				
			
				System.out.println("End of round"+i);
				printPS();
				i++;
				currentTime += timeQuantum;
			}
		}		
	}
	
	public static void allProcesses(int n) throws Exception{

		InputStreamReader isr = new InputStreamReader(System.in); // bytes to char
		BufferedReader br = new BufferedReader(isr);
		
		System.out.println("Enter information for each processes");
		processesArray = new Process[n];
		
		//test case
		Process p1 = new Process(1, 0, 4);
		Process p2 = new Process(2, 1, 5);
		Process p3 = new Process(3, 2, 6);
		Process p4 = new Process(4, 4, 1);
		Process p5 = new Process(5, 6, 3);
		Process p6 = new Process(6, 7, 2);
		processesArray[0] = p1;
		processesArray[1] = p2;
		processesArray[2] = p3;
		processesArray[3] = p4;
		processesArray[4] = p5;
		processesArray[5] = p6;
		printPA();
		
		/*for(int i=0; i<n; i++) {
			int AT;	//arrival time
			int BT;	//burst time
			
			//create new process
			System.out.format("Process"+"[%d]:\n", i+1);
			System.out.println(" Arrival time: ");
			AT = Integer.parseInt(br.readLine()); // char to int
			System.out.println(" Burst time: ");
			BT = Integer.parseInt(br.readLine()); // char to int

			Process newProcess = new Process(AT, BT);
		}
		*/
	}
	
	public static void printPA() {
		System.out.println("Printing processes array");
		for(Process p: processesArray) {
			p.ProcessInfo();
		}
	}
	
	public static void printPQ() {
		System.out.println("Printing processes queue");
		for(Process p: processesQueue) {
			p.ProcessInfo();
		}
	}
	
	public static void printPS() {
		System.out.println("Printing processes slot");
		for(Process p: processesSlot) {
			p.ProcessInfo();
		}
	}
	
	public static boolean notdone() {
		for(Process p:processesArray) {
			if(p.BT!=0) {
				return true;
			}
		}
		return false;
	}
}













