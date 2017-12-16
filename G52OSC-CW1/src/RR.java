import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.*;
import com.sun.management.OperatingSystemMXBean;

public class RR {
	static Queue<Process> pq = new LinkedList<>();			//queue for RR
	static LinkedList<Process> ps = new LinkedList<>();		//processing slots
	static Process[] pa;									//array to store all processes
	static final int TQ = 3;								//time quantum
	static int n=6;											//number of processes
	
	
	public static void main(String[] args) throws Exception{
		InputStreamReader isr = new InputStreamReader(System.in); // bytes to char
		BufferedReader br = new BufferedReader(isr); 
		
		/*
		System.out.print("Enter number of processes: ");
		n = Integer.parseInt(br.readLine()); // char to int
		*/
		
		allProcesses(n);
		printPA();
		
		long cputimeBefore = System.currentTimeMillis();
		roundRobin(pa);
		long cputimeAfter = System.currentTimeMillis();
		long cpuTimeDifference = cputimeAfter - cputimeBefore;

		System.out.println("CPU Time: " + cpuTimeDifference);
		
		printPA();
		printResult();
		printAvetat();
		printAvewt();
		
		printCPUInfo();
	}
	
	public static void roundRobin(Process[] pa) {
		
		int currentTime=0;			//current time (increments one unit time at a time)
		Process outStandingProcess = null;
	//	Process workingProcess = null;
		
		int i=0;					//RR #round
		while(notDone(pa)) {
		//while(currentTime<33) {
			System.out.println("\n\nRR round "+i);
			System.out.println("Current time: "+currentTime); 
			
			//add process to queue
			for(Process p:pa) {
				//System.out.println("*This process is: "+p.name); 
				if(p.happenedAt(currentTime) && !p.completed) {
					pq.add(p);	
					
					System.out.println(p.name+" is added to pq");
				}
			}
			
			if(outStandingProcess!=null) {
				pq.add(outStandingProcess);	
				System.out.println("outStandingProcess "+outStandingProcess.name+" added to queue");
				outStandingProcess = null;
			}
			
			printPQ();
			
			//processing...
			if(pq.peek()!=null) {
				
				//System.out.println("*pq.peek() is "+pq.peek().name);					
				//workingProcess = pq.peek();	
				//System.out.println("*workingProcess is "+workingProcess.name);
				
				pq.peek().processing(currentTime);	
				pq.peek().processingTime++;	
				pq.peek().printInfo();
				if(pq.peek().completelyFinishProcessing()) {
					pq.remove();					
				}
			}			
			
			currentTime++;
			
			//set out standing process (if any)
			if(pq.peek()!=null) {
				
				//new TQ round
				if(pq.peek().processingTime>TQ) {
					
					System.out.println("New UpperLimit");
					pq.peek().processingTime=1;
					if(!pq.peek().completed) {
						//System.out.println(workingProcess.name+" completed is "+workingProcess.completed);
						outStandingProcess = pq.peek();

						System.out.println("*workingProcess is "+pq.peek().name);

						System.out.println("*pq.peek() is "+pq.peek().name);
						pq.remove();

						System.out.println("*outStandingProcess is "+outStandingProcess.name);
					}					
				}
			}
			i++;
		}
	}
	
	public static void allProcesses(int n) throws Exception{

		InputStreamReader isr = new InputStreamReader(System.in); // bytes to char
		BufferedReader br = new BufferedReader(isr);
		
		System.out.println("Enter information for each processes");
		pa = new Process[n];
		
		
		//test case 1
		Process p1 = new Process(5, 5);
		Process p2 = new Process(4, 6);
		Process p3 = new Process(3, 7);
		Process p4 = new Process(1, 9);
		Process p5 = new Process(2, 2);
		Process p6 = new Process(6, 3);
		pa[0] = p1;
		pa[1] = p2;
		pa[2] = p3;
		pa[3] = p4;
		pa[4] = p5;
		pa[5] = p6;
		
		/*
		//test case 2
		Process p1 = new Process(0, 5);
		Process p2 = new Process(1, 3);
		Process p3 = new Process(2, 8);
		Process p4 = new Process(3, 6);
		pa[0] = p1;
		pa[1] = p2;
		pa[2] = p3;
		pa[3] = p4;
		
		/*
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
		
		*/
	}
	
	public static void printPA() {
		System.out.println("*Printing processes array");
		for(Process p: pa) {
			p.printInfo();
		}
	}
	
	public static void printPQ() {
		System.out.println("*Printing processes queue");
		if(pq.isEmpty()) {
			System.out.println("Queue is empty");
		}else {
			for(Process p: pq) {
				p.printInfo();
			}			
		}
	}
	
	public static void printPS() {
		System.out.println("*Printing processes slot");
		for(Process p: ps) {
			p.printInfo();
		}
	}
	
	public static boolean notDone(Process[] pa) {
		for(Process p:pa) {
			if(!p.completed) {
				return true;	//if even one isn't done, return true
			}
		}
		return false;			//else return false
	}
	
	public static void printResult() {
		System.out.print(" ____________________________________________________________________");
		System.out.println("\n|Process\t|BURST-TIME\t|WAITING-TIME\t|TURN AROUND-TIME|");
		for(Process p:pa)
		{
			System.out.println("--------------------------------------------------------------------");
			System.out.println("|" + p.name + "\t\t|" + p.BT + "\t\t|" + p.wt + "\t\t|" + p.tat + "\t\t    |");
		}
		System.out.println(" ____________________________________________________________________\n");
	}
	
	public static void printAvetat() {
		float avetat = 0;
		for(Process p:pa) {
			avetat+=p.tat;
		}
		avetat = avetat/n;
		System.out.println("Average turn around time: "+avetat);
	}
	
	public static void printAvewt() {
		float avewt = 0;
		for(Process p:pa) {
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
