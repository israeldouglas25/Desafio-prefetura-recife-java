package br.com.recifepe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.recifepe.model.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
