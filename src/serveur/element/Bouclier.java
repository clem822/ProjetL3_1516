package serveur.element;

import java.util.HashMap;

import utilitaires.Calculs;

/**
 * Un bouclier sera une extension de la classe Potion. On utilisera l'upcasting
 */
public class Bouclier extends Potion {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur d'une potion avec un nom, le groupe qui l'a envoyee et ses 
	 * caracteristiques (ajoutees lorsqu'un Personnage ramasse cette potion).
	 * @param nom nom de la potion
	 * @param groupe groupe d'etudiants de la potion
	 */
	public Bouclier(String nom, String groupe) {
		super(nom, groupe, caract());
	}
	
	
	/**
	 * Etablit les caracteristique du bouclier
	 * @return caracts caracteristique du bouclier
	 */
	private static HashMap<Caracteristique, Integer> caract (){
		HashMap<Caracteristique, Integer> caracts = new HashMap<Caracteristique, Integer>();
		caracts.put(Caracteristique.VITESSE, 0);
		caracts.put(Caracteristique.FORCE, 0);
		caracts.put(Caracteristique.VIE, 0);
		caracts.put(Caracteristique.MANA, 0);
		caracts.put(Caracteristique.INITIATIVE, 0);
		caracts.put(Caracteristique.ARMURE,Calculs.nombreAleatoire(5,20));
		return caracts;
	}
}
