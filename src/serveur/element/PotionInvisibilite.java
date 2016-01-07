package serveur.element;

import java.util.HashMap;


/**
 * Un poison sera une extension de la classe Potion. On utilisera l'upcasting
 */
public class PotionInvisibilite extends Potion {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur d'une potion avec un nom, le groupe qui l'a envoyee et ses 
	 * caracteristiques (ajoutees lorsqu'un Personnage ramasse cette potion).
	 * @param nom nom de la potion
	 * @param groupe groupe d'etudiants de la potion
	 */
	public PotionInvisibilite(String nom, String groupe) {
		super(nom, groupe, caract());
	}
	
	
	/**
	 * Etablit les caracteristique de la potion
	 * @return caracts caracteristique de la potion
	 */
	private static HashMap<Caracteristique, Integer> caract (){
		HashMap<Caracteristique, Integer> caracts = new HashMap<Caracteristique, Integer>();
		caracts.put(Caracteristique.INVISIBILITE, 1);
		caracts.put(Caracteristique.VITESSE, 2);
		caracts.put(Caracteristique.FORCE, 0);
		caracts.put(Caracteristique.VIE, 0);
		caracts.put(Caracteristique.MANA, 0);
		caracts.put(Caracteristique.INITIATIVE, 0);
		caracts.put(Caracteristique.ARMURE,0);
		return caracts;
	}
}