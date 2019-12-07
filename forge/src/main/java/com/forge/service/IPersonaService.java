package com.forge.service;

import com.forge.dto.ReqPersonaDto;
import com.forge.dto.ResponsePersonaDto;

public interface IPersonaService {
    ResponsePersonaDto guardarPersona(ReqPersonaDto persona) throws Exception;
}
