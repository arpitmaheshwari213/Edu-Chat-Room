/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forumclient;
import java.io.ObjectInputStream;
import javax.swing.*;
/**
 *
 * @author DELL
 */
public class MentorThread extends Thread{
    MentorWindow win;
    
    public MentorThread(MentorWindow win){
        this.win=win;
        this.start();
    }
    public void run(){
        try{
            while(true){
                ObjectInputStream in=new ObjectInputStream(clientres.Resources.client.getInputStream());
                commres.Request res=(commres.Request)in.readObject();
                    if(res==commres.Request.MENTOR_REG_ACK){
                    String resp=in.readObject().toString();
                    if(resp.equals("Success"))
                        JOptionPane.showMessageDialog(this.win, "Session request sent successfully...","Session_Request",JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(this.win, "Could not send Session request!!!\nPlease Retry","Session_Request",JOptionPane.ERROR_MESSAGE);
                }
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this.win, "Error in Mentor Thread :" + ex);
        }
    }
}
