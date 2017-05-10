/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forumclient;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectOutputStream;
import javax.swing.*;
/**
 *
 * @author user
 */
public class AdminWindow extends JFrame{
    JTabbedPane jtp;
    Dimension size;
    AdminRegistrationPanel adminReg;
    MentorRegistrationPanel mentorReg;
    SessionsRequestedPanel sessionReq;
    public AdminWindow(){
        this.setLayout(null);
         this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);   
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                if(JOptionPane.showConfirmDialog(AdminWindow.this, "Do You Want To Logout???","LOGOUT",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                    try{
                        ObjectOutputStream out=new ObjectOutputStream(clientres.Resources.client.getOutputStream());
                        out.writeObject(commres.Request.LOGOUT);
                        System.exit(1);
                    }catch(Exception ex){
                        
                    }

                }
            }
        });
        this.setTitle("Admin");
        // this.setBackground(Color.RED);
       Toolkit tool=Toolkit.getDefaultToolkit();
       size=tool.getScreenSize();
       this.setBounds(0,0,size.width,size.height);
        this.setVisible(true);
        design();
        
    }
    public void initRequest(){
        try{
            ObjectOutputStream out=new ObjectOutputStream(clientres.Resources.client.getOutputStream());
            out.writeObject(commres.Request.ADMIN_INIT);
            
        }catch(Exception ex){
            
        }
    }
    public void design(){
       
       this.adminReg= new AdminRegistrationPanel();
       this.mentorReg=new MentorRegistrationPanel();
       this.sessionReq=new  SessionsRequestedPanel();
        jtp=new JTabbedPane();
        jtp.addTab("Admin Registration",this.adminReg);
         jtp.addTab("Mentor Registration",this.mentorReg);
          jtp.addTab("Sessions Requested",this.sessionReq);
        this.add(jtp);
        jtp.setBackground(Color.blue);
        jtp.setForeground(Color.white);
         jtp.setBounds(0,0,size.width,size.height);
        
        jtp.setSelectedIndex(-1);
    }
}
