package logica;

public class Arco {

private Vertice vert1, vert2;

 public Arco(Vertice v1, Vertice v2) {
		this.vert1=v1;
		this.vert2=v2;	 
	 }
	 
 public Vertice getV1() {
		return this.vert1;
	 }
	 
 public Vertice getV2() {
		 return this.vert2;
	 }
}
