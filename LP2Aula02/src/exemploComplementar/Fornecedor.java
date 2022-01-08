package exemploComplementar;

import java.time.LocalDate;

public interface Fornecedor {
	public String emitirFaturaDoPeriodo(LocalDate inicio, LocalDate fim);
}
