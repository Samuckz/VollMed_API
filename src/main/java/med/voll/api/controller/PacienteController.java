package med.voll.api.controller;


import med.voll.api.models.medicos.DTOS.MedicoResponseDTO;
import med.voll.api.models.pacientes.DTOS.PacienteDTO;
import med.voll.api.models.pacientes.DTOS.PacienteDTOUpdate;
import med.voll.api.models.pacientes.PacienteModel;
import med.voll.api.models.pacientes.PacienteRepository;
import med.voll.api.models.pacientes.DTOS.PacienteResponseDTO;
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
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<PacienteModel> cadastrar(@RequestBody @Valid PacienteDTO paciente){
        PacienteModel response = pacienteRepository.save(new PacienteModel(paciente));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public Page<PacienteResponseDTO> listar(
            @PageableDefault(sort = "nome", direction = Sort.Direction.ASC, size = 10) final Pageable paginacao){
        return pacienteRepository.findAllByAtivoTrue(paginacao).map(
                PacienteResponseDTO::new
        );
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid PacienteDTOUpdate paciente){
        PacienteModel response = pacienteRepository.getById(paciente.getId());
        response.atualizarDados(paciente);
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        PacienteModel paciente = pacienteRepository.getById(id);
        paciente.delete();;
    }
}
