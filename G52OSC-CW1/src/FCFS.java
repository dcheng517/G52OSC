import java.io.*;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class FCFS extends ProcessingAlgorithms{
	
	static Process[] pa;									//array to store all processes
	public FCFS() throws Exception{
		pa = getProcesses();
		
		long cputimeBefore = System.currentTimeMillis();
		FirstComeFirstServe(pa);
		long cputimeAfter = System.currentTimeMillis();
		long cpuTimeDifference = cputimeAfter - cputimeBefore;

		System.out.println("CPU Time: " + cpuTimeDifference);

		printResult(pa);
		printAvetat(pa);
		printAvewt(pa);
		printCPUInfo();
	}
	
	public static void FirstComeFirstServe(Process[] pa) {
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