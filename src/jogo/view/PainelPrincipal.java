package jogo.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import jogo.controler.IControladorJogoVelha;

/*Classe Painel Principal*/
public class PainelPrincipal extends JPanel implements ActionListener {

	private static final long serialVersionUID = 7416550384424848702L;
	
	private IControladorJogoVelha controlador;
	private JLabel labelvx;
	private JLabel labelvo;
	private JLabel labelTexto;

	
	Timer timer= new Timer(500, this);

	
	public PainelPrincipal(int idJogador, JLabel labelTexto, JLabel labelvx, JLabel labelvo, IControladorJogoVelha factory) throws RemoteException {
		
		this.labelvx = labelvx;
		this.labelvo = labelvo;
		this.labelTexto = labelTexto;

		controlador = factory;
		timer.start();
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					int x = e.getX();
					int y = e.getY();
					
					int indexLinha = x / calculaMenorDimensao();
					int indexColuna = y / calculaMenorDimensao();
				
					System.out.println("Fazendo chamada RMI");
					controlador.usuarioClicou(idJogador, indexLinha, indexColuna);
					
					repaint();
				} catch (RemoteException e2) {
					
					e2.printStackTrace();
				}
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		int size = calculaMenorDimensao();
		
		try {
		// desenha as linhas
		g2.setColor(Color.red);		
		for(int i = 1; i <= 2; i++)
			g2.drawLine(size * i, 0, size * i, size * 3);
		for(int j = 1; j <= 2; j++)
			g2.drawLine(0, size * j, size * 3, size * j);
		
		// desenha os X e as Bolas
		g2.setColor(Color.black);
		for(int i = 0; i <= 2; i++)
			for(int j = 0; j <= 2; j++) {
				if(this.controlador.temCirculo(i, j)) {
					g2.drawOval(i * size + 10, j * size + 10, size - 20, size - 20);
				}
				if(this.controlador.temXis(i, j)) {
					g2.drawLine(i * size + 10, j * size + 10, 
							(i + 1) * size - 10, (j + 1) * size - 10);
					g2.drawLine((i + 1) * size - 10, j * size + 10, 
							i * size + 10, (j + 1) * size - 10);
				}
			}
		
		labelvx.setText("" + this.controlador.getVitoriasXis());
		labelvo.setText("" + this.controlador.getVitoriasCirculo());
		labelTexto.setText("Vez do Jogador : " + this.controlador.getJogador());
		} catch (RemoteException e1) {
			
			e1.printStackTrace();
		}
	}
	
	private int calculaMenorDimensao() {
		int dimensao = getSize().height;
		
		if(dimensao > getSize().width)
			dimensao = getSize().width;
		
		return dimensao/3;
	}

	public void restart() throws RemoteException {
		this.controlador.restart();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		  if(ev.getSource()==timer){
			// O chama a cada 2 segundos		  
	      repaint();
	    }
		
	}

}
