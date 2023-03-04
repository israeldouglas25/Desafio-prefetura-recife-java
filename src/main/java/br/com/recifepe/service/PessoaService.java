package br.com.recifepe.service;

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
		return pessoaRepository.findAll();
	}

	public Pessoa findById(Long id) {
		return pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
	}

	@Transactional
	public Pessoa update(Long id, Pessoa pessoa) {
		Pessoa pessoaDb = pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found."));

		pessoaDb.setId(id);
		pessoaDb.setNome(pessoa.getNome());
		pessoaDb.setIdade(pessoa.getIdade());

		return pessoaRepository.save(pessoaDb);

	}

	@Transactional
	public Pessoa save(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	@Transactional
	public void delete(Long id) {
		if (!pessoaRepository.existsById(id)) {
			throw new RuntimeException("User not found!");
		}
		pessoaRepository.deleteById(id);
	}

}
