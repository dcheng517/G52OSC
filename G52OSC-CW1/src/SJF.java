import java.util.*;

public class SJF extends SchedulingAlgorithms{
	private ArrayList<Process> pa;
	
	public SJF(ArrayList<Process> a) throws Exception{
		this.pa = a;
		
		long cputimeBefore = System.currentTimeMillis();
		shortestJobFirst(pa);
		long cputimeAfter = System.currentTimeMillis();
		printPA(pa);
		printResult(pa);
		printCPUTime(cputimeBefore, cputimeAfter);		
		printAvetat(pa);
		printAvewt(pa);
		printCPUInfo();
	}
	
	
	//shortest job first algorithm
	public static void shortestJobFirst(ArrayList<Process> a) {
		ArrayList<Process> pa = new ArrayList<>();	//sortedset for managing processes before processing
		
		int currentTime = 0;						//current time (increments one unit time at a time)
		while(notAllDone(a)) {		
			//adding process to sortedset...
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
	
	//Prints queue
	public static void printPS(ArrayList<Process> a, int ct) {
		Iterator<Process> pai = a.iterator();
		System.out.print(ct+":\t");
		if(pai.hasNext()) {
			while(pai.hasNext()) {
				System.out.print(pai.next().name+"|");
			}
			System.out.println("");
		}else {
			System.out.println("Queue is empty");
		}
	}
	
}