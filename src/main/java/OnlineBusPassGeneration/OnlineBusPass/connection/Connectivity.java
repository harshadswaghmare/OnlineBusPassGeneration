package OnlineBusPassGeneration.OnlineBusPass.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectivity {
    static Connection conn;
    public static Connection CreateConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/buspass";
            String user = "postgres";
            String pass = "ConfluxSYS247";

            conn = DriverManager.getConnection(url,user,pass);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;

    }
}
