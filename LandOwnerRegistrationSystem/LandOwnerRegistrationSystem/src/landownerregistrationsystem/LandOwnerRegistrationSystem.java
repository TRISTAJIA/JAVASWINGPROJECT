package landownerregistrationsystem;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class LandOwnerRegistrationSystem {
JTextField jtfname = new JTextField();
JTextField jtfid = new JTextField();
JTextField jtfloc = new JTextField();
ArrayList<Land> landlist;
String header[] = new String[]{"Land Owner Name", "Land Owner ID","Land Location"};
DefaultTableModel dtm;
int row,col;
 private void clearField(){
        jtfname.setText("");
        jtfid.setText("");
        jtfloc.setText("");
    }
 
private void loadData(){
     try {
            File file = new File("data.txt"); //create file
            file.createNewFile();//if not exit
            FileReader f = new FileReader("data.txt");
            StringBuffer sb = new StringBuffer();
            while (f.ready()) {
                char c = (char) f.read();
                    if (c == '-') {
                        String landarray[] = sb.toString().split(",");
                        //System.out.println(foodarray[2]);
                        landlist.add(new Land(landarray[0],landarray[1],landarray[2]));
                        sb = new StringBuffer();
                    }else{
                        sb.append(c);
                    }
            }
        }catch(IOException e){
            return;
        }
        dtm.setRowCount(0);//reset data model
        for (int i = 0; i < landlist.size(); i++) {
            Object[] objs = {landlist.get(i).LandOwnerName,landlist.get(i).LandOwnerID,landlist.get(i).LandLocation};
            dtm.addRow(objs);
        }
}
private void writeData(){
     try (FileWriter f = new FileWriter("data.txt")) {
            StringBuilder sb = new StringBuilder();
            for (int i=0;i<landlist.size();i++){
                sb.append(landlist.get(i).LandOwnerName+","+landlist.get(i).LandOwnerID+","+landlist.get(i).LandLocation+"-");
            }
            f.write(sb.toString());
            f.close();
        }catch(IOException e){
            return;
        }
        dtm.setRowCount(0);//reset data model
        for (int i = 0; i < landlist.size(); i++) {
            Object[] objs = {landlist.get(i).LandOwnerName,landlist.get(i).LandOwnerID,landlist.get(i).LandLocation};
            dtm.addRow(objs);
        }
}   
    

    public LandOwnerRegistrationSystem(){
JFrame mainframe = new JFrame("Land Owner Registration System Interface");

landlist = new ArrayList<>();
dtm = new DefaultTableModel(header,0);
JTable jtable = new JTable();
jtable.setBackground(Color.LIGHT_GRAY);
        jtable.setModel(dtm);
JScrollPane scrollMain = new JScrollPane(jtable);
    scrollMain.add(jtable.getTableHeader());
    scrollMain.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
    scrollMain.setBounds(20, 130, 168, 20);
 JScrollPane scrollMain2 = new JScrollPane(jtable);   
 scrollMain2.setBounds(130, 130, 225, 20);
  JScrollPane scrollMain3 = new JScrollPane(jtable);   
 scrollMain3.setBounds(250, 130, 270, 20);
    loadData(); 
        
JLabel la=new JLabel("Land Owner Name");
JLabel lb=new JLabel("Land Owner ID");
JLabel lc=new JLabel("Land Location");
JLabel a=new JLabel("Land Owner Name");
JLabel b=new JLabel("Land Owner ID");
JLabel c=new JLabel("Land Location");

JButton btn1 = new JButton("Insert");
JButton btn2 = new JButton("Delete");
JButton btn3 = new JButton("Update");
JButton btn4 = new JButton("Search");
JButton btn5 = new JButton("Clear");        
la.setBounds(20,20,120,20);
lb.setBounds(20,60,100,20);
lc.setBounds(300,20,100,20);
a.setBounds(50,130,120,20);
b.setBounds(230,130,120,20);
c.setBounds(395,130,120,20);
jtfname.setBounds(140,20,140,20);
jtfid.setBounds(140,60,140,20);
jtfloc.setBounds(400,20,120,20);
btn1.setBounds(20,100,100,20);
btn2.setBounds(120,100,100,20);
btn3.setBounds(220,100,100,20);
btn4.setBounds(320,100,100,20);
btn5.setBounds(420,100,100,20);
jtable.setBounds(20,150,500,200);

btn1.addActionListener(new ActionListener() {            
    @Override
    public void actionPerformed(ActionEvent e) {
    String LandOwnerName =jtfname.getText();
    String LandOwnerID = jtfid.getText();
    String LandLocation = jtfloc.getText();
    landlist.add(new Land(LandOwnerName,LandOwnerID,LandLocation));
    writeData();
    }
    });

btn2.addActionListener(new ActionListener() {            
    @Override
    public void actionPerformed(ActionEvent e) {
    row = jtable.getSelectedRow();
        col = jtable.getColumnCount();
        System.out.println(row+","+col);
        jtfname.setText(dtm.getValueAt(row, 0).toString());
        jtfid.setText(dtm.getValueAt(row, 1).toString());
        jtfloc.setText(dtm.getValueAt(row, 2).toString());
        landlist.remove(row);
        clearField();
        writeData();
    }
    });

jtable.addMouseListener(new MouseAdapter(){

@Override
public void mouseClicked(MouseEvent e){
 row = jtable.getSelectedRow();
        col = jtable.getColumnCount();
        System.out.println(row+","+col);
        jtfname.setText(dtm.getValueAt(row, 0).toString());
        jtfid.setText(dtm.getValueAt(row, 1).toString());
        jtfloc.setText(dtm.getValueAt(row, 2).toString());    
}
});

btn3.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e) {
    String updatedlandname = jtfname.getText();
    String updatedlandid = jtfid.getText();
      String updatedlandloc = jtfloc.getText();
        landlist.get(row).LandOwnerName = updatedlandname;
        landlist.get(row).LandOwnerID = updatedlandid;
        landlist.get(row).LandLocation = updatedlandloc;
        writeData();
    }
});

