import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javafx.scene.control.TextField;

public class Request{
		private static int AT, BT, P, TQ;
		private static float avewt, avetat;
		private Process newProcess;
		private static ArrayList<Process> pa = new ArrayList<Process>();
		
		//constructor 1, for FCFS and SJF,
		public Request(int choice, TextField[] ATs, TextField[] BTs) throws IndexOutOfBoundsException{
			
			for(int i=0; i<ATs.length; i++) {					
				if(!ATs[i].getText().isEmpty() && !BTs[i].getText().isEmpty()) {
					System.out.println("ATs["+i+"].getText() is "+ATs[i].getText());
					System.out.println("BTs["+i+"].getText() is "+BTs[i].getText());
					this.AT = Integer.parseInt(ATs[i].getText());
					this.BT = Integer.parseInt(BTs[i].getText());
					
					newProcess = new Process(AT, BT);
					pa.add(newProcess); 
				}
			}
			runAlgorithm(choice);
			pa.clear();
		}
		
		//constructor 2, for PrioSched
		public Request(TextField[] ATs, TextField[] BTs, TextField[] Ps) throws IndexOutOfBoundsException{
		
			for(int i=0; i<ATs.length; i++) {
				if(!ATs[i].getText().isEmpty() && !BTs[i].getText().isEmpty() && !Ps[i].getText().isEmpty()) {
					this.AT = Integer.parseInt(ATs[i].getText());
					this.BT = Integer.parseInt(BTs[i].getText());
					this.P = Integer.parseInt(Ps[i].getText());	
					
					newProcess = new Process(AT, BT, P);
					pa.add(newProcess); 
				}
			}
			runAlgorithm(3);
			pa.clear();
		}	
		
		//constructor 3, for RR
		public Request(TextField[] ATs, TextField[] BTs, String tq) throws IndexOutOfBoundsException{
			TQ = Integer.parseInt(tq);
			for(int i=0; i<ATs.length; i++) {
				if(!ATs[i].getText().isEmpty() && !BTs[i].getText().isEmpty()) {
					this.AT = Integer.parseInt(ATs[i].getText());
					this.BT = Integer.parseInt(BTs[i].getText());
					
					newProcess = new Process(AT, BT, P);
					pa.add(newProcess); 
				}
			}
			runAlgorithm(4);
			pa.clear();
		}	
		
		//initiating algorithm
		public static void runAlgorithm(int choice) {
			
				switch(choice) {
					case 1:
						try {
							FCFS fcfs = new FCFS(pa);
							avewt = fcfs.getAvewt(pa);
							avetat = fcfs.getAvetat(pa);
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case 2:
						try {
							SJF sjf = new SJF(pa);
							avewt = sjf.getAvewt(pa);
							avetat = sjf.getAvetat(pa);
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
						
					case 3:
						try {
							PrioSched priosched = new PrioSched(pa);
							avewt = priosched.getAvewt(pa);
							avetat = priosched.getAvetat(pa);
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case 4:
						try {
							RR rr = new RR(pa, TQ);
							avewt = rr.getAvewt(pa);
							avetat = rr.getAvetat(pa);
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
				}
		}
		
		//returns average waiting time
		public float getAvewt() {
			return avewt;
		}
		
		//returns average turn around time
		public float getAvetat() {
			return avetat;
		}
}