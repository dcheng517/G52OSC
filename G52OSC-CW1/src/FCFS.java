import java.io.*;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class FCFS {
	
	public static void main(String args[]) throws Exception {

		float AvgWT = 0; // avg wait time
		float AvgTAT = 0; // avg turn around time
		int n;

		InputStreamReader isr = new InputStreamReader(System.in); // bytes to char
		BufferedReader br = new BufferedReader(isr); 
		
		System.out.print("Enter number of processes: ");
		n = Integer.parseInt(br.readLine()); // char to int
		
		int[] wt = new int[n]; // wait time
		int[] tat = new int[n]; // turn around time
		int[] bt = new int[n]; // burst time
		int[] p = new int[n]; // process number
		
		System.out.println("\nEnter Burst Time for each process");
		for(int i=0; i<n; i++)
		{
			System.out.print("Process["+(i+1)+"]: ");
			bt[i] = Integer.parseInt(br.readLine());
			p[i] = i+1;
		}
		
		long cputimeBefore = System.currentTimeMillis();

		wt[0] = 0;
		for(int i=1; i<n; i++)
		{
			wt[i] = wt[i-1] + bt[i-1];
		}
		
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
		long cpuTimeDifference = cputimeAfter - cputimeBefore;

		System.out.println("CPU Time: " + cpuTimeDifference);
		
		OperatingSystemMXBean bean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		System.out.println(bean.getProcessCpuLoad());
		System.out.println("\n" + bean.getProcessCpuTime());

	}
}