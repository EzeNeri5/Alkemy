package Controladores;

import Entidades.Pelicula;
import Servicios.ServicioPersonaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/personaje")
public class ControladorPersonaje {

    @Autowired
    ServicioPersonaje servPersonaje;
    
    @GetMapping("/crearPersonaje")
    public String crearPersonaje() throws Exception{
        Pelicula peli = null; //prueba
        servPersonaje.crear("www.disney.com/foto", "Enano", 50, 35, "historia", peli); // prueba
        return "crearPersonaje.html";
    }
        
    @PostMapping("/crearPersonaje")
    public String crearPersonaje (ModelMap model, @RequestParam String foto, @RequestParam String nombre, @RequestParam Integer edad, @RequestParam Integer peso, @RequestParam String historia, @RequestParam Pelicula pelicula) throws Exception{
    
        try{
        servPersonaje.crear(foto, nombre, edad, peso, historia, pelicula);
        model.put("exito", "Guardado con exito");
        return "crearPersonaje.html";
        }
        catch (Exception e){
            model.put("error", e.getMessage());
            return "crearPersonaje.html";
        }  }
    
    @GetMapping("/editarPersonaje")
    public String editarPersonaje(@PathVariable String buscar, ModelMap model) throws Exception {
        model.put("personaje", servPersonaje.buscarPorNombre(buscar));
        return "editarPersonaje.html";
    }
    
    @PostMapping("/editarPersonaje")
    public String editarPersonaje(ModelMap model, @RequestParam String buscar, @RequestParam String foto, @RequestParam String nombre, @RequestParam Integer edad, @RequestParam Integer peso, @RequestParam String historia, @RequestParam Pelicula pelicula) throws Exception{
        try {
            servPersonaje.actualizar(buscar, foto, nombre, edad, peso, historia, pelicula);
            return "redirect:/index";
        } catch (Exception e) {
            model.put("error", "No se puede modificar el personaje");
            model.put("personaje", servPersonaje.buscarPorNombre(nombre));
            return "editarPersonaje.html";
        }   }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarPersonaje(@PathVariable String buscar){
        try {
            servPersonaje.eliminar(buscar);
            return "redirect:/index";
        } catch (Exception e) {
            return "redirect:/index";
        }    }
}

