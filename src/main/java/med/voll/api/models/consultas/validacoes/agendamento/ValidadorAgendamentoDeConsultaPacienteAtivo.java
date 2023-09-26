package med.voll.api.models.consultas.validacoes.agendamento;

import med.voll.api.models.consultas.DTOS.ConsultaDTO;
import med.voll.api.models.pacientes.PacienteRepository;
import med.voll.api.utils.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAgendamentoDeConsultaPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    PacienteRepository pacienteRepository;

    @Override
    public void validar(ConsultaDTO consultaDTO) {
        var pacienteId = consultaDTO.getPaciente();
        var paciente = pacienteRepository.getById(pacienteId);

        if(paciente.getAtivo() == false){
            throw new ValidacaoException("Paciente inativo no sistema");
        }


    }
}
