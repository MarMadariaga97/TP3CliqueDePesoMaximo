package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Grafo {
	private boolean[][] matrizAdyacencia;
	private ArrayList<Vertice> vertices;
	private ArrayList<Vertice> verticesOrdenadosPorPeso;
	private Set<Integer>indicesCliquePesoMax=new HashSet<Integer>();
	double pesoCliquePesoMax=0;

	public Grafo() {
		this.vertices= new ArrayList<>();
	}

	public void agregarVertice(double peso) {
		Vertice v = new Vertice(peso);
		this.vertices.add(v);
	}

	public void agregarArco(Integer indiceV1, Integer indiceV2) {
		if(existeArco(indiceV1, indiceV2)) {
			throw new IllegalArgumentException("Ya existe el arco entre el vertice " + indiceV1 + " y el vertice "+ indiceV2);
		}
		else
			this.matrizAdyacencia[indiceV1][indiceV2] = true;
			this.matrizAdyacencia[indiceV2][indiceV1] = true;
	}
	
	public void setMatrizAdyacencia(int cantVertices) {
		this.matrizAdyacencia=new boolean[cantVertices][cantVertices];
		
	}
	
	public boolean [][] getMatrizAdyacencia() {
		return matrizAdyacencia;
	}
	
	
	public ArrayList<Vertice> getVertices() {
		return this.vertices;
	}
	
	public Set<Integer> getCliquePesoMax(){
		return this.indicesCliquePesoMax;
	}

	public boolean existeArco(int i, int j) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		return matrizAdyacencia[i][j];
	}

	// Cantidad de vertices
	public int tamano() {
		return matrizAdyacencia.length;
	}
	
	// Vecinos de un vertice
	public Set<Integer> vecinos(int i)
	{
		verificarVertice(i);
		
		Set<Integer> ret = new HashSet<Integer>();
		for(int j = 0; j < this.tamano(); ++j) if( i != j )
		{
			if( this.existeArco(i,j) )
				ret.add(j);
		}
		
		return ret;
	}
	
	public Integer grado(Integer i)
	{
		verificarVertice(i);
		return vecinos(i).size();
	}
	
	//Devuelve una lista con los vertices ordenados por peso descendente.
	public ArrayList<Vertice> verticesOrdenadosPorPeso(){
		
		ArrayList<Vertice> copiaOrdenada = this.getVertices();
		ArrayList<Vertice> ret = new ArrayList<>(copiaOrdenada);

		Collections.sort(ret);

		this.verticesOrdenadosPorPeso=ret;
	
		return verticesOrdenadosPorPeso;
		
	}
	
	
	
	public Set<Integer> vecinosEntreTodos(Vertice v) {
	    Set<Integer> vecinosEntreTodos = new HashSet<>();
	    Set<Integer> vecinosDelVertice = this.vecinos(v.getIndice());
	    
	    // Convertimos el set de vecinosDelVertice en una lista para acceder por índice
	    ArrayList<Integer> vecinosDeV = new ArrayList<>(vecinosDelVertice);

	    if (this.grado(v.getIndice()) > 1) {
	    	
	        for (int i = 0; i < vecinosDeV.size(); i++) {
                int vecino1 = vecinosDeV.get(i);
                
	            for (int j = i + 1; j < vecinosDeV.size(); j++) {
	                int vecino2 = vecinosDeV.get(j);
	                
	                // Verifica que exista arco entre vecino1 y vecino2
	                if (this.existeArco(vecino1, vecino2)) {
	                  
	                	// Verifica que vecino1 y vecino2 sean vecinos de todos los vértices en vecinosEntreTodos
	                    if ((vecinosEntreTodos.isEmpty() || esVecinoDeTodos(vecinosEntreTodos, vecino1)) && esVecinoDeTodos(vecinosEntreTodos, vecino2)) {
	                        vecinosEntreTodos.add(v.getIndice());
	                        vecinosEntreTodos.add(vecino1);
	                        vecinosEntreTodos.add(vecino2); 
	                    }
	                }
	            }
	        }
	    }

	    return vecinosEntreTodos;
	}
	
	
	public boolean esVecinoDeTodos(Set<Integer> vecinosEntreTodos, int nuevoVecino) {
	    for (int v : vecinosEntreTodos) {
	        // Verificar si nuevoVecino es vecino de v
	        if (v != nuevoVecino && !this.existeArco(v, nuevoVecino)) {
	            return false;
	        }
	    }
	    return true;
	}

	public Set<Integer> indicesVerticesCliquePesoMax() {
	    ArrayList<Vertice> conjVertices = verticesOrdenadosPorPeso();
	    Set<Integer> maxClique = new HashSet<>();
	    for (int i = 0; i < conjVertices.size() && maxClique.size()<1; i++) {
	        Vertice v = conjVertices.get(i);
	        Set<Integer> clique = vecinosEntreTodos(v); // Encuentra el clique para el vértice v

	        if (clique.size() > maxClique.size()) {
	            maxClique = clique; // Actualiza el clique máximo encontrado
	        }
	    }

	    indicesCliquePesoMax = maxClique;
	    return indicesCliquePesoMax;
	}
	
	public double getPesoClique() {
		
		for(Integer i : this.getCliquePesoMax()) {
			this.pesoCliquePesoMax=this.pesoCliquePesoMax+this.getVertices().get(i).getPeso();
		}
		
		return pesoCliquePesoMax;
	}
	public void resetPesoClique() {
		this.pesoCliquePesoMax=0;
	}
	
	public void resetVertices() {
		this.vertices= new ArrayList<>();
		Vertice.reiniciarUltIndiceAgregado();
	}
	public void resetIndicePesoMax(){
		this.indicesCliquePesoMax = new HashSet<Integer>();
	}
	
	// Verifica que sea un vertice valido
	private void verificarVertice(int i) {
		if (i < 0)
			throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);

		if (i >= matrizAdyacencia.length)
			throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V|-1: " + i);
	}

	// Verifica que i y j sean distintos
	private void verificarDistintos(int i, int j) {
		if (i == j)
			throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
	}

}

