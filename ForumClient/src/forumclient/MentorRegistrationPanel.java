
package forumclient;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class MentorRegistrationPanel extends JPanel implements ActionListener
{
 
    JButton btnDelete ,btnEdit,btnRegister;
    JTable table;
    JScrollPane jsp;
     JLabel lblTitle,lblHeader,lblLoginId,lblPassword,lblFirstName,lblLastName,lblEmail,lblContact,lblError2,lblError;
    JTextField txtLoginId,txtFirstName,txtLastName,txtEmail,txtContact;
    JPasswordField pass;
    
   Vector<String>HEAD;
    Vector<Vector>DATA;
    Dimension size;
    public MentorRegistrationPanel(){
        this.setLayout(null);
        this.setVisible(true);
        Toolkit tool= Toolkit.getDefaultToolkit();
         size=tool.getScreenSize();
        this.setBounds(0,0,size.width,size.height);
       this.setBackground(Color.yellow);
        design();
         this.HEAD=new Vector<String>();
       this.HEAD.add("MentorName");
       this.HEAD.add("LoginId");
       this.HEAD.add("Email");
       this.HEAD.add("Contact");
       this.HEAD.add("DateOfRegistration");
       this.HEAD.add("RegisteredBy");
       this.DATA=new Vector<Vector>();
    }
   public void  design(){
         lblTitle=new JLabel("Mentor New Registration");
      this.add(lblTitle);
      lblTitle.setBounds(100,40,400,20);
       lblTitle.setBackground(Color.LIGHT_GRAY);
      lblTitle.setForeground(Color.blue);
       lblTitle.setFont(new Font("Arial",1,12));
       
      
      lblHeader=new JLabel("Fill all the details correctly and then click on Register");
      this.add(lblHeader);
      lblHeader.setBounds(50,80,320,20);
      lblHeader.setForeground(Color.GREEN);
      
      lblLoginId=new JLabel("Login ID");
      this.add(lblLoginId);
      lblLoginId.setBounds(50,120,110,20);
      
       lblFirstName=new JLabel("First Name");
      this.add(lblFirstName);
      lblFirstName.setBounds(50,160,110,20);
      
       lblLastName=new JLabel("Last Name");
      this.add(lblLastName);
      lblLastName.setBounds(50,200,110,20);
      
       lblEmail=new JLabel("Email ID");
      this.add(lblEmail);
      lblEmail.setBounds(370,120,110,20);
      
       lblContact=new JLabel("Contact No.");
      this.add(lblContact);
      lblContact.setBounds(370,160,110,20);
      
     
         lblPassword=new JLabel("Enter Password");
      this.add(lblPassword);
      lblPassword.setBounds(370,200,110,20);
      
      
       
       txtLoginId=new JTextField();
      this.add(txtLoginId);
      txtLoginId.setBounds(190,120,110,20);
      
       txtFirstName=new JTextField();
      this.add(txtFirstName);
      txtFirstName.setBounds(190,160,110,20);
      
       txtLastName=new JTextField();
      this.add(txtLastName);
      txtLastName.setBounds(190,200,110,20);
      
       txtEmail=new JTextField();
      this.add(txtEmail);
      txtEmail.setBounds(490,120,110,20);
      
       txtContact=new JTextField();
      this.add(txtContact);
      txtContact.setBounds(490,160,110,20);
      
       pass=new JPasswordField();
       this.add(pass);
       pass.setBounds(490,200,110,20);
        
     
        btnRegister=new JButton("Register");
        this.add(btnRegister);
        btnRegister.setBounds(280,250,110,20);
        btnRegister.setBackground(Color.BLUE);
        btnRegister.setForeground(Color.WHITE);
        
        btnRegister.setFont(new Font("Arial",1,12));
        
        table=new JTable(DATA,HEAD);
        table.setBackground(Color.RED);
        jsp=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(jsp);
        jsp.setBounds(40,300,size.width-100,size.height/2-100);
        
         btnDelete=new JButton("Delete");
         this.add(btnDelete);
         btnDelete.setBounds(450,600,110,20);
          btnDelete.setBackground(Color.BLUE);
        btnDelete.setForeground(Color.WHITE);
        
        btnDelete.setFont(new Font("Arial",1,12));
        
         
        btnEdit=new JButton("Edit");
         this.add(btnEdit);
         btnEdit.setBounds(300,600,110,20);
          btnEdit.setBackground(Color.BLUE);
        btnEdit.setForeground(Color.WHITE);
        
        btnEdit.setFont(new Font("Arial",1,12));
         btnRegister.addActionListener(this);
        btnDelete.addActionListener(this);
        btnEdit.addActionListener(this);
        
                lblError=new JLabel();
                this.add(lblError);
                lblError.setBounds(50,100,380,20);
                lblError.setForeground(Color.red);
                
                lblError2=new JLabel();
                this.add(lblError2);
                lblError2.setBounds(40,270,380,20);
                lblError2.setForeground(Color.red);

        
   }
   void reset(){
         txtLoginId.setText(null);
        txtFirstName.setText(null);
        txtLastName.setText(null);
        txtContact.setText(null);
        txtEmail.setText(null);
        pass.setText(null);
   }
   public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==btnRegister)
       {
           if(txtContact.getText().isEmpty()||txtFirstName.getText().isEmpty()||txtLoginId.getText().isEmpty()||pass.getPassword().length==0)
           {
           lblError.setText("Error:- LoginId/ FirstName/ Password/ Contact field(s) is/are empty ");
                
              }
           else if(txtContact.getText().length()!=10){
               lblError.setText("Error:-Invalid Contact");
           }
           else { lblError.setText("");
           
           
           
           try{
             ObjectOutputStream out=new ObjectOutputStream(clientres.Resources.client.getOutputStream());
             out.writeObject(commres.Request.REGISTER);
             out.writeObject(this.txtLoginId.getText());
             out.writeObject(new String(this.pass.getPassword()));
             out.writeObject(this.txtFirstName.getText());
             out.writeObject(this.txtLastName.getText());
             out.writeObject(this.txtContact.getText());
             out.writeObject(this.txtEmail.getText());
             out.writeObject("Mentor");
             reset();
//             //reset 
//             ObjectInputStream in=new ObjectInputStream(clientres.Resources.client.getInputStream());
//             //JOptionPane.showMessageDialog(this, "Successfully Registered!!!\nLogin Now to access Services","Register",JOptionPane.INFORMATION_MESSAGE);
//             String resp=in.readObject().toString();
//             if(resp.equals("Success")){
//                 JOptionPane.showMessageDialog(this, "Successfully Registered!!!\nLogin Now to access Services","Register",JOptionPane.INFORMATION_MESSAGE);
//             }else{
//                 JOptionPane.showMessageDialog(this, "Cannot Register!!!","Register",JOptionPane.ERROR_MESSAGE);
//            //LogRegisterWin win=new LogRegisterWin();
//             }
           }catch(Exception ex){
             
         }
       }
           
     }
       if(ae.getSource()==btnEdit){
          if ( table.getSelectedRow()==-1)
          {   
              lblError2.setText("Error:- No row selected ");
          }
          else lblError2.setText("");

          
       }
        if(ae.getSource()==btnDelete){
            
          if ( table.getSelectedRow()==-1)
          {   
              lblError2.setText("Error:- No row selected ");
          }
          else lblError2.setText("");
       
          
       }
       
   }
}
