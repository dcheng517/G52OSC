import java.util.*;

public class Process implements Comparable<Process> {
	public int AT;		//arrival time
	public int BT;		//starting burst time
	public int remBT;	//remaining burst time
	public int tat=0;	//turn around time
	public int wt=0;	//waiting time
	public String name;	//name of process
	private static int i=1;		//index of processes
	public boolean completed = false;
	Vector<Integer> workingct; //keeps track of all working currentTime
	
	public Process(int AT, int BT) {
		
		this.AT = AT;
		this.BT = BT;
		remBT = BT;
		name = "P"+i;
		
		workingct = new Vector<>();
		workingct.add(AT);
		
		i++;
	}
	
	public void ProcessInfo() {
		System.out.print(name+":");
		System.out.println("AT:"+AT+"|BT:"+BT+"|rem"+"BT:"+remBT);
		System.out.println("completed: "+completed);
		System.out.println("Workingct: "+workingct.toString());
	}
	
	public void calculateTat() {
		tat = workingct.lastElement() - workingct.firstElement();
		System.out.println("tat for "+name+" is: "+tat);
	}
	
	public void calculateWt() {
		Vector<Integer> copyworkingct = workingct;
		int n = copyworkingct.size();
		
		System.out.println(name);
		
		if(n>1) {
			for(int t=0; t<n-1; t+=2) {
				int startingTime = copyworkingct.get(t);
				//System.out.println("startingtime is "+startingTime);
				int endingTime = copyworkingct.get(t+1);
				//System.out.println("endingTime is "+endingTime);
				int tempwt = endingTime - startingTime;
				//System.out.println("tempwt is "+tempwt);
				wt += tempwt;
			}
			System.out.println(name+" wt is "+wt);
		}
	}

	public boolean happenedWithin(int currentTime) {
		if(AT<=currentTime) {
			return true;
		}
		return false;
	}
	
	//add process' starting time to workingct vector 
	public void recordStartTime(int wt) {
		workingct.add((Integer)wt);
		System.out.println("Current working time is: "+wt);
	}

	//add process' finishing time to workingct vector 
	public void recordFinishTime(int wt) {
		System.out.println(name+"finished processing");
		workingct.add((Integer)wt);
	}
	
	public void processing(int wt) {
		System.out.println("Processing "+name+" ...");
		
		remBT -= RR.TQ;
		
		//process finished in time less than TQ for particular cycle
		if(remBT<0)
			remBT=0;
		ProcessInfo();
	}
	
	public boolean completelyFinishProcessing() {
		if(completed) {
			return true;
		}else if(remBT==0) {
			System.out.println(name+" completely finished processing");
			calculateTat();
			calculateWt();
			completed = true;
			return true;
		}		
		return false;
	}
	
	public int compareTo(Process p) {
		return int.compare(p.AT, this.AT);
	}
}










