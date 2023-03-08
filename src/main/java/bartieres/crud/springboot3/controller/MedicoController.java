package bartieres.crud.springboot3.controller;

import bartieres.crud.springboot3.medico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalheMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListaMedico>> list(@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListaMedico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DadosAlteraMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalheMedico(medico));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable("id") Long id) {
        var medico = repository.getReferenceById(id);
        medico.deletar();

        return ResponseEntity.noContent().build();
    }
}
