import java.sql.*;
import java.util.Scanner;

public class Bookstore{
    public static void main(String args[]) {
        Connection con = null;
	
        try {
        	Class.forName("com.mysql.jdbc.Driver").newInstance() ;
            con = DriverManager.getConnection("jdbc:mysql://peace.handong.edu:3306/21300482_YOONDONGHWEE", "db21300482", "21300482");
            if (!con.isClosed())
            System.out.println("Successfully connected to MySQL server.");

            //create
            Statement s = con.createStatement();
            
            s.executeUpdate("DROP TABLE IF EXISTS contacts");
            s.executeUpdate(
            		"CREATE TABLE contacts ("
            		+ "name CHAR(50), phonenumber CHAR(50), PRIMARY KEY(name))");
            //read
            
            Scanner scan = new Scanner(System.in);
            
            for(;;){
            	String inputLine = scan.nextLine();
            	String[] splitLine = inputLine.split(" ");

            	//help
            	if(splitLine[0].equals("help")){
            		System.out.println("insert <name> <phone number>\nlist");
            	}
            	//insert
            	else if(splitLine[0].equals("insert")){
            		
            		PreparedStatement ss = con.prepareStatement(
                    		"INSERT INTO contacts(name, phonenumber) VALUES(?,?)");
            		ss.setString(1, splitLine[1]);
            		ss.setString(2, splitLine[2]);
            		ss.executeUpdate();
            	}
            	//list
            	else if(splitLine[0].equals("list")){
            		s.executeQuery("SELECT name, phonenumber FROM contacts");
            		ResultSet rs = s.getResultSet();
                	
            		if(rs.first()){
            			System.out.println("name\t|  Phone number\n" + "===========================");
            			do{
            				String inputName = rs.getString("name");
            				String inputPhoneNumber = rs.getString("Phonenumber");
            				System.out.println(inputName + "\t|  " + inputPhoneNumber);
            			}while(rs.next());
            			rs.close();
            		}
            		else{
            			System.out.println("empty");
                    		
            		}
            	}
            	else if(splitLine[0].equals("quit")){
            		break;
            	}
            	//stock, input = title, author, category (string), ISBN (int)
            	//reject if conflicts with the existing book info
            	//If there is no copy, insert tuple
            	//If there is at least one copy, update tuple
            	//
            	
            	
            	//purchase
            	//input = ISBN, customer name
            	//Update the book stock respectively
            
            	//search
            	//input = keyword(title, author)
            	
            	//
            	
            	//daily report
            	
            	
            	
            	
            }
            
        	s.close();
        } 
        catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } 
        finally{
        	try {
        		if (con != null)
        			con.close();
        	} 
        	catch(SQLException e) {}
        }
    }
}