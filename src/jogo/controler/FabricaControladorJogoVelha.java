package jogo.controler;

import java.rmi.RemoteException;

/*Classe:  Fabrica Controlador Jogo da Velha */
public class FabricaControladorJogoVelha {

	public static IControladorJogoVelha create() throws RemoteException {
		return new ControladorJogo();
	}
}
