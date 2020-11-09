import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CloudController {
    public ArrayList<Job> jobs;

    public CloudController(){
        jobs = new ArrayList<Job>();
    }

    double compTime = 0;

    public void readJobs(String filename)
            throws IOException
    {
        try (Scanner in = new Scanner(new File(filename)))
        {
            while (in.hasNext())
            {
                int idnumber = in.nextInt();
                int duration = in.nextInt();
                Job j = new Job(idnumber, duration);
                addJob(j);
            }
        }
    }

    public void addJob(Job j)
    {
        jobs.add(j);
    }

    public Job findJob(int idNumber)
    {
        for (Job j : jobs)
        {
            if (j.match(idNumber))
            {
                return j;
            }
        }
        return null;
    }

    public double calculateCtime(double duration){
        compTime= compTime + duration;
        return compTime;
    }
    /**dont need now


    public void printCompletionTimes(){
        int compTime = 0;
        System.out.println("ID = Job ID\nCT = Completion Time\n");
        for (Job j : jobs)
        {
            System.out.print("ID " + j.ID + "\t");
        }
        System.out.println();
        for (Job j : jobs)
        {
            System.out.print("CT: " + (j.duration + compTime) + "\t");
            compTime+=j.duration;
        }
    }*/
}
