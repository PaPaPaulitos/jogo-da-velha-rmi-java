package jogo.model;

public class ModelCadastroJogoVelha implements IModeloJogoVelha {

	public static int winsX = 0;
	public static int winsO = 0;

	private Jogador[][] tabuleiro;
	private Jogador vez;
	private Jogador ganhador;
	private int vxis = 0;
	private int vbolinha = 0;
	
	public ModelCadastroJogoVelha() {
		tabuleiro = new Jogador[3][3];
		vez = Jogador.VAZIO;
		ganhador = Jogador.VAZIO;
		limparTabuleiro();
	}
	
	@Override
	public void registreJogada(Jogador jog, int linha, int coluna) {
		tabuleiro[linha][coluna] = jog;
	}
	


	@Override
	public Jogador getRegistro(int linha, int coluna) {
		return tabuleiro[linha][coluna];
	}

	@Override
	public void registreVez(Jogador jog) {
		vez = jog;
	}

	@Override
	public Jogador getVez() {
		return vez;
	}

	@Override
	public void limparTabuleiro() {
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				tabuleiro[i][j] = Jogador.VAZIO;
	}

	@Override
	public void registreGanhador(Jogador jog) {
		if(jog == Jogador.VAZIO) {
			jog = getVez();
		}
		if(jog == Jogador.XIS) {			
			ganhador = jog;
			vxis++;
		}
		else if(jog == Jogador.BOLINHA) {			
			ganhador = jog;
			vbolinha++;
		}
	}

	@Override
	public Jogador getGanhador() {
		return ganhador;
	}

	@Override
	public int getVitoriasXis() {
		return vxis;
	}

	@Override
	public int getVitoriasBolinha() {
		return vbolinha;
	}

	@Override
	public boolean verificaVitorias() {
		String[] tipoVitorias = {
			"00 01 02",
			"00 10 20",
			"00 11 22",
			"01 11 21",
			"01 00 02",
			"02 01 00",
			"02 11 20",
			"02 12 22",
			"10 00 20",
			"10 11 12",
			"11 01 21",
			"11 10 12",
			"11 00 22",
			"11 20 02",
			"12 02 22",
			"12 11 10",
			"20 10 00",
			"20 11 02",
			"20 21 22",
			"21 11 01",
			"21 20 22",
			"22 12 02",
			"22 21 20",
			"22 11 00",
		};
		
		
		for (int i=0; i<tipoVitorias.length; i++) {
			String line = tipoVitorias [i];
			String [] lineArray = line.split(" ");
			Jogador[] items = new Jogador[3];
			for(int j=0;j<lineArray.length;j++) {
				String[] cedula = lineArray[j].split("");
				Jogador jogador = tabuleiro[Integer.parseInt(cedula[0])][Integer.parseInt(cedula[1])];
				items[j]= jogador;
			}
			
			if(items[0] == items[1] && items[1] == items[2] && (items[0] != Jogador.VAZIO && items[1] != Jogador.VAZIO && items[2] != Jogador.VAZIO)) {
				return true;
			}

		}
		return false;
	}

	@Override
	public void oPlus() {
		winsO++;
		
	}

	@Override
	public void xPlus() {
		winsX++;
	}
}
