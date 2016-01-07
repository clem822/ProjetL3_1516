package serveur.element;

import java.util.HashMap;

import utilitaires.Calculs;

public class Ninja extends Personnage{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Cree un Ninja avec un nom et un groupe.
	 * @param nom du personnage
	 * @param groupe d'etudiants du personnage
	 */

	public Ninja(String nom, String groupe) {
		super(nom, groupe, caract());
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Etablit les caracteristique du personnage
	 * @return caracts caracteristique du personnage
	 */
	private static HashMap<Caracteristique, Integer> caract (){
		HashMap<Caracteristique, Integer> caracts = new HashMap<Caracteristique, Integer>();
		caracts.put(Caracteristique.VIE, 80);
		caracts.put(Caracteristique.MANA, 60);
		caracts.put(Caracteristique.INITIATIVE, 130);
		caracts.put(Caracteristique.VITESSE, 2);
		caracts.put(Caracteristique.FORCE, Calculs.nombreAleatoire(40,50));
		caracts.put(Caracteristique.ARMURE, 15);
		return caracts;
	}

}
