/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forumserver;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author DELL
 */
public class ServerWin extends JFrame implements ActionListener{
    JTextArea tareaStatus;
    JScrollPane jspStatus;
    JButton btnStop;
    
    public ServerWin(){
        Toolkit tool=Toolkit.getDefaultToolkit();
        Dimension size=tool.getScreenSize();
   //     System.out.print(size.width + "  ");
    //    System.out.print(size.height);
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("SERVER");
        this.setBounds((size.width/2)-500/2, (size.height/2)-500/2, 500, 500);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(null);
        this.tareaStatus=new JTextArea();
        this.jspStatus=new JScrollPane(this.tareaStatus,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.btnStop=new JButton("STOP");
        
        this.add(this.jspStatus);
        this.add(this.btnStop);
        this.jspStatus.setBounds(2,2,490,400);
        this.btnStop.setBounds(2,420,100,20);
        this.btnStop.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(JOptionPane.showConfirmDialog(this, "Are You Sure To Stop Server!!!","SERVER",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            System.exit(1);
        }
    }
}
