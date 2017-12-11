# G52OSC

Assignment for G52OSC - Operating Systems and Concurrency

Task:
Implement a program in Java for the following process scheduling algorithms. 

(i)	First Come First Serve (FCFS) 
(ii)	Shortest Job First (SJF) Preemptive Version
(iii)	Round Robin 
(iv)	Priority, with higher priority jobs preempting a lower priority running job


After the implementation, design and perform the following experiments to test the effectiveness of your program. 

1)	Use the same workloads for all the scheduling algorithms, and compare average turnaround time, average waiting time and CPU usage. 

2)	Use the different workloads with a mix of CPU and I/O bursts for all the scheduling algorithms, and compare average turnaround time, average waiting time and CPU usage. 

Information about the processes that must be scheduled, including the number of processes, their start times, and burst durations should be given during the run time. i.e. A new job may arrive and every arriving job has an expected duration and a priority, which will need to be stored until it finishes. 

Your Report should include the following information:

1.	Which scheduling algorithm is more suitable for a given workload.
 
2.	What happens when a process returns from I/O, what happens when a new process is created and when/how often the scheduling takes place.

3.	The influence of  different time quantum in RR on the interactivity system.
