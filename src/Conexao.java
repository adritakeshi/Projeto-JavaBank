import java.sql.*;

public class Conexao {
    public static Connection conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/SistemaBancario";
        String usuario = "root";
        String senha = "282612";

        return DriverManager.getConnection(url, usuario, senha);
    }
}
