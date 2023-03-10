package br.com.recifepe.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.recifepe.model.Pessoa;
import br.com.recifepe.repository.PessoaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PessoaService {

	private PessoaRepository pessoaRepository;

	public List<Pessoa> findAll() {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		pessoas.sort(Comparator.comparing(Pessoa::getPosicao));
		return pessoas;
	}

	public Pessoa findById(Long id) {
		return pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
	}

	@Transactional
	public Pessoa update(Long id, Pessoa pessoa) {
		Pessoa pessoaDb = pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found."));

		pessoaDb.setId(id);
		pessoaDb.setNome(pessoa.getNome());
		pessoaDb.setIdade(pessoa.getIdade());
		pessoaDb.setTelefone(pessoa.getTelefone());
		pessoaDb.setPosicao(pessoa.getPosicao());
		pessoaDb.setAtendida(pessoa.getAtendida());
		
		atendida(pessoaDb);

		return pessoaRepository.save(pessoaDb);

	}

	@Transactional
	public Pessoa save(Pessoa pessoa) {
		var posicoes = pessoaRepository.getPosicao();
		
		if(posicoes.size() > 1000) {
			throw new RuntimeException("Limite excedido!");
		}
		
		int proxPosicao = posicoes.isEmpty() ? 1 : posicoes.get(0) + 1;
		pessoa.setPosicao(proxPosicao);
		
		atendida(pessoa);
		
		var saved = pessoaRepository.save(pessoa);
		
		return saved;
	}

	@Transactional
	public void delete(Long id) {
		if (!pessoaRepository.existsById(id)) {
			throw new RuntimeException("User not found!");
		}
		pessoaRepository.deleteById(id);
	}
	
	public void atendida(Pessoa pessoa) {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		int numFila = pessoas.size();
		
		if(pessoa.getAtendida() == true) {
			pessoa.setNumClienteFila(numFila - 1);
		}else {
			pessoa.setNumClienteFila(numFila);
		}
	}

}
