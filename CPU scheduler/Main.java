
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numOfProcesses;
        int arrivalTime;
        int Qnumber;
        String nameOfProcess;
        int contextSwitchTime;
        int RRtime;
        int priorityTime;

        ArrayList<Process> process = new ArrayList<Process>();
        Scanner input = new Scanner(System.in);

        System.out.println("please choose the algorithm you want to use" );
        System.out.println("1-SJF ");
        System.out.println("2-Round Robin");
        System.out.println("3-Preemptive Priority");
        System.out.println("4-MultiLevel ");
        int choice = input.nextInt();

        if(choice == 1)
        {
            System.out.println("Please enter number of processes ");
            numOfProcesses = input.nextInt();

            for (int i = 0; i < numOfProcesses; i++) {
                System.out.println("Please enter name of process " + (i+1));
                nameOfProcess = input.next();

                System.out.println("Please enter arrival time of process " + nameOfProcess);
                arrivalTime = input.nextInt();

                System.out.println("Please enter burst time of process " + nameOfProcess);
                int burstTime = input.nextInt();

                System.out.println("Please enter Queue number that the process will enter ");
                Qnumber =  input.nextInt();

                System.out.println("Please enter priority time ");
                priorityTime = input.nextInt();

                Process temp = new Process(nameOfProcess,arrivalTime,Qnumber,burstTime,priorityTime);
                process.add(temp);
            }

            System.out.println("Please enter context switching time ");
            contextSwitchTime = input.nextInt();
            
            SJF sjf = new SJF(process, contextSwitchTime);

        }

        else if (choice == 2)
        {
            System.out.println("Please enter number of processes ");
            numOfProcesses = input.nextInt();

            for (int i = 0; i < numOfProcesses; i++) {
                Process p = new Process();
                System.out.println("Please enter name of process " + (i+1));
                nameOfProcess = input.next();
                p.setProcessName(nameOfProcess);

                System.out.println("Please enter arrival time of process " + nameOfProcess);
                p.setArrivalTime(input.nextInt());

                System.out.println("Please enter burst time of process " + nameOfProcess);
                int t = input.nextInt();
                p.setBurstTime(t);
                p.setRemainingTime(t);

                process.add(p);

            }

            System.out.println("Please enter context switching time ");
            contextSwitchTime = input.nextInt();

            System.out.println("Please enter Round Robin time quantum ");
            RRtime = input.nextInt();

            RoundRobin RR = new RoundRobin(RRtime , contextSwitchTime , process);
            RR.processesExecution();


        }
        else if (choice == 3)
        {
            System.out.println("Please enter number of processes ");
            numOfProcesses = input.nextInt();

            for (int i = 0; i < numOfProcesses; i++) {
                System.out.println("Please enter name of process " + (i+1));
                nameOfProcess = input.next();

                System.out.println("Please enter arrival time of process " + nameOfProcess);
                arrivalTime = input.nextInt();

                System.out.println("Please enter burst time of process " + nameOfProcess);
                int burstTime = input.nextInt();

                System.out.println("Please enter Queue number that the process will enter ");
                Qnumber =  input.nextInt();

                System.out.println("Please enter priority time ");
                priorityTime = input.nextInt();

                Process temp = new Process(nameOfProcess,arrivalTime,Qnumber,burstTime,priorityTime);
                process.add(temp);
            }

            System.out.println("Please enter context switching time ");
            contextSwitchTime = input.nextInt();

            preemptivePriority priority= new preemptivePriority();
            priority.run(process, contextSwitchTime);
        }
        else if (choice == 4)
        {
            System.out.println("Please enter number of processes ");
            numOfProcesses = input.nextInt();

            for (int i = 0; i < numOfProcesses; i++) {
                Process p = new Process();
                System.out.println("Please enter name of process " + (i+1));
                nameOfProcess = input.next();
                p.setProcessName(nameOfProcess);

                System.out.println("Please enter arrival time of process " + nameOfProcess);
                p.setArrivalTime(input.nextInt());

                System.out.println("Please enter burst time of process " + nameOfProcess);
                int t = input.nextInt();
                p.setBurstTime(t);
                p.setRemainingTime(t);

                System.out.println("Please enter Queue number that the process will enter ");
                p.setQueueNumber(input.nextInt());

                process.add(p);
            }

            System.out.println("Please enter Round Robin time quantum ");
            RRtime = input.nextInt();

            MultiLevel ML = new MultiLevel(process,RRtime);
            ML.scheduling();
        }

    }

}