package com.forge.model;

import javax.persistence.*;
/**
 * Sebastian Gonzalez
 * sebastian_gonza_@hotmail.com
 */

@Entity
@Table(name = "Rol")
public class Rol {

    @Id
    @Column(name = "id_rol")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_rol", unique = true, nullable = false)
    private String tipo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
