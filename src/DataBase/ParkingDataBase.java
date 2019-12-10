package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ParkingDataBase
{
       public static Connection setConnection()
               {
                   String url = "jdbc:ucanaccess://D://hamza//assignment//NetBeansProjects//Parking//src//DataBase//parking.accdb";
                   Connection con = null;
                   try
                   {
                       con = DriverManager.getConnection(url);
                       System.out.println("Connection Established Succeesfull");
                   }
                   catch(Exception sql)
                   {
                       System.out.println(sql);
                   }
                  return con; 
               }
       public int getID(String TableName)
       {
           Connection con = setConnection();
            int k = 0;
           try
           {
           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("SELECT * FROM "+TableName+"");
           while(rs.next())
           {
               k++;
           }
           rs.close();
           st.close();
           }
           catch (Exception e)
           {
               System.out.println("Error Occuer");
           }
           return k+1;
       }
       public static int getRevenue(String TableName, String Date)
    {
        Connection con = setConnection();
        int count = 0;
        try{
        Statement st = con.createStatement();
        ResultSet rs ;
        if (Date.equals(""))
        rs = st.executeQuery("(SELECT * FROM "+TableName+")");
        else
            rs = st.executeQuery("(SELECT * FROM "+TableName+" Where ParkingDate = '"+Date+"')");
        while(rs.next())
        {
            count++;
        }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return count;
    }
       public void InsertCarDetail(String TableName, int id,String Reg , String Pos )
       {
           DateFormat TimeFormat = new SimpleDateFormat("HH:mm:ss a");
	Date time = new Date();
	String Time = (TimeFormat.format(time)); //2016/11/16 12:08:43
String pattern = "dd-MM-yyyy";
SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
String date = simpleDateFormat.format(new Date());
System.out.println(date);
           try{
           Connection con = setConnection();
           Statement st = con.createStatement();
           st.executeUpdate("INSERT into "+TableName+"Detail(ID,RegNo,Position,ParkingDate,TimeIn)VALUES("+id+",'"+Reg+"','"+Pos+"','"+date+"','"+Time+"')");
           st.executeUpdate("UPDATE "+TableName+"Position set RegNo = '"+Reg+"' Where Position = '"+Pos+"'");
           st.executeUpdate("UPDATE "+TableName+"Position set ID = "+id+" Where Position = '"+Pos+"'");
           System.out.println("Data Saved Suceesfully");
           st.close();
           con.close();
           }
           catch(Exception e)
           {
               System.out.println(e);
               System.out.println("Error Occurres");
           }
       }
       public String[] GetData(String TableName, String pos)
       {
           int id = 0;
           String Reg = "";
           Connection con = setConnection();
           try{
               Statement st = con.createStatement();
               ResultSet rs = st.executeQuery("SELECT * FROM "+TableName+" WHERE Position = '"+pos+"'");
               System.out.println("Select Quer work");
               while(rs.next())
               {
                  id = rs.getInt("ID");
                  Reg = rs.getString("RegNo");
                  System.out.println("Id = "+id);
                  System.out.println("reg #"+Reg);
               }
               rs.close();
               st.close();
               con.close();
           }
           catch(Exception e)
           {
               System.out.println("Error Occurs");
           }
          String[]Data = new String[2];
          Data[0]= String.valueOf(id);
          Data[1] = Reg;
return Data;
       }
       public void UpdateTimeOut(String TableName, int id)
       {
           Connection con = setConnection();
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss a");
	Date date = new Date();
	System.out.println(dateFormat.format(date)); 
        String time = dateFormat.format(date);
           try{
           Statement st = con.createStatement();
           st.executeUpdate("UPDATE "+TableName+" set TimeOut = '"+time+"' WHERE ID = "+id+"");
           System.out.println("Select Query Run Successfully");
           st.close();
           con.close();
           }
           catch(Exception e)
           {
               System.out.println("Error");
           }
           
       }
       public void UpdateCarPosition(String TableName , String pos)
       {
           try
           {
               Connection con = setConnection();
               Statement st = con.createStatement();
               st.executeUpdate("UPDATE "+TableName+" set RegNo = '0' Where Position = '"+pos+"'");
               st.executeUpdate("UPDATE "+TableName+" set ID = 0 Where Position = '"+pos+"'");
               System.out.println("Updated");
           st.close();
           con.close();
           }
           catch(Exception e)
           {
               System.out.println("Error");
           }
       }
       public  String  SelectCarPosition(String TableName)
       {
           String Pos = "N/A";
           try{
           Connection con = setConnection();
           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("SELECT * FROM "+TableName+" WHERE RegNo = '0'");
           while(rs.next())
           {
               Pos = rs.getString("Position");
               System.out.println("Position = "+Pos);
           }
           rs.close();
           st.close();
           con.close();
           }           catch(Exception e)
           {
           }
           return Pos;
       }
   public static void main(String[] args)
   {
   ParkingDataBase d1 = new ParkingDataBase();
   d1.UpdateCarPosition("BikePosition", "7");
   d1.UpdateTimeOut("BikeDetail", 1);
   }
}
