import com.siscontabil.siscontabil.model.DadosBancario;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DadosBancarioService {
    public DadosBancario save(DadosBancario dadosBancario);
    public DadosBancario findById(Long id);
    public List<DadosBancario> findAll();
}
