//Clase para crear la conexión con la base de datos

package Utilitarios;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author kamejia
 */
public class DBConexion {
    private Connection con;
    private boolean estadoConexion;
    
    public Connection dBConexion(){// throws ClassNotFoundException, SQLException{
        try{
            Configuraciones.loadConfig();
            Class.forName(Configuraciones.propiedades[0]);
            String ConnectionURL =  Configuraciones.propiedades[1] +  Configuraciones.propiedades[2] +
            ";databaseName="+   Configuraciones.propiedades[3] +
            ";user="+  Configuraciones.propiedades[4] +
            ";password="+  Configuraciones.propiedades[5] +  ";";
            this.con = DriverManager.getConnection(ConnectionURL);
            this.estadoConexion = true;
            return this.con;
        }catch(ClassNotFoundException | SQLException e){
            this.estadoConexion = false;
            con = null;
            System.out.println(e);
            return this.con;
        }   
    }

    /**
     *
     * @throws SQLException
     */
    
    public void dBDesconeccion(){
        try{
            this.con.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
}
