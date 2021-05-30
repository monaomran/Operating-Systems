
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collections;

public class RoundRobin {

    private int quantumTime;
    private int contextSwitch;
    private ArrayList<Process> processes  = new ArrayList<Process>();
    private ArrayList<Process> Order  = new ArrayList<Process>();
    Queue<Process> readyQueue = new LinkedList<>();
    private int clk;
    private int averageTurnAround = 0;
    private int averageWaitingTime= 0;
    private int tt = 0;
    private int wt= 0;



    public RoundRobin ( int _quantumTime ,int _contextSwitch ,  ArrayList<Process> _processes  )
    {
        this.quantumTime = _quantumTime;
        this.contextSwitch = _contextSwitch;
        this.processes = _processes;
        clk = 0;
        Collections.sort(processes);
    }

    public void processesExecution()
    {
        Process currentProcess = new Process();
        for( Process p : processes)
        {
            if(p.getArrivalTime() <= clk  )
            {
                readyQueue.add(p);
                p.setActive(true);
            }
        }

        while (!readyQueue.isEmpty())
        {
            currentProcess = readyQueue.peek();
            if(currentProcess.getRemainingTime() == currentProcess.getBurstTime())
            {
                currentProcess.setStartExecutionTime(clk);
            }

            if(currentProcess.getRemainingTime() <= quantumTime)
            {
                clk+=currentProcess.getRemainingTime();
                currentProcess.setRemainingTime(0);
                currentProcess.setEndTime(clk);
                currentProcess.setActive(false);
            }
            else if (currentProcess.getRemainingTime() > quantumTime)
            {
                clk+=quantumTime;
                currentProcess.setRemainingTime( currentProcess.getRemainingTime() - quantumTime);  // rem-=q;
                currentProcess.setActive(true);
            }
            Order.add(currentProcess);
            clk+=contextSwitch;
            for( Process p : processes)
            {
                if(p.getArrivalTime() <= clk && (p.getRemainingTime()!=0) && ( !p.isActive()))
                {
                    readyQueue.add(p);
                    p.setActive(true);
                }
            }
            if(currentProcess.getRemainingTime()!=0)
            {
                readyQueue.add(currentProcess);
            }

            readyQueue.remove();
        }
        
        

        // calculate arrival and turn around time for each process
        for( Process p : processes)
        {
            tt= p.getEndTime()-p.getArrivalTime();	// arrival time
            p.setTurnAroundTime(tt);
            averageTurnAround+=tt;

            wt = p.getTurnAroundTime() - p.getBurstTime(); // waiting time
            p.setWaitingTime(wt);
            averageWaitingTime+=wt;
        }

        //print execution order
        for( Process p : Order)
        {
            System.out.print(p.getProcessName() + "|");
        }
        System.out.println();

        // print each process turnaround and waiting times
        for( Process p : processes)
        {
            System.out.println(p.printRRData());
        }
        averageTurnAround  /= (processes.size());
        averageWaitingTime /= (processes.size());

        // print average waiting and turn around time
        System.out.println("Average waiting time equals " + averageWaitingTime);
        System.out.println("Average turn around time  equals " + averageTurnAround);
    }

    public int getAverageTurnAround() {
        return averageTurnAround;
    }
    public void setAverageTurnAround(int averageTurnAround) {
        this.averageTurnAround = averageTurnAround;
    }
    public int getAverageWaitingTime() {
        return averageWaitingTime;
    }
    public void setAverageWaitingTime(int averageWaitingTime) {
        this.averageWaitingTime = averageWaitingTime;
    }

    public ArrayList<Process> getProcesses() {
        return processes;
    }
    public int getClk()
    {
    	return clk;
    }
}
