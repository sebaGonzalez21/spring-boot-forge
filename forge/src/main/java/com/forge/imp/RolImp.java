package com.forge.imp;

import com.forge.dto.ReqRolDto;
import com.forge.dto.ResponseRolDto;
import com.forge.exception.NoEncontradoException;
import com.forge.exception.NoGuardarException;
import com.forge.mapping.MappingObjetosRol;
import com.forge.model.Rol;
import com.forge.repository.RolRepository;
import com.forge.service.IRolService;
import com.forge.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Sebastian Gonzalez
 * sebastian_gonza_@hotmail.com
 */

@Service
public class RolImp implements IRolService {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private MappingObjetosRol mappingObjetosRol;

    /**
     * Metodo para guardar rol
     * @param reqRolDto
     * @return
     * @throws Exception
     */
    @Override
    public ResponseRolDto guardarRol(ReqRolDto reqRolDto) throws Exception {
        ResponseRolDto responseRolDto =  null;
        try {
                if(null != reqRolDto.getTipoRolDto() && null != reqRolDto){
                    //response un metodo con el objeto transformado, lo guarda y lo vuelve a transformar para asignarlo y enviarlo como respuesta
                    responseRolDto = mappingObjetosRol.transformModelToDto(rolRepository.save(mappingObjetosRol.transformDtoIntoModel(reqRolDto)));
                }else{
                    throw new NoGuardarException(Constant.ERROR_GUARDAR);
                }

        }catch (NoGuardarException ex){
            ex.printStackTrace();
            throw new NoGuardarException(ex.getMessage());

        }catch (Exception ex){
            ex.printStackTrace();
            throw new Exception(Constant.ERROR_SISTEMA);
        }
        return responseRolDto;
    }

    /**
     * Metodo para buscar Rol por id
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Rol buscarRolporId(Long id) throws Exception {
        Rol rol =  null;
        try {
             rol = mappingObjetosRol.transformOptionalToModel(rolRepository.findById(id));
             if(null == rol){
                 throw new NoEncontradoException(Constant.ERROR_NO_ENCONTRADO);
             }
        }catch (NoEncontradoException ex){
            throw new NoEncontradoException(ex.getMessage());
        }catch (Exception ex){
            ex.printStackTrace();
            throw new Exception(Constant.ERROR_SISTEMA);
        }
        return rol;
    }


}
