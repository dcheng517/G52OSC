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
		//System.out.println("startTime: "+startTime+"endtime: "+endTime);
	}
	
	public int getTat() {
		return endTime - startTime + 1;
	}
	
	public int getWt() {
		return getTat() - BT;
		
	}

	public boolean happenedAt(int ct) {
		if(AT==ct) {
			return true;
		}
		return false;
	}
	
	
	public void processing(int currentTime) {
		System.out.println("*"+name+" entering processing...");
		
		remBT--;		//remaining burst time reduced
		tempProcessingTime++;
		System.out.println("New processing time for "+name+" is "+tempProcessingTime);
	}
	
	public boolean completelyFinishProcessing(int ct) {
		if(remBT==0) {
			System.out.println(name+" completely finished processing");
			getTat();
			getWt();
			completed = true;
			endTime = ct;
			return true;
		}		
		return false;
	}
}










