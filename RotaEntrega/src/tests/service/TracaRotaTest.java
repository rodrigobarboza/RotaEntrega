package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import dao.TrechoDAO;
import dto.Rota;
import entity.Trecho;


@RunWith(MockitoJUnitRunner.class)
public class TracaRotaTest {

	 @InjectMocks
	 TracaRota tracaRota = new TracaRota();
	 
	 @Mock
	 TrechoDAO trechoDAO = new TrechoDAO();	
	

	/**
	 * Prepara o dao para retornar as informações de origem, destino e distancia abaixo
	 * 
	 * A B 10
	 * A C 20
	 * B D 15
	 * C D 30
	 */
	@Before
	public void init() {
	    when(trechoDAO.findByExample(any(Trecho.class))).thenAnswer(new Answer<List<Trecho>>() {
	        @Override
	        public List<Trecho> answer(InvocationOnMock invocation) throws Throwable {
	            Object[] args = invocation.getArguments();
	            Trecho trecho = (Trecho) args[0];
	            List<Trecho> trechos = new ArrayList<Trecho>();
	            if (trecho.getOrigem().equals("A")) {
	            	adicionarTrecho(trechos, "A", "B", 10f);
	            	adicionarTrecho(trechos, "A", "C", 20f);
	            } else if(trecho.getOrigem().equals("B")) {
	            	adicionarTrecho(trechos, "B", "D", 15f);
	            } else if(trecho.getOrigem().equals("C")) {
	            	adicionarTrecho(trechos, "C", "D", 30f);
	            } 
	            return trechos;
	        }
	    });
	}

	@Test
	public void obterRotaMaisCurta() {
		
		Rota rota = tracaRota.obterRotaMaisCurta("A", "B");
		assertEquals("A", rota.getOrigem());
		assertEquals("B", rota.getDestino());
		assertTrue(10F == rota.getDistancia().floatValue());
		
		rota = tracaRota.obterRotaMaisCurta("A", "D");
		assertEquals("A", rota.getOrigem());
		assertEquals("D", rota.getDestino());
		assertTrue(2 == rota.getTrechos().size());
		assertTrue(25F == rota.getDistancia().floatValue());
		assertEquals("A", rota.getTrechos().get(0).getOrigem());
		assertEquals("B", rota.getTrechos().get(0).getDestino());
		assertEquals("B", rota.getTrechos().get(1).getOrigem());
		assertEquals("D", rota.getTrechos().get(1).getDestino());
	}
	
	
	private void adicionarTrecho(List<Trecho> trechos, String origem, String destino, Float distancia) {
		
		Trecho trecho = new Trecho();
		trecho.setDestino(destino);
		trecho.setOrigem(origem);
		trecho.setDistancia(distancia);
		trechos.add(trecho);
	}
	
	 
}
