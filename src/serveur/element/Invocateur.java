package serveur.element;

import java.util.HashMap;
import java.util.Vector;

public class Invocateur extends Personnage {

	/**
	 * Nombres de sbires créés par invocations 
	 */
	public static final int NB_SBIRES = 5;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Invocateur(String nom, String groupe, HashMap<Caracteristique, Integer> caracts) {
		super(nom, groupe, caracts);
		// TODO Auto-generated constructor stub
	}
}
