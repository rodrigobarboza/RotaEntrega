package ws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import dto.Rota;
import entity.Trecho;
import service.TracaRota;


@WebService
@SOAPBinding(style = Style.RPC)
public class CaminhoWS {

	@WebMethod
    public Caminho obterRotaMaisCurta(
    		@WebParam(name="origem") String origem,
    		@WebParam(name="destino") String destino, 
    		@WebParam(name="autonomiaKmPorLitro") Float autonomia, 
    		@WebParam(name="precoLitroCombustivel") Float preco) {
		
		Caminho caminho = new Caminho();
		
		try {
			
			TracaRota tracaRota = new TracaRota();
			Rota rota = tracaRota.obterRotaMaisCurta(origem, destino);
			if (rota != null) {
				caminho.setMensagem("OK");
				caminho.setCusto(calcularCusto(rota.getDistancia(), autonomia, preco));
				List<String> locais = new ArrayList<String>();
				locais.add(rota.getOrigem());
				for (Trecho trecho:rota.getTrechos()) {
					locais.add(trecho.getDestino());
				}
				caminho.setCaminho(locais);
				
			} else {
				
				caminho.setMensagem("Nenhum caminho encontrado");
			}
		} catch (Exception e) {
			e.printStackTrace();
			caminho.setMensagem("Erro ao tentar encontrar caminho");
		}
		return caminho;
	}

	private Float calcularCusto(Float distancia, Float autonomia, Float preco) {
		
		if (distancia == null || autonomia == null || preco == null || autonomia.equals(Float.valueOf(0F))) {
			return 0.0F;
		}
		Float custo = distancia / autonomia * preco;
		return custo;
	}
}
