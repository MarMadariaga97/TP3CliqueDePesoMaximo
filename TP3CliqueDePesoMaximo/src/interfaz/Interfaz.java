package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import logica.Grafo;
import logica.Vertice;

import javax.swing.JComboBox;

public class Interfaz {

	private JLayeredPane layeredPane;
	private JFrame frame;
	private JTextField textFieldCargarPeso;
	private Grafo g = new Grafo();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void cambiarPanel(JPanel panel) {
		panel.setVisible(true);
		this.layeredPane.removeAll();
		this.layeredPane.add(panel);
		this.layeredPane.repaint();
		this.layeredPane.revalidate();
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		this.layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 436, 252);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(null);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 0, 436, 263);
		layeredPane.add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JPanel panelCargarVertices = new JPanel();
		panelCargarVertices.setBounds(0, 0, 436, 252);
		layeredPane.add(panelCargarVertices);
		panelCargarVertices.setLayout(null);
		panelCargarVertices.setVisible(false);
		
		JPanel panelCargarArcos = new JPanel();
		panelCargarArcos.setBounds(0, 0, 436, 252);
		layeredPane.add(panelCargarArcos);
		panelCargarArcos.setLayout(null);
		panelCargarArcos.setVisible(false);

		JComboBox<Integer> comboBox1 = new JComboBox<>();
		comboBox1.setBounds(83, 70, 97, 22);
		panelCargarArcos.add(comboBox1);

		JComboBox<Integer> comboBox2 = new JComboBox<>();
		comboBox2.setBounds(243, 70, 97, 22);
		panelCargarArcos.add(comboBox2);
		
		JPanel panelVerCliquePesoMax = new JPanel();
		panelVerCliquePesoMax.setBounds(0, 0, 436, 252);
		layeredPane.add(panelVerCliquePesoMax);
		panelVerCliquePesoMax.setLayout(null);

		JLabel lblPrincipal = new JLabel("CLIQUE GOLOSA");
		lblPrincipal.setFont(new Font("Arial", Font.BOLD, 20));
		lblPrincipal.setBounds(118, 11, 183, 54);
		panelPrincipal.add(lblPrincipal);

		JLabel lbl1 = new JLabel("Para conocer la clique de peso máximo, vamos a ");
		lbl1.setFont(new Font("Arial", Font.PLAIN, 15));
		lbl1.setBounds(55, 82, 352, 45);
		panelPrincipal.add(lbl1);

		JLabel lbl2 = new JLabel("crear un grafo, en el cual cada vértice tendrá un peso,");
		lbl2.setFont(new Font("Arial", Font.PLAIN, 15));
		lbl2.setBounds(55, 102, 381, 45);
		panelPrincipal.add(lbl2);

		JLabel lbl3 = new JLabel("y arcos entre esos vértices, ambos ingresados por ud.");
		lbl3.setFont(new Font("Arial", Font.PLAIN, 15));
		lbl3.setBounds(55, 123, 381, 45);
		panelPrincipal.add(lbl3);

		JButton btnComenzar = new JButton("COMENZAR");
		btnComenzar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPanel(panelCargarVertices);
			}
		});
		btnComenzar.setFont(new Font("Arial", Font.BOLD, 15));
		btnComenzar.setBounds(147, 209, 138, 23);
		panelPrincipal.add(btnComenzar);


		JLabel lblCargarPeso = new JLabel("Ingrese el peso del vértice");
		lblCargarPeso.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCargarPeso.setBounds(128, 11, 176, 34);
		panelCargarVertices.add(lblCargarPeso);

		textFieldCargarPeso = new JTextField();
		textFieldCargarPeso.setBounds(176, 43, 96, 20);
		panelCargarVertices.add(textFieldCargarPeso);
		textFieldCargarPeso.setColumns(10);

		JButton btnAgregarVertice = new JButton("OK");
		btnAgregarVertice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Double.parseDouble(textFieldCargarPeso.getText()) >= 0) {
					g.agregarVertice(Double.valueOf(textFieldCargarPeso.getText()));
					textFieldCargarPeso.setText("");

				}
			}
		});
		btnAgregarVertice.setBounds(160, 74, 124, 23);
		panelCargarVertices.add(btnAgregarVertice);

		JLabel lblAclaracion = new JLabel("*Al presionar \"OK\" se crea un vértice en el grafo con el peso ingresado.");
		lblAclaracion.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAclaracion.setBounds(10, 158, 399, 27);
		panelCargarVertices.add(lblAclaracion);

		JButton btnAgregarArcos = new JButton("Agregar arcos");
		btnAgregarArcos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				g.setMatrizAdyacencia(g.getVertices().size());

				for (Vertice v : g.getVertices()) {
					comboBox1.addItem(v.getIndice());
					comboBox2.addItem(v.getIndice());
				}

				cambiarPanel(panelCargarArcos);
			}
		});
		btnAgregarArcos.setBounds(160, 108, 124, 23);
		panelCargarVertices.add(btnAgregarArcos);

		JLabel lblAclaracion2 = new JLabel("*Tenga en cuenta que al crear el vértice los indices suman de a uno,");
		lblAclaracion2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAclaracion2.setBounds(10, 181, 399, 27);
		panelCargarVertices.add(lblAclaracion2);

		JLabel lblAclaracion3 = new JLabel("comenzando por el índice 0.");
		lblAclaracion3.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAclaracion3.setBounds(20, 196, 399, 27);
		panelCargarVertices.add(lblAclaracion3);

		JLabel lblAclaracion4 = new JLabel("*El peso tiene que ser un número posotivo.");
		lblAclaracion4.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAclaracion4.setBounds(10, 220, 383, 21);
		panelCargarVertices.add(lblAclaracion4);

		

		JLabel lblPesoIndice1 = new JLabel("");
		lblPesoIndice1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPesoIndice1.setBounds(83, 103, 107, 21);
		panelCargarArcos.add(lblPesoIndice1);

		JLabel lblPesoIndice2 = new JLabel("");
		lblPesoIndice2.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPesoIndice2.setBounds(243, 103, 107, 21);
		panelCargarArcos.add(lblPesoIndice2);

		comboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer indice = (Integer) comboBox1.getSelectedItem();
				if (indice != null && indice >= 0 && indice < g.getVertices().size()) {
					lblPesoIndice1.setText("Peso " + Double.toString(g.getVertices().get(indice).getPeso()));
				} else {
					lblPesoIndice1.setText("indice null"); // o algún mensaje de error o estado vacío
				}
			}
		});

		comboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer indice = (Integer) comboBox2.getSelectedItem();
				if (indice != null && indice >= 0 && indice < g.getVertices().size()) {
					lblPesoIndice2.setText("Peso " + Double.toString(g.getVertices().get(indice).getPeso()));
				} else {
					lblPesoIndice2.setText("indice null"); // o algún mensaje de error o estado vacío
				}
			}
		});

		JLabel lblAgregarArcos = new JLabel("Agregue los arcos del grafo, eligiendo dos vértices distintos. ");
		lblAgregarArcos.setFont(new Font("Arial", Font.PLAIN, 15));
		lblAgregarArcos.setBounds(20, 27, 416, 32);
		panelCargarArcos.add(lblAgregarArcos);
		
		JLabel lbltextCliqueResultadoIndices = new JLabel("");
		lbltextCliqueResultadoIndices.setFont(new Font("Arial", Font.PLAIN, 12));
		lbltextCliqueResultadoIndices.setBounds(53, 81, 354, 65);
		panelVerCliquePesoMax.add(lbltextCliqueResultadoIndices);

		JLabel lbltextCliqueResultadoPeso = new JLabel("");
		lbltextCliqueResultadoPeso.setFont(new Font("Arial", Font.PLAIN, 12));
		lbltextCliqueResultadoPeso.setBounds(53, 116, 354, 65);
		panelVerCliquePesoMax.add(lbltextCliqueResultadoPeso);
		panelVerCliquePesoMax.setVisible(false);
		
		JLabel lblCliquePesoMax = new JLabel("CLIQUE DE PESO MÁXIMO");
		lblCliquePesoMax.setFont(new Font("Arial", Font.PLAIN, 20));
		lblCliquePesoMax.setBounds(95, 11, 261, 74);
		panelVerCliquePesoMax.add(lblCliquePesoMax);

		JButton btnCargarArco = new JButton("Cargar arco");
		btnCargarArco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					g.agregarArco((Integer) comboBox1.getSelectedItem(), (Integer) comboBox2.getSelectedItem());
				
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		btnCargarArco.setBounds(154, 149, 132, 32);
		panelCargarArcos.add(btnCargarArco);

		JButton btnListo = new JButton("Listo!");
		btnListo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String indices = "";
				Set<Integer> indicesVertices = g.indicesVerticesCliquePesoMax();

				for (Integer i : indicesVertices) {
					indices = indices + " " + i;
				}
				lbltextCliqueResultadoIndices.setText("La clique de peso maximo es {" + indices + " }");
				lbltextCliqueResultadoPeso.setText(" y tiene un peso de " + g.getPesoClique());

				cambiarPanel(panelVerCliquePesoMax);
			}
		});
		btnListo.setFont(new Font("Arial", Font.BOLD, 15));
		btnListo.setBounds(154, 209, 132, 32);
		panelCargarArcos.add(btnListo);


		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g.resetPesoClique();
				g.resetIndicePesoMax();
				cambiarPanel(panelCargarArcos);
			}
		});
		btnVolver.setBounds(160, 180, 124, 23);
		panelVerCliquePesoMax.add(btnVolver);

		JButton btnReiniciar = new JButton("Reiniciar");
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Lógica para reiniciar la aplicación
				g = new Grafo();
				g.resetVertices();
				textFieldCargarPeso.setText("");
				lblPesoIndice1.setText("");
				lblPesoIndice2.setText("");
				lbltextCliqueResultadoIndices.setText("");
				lbltextCliqueResultadoPeso.setText("");

				comboBox1.removeAllItems();
				comboBox2.removeAllItems();
				cambiarPanel(panelPrincipal);
			}
		});
		btnReiniciar.setBounds(160, 225, 124, 23);
		panelVerCliquePesoMax.add(btnReiniciar);
	}

}
