import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javafx.scene.control.TextField;

public class Algorithm {
		private int AT, BT, P;
		private static float avewt, avetat;
		private Process newProcess;
		private ArrayList<Process> pa; 
		
		public Algorithm(int algorithm, TextField[] ATs, TextField[] BTs, TextField[] Ps) {
			pa = new ArrayList<Process>();
			System.out.println("ATs length is "+ATs.length);
			if(algorithm!=2) {
				for(int i=0; i<ATs.length; i++) {					
					if(!ATs[i].getText().isEmpty() && !BTs[i].getText().isEmpty()) {
						this.AT = Integer.parseInt(ATs[i].getText());
						this.BT = Integer.parseInt(BTs[i].getText());
						
						newProcess = new Process(AT, BT);
						pa.add(newProcess); 
					}
				}
			}else {
				for(int i=0; i<ATs.length; i++) {
					
					if(!ATs[i].getText().isEmpty() && !BTs[i].getText().isEmpty() && !Ps[i].getText().isEmpty()) {
							
						this.AT = Integer.parseInt(ATs[i].getText());
						this.BT = Integer.parseInt(BTs[i].getText());
						this.P = Integer.parseInt(Ps[i].getText());	
						
						newProcess = new Process(AT, BT, P);
						pa.add(newProcess); 
					}
				}
			}
			
			switch(algorithm) {
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
						PrioSched priosched = new PrioSched(pa);
						avewt = priosched.getAvewt(pa);
						avetat = priosched.getAvetat(pa);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case 3:
					try {
						RR rr = new RR(pa);
						avewt = rr.getAvewt(pa);
						avetat = rr.getAvetat(pa);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case 4:
					try {
						SJF sjf = new SJF(pa);
						avewt = sjf.getAvewt(pa);
						avetat = sjf.getAvetat(pa);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
			}
		}
		
		//returns average waiting time
		public static float getAvewt() {
			System.out.println("Going to display avewt onto GUI");
			return avewt;
		}
		
		//returns average turn around time
		public static float getAvetat() {
			System.out.println("Going to display avetat onto GUI");
			return avetat;
		}
}