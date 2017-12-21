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
		printAveTat(pa);
		printAveWt(pa);
		printCPUInfo();
	}
	
	
	//priorityScheduling algorithm
	public static void priorityScheduling(ArrayList<Process> a) {
		
		//sortedset for managing processes before processing
		ArrayList<Process> pa = new ArrayList<>();	//sortedset for managing processes before processing
		
		//current time (increments one unit time at a time)
		int currentTime = 0;					
		
		while(notAllDone(a)) {
			//adding process to array
			for(Process p:a) {
				if(p.arrivedAt(currentTime) && !p.completed) {
					pa.add(p);
					Collections.sort(pa);
				}
			}
			//processing first element in ps, ie element of highest priority
			if(!pa.isEmpty()) {
				pa.get(0).processing();
				if(pa.get(0).done(currentTime)) {
					pa.remove(pa.get(0));
				}
			}	
			currentTime++;
		}	
	}	
}