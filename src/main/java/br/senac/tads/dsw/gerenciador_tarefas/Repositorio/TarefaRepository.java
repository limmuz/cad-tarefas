package br.senac.tads.dsw.gerenciador_tarefas.Repositorio;

import br.senac.tads.dsw.gerenciador_tarefas.Entidade.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

// Estendemos JpaRepository passando a Entidade (Tarefa) e o tipo do ID (Long)
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
    // A vantagem dessa config é que não precisamos escrever nada. O Spring cria findAll(), save(), deleteById() automaticamente.
}