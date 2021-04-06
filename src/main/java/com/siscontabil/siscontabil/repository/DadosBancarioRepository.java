import com.siscontabil.siscontabil.model.DadosBancario;

import org.springframework.data.jpa.repository.JpaRepository;
public interface DadosBancarioRepository extends JpaRepository<DadosBancario, Long> {
    
}
