/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

/**
 *
 * @author kamejia
 */
public class ClienteCabecera {
    //Properties
    private String numCliente;         //Número de cliente
    private String numIdentidad;    //Número de Documento Cliente (Identidad)
    private String nombre;          //Nombre del cliente
    private String apellido;        //apellido del cliente/nombre de la empresa

    //Constructors
    public ClienteCabecera(String numCliente, String numIdentidad, String nombre, String apellido) {
        this.numCliente = numCliente;
        this.numIdentidad = numIdentidad;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public ClienteCabecera() {
    }

    //Setters
    public void setNumCliente(String numCliente) {
        this.numCliente = numCliente;
    }

    public void setNumIdentidad(String numIdentidad) {
        this.numIdentidad = numIdentidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    //Getters
    public String getNumCliente() {
        return numCliente;
    }

    public String getNumIdentidad() {
        return numIdentidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
    
    
}
