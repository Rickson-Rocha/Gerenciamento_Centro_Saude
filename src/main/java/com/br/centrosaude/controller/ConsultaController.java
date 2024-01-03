package com.br.centrosaude.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.centrosaude.model.consulta.Consulta;
import com.br.centrosaude.service.ConsultaService;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/consultas")
@Validated
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> findById(@PathVariable Long id){
     Consulta consulta = this.consultaService.findById(id);
     return ResponseEntity.ok(consulta);
    }

   @GetMapping("/paciente/{pacienteId}")
   public ResponseEntity<List<Consulta>> findAllByUserId(@PathVariable Long userId){
     var consultas = consultaService.listarConsultasPaciente(userId);
     return ResponseEntity.ok(consultas);
   }

   @PostMapping
   @Transactional
   public ResponseEntity<Void>criarMedico(@Validated @RequestBody Consulta consulta,UriComponentsBuilder uriBuilder){
       Consulta createdTask = consultaService.criarConsulta(consulta); 
       var uri = uriBuilder.path("/tasks/{id}").buildAndExpand(createdTask.getId()).toUri();
       return ResponseEntity.created(uri).build();

   }

   @PutMapping("/{id}")
   @Transactional
   public ResponseEntity<Void> update(@Validated  @RequestBody  Consulta consulta , @PathVariable Long id){
        consulta.setId(id);
        Consulta novaConsulta = this.consultaService.editarConsultaPaciente(consulta);
        return ResponseEntity.noContent().build();

   }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        consultaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
