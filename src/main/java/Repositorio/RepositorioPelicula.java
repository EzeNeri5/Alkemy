package Repositorio;

import Entidades.Pelicula;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPelicula extends JpaRepository<Pelicula, String>{

    @Query("SELECT p FROM Pelicula p WHERE p.titulo = :titulo")
    public Pelicula buscarPorNombre(@Param("titulo") String nombre);
    
    @Query("SELECT p FROM Pelicula p WHERE p.genero = :genero")
    public List<Pelicula> buscarPorGenero(@Param("titulo") String nombre);

    @Query("SELECT p FROM Pelicula p ORDER BY p.creacion ASC")
    public List<Pelicula> ordenarAscendente ();

    @Query("SELECT p FROM Pelicula p ORDER BY p.creacion DESC")
    public List<Pelicula> ordenarDescendente();
   
}
