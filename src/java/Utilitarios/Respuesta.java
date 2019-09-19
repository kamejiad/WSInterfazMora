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
    private String estado;
    private String description;
    private List<Mora> morosos;

    public Respuesta(String estado, String description, List<Mora> moroso) {
        this.estado = estado;
        this.description = description;
        this.morosos =  moroso;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMorosos(List<Mora> morosos) {
        this.morosos = morosos;
    }

    public String getEstado() {
        return estado;
    }

    public String getDescription() {
        return description;
    }

    public List<Mora> getMorosos() {
        return morosos;
    }
}
