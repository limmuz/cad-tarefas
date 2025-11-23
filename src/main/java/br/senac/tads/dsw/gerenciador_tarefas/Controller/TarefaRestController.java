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

@RestController // 1. Essa anotação diz ao Spring que esta classe responde a requisições REST (JSON).
@RequestMapping("/api/tarefas") // 2. Prefixo de todas as URLs (ex: http://localhost:8080/api/tarefas).
@CrossOrigin(origins = "*") // 3. Libera o acesso para qualquer Frontend (evita erro de CORS).
public class TarefaRestController {

    @Autowired // 4. Injeta o Repository automaticamente.
    private TarefaRepository repository;

    // --- LISTAR TODAS AS TAREFAS ---
    @GetMapping
    public List<Tarefa> listarTodas() {
        return repository.findAll();
    }

    // --- BUSCAR POR ID (Para preencher o formulário de alteração) ---
    @GetMapping("/{id}")
    public Tarefa buscarPorId(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));
    }

    // --- INCLUIR NOVA TAREFA ---
    @PostMapping
    public Tarefa incluir(@RequestBody @Valid Tarefa tarefa) {
        // @Valid: Ativa as validações do @NotBlank/@NotNull da Entidade
        // @RequestBody: Pega o JSON enviado pelo site e converte em objeto Java
        return repository.save(tarefa);
    }

    // --- ALTERAR TAREFA ---
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> alterar(@PathVariable Integer id, @RequestBody @Valid Tarefa dadosAtualizados) {
        return repository.findById(id)
                .map(tarefaExistente -> {
                    // Atualiza os dados do objeto existente com os novos dados
                    tarefaExistente.setTitulo(dadosAtualizados.getTitulo());
                    tarefaExistente.setResponsavel(dadosAtualizados.getResponsavel());
                    tarefaExistente.setDataTermino(dadosAtualizados.getDataTermino());
                    tarefaExistente.setDetalhamento(dadosAtualizados.getDetalhamento());
                    
                    Tarefa tarefaSalva = repository.save(tarefaExistente);
                    return ResponseEntity.ok(tarefaSalva);
                })
                .orElse(ResponseEntity.notFound().build()); // Se não achar o ID, retorna erro 404
    }

    // --- EXCLUIR TAREFA ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build(); // Retorna status 204 (Sucesso sem conteúdo)
    }
}