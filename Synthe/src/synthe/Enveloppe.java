package synthe;

/**
 * <p>L'enveloppe est composée de 4 phases :
 * <ul><li>L'attaque, transition de 0 à 1</li>
 * <li>Le déclin, transition de 1 à hauteurSoutien</li>
 * <li>Le soutien, stable à hauteurSoutien</li>
 * <li>Le relachement, transition de hauteurSoutien à 0</li></ul>
 * Chaque phase a sa propre durée.
 * </p>
 */
public class Enveloppe {
	final int dureeAttaque;
	final int dureeDeclin;
	final double hauteurSoutien;
	final int dureeSoutien;
	final int dureeRelachement;
	
	public Enveloppe(int dureeAttaque, int dureeDeclin, double hauteurSoutien, int dureeSoutien, int dureeRelachement) {
		this.dureeAttaque = dureeAttaque;
		this.dureeDeclin = dureeDeclin;
		this.hauteurSoutien = hauteurSoutien;
		this.dureeSoutien = dureeSoutien;
		this.dureeRelachement = dureeRelachement;
	}
	
	public int[] envelopper(int[] son) {
		int[] resultat = new int[son.length];
		for (int i=0; i<son.length; i++) {
			double avancement;
			if (i<this.dureeAttaque) {
				// Attaque
				avancement = (double) i / (double) this.dureeAttaque;
				resultat[i] = (int) (transition(0d, 1d, avancement) * son[i]);
			} else if (i < this.dureeAttaque+this.dureeDeclin) {
				// Declin
				avancement = (double) (i-this.dureeAttaque) / (double) this.dureeDeclin;
				resultat[i] = (int) (transition(1d, hauteurSoutien, avancement) * son[i]);
			} else if (i < this.dureeAttaque+this.dureeDeclin+this.dureeSoutien) {
				// Soutien
				resultat[i] = (int) (hauteurSoutien * son[i]);
			} else {
				// Relachement
				avancement = (double) (i-this.dureeAttaque-this.dureeDeclin-this.dureeSoutien) / (double) this.dureeRelachement;
				resultat[i] = (int) (transition(hauteurSoutien, 0d, avancement) * son[i]);
			}
		}
		return resultat;
	}
	
	private double transition(double hauteurDebut, double hauteurFin, double avancement) {
		return 3*Math.pow(avancement, 2) - 2*Math.pow(avancement, 3) // pseudo-sigmoide (cubique)
				* (hauteurFin - hauteurDebut) + hauteurDebut;
	}
}
