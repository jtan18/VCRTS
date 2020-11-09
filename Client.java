import java.time.format.DateTimeFormatter;
import java.time.*;
import java.util.*;
import java.io.*;

public class Client {
    public int clientID;

    public void writeToText(int id, double duration, String duedate) throws IOException {
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

        bufferedWriter.close();
    }
}