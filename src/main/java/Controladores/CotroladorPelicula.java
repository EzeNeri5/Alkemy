package Controladores;

import Servicios.ServicioPelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pelicula")
public class CotroladorPelicula {
    
    @Autowired
    ServicioPelicula servPeli;
    
    @GetMapping("/crearPelicula")
    public String crearPelicula() throws Exception{
        servPeli.crear("www.disney.com/foto", "BlancaNieves", 5); // prueba
        return "crearPelicula.html";
    }
        
    @PostMapping("/crearPelicula")
    public String crearPelicula (ModelMap model, @RequestParam String foto, @RequestParam String titulo, @RequestParam Integer calificacion) throws Exception{
    
        try{
        servPeli.crear(foto, titulo, calificacion);
        model.put("exito", "Guardado con exito");
        return "crearPelicula.html";
        }
        catch (Exception e){
            model.put("error", e.getMessage());
            return "crearPelicula.html";
        }  }
    
    @GetMapping("/editarPelicula")
    public String editarPelicula(@PathVariable String buscar, ModelMap model) throws Exception {
        model.put("pelicula", servPeli.buscarPorNombre(buscar));
        return "editarPelicula.html";
    }
    
    @PostMapping("/editarPelicula")
    public String editarPelicula(ModelMap model, @RequestParam String buscar, @RequestParam String foto, @RequestParam String titulo, @RequestParam Integer calificacion) throws Exception{
        try {
            servPeli.editar(buscar, foto, titulo, calificacion);
            return "redirect:/index";
        } catch (Exception e) {
            model.put("error", "No se puede modificar la pelicula");
            model.put("pelicula", servPeli.buscarPorNombre(buscar));
            return "editarPelicula.html";
        }   }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarPelicula(@PathVariable String buscar){
        try {
            servPeli.eliminarPelicula(buscar);
            return "redirect:/index";
        } catch (Exception e) {
            return "redirect:/index";
        }    }
}
