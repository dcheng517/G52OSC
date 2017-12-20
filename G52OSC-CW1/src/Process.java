import java.util.*;

public class Process implements Comparable<Process> {
	public String name;	//name of process
	
	private int AT;					//arrival time
	private int BT;					//starting burst time
	private int P;					//priority
	private int remBT;				//remaining burst time
	private int startTime;			//time process starts
	private int endTime;			//time process ends
	private static int i=1;			//index of processes
	private int option=0;
	public boolean completed;
	public int tempProcessingTime = 1;
	
	//constructor1
	//sets arrival time, burst time, remaining burst time, name, and start time of process
	public Process(int AT, int BT) {
		
		this.AT = AT;
		this.BT = BT;
		remBT = BT;
		name = "P"+i;
		startTime = AT;
		completed = false;
		
		option = 0;		//compareTo() compares with remaining burst time
		i++;
	}
	
	//constructor2
	//sets arrival time, burst time,, priority remaining burst time, name, and start time of process
	public Process(int AT, int BT, int P) {
		
		this.AT = AT;
		this.BT = BT;
		this.P = P;
		remBT = BT;
		name = "P"+i;
		startTime = AT;
		completed = false;
		option = 1;			//compareTo() compares with priority
		i++;
	}
	
	
	//debugging tool
	//prints information of process
	public void printInfo() {
		System.out.print(name+":");
		System.out.println("AT:"+AT+"|BT:"+BT+"|rem"+"BT:"+remBT);
		//System.out.println("completed: "+completed);
		System.out.println("startTime: "+startTime+" endtime: "+endTime);
	}
	
	public int getAT() {
		return AT;
	}

	public int getBT() {
		return BT;
	}
	
	public int getP() {
		return P;
	}
	
	public int getRemBT() {
		return remBT;
	}
	
	public int getOption() {
		return option;
	}
	
	//returns turn around time
	public int getTat() {
		return endTime - startTime + 1;
	}
	
	//returns waiting time
	public int getWt() {
		return getTat() - BT;
	}
	
	//checks if process' arrival time is at ct
	public boolean arrivedAt(int ct) {
		if(AT==ct) {
			return true;
		}
		return false;
	}
	
	//processing...
	public void processing() {	
		//System.out.println(name+" is processing...");
		remBT--;				//remaining burst time reduced
		tempProcessingTime++;	//temporary processing time (useful only for round robin algo
	}
	
	//checks for completion status of process
	public boolean done(int ct) {
		if(remBT==0) {
			System.out.println(name+" done");
			getTat();
			getWt();
			endTime = ct;
			completed = true;
			return true;
		}		
		return false;
	}
	
	@Override
	public int compareTo(Process p) {
		if(option==1) {
			int comparePriority = ((Process) p).getP();
			return this.getP() - comparePriority;
		}else {
			int compareRemBT = ((Process) p).getRemBT();
			return this.getRemBT() - compareRemBT;
		}
	}
}











