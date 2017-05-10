/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forumclient;
import java.io.ObjectInputStream;
import java.util.Vector;
import javax.swing.*;
/**
 *
 * @author DELL
 */
public class AdminThread extends Thread{
    AdminWindow win;
    
    public AdminThread(AdminWindow win){
        this.win=win;
        this.start();
    }
    public void run(){
      try{
            while(true){
                ObjectInputStream in=new ObjectInputStream(clientres.Resources.client.getInputStream());
                commres.Request res=(commres.Request)in.readObject();
                if(res==commres.Request.ADMIN_INIT_RESP){
                    
                   Vector main=(Vector)in.readObject();
                     Vector upcom=(Vector)in.readObject();

                   
                      for(int i=0;i<main.size();i++){
                        this.win.sessionReq.DATAREQ.add((Vector)main.elementAt(i));
                    }
                    this.win.sessionReq.RequestTable.repaint();
                    
                     for(int i=0;i<upcom.size();i++){
                        this.win.sessionReq.DATAUP.add((Vector)upcom.elementAt(i));
                    }
                    this.win.sessionReq.Upcomingtable.repaint();
                }
                if(res==commres.Request.ADMIN_NEW_REQ){
                    Vector main=(Vector)in.readObject();
                    this.win.sessionReq.DATAREQ.removeAllElements();
                   
                    for(int i=0;i<main.size();i++){
                        this.win.sessionReq.DATAREQ.add((Vector)main.elementAt(i));
                    }
                    this.win.sessionReq.RequestTable.repaint();
                    
                }
                if(res==commres.Request.SESSION_REQUEST_ACCEPTED_ACK){
                    String resp=in.readObject().toString();
                    if(resp.equals("Success"))
                        JOptionPane.showMessageDialog(this.win, "Session request accepted successfully...","Session_Request_Accepted",JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(this.win, "Could not accepted Session request!!!\nPlease Retry","Session_Request_Accepted",JOptionPane.ERROR_MESSAGE);
                }
                if(res==commres.Request.ADMIN_ACCEPT_OR_REJECT_RESP){
                    Vector main=(Vector)in.readObject();
                       Vector upcom=(Vector)in.readObject();
                    this.win.sessionReq.DATAREQ.removeAllElements();
                    for(int i=0;i<main.size();i++){
                        this.win.sessionReq.DATAREQ.add((Vector)main.elementAt(i));
                    }
                    this.win.sessionReq.RequestTable.repaint();
                    
                   
                 this.win.sessionReq.DATAUP.removeAllElements();
                
                 for(int i=0;i<upcom.size();i++){
                        this.win.sessionReq.DATAUP.add((Vector)upcom.elementAt(i));
                    }
                    this.win.sessionReq.Upcomingtable.repaint();
                }
                  if(res==commres.Request.ADMIN_NEW_LIST){
                    Vector main=(Vector)in.readObject();
                    System.out.println("arpit");
                    this.win.adminReg.DATA.removeAllElements();
                    for(int i=0;i<main.size();i++){
                        this.win.adminReg.DATA.add((Vector)main.elementAt(i));
                    }
                    this.win.adminReg.table.repaint();
                }
                 if(res==commres.Request.ADMIN_REG_ACK){
                    String resp=in.readObject().toString();
                    if(resp.equals("Success"))
                        JOptionPane.showMessageDialog(this.win, "Session request sent successfully...","Session_Request",JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(this.win, "Could not send Session request!!!\nPlease Retry","Session_Request",JOptionPane.ERROR_MESSAGE);
                }
                
              }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.win, "Error in Admin Thread :" + ex);
        }   
    }
}

