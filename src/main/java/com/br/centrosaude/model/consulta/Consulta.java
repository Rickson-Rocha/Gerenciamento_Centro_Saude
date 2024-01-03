package com.br.centrosaude.model.consulta;

import java.time.LocalTime;
import java.util.Optional;

import com.br.centrosaude.model.medico.Medico;
import com.br.centrosaude.model.paciente.Paciente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_consulta")
@Table(name = "tb_consulta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @NotNull
    @Column(name="diagnostico")
    private String diagnostico;

    @NotNull
    @Column(name = "data_consulta")
    private LocalTime dataConsulta;
    @NotNull
    @Column(name = "prescricao")
    private String prescricao;
    
}
