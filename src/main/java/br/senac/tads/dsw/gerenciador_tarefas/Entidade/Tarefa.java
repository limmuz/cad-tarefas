package br.senac.tads.dsw.gerenciador_tarefas.Entidade;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "tarefa")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "O responsável é obrigatório")
    private String responsavel;

    @NotNull(message = "A data de término é obrigatória")
    private LocalDate dataTermino;

    private String detalhamento;
}