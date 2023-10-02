package jogo.controler;
import java.rmi.Remote;
import java.rmi.RemoteException;

import jogo.model.IModeloJogoVelha.Jogador;

/* Interface Controlador do Jogo */
public interface IControladorJogoVelha extends Remote {

	public void restart() throws RemoteException;

	public void usuarioClicou(int idJogador, int indexLinha, int indexColuna) throws RemoteException;
	
	public boolean temCirculo(int indexLinha, int indexColuna) throws RemoteException;
	
	public boolean temXis(int indexLinha, int indexColuna) throws RemoteException;

	public int getVitoriasXis() throws RemoteException;

	public int getVitoriasCirculo() throws RemoteException;

	void teste() throws RemoteException;
	
	// Cria um identificador para o jogador conectado
    public int criarIdJogador() throws RemoteException;
    
    public Jogador getJogador() throws RemoteException;

}
