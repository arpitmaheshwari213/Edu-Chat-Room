
package forumclient;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Vector;
/**
 *
 * @author user
 */
public class StudentRequestNewSessionPanel extends JPanel implements ActionListener
{
    JLabel lblDate,lblTopic,lblDesc,lblDuration,lblUp,lblReq,lblError,lblTime;
    JTextField txtDate,txtTopic,txtDuration,txtTime;
    TextArea txtDesc;
    JButton  btnAccept,btnReject,btnBlock,btnSend;
    JTable Upcomingtable;

    JScrollPane jspRequest,jspUpcoming;
    Vector<String> HEADUP;//"DATE","TIME","TOPIC","DESCRIPTION","CONDUCTED BY","DURATION"};
    Vector<Vector> DATAUP;
      Dimension size;
     public StudentRequestNewSessionPanel(){
          this.setLayout(null);
         Toolkit tool=Toolkit.getDefaultToolkit();
       size=tool.getScreenSize();
       this.setBounds(0,0,size.width,size.height);
        this.setVisible(true);
       this.HEADUP=new Vector<String>();
       this.HEADUP.add("DATE");
       this.HEADUP.add("TIME");
       this.HEADUP.add("TOPIC");
       this.HEADUP.add("DESCRIPTION");
       this.HEADUP.add("CONDUCTED BY");
       this.HEADUP.add("DURATION");
       this.DATAUP=new Vector<Vector>();
        design();
     }  
     public void design(){
           lblReq=new JLabel("Requested Sessions");
      this.add(lblReq);
      lblReq.setBounds(20,0,350,20);
       lblReq.setBackground(Color.LIGHT_GRAY);
      lblReq.setForeground(Color.blue);
       lblReq.setFont(new Font("Arial",1,12));
       
           lblTopic=new JLabel("Topic");
         this.add(lblTopic);
         lblTopic.setBounds(20,40,110,20);
         
           lblDesc=new JLabel("Description");
         this.add(lblDesc);
         lblDesc.setBounds(20,80,110,20);
         
           lblDuration=new JLabel("Duration");
         this.add(lblDuration);
         lblDuration.setBounds(20,200,110,20);
         
         lblDate=new JLabel("Proposed Date(dd/mm/yyyy)");
         this.add(lblDate);
         lblDate.setBounds(20,240,140,20);
         
         lblTime=new JLabel("Proposed Time");
         this.add(lblTime);
         lblTime.setBounds(20,280,110,20);
         
         txtTopic=new JTextField();
         this.add(txtTopic);
         txtTopic.setBounds(150,40,400,20);
         
         txtDesc=new TextArea();
         this.add(txtDesc);
         txtDesc.setBounds(150,80,400,100);
          
          txtDate=new JTextField();
         this.add(txtDate);
         txtDate.setBounds(150,240,150,20);
         
         txtTime=new JTextField();
         this.add(txtTime);
         txtTime.setBounds(150,280,150,20);
         
         txtDuration=new JTextField();
         this.add(txtDuration);
         txtDuration.setBounds(150,200,150,20);
         
          lblUp=new JLabel("Upcoming Sessions");
      this.add(lblUp);
      lblUp.setBounds(20,size.height/2-40,300,20);
       lblUp.setBackground(Color.LIGHT_GRAY);
      lblUp.setForeground(Color.blue);
       lblUp.setFont(new Font("Arial",1,12));
       
           btnSend=new JButton("Send");
        this.add(btnSend);
        btnSend.setBounds(320,280,110,20);
         btnSend.setFont(new Font("Arial",1,12));
       btnSend.setBackground(Color.BLUE);
       btnSend.setForeground(Color.WHITE);
       btnSend.addActionListener(this);
        Upcomingtable=new JTable(DATAUP,HEADUP);
      //  Upcomingtable.setEnabled(false);
      
      
        Upcomingtable.setBackground(Color.RED);
      jspUpcoming=new JScrollPane(Upcomingtable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      
        this.add(jspUpcoming);
        jspUpcoming.setBounds(20,size.height/2-20,size.width-100,size.height/2-100);
        
        lblError=new JLabel();
        this.add(lblError);
        lblError.setBounds(540,280,300,20);
        lblError.setFont(new Font("Arial",1,16));
        lblError.setForeground(Color.RED);
     }
     public void reset(){
         txtTopic.setText("");
         txtDesc.setText("");
         txtDate.setText("");
         txtDuration.setText("");
         txtTime.setText("");
     }
     public void actionPerformed(ActionEvent ae)
     {
         if(ae.getSource()==btnSend)
         {
             if(txtDate.getText().equals("")||txtDuration.getText().equals("")||txtTopic.getText().equals(""))
             {
                 JOptionPane.showMessageDialog(this,"complete all entries","Send",JOptionPane.INFORMATION_MESSAGE);
                 return;
             }
             try
             {
                 ObjectOutputStream out=new  ObjectOutputStream(clientres.Resources.client.getOutputStream());
                 out.writeObject(commres.Request.SESSION_REQUEST_SEND);
                 out.writeObject(this.txtTopic.getText());
                 out.writeObject(this.txtDesc.getText());
                 out.writeObject(this.txtDuration.getText());
                 out.writeObject(this.txtDate.getText());
                 out.writeObject(this.txtTime.getText());
                 reset();
                  
             }
             catch(Exception e)
             {
                 
             }
         }
     }
//             if(txtTopic.getText().equals(""))
//             {
//                lblError.setText("Topic not specified...");
//             }
//             else if(txtDate.getText().equals(""))
//             {
//                lblError.setText("Date not provided..." );
//             }
//     }
}
