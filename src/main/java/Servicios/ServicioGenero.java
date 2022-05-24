package Servicios;

import Entidades.Genero;
import Entidades.Pelicula;
import Repositorio.RepositorioGenero;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioGenero {

    @Autowired
    private RepositorioGenero rg;
    
    public Genero crear(String foto, String nombre, Pelicula peli) throws Exception{
    
        validar(foto, nombre, peli);
        Genero g = new Genero();
        
        g.setFoto(foto);
        g.setNombre(nombre);
        g.setPeli((List<Pelicula>) peli);
        
        rg.save(g);
        
        return g;
    }
    
    public Genero modificar (String buscar, String foto, String nombre, Pelicula peli) throws Exception{
    
        validar(foto, nombre, peli);
        Genero g = rg.buscarPorNombre(buscar);
        
        if (g != null) {
            
        g.setFoto(foto);
        g.setNombre(nombre);
        g.setPeli((List<Pelicula>) peli);
        rg.save(g);
        
        return g;}
        else {return null;}
        
    }
    
    public void validar(String foto, String nombre, Pelicula peli) throws Exception{
    
        if (nombre == null || nombre.isEmpty() || nombre.contains(" ")) {
            throw new Exception("No es un nombre valido");
        }
 
        if (foto == null || foto.trim().isEmpty() || foto.contains(" ")) {
            throw new Exception("No es una foto valida");
        }
        if (peli == null) {
            throw new Exception("Debe tener una Pelicula asociada");
        }    }
}
