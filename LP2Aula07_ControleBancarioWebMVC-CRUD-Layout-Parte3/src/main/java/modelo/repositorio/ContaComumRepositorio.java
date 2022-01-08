package modelo.repositorio;

import java.util.Collection;

import modelo.ContaComum;

public class ContaComumRepositorio extends Repositorio<ContaComum>
{
	public ContaComum recuperarContaComumPorNumero(long numero)
	{
		ContaComum resultado = null;
		
		try
		{
			resultado =
					PersistenceConfig.getEntityManager()
					.find(ContaComum.class, numero);
		}
		catch (Exception e)
		{
			System.out.println("Erro ao tentar recuperar a conta comum! " +
					e.getMessage());
		}
		
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<ContaComum> recuperarContasComuns()
	{
		Collection<ContaComum> resultado = null;
		
		try
		{
			// HQL: "FROM ContaComum"
			resultado = PersistenceConfig.getEntityManager()
					.createQuery("FROM " + ContaComum.class.getName())
					.getResultList();
		}
		catch (Exception e)
		{
			System.out.println(
					"Erro ao tentar recuperar as contas comuns cadastradas! " +
					e.getMessage());
			e.printStackTrace();
			resultado = null;
		}
		
		return resultado;
	}
}