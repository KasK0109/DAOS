package ex6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class OpretMedarbejder {

    public static void main(String[] args) {

        try {
            System.out.println("Opret Medarbejder ");
            BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Indtast navn: ");
            String navn = inLine.readLine();
            System.out.print("Intast stillingsbetegnelse: ");
            String stillingsbetegnelse = inLine.readLine();
            System.out.print("Indtast mobil: ");
            String mobil = inLine.readLine();

            Connection minConnection;
            minConnection = DriverManager.getConnection(
                    "jdbc:sqlserver://LAPTOP-T6TRNBO2\\SQLExpress;databaseName=JDBC;user=sa;password=FlightRadar24;");

            String sql = "insert into Medarbejder values(?,?,?,?) ";// preparedStatement
            PreparedStatement prestmt = minConnection.prepareStatement(sql);
            prestmt.clearParameters();

            Statement stmt = minConnection.createStatement();

            ResultSet res = stmt.executeQuery("select max(m.medarbejderNr) from Medarbejder m");

            res.next();
            prestmt.setInt(1, Integer.parseInt(res.getString(1)) + 1);
            prestmt.setString(2, navn.trim());
            prestmt.setString(3, stillingsbetegnelse.trim());
            prestmt.setString(4, mobil.trim());

            prestmt.executeUpdate();
            System.out.println("Medarbejder indsat");

            prestmt.close();
            minConnection.close();

        } catch (Exception e) {
            System.out.println("fejl:  " + e.getMessage());
        }
    }

}

