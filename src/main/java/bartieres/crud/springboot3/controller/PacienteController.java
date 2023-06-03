package bartieres.crud.springboot3.controller;

import bartieres.crud.springboot3.domain.paciente.DadosCadastroPaciente;
import bartieres.crud.springboot3.domain.paciente.DadosDetalhePaciente;
import bartieres.crud.springboot3.domain.paciente.Paciente;
import bartieres.crud.springboot3.domain.paciente.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(dados);
        repository.save(paciente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhePaciente(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable("id") Long id) {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhePaciente(paciente));
    }
}
