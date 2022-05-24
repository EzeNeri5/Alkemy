package Servicios;

import Entidades.Pelicula;
import Entidades.Personaje;
import Repositorio.RepositorioPersonaje;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPersonaje {

    @Autowired
    private RepositorioPersonaje rp;
    
    @Transactional
    public Personaje crear(String foto, String nombre,Integer edad, Integer peso, String historia, Pelicula peli) throws Exception{
        
        validar(nombre, foto, edad, peso, peli);
        
        Personaje p = new Personaje();
        
        p.setFoto(foto);
        p.setNombre(nombre);
        p.setEdad(edad);
        p.setPeso(peso);
        p.setHistoria(historia);
        p.setPeli(peli);
        
        rp.save(p);
        return p;
    }
    
    @Transactional
    public Personaje actualizar(String buscar, String foto, String nombre,Integer edad, Integer peso, String historia, Pelicula peli) throws Exception{

        validar(nombre, foto, edad, peso, peli);
        Personaje p = rp.buscarPorNombre(buscar);
        
        if (p != null) {
        p.setFoto(foto);
        p.setNombre(nombre);
        p.setEdad(edad);
        p.setPeso(peso);
        p.setHistoria(historia);
        p.setPeli(peli);
        
        rp.save(p);
        return p;}
        else{return null;}
    }
        
    @Transactional
    public void eliminar(String nombre) throws Exception{
        
        Personaje p = rp.buscarPorNombre(nombre);
        
        rp.delete(p);
    }
    
    @Transactional
    public List<Personaje> listarTodos() {
		return rp.findAll();
    }
    
    @Transactional
    public List<Personaje> buscarPorEdadMayor(String edad){
            return (List<Personaje>) rp.buscarPorEdadMayor(edad);
    }
    
    @Transactional
    public List<Personaje> buscarPorEdadMenor(String edad){
            return (List<Personaje>) rp.buscarPorEdadMenor(edad);
    }
    
    @Transactional
    public List<Personaje> buscarPorPesoMayor(String peso){
            return (List<Personaje>) rp.buscarPorPesoMayor(peso);
    }

    @Transactional
    public List<Personaje> buscarPorPesoMenor(String peso){
            return (List<Personaje>) rp.buscarPorPesoMenor(peso);
    }

    @Transactional
    public List<Personaje> buscarPorNombre(String nombre){
            return (List<Personaje>) rp.buscarPorNombre(nombre);
    }

    @Transactional
    public List<Personaje> buscarPorPelicula(String peli){
            return (List<Personaje>) rp.buscarPorPelicula(peli);
    }

    @Transactional
    public void validar(String nombre,String foto, Integer edad,Integer peso, Pelicula peli) throws Exception {

		if (nombre == null || nombre.isEmpty() || nombre.contains("  ")) {
			throw new Exception("Debe tener un nombre valido");
		}

		if (edad == null || edad > 0) {
			throw new Exception("Debe tener una edad valida");
		}

                if (peso == null || peso > 0) {
			throw new Exception("Debe tener un peso valido");
		}
                
		if (foto == null || foto.trim().isEmpty() || foto.contains("  ")) {
			throw new Exception("Foto invalida");    
                }
                if (peli == null) {
                        throw new Exception("Dede tener una pelicula asociada");
        }    }
}
