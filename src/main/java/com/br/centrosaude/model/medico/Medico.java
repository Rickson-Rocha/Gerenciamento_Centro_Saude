package com.br.centrosaude.model.medico;

import java.util.List;

import com.br.centrosaude.model.consulta.Consulta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_medico")
@Table(name = "tb_medico")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Column(name = "especialidade")
    private String especialidadeMedica;

    @NotNull
    @Column(name = "crm", unique = true)
    private String crm;

    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas;
}
