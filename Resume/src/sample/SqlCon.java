package sample;
import java.sql.*;


class SqlCon {
    static Connection crunchifyConn = null;
    static PreparedStatement crunchifyPrepareStat = null;
    private static void main(String args[]){
    }

    public static void makeJDBCConnection() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Congrats - Seems your MySQL JDBC Driver Registered!");}
        catch (ClassNotFoundException e) {
            System.out.println("Sorry, couldn't found JDBC driver.");
            e.printStackTrace();
            return;
        }

        try {
            // DriverManager: The basic service for managing a set of JDBC drivers.
            crunchifyConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/resume","test","test");
            if (crunchifyConn != null) {
                System.out.println("Connection Successful! Enjoy. Now it's time to push data");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("MySQL Connection Failed!");
            e.printStackTrace();

        }

    }


    public static void addDataToDB(String name, String surname, String email,
                                   String companyName1, String workDone1, String companyName2, String workDone2,
                                   String skill1, String skill2, String skill3, String skill4, String imageP, String companyName3, String workDone3) {

        try {
            String insertQueryStatement = "INSERT  INTO  person  VALUES  (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            crunchifyPrepareStat = crunchifyConn.prepareStatement(insertQueryStatement);
            crunchifyPrepareStat.setString(1, name);
            crunchifyPrepareStat.setString(2, surname);
            crunchifyPrepareStat.setString(3, email);
            crunchifyPrepareStat.setString(4, companyName1);
            crunchifyPrepareStat.setString(5, workDone1);
            crunchifyPrepareStat.setString(6, companyName2);
            crunchifyPrepareStat.setString(7, workDone2);
            crunchifyPrepareStat.setString(8, skill1);
            crunchifyPrepareStat.setString(9, skill2);
            crunchifyPrepareStat.setString(10, skill3);
            crunchifyPrepareStat.setString(11, skill4);
            crunchifyPrepareStat.setString(12, imageP);
            crunchifyPrepareStat.setString(13, companyName3);
            crunchifyPrepareStat.setString(14, workDone3);

            // execute insert SQL statement
            crunchifyPrepareStat.executeUpdate();
            System.out.println(name + " " + surname + " added successfully");
        } catch (

                SQLException e) {
            e.printStackTrace();
        }
    }


    public static void getDataFromDB() {



        try {


            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/resume";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "test", "test");

            // our SQL SELECT query.
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "SELECT * FROM person where name ='liza'";


            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {

                String name= rs.getString("name");
                String surName = rs.getString("surName");
                String email = rs.getString("email");
                if (name.equals("liza"))
                    System.out.println("trueee");

                // print the results
                System.out.println(name+surName+email+"   works");


            }
            st.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }


    }



    // Simple log utility
    private static void log(String string) {
        System.out.println(string);

    }


}




