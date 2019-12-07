package com.forge.mapping;

import com.forge.dto.ReqRolDto;
import com.forge.dto.ResponseRolDto;
import com.forge.model.Rol;
import com.forge.util.Constant;
import org.springframework.stereotype.Service;

import java.util.Optional;
/**
 * Sebastian Gonzalez
 * sebastian_gonza_@hotmail.com
 */

@Service
public class MappingObjetosRol {


    /**
     * Transforma objeto de reqRolDto
     * @param reqRolDto
     * @return
     * @throws Exception
     */
    //clase encargada de transformar un objeto de tipo ReqDto a Rol
    public Rol transformDtoIntoModel(ReqRolDto reqRolDto ) throws Exception {
        //objeto a guardar en la base de datos
        Rol rolLocal = null;
        try {
            if(null != reqRolDto){
                rolLocal = new Rol();
                rolLocal.setId(null);
                rolLocal.setTipo(reqRolDto.getTipoRolDto().toUpperCase());
            }

        }catch (Exception ex){
            ex.printStackTrace();
            throw new Exception(Constant.ERROR_SISTEMA);
        }
        return rolLocal;
    }

    /**
     * Transforma Objeto model a dto
     * @param rol
     * @return
     * @throws Exception
     */
    //clase encargada de transformar un objeto de tipo ReqDto a Rol
    public ResponseRolDto transformModelToDto(Rol rol ) throws Exception {
        //objeto a guardar en la base de datos
        ResponseRolDto rolLocal = null;
        try {

            if(null != rol){
                rolLocal = new ResponseRolDto();
                rolLocal.setIdDto(rol.getId());
                rolLocal.setTipoRolDto(rol.getTipo());
            }
        }catch (Exception ex){
            ex.printStackTrace();
            throw new Exception(Constant.ERROR_SISTEMA);
        }
        return rolLocal;
    }

    /**
     *
     * @param rolOptional
     * @return
     * @throws Exception
     */
    //clase encargada de transformar un objeto de tipo ReqDto a Rol
    public Rol transformOptionalToModel(Optional<Rol> rolOptional ) throws Exception {
        //objeto a guardar en la base de datos
        Rol rolLocal = null;
        try {

            if(rolOptional.isPresent()){
                rolLocal = new Rol();
                rolLocal.setId(rolOptional.get().getId());
                rolLocal.setTipo(rolOptional.get().getTipo());
            }
        }catch (Exception ex){
            ex.printStackTrace();
            throw new Exception(Constant.ERROR_SISTEMA);
        }
        return rolLocal;
    }
}
