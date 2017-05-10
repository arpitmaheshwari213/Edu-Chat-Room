/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forumclient;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;
import javax.swing.*;
/**
 *
 * @author DELL
 */
public class StudentThread extends Thread{
    StudentProfile win;
    
    public StudentThread(StudentProfile win){
        this.win=win;
        this.start();
    }
    public void run(){
        try{
            while(true){
                ObjectInputStream in=new ObjectInputStream(clientres.Resources.client.getInputStream());
                commres.Request res=(commres.Request)in.readObject();
                if(res==commres.Request.SESSION_REQUEST_ACK){
                    String resp=in.readObject().toString();
                    if(resp.equals("Success"))
                        JOptionPane.showMessageDialog(this.win, "Session request sent successfully...","Session_Request",JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(this.win, "Could not send Session request!!!\nPlease Retry","Session_Request",JOptionPane.ERROR_MESSAGE);
                }
                 if(res==commres.Request.STUDENT_NEW_REQ)
                {
                    Vector main=(Vector)in.readObject();
                    Vector upcom=(Vector)in.readObject();
                    this.win.selfSess.DATA.removeAllElements();
                    
                    System.out.println("size of main" + main.size());
                    for(int i=0;i<main.size();i++){
                        System.out.println(main.elementAt(i));
                        this.win.selfSess.DATA.add((Vector)main.elementAt(i));
                    }
                    this.win.selfSess.sessionInfo.repaint();
                       this.win.sessNewReq.DATAUP.removeAllElements();
                    for(int i=0;i<upcom.size();i++){
                           
                        this.win.sessNewReq.DATAUP.add((Vector)upcom.elementAt(i));
                    }
                    this.win.sessNewReq.Upcomingtable.repaint();
                } 
//                 if(res==commres.Request.STUDENT_INIT_RESP){
//                    
//                   Vector main=(Vector)in.readObject();
//                    
//
//                   
//                      for(int i=0;i<main.size();i++){
//                        this.win.sessNewReq.DATAUP.add((Vector)main.elementAt(i));
//                    }
                // }
//                 if(res==commres.Request.STUDENT_ACCEPT_OR_REJECT_RESP){
//                    Vector upcom=(Vector)in.readObject();
//                    
//                    this.win.sessNewReq.DATAUP.removeAllElements();
//                  
//                    for(int i=0;i<upcom.size();i++){
//                    this.win.sessNewReq.DATAUP.add((Vector)upcom.elementAt(i));
//                     
//                    }
//                   this.win.sessNewReq.Upcomingtable.repaint();
//                     
//                }
                
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.win, "Error in Student Thread :" + ex);
        }
    }
     
}
