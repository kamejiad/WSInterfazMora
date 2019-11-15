/**
 * Web Service para consultas de prestamos y tarjetas en mora. Clase principal
 */
package PQInterfazMora;

import Utilitarios.Respuesta;
import Utilitarios.Mora;
import Utilitarios.DBConexion;
import Utilitarios.Estado;
import Utilitarios.ClienteCabecera;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 * Web Service para consultas de prestamos y tarjetas en mora. Método principal
 *
 * @author kamejia
 */
@WebService(serviceName = "InterfazMora")
public class InterfazMora {

    //General Properties
    private Respuesta resp;             //Respuesta retornada por el servicio
    private Mora mora;                  //"MR" Objeto mora, si existe algun producto en mora para el cliente en consulta
    private List<Mora> listMora;        //Lista de los productos en mora -Objetos mora- para el cliente de la consulta
    private ClienteCabecera encabezado; //"CC" Objeto de datos generales del cliente 
    private Estado estado;              //"ST" Objeto que representa el estado de la respuesta 
    //Data Base Properties
    private DBConexion bd;              //Instancia para la conexión con la base de datos
    private Connection con;             //Objeto de conexión con la base de datos
    private ResultSet rs;               //Conjunto de resultados de las consultas realizadas 
    private PreparedStatement query;    //Método de consulta
    //Client Properties 
    private String codCliente;          //"CC" Número de cliente IBS 
    private String idnCliente;          //"CC" Número de Documento Cliente (Identidad) 
    private String nomCliente;          //"CC" Nombre del cliente de la consulta
    private String apeCliente;          //"CC" Apellido del cliente o nombre de la empresa de la consulta   
    //State property
    private String codigoEstado;        //"ST" Código del error    
    //Product properties
    private String numCuenta;           //"MR" Número de Cuenta del Préstamo/Tarjeta
    private String fechaInicio;         //"MR" Fecha Inicio de Mora
    private String producto;            //"MR" Producto
    private String tipoProducto;        //"MR" Tipo de producto
    private String descProducto;        //"MR" Descripción del producto
    private double saldoTotal;          //"MR" Saldo total
    private double deudaExigible;       //"MR" Deuda esxigible: Capital

    /**
     * Método encargado de llamar funciones y retornar la respuesta
     *
     * @param codigoCliente
     * @param tipoCodigo
     * @param codAplicacion
     * @return Respuesta 
     */
    @WebMethod(operationName = "response")
    public Respuesta response(@WebParam(name = "codigoCliente") String codigoCliente, @WebParam(name = "tipoCodigo") String tipoCodigo, @WebParam(name = "codigoAplicacion") String codAplicacion) {

        this.resp = null;
        if (codigoCliente.trim().equals("") || tipoCodigo.trim().equals("") || codAplicacion.trim().equals("")) {
            this.codigoEstado = "407";
            this.estado = new Estado(this.codigoEstado);
            this.resp = new Respuesta(this.estado);
        } else {
            if (autorizaAplicacion(codAplicacion.trim(), tipoCodigo.trim().toUpperCase(), codigoCliente.trim())) {
                this.encabezado = new ClienteCabecera(codCliente, idnCliente, nomCliente, apeCliente);
                consulta();     //llamada a la función que ejecuta las consultas para la extracción de datos.  
                if (!this.listMora.isEmpty()) {
                    this.codigoEstado = "200";
                    this.estado = new Estado(this.codigoEstado);
                    this.resp = new Respuesta(this.estado, this.encabezado, this.listMora);
                } else {
                    this.codigoEstado = "204";
                    this.estado = new Estado(this.codigoEstado);
                    this.resp = new Respuesta(this.estado, this.encabezado);
                }
            }
            this.bd.dBDesconeccion();
        }
        return this.resp;
    }

