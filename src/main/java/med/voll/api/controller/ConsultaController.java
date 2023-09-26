package med.voll.api.controller;

import med.voll.api.models.consultas.ConsultaModel;
import med.voll.api.models.consultas.ConsultaRepository;
import med.voll.api.services.ConsultaServiceImpl;
import med.voll.api.models.consultas.DTOS.CancelarConsultaDTO;
import med.voll.api.models.consultas.DTOS.ConsultaDTO;
import med.voll.api.models.consultas.DTOS.ConsultaResponseDTO;
import med.voll.api.models.medicos.MedicoRepository;
import med.voll.api.models.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("consultas")
public class ConsultaController {
    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    ConsultaServiceImpl consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid ConsultaDTO consultaDTO){
        var response = consultaService.validarConsulta(consultaDTO);
        return ResponseEntity.ok(response);

    }

    @GetMapping
    public Page<ConsultaResponseDTO> listar(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) final Pageable paginacao
    ){
        return consultaRepository.findAllByAtivoTrue(paginacao);
    }


    @PostMapping(path = "/cancelar")
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid CancelarConsultaDTO cancelarConsultaDTO){
        var consulta = consultaService.cancelar(cancelarConsultaDTO);
        var consultaCancelada = consultaRepository.getById(consulta.getId());

        consultaCancelada.cancelar();

        return ResponseEntity.ok(cancelarConsultaDTO);
    }




//    @PutMapping
//    @Transactional
//    public ResponseEntity<ConsultaModel> atualizar(@RequestBody @Valid ConsultaDTO consultaDTO){
//
//    }

}
