import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EksPreparedStatement {

    public static void main(String[] args) {

        try {
            System.out.println("Opret Medlem ");
            BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Indtast medlemsID: ");
            String medId = inLine.readLine();
            System.out.print("Intast navn: ");
            String navn = inLine.readLine();
            System.out.print("Indtast alder: ");
            String alder = inLine.readLine();

            Connection minConnection;
            minConnection = DriverManager.getConnection(
                    "jdbc:sqlserver://LAPTOP-T6TRNBO2\\SQLExpress;databaseName=Cashew;user=sa;password=FlightRadar24;");

            String sql = "insert into Medlem values(?,?,?) ";// preparedStatement
            PreparedStatement prestmt = minConnection.prepareStatement(sql);
            prestmt.clearParameters();

            prestmt.setInt(1, Integer.parseInt(medId.trim()));
            prestmt.setString(2, navn);
            prestmt.setInt(3, Integer.parseInt(alder.trim()));

            prestmt.executeUpdate();
            System.out.println("Medlem indsat");

            prestmt.close();
            minConnection.close();

        } catch(SQLException e) {
            System.out.println("Besked: " + e.getMessage());
            System.out.println("Kode: " + e.getErrorCode());
            if (e.getErrorCode() == 208) {
                System.out.println("Sti til database ikke korrekt");
            }
        }

        catch (Exception e) {
            System.out.println("fejl:  " + e.getMessage());
        }
    }

}
