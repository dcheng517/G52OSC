import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javafx.scene.control.TextField;

public class Request{
		private static int TQ; 
		private static float avewt, avetat;
		private static long cpuTime;
		private Process newProcess;
		private static ArrayList<Process> pa = new ArrayList<Process>();
		
		//constructor 1, for FCFS and SJF,
		public Request(int choice, TextField[] ATs, TextField[] BTs) throws Exception{
			int AT=0, BT=0;
			String at, bt, p;
			try {
			
				for(int i=0; i<ATs.length; i++) {	
					at = ATs[i].getText();
					bt = BTs[i].getText();
					if(!endOfUserInput(at, bt)) {
						AT = Integer.parseInt(ATs[i].getText());
						BT = Integer.parseInt(BTs[i].getText());
						
						newProcess = new Process(AT, BT);
						pa.add(newProcess); 
					}
				}
				runAlgorithm(choice);
				pa.clear();
			}catch(Exception e) {
				e.printStackTrace();
				AlertBox.handle();
			}
			
		}
		
		//constructor 2, for PrioSched
		public Request(TextField[] ATs, TextField[] BTs, TextField[] Ps) throws Exception{
			int AT=0, BT=0, P=0;
			String at, bt, p;
			try {
				
				for(int i=0; i<ATs.length; i++) {		

					at = ATs[i].getText();
					bt = BTs[i].getText();
					p = Ps[i].getText();
					
					if(!endOfUserInput(at, bt, p)) {

						System.out.println("i: "+i);
						AT = Integer.parseInt(ATs[i].getText());
						System.out.println("AT is "+AT);
						BT = Integer.parseInt(BTs[i].getText());
						System.out.println("BT is "+BT);
						P = Integer.parseInt(Ps[i].getText());
						System.out.println("P is "+P);
						
						newProcess = new Process(AT, BT, P);
						pa.add(newProcess); 
					}
				}
				
				runAlgorithm(3);
				pa.clear();
			}catch(Exception e1) {
				e1.printStackTrace();
				AlertBox.handle();
			}
		}	
		
		//constructor 3, for RR
		public Request(TextField[] ATs, TextField[] BTs, String tq) throws Exception{
			int AT=0, BT=0;
			String at, bt, p;
			try {
				TQ = Integer.parseInt(tq);
				for(int i=0; i<ATs.length; i++) {	
					at = ATs[i].getText();
					bt = BTs[i].getText();
					if(!endOfUserInput(at, bt)) {
						AT = Integer.parseInt(ATs[i].getText());
						BT = Integer.parseInt(BTs[i].getText());
						
						newProcess = new Process(AT, BT);
						pa.add(newProcess); 
					}
				}
				runAlgorithm(4);
				pa.clear();
			}catch(Exception e) {
				e.printStackTrace();
				AlertBox.handle();
			}			
		}	
		
		public boolean endOfUserInput(String x, String y) {
			if(x.isEmpty() && y.isEmpty()) {
				return true;
			}
			return false;
		}
		
		public boolean endOfUserInput(String x, String y, String z) {
			if(x.isEmpty() && y.isEmpty() && z.isEmpty()) {
				return true;
			}
			return false;
		}
		
		//initializing algorithm
		public static void runAlgorithm(int choice) {
			
				switch(choice) {
					case 1:
						try {
							FCFS fcfs = new FCFS(pa);
							avewt = fcfs.getAvewt();
							avetat = fcfs.getAvetat();
							cpuTime = fcfs.getCPUTime();
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case 2:
						try {
							SJF sjf = new SJF(pa);
							avewt = sjf.getAvewt();
							avetat = sjf.getAvetat();
							cpuTime = sjf.getCPUTime();
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
						
					case 3:
						try {
							PrioSched priosched = new PrioSched(pa);
							avewt = priosched.getAvewt();
							avetat = priosched.getAvetat();
							cpuTime = priosched.getCPUTime();
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case 4:
						try {
							RR rr = new RR(pa, TQ);
							avewt = rr.getAvewt();
							avetat = rr.getAvetat();
							cpuTime = rr.getCPUTime();
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
		
		//returns cpu time
		public long getCPUTime() {
			return cpuTime;
		}
}