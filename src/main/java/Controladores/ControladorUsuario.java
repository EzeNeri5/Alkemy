
package Controladores;

import Repositorio.RepositorioUsuario;
import Servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class ControladorUsuario {
  
    @Autowired
    ServicioUsuario servUsu;
    
    @Autowired
    RepositorioUsuario repoUsu;
    
    @GetMapping("/crearUsuario")
    public String crearUsuario() throws Exception{
        servUsu.crear("Usuario", "clave", "clave", "email"); // prueba
        return "crearUsuario.html";
    }
        
    @PostMapping("/crearUsuario")
    public String crearUsuario (ModelMap model, @RequestParam String nombre, @RequestParam String clave1, @RequestParam String clave2, @RequestParam String email) throws Exception{
    
        try{
        servUsu.crear(nombre, clave2, clave2, email);
        model.put("exito", "Guardado con exito");
        return "crearUsuario.html";
        }
        catch (Exception e){
            model.put("error", e.getMessage());
            return "crearUsuario.html";
        }  }
    
    @GetMapping("/editarUsuario")
    public String editarUsuario(@PathVariable String buscar, ModelMap model) throws Exception {
        model.put("pelicula", repoUsu.buscarPorNombre(buscar));
        return "editarUsuario.html";
    }
    
    @PostMapping("/editarUsuario")
    public String editarUsuario(ModelMap model, @RequestParam String buscar, @RequestParam String nombre, @RequestParam String clave1, @RequestParam String clave2, @RequestParam String email) throws Exception{
        try {
            servUsu.modificar(buscar, nombre, clave2, clave2, email);
            return "redirect:/index";
        } catch (Exception e) {
            model.put("error", "No se puede modificar el Usuario");
            model.put("usuario", repoUsu.buscarPorNombre(nombre));
            return "editarUsuario.html";
        }   }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable String buscar){
        try {
            servUsu.eliminarUsuario(buscar);
            return "redirect:/index";
        } catch (Exception e) {
            return "redirect:/index";
        }    }
}
