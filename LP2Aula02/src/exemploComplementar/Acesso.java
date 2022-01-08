package exemploComplementar;

import java.time.LocalDateTime;

public class Acesso {

	private LocalDateTime login;
	private LocalDateTime logout;
	private Pessoa pessoa;

	/**
	 * Método construtor da classe.
	 */
	public Acesso(Pessoa pessoa, LocalDateTime login) {
		this.pessoa = pessoa;
		this.login = login;
	}

	/* Métodos Getters e Setters */

	public LocalDateTime getLogin() {
		return this.login;
	}

	public void setLogin(LocalDateTime login) {
		this.login = login;
	}

	public LocalDateTime getLogout() {
		return this.logout;
	}

	public void setLogout(LocalDateTime logout) {
		this.logout = logout;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}
}
