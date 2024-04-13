import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistroUsuario {
    public static boolean registrarUsuario(Usuario usuario) {
        try {
            Connection conexion = DatabaseConnection.getConnection();
            String consulta = "INSERT INTO usuarios (nombre_usuario, contraseña, nombre, apellido, correo_electronico, telefono) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, usuario.getNombreUsuario());
            statement.setString(2, usuario.getContraseña());
            statement.setString(3, usuario.getNombre());
            statement.setString(4, usuario.getApellido());
            statement.setString(5, usuario.getCorreoElectronico());
            statement.setString(6, usuario.getTelefono());
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

