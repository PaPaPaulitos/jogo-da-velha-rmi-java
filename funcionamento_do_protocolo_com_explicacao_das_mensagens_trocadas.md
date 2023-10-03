# Funcionamento do protocolo com explicação das mensagens trocadas

No nosso sistema distribuído de jogo da velha, utilizando o Java RMI para habilitar a interação entre os dois jogadores e o servidor central. Esse sistema permite que dois jogadores joguem o jogo da velha em máquinas diferentes, mantendo a integridade do tabuleiro e garantindo a sincronização adequada das jogadas.

O servidor central do jogo da velha assume a responsabilidade de gerenciar o estado do tabuleiro e coordenar as jogadas entre os jogadores. O processo de jogar é definido da seguinte forma:

**Determinação do Jogador Atual**: O servidor controla a lógica do jogo e determina qual dos dois jogadores é o próximo a jogar. Ele então notifica o jogador da vez para fazer sua jogada.

> Assim como o painel cria o Id do jogador:

```java
	// Solicita novo identificador para o usuário
    private void criarIdUsuario() throws Exception{
        this.idJogador = this.controlador.criarIdJogador();
    }
```

Caminho no código:

```
\jogo-da-velha-rmi-java\src\jogo\view\PainelDoJogo.java
```

> Para que o Controller retorne uma *Response* para a View para dizer qual é o jogador atual

```java
    @Override
    public int criarIdJogador() throws RemoteException{
        // Retorna o próximo identificador
        return ++idJogador;
    }
    
```

Caminho no código:

```
\jogo-da-velha-rmi-java\src\jogo\controler\ControladorJogo.java
```

**Jogador Realiza a Jogada**: O jogador da vez realiza sua jogada escolhendo uma posição no tabuleiro. Após fazer a jogada, ele envia uma mensagem RMI ao servidor, transmitindo as coordenadas da jogada, ou seja, a coluna e a linha onde fez sua jogada.

**Verificação da Jogada**: O servidor recebe a mensagem RMI do jogador e verifica se a jogada é válida, garantindo que a posição escolhida esteja vazia e dentro dos limites do tabuleiro. Se a jogada for válida, o servidor atualiza o estado do tabuleiro com o símbolo do jogador.


> Assim como o Controller verifica essas regras de negócio:

```java
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
```

Caminho no código:

```
\jogo-da-velha-rmi-java\src\jogo\controler\ControladorJogo.java
```

**Atualização do Tabuleiro**: Após a verificação bem-sucedida, o servidor envia as atualizações do tabuleiro para ambos os jogadores, incluindo a representação atualizada do tabuleiro. Isso permite que os jogadores visualizem o estado atual do jogo e se preparem para a próxima jogada.

> Assim como o metódo ModelCadastroJogoVelha:

```java
@Override
public void registreJogada(Jogador jog, int linha, int coluna) {
    tabuleiro[linha][coluna] = jog;
}
```

Caminho no código

```
\jogo-da-velha-rmi-java\src\jogo\model\ModelCadastroJogoVelha.java
```

A classe *ControladorJogo* utiliza-se do *ModelCadastroJogoVelha* com o *UnicastRemoteObject* para fazer a atualização dessas jogadas.

```java
public class ControladorJogo extends UnicastRemoteObject implements IControladorJogoVelha
```

Caminho no código

```
\jogo-da-velha-rmi-java\src\jogo\controler\ControladorJogo.java
```
