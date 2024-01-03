package com.br.centrosaude.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.centrosaude.model.consulta.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta,Long> {

    List<Consulta> findByPaciente_Id(Long pacienteId);
    
}
