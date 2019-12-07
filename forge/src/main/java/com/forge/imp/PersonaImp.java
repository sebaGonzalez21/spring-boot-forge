//package encargado de logica de negocio
package com.forge.imp;

import com.forge.dto.ReqPersonaDto;
import com.forge.dto.ResponsePersonaDto;
import com.forge.exception.NoGuardarException;
import com.forge.mapping.MappingObjectosPersona;
import com.forge.model.Login;
import com.forge.model.Persona;
import com.forge.model.Rol;
import com.forge.repository.PersonaRepository;
import com.forge.service.IPersonaService;
import com.forge.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
/**
 * Sebastian Gonzalez
 * sebastian_gonza_@hotmail.com
 */

@Service
public class PersonaImp implements IPersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private RolImp rolImp;

    @Autowired
    private LoginImp loginImp;

    @Autowired
    private MappingObjectosPersona mappingObjectosPersona;

    /**
     * Metodo para guardar persona
     * @param persona
     * @return
     * @throws Exception
     */
    @Override
    public ResponsePersonaDto guardarPersona(ReqPersonaDto persona) throws Exception {
        Persona personaLocal;
        ResponsePersonaDto personaDto;
        try {
            //valida que el id de login entregado exista y retorna boolean true si existe
            Login login = loginImp.buscarPorId(persona.getIdLoginDto());
            //valida que el rol entregado exista y retorna boolean true si existe
            Rol rol = rolImp.buscarRolporId(persona.getIdRolDto());

            //se consulta si los valores realmente existen y cumplen la condicion se crea la persona
            if(null != login && null != rol && persona.getIdPersonaDto() == null){
                persona.setFechaCreacionDto(new Date());
                persona.setActiveDto((byte)1);
                persona.setFechaActualizacionDto(null);
                //se invoca clase que transformara el objeto y luego lo guardara en el repository
                personaLocal = personaRepository.save(mappingObjectosPersona.transformDtoIntoModel(persona,login,rol));
                //se transforma a objeto para enviar a controller y el front
                personaDto = mappingObjectosPersona.transformModelIntoDtoResponse(personaLocal);

            }else{
                //excepcion personalizada
                throw new NoGuardarException(Constant.ERROR_GUARDAR);
            }

        }catch (NoGuardarException ex){
            ex.printStackTrace();
            throw new NoGuardarException(ex.getMessage());
        }catch (Exception ex){
            ex.printStackTrace();
            throw new Exception(Constant.ERROR_SISTEMA);
        }
        return personaDto;
    }






}
