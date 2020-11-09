import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientSubmit extends JFrame {
    private JLabel ClientLabel;
    private JPanel clientPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton sumitButton;
    int compTime;
    CloudController cc = new CloudController();

    public void writeToText(int id, double duration, String duedate, double cTime) {
        try {LocalDateTime timestamp = LocalDateTime.now();
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

            bufferedWriter.close();} catch (IOException e){
            e.printStackTrace();
        }

    }
    ClientSubmit(){
        super("Client Information Submit");
        this.setContentPane(this.clientPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        sumitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String clientID=textField1.getText();
                int id=Integer.parseInt(clientID);

                String jobDuration=textField2.getText();
                double jobD=Double.parseDouble(jobDuration);
                double totalCompletionTime= cc.calculateCtime(jobD);

                String jobDeadline=textField3.getText();
                writeToText(id,jobD,jobDeadline,totalCompletionTime);
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                dispose();
            }
        });
    }
}
