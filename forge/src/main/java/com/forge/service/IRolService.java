package com.forge.service;

import com.forge.dto.ReqRolDto;
import com.forge.dto.ResponseRolDto;
import com.forge.model.Rol;

public interface IRolService {

    ResponseRolDto guardarRol(ReqRolDto reqRolDto) throws Exception;
    Rol buscarRolporId(Long id) throws Exception;
}
