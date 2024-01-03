package com.br.centrosaude.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.centrosaude.model.consulta.Consulta;
import com.br.centrosaude.model.medico.Medico;
import com.br.centrosaude.model.paciente.Paciente;
import com.br.centrosaude.repository.ConsultaRepository;
import com.br.centrosaude.repository.MedicoRepository;
import com.br.centrosaude.repository.PacienteRepository;

import jakarta.transaction.Transactional;


@Service
public class ConsultaServiceImpl implements ConsultaService{

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;
    @Override
    public Consulta findById(Long id) {
        Optional<Consulta> medico = this.consultaRepository.findById(id);
        return medico.orElseThrow(()-> new RuntimeException("Consulta não encontrada"));
    }

    @Override
    @Transactional
    public Consulta criarConsulta(Consulta consulta) {
        consulta.setId(null);
        Optional<Paciente> pacienteOptional  = pacienteRepository.findById(consulta.getPaciente().getId());
        Paciente paciente = pacienteOptional.orElse(null);
        Optional<Medico> medicoOptional = medicoRepository.findById(consulta.getMedico().getId());
        Medico medico = medicoOptional.orElse(null);
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        Consulta novaConsulta = consultaRepository.save(consulta);
        return novaConsulta;
    }

    @Override
    public List<Consulta> listarConsultasPaciente(Long pacienteId) {
        List<Consulta> consultas= consultaRepository.findByPaciente_Id(pacienteId);
        return consultas;
    }

    @Override
    public Consulta editarConsultaPaciente(Consulta consulta) {
        Consulta novaConsulta = findById(consulta.getId());
        novaConsulta.setMedico(consulta.getMedico());
        novaConsulta.setPaciente(consulta.getPaciente());
        novaConsulta.setDataConsulta(consulta.getDataConsulta());
        novaConsulta.setPrescricao(consulta.getPrescricao());
        novaConsulta.setDiagnostico(consulta.getDiagnostico());
        return novaConsulta;
    }

    @Override
    public void delete(Long id) {
        findById(id);
        consultaRepository.deleteById(id);
    }
    

}
