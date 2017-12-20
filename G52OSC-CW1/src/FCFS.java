import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;

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
		
		int currentTime = 0;
		boolean processDone;
			while(notAllDone(pa))
			for(Process p:pa) {
				processDone = false;
				printPA(pa);
				while(!processDone) {
					p.processing();
					System.out.println("ct is "+currentTime);
					processDone = p.done(currentTime);
					
					currentTime+=1;
				}
			}
	}
	
}