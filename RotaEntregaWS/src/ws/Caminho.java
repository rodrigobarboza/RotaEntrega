package ws;

import java.io.Serializable;
import java.util.List;

public class Caminho implements Serializable {

	private static final long serialVersionUID = -7904891636701615461L;

	private List<String> caminho;
	private Float custo;
	private String mensagem;
	
	public List<String> getCaminho() {
		return caminho;
	}
	public void setCaminho(List<String> caminho) {
		this.caminho = caminho;
	}
	public Float getCusto() {
		return custo;
	}
	public void setCusto(Float custo) {
		this.custo = custo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}