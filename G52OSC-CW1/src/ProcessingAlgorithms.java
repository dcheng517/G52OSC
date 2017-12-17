import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.*;
import com.sun.management.OperatingSystemMXBean;

public class ProcessingAlgorithms {
	
	//get process' details from users
	public static Process[] getProcesses() throws Exception{
		Process[] pa;									//array to store all processes
		
		InputStreamReader isr = new InputStreamReader(System.in); // bytes to char
		BufferedReader br = new BufferedReader(isr);
		
		System.out.print("Enter number of processes: ");
		int n = Integer.parseInt(br.readLine()); // char to int
		
		pa = new Process[n];
		System.out.println("Enter information for each processes");
		
		for(int i=0; i<n; i++) {
			int AT;	//arrival time
			int BT;	//burst time
			
			//create new process
			System.out.format("Process"+"[%d]:\n", i+1);
			System.out.print(" Arrival time: ");
			AT = Integer.parseInt(br.readLine()); // char to int
			System.out.print(" Burst time: ");
			BT = Integer.parseInt(br.readLine()); // char to int
			Process newProcess = new Process(AT, BT);
			pa[i] = newProcess;
		}
		
		//debugging tool
		/*
		//test case RR
		Process RR1 = new Process(5, 5);
		Process RR2 = new Process(4, 6);
		Process RR3 = new Process(3, 7);
		Process RR4 = new Process(1, 9);
		Process RR5 = new Process(2, 2);
		Process RR6 = new Process(6, 3);
		pa[0] = RR1;
		pa[1] = RR2;
		pa[2] = RR3;
		pa[3] = RR4;
		pa[4] = RR5;
		pa[5] = RR6;
		
		//test case Priority scheduling
		Process pp1 = new Process(0, 10, 3);
		Process pp2 = new Process(0, 1, 1);
		Process pp3 = new Process(0, 2, 4);
		Process pp4 = new Process(0, 1, 5);
		Process pp5 = new Process(0, 5, 2);
		pa[0] = pp1;
		pa[1] = pp2;
		pa[2] = pp3;
		pa[3] = pp4;
		pa[4] = pp5;
		
		//test case FCFS
		Process p1 = new Process(0, 24);
		Process p2 = new Process(0, 3);
		Process p3 = new Process(0, 3);
		pa[0] = p1;
		pa[1] = p2;
		pa[2] = p3;
		*/
		
		return pa;
	}
	
	//debugging tool
	//to use, uncomment Process.printInfo()
	
	
	public static void printPA(Process[] pa) {
		System.out.println("*Printing processes array");
		for(Process p: pa) {
			p.printInfo();
		}
	}
	/*
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
	
	//prints result in tabular format for RR
	public static void printResult(Process[] pa) {
		int option = pa[0].getOption();
		switch(option) {
			
			//1 for result without priority
			case 1: {
				System.out.print(" ________________________________________________________________________________");
				System.out.println("\n|PROCESS\t|ARRIVAL-TIME\t|BURST-TIME\t|WAITING-TIME\t|TURN-AROUND-TIME|");
				for(Process p:pa)
				{
					System.out.println("----------------------------------------------------------------------------------");
					System.out.println("|" + p.name + "\t\t|" + p.getAT() + "\t\t|"+ p.getBT() + "\t\t|" + p.getWt() + "\t\t|" + p.getTat() + "\t\t |");
				}
				System.out.println("_________________________________________________________________________________\n");
			}
			break;
			
			//2 for result with priority
			case 2: {
				System.out.print(" ________________________________________________________________________________________________");
				System.out.println("\n|PROCESS\t|ARRIVAL-TIME\t|BURST-TIME\t|PRIORITY\t|WAITING-TIME\t|TURN-AROUND-TIME|");
				for(Process p:pa)
				{
					System.out.println("-------------------------------------------------------------------------------------------------");
					System.out.println("|" + p.name + "\t\t|" + p.getAT() + "\t\t|"+ p.getBT() + "\t\t|"+ p.getP() + "\t\t|" + p.getWt() + "\t\t|" + p.getTat() + "\t\t |");
				}
				System.out.println("_________________________________________________________________________________________________\n");
			}
			break;
			
			default:
				System.out.println("Error in printing result");
			break;
		}
	}
	
	
	//prints averate turn around time
	public static void printAvetat(Process[] pa) {
		float avetat = 0;
		for(Process p:pa) {
			avetat+=p.getTat();
		}
		avetat = avetat/pa.length;
		System.out.println("Average turn around time: "+avetat);
	}
	
	//prints average waiting time
	public static void printAvewt(Process[] pa) {
		float avewt = 0;
		for(Process p:pa) {
			avewt+=p.getWt();
		}
		avewt = avewt/pa.length;
		System.out.println("Average waiting time: "+avewt);
	}
	
	//prints CPU Info
	public static void printCPUInfo() {
		OperatingSystemMXBean bean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		System.out.println(bean.getProcessCpuLoad());
		System.out.println("\n" + bean.getProcessCpuTime()+"nanoseconds");
	}
}
