package conta.controller;

import java.util.ArrayList;

import conta.model.Conta;
import conta.repository.ContaRepository;

public class ContaController implements ContaRepository {
	
	private ArrayList<Conta> listaContas = new ArrayList<Conta>();

	@Override
	public void procurarPorNumero(int numero) {
		var conta = buscarNaCollection(numero);
		
		if ( conta != null)
			conta.visualizar();
		else
			System.out.println("A Conta n�mero: " + numero + " n�o foi encontrada!");
	}

	@Override
	public void listarTodas() {
		for (var conta : listaContas) {
			conta.visualizar();
		}
		
	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("A Conta n�mero: " + conta.getNumero() + " foi criada com sucesso!");
		
	}

	@Override
	public void atualizar(Conta conta) {
		var buscaConta = buscarNaCollection(conta.getNumero());
		
		if (buscaConta != null) {
			listaContas.set(listaContas.indexOf(buscaConta), conta);
			System.out.println("A Conta numero: " + conta.getNumero() + " foi atualizada com sucesso!");
		} else
			System.out.println("A Conta n�mero: " + conta.getNumero() + " n�o foi encontrada!");
		
	}

	@Override
	public void deletar(int numero) {
		var conta = buscarNaCollection(numero);
		
		if (conta != null) {
			if (listaContas.remove(conta) == true)
				System.out.print("A Conta numero: " + numero + " foi deletada com sucesso!");
		} else
			System.out.print("A Conta numero: " + numero + " n�o foi encontrada!");
	}

	@Override
	public void sacar(int numero, float valor) {
		var buscaConta = buscarNaCollection(numero);
		
		if (buscaConta != null) {
			
			if (listaContas.get(listaContas.indexOf(buscaConta)).sacar(valor) == true)
				System.out.print("O saque na Conta n�mero: " + numero + " foi efetuado com sucesso!");
		} else
			System.out.print("A Conta n�mero: " + numero + " n�o foi encontrada!");
	}

	@Override
	public void depositar(int numero, float valor) {
		var buscaConta = buscarNaCollection(numero);
		
		if (buscaConta != null) {
			var indiceConta = listaContas.indexOf(buscaConta);
			listaContas.get(indiceConta).depositar(valor);
			System.out.print("O dep�sito na Conta n�mero: " + numero + " foi efetuado com sucesso!");
		} else
			System.out.print("A Conta n�mero: " + numero + " n�o foi encontrada ou a Conta destino n�o � uma Conta Corrente!");		
	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		var buscaContaOrigem = buscarNaCollection(numeroOrigem);
		var buscaContaDestino = buscarNaCollection(numeroDestino);
		
		if (buscaContaOrigem != null && buscaContaDestino != null) {
			
			if (listaContas.get(listaContas.indexOf(buscaContaOrigem)).sacar(valor) == true) {
				listaContas.get(listaContas.indexOf(buscaContaDestino)).depositar(valor);
				System.out.print("A Transfer�ncia foi efetuado com sucesso!");
			}
		} else
			System.out.print("A Conta de Origem e/ou Destino n�o foram encontradas!");
	}
	
	public int gerarNumero() {
		return listaContas.size() + 1;
	}
	
	public Conta buscarNaCollection(int numero) {
		for (var conta : listaContas) {
			if ( conta.getNumero() == numero) {
				return conta;
			}
		}
		return null;
	}
	
	public int retornaTipo(int numero) {
		for (var conta : listaContas) {
			if (conta.getNumero() == numero) {
				return conta.getTipo();
			}
		}
		return 0;
	}

}
