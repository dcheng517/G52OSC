import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FCFS extends SchedulingAlgorithms{
	private ArrayList<Process> pa;
	public FCFS(ArrayList<Process> a) throws Exception{
		this.pa = a;
		
		long cputimeBefore = System.currentTimeMillis();
		FirstComeFirstServe(pa);
		long cputimeAfter = System.currentTimeMillis();
		printResult(pa);
		printCPUTime(cputimeBefore, cputimeAfter);		
		printAvetat(pa);
		printAvewt(pa);
		printCPUInfo();
	}
	
	public static void FirstComeFirstServe(ArrayList<Process> a) {
		Queue<Process> Q = new LinkedList<Process>();
		
		int currentTime = 0;
		while(notAllDone(a)) {
			System.out.println("Current time is"+currentTime);
			
			//adding process to queue at currentTime...
			for(Process p:a) {
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
		printPA(a);
	}	
}