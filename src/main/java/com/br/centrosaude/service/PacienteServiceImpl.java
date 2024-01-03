package com.br.centrosaude.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.centrosaude.model.paciente.Paciente;
import com.br.centrosaude.repository.PacienteRepository;

import jakarta.transaction.Transactional;

@Service
public class PacienteServiceImpl  implements PacienteService{

    @Autowired
    private PacienteRepository pacienteRepository;
    @Override
    public Paciente findById(Long id) {
        Optional<Paciente> paciente = this.pacienteRepository.findById(id);
        return paciente.orElseThrow(()-> new RuntimeException("Paciente não encontrado"));
    }

    @Override
    @Transactional
    public Paciente criarPaciente(Paciente paciente) {
       paciente.setId(null);
       Paciente novoPaciente = pacienteRepository.save(paciente);
       return novoPaciente;
    }

    @Override
    @Transactional
    public Paciente editarPaciente(Paciente paciente) {
        Paciente novoPaciente = findById(paciente.getId());
        novoPaciente.setNome(paciente.getNome());
        novoPaciente.setCpf(paciente.getCpf());
        novoPaciente.setConsulta(paciente.getConsulta());
        return novoPaciente;
    }
    
    @Override
    public void delete(Long id) {
       findById(id);
       pacienteRepository.deleteById(id);
    }

    @Override
    public Page<Paciente> listarPacientes(Pageable pageable) {
        return pacienteRepository.findAll(pageable);
    }
    
}
