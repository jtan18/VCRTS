import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OwnerSubmit extends JFrame{
    private JLabel ownerLabel;
    private JPanel ownerPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton submitButton;
    private JPanel JPanelConfirm;
    private JPanel JPanel1;
    private JButton rejectButton;

    public int ownerID;
    public Vehicle queuedVehicle;

    public void writeToText(int id, String vehicleInfo, String vehicleTime) {
        try {LocalDateTime timestamp = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
            String formattedDate = timestamp.format(dateFormatter);
            FileWriter writer = new FileWriter("out.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.newLine();
            bufferedWriter.write("[" + formattedDate + "]");
            bufferedWriter.newLine();
            bufferedWriter.write("Owner ID: " + id);
            bufferedWriter.newLine();
            bufferedWriter.write("Vehicle Description: " + vehicleInfo );
            bufferedWriter.newLine();
            bufferedWriter.write("Available for " + vehicleTime + " hours");
            bufferedWriter.newLine();
            bufferedWriter.close();} catch (IOException e){
            e.printStackTrace();
        }

    }
    OwnerSubmit(){
        super("Owner Information Submit");
        this.setContentPane(this.ownerPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        CloudController cc=new CloudController(this);
        CloudController cloudController=new CloudController();
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ownerID = textField1.getText();
                int id=Integer.parseInt(ownerID);
                String vehicleInfo=textField2.getText();
                String vehicleTime=textField3.getText();
                int vTime=Integer.parseInt(vehicleTime);
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                cc.setVisible(true);
                queuedVehicle = new Vehicle(id, vehicleInfo, vehicleTime);
                dispose();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
