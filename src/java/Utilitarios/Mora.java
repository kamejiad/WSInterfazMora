//Clase para almacenar la información de los clientes en Mora si estos existen.

package Utilitarios;

/**
 *
 * @author kamejia
 */
public class Mora {
    
    //Properties
    private String numCuenta;       //Número de Cuenta del Préstamo/Tarjeta
    private String fechaInicio;     //Fecha Inicio de Mora
    private String producto;        //Producto
    private String tipoProducto;    //Tipo de producto
    private String descProducto;    //Descripción del producto
    private double saldoTotal;         //Saldo total
    private double deudaExigible;      //Deuda esxigible: Capital

    //Constructors
    public Mora(String numCuenta, String fechaInicio,String producto, String tipoProducto, String descProducto, double saldoTotal, double deudaExigible) {
        this.numCuenta = numCuenta;
        this.fechaInicio = fechaInicio;
        this.producto = producto;
        this.tipoProducto = tipoProducto;
        this.descProducto = descProducto;
        this.saldoTotal = saldoTotal;
        this.deudaExigible = deudaExigible;
    }

    public Mora(String numCuenta, String fechaInicio, String producto, String tipoProducto, String descProducto, double saldoTotal) {
        this.numCuenta = numCuenta;
        this.fechaInicio = fechaInicio;
        this.producto = producto;
        this.tipoProducto = tipoProducto;
        this.descProducto = descProducto;
        this.saldoTotal = saldoTotal;
    }
    
    //Empty constructor
    public Mora(){}

    //Setters
    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
    
    public void setDescProducto(String descProducto){
        this.descProducto = descProducto;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public void setDeudaExigible(double deudaExigible) {
        this.deudaExigible = deudaExigible;
    }

    //Getters
    public String getNumCuenta() {
        return this.numCuenta;
    }

    public String getFechaInicio() {
        return this.fechaInicio;
    }

    public String getProducto() {
        return this.producto;
    }

    public String getTipoProducto() {
        return this.tipoProducto;
    }
    
    public String getDescProducto(){
        return this.descProducto;
    }

    public double getSaldoTotal() {
        return this.saldoTotal;
    }

    public double getDeudaExigible() {
        return this.deudaExigible;
    }

    //toString
    @Override
    public String toString() {
        return "Mora{" + ", numCuenta=" + numCuenta + ", fechaInicio=" + fechaInicio + ", producto=" + producto + ", tipoProducto=" + tipoProducto + ", saldoTotal=" + saldoTotal + ", deudaExigible=" + deudaExigible + '}';
    }
}
