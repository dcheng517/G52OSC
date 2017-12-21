import java.util.*;

public class PrioSched extends SchedulingAlgorithms{
	private ArrayList<Process> pa;
	
	public PrioSched(ArrayList<Process> a) throws Exception{
		this.pa = a;
		
		long cputimeBefore = System.currentTimeMillis();
		priorityScheduling(pa);
		long cputimeAfter = System.currentTimeMillis();
		printResult(pa);
		printCPUTime(cputimeBefore, cputimeAfter);		
		printAvetat(pa);
		printAvewt(pa);
		printCPUInfo();
	}
	
	
	//priorityScheduling algorithm
	public static void priorityScheduling(ArrayList<Process> a) {
		
		//sortedset for managing processes before processing
		SortedSet<Process> ps = new TreeSet<>();	
		
		//current time (increments one unit time at a time)
		int currentTime = 0;					
		
		while(notAllDone(a)) {
			
			//adding process to sortedset...
			for(Process p:a) {
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
}