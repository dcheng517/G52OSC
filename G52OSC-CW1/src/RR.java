import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.*;
import com.sun.management.OperatingSystemMXBean;

public class RR {
	static Queue<Process> processesQueue = new LinkedList<>();
	static LinkedList<Process> processesSlot = new LinkedList<>();
	static Process[] processesArray;
	static final int TQ = 3;	//time quantum
	static int n;	//number of processes
	
	
	public static void main(String[] args) throws Exception{
		InputStreamReader isr = new InputStreamReader(System.in); // bytes to char
		BufferedReader br = new BufferedReader(isr); 
		
		System.out.print("Enter number of processes: ");
		n = Integer.parseInt(br.readLine()); // char to int
		
		allProcesses(n);
		printPA();
		
		long cputimeBefore = System.currentTimeMillis();
		roundRobin(processesArray);
		long cputimeAfter = System.currentTimeMillis();
		long cpuTimeDifference = cputimeAfter - cputimeBefore;

		System.out.println("CPU Time: " + cpuTimeDifference);
		
		printResult();
		printAvetat();
		printAvewt();
		
		printCPUInfo();
	}
	
	public static void roundRobin(Process[] pa) {
		int currentTime = 0;
		int nextTime = currentTime;
		Process workingProcess;
		Process outStandingProcess = null;
		int next=0;	
		int n = pa.length;
	
		//while not all processes are finished
		while(notdone()) {		
			//for(int l=0; l<n; l++) {
				printPA();
				//printPS();
				
				System.out.println("\n\nRR. Current time is "+currentTime);
				printPQ();
				
				System.out.println("Adding to process queue...");
				//add processes to queue
				for(int j=next; j<n; j++) {
					System.out.println("Current j is "+j);
					processesArray[j].ProcessInfo();
					if(processesArray[j].happenedWithin(currentTime)) {
						processesQueue.add(processesArray[j]);
						System.out.println(processesArray[j].name+" is added to queue");
						next++;
						System.out.println("New next is "+next);
					}
				}
				if(outStandingProcess!=null) {
					processesQueue.add(outStandingProcess);
				}
				
				printPQ();
				
				if(!processesQueue.isEmpty()){
					//add head of queue to working processes
					workingProcess = processesQueue.poll();
					processesSlot.add(workingProcess);
					
					//set next working time
					if(workingProcess.remBT>=TQ) {
						nextTime +=TQ;
					}else {
						nextTime += workingProcess.remBT;
					}	
					
					//updating BT of current process being worked on
					if(!workingProcess.completelyFinishProcessing()) {
						workingProcess.recordStartTime(currentTime);
						workingProcess.processing(currentTime);	
						
						//current process being worked on becomes
						//outstanding process if BT != 0
						if(!workingProcess.completelyFinishProcessing()) {
							workingProcess.recordFinishTime(nextTime);
							outStandingProcess = workingProcess;
						}else {
							outStandingProcess = null;
						}
					}
				}else {
					nextTime +=TQ;
				}
				currentTime = nextTime;	
			//}
			System.out.println("whilewhilewhilewhilewhilewhilewhilewhile");
		}
	}
	
	public static void allProcesses(int n) throws Exception{

		InputStreamReader isr = new InputStreamReader(System.in); // bytes to char
		BufferedReader br = new BufferedReader(isr);
		
		System.out.println("Enter information for each processes");
		processesArray = new Process[n];
		
		/*
		//test case
		Process p1 = new Process(0, 4);
		Process p2 = new Process(1, 5);
		Process p3 = new Process(2, 6);
		Process p4 = new Process(4, 1);
		Process p5 = new Process(6, 3);
		Process p6 = new Process(7, 2);
		processesArray[0] = p1;
		processesArray[1] = p2;
		processesArray[2] = p3;
		processesArray[3] = p4;
		processesArray[4] = p5;
		processesArray[5] = p6;
		printPA();
		*/
		for(int i=0; i<n; i++) {
			int AT;	//arrival time
			int BT;	//burst time
			
			//create new process
			System.out.format("Process"+"[%d]:\n", i+1);
			System.out.println(" Arrival time: ");
			AT = Integer.parseInt(br.readLine()); // char to int
			System.out.println(" Burst time: ");
			BT = Integer.parseInt(br.readLine()); // char to int
			Process newProcess = new Process(AT, BT);
			processesArray[i] = newProcess;
		}
		
		Arrays.sort(processesArray);
	}
	
	public static void printPA() {
		System.out.println("Printing processes array");
		for(Process p: processesArray) {
			p.ProcessInfo();
		}
	}
	
	public static void printPQ() {
		System.out.println("Printing processes queue");
		if(processesQueue.isEmpty()) {
			System.out.println("Queue is empty");
		}else {
			for(Process p: processesQueue) {
				p.ProcessInfo();
			}			
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
			if(!p.completed) {
				return true;	//if even one isn't done, return true
			}
		}
		return false;			//else return false
	}
	
	public static void printResult() {
		System.out.print(" ____________________________________________________________________");
		System.out.println("\n|Process\t|BURST-TIME\t|WAITING-TIME\t|TURN AROUND-TIME   |");
		for(Process p:processesArray)
		{
			System.out.println("--------------------------------------------------------------------");
			System.out.println("|" + p.name + "\t\t|" + p.BT + "\t\t|" + p.wt + "\t\t|" + p.tat + "\t\t    |");
		}
		System.out.println(" ____________________________________________________________________\n");
	}
	
	public static void printAvetat() {
		float avetat = 0;
		for(Process p:processesArray) {
			avetat+=p.tat;
		}
		avetat = avetat/n;
		System.out.println("Average turn around time: "+avetat);
	}
	
	public static void printAvewt() {
		float avewt = 0;
		for(Process p:processesArray) {
			avewt+=p.wt;
		}
		avewt = avewt/n;
		System.out.println("Average waiting time: "+avewt);
	}
	
	public static void printCPUInfo() {
		OperatingSystemMXBean bean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		System.out.println(bean.getProcessCpuLoad());
		System.out.println("\n" + bean.getProcessCpuTime()+"nanoseconds");
	}
}
