package bartieres.crud.springboot3.controller;

import bartieres.crud.springboot3.domain.consulta.AgendaConsultaService;
import bartieres.crud.springboot3.domain.consulta.DadosAgendamentoConsulta;
import bartieres.crud.springboot3.domain.consulta.DadosCancelamentoConsulta;
import bartieres.crud.springboot3.domain.consulta.DadosDetalhamentoConsulta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaConsultaService agendaConsultaService;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dadosAendamento = agendaConsultaService.agendar(dados);
        return ResponseEntity.ok(dadosAendamento);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agendaConsultaService.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
