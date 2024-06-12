package logica;


public class Vertice implements Comparable<Vertice> {
	private static Integer ultIndiceAgregado = -1;
	private double peso;
	private Integer indice;

	public Vertice(double p) {
		ultIndiceAgregado++;
		this.peso = p;
		this.indice = ultIndiceAgregado;
	}

	public Integer getIndice() {
		return this.indice;
	}

	public double getPeso() {
		return this.peso;
	}

    public static void reiniciarUltIndiceAgregado() {
        ultIndiceAgregado = -1;
    }
    
	@Override
	public int compareTo(Vertice otro) {
		return (int) (otro.getPeso() - this.getPeso());
	}

}