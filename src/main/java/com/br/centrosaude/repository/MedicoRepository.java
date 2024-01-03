package com.br.centrosaude.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.centrosaude.model.medico.Medico;

public interface MedicoRepository extends JpaRepository<Medico,Long>{
    
}
