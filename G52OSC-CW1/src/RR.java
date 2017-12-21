import java.util.*;

public class RR extends SchedulingAlgorithms{
	static LinkedList<Process> ps = new LinkedList<>();		//linkedlist to process processes
	static int TQ;	//time quantum
	ArrayList<Process> pa;
	public RR(ArrayList<Process> a, int tq) throws Exception{
		this.pa = a;
		TQ = tq;
		
		long cputimeBefore = System.currentTimeMillis();
		roundRobin(a, TQ);
		long cputimeAfter = System.currentTimeMillis();
		printResult(pa);
		printCPUTime(cputimeBefore, cputimeAfter);
		printAveTat(pa);
		printAveWt(pa);
		printCPUInfo();
	}

	
	//round robin algorithm
	//implements a per unit time cycle concept
	//e.g., if TQ = 3, then TQ = 3 unit time cycle
	public static void roundRobin(ArrayList<Process> a, int tq) {
		Queue<Process> pq = new LinkedList<>();		//queue for managing processes before processing
		int currentTime=0;							//current time (increments one unit time at a time)
		Process outStandingProcess = null;
	
		while(notAllDone(a)) {
		
			//adding process to queue...
			for(Process p:a) { 
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