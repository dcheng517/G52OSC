/** Job with shortest burst time is given priority
*/

import java.io.*;

public class PreSJF {

	public static void main(String args[]) throws Exception
	{

		int n; // number of process
		int x;
		int pos;
		float AvgWT = 0; // average wait time
		float AvgTAT = 0; // average turn around time

		InputStreamReader isr = new InputStreamReader(System.in); // bytes to char
		BufferedReader br = new BufferedReader(isr); 

		System.out.print("Enter number of processes: ");
		n = Integer.parseInt(br.readLine()); // char to int

		int[] p = new int[n]; // process number
		int[] bt = new int[n]; // burst time
		int[] wt = new int[n]; // wait time 
		int[] at = new int[n]; // arrival time
		int[] tat = new int[n]; // turn around time

		for(int i=0; i<n; i++)
		{
			p[i] = i+1;
			System.out.println("\nEnter Arrival Time for process " + p[i]);
			at[i] = Integer.parseInt(br.readLine());			
			System.out.println("Enter Burst Time for process " + p[i]);
			bt[i] = Integer.parseInt(br.readLine());
			
		}

		long cputimeBefore = System.currentTimeMillis();

		// sort burst time of process in ascending order using selection sort
		for(int i=0; i<n; i++)
		{
			pos = i;
			for(int j=i+1; j<n; j++)
			{
				if(bt[j] < bt[pos])
				{
					pos = j;
				}
			}

			x = bt[i];
			bt[i] = bt[pos];
			bt[pos] = x;

			x = p[i];
			p[i] = p[pos];
			p[pos] = x;
			
		}

		// calculating wait time
		System.out.println("\n");
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

		System.out.print(" ____________________________________________________________________");
		System.out.println("\n|Process\t|BURST-TIME\t|WAITING-TIME\t|TURN AROUND-TIME   |");
		for(int i=0; i<n; i++)
		{
			System.out.println("--------------------------------------------------------------------");
			System.out.println("|" + p[i] + "\t\t|" + bt[i] + "\t\t|" + wt[i] + "\t\t|" + tat[i] + "\t\t    |");
		}
		System.out.println(" ____________________________________________________________________");
		
		AvgWT = AvgWT/n;
		System.out.println("\nAverage waiting time = " + AvgWT + "\n");
		
		AvgTAT = AvgTAT/n;
		System.out.println("Average turn-around time = " + AvgTAT + "\n");


		long cputimeAfter = System.currentTimeMillis();		
		long cputimeDifference = cputimeAfter - cputimeBefore; 

		System.out.println("CPU Time: " + cputimeDifference);
	}
}