public class Job {
    public int ID;
    public int duration;

    public Job(int idNumber, int jobDuration){
        ID = idNumber;
        duration = jobDuration;
    }

    public boolean match(int idNumber)
    {
        return ID == idNumber;
    }
}
