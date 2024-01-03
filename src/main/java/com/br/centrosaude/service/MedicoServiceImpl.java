package com.br.centrosaude.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.centrosaude.model.medico.Medico;
import com.br.centrosaude.repository.MedicoRepository;
import org.springframework.data.domain.Page;
import jakarta.transaction.Transactional;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;
    @Override
    public Medico findById(Long id) {
        Optional<Medico> medico = this.medicoRepository.findById(id);
        return medico.orElseThrow(()-> new RuntimeException("Médico não encontrado"));
    }

    @Override
    @Transactional
    public Medico criarMedico(Medico medico) {
       medico.setId(null);
       Medico novoMedico = medicoRepository.save(medico);
       return novoMedico;
    }

    @Override
    public Page<Medico> listarMedicos(Pageable pageable) {
        return medicoRepository.findAll(pageable);
    }

    @Override
    public Medico editarMedico(Medico medico) {
       Medico novoMedico = findById(medico.getId());
       novoMedico.setNome(medico.getNome());
       novoMedico.setCrm(medico.getCrm());
       novoMedico.setConsultas(medico.getConsultas());
       return novoMedico;
    }

    @Override
    public void delete(Long id) {
        findById(id);
        medicoRepository.deleteById(id);
    }
    
}
