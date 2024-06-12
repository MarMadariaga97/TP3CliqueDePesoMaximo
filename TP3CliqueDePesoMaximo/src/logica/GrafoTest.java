package logica;

import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class GrafoTest {
	
	
	@Test
	public void agregarVertice() {
		Vertice.reiniciarUltIndiceAgregado();
		
		Grafo g=new Grafo();
		g.agregarVertice(3);
		g.agregarVertice(7);
		g.agregarVertice(1);
		
		g.setMatrizAdyacencia(g.getVertices().size());
		
		assertEquals(3, g.getVertices().size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarArcoRepetido(){
		Vertice.reiniciarUltIndiceAgregado();
		Grafo g=new Grafo();
		
		g.agregarVertice(3);
		g.agregarVertice(7);
		g.agregarVertice(1);
		
		g.setMatrizAdyacencia(g.getVertices().size());
		
		g.agregarArco(0, 1);
		g.agregarArco(1, 0);
	}
		
	@Test
	public void agregarArcoCorrectamente() {
		Vertice.reiniciarUltIndiceAgregado();
		Grafo g=new Grafo();
		
		g.agregarVertice(3);
		g.agregarVertice(7);
		g.agregarVertice(1);
		g.agregarVertice(2);
		
		g.setMatrizAdyacencia(g.getVertices().size());
		
		g.agregarArco(0, 3);
		
		boolean[][] mAdyacencia=g.getMatrizAdyacencia();

		assertTrue(mAdyacencia[3][0]);
	}	
	
	@Test
	public void vecinosTest() {
		Vertice.reiniciarUltIndiceAgregado();
		Grafo g=new Grafo();
		
		g.agregarVertice(3);
		g.agregarVertice(7);
		g.agregarVertice(1);
		g.agregarVertice(2);
		g.agregarVertice(10);
		g.setMatrizAdyacencia(g.getVertices().size());
		
		Set<Integer>v=new HashSet<>();

		g.agregarArco(0, 3);
		v.add(3);
		g.agregarArco(0, 4);
		v.add(4);
		g.agregarArco(1, 0);
		v.add(1);
		g.agregarArco(4, 2);
		
		assertEquals(g.vecinos(0), v);
	}
	
	@Test 
	public void ningunVecinoTest() {
		Vertice.reiniciarUltIndiceAgregado();
		Grafo g=new Grafo();
		
		g.agregarVertice(7);
		g.agregarVertice(3);
		g.agregarVertice(7);
		g.agregarVertice(1);
		g.agregarVertice(2);
		
		g.setMatrizAdyacencia(g.getVertices().size());
		
		Set<Integer>vecinosDeIndice3=new HashSet<>();
		
		assertEquals(g.vecinos(0), vecinosDeIndice3);
	}
	
	@Test
	public void unSoloVecinoTest() {
		Vertice.reiniciarUltIndiceAgregado();
		Grafo g=new Grafo();
		
		g.agregarVertice(3);
		g.agregarVertice(5);
		g.agregarVertice(15);
		g.agregarVertice(7);
		g.setMatrizAdyacencia(g.getVertices().size());
		
		Set<Integer>vecinos=new HashSet<>();

		g.agregarArco(0, 2);
		vecinos.add(2);
		
		assertEquals(g.vecinos(0), vecinos);		
	}
	
	@Test
	public void vOrdenadosPorPeso() {
		Vertice.reiniciarUltIndiceAgregado();
		Grafo g=new Grafo();
		
		g.agregarVertice(2.5);
		g.agregarVertice(11.5);
		g.agregarVertice(7);
		g.agregarVertice(20.2);
		
		Grafo nuevo=new Grafo();
		
		nuevo.agregarVertice(20.2);
		nuevo.agregarVertice(11.5);
		nuevo.agregarVertice(7);
		nuevo.agregarVertice(2.5);
		
		assertEquals(0, g.verticesOrdenadosPorPeso().get(0).compareTo(nuevo.getVertices().get(0)));
		assertEquals(0, g.verticesOrdenadosPorPeso().get(1).compareTo(nuevo.getVertices().get(1)));
		assertEquals(0, g.verticesOrdenadosPorPeso().get(2).compareTo(nuevo.getVertices().get(2)));
		assertEquals(0, g.verticesOrdenadosPorPeso().get(3).compareTo(nuevo.getVertices().get(3)));	
	}
	
	@Test
	public void todosConIgualPeso() {
		Vertice.reiniciarUltIndiceAgregado();
		Grafo g=new Grafo();
		
		g.agregarVertice(3.7);
		g.agregarVertice(3.7);
		g.agregarVertice(3.7);
		g.agregarVertice(3.7);
		
		assertEquals(0, g.getVertices().get(0).compareTo(g.verticesOrdenadosPorPeso().get(0)));
		assertEquals(0, g.getVertices().get(1).compareTo(g.verticesOrdenadosPorPeso().get(1)));
		assertEquals(0, g.getVertices().get(2).compareTo(g.verticesOrdenadosPorPeso().get(2)));
		assertEquals(0, g.getVertices().get(3).compareTo(g.verticesOrdenadosPorPeso().get(3)));
	}	
	
	//Test de esVecinoDeTodos
	@Test 
	public void verticeNoEsVecinoDeTodos() {
		Vertice.reiniciarUltIndiceAgregado();
		Grafo g=new Grafo();
		
		g.agregarVertice(10);
		g.agregarVertice(11.3);
		g.agregarVertice(7);
		g.agregarVertice(20.2);
		g.agregarVertice(0.1);
		g.agregarVertice(10.6);
		g.agregarVertice(5);
		
		g.setMatrizAdyacencia(g.getVertices().size());
		
		g.agregarArco(1, 0);
		g.agregarArco(1, 4);
		g.agregarArco(1, 3);
		g.agregarArco(1, 5);
		g.agregarArco(4, 0);
		g.agregarArco(4, 3);
		
		
		
		Set<Integer>vecinosDeVerticeIndice1=g.vecinos(1);
		
		assertFalse(g.esVecinoDeTodos(vecinosDeVerticeIndice1, 4));
	
	}
	
	@Test 
	public void verticeEsVecinoDeTodos() {
		Vertice.reiniciarUltIndiceAgregado();
		Grafo g=new Grafo();
		
		g.agregarVertice(10);
		g.agregarVertice(11.3);
		g.agregarVertice(7);
		g.agregarVertice(20.2);
		g.agregarVertice(0.1);
		g.agregarVertice(10.6);
		g.agregarVertice(5);
		
		g.setMatrizAdyacencia(g.getVertices().size());
		
		g.agregarArco(1, 0);
		g.agregarArco(1, 4);
		g.agregarArco(1, 3);
		g.agregarArco(1, 5);
		g.agregarArco(4, 0);
		g.agregarArco(4, 3);
		g.agregarArco(4, 5);
		
		
		
		Set<Integer>vecinosDeVerticeIndice1=g.vecinos(1);
		
		assertTrue(g.esVecinoDeTodos(vecinosDeVerticeIndice1, 4));
	
	}
	
	//Test de vecinosEntreTodos	
	@Test
	public void verticeConMenosde2Vecinos() {
		Vertice.reiniciarUltIndiceAgregado();
		Grafo g=new Grafo();
		
		g.agregarVertice(10);
		g.agregarVertice(11.3);
		g.agregarVertice(7);
		g.agregarVertice(5);
		
		g.setMatrizAdyacencia(g.getVertices().size());
		
		g.agregarArco(1, 0);
		
		Set<Integer>vacio=new HashSet<Integer>();
		Set<Integer>set=g.vecinosEntreTodos(g.getVertices().get(0));
		
		assertEquals(vacio.size(), set.size());
	}
		
	@Test
	public void tieneAlMenos2VecinosPeroNigunoEsVecino() {
		Vertice.reiniciarUltIndiceAgregado();
		Grafo g=new Grafo();
		
		g.agregarVertice(10);
		g.agregarVertice(11.3);
		g.agregarVertice(7);
		g.agregarVertice(5);
		g.agregarVertice(13);
		
		g.setMatrizAdyacencia(g.getVertices().size());
		
		g.agregarArco(1, 0);
		g.agregarArco(1, 2);
		g.agregarArco(1, 3);
		g.agregarArco(1, 4);
		
		Set<Integer>ret=new HashSet<Integer>();
		Set<Integer>set=g.vecinosEntreTodos(g.getVertices().get(1));
		
		assertEquals(set.size(), ret.size());
	}
	
	@Test
	public void tieneAlMenos2VecinosYAlgunosSonVecinosEntreSi() {
		Vertice.reiniciarUltIndiceAgregado();
		Grafo g=new Grafo();
		
		g.agregarVertice(10);
		g.agregarVertice(11.3);
		g.agregarVertice(7);
		g.agregarVertice(5);
		g.agregarVertice(13);
		
		g.setMatrizAdyacencia(g.getVertices().size());
		
		g.agregarArco(1, 0);
		g.agregarArco(1, 2);
		g.agregarArco(1, 3);
		g.agregarArco(1, 4);
		g.agregarArco(2, 4);
		g.agregarArco(3, 2);
		
		
		Set<Integer>set=g.vecinosEntreTodos(g.getVertices().get(1));
		
		assertEquals(3, set.size());
	}
	
	/*----------------------------------------------
	public Set<Integer> indicesVerticesCliquePesoMax() {
	    ArrayList<Vertice> conjVertices = verticesOrdenadosPorPeso();
	    Set<Integer> maxClique = new HashSet<>();

	    for (int i = 0; i < conjVertices.size(); i++) {
	        Vertice v = conjVertices.get(i);
	        Set<Integer> clique = vecinosEntreTodos(v); // Encuentra el clique para el vértice v

	        if (clique.size() > maxClique.size()) {
	            maxClique = clique; // Actualiza el clique máximo encontrado
	        }
	    }

	    indicesCliquePesoMax = maxClique;
	    return indicesCliquePesoMax;
	}*/
	//Test indicesVerticesCliquePesoMaximo
	@Test 
	public void elGrafoNoTieneCliques() {
		Vertice.reiniciarUltIndiceAgregado();
		Grafo g=new Grafo();
		
		g.agregarVertice(10);
		g.agregarVertice(11.3);
		g.agregarVertice(7);
		g.agregarVertice(5);
		g.agregarVertice(13);
		
		g.setMatrizAdyacencia(g.getVertices().size());
		
		g.agregarArco(0, 1);
		g.agregarArco(0, 2);
		g.agregarArco(0, 4);
		g.agregarArco(1, 3);
		
		assertEquals(0, g.indicesVerticesCliquePesoMax().size());
	}
	
	//
	@Test
	public void elGrafoTieneCliqueDePesoMaximo() {
		Vertice.reiniciarUltIndiceAgregado();
		Grafo g=new Grafo();
		
		g.agregarVertice(11);
		g.agregarVertice(5.5);
		g.agregarVertice(1.1);
		g.agregarVertice(7);
		g.agregarVertice(2.5);
		g.agregarVertice(3.5);
		
		g.setMatrizAdyacencia(g.getVertices().size());
		
		g.agregarArco(0, 1);
		g.agregarArco(0, 3);
		g.agregarArco(1, 2);
		g.agregarArco(1, 3);
		g.agregarArco(1, 4);
		g.agregarArco(1, 5);
		g.agregarArco(2, 4);
		g.agregarArco(4, 3);
		g.agregarArco(5, 3);
		g.agregarArco(4, 5);
		
		Set<Integer>cliqueDeGrafoG=g.indicesVerticesCliquePesoMax();

		assertTrue(cliqueDeGrafoG.contains(0));
		assertTrue(cliqueDeGrafoG.contains(1));
		assertTrue(cliqueDeGrafoG.contains(3));
	}
	
	

}

