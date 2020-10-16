import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class Screen extends JFrame{
    private JLabel Question;
    private JButton buttonClient;
    private JButton buttonOwner;
    private JPanel panelMain;

    Screen()throws IOException{
        super("VCRTS");
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        OwnerSubmit ownersubmit=new OwnerSubmit();
        ClientSubmit clientsubmit=new ClientSubmit();
        buttonClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientsubmit.setVisible(true);

            }
        });
        buttonOwner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ownersubmit.setVisible(true);
            }
        });
    }

    public static void main(String[] args) throws IOException {
        Screen screen=new Screen();
        screen.setVisible(true);

    }
}
