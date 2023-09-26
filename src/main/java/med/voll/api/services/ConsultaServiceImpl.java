package med.voll.api.services;

import lombok.extern.slf4j.Slf4j;
import med.voll.api.models.consultas.ConsultaModel;
import med.voll.api.models.consultas.ConsultaRepository;
import med.voll.api.models.consultas.DTOS.CancelarConsultaDTO;
import med.voll.api.models.consultas.DTOS.ConsultaDTO;
import med.voll.api.models.consultas.DTOS.ConsultaResponseDTO;
import med.voll.api.models.consultas.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.api.models.consultas.validacoes.cancelamento.ValidadorCancelamentoConsulta;
import med.voll.api.models.medicos.MedicoModel;
import med.voll.api.models.medicos.MedicoRepository;
import med.voll.api.models.pacientes.PacienteRepository;
import med.voll.api.utils.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ConsultaServiceImpl implements PoliticaConsultas {
    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;
    // Este método faz com que o Spring procure todas as classes que implementam a interface referenciada

    @Autowired
    private List<ValidadorCancelamentoConsulta> cancelamentoConsultas;


    public ConsultaResponseDTO validarConsulta(ConsultaDTO consultaDTO){

        if(!pacienteRepository.existsById(consultaDTO.getPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        if(consultaDTO.getMedico() != null && !medicoRepository.existsById(consultaDTO.getMedico())){
            throw new ValidacaoException("Id do médico informado não existe!");
        }

        validadores.forEach(v -> v.validar(consultaDTO));

        var medico = escolherMedico(consultaDTO);
        if(medico == null){
            throw new ValidacaoException("Não existe médico disponível nessa data");
        }

        var paciente = pacienteRepository.getById(consultaDTO.getPaciente());
        var consulta = new ConsultaModel(null, medico, paciente, consultaDTO.getData());

        consultaRepository.save(consulta);

        return new ConsultaResponseDTO(consulta);
    }

    private MedicoModel escolherMedico(ConsultaDTO consultaDTO) {
        if(consultaDTO.getMedico() != null){
            return medicoRepository.getById(consultaDTO.getMedico());
        }

        if(consultaDTO.getEspecialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhida");
        }

//        Pageable pageable = PageRequest.of(0, 1);
        Pageable pageable = PageRequest.of(0,1);
        List<MedicoModel> medicos = medicoRepository.escolherMedicoAleatorioLivreNaData(consultaDTO.getEspecialidade(), consultaDTO.getData(), pageable);
        MedicoModel medicoAleatorio = medicos.isEmpty() ? null : medicos.get(0);

        return medicoAleatorio;


    }

    public CancelarConsultaDTO cancelar(CancelarConsultaDTO cancelarConsultaDTO){

        if(!consultaRepository.existsById(cancelarConsultaDTO.getId())){
            throw new ValidacaoException("Não foi encontrada nenhuma consulta com o Id informado!");
        }

        cancelamentoConsultas.forEach(v -> v.validar(cancelarConsultaDTO));

        return cancelarConsultaDTO;

    }


}

/*
    APLICANDO PADRÕES SOLID

    S -> Single Responsability Principle: Cada classe de validação tewm uma unica responsabilidae
    O -> Open Closed Principle: Classe fechada para modificação, mas aberta para extensão
    D -> Dependency Inversion Principle: A nossa classe service depende de uma abstração

 */
