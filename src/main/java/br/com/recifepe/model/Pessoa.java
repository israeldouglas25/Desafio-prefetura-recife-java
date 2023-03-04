package br.com.recifepe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Pessoa implements Comparable<Pessoa>{
	
	@EqualsAndHashCode.Include
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private int idade;
	private Long posicao;
	
	@Override
	public int compareTo(Pessoa p) {
		return p.getId() < this.id ? 1 : -1;
	}
	

}
