package Utilitarios;

/**
 * Clase que representa el estado de la respuesta éxito/error
 *
 * @author kamejia
 */
public class Estado {

    //Properties
    private String codigoEstado; //codigo del estado
    private String descripcion; //descripción del estado
    private String detalleTecnico; //descripción técnica 

    /**
     * Constructor principal para generar el objeto estado
     *
     * @param codigoEstado
     */
    public Estado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
        this.asignarEstado();
    }

    /**
     * Constructor por defecto
     */
    public Estado() {
    }

    /**
     *
     * @param codigoEstado
     */
    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    /**
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *
     * @param detalleTecnico
     */
    public void setDetalleTecnico(String detalleTecnico) {
        this.detalleTecnico = detalleTecnico;
    }

    /**
     *
     * @return codigoEstado
     */
    public String getCodigoEstado() {
        return this.codigoEstado;
    }

    /**
     *
     * @return descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     *
     * @return detalleTecnico
     */
    public String getDetalleTecnico() {
        return this.detalleTecnico;
    }

    private void asignarEstado() {
        switch (this.codigoEstado) {
            case "200":
                this.descripcion = "Éxito";
                this.detalleTecnico = "El proceso se ejecutó correctamente";
                break;
            case "204":
                this.descripcion = "Éxito, sin resultados de la búsqueda";
                this.detalleTecnico = "No hay resultados para la búsqueda";
                break;
            case "401":
                this.descripcion = "Error de permisos de aplicación";
                this.detalleTecnico = "El código de aplicación enviado no tiene permisos";
                break;
            case "402":
                this.descripcion = "Error de conexión con la base de datos";
                this.detalleTecnico = "Es probable que no se haya encontrado el destino de datos";
                break;
            case "403":
                this.descripcion = "El cliente no ha sido encontrado";
                this.detalleTecnico = "El número de cliente que ha proporcionado no existe en el archivo de moras";
                break;
            case "404":
                this.descripcion = "Error de tipo de búsqueda";
                this.detalleTecnico = "Debe enviar un tipo de búsqueda correcto";
                break;
            case "407":
                this.descripcion = "Argumentos vacíos";
                this.detalleTecnico = "Los parámetros deben contener valores correctos";
                break;
        }
    }
}
