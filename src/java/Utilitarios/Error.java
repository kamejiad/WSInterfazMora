
package Utilitarios;

/**
 *
 * @author kamejia
 */
public class Error {
    private String estado; //codigo del error
    private String descripcion; //descripción del error
    private String detalleTecnico; //explicación técnica 

    //Constructors

    public Error(String codigo, String descripcion, String detalleTecnico) {
        this.estado = codigo;
        this.descripcion = descripcion;
        this.detalleTecnico = detalleTecnico;
    }

    public Error() {
    }

    //Setters
    public void setCodigo(String codigo) {
        this.estado = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDetalleTecnico(String detalleTecnico) {
        this.detalleTecnico = detalleTecnico;
    }


    //Getters
    public String getCodigo() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDetalleTecnico() {
        return detalleTecnico;
    }

}
