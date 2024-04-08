import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutenticacionUsuario {
    public static boolean autenticarUsuario(String nombreUsuario, String contraseña) {
        try {
            Connection conexion = DatabaseConnection.getConnection();
            String consulta = "SELECT * FROM usuarios WHERE nombreUsuario = ? AND contraseña = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, nombreUsuario);
            statement.setString(2, contraseña);
            ResultSet resultado = statement.executeQuery();
            boolean autenticado = resultado.next();
            statement.close();
            conexion.close();
            return autenticado;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

