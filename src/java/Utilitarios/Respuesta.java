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
    private Estado estado;
    private ClienteCabecera cliente;
    private List<Mora> moroso;

    //Constructors
    public Respuesta(){
    }
    
    public Respuesta(Estado estado){
        this.estado = estado;
    }
    
    public Respuesta(Estado estado, ClienteCabecera cliente){
        this.estado = estado;
        this.cliente = cliente;
    }
    
    public Respuesta(Estado estado, ClienteCabecera cliente, List<Mora> moroso) {
        this.estado = estado;
        this.cliente = cliente;
        this.moroso =  moroso;
    }

    //Setters
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setCliente(ClienteCabecera cliente) {
        this.cliente = cliente;
    }

    public void setMoroso(List<Mora> moroso) {
        this.moroso = moroso;
    }

    //Getters
    public Estado getEstado() {
        return estado;
    }

    public ClienteCabecera getCliente() {
        return cliente;
    }
    
    public List<Mora> getMoroso() {
        return moroso;
    }
}
