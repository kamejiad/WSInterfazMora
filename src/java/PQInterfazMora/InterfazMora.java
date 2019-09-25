/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PQInterfazMora;

import Utilitarios.Respuesta;
import Utilitarios.Mora;
import Utilitarios.DBConexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author kamejia
*/
@WebService(serviceName = "InterfazMora")
public class InterfazMora {
    
    //Variables Globales
    private Respuesta resp;
    private Mora mora;
    private List<Mora> listMora = new ArrayList<>();
    private Connection con;
    private ResultSet  rs1;
    private String codigo1;
    private DBConexion bd;
    
    /**
     * @param codigo
     * @param tipo
     * @param codAplicacion
     * @return 
     */
    @WebMethod(operationName = "Response")
    public String Response(@WebParam(name = "codigo") String codigo,@WebParam(name = "tipo") String tipo,@WebParam(name = "codAplicacion") String codAplicacion) {
   
          
        //TO DO  probar la conexion con la base de datos
        
        System.out.println(codigo1);
        consulta();
        return  this.codigo1;
    }
    
    //Función que comprueba que el código de aplicación exista.
    private void autorizaAplicacion(String codigo){
        
    }
    
    //Metodo que realiza las consultas para devolver el lista de registros.
    private void consulta(){
        //TO DO probar si realiza conexion  
    }
}
