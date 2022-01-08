package exemploComplementar;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ClassePrincipal {

	public static void main(String[] args) {

		Pessoa p = new PessoaFisica("Vagner", "+55 11 9999-9999", 1000.0, "vagner", "123456", 25829749866l,
				LocalDate.of(1992, 9, 8), "São Paulo - SP");

		Acesso a1 = new Acesso(p, LocalDateTime.of(2020, 11, 5, 19, 25));
		a1.setLogout(LocalDateTime.of(2020, 11, 5, 20, 32));
		Acesso a2 = new Acesso(p, LocalDateTime.now());

		p.getAcessos().add(a1);
		p.getAcessos().add(a2);

		for (Acesso a : p.getAcessos()) {
			System.out.println("Usuário: " + a.getPessoa().getNome() + " / Login: " + a.getLogin() + " / Logout: "
					+ a.getLogout() + "\n");
		}

		Pessoa p2 = new PessoaJuridica("Instituto Federal de São Paulo", "+55 11 9999-9999", 500000.0, "ifsp-spo",
				"123456", 61852935000190l, "IFSP");

		Endereco e1 = new Endereco(p2, TipoLogradouro.Rua, "Pedro Vicente", 650, "Canindé", "São Paulo", "SP", "Brasil",
				1109010l, true);

		Endereco e2 = new Endereco(p2, TipoLogradouro.Avenida, "XYZ", 1000, "Centro", "São Paulo", "SP", "Brasil",
				99999999l, false);

		p2.getEnderecos().add(e1);
		p2.getEnderecos().add(e2);

		for (Endereco e : p2.getEnderecos()) {
			System.out.println(e.getTipo() + " " + e.getLogradouro() + ", " + e.getNumero() + ", " + e.getBairro()
					+ "\n" + e.getCidade() + " - " + e.getEstado() + "\n" + "CEP: " + e.getCep() + "\n");
		}

		// System.out.println("Nome: " + p.nome);
		// System.out.println("Nascto: " + ((PessoaFisica)p).getNascimento());
		// System.out.println(e1.getTipo() + " " + e1.getLogradouro());
	}
}
