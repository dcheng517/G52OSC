
public class Process {
	public int AT;	//arrival time
	public int BT;	//burst time
	public String name;
	static int i=1;
	
	public Process(int AT, int BT) {
		this.AT = AT;
		this.BT = BT;
		System.out.println("Creation of new node"+i);
		i++;
	}
	
	public Process(int num, int AT, int BT) {
		name = "P"+num;
		this.AT = AT;
		this.BT = BT;
		System.out.println("Creation of new node"+name);
		i++;
	}
	
	public void ProcessInfo() {
		System.out.print(name+":");
		System.out.print(AT);
		System.out.print(","+BT+"\n");
	}
	

	public boolean happenedWithin(int currentTime) {
		if(AT<=currentTime) {
			return true;
		}
		return false;
	}
	
	public void processing(int tq) {
		BT -= tq;
		if(BT<0) 
			BT=0;
	}
	
	public boolean finishProcessing() {
		if(BT==0) {
			return true;
		}
		return false;
	}
}


/*
P1:0,4
P2:1,5
P3:2,6
P4:4,1
P5:6,3
P6:7,2
*/
