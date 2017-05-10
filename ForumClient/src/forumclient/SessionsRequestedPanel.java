
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forumclient;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.ObjectOutputStream;
import java.util.Vector;
/**
 *
 * @author user
 */
public class SessionsRequestedPanel extends JPanel implements ActionListener{
    JButton  btnAccept,btnReject,btnBlock;
       JTable RequestTable, Upcomingtable;
     JLabel lblReq,lblUp,lblError;
    JScrollPane jspRequest,jspUpcoming;
   Vector<String> HEADUP; //"DATE","TIME","TOPIC","DESCRIPTION","CONDUCTED BY","DURATION"};
    Vector<Vector> DATAUP;
      Dimension size;
    Vector<String> HEADREQ;
    //=new Object[]{"REQUESTED BY","PROPOSED DATE","TOPIC","DESCRIPTION","DURATION"};
    Vector<Vector> DATAREQ;
    public SessionsRequestedPanel(){
        this.setLayout(null);
         Toolkit tool=Toolkit.getDefaultToolkit();
       size=tool.getScreenSize();
       this.setBounds(0,0,size.width,size.height);
        this.setVisible(true);
       this.setBackground(Color.yellow);
      
        this.HEADREQ=new Vector<String>();
       this.HEADREQ.add("REQUESTED BY");
       this.HEADREQ.add("PROPOSED DATE");
       this.HEADREQ.add("TOPIC");
       this.HEADREQ.add("DESCRIPTION");
       this.HEADREQ.add("TIME");
       this.DATAREQ=new Vector<Vector>();
       
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
       
        lblUp=new JLabel("Upcoming Sessions");
      this.add(lblUp);
      lblUp.setBounds(20,size.height/2-40,300,20);
       lblUp.setBackground(Color.LIGHT_GRAY);
      lblUp.setForeground(Color.blue);
       lblUp.setFont(new Font("Arial",1,12));
 
       btnAccept=new JButton("Accept");
        this.add(btnAccept);
        btnAccept.setBounds(600,320,110,20);
         btnAccept.setFont(new Font("Arial",1,12));
       btnAccept.setBackground(Color.BLUE);
       btnAccept.setForeground(Color.WHITE);
       
         btnReject=new JButton("Reject");
        this.add(btnReject);
        btnReject.setBounds(730,320,110,20);
         btnReject.setFont(new Font("Arial",1,12));
       btnReject.setBackground(Color.BLUE);
       btnReject.setForeground(Color.WHITE);
       
        
         btnBlock=new JButton("Block");
        this.add(btnBlock);
        btnBlock.setBounds(600,660,110,20);
         btnBlock.setFont(new Font("Arial",1,12));
       btnBlock.setBackground(Color.BLUE);
       btnBlock.setForeground(Color.WHITE);
        
       RequestTable=new JTable(DATAREQ,HEADREQ);
       //RequestTable.setEnabled(false);
      
      
           RequestTable.setBackground(Color.RED);
      jspRequest=new JScrollPane( RequestTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jspRequest.setBounds(20,20,size.width-100,size.height/2-100);
      
        this.add(jspRequest);
          Upcomingtable=new JTable(DATAUP,HEADUP);
        //Upcomingtable.setEnabled(false);
      
      
        Upcomingtable.setBackground(Color.RED);
      jspUpcoming=new JScrollPane(Upcomingtable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
      
        this.add(jspUpcoming);
        jspUpcoming.setBounds(20,size.height/2-20,size.width-100,size.height/2-100);
      btnAccept.addActionListener(this);
      btnReject.addActionListener(this);
      btnBlock.addActionListener(this);
       lblError=new JLabel("Error:- No row selected ");
                this.add(lblError);
       
    }
    public void actionPerformed(ActionEvent ae){
       if(ae.getSource()==btnAccept){
          if ( RequestTable.getSelectedRow()==-1)
          {   
              
                lblError.setBounds(200,0,380,20);
                lblError.setForeground(Color.red);
          }
          else {
              lblError.setText("");
              
                    try
             {    
                 ObjectOutputStream out=new  ObjectOutputStream(clientres.Resources.client.getOutputStream());
                 out.writeObject(commres.Request.SESSION_REQUEST_ACCEPTED);
                 out.writeObject( DATAREQ.elementAt(RequestTable.getSelectedRow()).elementAt(5));//send session id
             }catch(Exception ex)
             {
                 
             }
                    
                 
           } 
          

        }
          
       if(ae.getSource()==btnReject){
          if ( RequestTable.getSelectedRow()==-1)
          {   
           
                lblError.setBounds(200,0,380,20);
                lblError.setForeground(Color.red);
          }
          else lblError.setVisible(false);

        }
       
       if(ae.getSource()==btnBlock){
          if ( RequestTable.getSelectedRow()==-1)
          {   
              
                lblError.setBounds(200,344,380,20);
                lblError.setForeground(Color.red);
          }
          else lblError.setVisible(false);

        }


    }
    
}
