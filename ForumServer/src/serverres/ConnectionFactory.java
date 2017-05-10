/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverres;
import java.sql.*;
/**
 *
 * @author DELL
 */
public class ConnectionFactory {
    public static ResultSet getResultSet(String query)throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost/DiscussForumDb","root","");
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        return rs;
    }
    public static int setData(String query)throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost/DiscussForumDb","root","");
        Statement stmt=con.createStatement();
        int n=stmt.executeUpdate(query);
        return n;
    }
}
