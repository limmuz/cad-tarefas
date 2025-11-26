package br.senac.tads.dsw.gerenciador_tarefas.Repositorio;

import br.senac.tads.dsw.gerenciador_tarefas.Entidade.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
}