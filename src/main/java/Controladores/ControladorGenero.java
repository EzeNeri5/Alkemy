
package Controladores;

import Entidades.Pelicula;
import Repositorio.RepositorioGenero;
import Servicios.ServicioGenero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/genero")
public class ControladorGenero {
      
    @Autowired
    ServicioGenero servGenero;
    
    @Autowired
    RepositorioGenero repoGenero;

    @GetMapping("/crearGenero")
    public String crearGenero() throws Exception {
        Pelicula BlancaNieves = null; // Prueba
        servGenero.crear("www.disney.com/foto", "Comedia", BlancaNieves); //Prueba
        return "crearGenero.html";
    }
  
    @PostMapping("/crearGenero")
    public String crearGenero(ModelMap modelo,@RequestParam String foto,@RequestParam String nombre, @RequestParam Pelicula pelicula) {
        try {
            servGenero.crear(foto, nombre, pelicula);
            modelo.put("exito", "Guardado con exito");
            return "crearGenero.html";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "crearGenero.html"; 
        }   }

    @GetMapping("/modificar/{id}")
    public String modificarGenero(@PathVariable String buscar, ModelMap modelo) throws Exception {
        modelo.put("producto", repoGenero.buscarPorNombre(buscar));
        return "modificarGenero.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificarGenero(ModelMap modelo, @PathVariable String buscar, @RequestParam String foto,@RequestParam String nombre, @RequestParam Pelicula pelicula) throws Exception {
        try {
            servGenero.modificar(buscar, foto, nombre, pelicula);
            return "redirect:/index";
        } catch (Exception e) {
            modelo.put("error", "No se pudo modificar el Genero");
            modelo.put("genero", repoGenero.buscarPorNombre(buscar));
            return "modificarGenero.html";
        }   }

     @GetMapping("/eliminar/{id}")
    public String eliminarGenero(@PathVariable String id){
        try {
            servGenero.eliminarGenero(id);
            return "redirect:/index";
        } catch (Exception e) {
            return "redirect:/index";
        }    }
}
