package service;

import java.util.ArrayList;
import java.util.List;

import dao.TrechoDAO;
import dto.Rota;
import entity.Trecho;

public class TracaRota {
	
	TrechoDAO trechoDAO = new TrechoDAO();

	public Rota obterRotaMaisCurta(String origem, String destino) {
		
		List<Rota> rotas = inicializarRotas(origem);
		Rota rotaFinal = null;
		while (rotas.size() > 0) {
			List<Rota> rotasNovas = new ArrayList<Rota>();
			for (Rota rota:rotas) {
				if (rotaFinal == null || rotaFinal.getDistancia() > rota.getDistancia()) {
					if (rota.getDestino().equals(destino)) {
						rotaFinal = rota;
					} else {
						rotasNovas.addAll(obterRotasNovas(rota, rotaFinal));
					}
				}
			}
			rotas = rotasNovas;
		}
		return rotaFinal;
	}
	
	private List<Rota> obterRotasNovas(Rota rota, Rota rotaFinal) {
		
		List<Rota> rotasNovas = new ArrayList<Rota>();
		List<Trecho> trechos = obterTrechos(rota.getDestino());
		for (Trecho trecho:trechos) {
			if (isDestinoNovo(rota, trecho)) {
				Rota rotaNova = copiarRota(rota);
				rotaNova.adicionarTrecho(trecho);
				if(rotaFinal == null || rotaFinal.getDistancia() > rotaNova.getDistancia()) {
					rotasNovas.add(rotaNova);
				}
			}
		}
		return rotasNovas;
	}

	private boolean isDestinoNovo(Rota rota, Trecho trecho) {

		for (Trecho trechoExistente:rota.getTrechos()) {
			if (trecho.getDestino().equals(trechoExistente.getDestino()) || trecho.getDestino().equals(trechoExistente.getOrigem())) {
				return false;
			}
		}
		return true;
	}

	private Rota copiarRota(Rota rota) {
		
		Rota copia = new Rota();
		for (Trecho trecho:rota.getTrechos()) {
			copia.adicionarTrecho(trecho);
		}
		return copia;
	}

	private List<Trecho> obterTrechos(String origem) {
		
		Trecho trecho = new Trecho();
		trecho.setOrigem(origem);
		return trechoDAO.findByExample(trecho);	
	}

	private List<Rota> inicializarRotas(String origem) {
		
		List<Trecho> trechos = obterTrechos(origem);
		List<Rota> rotas = new ArrayList<Rota>();
		for (Trecho trecho:trechos) {
			Rota rota = new Rota();
			rota.adicionarTrecho(trecho);
			rotas.add(rota);
		}
		return rotas;
	}
}