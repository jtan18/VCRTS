public class Job {
    public int ID;
    public double duration;
    public String dueDate;
    public double compTime;

    public Job(int idNumber, double jobDuration, String due){
        ID = idNumber;
        duration = jobDuration;
        dueDate = due;
        compTime = 0.0;
    }

    public Job(int idNumber, double jobDuration, String due, double cTime){
        ID = idNumber;
        duration = jobDuration;
        dueDate = due;
        compTime = cTime;
    }

    public boolean match(int idNumber)
    {
        return ID == idNumber;
    }

    public void setCompTime(double compTime) {
        this.compTime = compTime;
    }
}
