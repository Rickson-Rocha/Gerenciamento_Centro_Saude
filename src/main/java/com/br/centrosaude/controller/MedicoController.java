package com.br.centrosaude.controller;

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
import org.springframework.data.web.PageableDefault;

import com.br.centrosaude.model.medico.Medico;
import com.br.centrosaude.service.MedicoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medico")
@Validated
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping("/{id}")
    public ResponseEntity<Medico> findById(@PathVariable Long id){
     Medico medico = this.medicoService.findById(id);
     return ResponseEntity.ok(medico);
    }

    @GetMapping
    public ResponseEntity<Page<Medico>> listMedicos(
            @PageableDefault(size = 10) Pageable pageable 
    ) {
        Page<Medico> medicoPage = medicoService.listarMedicos(pageable);
        return ResponseEntity.ok(medicoPage);
}
@PostMapping
public ResponseEntity<Void> create(@Validated @RequestBody Medico medico, UriComponentsBuilder uriBuilder){
    Medico criarmedico = medicoService.criarMedico(medico);
    var uri = uriBuilder.path("/medico/{id}").buildAndExpand(criarmedico.getId()).toUri();
    return ResponseEntity.created(uri).build();
}
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Medico> update(@PathVariable Long id,@RequestBody @Valid Medico medico) {
        medico.setId(id);
        Medico medicoLocal = medicoService.editarMedico(medico);

        return ResponseEntity.ok(medicoLocal);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
