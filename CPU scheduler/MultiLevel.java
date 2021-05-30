import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class MultiLevel {

    Process currentProcess;
    private ArrayList<Process> processes  = new ArrayList<Process>();
    Queue <Process> higherPriority = new LinkedList<>();
    Queue<Process> lowerPriority = new LinkedList<>();
    int contextSwitchTime;
    int priorityTime;
    int RRtime;
    int clk;
    int RRwaitingTime=0;
    int PrioritywaitingTime=0;
    int RRturnaroundTime=0;
    int PriorityturnaroundTime=0;
    RoundRobin RR = null;
    preemptivePriority priority = null;

    public MultiLevel(ArrayList<Process> Processes,int quantumTime)
    {
        this.processes = Processes;
        this.RRtime = quantumTime;
    }

    public void scheduling()
    {
        Scanner input = new Scanner(System.in);
        for( Process p : processes)
        {
            if(p.getQueueNumber() == 1)
            {
                higherPriority.add(p);
            }
            else if(p.getQueueNumber() == 2)
            {
                lowerPriority.add(p);
                System.out.println("Please enter context switching time ");
                contextSwitchTime = input.nextInt();
            }
        }

        ArrayList <Process> Q1 = new ArrayList<>(higherPriority);
        RR = new RoundRobin(RRtime , contextSwitchTime , Q1);
        RR.processesExecution();

        while(!higherPriority.isEmpty())
        {
            currentProcess = higherPriority.peek();

            if(currentProcess.getRemainingTime() == 0)
                higherPriority.remove();
        }

        clk=RR.getClk();
        priority= new preemptivePriority();
        ArrayList <Process> Q2 = new ArrayList<>(lowerPriority);
        for(int i=0;i<Q2.size();i++)
        {
        	Q2.get(i).setWaitingTime(clk);
        }
        priority.run(Q2, contextSwitchTime);

        while(!lowerPriority.isEmpty())
        {
            currentProcess = lowerPriority.peek();
            
            if(currentProcess.getRemainingTime() == 0 )
                lowerPriority.remove();
        }

        int sumWaiting=0;
        int sumTurnaround=0;


        ArrayList<Process> RRprocesses = new ArrayList<>(RR.getProcesses());

        for (int i = 0; i <RRprocesses.size();i++) {
            System.out.println("Process "+ RRprocesses.get(i).getProcessName());
            System.out.println("RR Waiting time: "+ RRprocesses.get(i).getWaitingTime());
            System.out.println("RR Turnaround time: "+ RRprocesses.get(i).getTurnAroundTime());
            sumWaiting += RRprocesses.get(i).getWaitingTime();
            sumTurnaround += RRprocesses.get(i).getTurnAroundTime();
        }

        ArrayList<Process> pProcesses = new ArrayList<>(priority.getPriorityProcesses());

        for (int i = 0; i < pProcesses.size();i++) {
            System.out.println("Process "+ pProcesses.get(i).getProcessName());
            System.out.println("Priority Waiting time: "+ pProcesses.get(i).getWaitingTime());
            System.out.println("Priority Turnaround time: "+ pProcesses.get(i).getTurnAroundTime());
            sumWaiting += pProcesses.get(i).getWaitingTime();
            sumTurnaround += RRprocesses.get(i).getTurnAroundTime();
        }

        System.out.println("Average waiting time: "+sumWaiting/processes.size());
        System.out.println("Average turnaorund time: "+ sumTurnaround/processes.size());



    }
}
