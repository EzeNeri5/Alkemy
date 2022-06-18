package Servicios;

import Entidades.Pelicula;
import Entidades.Personaje;
import Repositorio.RepositorioPelicula;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPelicula {
   
    @Autowired
    private RepositorioPelicula rp;
    
    @Transactional
    public Pelicula crear(String foto, String titulo, Integer calificacion) throws Exception {
    
        validar(foto, titulo, calificacion);
        Pelicula p = new Pelicula();
        
        p.setFoto(foto);
        p.setTitulo(titulo);
        p.setCreacion(new Date());
        p.setCalificacion(calificacion);
                
        rp.save(p);
        return p;
    }
    
    @Transactional
    public Pelicula editar(String buscar, String foto, String titulo, Integer calificacion) throws Exception {
    
        validar(foto, titulo, calificacion);
        Pelicula p = rp.buscarPorNombre(buscar);
          
        if (p != null) {
        p.setFoto(foto);
        p.setTitulo(titulo);
        p.setCalificacion(calificacion);
                
        rp.save(p);
        return p;}
        else {return null;}
    }

    @Transactional
    public List<Pelicula> ordenarAscentende(){
        return rp.ordenarAscendente();    }

    @Transactional
    public List<Pelicula> ordenarDescentende(){
        return rp.ordenarDescendente();    }

    @Transactional
    public List<Pelicula> buscarPorNombre(String nombre){
        return rp.buscarPorGenero(nombre);    }

    @Transactional
    public List<Pelicula> buscarPOrGenero(String genero){
        return rp.buscarPorGenero(genero);    }   
    
    @Transactional
    public Personaje agregarPersonaje(Personaje personaje, String nombre){
        Personaje p = new Personaje();
              
        Pelicula  peli = (Pelicula) rp.buscarPorNombre(nombre);
        
        p.setPeli(peli);
        
        return p;    }
    
    @Transactional()
    public void eliminarPelicula(String id) throws Exception {
     Optional <Pelicula> respuesta = rp.findById(id);
        if (respuesta.isPresent()) {
            rp.deleteById(id);
        }else {
            throw new Exception("No se encontro el ID solicitado");
        }    }
    
    @Transactional
    public void validar(String foto, String titulo, Integer calificacion) throws Exception{
    
        if (titulo == null || titulo.isEmpty() || titulo.contains(" ")) {
            throw new Exception("Debe tener un titulo valido");        }

        if (foto == null || foto.isEmpty() || foto.contains(" ")) {
            throw new Exception("Debe tener una foto valida");         }
        
        if (calificacion  == null || calificacion < 1 || calificacion > 5) {
            throw new Exception("Debe tener una calificacion valida (entre 1 y 5)"); }
    }
}