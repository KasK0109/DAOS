import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectMedlemmer {

    public static void main(String[] args) {
        Connection minConnection;

        try {

            minConnection = DriverManager
                    .getConnection("jdbc:sqlserver://LAPTOP-T6TRNBO2\\SQLExpress;databaseName=KlubEksempel;user=sa;password=FlightRadar24;");

            Statement stmt = minConnection.createStatement();

            ResultSet res = stmt.executeQuery("select * from Medlem");
            while (res.next()) {
                System.out.println(res.getString(1) + "\t" + res.getString(2) + " \t " + res.getString(3));
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
