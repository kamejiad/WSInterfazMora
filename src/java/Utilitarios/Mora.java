package Utilitarios;

/**
 * Clase para almacenar la información de los productos en mora de un cliente
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
    private double saldoTotal;      //Saldo total
    private double deudaExigible;   //Deuda esxigible: Capital

    /**
     * Constructor principal para objetos mora prestamos
     *
     * @param numCuenta
     * @param fechaInicio
     * @param producto
     * @param tipoProducto
     * @param descProducto
     * @param saldoTotal
     * @param deudaExigible
     */
    public Mora(String numCuenta, String fechaInicio, String producto, String tipoProducto, String descProducto, double saldoTotal, double deudaExigible) {
        this.numCuenta = numCuenta;
        this.fechaInicio = fechaInicio;
        this.producto = producto;
        this.tipoProducto = tipoProducto;
        this.descProducto = descProducto;
        this.saldoTotal = saldoTotal;
        this.deudaExigible = deudaExigible;
    }

    /**
     * Construntor principal para objetos mora tarjetas
     *
     * @param numCuenta
     * @param fechaInicio
     * @param producto
     * @param tipoProducto
     * @param descProducto
     * @param saldoTotal
     */
    public Mora(String numCuenta, String fechaInicio, String producto, String tipoProducto, String descProducto, double saldoTotal) {
        this.numCuenta = numCuenta;
        this.fechaInicio = fechaInicio;
        this.producto = producto;
        this.tipoProducto = tipoProducto;
        this.descProducto = descProducto;
        this.saldoTotal = saldoTotal;
    }

    /**
     * Constructor vacío
     */
    public Mora() {
    }

    /**
     *
     * @param numCuenta
     */
    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    /**
     *
     * @param fechaInicio
     */
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     *
     * @param producto
     */
    public void setProducto(String producto) {
        this.producto = producto;
    }

    /**
     *
     * @param tipoProducto
     */
    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    /**
     *
     * @param descProducto
     */
    public void setDescProducto(String descProducto) {
        this.descProducto = descProducto;
    }

    /**
     *
     * @param saldoTotal
     */
    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    /**
     *
     * @param deudaExigible
     */
    public void setDeudaExigible(double deudaExigible) {
        this.deudaExigible = deudaExigible;
    }

    /**
     *
     * @return numCuenta
     */
    public String getNumCuenta() {
        return this.numCuenta;
    }

    /**
     *
     * @return fechaInicio
     */ 
    public String getFechaInicio() {
        return this.fechaInicio;
    }

    /**
     *
     * @return producto
     */
    public String getProducto() {
        return this.producto;
    }

    /**
     *
     * @return tipoProducto
     */
    public String getTipoProducto() {
        return this.tipoProducto;
    }

    /**
     *
     * @return descProducto
     */
    public String getDescProducto() {
        return this.descProducto;
    }

    /**
     *
     * @return saldoTotal
     */
    public double getSaldoTotal() {
        return this.saldoTotal;
    }

    /**
     *
     * @return deudaExigible
     */
    public double getDeudaExigible() {
        return this.deudaExigible;
    }

    @Override
    public String toString() {
        return "Mora{" + ", numCuenta=" + numCuenta + ", fechaInicio=" + fechaInicio + ", producto=" + producto + ", tipoProducto=" + tipoProducto + ", saldoTotal=" + saldoTotal + ", deudaExigible=" + deudaExigible + '}';
    }
}
