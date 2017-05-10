/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forumserver;

import java.net.*;
import javax.swing.*;
import java.io.*;
import commres.Request;
import java.sql.*;
import java.util.Calendar;
import java.util.Vector;
/**
 *
 * @author DELL
 */
public class ClientSupportThread extends Thread{
    
    private Socket client;
    private int id;
    private String role;
    
    public ClientSupportThread(Socket client){
        this.client=client;
        System.out.println("i am in constructor");
        this.start();
    }
    private String currentDate(){
        Calendar calendar=Calendar.getInstance();
        String date=calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DATE);
        return date;
    }
    private String convert(String userDate){
        String arr[];
        if(userDate.indexOf("/")!=-1)
            arr=userDate.split("/");
        else
            arr=userDate.split("-");
        String date=arr[2] + "/" + arr[1] + "/" + arr[0];
        return date;
    }
    public void run(){
        //System.out.println("i am in run");
        try{
            while(true){
                ObjectInputStream in=new ObjectInputStream(this.client.getInputStream());
                Request req=(Request)in.readObject();
                if(req==Request.REGISTER){
                    //System.out.println("abha");
                    String logid=in.readObject().toString();
                    String pass=in.readObject().toString();
                    String fname=in.readObject().toString();
                    String lname=in.readObject().toString();
                    String contact=in.readObject().toString();
                    String email=in.readObject().toString();
                    String type=in.readObject().toString();
                      int regBy=-1;
                    if(type.equals("Admin")||type.equals("Mentor"))
                    {   regBy=this.id;
                    }
                     
                    String query= "INSERT INTO user_master set " +
                                  "loginId='" + logid + "'," + 
                                  "password='" + pass + "'," + 
                                  "firstName='" + fname + "'," + 
                                  "lastName='" + lname + "'," + 
                                  "contact='" + contact + "'," + 
                                  "Email='" + email + "'," + 
                                  "type='" + type + "'," + 
                                  "regOn='" + this.currentDate() + "'," + 
                                  "regBy='" + regBy + "'";
                     //System.out.println("success");
                    int n=serverres.ConnectionFactory.setData(query);
                    ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
                    if(type.equals("Admin"))
                      out.writeObject(commres.Request.ADMIN_REG_ACK);
                    if(type.equals("Mentor"))
                      out.writeObject(commres.Request.MENTOR_REG_ACK);
                    if(n>0)
                        out.writeObject("Success");
                    else
                        out.writeObject("Failed");
                }
                if(req==Request.LOGIN){
                    String role=in.readObject().toString();
                    String login=in.readObject().toString();
                    String pass=in.readObject().toString();
                   
                    String query="select * from user_master where loginid='" + login + "' and password='" + pass + "'and type='"+role+"'";
                    ResultSet rs=serverres.ConnectionFactory.getResultSet(query);
                    ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
                    if(rs.next()){
                        this.id=rs.getInt("Id");
                        this.role=role;
                        out.writeObject("Success");
                        out.writeObject(this.id + "");
                        out.writeObject(rs.getString(4));
                        out.writeObject(rs.getString(5));
                        serverres.ClientDetail detail=new serverres.ClientDetail();
                        detail.id=this.id;
                        detail.client=this.client;
                        detail.type=this.role;
                        serverres.SharedResources.loggedInClient.add(detail);
                    
//                        if(this.role.equals("Admin")){
//                            
//                        }
//              if(role.equals("Admin")){
//                       String query1="select loginId,firstName,lastName,contact,regOn,regBy, Email from user_master where type='Admin'";
//                         rs=serverres.ConnectionFactory.getResultSet(query1);
//                        Vector<Vector> main=new Vector<Vector>();
//                        while(rs.next()){
//                             System.out.println("neha");
//                            Vector<String> sub=new Vector<String>();
//
//                            sub.add(rs.getString("firstName") + " " + rs.getString("lastName"));
//                            sub.add(rs.getString("loginId"));
//                            sub.add(rs.getString("Email"));
//                            sub.add(rs.getString("contact"));
//                            sub.add(rs.getString("regOn"));
//                            sub.add(rs.getString("regBy"));
//                            main.add(sub);
//                        }
//                        
//                        //for(int i=0;i<serverres.SharedResources.loggedInClient.size();i++){
//                           // if(serverres.SharedResources.loggedInClient.elementAt(i).type.equals("Admin")){
//                                ObjectOutputStream tmpout=new ObjectOutputStream(this.client.getOutputStream());
//                                tmpout.writeObject(commres.Request.ADMIN_NEW_LIST);
//                                tmpout.writeObject(main);
//   
//                          //}
//                    //}
//   }  
                    }
                    else{
                        out.writeObject("Failed");
                    }
                }
                if(req==Request.LOGOUT){
                    for(int i=0;i<serverres.SharedResources.loggedInClient.size();i++){
                            if(serverres.SharedResources.loggedInClient.elementAt(i).id==this.id){
                                serverres.SharedResources.loggedInClient.remove(i);
                            }
                    }
                    break;
                }
                if(req==Request.SESSION_REQUEST_SEND)
                {
                    String Title=in.readObject().toString();
                    String Desc=in.readObject().toString();
                    String Duration=in.readObject().toString();
                    String Date=this.convert(in.readObject().toString());
                    String time=in.readObject().toString();
                    
                    String query= "INSERT INTO session_master set " +
                                  "title='" + Title + "'," + 
                                  "description='" + Desc + "'," + 
                                  "duration='" + Duration + "'," + 
                                  "sess_date='" + Date + "'," + 
                                  "user_id=" + this.id +  "," + 
                                  "sess_time='" + time + "'," + 
                                  "status=" + 0 + "," + 
                                  "isavail="   +  0 + "";
                    
                    int n=serverres.ConnectionFactory.setData(query);
                    ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
                    out.writeObject(commres.Request.SESSION_REQUEST_ACK);
                    if(n>0){
                        out.writeObject("Success");
                        query="select sm.id,um.firstName,um.lastName,sm.title,sm.description,sm.sess_date,sm.sess_time from session_master sm,user_master um where sm.status=0 and um.id=sm.user_id";
                        ResultSet rs=serverres.ConnectionFactory.getResultSet(query);
                        Vector<Vector> main=new Vector<Vector>();
                        while(rs.next()){
                            Vector<String> sub=new Vector<String>();

                            sub.add(rs.getString("firstName") + " " + rs.getString("lastName"));
                            sub.add(rs.getString("title"));
                            sub.add(rs.getString("description"));
                            sub.add(rs.getString("sess_date"));
                            sub.add(rs.getString("sess_time"));
                            sub.add(rs.getString("id"));
                            main.add(sub);
                        }
                        
                        for(int i=0;i<serverres.SharedResources.loggedInClient.size();i++){
                            if(serverres.SharedResources.loggedInClient.elementAt(i).type.equals("Admin")){
                                ObjectOutputStream tmpout=new ObjectOutputStream(serverres.SharedResources.loggedInClient.elementAt(i).client.getOutputStream());
                                tmpout.writeObject(commres.Request.ADMIN_NEW_REQ);
                                tmpout.writeObject(main);
                                
                            }
                            
                            if(serverres.SharedResources.loggedInClient.elementAt(i).type.equals("Student")){
                                ObjectOutputStream tmout=new ObjectOutputStream(serverres.SharedResources.loggedInClient.elementAt(i).client.getOutputStream());
                                    if(this.id==id)                       
                                    {
                        String quer="select sm.id,sm.status,um.firstName,um.lastName,sm.title,sm.duration,sm.sess_date,sm.sess_time from session_master sm,user_master um where  sm.user_id=" + this.id + " and um.id=" + this.id + "";
                        ResultSet Stud_rs=serverres.ConnectionFactory.getResultSet(quer);
                         Vector<Vector> Stud_main=new Vector<Vector>();
                             while(rs.next())
                             {
                         Vector<String> Stud_sub=new Vector<String>();
                        Stud_sub.add(Stud_rs.getString("title"));
                        Stud_sub.add(this.convert(Stud_rs.getString("sess_date")));
                        Stud_sub.add(Stud_rs.getString("sess_time"));
                        Stud_sub.add(Stud_rs.getString("duration"));
                        if(Stud_rs.getString("status").equals("0"))
                            Stud_sub.add("pending");
                        else if(Stud_rs.getString("status").equals("1"))
                            Stud_sub.add("accepted");
                        else
                            Stud_sub.add("rejected");
                            Stud_main.add(Stud_sub);
                                }
                              System.out.println("size of main" + Stud_main.size());
                             for(i=0;i<Stud_main.size();i++)
                             System.out.println(Stud_main.elementAt(i));
                             tmout.writeObject(commres.Request.STUDENT_NEW_REQ);
                                tmout.writeObject(Stud_main);
                                
                                   
                                    }
                    
                        
                            }
                        
                        }
                    }
                    else
                        out.writeObject("Failed");
                }
                
                if(req==Request.ADMIN_INIT){
                    String query="select sm.id,um.firstName,um.lastName,sm.title,sm.description,sm.sess_date,sm.sess_time from session_master sm,user_master um where sm.status=0 and um.id=sm.user_id";
                    ResultSet rs=serverres.ConnectionFactory.getResultSet(query);
                    Vector<Vector> main=new Vector<Vector>();
                    while(rs.next()){
                        Vector<String> sub=new Vector<String>();

                        sub.add(rs.getString("firstName") + " " + rs.getString("lastName"));
                        sub.add(rs.getString("title"));
                        sub.add(rs.getString("description"));
                        sub.add(rs.getString("sess_date"));
                        sub.add(rs.getString("sess_time"));
                        sub.add(rs.getString("id"));
                        main.add(sub);
                        
                    }
                    
                           query="select sm.id,um.firstName,um.lastName,sm.title,sm.description,sm.sess_date,sm.sess_time,sm.duration from session_master sm,user_master um where sm.status=1 and um.id=sm.user_id";
                        ResultSet rs2=serverres.ConnectionFactory.getResultSet(query);
                        Vector<Vector> upcom=new Vector<Vector>();
                        while(rs2.next()){
                            Vector<String> sub2=new Vector<String>();
                             sub2.add(rs2.getString("sess_date"));
                             sub2.add(rs2.getString("sess_time"));
                             sub2.add(rs2.getString("title"));
                             sub2.add(rs2.getString("description"));
                            sub2.add(rs2.getString("firstName") + " " + rs2.getString("lastName"));
                            sub2.add(rs2.getString("duration"));
                            sub2.add(rs2.getString("id"));
                            upcom.add(sub2);
                        }
                    ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
                    out.writeObject(commres.Request.ADMIN_INIT_RESP);
                    out.writeObject(main);
                    out.writeObject(upcom);
                }
                
                
           if(req==Request.STUDENT_INIT)
                {
                    //String id=in.readObject().toString();
                    String query="select sm.id,sm.status,um.firstName,um.lastName,sm.title,sm.duration,sm.sess_date,sm.sess_time from session_master sm,user_master um where  sm.user_id=" + this.id + " and um.id=" + this.id + "";
                    ResultSet rs=serverres.ConnectionFactory.getResultSet(query);
                    Vector<Vector> main=new Vector<Vector>();
                    while(rs.next()){
                        Vector<String> sub=new Vector<String>();

                        sub.add(rs.getString("title"));
                        sub.add(rs.getString("sess_date"));
                        sub.add(rs.getString("sess_time"));
                        sub.add(rs.getString("duration"));
                        if(rs.getString("status").equals("0"))
                            sub.add("pending");
                        else if(rs.getString("status").equals("1"))
                            sub.add("accepted");
                        else
                            sub.add("rejected");
                       // sub.add(rs.getString("status"));
                        main.add(sub);
                    }
                    
                            
                        query="select sm.id,um.firstName,um.lastName,sm.title,sm.description,sm.sess_date,sm.sess_time,sm.duration from session_master sm,user_master um where sm.status=1 and um.id=sm.user_id";
                        ResultSet rs2=serverres.ConnectionFactory.getResultSet(query);
                        Vector<Vector> upcom=new Vector<Vector>();
                        while(rs2.next()){
                            Vector<String> sub2=new Vector<String>();
                             sub2.add(rs2.getString("sess_date"));
                             sub2.add(rs2.getString("sess_time"));
                             sub2.add(rs2.getString("title"));
                             sub2.add(rs2.getString("description"));
                            sub2.add(rs2.getString("firstName") + " " + rs2.getString("lastName"));
                            sub2.add(rs2.getString("duration"));
                            sub2.add(rs2.getString("id"));
                            upcom.add(sub2);
                        }
                    
                    ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
                    out.writeObject(commres.Request.STUDENT_NEW_REQ);
                    out.writeObject(main);
                    out.writeObject(upcom);
                }
                
              if(req==commres.Request.SESSION_REQUEST_ACCEPTED){
                String sess_id=in.readObject().toString();
                String query="Update session_master set status=1 where id='"+sess_id+"'";
                 int n=serverres.ConnectionFactory.setData(query);
                    ObjectOutputStream out=new ObjectOutputStream(this.client.getOutputStream());
                    out.writeObject(commres.Request.SESSION_REQUEST_ACCEPTED_ACK);
                    if(n>0){
                        out.writeObject("Success");
                         query="select sm.id,um.firstName,um.lastName,sm.title,sm.description,sm.sess_date,sm.sess_time from session_master sm,user_master um where sm.status=0 and um.id=sm.user_id";
                        ResultSet rs=serverres.ConnectionFactory.getResultSet(query);
                        Vector<Vector> main=new Vector<Vector>();
                        while(rs.next()){
                            Vector<String> sub=new Vector<String>();

                            sub.add(rs.getString("firstName") + " " + rs.getString("lastName"));
                            sub.add(rs.getString("title"));
                            sub.add(rs.getString("description"));
                            sub.add(rs.getString("sess_date"));
                            sub.add(rs.getString("sess_time"));
                            sub.add(rs.getString("id"));
                            main.add(sub);
                            
                        }
                        
                          query="select sm.id,um.firstName,um.lastName,sm.title,sm.description,sm.sess_date,sm.sess_time,sm.duration from session_master sm,user_master um where sm.status=1 and um.id=sm.user_id";
                        ResultSet rs2=serverres.ConnectionFactory.getResultSet(query);
                        Vector<Vector> upcom=new Vector<Vector>();
                        while(rs2.next()){
                            Vector<String> sub2=new Vector<String>();
                             sub2.add(rs2.getString("sess_date"));
                             sub2.add(rs2.getString("sess_time"));
                             sub2.add(rs2.getString("title"));
                             sub2.add(rs2.getString("description"));
                            sub2.add(rs2.getString("firstName") + " " + rs2.getString("lastName"));
                            sub2.add(rs2.getString("duration"));
                            sub2.add(rs2.getString("id"));
                            upcom.add(sub2);
                           
                        }
                        
                        for(int i=0;i<serverres.SharedResources.loggedInClient.size();i++){
                            if(serverres.SharedResources.loggedInClient.elementAt(i).type.equals("Admin")){
                                ObjectOutputStream tmpout=new ObjectOutputStream(serverres.SharedResources.loggedInClient.elementAt(i).client.getOutputStream());
                                tmpout.writeObject(commres.Request.ADMIN_ACCEPT_OR_REJECT_RESP);
                                tmpout.writeObject(main);
                               tmpout.writeObject(upcom);
                            }
                            else if(serverres.SharedResources.loggedInClient.elementAt(i).type.equals("Student")){ 
                              ObjectOutputStream tmpout=new ObjectOutputStream(serverres.SharedResources.loggedInClient.elementAt(i).client.getOutputStream());
                                    
                                    
                           String que="select sm.id,sm.status,um.firstName,um.lastName,sm.title,sm.duration,sm.sess_date,sm.sess_time from session_master sm,user_master um where  sm.user_id=" + this.id + " and um.id=" + this.id + "";
                           ResultSet Stud_rs=serverres.ConnectionFactory.getResultSet(que);
                           Vector<Vector> Stud_main=new Vector<Vector>();
                             while(Stud_rs.next())
                             {
                                  Vector<String> Stud_sub=new Vector<String>();
                                  Stud_sub.add(Stud_rs.getString("title"));
                                  Stud_sub.add(Stud_rs.getString("sess_date"));
                                  Stud_sub.add(Stud_rs.getString("sess_time"));
                                  Stud_sub.add(Stud_rs.getString("duration"));
                                   if(rs.getString("status").equals("0"))
                             Stud_sub.add("pending");
                        else if(rs.getString("status").equals("1"))
                            Stud_sub.add("accepted");
                        else
                            Stud_sub.add("rejected");
                                 Stud_main.add(Stud_sub);
                                }
                            
                             tmpout.writeObject(commres.Request.STUDENT_NEW_REQ);
                                tmpout.writeObject(Stud_main);
                                tmpout.writeObject(upcom);
                                   
                             
//                              tmpout.writeObject(commres.Request.STUDENT_ACCEPT_OR_REJECT_RESP);
//                              tmpout.writeObject(upcom);
                             }
                      
                        }                   
                       
                        
                    }
                    else
                        out.writeObject("Failed");
              }
        }    
        }catch(Exception ex){
            System.out.println("Error in Server Side Thread : " + ex);
        }
      
    }
}
