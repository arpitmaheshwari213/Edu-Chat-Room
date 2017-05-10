/*5
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forumclient;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.ObjectOutputStream;

/**
 *
 * @author APEKSHA
 */
public class StudentProfile extends JFrame
{     SelfSession selfSess;
      Search search;
      StudentRequestNewSessionPanel sessNewReq;
       JTabbedPane jtp;
       public StudentProfile(){
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);   
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                if(JOptionPane.showConfirmDialog(StudentProfile.this, "Do You Want To Logout???","LOGOUT",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                    try{
                        ObjectOutputStream out=new ObjectOutputStream(clientres.Resources.client.getOutputStream());
                        out.writeObject(commres.Request.LOGOUT);
                        System.exit(1);
                    }catch(Exception ex){
                        
                    }
                        
                        
                }
            }
        });
        this.setLayout(null);
        this.setTitle("Student");
        this.setVisible(true); 
        this.setBounds(0,0,1200,1000);  
        //setContentPane(new JLabel(new ImageIcon("My Computer\\Downloads\\students.png")));
        this.selfSess=new SelfSession();
        this.search=new Search();
        this.sessNewReq=new StudentRequestNewSessionPanel();        
        jtp=new JTabbedPane();
        jtp.addTab("Self Sessions",this.selfSess);
        jtp.addTab("Search Study Material",this.search);
        jtp.addTab("Sessions Requested",this.sessNewReq);
        this.add(jtp);
        jtp.setBackground(Color.MAGENTA);
        jtp.setForeground(Color.white);
        jtp.setBounds(100,10,1000,900);
        
        jtp.setSelectedIndex(-1);
}
       
 public void initRequest(){
        try{
            ObjectOutputStream out=new ObjectOutputStream(clientres.Resources.client.getOutputStream());
            out.writeObject(commres.Request.STUDENT_INIT);
            
        }catch(Exception ex){
            
        }
      }
}
