/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import java.util.List;

/**
 *
 * @author kamejia
 */
public class Respuesta {
    
    //Propiedades
    private Error error;
    private List<Mora> morosos;

    //Constructors
    public Respuesta(){
    }
    
    public Respuesta(Error error){
        this.error = error;
    }
    
    public Respuesta(Error error, List<Mora> moroso) {
        this.error = error;
        this.morosos =  moroso;
    }

    //Setters
    public void setError(Error error) {
        this.error = error;
    }

    public void setMorosos(List<Mora> morosos) {
        this.morosos = morosos;
    }

    //Getters
    public Error getError() {
        return error;
    }
    
    public List<Mora> getMorosos() {
        return morosos;
    }
}
