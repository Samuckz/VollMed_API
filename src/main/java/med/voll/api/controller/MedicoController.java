package med.voll.api.controller;

import med.voll.api.models.medicos.DTOS.DadosDetalhamentoMedicoDTO;
import med.voll.api.models.medicos.DTOS.MedicoDTO;
import med.voll.api.models.medicos.DTOS.MedicoDTOUpdate;
import med.voll.api.models.medicos.DTOS.MedicoResponseDTO;
import med.voll.api.models.medicos.MedicoModel;
import med.voll.api.models.medicos.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired // injeção de dependencias
    private MedicoRepository repository;

    @PostMapping
    @Transactional // Método de insert no dados, precisa de uma transação ativa com o banco de dados
    public ResponseEntity cadastrar(@RequestBody @Valid MedicoDTO medico, UriComponentsBuilder uriBuilder){
        MedicoModel medicoModel = repository.save(new MedicoModel(medico));

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medicoModel.getId()).toUri(); // Aqui você cria a variavel do heador local

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedicoDTO(medicoModel));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoResponseDTO>> listar(
            @PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 10) final Pageable paginacao
    ){
        var page =  repository.findAllByAtivoTrue(paginacao).map(
                MedicoResponseDTO::new
        );

        return ResponseEntity.ok(page);

        // Para fazer uma lista, use o stream().map()
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity detalharMedico(@PathVariable  Long id){

        var medico = repository.getById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));

    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid MedicoDTOUpdate medico){
        MedicoModel response = repository.getById(medico.getId());
        response.atualizarDados(medico);


        return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(response));

    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        MedicoModel response = repository.getById(id);
        response.excluir();

        return ResponseEntity.noContent().build();

    }




}
