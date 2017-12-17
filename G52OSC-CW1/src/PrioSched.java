import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.*;
import com.sun.management.OperatingSystemMXBean;

public class PrioSched {
	static SortedSet<Process>  ps = new TreeSet<>();					//sortedset for managing processes before processing
	static LinkedList<Process> pl = new LinkedList<>();					//linkedlist to process processes
	static ArrayList<Process> pa = new ArrayList<>();					//array to store all processes
	static int n;
	
	public static void main(String[] args) throws Exception{
		InputStreamReader isr = new InputStreamReader(System.in); // bytes to char
		BufferedReader br = new BufferedReader(isr); 
		
		
		System.out.print("Enter number of processes: ");
		n = Integer.parseInt(br.readLine()); // char to int
		
		
		getProcesses();
		
		long cputimeBefore = System.currentTimeMillis();
		priorityScheduling(pa);
		long cputimeAfter = System.currentTimeMillis();
		long cpuTimeDifference = cputimeAfter - cputimeBefore;

		System.out.println("CPU Time: " + cpuTimeDifference);
		
		printResult();
		printAvetat();
		printAvewt();
		printCPUInfo();
	}
	
	
	//priorityScheduling algorithm
	public static void priorityScheduling(ArrayList<Process> pa) {
		
		int currentTime = 0;					//current time (increments one unit time at a time)
		
		while(allNotDone(pa)) {
			
			//adding process to sortedset...
			for(Process p:pa) {
				if(p.arrivedAt(currentTime) && !p.completed) {
					ps.add(p);
				}
			}
			
			//processing first element in ps, ie element of highest priority
			if(!ps.isEmpty()) {
				ps.first().processing(currentTime);
				if(ps.first().done(currentTime)) {
					ps.remove(ps.first());
				}
			}	
			currentTime++;
		}	
	}
	
	public static void getProcesses() throws Exception{

		InputStreamReader isr = new InputStreamReader(System.in); // bytes to char
		BufferedReader br = new BufferedReader(isr);
		
		System.out.println("Enter information for each processes:");
		
		for(int i=0; i<n; i++) {
			int AT;	//arrival time
			int BT;	//burst time
			int P;	//priority
			//create new process
			System.out.format("Process"+"[%d]:\n", i+1);
			System.out.print(" Arrival time: ");
			AT = Integer.parseInt(br.readLine()); // char to int
			
			
			System.out.print(" Burst time: ");
			BT = Integer.parseInt(br.readLine()); // char to int
			
			
			System.out.print(" Priority: ");
			P = Integer.parseInt(br.readLine()); // char to int
			
			Process newProcess = new Process(AT, BT, P);
			pa.add(newProcess);
		}
		
		
		//debugging tool
		
		/*
		//test case 1
		Process p1 = new Process(0, 6, 4);
		Process p2 = new Process(0, 8, 1);
		Process p3 = new Process(0, 7, 3);
		Process p4 = new Process(0, 3, 2);
		Process p5 = new Process(9, 16, 4);
		pa.add(p1);
		pa.add(p2);
		pa.add(p3);
		pa.add(p4);
		pa.add(p5);
		
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
		*/
	}
	
	//debugging tool
	//to use, uncomment Process.printInfo()
	/*
	public static void printPS() {
		System.out.println("*Printing processes slot");
		for(Process p: ps) {
			p.printInfo();
		}
	}
	
	public static void printPL() {
		System.out.println("*Printing processes list");
		if(pl.isEmpty()) {
			System.out.println("List is empty");
		}else {
			for(Process p: pl) {
				p.printInfo();
			}			
		}
	}
	
	public static void printPA() {
		System.out.println("*Printing processes array");
		for(Process p: pa) {
			p.printInfo();
		}
	}
	
	*/
	
	//check completion status for all processes
	public static boolean allNotDone(ArrayList<Process> pa) {
		for(Process p:pa) {
			if(!p.completed) {
				return true;	//if even one isn't done, return true
			}
		}
		return false;			//else return false
	}
	
	//prints result in tabular format
	public static void printResult() {
		System.out.print(" ________________________________________________________________________________________________");
		System.out.println("\n|PROCESS\t|ARRIVAL-TIME\t|BURST-TIME\t|PRIORITY\t|WAITING-TIME\t|TURN-AROUND-TIME|");
		for(Process p:pa)
		{
			System.out.println("-------------------------------------------------------------------------------------------------");
			System.out.println("|" + p.name + "\t\t|" + p.getAT() + "\t\t|"+ p.getBT() + "\t\t|"+ p.getP() + "\t\t|" + p.getWt() + "\t\t|" + p.getTat() + "\t\t |");
		}
		System.out.println("_________________________________________________________________________________________________\n");
	}
	
	//prints averate turn around time
	public static void printAvetat() {
		float avetat = 0;
		for(Process p:pa) {
			avetat+=p.getTat();
		}
		avetat = avetat/pa.size();
		System.out.println("Average turn around time: "+avetat);
	}
	
	//prints average waiting time
	public static void printAvewt() {
		float avewt = 0;
		for(Process p:pa) {
			avewt+=p.getWt();
		}
		avewt = avewt/pa.size();
		System.out.println("Average waiting time: "+avewt);
	}
	
	//prints CPU Info
	public static void printCPUInfo() {
		OperatingSystemMXBean bean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		System.out.println(bean.getProcessCpuLoad());
		System.out.println("\n" + bean.getProcessCpuTime()+"nanoseconds");
	}
}