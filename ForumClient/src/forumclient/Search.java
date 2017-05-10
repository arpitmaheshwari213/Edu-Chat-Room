/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forumclient;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
/**
 *
 * @author user
 */
public class Search extends JPanel implements ActionListener{
    Label lblFrom,lblTo,lblError;
    TextField txtFrom,txtTo,txtTopic;
    Button btnSearch,btnDownload;
    Choice drop;
   
    Checkbox chbDate,chbUser,chbTopic;
    JTable table;
    JScrollPane jsp;
    Vector<String> HEAD;
    Vector<Vector> DATA;
       
    Dimension size;
    public Search(){
        this.setLayout(null);
         Toolkit tool=Toolkit.getDefaultToolkit();
       size=tool.getScreenSize();
       this.setBounds(0,0,size.width,size.height);
        this.setVisible(true);
        design();
         this.HEAD=new Vector<String>();
        this.HEAD.add("DATE");
        this.HEAD.add("TIME");
        this.HEAD.add("TOPIC");
        this.HEAD.add("DURATION");
        this.HEAD.add("CONDUCTED BY");
        this.HEAD.add("AVAILABILITY");
        this.DATA=new Vector<Vector>();
      
    }
    
    public void design(){
        
        chbDate=new Checkbox("By Date");
        this.add(chbDate);
        chbDate.setBounds(40,60,100,20);
        
        chbUser=new Checkbox("By User");
         this.add(chbUser);
        chbUser.setBounds(40,100,100,20);
        
        chbTopic=new Checkbox("By Topic");
         this.add(chbTopic);
        chbTopic.setBounds(40,140,100,20);
        
        lblFrom=new Label("From");
        this.add(lblFrom);
        lblFrom.setBounds(140,40,90,20 );
        
         lblTo=new Label("To");
        this.add(lblTo);
        lblTo.setBounds(250,40,90,20 );
        
        txtFrom=new TextField();
        this.add(txtFrom);
        txtFrom.setBounds(140,60,90,20);
        
        txtTo=new TextField();
        this.add(txtTo);
        txtTo.setBounds(250,60,90,20);
        
        drop=new Choice();
        this.add(drop);
        drop.setBounds(140,100,180,20);
        
        txtTopic=new TextField();
        this.add(txtTopic);
        txtTopic.setBounds(140,140,180,20);
        
        btnSearch= new Button("Search");
        this.add(btnSearch);
        btnSearch.setBounds(140,200,100,20);
        btnSearch.setFont(new Font("Arial",1,12));
       btnSearch.setBackground(Color.BLUE);
       btnSearch.setForeground(Color.WHITE);
       btnSearch.addActionListener(this);
       
       table=new JTable(DATA,HEAD);
       table.setBackground(Color.ORANGE);
       table.setEnabled(false);
       jsp=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      jsp.setBounds(20,280,size.width-100,size.height-400);
      this.add(jsp);

      btnDownload=new Button("Download");
       this.add(btnDownload);
        btnDownload.setBounds(1100,660,100,20);
        btnDownload.setFont(new Font("Arial",1,12));
       btnDownload.setBackground(Color.BLUE);
       btnDownload.setForeground(Color.WHITE);
       btnDownload.addActionListener(this);
       
        lblError=new Label();
        this.add(lblError);
        lblError.setFont(new Font("Arial",1,16));
        lblError.setForeground(Color.RED);
    } 
     
     public void actionPerformed(ActionEvent ae)
    {
        if(chbDate.getState()==false && chbUser.getState()==false && chbTopic.getState()==false)
        {
            lblError.setText("No search option selected...");
            lblError.setBounds(450,200,300,20);
        }
        else if(chbDate.getState()==true)
        {
            if(txtFrom.getText().equals("") || txtTo.getText().equals(""))
            {
              lblError.setText("Date not entered...");
              lblError.setBounds(450,60,300,20);
            }
        }
        else if(chbUser.getState()==true)
        {
            if(drop.getSelectedIndex()==-1)
            {
              lblError.setText("No user selected...");
              lblError.setBounds(450,100,300,20);
            }
        }
         else if(chbTopic.getState()==true)
        {
            if(txtTopic.getText().equals(""))
            {
              lblError.setText("Topic not specified...");
              lblError.setBounds(450,140,300,20);
            }
        }
        else 
        {
            lblError.setText(null);
        }
    }
  
}