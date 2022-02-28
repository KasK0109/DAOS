package opgave7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class Opgave7 {

    public static void main(String[] args) {

        try {
            System.out.println("--Indsæt karakter--");
            BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Indtast karakter: ");
            String karakter = inLine.readLine();
            System.out.print("Indtast forsøgsnummer: ");
            String forsøgsnummer = inLine.readLine();
            System.out.print("Indtast studieId: ");
            String studieId = inLine.readLine();
            System.out.print("Indtast eksamensnavn: ");
            String eksamensNavn = inLine.readLine();

            Connection minConnection;
            minConnection = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-T6TRNBO2\\SQLExpress;databaseName=MiniProjekt;user=sa;password=FlightRadar24;");

            String sql = "insert into karakter (karakter, forsoegsCounter, studieID, EksamensNavn) values(?,?,?,?) ";// preparedStatement
            PreparedStatement prestmt = minConnection.prepareStatement(sql);
            prestmt.clearParameters();

            prestmt.setInt(1, Integer.parseInt(karakter));
            prestmt.setInt(2, Integer.parseInt(forsøgsnummer));
            prestmt.setInt(3, Integer.parseInt(studieId));
            prestmt.setString(4,eksamensNavn);

            prestmt.executeUpdate();
            System.out.println("Karakter indsat");

            prestmt.close();
            minConnection.close();

        }catch (SQLException e) {
            System.out.println("Fejl: " + e.getMessage());
            System.out.println("Kode: " + e.getErrorCode());
            if (e.getErrorCode() == 547) {
                System.out.println("--Karakteren du prøvede at indsætte er ikke på karakter skalaen!--");
            }
            if (e.getErrorCode() == 8152) {
                System.out.println("--Eksamensnavnet er for langt - prøv et kortere navn --");
            }

        }
        catch (Exception e) {
            System.out.println("fejl:  " + e.getMessage());
            System.out.println();
        }
    }

}
