package jogo.model;

public interface IModeloJogoVelha {

	public enum Jogador {
		XIS, BOLINHA, VAZIO
	}	
	
	public void oPlus();
	
	public void xPlus();
	
	public boolean verificaVitorias();
	
	public void registreJogada(Jogador jog, int linha, int coluna);
	
	public Jogador getRegistro(int linha, int coluna);
	
	public void registreVez(Jogador jog);
	
	public Jogador getVez();
	
	public void registreGanhador(Jogador jog);
	
	public Jogador getGanhador();
	
	public void limparTabuleiro();
	
	public int getVitoriasXis();
	
	public int getVitoriasBolinha();
}
