package com.forge.controller;

import com.forge.dto.ReqPersonaDto;
import com.forge.exception.NoGuardarException;
import com.forge.imp.PersonaImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/personas")
public class PersonaController {

    @Autowired
    private PersonaImp personaImp;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> guardarPersona(@RequestBody ReqPersonaDto reqPersonaDto){
        ResponseEntity<Object> rs = null;
        try {
            rs = new ResponseEntity<Object>(personaImp.guardarPersona(reqPersonaDto),HttpStatus.OK);

        }catch (NoGuardarException ex){
            ex.printStackTrace();
            rs = new ResponseEntity<Object>(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception ex){
            ex.printStackTrace();
            rs = new ResponseEntity<Object>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return rs;
    }



}
