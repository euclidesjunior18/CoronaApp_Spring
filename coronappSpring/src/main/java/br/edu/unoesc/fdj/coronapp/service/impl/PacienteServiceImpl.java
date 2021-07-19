package br.edu.unoesc.fdj.coronapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unoesc.fdj.coronapp.model.Paciente;
import br.edu.unoesc.fdj.coronapp.repository.PacienteRepository;
import br.edu.unoesc.fdj.coronapp.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {
	@Autowired
	private PacienteRepository pacienteRepository;

	@Override
	public void salvar(Paciente paciente) {
		pacienteRepository.save(paciente);
	}

	@Override
	public void excluir(Paciente paciente) {
		pacienteRepository.delete(paciente);
	}

	@Override
	public List<Paciente> listar() {
		return pacienteRepository.findAll();
	}

	@Override
	public List<Paciente> listarPorNome(String nome) {
		return pacienteRepository.findByNomeLike("%" + nome + "%");
	}

	@Override
	public Paciente listarPorCPF(Long cpf) {
		return pacienteRepository.findByCpf(cpf);
	}
	
	@Override
	public List<Paciente> listarPorResultado(Double resultado) {
		return pacienteRepository.findByResultado(resultado);
	}
	
	@Override
	public List<Paciente> listarPorSexo(String sexo) {
		return pacienteRepository.porSexo(sexo);
	}

	@Override
	public List<Paciente> listaResultPorValor(double valor) {
		return pacienteRepository.listaResultPorValor(valor);
	}

}
