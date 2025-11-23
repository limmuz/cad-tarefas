package br.senac.tads.dsw.gerenciador_tarefas.Entidade; // Ajuste se seu pacote for diferente

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data; // Import do Lombok

@Data // Aqui o LOMBOK está gerando os Getters, Setters, toString, equals, etc. automaticamente.
@Entity // JPA: Avisa que essa classe é uma tabela no banco.
@Table(name = "tarefa") // JPA está definindo o nome da tabela.
public class Tarefa {

    @Id // JPA: Chave primária (PK).
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA: Auto-increment (1, 2, 3...).
    private Integer id;

    @NotBlank(message = "O título é obrigatório") // Aqui é onde ocorre a validação, informando ao código que não deve receber valor vazio.
    private String titulo;

    @NotBlank(message = "O responsável é obrigatório")
    private String responsavel;

    @NotNull(message = "A data de término é obrigatória") // Use NotNull para datas (NotBlank é só para String).
    private LocalDate dataTermino;

    private String detalhamento; // Opcional, sem validação.
}