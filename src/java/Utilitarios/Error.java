
package Utilitarios;

/**
 *
 * @author kamejia
 */
public class Error {
    private String codError; //codigo del error
    private String descError; //descripción del error

    //Constructors
    public Error(String codError, String descError) {
        this.codError = codError;
        this.descError = descError;
    }

    public Error() {
    }

    //Setters
    public void setCodError(String codError) {
        this.codError = codError;
    }

    public void setDescError(String descError) {
        this.descError = descError;
    }

    //Getters
    public String getCodError() {
        return codError;
    }

    public String getDescError() {
        return descError;
    } 
}
