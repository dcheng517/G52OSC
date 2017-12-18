import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Algorithm {
	
	public Algorithm() {		
		int choice=0;
		
		while(choice!=-1) {
			InputStreamReader isr = new InputStreamReader(System.in); // bytes to char
			BufferedReader br = new BufferedReader(isr); 
			
			boolean error = true;
			ProcessingAlgorithms pa;
			while(error) {
				try {
					System.out.println("Pick your choice:");
					System.out.println("1. FCFS");
					System.out.println("2. SJF");
					System.out.println("3. Priority Scheduling");
					System.out.println("4. RR");
					System.out.print("Your choice (-1 to exit) :");
					choice = Integer.parseInt(br.readLine()); // char to int
					error = false;
				}catch(Exception e) {
					System.out.println("Input error");
				}
			}
			
			
			try {
				switch(choice) {
					case 1:
						pa = new FCFS();
						break;
					case 2:
						pa = new SJF();
						break;
					case 3:
						pa = new PrioSched();
						break;
					case 4:
						pa = new RR();
						break;
					case -1:
						choice = -1;
						break;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}