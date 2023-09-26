package med.voll.api.models.consultas;

import med.voll.api.models.consultas.DTOS.ConsultaResponseDTO;
import med.voll.api.models.medicos.MedicoModel;
import net.bytebuddy.jar.asm.commons.Remapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;


public interface ConsultaRepository extends JpaRepository<ConsultaModel, Long> {

    boolean existsByMedicoIdAndData(Long medico, LocalDateTime data);

    boolean existsByPacienteIdAndDataBetween(Long paciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    Page<ConsultaResponseDTO> findAllByAtivoTrue(Pageable paginacao);
}
