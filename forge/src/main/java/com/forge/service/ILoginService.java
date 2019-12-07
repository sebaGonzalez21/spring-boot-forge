package com.forge.service;

import com.forge.dto.ReqLoginDto;
import com.forge.dto.ResponseLoginDto;
import com.forge.exception.NoActualizarException;
import com.forge.exception.NoEncontradoException;
import com.forge.model.Login;

import java.util.List;

//Interface con las funciones de metodos a utilizar
public interface ILoginService {

    ResponseLoginDto guardarLogin(ReqLoginDto reqLoginDto) throws Exception;
    boolean validarSession(ReqLoginDto reqLoginDto) throws Exception;
    Login buscarPorId(Long id) throws Exception;
    List<Login> listarLogin()throws Exception;
    boolean eliminarLogin(Long id) throws Exception;
    ResponseLoginDto actualizarLogin(Long id,ReqLoginDto reqLoginDto) throws Exception;

}
