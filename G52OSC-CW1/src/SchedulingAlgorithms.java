import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.*;
import com.sun.management.OperatingSystemMXBean;

public class SchedulingAlgorithms {
	static long cpuTimeDifference;
	
	//debugging tool
	//to use, uncomment Process.printInfo()
	public static void printPA(ArrayList<Process> a) {
		System.out.println("*Printing processes array");
		for(Process p: a) {
			p.printInfo();
		}
	}
	
	//Prints queue
	public static void printPS(ArrayList<Process> a, int ct) {
		Iterator<Process> pai = a.iterator();
		System.out.print(ct+":\t");
		if(pai.hasNext()) {
			while(pai.hasNext()) {
				System.out.print(pai.next().name+"|");
			}
			System.out.println("");
		}else {
			System.out.println("Queue is empty");
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
	public static boolean notAllDone(ArrayList<Process> a) {
		for(Process p:a) {
			if(!p.completed) {
				return true;	//if even one isn't done, return true
			}
		}
		return false;			//else return false
	}
	
	//prints result in tabular format for RR
	public static void printResult(ArrayList<Process> a) {
		int option = a.get(0).getOption();
		switch(option) {
			
			//1 for result without priority
			case 0: {
				System.out.print(" ________________________________________________________________________________");
				System.out.println("\n|PROCESS\t|ARRIVAL-TIME\t|BURST-TIME\t|WAITING-TIME\t|TURN-AROUND-TIME|");
				for(Process p:a)
				{
					System.out.println("----------------------------------------------------------------------------------");
					System.out.println("|" + p.name + "\t\t|" + p.getAT() + "\t\t|"+ p.getBT() + "\t\t|" + p.getWt() + "\t\t|" + p.getTat() + "\t\t |");
				}
				System.out.println("_________________________________________________________________________________\n");
			}
			break;
			
			//2 for result with priority
			case 1: {
				System.out.print(" ________________________________________________________________________________________________");
				System.out.println("\n|PROCESS\t|ARRIVAL-TIME\t|BURST-TIME\t|PRIORITY\t|WAITING-TIME\t|TURN-AROUND-TIME|");
				for(Process p:a)
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
	
	//prints average waiting time
	public static void printAveWt(ArrayList<Process> a) {
		float avewt = 0;
		int n = a.size();
		if(n>0) {
			for(Process p:a) {
				avewt+=p.getWt();
			}
			avewt = avewt/n;
			System.out.println("Average waiting time: "+avewt);
		}	
	}
	

	//prints averate turn around time
	public static void printAveTat(ArrayList<Process> a) {
		float avetat = 0;
		int n = a.size();
		if(n>0) {
			for(Process p:a) {
				avetat+=p.getTat();
			}
			avetat = avetat/n;
			System.out.println("Average turn around time: "+avetat);
		}
	}

	//prints CPU time difference in console
	public static void printCPUTime(long pre, long post) {
		cpuTimeDifference = post - pre;
		System.out.println("Processing time in nanoseconds: "+cpuTimeDifference);
	}
		
	//prints CPU Info
	public static void printCPUInfo() {
		OperatingSystemMXBean bean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		System.out.println(bean.getProcessCpuLoad());
		System.out.println(bean.getProcessCpuTime()+"nanoseconds\n\n");
	}
}
