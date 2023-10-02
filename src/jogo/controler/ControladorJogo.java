package jogo.controler;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import jogo.model.FabricaModelJogoVelha;
import jogo.model.IModeloJogoVelha;
import jogo.model.IModeloJogoVelha.Jogador;

/* Classe: Controlador do Jogo */
public class ControladorJogo extends UnicastRemoteObject implements IControladorJogoVelha {

	private static final long serialVersionUID = 1L;

	private IModeloJogoVelha model;
	
	private int idUltimoJogador = 0;
	
	private boolean stopGame = false;
	
	private int i = 0;
	
	 private static int idJogador=0;
	
	public ControladorJogo() throws RemoteException {
		super();
		model = FabricaModelJogoVelha.create();
	}
	
	 // Cria um identificador para o jogador conectado
    @Override
    public int criarIdJogador() throws RemoteException{
        // Retorna o próximo identificador
        return ++idJogador;
    }
	
	@Override
	public void teste() {
		System.out.println("testando chamada remota, n�o restarta, mas funciona");
		i++;
		System.out.println("valor de i: "+ i);

	}
	
	@Override
	public void restart() {
		stopGame = false;
		model.limparTabuleiro();
	}
	
	@Override
	public void usuarioClicou(int idJogador, int indexLinha, int indexColuna) {
		if(idJogador != this.idUltimoJogador) {
			this.idUltimoJogador = idJogador;
			Jogador jogador = model.getRegistro(indexLinha, indexColuna);
			if(jogador.equals(Jogador.VAZIO) && !stopGame) {
				model.registreJogada(model.getVez(), indexLinha, indexColuna);
				if(model.verificaVitorias()) {
					model.registreGanhador(jogador);
					stopGame = true;
				}
				
			}
			
			model.registreVez(model.getVez() == Jogador.BOLINHA ?
					Jogador.XIS :
					Jogador.BOLINHA);
			
			if(stopGame) {
				model.getGanhador();
				model.getVitoriasBolinha();
				model.getVitoriasXis();
				restart();
			}
		}
	}

	@Override
	public boolean temCirculo(int indexLinha, int indexColuna) {
		return model.getRegistro(indexLinha, indexColuna) == Jogador.BOLINHA;
	}

	@Override
	public boolean temXis(int indexLinha, int indexColuna) {
		return model.getRegistro(indexLinha, indexColuna) == Jogador.XIS;
	}

	@Override
	public int getVitoriasXis() {
		return model.getVitoriasXis();
	}

	@Override
	public int getVitoriasCirculo() {
		return model.getVitoriasBolinha();
	}
	
	@Override
	public Jogador getJogador() {
		return model.getVez();
	}

}
