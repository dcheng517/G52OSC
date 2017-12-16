import java.util.*;

public class Process{
	public int AT;		//arrival time
	public int BT;		//starting burst time
	public int remBT;	//remaining burst time
	public int tat=0;	//turn around time
	public int wt=0;	//waiting time
	public String name;	//name of process
	private static int i=1;		//index of processes
	public boolean completed = false;
	public int timeTaken;
	public int startTime;
	public int finishTime;
	public int processingTime = 1;
	Vector<Integer> workingTimeStamp; //keeps track of all working currentTime
	
	public Process(int AT, int BT) {
		
		this.AT = AT;
		this.BT = BT;
		remBT = BT;
		name = "P"+i;
		
		workingTimeStamp = new Vector<>();
		startTime = AT;
		workingTimeStamp.add(startTime);
		
		i++;
	}
	
	public void printInfo() {
		System.out.print(name+":");
		System.out.println("AT:"+AT+"|BT:"+BT+"|rem"+"BT:"+remBT);
		//System.out.println("completed: "+completed);
		//System.out.println("workingTimeStamp: "+workingTimeStamp.toString());
	}
	
	public void calculateTat() {
		tat = workingTimeStamp.lastElement() - workingTimeStamp.firstElement();
		System.out.println("tat for "+name+" is: "+tat);
	}
	
	public void calculateWt() {
		Vector<Integer> copyworkingct = workingTimeStamp;
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

	public boolean happenedAt(int ct) {
		if(AT==ct) {
			return true;
		}
		return false;
	}
	
	//add process' starting time to workingct vector 
	public void recordStartTime(int wt) {
		workingTimeStamp.add((Integer)wt);
		System.out.println("Current working time is: "+wt);
	}

	//add process' finishing time to workingct vector 
	public void recordFinishTime(int wt) {
		System.out.println(name+"finished processing");
		workingTimeStamp.add((Integer)wt);
	}
	
	public void processing(int currentTime) {
		System.out.println("*"+name+" entering processing...");
		
		remBT--;		//remaining burst time reduced
		timeTaken++;	//time take to process this process increased
		//processingTime++;
		System.out.println("New processing time for "+name+" is "+processingTime);
		
		
/*
		System.out.println(name+" time Taken is "+timeTaken);
		startTime = currentTime;
		System.out.println(name+" start time is "+startTime);
		finishTime = startTime + timeTaken;
		System.out.println(name+" finish time is "+finishTime);
		workingTimeStamp.add(startTime);
		workingTimeStamp.add(finishTime);
*/			
	}
	
	public boolean completelyFinishProcessing() {
		if(remBT==0) {
			System.out.println(name+" completely finished processing");
			//calculateTat();
			//calculateWt();
			completed = true;
			return true;
		}		
		return false;
	}
}










