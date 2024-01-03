package com.br.centrosaude.service;

import com.br.centrosaude.model.consulta.Consulta;

import java.util.List;
public interface ConsultaService{
    Consulta findById(Long id);

    Consulta criarConsulta(Consulta consulta);

    List<Consulta> listarConsultasPaciente(Long pacienteId);

    Consulta editarConsultaPaciente(Consulta consulta);

    void delete (Long id);
}
