/**
 * 
 */
package serveur;

import java.awt.Point;
import java.util.HashMap;

import client.StrategieSbire;
import logger.LoggerProjet;
import serveur.element.Caracteristique;

/**
 * @author clement
 *
 */
public class ThreadInvocation extends Thread {

	Arene arene;
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
	
	public ThreadInvocation(Arene arene, String ipArene, int port, String ipConsole, String nom, String groupe,
			int nbTours, Point position, LoggerProjet logger, int maitre) {
		super();
		this.arene = arene;
		this.ipArene = ipArene;
		this.port = port;
		this.ipConsole = ipConsole;
		this.nom = nom;
		this.groupe = groupe;
		this.nbTours = nbTours;
		this.position = position;
		this.logger = logger;
		this.maitre = maitre;
		
		start();
	}



	@Override
	public void run() {
		new StrategieSbire(ipArene, port, ipConsole, nom, groupe, nbTours, position, logger, maitre);
	}


}
