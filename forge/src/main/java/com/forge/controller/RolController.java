package com.forge.controller;

import com.forge.dto.ReqRolDto;
import com.forge.exception.NoGuardarException;
import com.forge.imp.RolImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/roles")
public class RolController {

    @Autowired
    private RolImp rolImp;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> guardarRol(@RequestBody ReqRolDto reqRolDto){
        ResponseEntity<Object> rs = null;
        try {
            rs = new ResponseEntity<Object>(rolImp.guardarRol(reqRolDto), HttpStatus.OK);
        }catch (NoGuardarException ex){
            ex.printStackTrace();
            rs = new ResponseEntity<Object>(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception ex){
            ex.printStackTrace();
            rs = new ResponseEntity<Object>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  rs;
    }
}
