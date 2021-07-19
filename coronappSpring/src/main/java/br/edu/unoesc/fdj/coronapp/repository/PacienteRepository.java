package br.edu.unoesc.fdj.coronapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.unoesc.fdj.coronapp.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	
	public List<Paciente> findByNomeLike(String nome);

	public Paciente findByCpf(Long cpf);
	
	public List<Paciente> findByResultado(Double resultado);
	
	@Query("select p from Paciente p where p.sexo = :sexo")
	List<Paciente> porSexo(@Param("sexo") String sexo);
	
	@Query("select p from Paciente p where p.resultado >= :valor")
	List<Paciente> listaResultPorValor(@Param("valor") double valor);
}
