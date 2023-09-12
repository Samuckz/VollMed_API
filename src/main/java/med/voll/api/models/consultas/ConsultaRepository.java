package med.voll.api.models.consultas;

import net.bytebuddy.jar.asm.commons.Remapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<ConsultaModel, Long> {
    Page<ConsultaModel> findAllByAtivoTrue(Pageable paginacao);
}
