package com.forge.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
/**
 * Sebastian Gonzalez
 * sebastian_gonza_@hotmail.com
 */

@Entity
@Table(name = "Persona")
public class Persona {
    /*
        name: Indica el nombre con el que se deberá de crear la columna dentro de la tabla.
        referencedColumnName: Se utiliza para indicar sobre que columna se realizará el Join de la otra tabla. Por lo general no se suele utilizar, pues JPA asume que la columna es el ID de la Entidad objetivo.
        unique: Crea un constraints en la tabla para impedir valores duplicados (default false).
        nullable: Crea un constraints en la tabla para impedir valores nulos (default true).
        insertable: Le indica a JPA si este valor deberá guardarse en la operación de inserción (default true)
        updatable: Le indica a JPA si el valor deberá actualizarse durante el proceso de actualización (default true)
        columnDefinition: Esta propiedad es utiliza para indicar la instrucción SQL que se deberá utilizar la crear la columna en la base de datos. Esta nos ayuda a definir exactamente como se creará la columna sin depender de la configuración de JPA.
        table: Le indicamos sobre que tabla deberá realizar el JOIN, normalmente no es utilizada, pues JPA asume la tabla por medio de la entidad objetivo.
        foreignKey: Le indica a JPA si debe de crear el Foreign Key, esta propiedad recibe uno de los siguientes valores CONSTRAINT , NO_CONSTRAINT , PROVIDER_DEFAULT  definidos en la enumeración javax.persistence.ForeignKey .
     */

    @Id
    @Column(name = "id_persona")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;
    @OneToOne // indica relacion uno a uno y el joinColumn el nombbre exacto de la columna
    @JoinColumn(name = "id_login", updatable = false, nullable = false)
    private Login login;
    @OneToOne
    @JoinColumn(name = "id_rol", updatable = false, nullable = false)
    private Rol rol;
    @Column(name = "nombre_persona", nullable = false)
    private String nombrePersona;
    @Column(name = "apellido_persona", nullable = false)
    private String apellidoPersona;
    @Column(name = "rut_persona", nullable = false,unique = true)
    private String rutPersona;
    @Column(name = "fecha_creacion", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date fechaCreacion;
    @Column(name = "fecha_actualizacion")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date fechaActualizacion;
    @Column(name = "active", nullable = false)
    private byte active;

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellidoPersona() {
        return apellidoPersona;
    }

    public void setApellidoPersona(String apellidoPersona) {
        this.apellidoPersona = apellidoPersona;
    }

    public String getRutPersona() {
        return rutPersona;
    }

    public void setRutPersona(String rutPersona) {
        this.rutPersona = rutPersona;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }
}
