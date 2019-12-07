package com.forge.dto;
import java.util.Date;
/**
 * Sebastian Gonzalez
 * sebastian_gonza_@hotmail.com
 */

public class ReqPersonaDto {

    private Long idPersonaDto;
    private Long idLoginDto;
    private Long idRolDto;
    private String nombrePersonaDto;
    private String apellidoPersonaDto;
    private String rutPersonaDto;
    private Date fechaCreacionDto;
    private Date fechaActualizacionDto;
    private byte activeDto;

    public Long getIdPersonaDto() {
        return idPersonaDto;
    }

    public void setIdPersonaDto(Long idPersonaDto) {
        this.idPersonaDto = idPersonaDto;
    }

    public Long getIdLoginDto() {
        return idLoginDto;
    }

    public void setIdLoginDto(Long idLoginDto) {
        this.idLoginDto = idLoginDto;
    }

    public Long getIdRolDto() {
        return idRolDto;
    }

    public void setIdRolDto(Long idRolDto) {
        this.idRolDto = idRolDto;
    }

    public String getNombrePersonaDto() {
        return nombrePersonaDto;
    }

    public void setNombrePersonaDto(String nombrePersonaDto) {
        this.nombrePersonaDto = nombrePersonaDto;
    }

    public String getApellidoPersonaDto() {
        return apellidoPersonaDto;
    }

    public void setApellidoPersonaDto(String apellidoPersonaDto) {
        this.apellidoPersonaDto = apellidoPersonaDto;
    }

    public String getRutPersonaDto() {
        return rutPersonaDto;
    }

    public void setRutPersonaDto(String rutPersonaDto) {
        this.rutPersonaDto = rutPersonaDto;
    }

    public Date getFechaCreacionDto() {
        return fechaCreacionDto;
    }

    public void setFechaCreacionDto(Date fechaCreacionDto) {
        this.fechaCreacionDto = fechaCreacionDto;
    }

    public Date getFechaActualizacionDto() {
        return fechaActualizacionDto;
    }

    public void setFechaActualizacionDto(Date fechaActualizacionDto) {
        this.fechaActualizacionDto = fechaActualizacionDto;
    }

    public byte getActiveDto() {
        return activeDto;
    }

    public void setActiveDto(byte activeDto) {
        this.activeDto = activeDto;
    }
}
