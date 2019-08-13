import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.*;
import com.sun.management.OperatingSystemMXBean;

public class SJF extends ProcessingAlgorithms{
	
	public SJF(ArrayList<Process> a) throws Exception{
		pa = a;
		try {
			long cputimeBefore = System.nanoTime();
			shortestJobFirst(pa);
			long cputimeAfter = System.nanoTime();
			printResult(pa);
			calcCPUTime(cputimeBefore, cputimeAfter);
			printCPUInfo();
		}catch(IndexOutOfBoundsException e) {
			AlertBox.handle();
		}
	}
	
	
	//shortest job first algorithm
	public static void shortestJobFirst(ArrayList<Process> pa) {
		ArrayList<Process>  ps = new ArrayList<>();	//sortedset for managing processes before processing
		
		int currentTime = 0;						//current time (increments one unit time at a time)
		while(notAllDone(pa)) {			
		
			//adding process to sortedset...
			for(Process p:pa) {
				if(p.arrivedAt(currentTime) && !p.completed) {
					ps.add(p);
					Collections.sort(ps);
				}				
			}
			//processing first element in ps, ie element of highest priority
			if(!ps.isEmpty()) {
				ps.get(0).processing();
				if(ps.get(0).done(currentTime)) {
					ps.remove(ps.get(0));
				}
			}	
			currentTime++;
		}	
	}
}