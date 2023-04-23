package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bdd {
    private String url = "jdbc:mysql://localhost:3306/todolist";
    private String user = "root";
    private String password = "";

    public Connection getConnexion() throws SQLException {
        Connection maConnection = DriverManager.getConnection(this.url, this.user, this.password);
        return maConnection;
    }
}
