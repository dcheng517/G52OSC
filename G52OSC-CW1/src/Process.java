import java.util.*;

public class Process{
	public int AT;		//arrival time
	public int BT;		//starting burst time
	public int remBT;	//remaining burst time
	public String name;	//name of process
	private static int i=1;		//index of processes
	public boolean completed = false;
	public int startTime;	//time process starts
	public int endTime;	//time process ends
	public int tempProcessingTime = 1;
	
	//constructor
	//sets arrival time, burst time, remaining burst time, name, and start time of process
	public Process(int AT, int BT) {
		
		this.AT = AT;
		this.BT = BT;
		remBT = BT;
		name = "P"+i;
		startTime = AT;
		i++;
	}
	
	//debugging tool
	//prints information of process
	public void printInfo() {
		System.out.print(name+":");
		System.out.println("AT:"+AT+"|BT:"+BT+"|rem"+"BT:"+remBT);
		System.out.println("completed: "+completed);
		System.out.println("startTime: "+startTime+" endtime: "+endTime);
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
	public void processing(int currentTime) {		
		remBT--;		//remaining burst time reduced
		tempProcessingTime++;
	}
	
	//checks for completion status of process
	public boolean done(int ct) {
		if(remBT==0) {
			getTat();
			getWt();
			completed = true;
			endTime = ct;
			return true;
		}		
		return false;
	}
}