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
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author akshara
 */
public class IPEntry extends JFrame implements ActionListener{
    JButton btnCnct;
    JLabel lblIPEntry;
    JTextField txtIPEntry;
    public IPEntry()
    {  
        Toolkit tool=Toolkit.getDefaultToolkit();
        Dimension size=tool.getScreenSize();
   //     System.out.print(size.width + "  ");
    //    System.out.print(size.height);
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("IP ENTRY");
        this.setBounds((size.width/2)-200, (size.height/2)-100, 400, 200);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        init();
        addComp();
        this.btnCnct.addActionListener(this);
    }
    private void init(){
        btnCnct=new JButton("Connect");
        lblIPEntry=new JLabel("Enter Server IP");
        txtIPEntry=new JTextField("127.0.0.1");
    }
    private void addComp()
    {
        this.add(this.btnCnct);
        this.add(this.lblIPEntry);
        this.add(this.txtIPEntry);
        this.lblIPEntry.setBounds(20, 30, 100, 50);
        this.txtIPEntry.setBounds(200,50,150,20);
        this.btnCnct.setBounds(210,100,100,30);
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getSource()==this.btnCnct){
            String ip=this.txtIPEntry.getText().trim();
            if(ip.equals("")){
                JOptionPane.showMessageDialog(this,"No Server IP Provided!!!","IP Entry",JOptionPane.ERROR_MESSAGE);
                return;
            }
            try{
                InetAddress inet=InetAddress.getByName(ip);
                clientres.Resources.client=new Socket(inet,2233);
                JOptionPane.showMessageDialog(this,"Connected To Server Successfully...","IP Entry",JOptionPane.INFORMATION_MESSAGE);
                
                LogRegisterWin win=new LogRegisterWin();
                win.setVisible(true);
                Toolkit tool=Toolkit.getDefaultToolkit();
                Dimension size=tool.getScreenSize();
                final int WIDTH=800;
                final int HEIGHT=500;
                win.setVisible(true);
                win.setBounds(size.width/2 -WIDTH/2,size.height/2-HEIGHT/2,WIDTH,HEIGHT);
                win.setResizable(false);
                win.setTitle("REGISTRATION/LOGIN");
                this.dispose();
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this,"Cannot Connect To Server!!!","IP Entry",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    
}
