package com.br.centrosaude.service;

import com.br.centrosaude.model.consulta.Consulta;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
public interface ConsultaService{
    Consulta findById(Long id);

    Consulta criarConsulta(Consulta consulta);

    Page<Consulta> listarConsultas(Pageable pageable);

    Consulta editarConsulta(Consulta consulta);

    void delete (Long id);
}
