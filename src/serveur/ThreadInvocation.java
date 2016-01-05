/**
 * 
 */
package serveur;

import java.awt.Point;
import java.rmi.RemoteException;
import java.util.HashMap;

import client.StrategieSbire;
import logger.LoggerProjet;
import serveur.element.Caracteristique;
import utilitaires.Constantes;

/**
 * @author clement
 *
 */
public class ThreadInvocation extends Thread {

	String ipArene;
	int port;
	String ipConsole;
	String nom;
	String groupe;
	HashMap<Caracteristique, Integer> caracts;
	int nbTours;
	Point position;
	LoggerProjet logger;
	int maitre;
	
	
	
	public ThreadInvocation(String ipArene, int port, String ipConsole, String nom, String groupe,
			HashMap<Caracteristique, Integer> caracts, int nbTours, Point position, LoggerProjet logger, int maitre) {
		super();
		this.ipArene = ipArene;
		this.port = port;
		this.ipConsole = ipConsole;
		this.nom = nom;
		this.groupe = groupe;
		this.caracts = caracts;
		this.nbTours = nbTours;
		this.position = position;
		this.logger = logger;
		this.maitre = maitre;
		
		start();
	}



	@Override
	public void run() {
		new StrategieSbire(ipArene, port, ipConsole, nom, groupe, caracts, nbTours, position, logger, maitre);
	}


}
