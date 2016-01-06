package serveur.element;

import java.util.HashMap;

import utilitaires.Calculs;

public class Invocateur extends Personnage {

	/**
	 * Nombres de sbires crees par invocations 
	 */
	public static final int NB_SBIRES = 5;
	
	/**
	 * Cree un invocateur avec un nom et un groupe.
	 * @param nom du personnage
	 * @param groupe d'etudiants du personnage
	 */
	private static final long serialVersionUID = 1L;

	public Invocateur(String nom, String groupe) {
		super(nom, groupe, caract());
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Etablit les caracteristique du personnage
	 * @return caracts caracteristique du personnage
	 */
	private static HashMap<Caracteristique, Integer> caract (){
		HashMap<Caracteristique, Integer> caracts = new HashMap<Caracteristique, Integer>();
		caracts.put(Caracteristique.VIE, 90);
		caracts.put(Caracteristique.INITIATIVE, 20);
		caracts.put(Caracteristique.FORCE, Calculs.nombreAleatoire(30,45));
		caracts.put(Caracteristique.ARMURE, 25);
		return caracts;
	}
}
