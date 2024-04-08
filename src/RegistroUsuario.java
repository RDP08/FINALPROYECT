import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistroUsuario {
    public static boolean registrarUsuario(Usuario usuario) {
        try {
            Connection conexion = DatabaseConnection.getConnection();
            String consulta = "INSERT INTO usuarios (nombreUsuario, contraseña) VALUES (?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, usuario.getNombreUsuario());
            statement.setString(2, usuario.getContraseña());
            int filasInsertadas = statement.executeUpdate();
            statement.close();
            conexion.close();
            return filasInsertadas > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
