package landownerregistrationsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LandOwnerRegistrationSystemInterface {

    public LandOwnerRegistrationSystemInterface() {
     JFrame f =new JFrame("Login");//create frame object
         JLabel la = new JLabel("User name");
         JLabel lb = new JLabel("Password");
         JTextField jtfa = new JTextField("jia");
         JTextField jtfb = new JTextField("12345");
         JButton btna = new JButton("Login");
         JButton btnb = new JButton("Cancel");
         
         la.setBounds(20, 20, 100, 20);
         lb.setBounds(20, 60, 100, 20);
         jtfa.setBounds(100, 20, 200, 20);
         jtfb.setBounds(100, 60, 200, 20);
         btna.setBounds(100, 100, 100, 20);
         btnb.setBounds(200, 100, 100, 20);
         
         f.add(la);
         f.add(lb);
         f.add(jtfa);
         f.add(jtfb);
         f.add(btna);
         f.add(btnb);
         
         btna.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                    String name = jtfa.getText();
                    String pass = jtfb.getText();
                    if (name.equals("jia") && (pass.equals("12345"))){
                        JOptionPane.showMessageDialog(null, "Welcome to the System");
                        f.setVisible(false);
                        new LandOwnerRegistrationSystem();
                    }else{
                        JOptionPane.showMessageDialog(null, "Login Failed");
                    }
             }
         });
         
         btnb.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                   f.setVisible(false); 
                   f.dispose();
             }
         });
         
         f.setResizable(false);
         f.setSize(350,200); 
         f.setLayout(null);  
         f.setLocationRelativeTo(null);
         f.setVisible(true);  
         f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
       
}