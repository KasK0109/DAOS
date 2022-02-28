package ex3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Medarbejder {

    public static void main(String[] args) {

        try {
            System.out.println("Find Medarbejder ");
            BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Indtast medarbejder navn: ");
            String medNavn = inLine.readLine();

            Connection minConnection;
            minConnection = DriverManager.getConnection(
                    "jdbc:sqlserver://LAPTOP-T6TRNBO2\\SQLExpress;databaseName=Cashew;user=sa;password=FlightRadar24;");



            String sql = "select m.mobil from Medarbejder m where m.navn = ?";// preparedStatement
            PreparedStatement prestmt = minConnection.prepareStatement(sql);
            prestmt.clearParameters();

            prestmt.setString(1, medNavn.trim());

            ResultSet res = prestmt.executeQuery();

            res.next();
            System.out.println(res.getString(1));

            prestmt.close();
            minConnection.close();

        } catch (Exception e) {
            System.out.println("fejl:  " + e.getMessage());
        }
    }

}

