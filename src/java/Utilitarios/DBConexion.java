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
    private Error error; //Objeto error
    private String codigo; //codigo del error
    private String descripcion; //descripción del error
    private String detalleTecnico; //explicación técnica 
    
    public Connection dBConexion(){// throws ClassNotFoundException, SQLException{
        try{
            Configuraciones.loadConfig();
            Class.forName(Configuraciones.propiedades[0]);
            String ConnectionURL =  Configuraciones.propiedades[1] +  Configuraciones.propiedades[2] +
            ";user="+  Configuraciones.propiedades[3] +
            ";password="+  Configuraciones.propiedades[4] +  ";";
            this.con = DriverManager.getConnection(ConnectionURL);
            return this.con;
        }catch(ClassNotFoundException e ){
            System.out.println(e);
            this.codigo = "1111";
            this.descripcion = "Error inesperado en el servicio";
            this.detalleTecnico = "Error producido internamente del servicio";
            this.error = new Error(this.codigo,this.descripcion,this.detalleTecnico);
            this.con = null;
            return this.con;
        }catch(SQLException e){
            System.out.println(e);
            this.codigo = "1122";
            this.descripcion = "Error de conexión de base de datos";
            this.detalleTecnico = "Asegurese que la base de datos esté siendo";
            this.error = new Error(this.codigo,this.descripcion,this.detalleTecnico);
            this.con = null;
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

    public Error getError() {
        return error;
    }
}
