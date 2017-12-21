import java.lang.management.ManagementFactory;
import java.util.*;
import com.sun.management.OperatingSystemMXBean;

public class ProcessingAlgorithms {
	static ArrayList<Process> pa;
	static long cpuTimeDifference;
	
	//prints process array in console
	public static void printPA(ArrayList<Process> pa) {
		System.out.println("*Printing processes array");
		System.out.println("*Size of processes array is "+pa.size());		
		for(Process p: pa) {
			p.printInfo();
		}
	}
	
	//check completion status for all processes
	public static boolean notAllDone(ArrayList<Process> pa) {
		for(Process p:pa) {
			if(!p.completed) {
				return true;	//if even one isn't done, return true
			}
		}
		return false;			//else return false
	}
	
	//prints result in tabular format for RR in console
	public static void printResult(ArrayList<Process> pa) {
		int option = pa.get(0).getOption();
		switch(option) {
			
			//1 for result without priority
			case 0: {
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
			case 1: {
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
	
	//returns average waiting time
	public static float getAvewt() {
		float avewt = 0;
		int n = pa.size();
		if(n>0) {
			for(Process p:pa) {
				avewt+=p.getWt();
			}
			avewt = avewt/n;
			System.out.println("Average waiting time: "+avewt);
		}		
		return avewt;
	}
	
	
	//returns average turn around time
	public static float getAvetat() {
		float avetat = 0;
		int n = pa.size();
		if(n>0) {
			for(Process p:pa) {
				avetat+=p.getTat();
			}
			avetat = avetat/pa.size();
			System.out.println("Average turn around time: "+avetat);
		}
		return avetat;
	}
	
	//returns CPU time difference in console
	public static long calcCPUTime(long pre, long post) {
		cpuTimeDifference = post - pre;
		System.out.println("CPU Time: " + cpuTimeDifference);
		return cpuTimeDifference;
	}
	
	public long getCPUTime() {
		return cpuTimeDifference;
	}
	
	//prints CPU Info in console
	public static void printCPUInfo() {
		OperatingSystemMXBean bean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		System.out.println(bean.getProcessCpuLoad());
		System.out.println("\n" + bean.getProcessCpuTime()+"nanoseconds");
	}
	
	//debugging tools
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
}
