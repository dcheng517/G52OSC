import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.*;
import com.sun.management.OperatingSystemMXBean;

public class PrioSched extends ProcessingAlgorithms{
	static Process[] pa;					//array to store all processes
	
	public PrioSched() throws Exception{
		
		pa = getProcesses();
		
		long cputimeBefore = System.currentTimeMillis();
		priorityScheduling(pa);
		long cputimeAfter = System.currentTimeMillis();
		long cpuTimeDifference = cputimeAfter - cputimeBefore;

		System.out.println("CPU Time: " + cpuTimeDifference);
		
		printResult(pa);
		printAvetat(pa);
		printAvewt(pa);
		printCPUInfo();
	}
	
	
	//priorityScheduling algorithm
	public static void priorityScheduling(Process[] pa) {
		
		//sortedset for managing processes before processing
		SortedSet<Process> ps = new TreeSet<>();	
		
		//current time (increments one unit time at a time)
		int currentTime = 0;					
		
		while(allNotDone(pa)) {
			
			//adding process to sortedset...
			for(Process p:pa) {
				if(p.arrivedAt(currentTime) && !p.completed) {
					ps.add(p);
				}
			}
			
			//super.printPS();
			
			//processing first element in ps, ie element of highest priority
			if(!ps.isEmpty()) {
				ps.first().processing();
				if(ps.first().done(currentTime)) {
					ps.remove(ps.first());
				}
			}	
			currentTime++;
		}	
	}
		
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
			pa[i] = newProcess;
		}
		
		return pa;
	}
}