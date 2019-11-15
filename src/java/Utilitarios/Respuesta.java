package Utilitarios;

import java.util.List;

/**
 * Clase que representa la respuesta que retorna el servicio
 *
 * @author kamejia
 */
public class Respuesta {

    //Properties
    private Estado estado;              //Objeto que representa el estado de la respuesta
    private ClienteCabecera cliente;    //Objeto que representa los datos generales del cliente
    private List<Mora> mora;            //Objeto que almacena la lista de productos en mora para el cliente

    /**
     * Constructor por defecto
     */
    public Respuesta() {
    }

    /**
     * Constructor usado cuando no hay resultados del servicio y se retorna solo
     * el estado
     *
     * @param estado
     */
    public Respuesta(Estado estado) {
        this.estado = estado;
    }

    /**
     * Constructor usado para retornar solo el estado y la información general
     * del cliente
     *
     * @param estado
     * @param cliente
     */
    public Respuesta(Estado estado, ClienteCabecera cliente) {
        this.estado = estado;
        this.cliente = cliente;
    }

    /**
     * constructor usado en caso de éxito del servicio
     *
     * @param estado
     * @param cliente
     * @param mora
     */
    public Respuesta(Estado estado, ClienteCabecera cliente, List<Mora> mora) {
        this.estado = estado;
        this.cliente = cliente;
        this.mora = mora;
    }

    /**
     *
     * @param estado
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     *
     * @param cliente
     */
    public void setCliente(ClienteCabecera cliente) {
        this.cliente = cliente;
    }

    /**
     *
     * @param mora
     */
    public void setMora(List<Mora> mora) {
        this.mora = mora;
    }

    /**
     *
     * @return estado
     */
    public Estado getEstado() {
        return this.estado;
    }

    /**
     *
     * @return cliente
     */
    public ClienteCabecera getCliente() {
        return this.cliente;
    }

    /**
     *
     * @return mora
     */
    public List<Mora> getMora() {
        return this.mora;
    }
}
