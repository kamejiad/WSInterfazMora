package Utilitarios;

/**
 * Clase que representa los datos generales del cliente
 *
 * @author kamejia
 */
public class ClienteCabecera {

    //Properties

    private String numCliente;      //Número de cliente IBS
    private String numIdentidad;    //Número de Documento Cliente (Identidad)
    private String nombre;          //Nombre del cliente
    private String apellido;        //Apellido del cliente/Nombre de la empresa

    /**
     * Constructor principal, usado si el cliente se encuentra
     *
     * @param numCliente
     * @param numIdentidad
     * @param nombre
     * @param apellido
     */
    public ClienteCabecera(String numCliente, String numIdentidad, String nombre, String apellido) {
        this.numCliente = numCliente;
        this.numIdentidad = numIdentidad;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    /**
     * Constructor por defecto
     */
    public ClienteCabecera() {
    }

    /**
     *
     * @param numCliente
     */
    public void setNumCliente(String numCliente) {
        this.numCliente = numCliente;
    }

    /**
     *
     * @param numIdentidad
     */
    public void setNumIdentidad(String numIdentidad) {
        this.numIdentidad = numIdentidad;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @param apellido
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     *
     * @return numCliente
     */
    public String getNumCliente() {
        return this.numCliente;
    }

    /**
     *
     * @return numIdentidad
     */
    public String getNumIdentidad() {
        return this.numIdentidad;
    }

    /**
     *
     * @return nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     *
     * @return apellido
     */
    public String getApellido() {
        return this.apellido;
    }
}
