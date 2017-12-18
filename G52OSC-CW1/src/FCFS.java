import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;

import com.sun.management.OperatingSystemMXBean;

public class FCFS extends ProcessingAlgorithms{
										
	public FCFS(ArrayList<Process> a) throws Exception{
		pa = a;
		
		long cputimeBefore = System.currentTimeMillis();
		FirstComeFirstServe(pa);
		long cputimeAfter = System.currentTimeMillis();
		printCPUTime(cputimeBefore, cputimeAfter);
		printResult(pa);
		printCPUInfo();
	}
	
	public static void FirstComeFirstServe(ArrayList<Process> a) {
		
		System.out.println("Inside FCFS.java");
		printPA(pa);
		int currentTime = 0;
		boolean processDone;
			for(Process p:pa) {
				processDone = false;
				while(!processDone) {
					p.processing();
					if(p.done(currentTime)) processDone = true;
					currentTime+=1;
				}
			}
	}
	
}