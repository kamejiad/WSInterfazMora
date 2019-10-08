/*
 Web Service para consultas de prestamos y tarjetas en mora.
 Función principal
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
 *
 * @author kamejia
 */
@WebService(serviceName = "InterfazMora")
public class InterfazMora {

    //General Properties
    private Respuesta resp;
    private Mora mora;
    private List<Mora> listMora;// = new ArrayList<>();
    private ClienteCabecera encabezado;

    private Estado estado;
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

    private String codigoEstado;          //codigo del error 

    private String numCuenta;       //Número de Cuenta del Préstamo/Tarjeta
    private String fechaInicio;     //Fecha Inicio de Mora
    private String producto;        //Producto
    private String tipoProducto;    //Tipo de producto
    private String descProducto;    //Descripción del producto
    private double saldoTotal;         //Saldo total
    private double deudaExigible;      //Deuda esxigible: Capital

    /**
     * @param codigo
     * @param tipo
     * @param codAplicacion
     * @return
     */
    @WebMethod(operationName = "Response")
    public Respuesta Response(@WebParam(name = "codigoCliente") String codigoCliente, @WebParam(name = "tipoCodigo") String tipoCodigo, @WebParam(name = "codigoAplicacion") String codAplicacion) {
        this.resp = null;
        if (codigoCliente.trim().equals("") || tipoCodigo.trim().equals("") || codAplicacion.trim().equals("")) {
            this.codigoEstado = "407";
            this.estado = new Estado(this.codigoEstado);
            this.resp = new Respuesta(this.estado);
        } else {
            if (autorizaAplicacion(codAplicacion.trim(), tipoCodigo.trim().toUpperCase(), codigoCliente.trim())) {
                this.encabezado = new ClienteCabecera(codCliente, idnCliente, nomCliente, apeCliente);
         
                consulta();

                if (!this.listMora.isEmpty()) {
                    this.codigoEstado = "200";
                    this.estado = new Estado(this.codigoEstado);
                    this.resp = new Respuesta(this.estado, this.encabezado, this.listMora);
                } else {
                    this.codigoEstado = "201";
                    this.estado = new Estado(this.codigoEstado);
                    this.resp = new Respuesta(this.estado, this.encabezado);
                }
            }
            this.bd.dBDesconeccion();
        }   
        return this.resp;
    }

    //Función que comprueba que el código de aplicación exista.
    private boolean autorizaAplicacion(String codAplicacion, String tipoCodigo, String codigoCliente) {
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
                this.codigoEstado = "405";
                this.estado = new Estado(this.codigoEstado);
                this.resp = new Respuesta(this.estado);
            }
        } catch (SQLException ex) {
            this.codigoEstado = "4012";
            this.estado = new Estado(this.codigoEstado);
            this.resp = new Respuesta(this.estado);
        }

        if (bandera) {
            if ("C".equals(tipoCodigo)) {
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
                        this.codigoEstado = "4013";
                        this.estado = new Estado(this.codigoEstado);
                        this.resp = new Respuesta(this.estado);
                        this.rs.close();
                        this.bd.dBDesconeccion();
                    }
                } catch (SQLException ex) {
                    this.codigoEstado = "4012";
                    this.estado = new Estado(this.codigoEstado);
                    this.resp = new Respuesta(this.estado);
                }
            } else {
                if ("T".equals(tipoCodigo)) {
                    try {
                        this.query = this.con.prepareStatement("SELECT * FROM TLSDTA.CLI WHERE CLINDO=?");
                        this.query.setString(1, codigoCliente);
                        this.rs = this.query.executeQuery();
                        if (this.rs.isBeforeFirst()) {
                            while (this.rs.next()) {
                                paso = true;
                                this.codCliente = Integer.toString(this.rs.getInt("CLINUM"));
                                //this.idnCliente = codigo;
                                this.idnCliente = this.rs.getString("CLINDO");
                                this.nomCliente = this.rs.getString("CLINOM");
                                this.apeCliente = this.rs.getString("CLIAPE");
                            }
                            this.rs.close();
                        } else {
                            this.codigoEstado = "4013";
                            this.estado = new Estado(this.codigoEstado);
                            this.resp = new Respuesta(this.estado);
                            this.rs.close();
                            this.bd.dBDesconeccion();
                        }
                    } catch (SQLException ex) {
                        this.codigoEstado = "4012";
                        this.estado = new Estado(this.codigoEstado);
                        this.resp = new Respuesta(this.estado);
                    }
                } else {
                    this.codigoEstado = "4011";
                    this.estado = new Estado(this.codigoEstado);
                    this.resp = new Respuesta(this.estado);
                    this.bd.dBDesconeccion();
                }
            }
        }
        return paso;
    }

    //Metodo que realiza las consultas para devolver el lista de registros.
    private void consulta() {
        // Busqueda de prestamos en mora
        listMora = new ArrayList<>();
        try {
            this.query = this.con.prepareStatement("SELECT "
                    + " CLINUM,"
                    + " PROCTA,"
                    + " PROFMA,"
                    + " PRODUC,"
                    + " PROTIP,"
                    + " PROSTO,"
                    + " MPRODE "
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
            this.codigoEstado = "4012";
            this.estado = new Estado(this.codigoEstado);
            this.resp = new Respuesta(this.estado);
        }

        //Busqueda de tarjetas en mora
        try {
            this.query = this.con.prepareStatement("SELECT "
                    + " CLINUM,"
                    + " TPROCTA,"
                    + " TPROINI,"
                    + " TPRODUC,"
                    + " TPROTIP,"
                    + " TPROSTO,"
                    + " TPRODEC,"
                    + " MPRODE "
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
            this.codigoEstado = "4012";
            this.estado = new Estado(this.codigoEstado);
            this.resp = new Respuesta(this.estado);
        }
    }
}