btn4.addActionListener(new ActionListener(){ 

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = JOptionPane.showInputDialog(this,"Search Land Owner Name:");
        for (int i = 0; i < landlist.size(); i++) {
           if (landlist.get(i).LandOwnerName.equalsIgnoreCase(input)){
                JOptionPane.showMessageDialog(btn4, "Found!!!","Search Food",2);
                jtfname.setText(landlist.get(i).LandOwnerName);
                jtfid.setText(landlist.get(i).LandOwnerID);
                jtfloc.setText(landlist.get(i).LandLocation);
                 return;//Quit after found
            }
        }
       JOptionPane.showMessageDialog(btn4, "Not Found!!!","Search Food",2);
   }
});

btn5.addActionListener(new ActionListener(){ 

    @Override
    public void actionPerformed(ActionEvent e) {
     clearField();
    }
});

mainframe.add(la);
mainframe.add(lb);
mainframe.add(lc);
mainframe.add(a);
mainframe.add(b);
mainframe.add(c);
mainframe.add(jtfname);
mainframe.add(jtfid);
mainframe.add(jtfloc);
mainframe.add(btn1);
mainframe.add(btn2);
mainframe.add(btn3);
mainframe.add(btn4);
mainframe.add(btn5);
mainframe.add(jtable);
mainframe.add(scrollMain);
mainframe.add(scrollMain2);
mainframe.add(scrollMain3);
mainframe.setResizable(false);
mainframe.setSize(550,400);
mainframe.setLayout(null);
mainframe.setLocationRelativeTo(null);
mainframe.setVisible(true);
mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}    

    public static void main(String[] args) {
       
        new LandOwnerRegistrationSystemInterface();
         
}
    
    }



