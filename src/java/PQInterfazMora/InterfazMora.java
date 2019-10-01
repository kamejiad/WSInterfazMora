/*
Web Service para consultas de prestamos y tarjetas en mora.
Función principal
*/

package PQInterfazMora;
import Utilitarios.Respuesta;
import Utilitarios.Mora;
import Utilitarios.DBConexion;
import Utilitarios.Error;
import Utilitarios.ClienteCabecera;
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

    //General Properties
    private Respuesta resp;
    private Mora mora;
    private List<Mora> listMora = new ArrayList<>();
    private ClienteCabecera encabezado;

    private Error error;
    //Data Base Properties
    private DBConexion bd;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement query;

    //Client Properties 
    private String codCliente;
    private String idnCliente;
    private String nomCliente;
    private String apeCliente;

    private String estado;          //codigo del error
    private String descripcion;     //descripción del error
    private String detalleTecnico;  //explicación técnica 

    private String numCuenta;       //Número de Cuenta del Préstamo/Tarjeta
    private String fechaInicio;     //Fecha Inicio de Mora
    private String producto;        //Producto
    private String tipoProducto;    //Tipo de producto
    private int saldoTotal;         //Saldo total
    private int deudaExigible;      //Deuda esxigible: Capital

    /**
     * @param codigo
     * @param tipo
     * @param codAplicacion
     * @return
     */
    @WebMethod(operationName = "Response")
    public Respuesta Response(@WebParam(name = "codigo") String codigo, @WebParam(name = "tipo") String tipo, @WebParam(name = "codAplicacion") String codAplicacion) {

        if (autorizaAplicacion(codAplicacion, tipo, codigo)) {
            //---------------------------------------------------
            this.encabezado = new ClienteCabecera(codCliente, idnCliente, nomCliente, apeCliente);
            //---------------------------------------
            this.estado = "200";
            this.descripcion = "Exito";
            this.detalleTecnico = "El proceso se ejecutó correctamente";
            this.error = new Error(this.estado, this.descripcion, this.detalleTecnico);
            //--------------------------------/
            /*this.numCuenta = "1564511"; //Número de Cuenta del Préstamo/Tarjeta
            this.fechaInicio ="25072019"; //Fecha Inicio de Mora
            this.producto="4251"; //Producto
            this.tipoProducto="target 23"; //Tipo de producto
            this.saldoTotal=554461; //Saldo total
            this.deudaExigible=2654511;
            this.mora = new Mora(this.numCuenta,this.fechaInicio,this.producto,this.tipoProducto,this.saldoTotal,this.deudaExigible);
            *///--------------------------------------
            //this.listMora.add(mora);
            //----------------------------------------
            //this.resp = new Respuesta(this.error,this.encabezado,this.listMora);
            //---------------------
            consulta();
            
            if(!this.listMora.isEmpty()){
                this.resp = new Respuesta(this.error,this.encabezado,this.listMora);
            }
            
        }
        this.bd.dBDesconeccion();
        return this.resp;
    }

    //Función que comprueba que el código de aplicación exista.
    private boolean autorizaAplicacion(String codAplicacion, String tipo, String codigo) {
        boolean paso = false;
        boolean bandera = false;
        this.bd = new DBConexion();
        this.con = this.bd.dBConexion();
        try {
            this.query = this.con.prepareStatement("select * from TLSDTA.TLSAPLC where TLSCOD=?");
            this.query.setString(1, codAplicacion);
            this.rs = query.executeQuery();
            if (this.rs.isBeforeFirst()) {
                while (this.rs.next()) {
                    bandera = true;
                }
                this.rs.close();
            } else {
                this.rs.close();
                this.bd.dBDesconeccion();
            }
        } catch (SQLException ex) {
            this.estado = "4012";
            this.descripcion = "Error de conexión con la base de datos";
            this.detalleTecnico = "Es probable que no se haya encontrado el destino de datos";
            this.error = new Error(this.estado, this.descripcion, this.detalleTecnico);
            this.resp = new Respuesta(this.error);
        }

        if (bandera) {
            if ("C".equals(tipo)) {
                try {
                    //revision por tipo
                    this.query = this.con.prepareStatement("select CLINDO,CLINOM,CLIAPE from TLSDTA.CLI where CLINUM=?");
                    this.query.setString(1, codigo);
                    this.rs = query.executeQuery();
                    if (this.rs.isBeforeFirst()) {
                        while (this.rs.next()) {
                            paso = true;
                            this.codCliente = codigo;
                            this.idnCliente = this.rs.getString("CLINDO");
                            this.nomCliente = this.rs.getString("CLINOM");
                            this.apeCliente = this.rs.getString("CLIAPE");
                        }
                        this.rs.close();
                    } else {
                        this.rs.close();
                        this.bd.dBDesconeccion();
                    }
                } catch (SQLException ex) {
                    this.estado = "4012";
                    this.descripcion = "Error de conexión con la base de datos";
                    this.detalleTecnico = "Es probable que no se haya encontrado el destino de datos";
                    this.error = new Error(this.estado, this.descripcion, this.detalleTecnico);
                    this.resp = new Respuesta(this.error);
                }
            } else {
                if ("T".equals(tipo)) {
                    try {
                        this.query = this.con.prepareStatement("select * from TLSDTA.CLI where CLINDO=?");
                        this.query.setString(1, codigo);
                        this.rs = this.query.executeQuery();
                        if (this.rs.isBeforeFirst()) {
                            while (this.rs.next()) {
                                paso = true;
                                this.codCliente = this.rs.getString("CLINUM");
                                this.idnCliente = codigo;
                                this.nomCliente = this.rs.getString("CLINOM");
                                this.apeCliente = this.rs.getString("CLIAPE");
                            }
                            this.rs.close();
                        } else {
                            this.estado = "4012";
                            this.descripcion = "El cliente no ha sido encontrado";
                            this.detalleTecnico = "El numero de cliente que ha proporcionado no existe en el archivo de moras";
                            this.error = new Error(this.estado, this.descripcion, this.detalleTecnico);
                            this.resp = new Respuesta(this.error);
                            this.rs.close();
                            
                        }
                        this.bd.dBDesconeccion();
                    } catch (SQLException ex) {
                        this.estado = "4012";
                        this.descripcion = "Error de conexión con la base de datos";
                        this.detalleTecnico = "Es probable que no se haya encontrado el destino de datos";
                        this.error = new Error(this.estado, this.descripcion, this.detalleTecnico);
                        this.resp = new Respuesta(this.error);
                    }
                } else {
                    this.estado = "4011";
                    this.descripcion = "Error de tipo de busqueda";
                    this.detalleTecnico = "Debe enviar un tipo de busqueda correcto";
                    this.error = new Error(this.estado, this.descripcion, this.detalleTecnico);
                    this.resp = new Respuesta(this.error);
                    this.bd.dBDesconeccion();
                }
            }
        }
        
        return paso;
    }

    //Metodo que realiza las consultas para devolver el lista de registros.
    private void consulta() {
        //TO DO probar si realiza conexion

        // Busqueda de prestamos en mora
        try {
            this.query = this.con.prepareStatement("select * from TLSDTA.PRO where CLINUM=?");
            this.query.setString(1, this.codCliente);
            this.rs = this.query.executeQuery();
            if (this.rs.isBeforeFirst()) {
                while (this.rs.next()) {
                    this.numCuenta = this.rs.getString("PROCTA");
                    this.fechaInicio = this.rs.getString("PROFMA");
                    this.producto = this.rs.getString("PRODUC");
                    this.tipoProducto = this.rs.getString("PROTIP");
                    this.saldoTotal = this.rs.getInt("PROSTO");
                    this.mora = new Mora(this.numCuenta, this.fechaInicio, this.producto, this.tipoProducto, this.saldoTotal);
                    this.listMora.add(mora);
                }
                this.rs.close();
            }
        } catch (SQLException ex) {
            this.estado = "4012";
            this.descripcion = "Error de conexión con la base de datos";
            this.detalleTecnico = "Es probable que no se haya encontrado el destino de datos";
            this.error = new Error(this.estado, this.descripcion, this.detalleTecnico);
            this.resp = new Respuesta(this.error);
        }

        //Busqueda de tarjetas en mora
        try {
            this.query = this.con.prepareStatement("select * from TLSDTA.TCPRO where CLINUM=?");
            this.query.setString(1, this.codCliente);
            this.rs = this.query.executeQuery();
            if (this.rs.isBeforeFirst()) {
                while (this.rs.next()) {
                    this.numCuenta = this.rs.getString("TPROCTA");
                    this.fechaInicio = this.rs.getString("TPROINI");
                    this.producto = this.rs.getString("TPRODUC");
                    this.tipoProducto = this.rs.getString("TPROTIP");
                    this.saldoTotal = this.rs.getInt("TPROSTO");
                    this.deudaExigible = this.rs.getInt("TPRODEC");
                    this.mora = new Mora(this.numCuenta, this.fechaInicio, this.producto, this.tipoProducto, this.saldoTotal, this.deudaExigible);
                    this.listMora.add(mora);
                }
                this.rs.close();
                this.bd.dBDesconeccion();
            }
        } catch (SQLException ex) {
            this.estado = "4012";
            this.descripcion = "Error de conexión con la base de datos";
            this.detalleTecnico = "Es probable que no se haya encontrado el destino de datos";
            this.error = new Error(this.estado, this.descripcion, this.detalleTecnico);
            this.resp = new Respuesta(this.error);
        }

    }
    
    private void ingresarEstado(String controlador){
        switch (controlador) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
        } 
    }
}
