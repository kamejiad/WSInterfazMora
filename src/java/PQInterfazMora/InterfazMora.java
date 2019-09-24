/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PQInterfazMora;

import Utilitarios.Respuesta;
import Utilitarios.Mora;
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
    
    /**
     * @param codigo
     * @param tipo
     * @param codAplicacion
     * @return 
     */
    @WebMethod(operationName = "Response")
    public Respuesta Response(@WebParam(name = "codigo") String codigo,@WebParam(name = "tipo") String tipo,@WebParam(name = "codAplicacion") String codAplicacion) {
        return  this.resp;
    }
    
    //Función que comprueba que el código de aplicación exista.
    public void autorizaAplicacion(String codigo){
        
    }
    
    //Metodo que realiza las consultas para devolver el lista de registros.
    public void consulta(){
    
    }
}
