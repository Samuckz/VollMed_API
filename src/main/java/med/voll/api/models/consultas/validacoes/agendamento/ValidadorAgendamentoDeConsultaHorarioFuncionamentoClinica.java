package med.voll.api.models.consultas.validacoes.agendamento;

import med.voll.api.models.consultas.DTOS.ConsultaDTO;
import med.voll.api.utils.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorAgendamentoDeConsultaHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {
    @Override
    public void validar(ConsultaDTO consultaDTO){
        var data = consultaDTO.getData();
        var domingo = data.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDeAbertura = data.getHour() < 7;
        var depoisDoEncerramento = data.getHour() > 18;

        if (domingo || antesDeAbertura || depoisDoEncerramento){
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}
