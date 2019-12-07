package com.forge.controller;

import com.forge.dto.ReqLoginDto;
import com.forge.exception.NoActualizarException;
import com.forge.exception.NoEncontradoException;
import com.forge.exception.NoGuardarException;
import com.forge.exception.NoValidarSesionException;
import com.forge.imp.LoginImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// anotacion para definir rest y el controlador

@CrossOrigin(origins = "*") //anotacion para permitir peticiones desde un framework angular y no ser rechazado por cors
@RestController //incluye controller y response body para retornar un objeto
@RequestMapping("/api/v1/logins") //url de los servicios api/v1 y en plural
public class LoginController {

    @Autowired
    private LoginImp loginImp;
    //Leer modelo de madurez
    // https://waltermontes.wordpress.com/2014/02/21/modelo-de-madurez-de-richardson-rest/


    //este servicio recibira un post
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> guardarLogin(@RequestBody ReqLoginDto loginDto){
        ResponseEntity<Object> rs = null;
        try {
            //https://developer.mozilla.org/es/docs/Web/HTTP/Status codigos status
            rs = new ResponseEntity<Object>(loginImp.guardarLogin(loginDto), HttpStatus.OK) ;
        }catch (NoGuardarException ex){
            rs = new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE) ;
        }catch (Exception ex){
            ex.printStackTrace();
            rs = new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
        return  rs;
    }

    //este servicio recibira un post
    @RequestMapping(value = "/sessions",method = RequestMethod.POST)
    public ResponseEntity<Object> validarSession(@RequestBody ReqLoginDto loginDto){
        ResponseEntity<Object> rs = null;
        try {
            rs = new ResponseEntity<Object>(loginImp.validarSession(loginDto),HttpStatus.OK);
        }catch (NoValidarSesionException ex){
            ex.printStackTrace();
            rs = new ResponseEntity<Object>(ex.getMessage(), HttpStatus.UNAUTHORIZED) ;
        }catch (Exception ex){
            ex.printStackTrace();
            rs = new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
        return  rs;
    }

    //este servicio recibira un get por path variable
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id){
        ResponseEntity<Object> rs = null;
        try {
            rs = new ResponseEntity<Object>(loginImp.buscarPorId(id),HttpStatus.OK);
        }catch (NoEncontradoException ex){
            ex.printStackTrace();
            rs = new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND) ;
        }catch (Exception ex){
            ex.printStackTrace();
            rs = new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
        return  rs;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> listarLogin(){
        ResponseEntity<Object> rs = null;
        try {
            rs = new ResponseEntity<Object>(loginImp.listarLogin(),HttpStatus.OK);
        }catch (NoEncontradoException ex){
            ex.printStackTrace();
            rs = new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND) ;
        }catch (Exception ex){
            ex.printStackTrace();
            rs = new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
        return  rs;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Object> eliminarLogin(@PathVariable Long id){
        ResponseEntity<Object> rs = null;
        try {
            rs = new ResponseEntity<Object>(loginImp.eliminarLogin(id),HttpStatus.OK);
        }catch (NoEncontradoException ex){
            ex.printStackTrace();
            rs = new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND) ;
        }catch (Exception ex){
            ex.printStackTrace();
            rs = new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
        return  rs;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Object> actualizarLogin(@PathVariable Long id,@RequestBody ReqLoginDto reqLoginDto){
        ResponseEntity<Object> rs = null;
        try {
            rs = new ResponseEntity<Object>(loginImp.actualizarLogin(id,reqLoginDto),HttpStatus.OK);
        }catch (NoActualizarException ex){
            ex.printStackTrace();
            rs = new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE) ;
        }catch (NoEncontradoException ex){
            ex.printStackTrace();
            rs = new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND) ;
        }catch (Exception ex){
            ex.printStackTrace();
            rs = new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
        return  rs;
    }
}
