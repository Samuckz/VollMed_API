package med.voll.api.models.medicos;

import med.voll.api.utils.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicoRepository extends JpaRepository<MedicoModel, Long> {
    Page<MedicoModel> findAllByAtivoTrue(Pageable paginacao);

    @Query("select m from Medico m where m.ativo = true and m.especialidade = :especialidade and m.id not in (select c.medico.id from Consulta c where c.data = :data) order by rand()")
    List<MedicoModel> escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data, Pageable pageable);



}
