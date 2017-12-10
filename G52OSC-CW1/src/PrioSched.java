/** Priority Scheduling
 * 	Sort out processes according to priority assigned,
 * 	lower number represents a higher priority,
 * 	apply FSFC algorithm
 */

import java.io.*;

public class PrioSched
{
	
	public static void main(String args[]) throws Exception
	{
		int n;
		int x = 0;
		float AvgWT = 0; // average wait time
		float AvgTAT = 0; // average turn around time

		InputStreamReader isr = new InputStreamReader(System.in); // bytes to char
		BufferedReader br = new BufferedReader(isr); 

		System.out.print("Enter number of processes: ");
		n = Integer.parseInt(br.readLine()); // char to int

		int[] wt = new int[n]; // wait time
		int[] bt = new int[n]; // burst time
		int[] tat = new int[n]; // turn around time 
		int[] p = new int[n]; // process number
		int[] pp = new int[n]; // process priority

		System.out.println("\nEnter Burst Time for each process");
		for(int i=0; i<n; i++)
		{
			System.out.print("Process["+(i+1)+"]: ");
			bt[i] = Integer.parseInt(br.readLine());
		}

		System.out.print("\nEnter Time Priority for each process\n");
		for(int i=0; i<n; i++)
		{
			System.out.println("Process["+(i+1)+"]: ");
			pp[i] = Integer.parseInt(br.readLine());
			p[i] = i+1;
		}
		
		long cputimeBefore = System.currentTimeMillis();

		// sorting priority in ascending order
		// lower number == higher priority
		for( int i = 0; i<n-1; i++)
		{
			for( int j = i+1; j<n; j++)
			{
				if( pp[i] > pp[j])
				{
					x = pp[j];
					pp[j] = pp[i];
					pp[i] = x;

					x = bt[j];
					bt[j] = bt[i];
					bt[i] = x;

					x = p[j];
					p[j] = p[i];
					p[i] = x;
				}
			}
		}

		// calculating wait time
		wt[0] = 0;
		for(int i=1; i<n; i++)
		{
			wt[i] = wt[i-1] + bt[i-1];
		}

		// calculating turn around time, avg wait time, avg turn around time
		for(int i=0; i<n; i++)
		{
			tat[i] = wt[i] + bt[i];
			AvgWT = AvgWT + wt[i];
			AvgTAT = AvgTAT + tat[i];
		}

		System.out.println("\nProcess\t\t|PRIORITY\t|BURST-TIME\t|WAITING-TIME\t|TURN AROUND-TIME");
		for(int i=0; i<n; i++)
		{
			System.out.println(p[i] + "\t\t|" + pp[i] + "\t\t|" + bt[i] + "\t\t|" + wt[i] + "\t\t|" + tat[i]);
		}
		
		AvgWT = AvgWT/n;
		System.out.println("\nAverage waiting time = " + AvgWT + "\n");
		
		AvgTAT = AvgTAT/n;
		System.out.println("Average turn-around time = " + AvgTAT + "\n");
		
		long cputimeAfter = System.currentTimeMillis();
		long cpuTimeDifference = cputimeAfter - cputimeBefore;

		System.out.println("CPU Time: " + cpuTimeDifference);
		
	}
}