package deadlyspace.jelastic.servint.net.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOConnector 
{    
    public static String runQuery(String query) {
    	String result = null;
    	Connection con = null;
    	PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123");
            con = DriverManager.getConnection("jdbc:mysql://mysql-deadlyspace.jelastic.com/test", "root", "1K7NTmrSTZ");
            
            ps = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();
            while(rs.next()){
            	result = rs.getString("answer");
            }
         } catch (SQLException e) {
            System.err.println("There are problems with the query " + query);
            e.printStackTrace(System.err);
         } catch (Exception ex) {
        	 System.err.println("Exception in getNomen, query "+query+", message " + ex.getMessage());
        	 ex.printStackTrace(System.err);
         } finally {
        	 try {
	             if (rs != null) {
	            	 rs.clearWarnings();
	            	 rs.close();
	             }
	             if (ps != null) {
	            	 ps.close();
	             }
	             if (con != null) {
	                 con.close();
	             }
        	 } catch (SQLException ex){
        		 ex.printStackTrace();
        	 }
         }
    	return result;
    }
}