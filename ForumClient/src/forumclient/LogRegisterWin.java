/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forumclient;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.colorchooser.*;
/**
 *
 * @author user_pc
 */
public class LogRegisterWin extends JFrame implements ActionListener{
    JLabel lblReg,lblFname,lblLname,lblMail,lblCont,lblNewlid,lblNewpass,lblLogin,lblRole,lblLid,lblPass;
    JTextField txtFname,txtLname,txtMail,txtCont,txtNewlid,txtLid;
    JButton btnReg,btnLogin;
    JPasswordField Newpass,Pass;
    Choice Role;
   
    
    public LogRegisterWin(){
        this.setLayout(null);
        
        //NEW REGISTRATION
        lblReg=new JLabel("REGISTRATION");
        lblFname=new JLabel("First Name");
        lblLname=new JLabel("Last Name");
        lblMail=new JLabel("E-mail");
        lblCont=new JLabel("Contact Number");
        lblNewlid=new JLabel("Login Id");
        lblNewpass=new JLabel("Password");
      
        txtFname=new JTextField(15);
        txtLname=new JTextField(15);
        txtMail=new JTextField(15);
        txtCont=new JTextField(15);
        txtNewlid=new JTextField(15);
        
        Newpass=new JPasswordField();
        
        Role=new Choice();
        Role.add("Student");
        Role.add("Mentor");
        Role.add("Admin");

        btnReg=new JButton("Register");
        
        //LOGIN
        lblLogin=new JLabel("LOGIN");
        lblRole=new JLabel("Select Role");
        lblLid=new JLabel("Login Id");
        lblPass=new JLabel("Password");
        
        txtLid=new JTextField(15);
        
        Pass=new JPasswordField();
        
        btnLogin=new JButton("Login");
        
        design();
        
        this.btnLogin.addActionListener(this);
        this.btnReg.addActionListener(this);
        
    }
   Boolean checkText(JTextField c)
    {
        int i;
     Boolean t=false;
      for(i=0;i<c.getText().length();i++)
        {
        if((c.getText().charAt(i)>='A' && c.getText().charAt(i)<='Z') ||( c.getText().charAt(i)>='a'&& c.getText().charAt(i)<='z'))
        {
             t=true;
            }
        else
        {
            t=false;
            break;
                   
        }
        }
      return t;
    }
    void reset()
    {
        txtNewlid.setText(null);
        txtFname.setText(null);
        txtLname.setText(null);
        txtCont.setText(null);
        txtMail.setText(null);
        Newpass.setText(null);
    }
    Boolean check()
    {
        int i;
        Boolean t=false;
        int l=txtMail.getText().length();
        if(l<12)
            return false;
        
        String Mail=txtMail.getText().substring(l-10);
        if(Mail.equals("@gmail.com"))
            t=true;
        else
            t=false;
        return t;
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btnReg){
                if(txtNewlid.getText().equals("")||new String(this.Newpass.getPassword()).equals("")||txtFname.getText().equals("")||txtLname.getText().equals("")||txtCont.getText().equals("")|| txtMail.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Blanked Fields!!!\n Please fill all entries","Register",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if(txtCont.getText().length()!=10){
                    JOptionPane.showMessageDialog(this, "Conatct no should have 10 digits","Register",JOptionPane.INFORMATION_MESSAGE);
                    txtCont.setText(null);
                    return;
                }
                if(!checkText(txtFname)){
                    JOptionPane.showMessageDialog(this, "Enter valid First name","Register",JOptionPane.INFORMATION_MESSAGE);   
                    return;
                }
                if(!checkText(txtLname)){
                    JOptionPane.showMessageDialog(this, "Eenter valid Last name","Register",JOptionPane.INFORMATION_MESSAGE);   
                    return;
                }
                if(!check()){
                   JOptionPane.showMessageDialog(this, "Enter valid email id","Register",JOptionPane.INFORMATION_MESSAGE);   
                    return;    
                }
            
         try{
             ObjectOutputStream out=new ObjectOutputStream(clientres.Resources.client.getOutputStream());
             out.writeObject(commres.Request.REGISTER);
             out.writeObject(this.txtNewlid.getText());
             out.writeObject(new String(this.Newpass.getPassword()));
             out.writeObject(this.txtFname.getText());
             out.writeObject(this.txtLname.getText());
             out.writeObject(this.txtCont.getText());
             out.writeObject(this.txtMail.getText());
             out.writeObject("Student");
             reset();
             //reset 
             ObjectInputStream in=new ObjectInputStream(clientres.Resources.client.getInputStream());
             JOptionPane.showMessageDialog(this, "Successfully Registered!!!\nLogin Now to access Services","Register",JOptionPane.INFORMATION_MESSAGE);
             String resp=in.readObject().toString();
             if(resp.equals("Success")){
                 JOptionPane.showMessageDialog(this, "Successfully Registered!!!\nLogin Now to access Services","Register",JOptionPane.INFORMATION_MESSAGE);
             }else{
                 JOptionPane.showMessageDialog(this, "Cannot Register!!!","Register",JOptionPane.ERROR_MESSAGE);
            //LogRegisterWin win=new LogRegisterWin();
             }
             
                 
         }catch(Exception ex){
             
         }
            
        }
        if(e.getSource()==this.btnLogin){
            if(Role.getSelectedItem().equals("") || txtLid.getText().equals("") ||(new String(this.Pass.getPassword())).equals(""))
            {
                 JOptionPane.showMessageDialog(this, "Blanked Fields!!!\n Please fill all entries","Login",JOptionPane.INFORMATION_MESSAGE);
                 return;
            }
                            //validation check...
            try{
                ObjectOutputStream out=new ObjectOutputStream(clientres.Resources.client.getOutputStream());
                out.writeObject(commres.Request.LOGIN);
                out.writeObject(this.Role.getSelectedItem());
                out.writeObject(this.txtLid.getText());
                out.writeObject(new String(this.Pass.getPassword()));
                ObjectInputStream in=new ObjectInputStream(clientres.Resources.client.getInputStream());
             String resp=in.readObject().toString();
             if(resp.equals("Success")){
                 String id=in.readObject().toString();
                 String fname=in.readObject().toString();
                 String lname=in.readObject().toString();
                 System.out.println(fname);
                  System.out.println(lname);
                 
                 clientres.Resources.clientName=fname + " " + lname;
                 clientres.Resources.id=Integer.parseInt(id);
                 clientres.Resources.type=this.Role.getSelectedItem();
                 JOptionPane.showMessageDialog(this, "Successfully Logged In!!!","Login",JOptionPane.INFORMATION_MESSAGE);
                 //check & open window....
                 
                 
                 
                 if(clientres.Resources.type.equals("Admin")){
                    AdminWindow win=new AdminWindow();
                    win.setVisible(true);
                    new AdminThread(win);
                    win.initRequest();
                    this.dispose();
                    
                 }
                 if(clientres.Resources.type.equals("Student")){
                    StudentProfile win=new StudentProfile();
                    win.setVisible(true);
                    new StudentThread(win);
                    win.initRequest();
                    this.dispose();
                 }
                 
                 if(clientres.Resources.type.equals("Mentor")){
                    MentorWindow win=new MentorWindow();
                    win.setVisible(true);
                    new MentorThread(win);
                    this.dispose();
                    
                 }
                 
             }else{
                 JOptionPane.showMessageDialog(this, "Login Id/Password Invalid!!!","Login",JOptionPane.ERROR_MESSAGE);
             }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Exception in Login :" + ex,"Login",JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
    
    private void setPos(Component c,int x,int y,int w,int h){
       this.add(c);
       c.setBounds(x,y,w,h);
    }
    private void design(){
        this.setPos(lblReg,110,50,100,20);
        this.setPos(lblFname,40,120,100,20);
        this.setPos(txtFname,140,120,180,20);
        this.setPos(lblLname,40,160,100,20);
        this.setPos(txtLname,140,160,180,20);
        this.setPos(lblMail,40,200,100,20);
        this.setPos(txtMail,140,200,180,20);
        this.setPos(lblCont,40,240,100,20);
        this.setPos(txtCont,140,240,180,20);
        this.setPos(lblNewlid,40,280,100,20);
        this.setPos(txtNewlid,140,280,180,20);
        this.setPos(lblNewpass,40,320,100,20);
        this.setPos(Newpass,140,320,180,20);
        this.setPos(btnReg,100,400,150,50);
        
        this.setPos(lblLogin,600,50,100,20);
        this.setPos(lblRole,450,120,100,20);
        this.setPos(Role,550,120,180,20);
        this.setPos(lblLid,450,180,100,20);
        this.setPos(txtLid,550,180,180,20);
        this.setPos(lblPass,450,240,100,20);
        this.setPos(Pass,550,240,180,20);
        this.setPos(btnLogin,500,400,150,50);
        
        
    }

    
    
}
