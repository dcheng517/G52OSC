import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Request {
	private ArrayList<Process> pa = new ArrayList<Process>();
	private int AT;	//arrival time
	private int BT;	//burst time
	private int P;	//priority
	private int TQ;	//time quantum
	private int choice;
	private boolean error;
	private int n;
	SchedulingAlgorithms sa;
	
	
	InputStreamReader isr = new InputStreamReader(System.in); 
	BufferedReader br = new BufferedReader(isr);
	
	public Request(int c) throws Exception {
		this.choice = c;
	
		error = true;
		while(error) {
			try {
				System.out.print("Enter number of processes: ");
				n = Integer.parseInt(br.readLine()); 
				if(n>0) {
					error = false;
				}else {
					throw new Exception();
				}
			}catch(Exception e) {
				System.out.println("Invalid input");
			}
		}	
		
		System.out.println("Enter information for each processes");
			
		switch(choice) {
			//prompt for priority
			case 3:	
				for(int i=0; i<n; i++) {
					//create new process
					System.out.format("Process"+"[%d]:\n", i+1);
						try {
							System.out.print(" Arrival time: ");
								AT = Integer.parseInt(br.readLine());
								if(AT<0) {throw new Exception();}
							System.out.print(" Burst time: ");
								BT = Integer.parseInt(br.readLine()); // char to int
								if(BT<0) {throw new Exception();}
							System.out.print(" Priority ");
								P = Integer.parseInt(br.readLine()); // char to int	
								if(P<0) {throw new Exception();}
						 		
							Process newProcess = new Process(i, AT, BT, P);
							pa.add(newProcess);
						}catch (Exception e) {
							System.out.println("Invalid input");
						}
				}
				break;
			
			//prompt for timeQuantum
			case 4:
				for(int i=0; i<n; i++) {
					//create new process
					System.out.format("Process"+"[%d]:\n", i+1);
					try {
						System.out.print(" Arrival time: ");
							AT = Integer.parseInt(br.readLine());
							if(AT<0) {throw new Exception();}
						System.out.print(" Burst time: ");
							BT = Integer.parseInt(br.readLine()); // char to int
							if(BT<0) {throw new Exception();}
						Process newProcess = new Process(i, AT, BT);
						pa.add(newProcess);
					} catch (Exception e) {
						System.out.println("Invalid input");
					}
				}
				try {
					System.out.print(" Time Quantum: ");
					TQ = Integer.parseInt(br.readLine());
					if(TQ<0) {throw new Exception();}
				} catch (Exception e) {
					System.out.println("Invalid input");
				}
				break;
			
			//gets process without priority nor time quantum
			default:
				for(int i=0; i<n; i++) {
					int AT;
					int BT;
					System.out.println("i is "+i);
					//create new process
					System.out.format("Process"+"[%d]:\n", i+1);
					try {
						System.out.print(" Arrival time: ");
							AT = Integer.parseInt(br.readLine()); // char to int
							if(AT<0) {throw new Exception();}
						System.out.print(" Burst time: ");
							BT = Integer.parseInt(br.readLine()); // char to int
							if(BT<0) {throw new Exception();}
						Process newProcess = new Process(i, AT, BT);
						pa.add(newProcess);
					} catch (Exception e) {
						System.out.println("Invalid input");
					}
				}
				break;
		}
		
		//intialize scheduling algorithm
		switch(choice) {
			case 1:
				sa = new FCFS(pa);
					break;
				case 2:
					sa = new SJF(pa);
					break;
				case 3:
					sa = new PrioSched(pa);
					break;
				case 4:
					sa = new RR(pa, TQ);
					break;
				default:
				break;
		}
	}
}
