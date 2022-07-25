
package ASimulatorSystem;
import java.sql.*;
/**
 *
 * @author Mayank
 */
public class Conn {
    Connection c;
    Statement s;
    public Conn(){
      try{
          Class.forName("com.mysql.cj.jdbc.Driver");
          c=DriverManager.getConnection("jdbc:mysql:///atm_sim ","root","");
          s=c.createStatement();
      }  
      catch(Exception e){
          System.out.println(e);
      }
    }
}
