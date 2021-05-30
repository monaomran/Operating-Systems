import java.util.ArrayList;
import java.util.Collections;

public class preemptivePriority {

	ArrayList<Process> finished= new ArrayList<Process>();
	public void run(ArrayList<Process> processes, int contextSwitching) {
		ArrayList<Process> waiting = new ArrayList<Process>(processes);
		ArrayList<Process> arrived= new ArrayList<Process>();

		Process current = new Process();
		Process previous = new Process();
		int clock =0;
		//assume we increase priority of waiting processes every 3 seconds to avoid starvation
		while(waiting.size()!=0 || arrived.size()!=0 ) {
			
			if (clock%3==0 &&clock !=0)
			{
				for(int i=0 ; i<arrived.size() ;i++)
					arrived.get(i).setPriorityTime(arrived.get(i).getPriorityTime()-1);
			}
			//seeing which processes have already arrived
			for(int i=0 ; i<waiting.size() ; i++) {
				if(waiting.get(i).getArrivalTime()<=clock)
					{
					arrived.add(waiting.get(i));
					waiting.remove(i);
					}
			}
			//getting the least priority 
			int leastPriorityIndex=0;
			for(int i=1 ; i<arrived.size() ; i++) {
				if(arrived.get(i).getPriorityTime()<arrived.get(leastPriorityIndex).getPriorityTime() )
					leastPriorityIndex=i;
				
			}
			
			current=arrived.get(leastPriorityIndex);
			System.out.print(current.getProcessName()+"  ");
			if(clock>0 &&current!=previous)
				clock+=(contextSwitching+1);
			else
				clock++;
			
			int remaining=current.getRemainingTime()-1;
			arrived.get(leastPriorityIndex).setRemainingTime(remaining);
			if(current.getRemainingTime()==0)
				{
				arrived.get(leastPriorityIndex).setEndTime(clock);
				finished.add(arrived.get(leastPriorityIndex));
				arrived.remove(leastPriorityIndex);
				
				}
			previous=current;
		}
		System.out.println("");
		calculateProcessTime(finished);
	}	
	
	public ArrayList<Process> getPriorityProcesses() {
		return finished;
	}
	
	
	void calculateProcessTime(ArrayList<Process> processes) {
			int sumWait=0;
			int sumturnaround=0;
		for(int i =0 ; i< processes.size() ; i++) {
			int wait =processes.get(i).getEndTime()-processes.get(i).getArrivalTime()-processes.get(i).getBurstTime();
			int turnaround=(processes.get(i).getEndTime() - processes.get(i).getArrivalTime());
			int wait2=processes.get(i).getWaitingTime() + wait;
			System.out.println("Process Name: "+processes.get(i).getProcessName());
			System.out.println("Waiting time: "+wait2);
			System.out.println("Turnaround time: "+ turnaround);
			System.out.println("____________________________\n");
			processes.get(i).setWaitingTime(wait2);
			processes.get(i).setTurnAroundTime(turnaround);
			sumWait+=wait;
			sumturnaround+=turnaround;
			
		}
		System.out.println("Average waiting time: "+sumWait/processes.size());
		System.out.println("Average turnaorund time: "+ sumturnaround/processes.size());
	}
	
}
