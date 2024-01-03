package com.br.centrosaude.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.centrosaude.model.consulta.Consulta;
import com.br.centrosaude.repository.ConsultaRepository;

import jakarta.transaction.Transactional;


@Service
public class ConsultaServiceImpl implements ConsultaService{

    @Autowired
    private ConsultaRepository consultaRepository;
    @Override
    public Consulta findById(Long id) {
        Optional<Consulta> medico = this.consultaRepository.findById(id);
        return medico.orElseThrow(()-> new RuntimeException("Consulta não encontrada"));
    }

    @Override
    @Transactional
    public Consulta criarConsulta(Consulta consulta) {
        consulta.setId(null);
        Consulta novoMedico = consultaRepository.save(consulta);
        return novoMedico;
    }

    @Override
    public Page<Consulta> listarConsultas(Pageable pageable) {
        return consultaRepository.findAll(pageable);
    }

    @Override
    public Consulta editarConsulta(Consulta consulta) {
        Consulta novaConsulta = findById(consulta.getId());
        novaConsulta.setMedico(consulta.getMedico());
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
