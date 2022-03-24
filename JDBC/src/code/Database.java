package code;

import java.sql.*;
import java.util.ArrayList;

class Database {

    private static Connection con;
    private static PreparedStatement stmt;

    private static void openConnection() {
        try {
            String url =
                "jdbc:mysql://localhost:3306/bdcars?serverTimezone=Europe/Madrid&useSSL=false";
            con = DriverManager.getConnection(url, "root", "abcd*1234");
        } catch (SQLException sqe) {
            System.err.println("Error opening the database.");
        }
    }

    private static void closeConnection() {
        try {
            if (stmt != null) 
                stmt.close();
            if(con != null)
                con.close();

        } catch (SQLException e) {
            System.err.print("Error closing the connection with the database.");
        }
    }
    

    public static Owner searchOwner(String pID) {
        ResultSet rs = null;
        Owner o = null;

        openConnection();
        String searchOwner = "select * from owner where id = ?;";

        try {
            stmt = con.prepareStatement(searchOwner);
            stmt.setString(1, pID);

            rs = stmt.executeQuery();
            if (rs.next()) 
                o = new Owner(
                    pID, 
                    rs.getString("name"), 
                    rs.getString("telephone"));
            
        } catch (SQLException sqle) {
            System.err.println("Error reading from the database.");
        }

        return o;
    }

    public static ArrayList <Owner> getAllOwners() {
        ResultSet rs = null;
        openConnection();
        ArrayList <Owner> ans = 
            new ArrayList <Owner> ();
        
        try {
            String allOwners = "select * from owner;";
            stmt = con.prepareStatement(allOwners);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ans.add(new Owner(
                    rs.getString("id"), 
                    rs.getString("name"), 
                    rs.getString("telephone")));
               
            }
        } catch (SQLException sqle) {
            System.err.println("Error reading from the database.");
        }

        closeConnection();
        return ans;
    }


    public static void main(String[] args) {


        System.out.println("These are all the owners in the database:");
        ArrayList <Owner> lo = getAllOwners();
        for (Owner r : lo) 
            System.out.println(r.toString());
    }
}
