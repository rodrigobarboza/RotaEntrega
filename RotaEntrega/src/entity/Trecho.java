package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity(name="TRECHO")
public class Trecho {

	@Id
	@GeneratedValue
	@Column(name="TRECHO_ID")
	private Integer id;
	
	@Column(name="ORIGEM")
	private String origem;
	
	@Column(name="DESTINO")
	private String destino;
	
	@Column(name="DISTANCIA")
	private Float distancia;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrigem() {
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

	public Float getDistancia() {
		return distancia;
	}

	public void setDistancia(Float distancia) {
		this.distancia = distancia;
	}
}
