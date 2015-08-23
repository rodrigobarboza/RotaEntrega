package dto;

import java.util.ArrayList;
import java.util.List;

import entity.Trecho;

public class Rota {
	
	private String origem;
	
	private String destino;
	
	private List<Trecho> trechos;
	
	private Float distancia;
	
	private boolean recalcularDistancia = false;
	
	public Rota() {
		trechos = new ArrayList<Trecho>();
		distancia = new Float(0.0F);
	}

	public String getOrigem() {
		if (origem == null && trechos != null && trechos.size() > 0) {
			origem = trechos.get(0).getOrigem();
		}
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public List<Trecho> getTrechos() {
		recalcularDistancia = true;
		return trechos;
	}

	public void setTrechos(List<Trecho> trechos) {
		recalcularDistancia = true;
		this.trechos = trechos;
	}

	public Float getDistancia() {
		if (recalcularDistancia) {
			distancia = 0.0F;
			for (Trecho trecho:trechos) {
				distancia += trecho.getDistancia();
			}
		}
		return distancia;
	}

	public void setDistancia(Float distancia) {
		this.distancia = distancia;
	}
	
	
	public void adicionarTrecho(Trecho trecho) {
		
		destino = trecho.getDestino();
		trechos.add(trecho);
		distancia += trecho.getDistancia();
	}
	
	

}
