import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import com.sun.management.OperatingSystemMXBean;

public class FCFS extends ProcessingAlgorithms{
										
	public FCFS(ArrayList<Process> a) throws Exception{
		pa = a;
		try {
			long cputimeBefore = System.nanoTime();
			FirstComeFirstServe(pa);
			long cputimeAfter = System.nanoTime();
			calcCPUTime(cputimeBefore, cputimeAfter);
			printCPUInfo();
		}catch(IndexOutOfBoundsException e) {
			AlertBox.handle();
		}
		
	}
	
	public static void FirstComeFirstServe(ArrayList<Process> a) {
		
		Queue<Process> Q = new LinkedList<Process>();
		
		int currentTime = 0;
		while(notAllDone(pa)) {
			System.out.println("Current time is"+currentTime);
			//adding process to queue at currentTime...
			for(Process p:pa) {
				if(p.arrivedAt(currentTime) && !p.completed) {
					Q.add(p);
				}				
			}
			
			for(Process q:Q) {
				q.printInfo();
			}
			
			//processing first element in ps, ie element of highest priority
			if(!Q.isEmpty()) {
				Q.peek().processing();
				if(Q.peek().done(currentTime)) {	//if process is done after processing
					Q.remove();
				}
			}	
			currentTime++;
		}
		printPA(pa);
	}	
}