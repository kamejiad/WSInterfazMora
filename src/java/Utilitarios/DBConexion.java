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
    
    public Connection dBConexion(){// throws ClassNotFoundException, SQLException{
        try{
            Configuraciones.loadConfig();
            Class.forName(Configuraciones.propiedades[0]);
            String ConnectionURL =  Configuraciones.propiedades[1] +  Configuraciones.propiedades[2] +
            ";user="+  Configuraciones.propiedades[3] +
            ";password="+  Configuraciones.propiedades[4] +  ";";
            this.con = DriverManager.getConnection(ConnectionURL);
        }catch(ClassNotFoundException | SQLException e ){
            System.out.println(e);
        }   
        return this.con;
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
