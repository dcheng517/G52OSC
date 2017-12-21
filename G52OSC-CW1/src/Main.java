import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Main {
	
	public static void main(String[] args) throws Exception{	
		final int EXIT = -1;
		final List<Integer> validChoices = Arrays.asList(-1, 1, 2, 3, 4);
		int choice=0;
		Request req;
		
		InputStreamReader isr = new InputStreamReader(System.in); 
		BufferedReader br = new BufferedReader(isr);
		
		while(choice!=EXIT) {
			//main menu
			boolean error = true;
			//user choice for algorithm to simulate
			System.out.println("************Scheduling Algorithm Simulator************");
			System.out.println("Pick your choice:");
			System.out.println("1. FCFS");
			System.out.println("2. SJF");
			System.out.println("3. Priority Scheduling");
			System.out.println("4. RR");
			System.out.println("******************************************************");
			while(error) {
				try {		
					System.out.print("Your choice (-1 to exit) :");
					choice = Integer.parseInt(br.readLine()); // char to int
					if(validChoices.contains(choice)) {
						error = false;
					}else {
						throw new Exception();
					}
				}catch(Exception e) {
					System.out.println("Invalid input");
				}
			}
			if(choice!=EXIT) {
				req = new Request(choice);
			}		
		}	
			System.out.println("\n********************End of Program********************");
	}
}

