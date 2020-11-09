import javax.swing.*;
import java.util.*;
import java.io.*;

public class main {
    public static void main(String[] args) throws IOException {
        try
        {
            Screen screen=new Screen();
            screen.setVisible(true);

        }
        catch(IOException e)
        {
            //JOptionPane.showMessageDialog(null, "Error opening jobs file.");
            System.out.println("Could not locate joblist file");
            return;
        }



    }
}
