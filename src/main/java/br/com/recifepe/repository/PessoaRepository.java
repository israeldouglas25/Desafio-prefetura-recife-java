package br.com.recifepe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.recifepe.model.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	@Query(nativeQuery = true, value = "SELECT posicao FROM pessoa ORDER BY posicao DESC")
	List<Integer> getPosicao();

}
