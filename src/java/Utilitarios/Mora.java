//Clase para almacenar la información de los clientes en Mora si estos existen.

package Utilitarios;

/**
 *
 * @author kamejia
 */
public class Mora {
    
    //Propiedades del objeto
    private String numCuenta;       //Número de Cuenta del Préstamo/Tarjeta
    private String fechaInicio;     //Fecha Inicio de Mora
    private String producto;        //Producto
    private String tipoProducto;    //Tipo de producto
    private int saldoTotal;         //Saldo total
    private int deudaExigible;      //Deuda esxigible: Capital

    //Constructor principal
    public Mora(String numCuenta, String fechaInicio,String producto, String tipoProducto, int saldoTotal, int deudaExigible) {
        this.numCuenta = numCuenta;
        this.fechaInicio = fechaInicio;
        this.producto = producto;
        this.tipoProducto = tipoProducto;
        this.saldoTotal = saldoTotal;
        this.deudaExigible = deudaExigible;
    }
    
    //Constructor vacio
    public Mora(){}

    //Metodos Setters
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

    public void setSaldoTotal(int saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public void setDeudaExigible(int deudaExigible) {
        this.deudaExigible = deudaExigible;
    }

    //Metodos Getters
    public String getNumCuenta() {
        return numCuenta;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getProducto() {
        return producto;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public int getSaldoTotal() {
        return saldoTotal;
    }

    public int getDeudaExigible() {
        return deudaExigible;
    }

    //Metodo toString muestra información del objeto
    @Override
    public String toString() {
        return "Mora{" + ", numCuenta=" + numCuenta + ", fechaInicio=" + fechaInicio + ", producto=" + producto + ", tipoProducto=" + tipoProducto + ", saldoTotal=" + saldoTotal + ", deudaExigible=" + deudaExigible + '}';
    }
}
