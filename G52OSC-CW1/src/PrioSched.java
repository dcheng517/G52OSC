import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.*;
import com.sun.management.OperatingSystemMXBean;

public class PrioSched extends ProcessingAlgorithms{
	public PrioSched(ArrayList<Process> a) throws Exception{
		
		pa = a;
		try {
			long cputimeBefore = System.nanoTime();
			priorityScheduling(pa);
			long cputimeAfter = System.nanoTime();
			calcCPUTime(cputimeBefore, cputimeAfter);
			printCPUInfo();
		}catch(IndexOutOfBoundsException e) {
			AlertBox.handle();
		}
	}
	
	public static void priorityScheduling(ArrayList<Process> pa) {
			
		SortedSet<Process> ps = new TreeSet<>();	//sortedset for managing processes before processing
		int currentTime = 0;						//current time (increments one unit time at a time)		
		
		while(allNotDone(pa)) {
			
			//adding process to sortedset...
			for(Process p:pa) {
				if(p.arrivedAt(currentTime) && !p.completed) {
					ps.add(p);
				}
			}
			
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
}