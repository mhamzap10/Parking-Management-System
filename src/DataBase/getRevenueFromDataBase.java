/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase;

import static DataBase.ParkingDataBase.setConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author MuhammadHamza
 */
public class getRevenueFromDataBase extends ParkingDataBase {
    public static int getRevenue(String TableName, String Date)
    {
        Connection con = setConnection();
        int count = 0;
        try{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("(SELECT * FROM "+TableName+" Where ParkingDate = '"+Date+"')");
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
    public static void main(String[]args)
    {
    }
}
    
