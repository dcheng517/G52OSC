import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.*;
import com.sun.management.OperatingSystemMXBean;

public class RR {
	static Queue<Process> pq = new LinkedList<>();			//queue for managing processes before processing
	static LinkedList<Process> ps = new LinkedList<>();		//linkedlist to process processes
	static Process[] pa;									//array to store all processes
	static final int TQ = 3;								//time quantum
	static int n;											//number of processes
	
	
	public static void main(String[] args) throws Exception{
		InputStreamReader isr = new InputStreamReader(System.in); // bytes to char
		BufferedReader br = new BufferedReader(isr); 
		
		
		System.out.print("Enter number of processes: ");
		n = Integer.parseInt(br.readLine()); // char to int
		
		
		getProcesses(n);
		
		long cputimeBefore = System.currentTimeMillis();
		roundRobin(pa);
		long cputimeAfter = System.currentTimeMillis();
		long cpuTimeDifference = cputimeAfter - cputimeBefore;

		System.out.println("CPU Time: " + cpuTimeDifference);
		
		printResult();
		printAvetat();
		printAvewt();
		printCPUInfo();
	}
	
	
	//round robin algorithm
	//implements a per unit time cycle concept
	//e.g., if TQ = 3, then TQ = 3 unit time cycle
	public static void roundRobin(Process[] pa) {
		
		int currentTime=0;					//current time (increments one unit time at a time)
		Process outStandingProcess = null;
	
		while(allNotDone(pa)) {
		
			//adding process to queue...
			for(Process p:pa) {
				//System.out.println("*This process is: "+p.name); 
				if(p.arrivedAt(currentTime) && !p.completed) {
					pq.add(p);	
				}
			}			
			if(outStandingProcess!=null) {
				pq.add(outStandingProcess);	
				outStandingProcess = null;
			}
			
			//processing process...
			if(pq.peek()!=null) {
				pq.peek().processing(currentTime);		//processing head of queue
				if(pq.peek().done(currentTime)) {		//check for completion
					pq.remove();					
				}
			}			
			
			currentTime++;
			
			//set out standing process (if any)
			if(pq.peek()!=null) {	
				
				//end of TQ
				if(pq.peek().tempProcessingTime>TQ) {
					pq.peek().tempProcessingTime=1;		//reset tempProcessingTime
					if(!pq.peek().completed) {
						outStandingProcess = pq.peek();	//assign outstanding processes to next cycle
						pq.remove();
					}					
				}
			}
		}
	}
	
	public static void getProcesses(int n) throws Exception{

		InputStreamReader isr = new InputStreamReader(System.in); // bytes to char
		BufferedReader br = new BufferedReader(isr);
		
		System.out.println("Enter information for each processes");
		pa = new Process[n];
		
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
			pa[i] = newProcess;
		}
		
		//debugging tool
		
		/*
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
		
		//test case 2
		Process p1 = new Process(0, 5);
		Process p2 = new Process(1, 3);
		Process p3 = new Process(2, 8);
		Process p4 = new Process(3, 6);
		pa[0] = p1;
		pa[1] = p2;
		pa[2] = p3;
		pa[3] = p4;
		
		*/
	}
	
	//debugging tool
	//to use, uncomment Process.printInfo()
	
	/*
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
	*/
	
	//check completion status for all processes
	public static boolean allNotDone(Process[] pa) {
		for(Process p:pa) {
			if(!p.completed) {
				return true;	//if even one isn't done, return true
			}
		}
		return false;			//else return false
	}
	
	//prints result in tabular format
	public static void printResult() {
		System.out.print(" ________________________________________________________________________________");
		System.out.println("\n|PROCESS\t|ARRIVAL-TIME\t|BURST-TIME\t|WAITING-TIME\t|TURN-AROUND-TIME|");
		for(Process p:pa)
		{
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println("|" + p.name + "\t\t|" + p.AT + "\t\t|"+ p.BT + "\t\t|" + p.getWt() + "\t\t|" + p.getTat() + "\t\t |");
		}
		System.out.println("_________________________________________________________________________________\n");
	}
	
	//prints averate turn around time
	public static void printAvetat() {
		float avetat = 0;
		for(Process p:pa) {
			avetat+=p.getTat();
		}
		avetat = avetat/n;
		System.out.println("Average turn around time: "+avetat);
	}
	
	//prints average waiting time
	public static void printAvewt() {
		float avewt = 0;
		for(Process p:pa) {
			avewt+=p.getWt();
		}
		avewt = avewt/n;
		System.out.println("Average waiting time: "+avewt);
	}
	
	//prints CPU Info
	public static void printCPUInfo() {
		OperatingSystemMXBean bean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		System.out.println(bean.getProcessCpuLoad());
		System.out.println("\n" + bean.getProcessCpuTime()+"nanoseconds");
	}
}