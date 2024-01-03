package com.br.centrosaude.model.paciente;

import java.io.Serializable;
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
import jakarta.validation.constraints.Digits;

@Entity(name = "tb_paciente")
@Table(name = "tb_paciente")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Paciente  implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Column(name = "cpf", unique = true)
    @Digits(integer = 11, fraction = 0, message = "O CPF deve conter apenas dígitos")
    private String cpf;

    @NotNull
    private String historicoMedico;

    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consulta;
}
