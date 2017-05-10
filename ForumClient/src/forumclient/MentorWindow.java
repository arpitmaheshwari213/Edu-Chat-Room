
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forumclient;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author user
 */
public class MentorWindow extends JFrame implements ActionListener{
    JLabel lblDate,lblTopic,lblDesc,lblDuration,lblUp,lblReq,lblError,lblError2,lblTime;
    JTextField txtDate,txtTopic,txtDuration,txtTime;
    TextArea txtDesc;
     JButton  btnSend,btnRemove;
       JTable RequestTable, Upcomingtable;

    JScrollPane jspRequest,jspUpcoming;
    Object HEADUP[]=new Object[]{"DATE","TIME","TOPIC","DESCRIPTION","CONDUCTED BY","DURATION"};
    Object DATAUP[][]=new Object[][]{};
      Dimension size;
     public MentorWindow(){
          this.setLayout(null);
         Toolkit tool=Toolkit.getDefaultToolkit();
       size=tool.getScreenSize();
       this.setBounds(0,0,size.width,size.height);
        this.setVisible(true);
      this.setTitle("Mentor");
        design();
     }  
     public void design(){
           lblReq=new JLabel("New Session");
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
         
         lblDate=new JLabel("Date");
         this.add(lblDate);
         lblDate.setBounds(20,240,110,20);
         
         lblTime=new JLabel("Time");
         this.add(lblTime);
         lblTime.setBounds(20,280,110,20);
         
         
         txtTopic=new JTextField();
         this.add(txtTopic);
         txtTopic.setBounds(150,40,150,20);
         
         txtDesc=new TextArea();
         this.add(txtDesc);
         txtDesc.setBounds(150,80,400,100);
          
          txtDate=new JTextField();
         this.add(txtDate);
         txtDate.setBounds(150,240,150,20);
         
          txtDuration=new JTextField();
         this.add(txtDuration);
         txtDuration.setBounds(150,200,150,20);
         
         txtTime=new JTextField();
         this.add(txtTime);
         txtTime.setBounds(150,280,150,20);
         
          lblUp=new JLabel("Upcoming Sessions");
      this.add(lblUp);
      lblUp.setBounds(20,size.height/2-40,300,20);
       lblUp.setBackground(Color.LIGHT_GRAY);
      lblUp.setForeground(Color.blue);
       lblUp.setFont(new Font("Arial",1,12));
       
           btnSend=new JButton("Send");
        this.add(btnSend);
        btnSend.setBounds(250,310,110,20);
         btnSend.setFont(new Font("Arial",1,12));
       btnSend.setBackground(Color.BLUE);
       btnSend.setForeground(Color.WHITE);
       
        Upcomingtable=new JTable(DATAUP,HEADUP);
        Upcomingtable.setEnabled(false);
      
      
        Upcomingtable.setBackground(Color.RED);
      jspUpcoming=new JScrollPane(Upcomingtable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
      
        this.add(jspUpcoming);
        jspUpcoming.setBounds(20,size.height/2-20,size.width-100,size.height/2-100);
         btnRemove=new JButton("Remove");
        this.add(btnRemove);
        btnRemove.setBounds(250,655,110,20);
         btnRemove.setFont(new Font("Arial",1,12));
       btnRemove.setBackground(Color.BLUE);
       btnRemove.setForeground(Color.WHITE);
       
        btnSend.addActionListener(this);
        btnRemove.addActionListener(this);
        
        lblError=new JLabel();
        this.add(lblError);
        lblError.setBounds(20,20,380,20);
        lblError.setForeground(Color.red);
        
        lblError2=new JLabel();
        this.add(lblError2);
        lblError2.setBounds(150,340,300,20);
        lblError2.setForeground(Color.red);
     }
     public void actionPerformed(ActionEvent ae){
          if(ae.getSource()==btnSend)
       {
           if(txtTopic.getText().isEmpty()||txtDuration.getText().isEmpty()||txtDesc.getText().isEmpty()||txtDate.getText().isEmpty()||txtTime.getText().isEmpty())
           {
               lblError.setText("Error:-Some field(s) is/are empty ");
              }
           else{ 
               lblError.setText("");
           
           }
         
       }
          if(ae.getSource()==btnRemove)
          {
              if(Upcomingtable.getSelectedRow()==-1)
              {
                   lblError2.setText("Error:- No row selected ");
              }
               else lblError2.setText("");
          }
     }
}
