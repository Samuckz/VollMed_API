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
import med.voll.api.utils.DataHora;
import med.voll.api.utils.DataHoraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Boolean validarHorarioFuncionamento(DataHoraDTO dataHoraDTO){
        if (dataHoraDTO.getHora() < 7 || dataHoraDTO.getHora() > 19){
            return false;
        }

        return true;
    }

//    public Boolean antecedenciaMinima(DataHoraDTO dataHoraDTO){
//
//    }

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

    @Override
    public void excluir(Long id) {

    }

    @Transactional
    @Override
    public void cancelar(CancelarConsultaDTO consultaDTO){
        ConsultaModel response = consultaRepository.getById(consultaDTO.getId());
        response.setAtivo(false);
//        response.setJustificativa(consultaDTO.getCancelamento());

    }


}
