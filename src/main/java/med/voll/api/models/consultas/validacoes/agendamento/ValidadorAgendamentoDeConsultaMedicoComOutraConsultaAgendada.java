package med.voll.api.models.consultas.validacoes.agendamento;

import med.voll.api.models.consultas.ConsultaRepository;
import med.voll.api.models.consultas.DTOS.ConsultaDTO;
import med.voll.api.utils.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAgendamentoDeConsultaMedicoComOutraConsultaAgendada implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(ConsultaDTO consultaDTO) {
        var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndData(consultaDTO.getMedico(), consultaDTO.getData());
        if(medicoPossuiOutraConsultaNoMesmoHorario){
            throw new ValidacaoException("Médico já possui outra consulta agendada neste mesmo horário");
        }


    }
}
