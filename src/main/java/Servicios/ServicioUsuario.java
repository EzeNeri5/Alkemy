package Servicios;

import Entidades.Usuario;
import Repositorio.RepositorioUsuario;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioUsuario {

    @Autowired
    private RepositorioUsuario ru;
    @Autowired
    private ServicioMail sm;
    
    @Transactional
    public Usuario crear(String nombre, String clave, String clave2, String email) throws Exception{
    
        validar(nombre, clave,clave2, email);        
        Usuario u = new Usuario();
        
        u.setNombre(nombre);
        u.setClave(clave);
        u.setEmail(email);
        
        ru.save(u);
        
        sm.enviar("Binevenidos al Mundo de Disney", "Mundo de Disney", u.getEmail());
        return u;
    }
    
    @Transactional
    public Usuario modificar(String buscar, String nombre, String clave, String clave2, String email) throws Exception{

        validar(nombre, clave, clave2, email); 
        Usuario u = ru.buscarPorNombre(buscar);
        if (u != null) {
        u.setNombre(nombre);
        u.setClave(clave);
        u.setEmail(email);
        
        ru.save(u);
        return u;}
        else {return null;}
    }
    
    @Transactional()
    public void eliminarUsuario(String id) throws Exception {
        Optional <Usuario> respuesta = ru.findById(id);
        if (respuesta.isPresent()) {
            ru.deleteById(id);
        }else {
            throw new Exception("No se encontro el ID solicitado");
        }    }
    @Transactional
    public void validar(String nombre, String clave, String clave2, String email) throws Exception{
    
        if (nombre == null || nombre.isEmpty() || nombre.contains(" ")) {
            throw new Exception("Debe tener un nombre valido");        }

        if (clave == null || clave.trim().isEmpty() || clave.contains(" ") ||clave.length() < 8 || clave != clave2){ 
            throw new Exception("Debe tener una clave valida");        }

        if (email == null || email.trim().isEmpty() || email.contains(" ") || email.contains("@") || email.contains(".com")){ 
            throw new Exception("Debe tener un email valido");        }
    }
}
