package serveur.element;

import java.util.HashMap;

import utilitaires.Calculs;


/**
 * Un poison sera une extension de la classe Potion. On utilisera l'upcasting
 */
public class Poison extends Potion {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur d'une potion avec un nom, le groupe qui l'a envoyee et ses 
	 * caracteristiques (ajoutees lorsqu'un Personnage ramasse cette potion).
	 * @param nom nom de la potion
	 * @param groupe groupe d'etudiants de la potion
	 */
	public Poison(String nom, String groupe) {
		super(nom, groupe, caract());
	}
	
	
	/**
	 * Etablit les caracteristique du poison
	 * @return caracts caracteristique du poison
	 */
	private static HashMap<Caracteristique, Integer> caract (){
		HashMap<Caracteristique, Integer> caracts = new HashMap<Caracteristique, Integer>();
		caracts.put(Caracteristique.VITESSE,-( Calculs.valeurCaracAleatoire(Caracteristique.VITESSE)));
		caracts.put(Caracteristique.FORCE, -( Calculs.valeurCaracAleatoire(Caracteristique.FORCE)));
		caracts.put(Caracteristique.VIE, -( Calculs.valeurCaracAleatoire(Caracteristique.VIE)));
		caracts.put(Caracteristique.MANA, -( Calculs.valeurCaracAleatoire(Caracteristique.MANA)));
		caracts.put(Caracteristique.INITIATIVE, -( Calculs.valeurCaracAleatoire(Caracteristique.INITIATIVE)));
		caracts.put(Caracteristique.ARMURE,-( Calculs.valeurCaracAleatoire(Caracteristique.ARMURE)));
		return caracts;
	}
}
