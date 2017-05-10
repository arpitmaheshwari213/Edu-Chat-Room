
package forumclient;
import java.util.Vector;
import javax.swing.*;

public class SelfSession  extends JPanel
{
    //JLabel lblStudName;
    //JTextField txtStudName;
    JTable sessionInfo;
    JScrollPane StudSession;
     Vector<String> HEAD;//"Session Topic","Date","Time","Duration","Status"};
    Vector<Vector> DATA;
    public SelfSession()
    {     this.HEAD=new Vector<String>();
       this.HEAD.add("SESSION TOPIC");
       //this.HEAD.add("DESCRIPTION");
       this.HEAD.add("DATE");
       this.HEAD.add("TIME");
       this.HEAD.add("DURATION");
       this.HEAD.add("STATUS");
       
       this.DATA=new Vector<Vector>();    
       
       
       
       sessionInfo=new JTable(DATA,HEAD);
       StudSession=new JScrollPane(sessionInfo,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       this.add(StudSession); 
       StudSession.setBounds(60,60,800,600);
       
      
    }
}
