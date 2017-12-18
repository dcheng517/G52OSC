import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.*;
import com.sun.management.OperatingSystemMXBean;

public class RR extends ProcessingAlgorithms{
	
	static LinkedList<Process> ps = new LinkedList<>();		//linkedlist to process processes
	static int TQ;											//time quantum
	
	public RR(ArrayList<Process> a, int tq) throws Exception{
		TQ = tq;
		pa = a;
		try {
			long cputimeBefore = System.currentTimeMillis();
			roundRobin(pa, TQ);
			long cputimeAfter = System.currentTimeMillis();
			printCPUTime(cputimeBefore, cputimeAfter);
			printResult(pa);
			printCPUInfo();
		}catch(IndexOutOfBoundsException e) {
			AlertBox.handle();
		}
	}

	
	
	//implements a per unit time cycle concept
	//e.g., if TQ = 3, then TQ = 3 unit time cycle
	public static void roundRobin(ArrayList<Process> a, int TQ) {
		Queue<Process> pq = new LinkedList<>();			//queue for managing processes before processing
		int currentTime=0;								//current time (increments one unit time at a time)
		Process outStandingProcess = null;
	
		while(allNotDone(pa)) {
		
			//adding process to queue...
			for(Process p:pa) {
				//System.out.println("*This process is: "+p.name); 
				if(p.arrivedAt(currentTime) && !p.completed) {
					pq.add(p);	
				}
			}			
			if(outStandingProcess!=null) {
				pq.add(outStandingProcess);	
				outStandingProcess = null;
			}
			
			//processing process...
			if(pq.peek()!=null) {
				pq.peek().processing();					//processing head of queue
				if(pq.peek().done(currentTime)) {		//check for completion
					pq.remove();					
				}
			}		
			currentTime++;
			
			//set out standing process (if any)
			if(pq.peek()!=null) {	
				//end of TQ
				if(pq.peek().tempProcessingTime>TQ) {
					pq.peek().tempProcessingTime=1;		//reset tempProcessingTime
					if(!pq.peek().completed) {
						outStandingProcess = pq.peek();	//assign outstanding processes to next cycle
						pq.remove();
					}					
				}
			}
		}
	}
}