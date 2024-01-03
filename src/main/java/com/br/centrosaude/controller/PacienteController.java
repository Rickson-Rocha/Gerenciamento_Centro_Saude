package com.br.centrosaude.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.br.centrosaude.model.paciente.Paciente;
import com.br.centrosaude.service.PacienteService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.web.PageableDefault;
@RestController
@RequestMapping("/paciente")
@Validated
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;


    @GetMapping("/{id}")
    public ResponseEntity<Paciente> findById(@PathVariable Long id){
     Paciente paciente = this.pacienteService.findById(id);
     return ResponseEntity.ok(paciente);
    }

    @GetMapping
    public ResponseEntity<Page<Paciente>> listPacientes(
            @PageableDefault(size = 10) Pageable pageable 
    ) {
        Page<Paciente> pacientePage = pacienteService.listarPacientes(pageable);
        return ResponseEntity.ok(pacientePage);
}
@PostMapping
public ResponseEntity<Void> create(@Validated @RequestBody Paciente paciente, UriComponentsBuilder uriBuilder){
    Paciente criarPaciente = pacienteService.criarPaciente(paciente);
    var uri = uriBuilder.path("/paciente/{id}").buildAndExpand(criarPaciente.getId()).toUri();
    return ResponseEntity.created(uri).build();
}
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Paciente> update(@PathVariable Long id,@RequestBody @Valid Paciente paciente) {
        paciente.setId(id);
        Paciente pacienteLocal = pacienteService.editarPaciente(paciente);

        return ResponseEntity.ok(pacienteLocal);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pacienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}