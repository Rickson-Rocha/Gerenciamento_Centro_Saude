package com.br.centrosaude.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.centrosaude.model.consulta.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta,Long> {
    
}
