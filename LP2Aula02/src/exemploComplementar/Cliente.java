package exemploComplementar;

import java.time.LocalDate;

public interface Cliente {
	public String obterPedidosDoPeriodo(LocalDate inicio, LocalDate fim);
}
