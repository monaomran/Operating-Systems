
public class Process implements Comparable<Process>
{
    private String processName;
    private int arrivalTime;
    private int queueNumber;
    private int burstTime;
    private int endTime;
    private int remainingTime;
    private int priorityTime;

    private int turnAroundTime=0;
    private int waitingTime=0;
    private boolean isActive;
    private int startExecutionTime;


    public Process() {
        setActive(false);
        setRemainingTime(burstTime);
    }

    public Process(String processName, int arrivalTime, int queueNumber, int burstTime, int priority) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.queueNumber = queueNumber;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priorityTime= priority;
    }
    public String printRRData()
    {
        return "Process [processName=" + processName +  ", turnAroundTime=" + turnAroundTime + ", waitingTime=" + waitingTime +  "]";
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(int queueNumber) {
        this.queueNumber = queueNumber;
    }

    @Override
    public String toString() {
        return "Process [processName=" + processName + ", arrivalTime=" + arrivalTime + ", queueNumber=" + queueNumber
                + ", burstTime=" + burstTime + ", endTime=" + endTime + ", remainingTime=" + remainingTime
                + ", turnAroundTime=" + turnAroundTime + ", waitingTime=" + waitingTime + ", isActive=" + isActive
                + ", startExecutionTime=" + startExecutionTime + "]";
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getStartExecutionTime() {
        return startExecutionTime;
    }

    public void setStartExecutionTime(int startExecutionTime) {
        this.startExecutionTime = startExecutionTime;
    }
    @Override
    public int compareTo(Process process) {
        return Integer.compare(this.arrivalTime, process.arrivalTime);
    }

    public int getPriorityTime() {
        return priorityTime;
    }

    public void setPriorityTime(int priorityTime) {
        this.priorityTime = priorityTime;
    }

}
