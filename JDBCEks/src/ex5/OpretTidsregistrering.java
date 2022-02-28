package ex5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class OpretTidsregistrering {

    public static void main(String[] args) {

        try {
            System.out.println("Opret Tidsregistrering ");
            BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Intast start tid: ");
            String startTid = inLine.readLine();
            System.out.print("Indtast slut tid: ");
            String slutTid = inLine.readLine();
            System.out.print("Indtast beskrivelse af arbejde: ");
            String beskrivelseAfArbejde = inLine.readLine();
            System.out.print("Indtast registrerings dato: ");
            String datoRegistrering = inLine.readLine();
            System.out.print("Indtast medarbejder nummer: ");
            String medarbedjerNr = inLine.readLine();
            System.out.print("Indtast opgave nummer: ");
            String opgaveNr = inLine.readLine();

            Connection minConnection;
            minConnection = DriverManager.getConnection(
                    "jdbc:sqlserver://LAPTOP-T6TRNBO2\\SQLExpress;databaseName=JDBC;user=sa;password=FlightRadar24;");

            String sql = "insert into Tidsregistrering values(?,?,?,?,?,?) ";// preparedStatement
            PreparedStatement prestmt = minConnection.prepareStatement(sql);
            prestmt.clearParameters();

            prestmt.setString(1, startTid.trim());
            prestmt.setString(2, slutTid.trim());
            prestmt.setString(3,beskrivelseAfArbejde.trim());
            prestmt.setString(4,datoRegistrering.trim());
            prestmt.setInt(5,Integer.parseInt(medarbedjerNr.trim()));
            prestmt.setInt(6,Integer.parseInt(opgaveNr.trim()));

            prestmt.executeUpdate();
            System.out.println("Tidsregistrering");

            prestmt.close();
            minConnection.close();

        } catch (Exception e) {
            System.out.println("fejl:  " + e.getMessage());
        }
    }

}
