import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InicioSesion {
    public boolean verificarCredenciales(String usuario, String contraseña) {
        try {
            Connection conexion = DatabaseConnection.getConnection();
            String consulta = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contraseña = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, usuario);
            statement.setString(2, contraseña);
            ResultSet resultado = statement.executeQuery();
            boolean credencialesCorrectas = resultado.next();
            resultado.close();
            statement.close();
            conexion.close();
            return credencialesCorrectas;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

