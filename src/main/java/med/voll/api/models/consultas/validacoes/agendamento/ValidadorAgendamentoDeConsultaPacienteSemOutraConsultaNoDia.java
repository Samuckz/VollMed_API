package med.voll.api.models.consultas.validacoes.agendamento;

import med.voll.api.models.consultas.ConsultaRepository;
import med.voll.api.models.consultas.DTOS.ConsultaDTO;
import med.voll.api.utils.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAgendamentoDeConsultaPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(ConsultaDTO consultaDTO) {
        var primeiroHorario = consultaDTO.getData().withHour(7);
        var ultimoHorario = consultaDTO.getData().withHour(18);
        var pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(consultaDTO.getPaciente(), primeiroHorario, ultimoHorario);
        if(pacientePossuiOutraConsultaNoDia){
            throw new ValidacaoException("Paciente já possui uma consulta agendada neste dia");
        }

    }
}
