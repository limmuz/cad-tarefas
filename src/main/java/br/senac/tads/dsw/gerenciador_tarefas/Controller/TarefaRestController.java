package br.senac.tads.dsw.gerenciador_tarefas.Controller;

import br.senac.tads.dsw.gerenciador_tarefas.Entidade.Tarefa;
import br.senac.tads.dsw.gerenciador_tarefas.Repositorio.TarefaRepository;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/tarefas")
@CrossOrigin(origins = "*")
public class TarefaRestController {

    @Autowired
    private TarefaRepository repository;

    @GetMapping
    public List<Tarefa> listarTodas() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Tarefa buscarPorId(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));
    }

    @PostMapping
    public Tarefa incluir(@RequestBody @Valid Tarefa tarefa) {
        return repository.save(tarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> alterar(@PathVariable Integer id, @RequestBody @Valid Tarefa dadosAtualizados) {
        return repository.findById(id)
                .map(tarefaExistente -> {
                    tarefaExistente.setTitulo(dadosAtualizados.getTitulo());
                    tarefaExistente.setResponsavel(dadosAtualizados.getResponsavel());
                    tarefaExistente.setDataTermino(dadosAtualizados.getDataTermino());
                    tarefaExistente.setDetalhamento(dadosAtualizados.getDetalhamento());
                    
                    Tarefa tarefaSalva = repository.save(tarefaExistente);
                    return ResponseEntity.ok(tarefaSalva);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}