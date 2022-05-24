package Repositorio;

import Entidades.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPersonaje extends JpaRepository<Personaje, String>{

	@Query("SELECT a from Personaje a WHERE a.nombre = :nombre")
	public Personaje buscarPorNombre(@Param("nombre")String nombre);

        @Query("SELECT c FROM Personaje c WHERE c.id = :id")
        public Personaje buscarPorId(@Param("id") String id);

        @Query("SELECT c FROM Personaje c WHERE c.edad < :edad")
        public Personaje buscarPorEdadMenor(@Param("edad") String edad);

        @Query("SELECT c FROM Personaje c WHERE c.edad > :edad")
        public Personaje buscarPorEdadMayor(@Param("edad") String edad);

        @Query("SELECT c FROM Personaje c WHERE c.peso < :peso")
        public Personaje buscarPorPesoMenor(@Param("peso") String peso);

        @Query("SELECT c FROM Personaje c WHERE c.peso > :peso")
        public Personaje buscarPorPesoMayor(@Param("peso") String peso);

        @Query("SELECT c FROM Personaje c WHERE c.pelicula = :pelicula")
        public Personaje buscarPorPelicula(@Param("pelicula") String pelicula);
        
}
