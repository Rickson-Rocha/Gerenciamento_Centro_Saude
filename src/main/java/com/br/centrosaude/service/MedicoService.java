package com.br.centrosaude.service;

import com.br.centrosaude.model.medico.Medico;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
public interface MedicoService{
    Medico findById(Long id);

    Medico criarMedico(Medico medico);

    Page<Medico> listarMedicos(Pageable pageable);


    Medico editarMedico(Medico medico);

    void delete (Long id);
}
