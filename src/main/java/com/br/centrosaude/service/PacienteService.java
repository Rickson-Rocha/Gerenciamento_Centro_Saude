package com.br.centrosaude.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.br.centrosaude.model.paciente.Paciente;

public interface PacienteService {
    Paciente findById(Long id);

    Paciente criarPaciente(Paciente paciente);

    Page<Paciente> listarPacientes(Pageable pageable);


    Paciente editarPaciente(Paciente paciente);

    void delete (Long id);

}
