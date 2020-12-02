import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;


public class CloudController extends JFrame{
    public ArrayList<Job> jobs;
    private JButton JButtonAccept;
    private JButton JButtonReject;
    private JPanel CCPanel;
    public OwnerSubmit own;
    public ClientSubmit cli;

    static Connection connection = null;
    static String url = "jdbc:mysql://localhost:3306/VC3?useTimezone=true&serverTimezone=UTC";
    static String username = "root";
    static String password = "rootpass";

    public CloudController(ClientSubmit client) {
        super("VC Controller");
        this.setContentPane(this.CCPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        jobs = new ArrayList<Job>();
        cli=client;
        Rejected reject=new Rejected();
        Accepted accept=new Accepted();
        JButtonAccept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authJob(cli);
                accept.setVisible(true);
                dispose();
            }
        });
        JButtonReject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reject.setVisible(true);
                dispose();
            }
        });
    }
    public CloudController(OwnerSubmit owner) {
        super("VC Controller");
        this.setContentPane(this.CCPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        jobs = new ArrayList<Job>();
        own=owner;
        Accepted accept=new Accepted();
        Rejected reject=new Rejected();
        JButtonAccept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authVehicle(own);
                accept.setVisible(true);
                dispose();
            }
        });
        JButtonReject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reject.setVisible(true);
                dispose();
            }
        });
    }
    public CloudController() {
        jobs = new ArrayList<Job>();
        own=null;
        cli=null;
        JButtonAccept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JButtonReject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    double compTime = 0;

    public void readJobs(String filename)
            throws IOException {
        try (Scanner in = new Scanner(new File(filename))) {
            while (in.hasNext()) {
                int idnumber = in.nextInt();
                int duration = in.nextInt();
                Job j = new Job(idnumber, duration, "N/A");
                addJob(j);
            }
        }
    }
    public void getQueuedJob(int id){

    }
    public void addJob(Job j) {
        jobs.add(j);
    }

    public Job findJob(int idNumber) {
        for (Job j : jobs) {
            if (j.match(idNumber)) {
                return j;
            }
        }
        return null;
    }

    public double calculateCtime(double duration) {
        compTime = compTime + duration;
        return compTime;
    }

    /**
     * dont need now
     * <p>
     * <p>
     * public void printCompletionTimes(){
     * int compTime = 0;
     * System.out.println("ID = Job ID\nCT = Completion Time\n");
     * for (Job j : jobs)
     * {
     * System.out.print("ID " + j.ID + "\t");
     * }
     * System.out.println();
     * for (Job j : jobs)
     * {
     * System.out.print("CT: " + (j.duration + compTime) + "\t");
     * compTime+=j.duration;
     * }
     * }
     */

    public void authJob(ClientSubmit client) {
        cli=client;
        jobWriteToText(cli.queuedJob.ID, cli.queuedJob.duration, cli.queuedJob.dueDate, cli.queuedJob.compTime);
    }

    public void authVehicle(OwnerSubmit owner) {
        own=owner;
        vehicleWriteToText(own.queuedVehicle.ID, own.queuedVehicle.vehInfo, own.queuedVehicle.timeAvail);
    }


    public void jobWriteToText(int id, double duration, String duedate, double cTime) {
        try {
            LocalDateTime timestamp = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
            String formattedDate = timestamp.format(dateFormatter);
            FileWriter writer = new FileWriter("out.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.newLine();
            bufferedWriter.write("[" + formattedDate + "]");
            bufferedWriter.newLine();
            bufferedWriter.write("Client ID: " + id);
            bufferedWriter.newLine();
            bufferedWriter.write("Approximate Duration: " + duration + " minutes");
            bufferedWriter.newLine();
            bufferedWriter.write("Deadline Date: " + duedate);
            bufferedWriter.newLine();
            bufferedWriter.write("Completion Time of Jobs: " + cTime);
            bufferedWriter.newLine();

            bufferedWriter.close();


            connection = DriverManager.getConnection(url, username, password);

            String sql = "INSERT INTO Jobs" + "(JobID , Duration , Deadline)" + "VALUES ("+id+", "+duration+" , '"+duedate+"')";

            Statement statement = connection.createStatement();

            int row = statement.executeUpdate(sql);

            connection.close();
        }
        catch (SQLException e){
            e.getMessage();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void vehicleWriteToText(int id, String vehicleInfo, String vehicleTime) {
        try {
            LocalDateTime timestamp = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
            String formattedDate = timestamp.format(dateFormatter);
            FileWriter writer = new FileWriter("out.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.newLine();
            bufferedWriter.write("[" + formattedDate + "]");
            bufferedWriter.newLine();
            bufferedWriter.write("Owner ID: " + id);
            bufferedWriter.newLine();
            bufferedWriter.write("Vehicle Description: " + vehicleInfo);
            bufferedWriter.newLine();
            bufferedWriter.write("Available for " + vehicleTime + " hours");
            bufferedWriter.newLine();

            bufferedWriter.close();

            connection = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO Vehicles" + "(VehicleID , AvailableTime , VehicleInfo)" + "VALUES ("+id+", '"+vehicleTime+"' , '"+vehicleInfo+"')";
            Statement statement = connection.createStatement();
            int row = statement.executeUpdate(sql);
            connection.close();

        }catch (SQLException e){
            e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


