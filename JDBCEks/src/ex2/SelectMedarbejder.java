package ex2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectMedarbejder
{

    public static void main(String[] args) {
        Connection minConnection;

        try {

            minConnection = DriverManager
                    .getConnection("jdbc:sqlserver://LAPTOP-T6TRNBO2\\SQLExpress;databaseName=Cashew;user=sa;password=FlightRadar24;");

            Statement stmt = minConnection.createStatement();

            ResultSet res = stmt.executeQuery("select m.navn,  m.mobil "
                    + "from Medarbejder m");
            while (res.next()) {
                System.out.println(res.getString(1) + "\t" + res.getString(2));
            }

            res.close();
            stmt.close();
            if (!minConnection.isClosed()) {
                minConnection.close();
            }

        } catch (Exception e) {
            System.out.println("fejl:  " + e.getMessage());
        }
    }
}

