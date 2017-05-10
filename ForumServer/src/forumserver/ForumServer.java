/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forumserver;

/**
 *
 * @author DELL
 */
import java.io.*;
import java.net.*;
import java.util.Vector;
import serverres.ClientDetail;

public class ForumServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            ServerSocket server=new ServerSocket(2233);
            serverres.SharedResources.loggedInClient=new Vector<ClientDetail>();
            new ServerWin().setVisible(true);
            while(true){
                Socket client=server.accept();
                new ClientSupportThread(client);
            }
        }catch(Exception ex){
            
        }
    }
    
}
