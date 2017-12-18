import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.*;
import com.sun.management.OperatingSystemMXBean;

public class SJF extends ProcessingAlgorithms{
	
	public SJF(ArrayList<Process> a) throws Exception{
		pa = a;
		
		long cputimeBefore = System.currentTimeMillis();
		shortestJobFirst(pa);
		long cputimeAfter = System.currentTimeMillis();
		long cpuTimeDifference = cputimeAfter - cputimeBefore;
		printCPUTime(cputimeBefore, cputimeAfter);
		printResult(pa);
		getPerformance();
		printCPUInfo();
	}
	
	
	//shortest job first algorithm
	public static void shortestJobFirst(ArrayList<Process> pa) {
		SortedSet<Process>  ps = new TreeSet<>();					//sortedset for managing processes before processing
		
		int currentTime = 0;					//current time (increments one unit time at a time)
		
		while(allNotDone(pa)) {
			
			//adding process to sortedset...
			for(Process p:pa) {
				if(p.arrivedAt(currentTime) && !p.completed) {
					ps.add(p);
				}
			}
			
			//printPS(ps, currentTime);
			
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
	
	//Prints queue
	public static void printPS(SortedSet<Process> ps, int ct) {
		Iterator<Process> psi = ps.iterator();
		System.out.print(ct+":\t");
		if(psi.hasNext()) {
			while(psi.hasNext()) {
				System.out.print(psi.next().name+"|");
			}
			System.out.println("");
		}else {
			System.out.println("Set is empty");
		}
	}
	
}