package jogo.model;

public class FabricaModelJogoVelha {

	public static IModeloJogoVelha create() {
		return new ModelCadastroJogoVelha();
	}
}
