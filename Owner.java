import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.time.*;

public class Owner extends JFrame {
    public int ownerID;

    public void writeToText(int id, String os, double cpupower, int VehicleTime) throws IOException {
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
        bufferedWriter.write("OS: " + os + "CPU Power: " + cpupower + "GHz");
        bufferedWriter.newLine();
        bufferedWriter.write("Available for " + VehicleTime + " hours");

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}