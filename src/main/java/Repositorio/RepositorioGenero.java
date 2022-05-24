
package Repositorio;

import Entidades.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public interface RepositorioGenero extends JpaRepository<Genero, String>{

	@Query("SELECT a from Genero a WHERE a.nombre = :nombre")
	public Genero buscarPorNombre(@Param("nombre")String nombre);
}
