
package Utilitarios;

/**
 *
 * @author kamejia
 */
public class Error {
    private String codigo; //codigo del error
    private String descripcion; //descripción del error
    private String detalleTecnico; //explicación técnica 

    //Constructors

    public Error(String codigo, String descripcion, String detalleTecnico) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.detalleTecnico = detalleTecnico;
    }

    public Error() {
    }

    //Setters
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDetalleTecnico(String detalleTecnico) {
        this.detalleTecnico = detalleTecnico;
    }


    //Getters
    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDetalleTecnico() {
        return detalleTecnico;
    }

}
