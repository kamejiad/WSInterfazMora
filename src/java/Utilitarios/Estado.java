package Utilitarios;

/**
 *
 * @author kamejia
 */
public class Estado {

    private String codigoEstado; //codigo del error
    private String descripcion; //descripción del error
    private String detalleTecnico; //explicación técnica 

    //Constructors
    public Estado(String codigoEstado, String descripcion, String detalleTecnico) {
        this.codigoEstado = codigoEstado;
        this.descripcion = descripcion;
        this.detalleTecnico = detalleTecnico;
    }

    public Estado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
        this.asignarEstado();
    }

    public Estado() {
    }

    //Setters
    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDetalleTecnico(String detalleTecnico) {
        this.detalleTecnico = detalleTecnico;
    }

    //Getters
    public String getCodigoEstado() {
        return codigoEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDetalleTecnico() {
        return detalleTecnico;
    }

    private void asignarEstado() {
        switch (this.codigoEstado) {
            case "4012":
                this.descripcion = "Error de conexión con la base de datos";
                this.detalleTecnico = "Es probable que no se haya encontrado el destino de datos";
                break;
            case "4011":
                this.descripcion = "Error de tipo de busqueda";
                this.detalleTecnico = "Debe enviar un tipo de busqueda correcto";
                break;
            case "4013":
                this.descripcion = "El cliente no ha sido encontrado";
                this.detalleTecnico = "El numero de cliente que ha proporcionado no existe en el archivo de moras";
                break;
            case "200":
                this.descripcion = "Éxito";
                this.detalleTecnico = "El proceso se ejecutó correctamente";
                break;
            case "201":
                this.descripcion = "Éxito, sin resultados de la busqueda";
                this.detalleTecnico = "No hay resultados para la busqueda";
                break;
            case "405":
                this.descripcion = "Error de permisos de aplicación";
                this.detalleTecnico = "El codigo de aplicación enviado no tiene permisos";
                break;
            case "407":
                this.descripcion = "Argumentos vacios";
                this.detalleTecnico = "Los parametros deben contener valores correctos";
                break;
        }
    }

}
