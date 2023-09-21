package med.voll.api.services;

import lombok.extern.slf4j.Slf4j;
import med.voll.api.models.consultas.ConsultaModel;
import med.voll.api.models.consultas.ConsultaRepository;
import med.voll.api.models.consultas.DTOS.CancelarConsultaDTO;
import med.voll.api.models.consultas.DTOS.ConsultaDTO;
import med.voll.api.models.consultas.DTOS.ConsultaResponseDTO;
import med.voll.api.models.medicos.MedicoModel;
import med.voll.api.models.medicos.MedicoRepository;
import med.voll.api.models.pacientes.PacienteModel;
import med.voll.api.models.pacientes.PacienteRepository;
import med.voll.api.utils.exception.DadosErroValidacao;
import med.voll.api.utils.DataHora.DataHora;
import med.voll.api.utils.DataHora.DataHoraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

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
    DataHora dataHora;

    public ConsultaDTO validarConsulta(ConsultaDTO consultaDTO){

        // === Horário de funcionamento da Clinica ===


        return consultaDTO;
    }

    public Boolean validarHorarioFuncionamento(DataHoraDTO dataHoraDTO) throws DadosErroValidacao {
        if (dataHoraDTO.getHora() < 7 || dataHoraDTO.getHora() > 19){
            throw new DadosErroValidacao("hora", "A clínica não funciona neste horário, favor escolher um horário entre 07:00 e 19:00 horas");
        }
        return true;
    }

    public Boolean verificaExistenciaMedico(ConsultaDTO consultaDTO) throws DadosErroValidacao{
        medicoRepository.getById(consultaDTO.getMedico());
        return true;
    }

    public Boolean verificaExistenciaPaciente(ConsultaDTO consultaDTO) throws DadosErroValidacao{
        pacienteRepository.getById(consultaDTO.getPaciente());
        return true;
    }

    public Boolean verificaDisponibilidadeMedico(ConsultaDTO consultaDTO, Pageable paginacao) throws DadosErroValidacao{
        var consultas = consultaRepository.findAllByAtivoTrue(paginacao).stream().filter(
                consultaModel -> {
                    if(consultaModel.getMedico() == consultaDTO.getMedico()){
                        if(consultaModel.getDataHora().getDia() == consultaDTO.getDataHoraDTO().getDia() &&
                                consultaModel.getDataHora().getMes() == consultaDTO.getDataHoraDTO().getMes() &&
                                consultaModel.getDataHora().getAno() == consultaDTO.getDataHoraDTO().getAno() &&
                                consultaModel.getDataHora().getHora() == consultaDTO.getDataHoraDTO().getHora()){
                            return true;
                        }
                    }
                    return false;
                }


        ).collect(Collectors.toList());

        if(!consultas.isEmpty()){
            throw new DadosErroValidacao("Médico", "O(A) médico(a) não estará disponível neste horário");
        }

        return true;
    }

//    public Boolean antecedenciaMinima(DataHoraDTO dataHoraDTO){
//
//    }

    public Boolean naoPermitirDuasConsultasPacienteDia(ConsultaDTO consultaDTO, Pageable paginacao) throws DadosErroValidacao {
        var consultas = consultaRepository.findAllByAtivoTrue(paginacao).stream().filter(
                consultaModel -> {
                    if(consultaModel.getPaciente() == consultaDTO.getPaciente()){
                        if(consultaModel.getDataHora().getDia() == consultaDTO.getDataHoraDTO().getDia() &&
                                consultaModel.getDataHora().getMes() == consultaDTO.getDataHoraDTO().getMes() &&
                                consultaModel.getDataHora().getAno() == consultaDTO.getDataHoraDTO().getAno() &&
                                consultaModel.getDataHora().getHora() == consultaDTO.getDataHoraDTO().getHora()){
                            return true;
                        }
                    }
                    return false;
                }


        ).collect(Collectors.toList());

        if(!consultas.isEmpty()){
            throw new DadosErroValidacao("Paciente", "Este paciente já apresenta consulta cadastrada neste dia");
        }

        return true;


    }

    @Transactional(readOnly = true)
    @Override
    public Page<ConsultaResponseDTO> listarConsultas(
            Pageable paginacao
    ){
        return consultaRepository.findAllByAtivoTrue(paginacao).map(consulta ->{
            MedicoModel medicoName = medicoRepository.getById(consulta.getMedico());
            PacienteModel pacienteName = pacienteRepository.getById(consulta.getPaciente());

            return new ConsultaResponseDTO(consulta.getId(),medicoName.getNome(), pacienteName.getNome(), dataHora.toString(consulta.getDataHora()));
                }
        );

    }

    @Transactional
    @Override
    public void cancelar(CancelarConsultaDTO consultaDTO){
        ConsultaModel response = consultaRepository.getById(consultaDTO.getId());
        response.setAtivo(false);
//        response.setJustificativa(consultaDTO.getCancelamento());

    }


}