    /**
     * Este método comprueba si la aplicación tiene acceso a consumir el
     * servicio, si tiene autorización comprueba que el cliente exista en el
     * archivo de moras
     *
     * @param codAplicacion
     * @param tipoCodigo
     * @param codigoCliente
     */
    private boolean autorizaAplicacion(String codAplicacion, String tipoCodigo, String codigoCliente) {

        boolean paso = false;
        boolean bandera = false;
        this.bd = new DBConexion();
        this.con = this.bd.dBConexion();
        try {
            this.query = this.con.prepareStatement("SELECT * FROM TLSDTA.TLSAPLC WHERE TLSCOD=?");
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
                this.codigoEstado = "401";
                this.estado = new Estado(this.codigoEstado);
                this.resp = new Respuesta(this.estado);
            }
        } catch (SQLException ex) {
            this.codigoEstado = "401";
            this.estado = new Estado(this.codigoEstado);
            this.resp = new Respuesta(this.estado);
        }
        //Si la aplicación está autorizada busca al cliente
        if (bandera) {
            if ("C".equals(tipoCodigo)) {   //Búsqueda de cliente por número de cliente IBS
                try {
                    this.query = this.con.prepareStatement("SELECT * FROM TLSDTA.CLI WHERE CLINUM=?");
                    this.query.setString(1, codigoCliente);
                    this.rs = query.executeQuery();
                    if (this.rs.isBeforeFirst()) {
                        while (this.rs.next()) {
                            paso = true;
                            this.codCliente = Integer.toString(this.rs.getInt("CLINUM"));
                            this.idnCliente = this.rs.getString("CLINDO");
                            this.nomCliente = this.rs.getString("CLINOM");
                            this.apeCliente = this.rs.getString("CLIAPE");
                        }
                    } else {
                        this.codigoEstado = "403";
                        this.estado = new Estado(this.codigoEstado);
                        this.resp = new Respuesta(this.estado);
                        this.rs.close();
                        this.bd.dBDesconeccion();
                    }
                } catch (SQLException ex) {
                    this.codigoEstado = "403";
                    this.estado = new Estado(this.codigoEstado);
                    this.resp = new Respuesta(this.estado);
                }
            } else {
                if ("I".equals(tipoCodigo)) {   //Búsqueda de cliente por número de identidad
                    try {
                        this.query = this.con.prepareStatement("SELECT * FROM TLSDTA.CLI WHERE CLINDO=?");
                        this.query.setString(1, codigoCliente);
                        this.rs = this.query.executeQuery();
                        if (this.rs.isBeforeFirst()) {
                            while (this.rs.next()) {
                                paso = true;
                                this.codCliente = Integer.toString(this.rs.getInt("CLINUM"));
                                this.idnCliente = this.rs.getString("CLINDO");
                                this.nomCliente = this.rs.getString("CLINOM");
                                this.apeCliente = this.rs.getString("CLIAPE");
                            }
                            this.rs.close();
                        } else {
                            this.codigoEstado = "403";
                            this.estado = new Estado(this.codigoEstado);
                            this.resp = new Respuesta(this.estado);
                            this.rs.close();
                            this.bd.dBDesconeccion();
                        }
                    } catch (SQLException ex) {
                        this.codigoEstado = "403";
                        this.estado = new Estado(this.codigoEstado);
                        this.resp = new Respuesta(this.estado);
                    }
                } else {
                    this.codigoEstado = "404";
                    this.estado = new Estado(this.codigoEstado);
                    this.resp = new Respuesta(this.estado);
                    this.bd.dBDesconeccion();
                }
            }
        }
        return paso;
    }

    /**
     * Método que realiza las consultas para devolver la lista de registros de
     * moras
     */
    private void consulta() {
        // Búsqueda de prestamos en mora
        listMora = new ArrayList<>();
        try {
            this.query = this.con.prepareStatement("SELECT "
                    + "CLINUM, "
                    + "PROCTA, "
                    + "PROFMA, "
                    + "PRODUC, "
                    + "PROTIP, "
                    + "PROSTO, "
                    + "MPRODE "
                    + "FROM TLSDTA.PRO AS A "
                    + "INNER JOIN TLSDTA.PROM1 AS B "
                    + "ON A.PRODUC = B.MPROTI  AND A.PROTIP = B.MPRODU "
                    + "WHERE CLINUM=?");
            this.query.setString(1, this.codCliente);
            this.rs = this.query.executeQuery();
            if (this.rs.isBeforeFirst()) {
                while (this.rs.next()) {
                    this.numCuenta = this.rs.getString("PROCTA");
                    this.fechaInicio = this.rs.getString("PROFMA");
                    this.producto = this.rs.getString("PRODUC");
                    this.tipoProducto = this.rs.getString("PROTIP");
                    this.descProducto = this.rs.getString("MPRODE");
                    this.saldoTotal = this.rs.getDouble("PROSTO");
                    this.mora = new Mora(this.numCuenta, this.fechaInicio, this.producto, this.tipoProducto, this.descProducto, this.saldoTotal);
                    this.listMora.add(mora);
                }
                this.rs.close();
            }
        } catch (SQLException ex) {
            this.codigoEstado = "402";
            this.estado = new Estado(this.codigoEstado);
            this.resp = new Respuesta(this.estado);
        }
        //Búsqueda de tarjetas en mora
        try {
            this.query = this.con.prepareStatement("SELECT "
                    + "CLINUM, "
                    + "TPROCTA, "
                    + "TPROINI, "
                    + "TPRODUC, "
                    + "TPROTIP, "
                    + "TPROSTO, "
                    + "TPRODEC, "
                    + "MPRODE "
                    + "FROM TLSDTA.TCPRO AS A "
                    + "INNER JOIN TLSDTA.PROM1 AS B "
                    + "ON A.TPRODUC = B.MPROTI  AND A.TPROTIP = B.MPRODU "
                    + "WHERE CLINUM=?");
            this.query.setString(1, this.codCliente);
            this.rs = this.query.executeQuery();
            if (this.rs.isBeforeFirst()) {
                while (this.rs.next()) {
                    this.numCuenta = this.rs.getString("TPROCTA");
                    this.fechaInicio = this.rs.getString("TPROINI");
                    this.producto = this.rs.getString("TPRODUC");
                    this.tipoProducto = this.rs.getString("TPROTIP");
                    this.descProducto = this.rs.getString("MPRODE");
                    this.saldoTotal = this.rs.getDouble("TPROSTO");
                    this.deudaExigible = this.rs.getDouble("TPRODEC");
                    this.mora = new Mora(this.numCuenta, this.fechaInicio, this.producto, this.tipoProducto, this.descProducto, this.saldoTotal, this.deudaExigible);
                    this.listMora.add(mora);
                }
                this.rs.close();
                this.bd.dBDesconeccion();
            }
        } catch (SQLException ex) {
            this.codigoEstado = "402";
            this.estado = new Estado(this.codigoEstado);
            this.resp = new Respuesta(this.estado);
        }
    }
}
