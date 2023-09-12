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
    public ResponseEntity<ConsultaModel> cadastrar(@RequestBody @Valid ConsultaDTO consultaDTO){
//        consultaService.validateConsulta(consultaDTO);
        ConsultaModel response = consultaRepository.save(new ConsultaModel(consultaDTO));
        return new ResponseEntity<>(response, HttpStatus.CREATED);


    }

    @GetMapping
    public Page<ConsultaResponseDTO> listar(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) final Pageable paginacao
    ){
        return consultaService.listarConsultas(paginacao);
    }


    @PostMapping(path = "/cancelar")
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid CancelarConsultaDTO consulta){
        consultaService.cancelar(consulta);

        return ResponseEntity.ok("Consulta cancelada com sucesso");
    }




//    @PutMapping
//    @Transactional
//    public ResponseEntity<ConsultaModel> atualizar(@RequestBody @Valid ConsultaDTO consultaDTO){
//
//    }

}
