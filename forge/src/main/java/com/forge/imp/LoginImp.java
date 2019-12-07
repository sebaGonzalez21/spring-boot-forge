//package encargado de logica de negocio
package com.forge.imp;

import com.forge.dto.ReqLoginDto;
import com.forge.dto.ResponseLoginDto;
import com.forge.exception.NoActualizarException;
import com.forge.exception.NoEncontradoException;
import com.forge.exception.NoGuardarException;
import com.forge.exception.NoValidarSesionException;
import com.forge.mapping.MappingObjetosLogin;
import com.forge.model.Login;
import com.forge.repository.LoginRepository;
import com.forge.service.ILoginService;
import com.forge.service.IPbkdf2EncryptService;
import com.forge.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * Sebastian Gonzalez
 * sebastian_gonza_@hotmail.com
 */


//Un servicio que sera llamado desde el controller
@Service //service a lo que se utilizara como logica de negocio
public class LoginImp implements ILoginService {

    // crear una instancia o Injeccion de depencia de interfaz
    @Autowired
    private LoginRepository loginRepository;

    // servicio que se encarga de encryptar la password y guardar en la base de datos
    @Autowired
    private IPbkdf2EncryptService iPbkdf2EncryptService;

    //injeccion a la clase / instancia
    @Autowired
    private MappingObjetosLogin transformarObjetos;//Clase que se encarga de transformar los objetos

    /**
     * Metodo que se encarga de guardar un login
     * @param reqLoginDto
     * @return
     * @throws Exception
     */
    @Override
    public ResponseLoginDto guardarLogin(ReqLoginDto reqLoginDto) throws Exception {
        ResponseLoginDto responseLoginDto;
        Login login;
        try{
                Login validateEmail= loginRepository.findByEmail(reqLoginDto.getEmailDto());
             if(null == validateEmail && reqLoginDto.getPasswordDto().length()>Constant.ZERO){
                 login = new Login();
                 login.setEmail(reqLoginDto.getEmailDto());
                 //se encarga de encriptar la password para posteriormente ir a guardar a la base de datos esa password encriptada
                 login.setPassword(iPbkdf2EncryptService.generarHashPassword(reqLoginDto.getPasswordDto()));

                 //transforma objetos para responder un dto
                 responseLoginDto = transformarObjetos.transformarLoginToResponseDto(loginRepository.save(login));
             }else{
                 //en caso que no cumpla alguna validacion se envia como mensaje
                throw new NoGuardarException(Constant.ERROR_GUARDAR);
             }

       }catch (NoGuardarException ex){
            ex.printStackTrace();
            //enviar excepcion al controlador
            throw new NoGuardarException(ex.getMessage());
        }catch (Exception ex){
            ex.printStackTrace();
            //enviar excepcion al controlador y carga el mensaje
            throw new Exception(Constant.ERROR_SISTEMA);
       }
        return responseLoginDto;
    }

    /**
     * Servicio para validar la sesion
     * @param reqLoginDto
     * @return
     */
    @Override
    public boolean validarSession(ReqLoginDto reqLoginDto) throws Exception {
        Login loginLocal;
        try {
                loginLocal = loginRepository.findByEmail(reqLoginDto.getEmailDto());
                if(null != loginLocal){
                    return iPbkdf2EncryptService.validarPassword(reqLoginDto.getPasswordDto(),loginLocal.getPassword());
                }else{
                    throw new NoValidarSesionException(Constant.ERROR_SESSION);
                }

        }catch (NoValidarSesionException ex){
            ex.printStackTrace();
            throw new NoValidarSesionException(ex.getMessage());

        }catch (Exception ex){
            ex.printStackTrace();
            throw new Exception(Constant.ERROR_SISTEMA);
        }
    }

    /**
     * Servicio para entregar un login por id
     * @param id
     * @return
     */
    @Override
    public Login buscarPorId(Long id) throws Exception {
        //Clase a la que se asigara el objeto
        Login loginLocal;
        try{

            loginLocal = transformarObjetos.transformarOptionaLogin(loginRepository.findById(id));//metodo que se encarga de transformar el objeto que retorna crud repository
            if(null == loginLocal){
                throw new NoEncontradoException(Constant.ERROR_NO_ENCONTRADO);
            }
        }catch (NoEncontradoException ex){
            ex.printStackTrace();
            throw new NoEncontradoException(ex.getMessage());
        }catch (Exception ex){
            ex.printStackTrace();
            throw new Exception(Constant.ERROR_SISTEMA);
        }
        //objeto a retornar
        return loginLocal;
    }

    /**
     * Metodo que entrega la lista de logins
     * @return
     * @throws Exception
     */
    @Override
    public List<Login> listarLogin() throws Exception {
        //Login retornara vacio en caso de no ser encontrado
        List<Login> listLogin = new ArrayList<>();
        try {
             listLogin = loginRepository.findAll();
        }catch (Exception ex){
            ex.printStackTrace();
            throw new Exception(Constant.ERROR_SISTEMA);
        }
        return listLogin;
    }

    /**
     * Metodo para eliminar registro
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean eliminarLogin(Long id) throws Exception {
        try{

            Login loginLocal = transformarObjetos.transformarOptionaLogin(loginRepository.findById(id));//metodo que se encarga de transformar el objeto que retorna crud repository
            if(null == loginLocal){
                throw new NoEncontradoException(Constant.ERROR_NO_ENCONTRADO);
            }else{
                loginRepository.deleteById(id);
                return true;
            }
        }catch (NoEncontradoException ex){
            ex.printStackTrace();
            throw new NoEncontradoException(ex.getMessage());
        }catch (Exception ex){
            ex.printStackTrace();
            throw new Exception(Constant.ERROR_SISTEMA);
        }
    }

    /**
     * Metodo para actualizar login
     * @param id
     * @param reqLoginDto
     * @return
     * @throws Exception
     */
    @Override
    public ResponseLoginDto actualizarLogin(Long id, ReqLoginDto reqLoginDto) throws Exception {
        ResponseLoginDto responseLoginDto = null;
        try{

            Login login = buscarPorId(id);
            if(null != reqLoginDto && null != reqLoginDto.getEmailDto() && null != reqLoginDto.getPasswordDto()){
                login.setEmail(reqLoginDto.getEmailDto());
                login.setPassword(iPbkdf2EncryptService.generarHashPassword(reqLoginDto.getPasswordDto()));
                responseLoginDto = transformarObjetos.transformarLoginToResponseDto(loginRepository.save(login));
            }else{
                throw new NoActualizarException(Constant.ERROR_ACTUALIZAR);
            }

        }catch (NoActualizarException ex){
            ex.printStackTrace();
            throw new NoActualizarException(ex.getMessage());
        }catch (NoEncontradoException ex){
            ex.printStackTrace();
            throw new NoEncontradoException(ex.getMessage());
        }catch (Exception ex){
            ex.printStackTrace();
            throw new Exception(Constant.ERROR_SISTEMA);
        }
        return responseLoginDto;
    }


}
