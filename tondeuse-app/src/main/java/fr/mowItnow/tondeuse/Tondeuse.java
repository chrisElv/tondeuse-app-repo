package fr.mowItnow.tondeuse;

public class Tondeuse {

	private Position coinSuperieurDroitPelouse;
	private Position positionActuelle;
	private Orientation orientation;

	private Tondeuse(Orientation orientation, Position positionActuelle, Position coinSuperieurDroitPelouse) {
		this.orientation = orientation;
		this.positionActuelle = positionActuelle;
		this.coinSuperieurDroitPelouse = coinSuperieurDroitPelouse;
	}

	/**
	 * <p>
	 * Construit une instance de {@link Tondeuse} à partir de la position et
	 * l'orientation. La position et l'orientation sont fournies sous la forme de 2
	 * chiffres et d’une lettre, séparés par un espace.
	 * </p>
	 * 
	 * <p>
	 * Example: positionInitialeEtOrientation = 1 2 N,  retournera une instance de {@link Tondeuse} avec
	 * Position(x=1, y=2) et Orientation: N
	 * </p>
	 * 
	 * @param positionInitialeEtOrientation chaîne de caractère representant la position initiale de la
	 *          tondeuse, ainsi que son orientation
	 * @param coinSuperieurDroitPelouse coin supérieur droit de la pelouse
	 * @return une instance de {@link Tondeuse}
	 */
	public static Tondeuse of(String positionInitialeEtOrientation, Position coinSuperieurDroitPelouse) {

		String[] split = positionInitialeEtOrientation.split(" ");

		int x = Integer.parseInt(split[0]); 
		int y = Integer.parseInt(split[1]);

		if ((x < 0 || x > coinSuperieurDroitPelouse.getX()) || 
		    (y < 0 || y > coinSuperieurDroitPelouse.getY())) {
			throw new IllegalArgumentException("Position initiale de la tondeuse incorrect!");
		}

		Orientation orientation = Orientation.valueOf(split[2]);
		return new Tondeuse(orientation, Position.of(x, y), coinSuperieurDroitPelouse);

	}

	public Tondeuse traiteInstructions(String instructions) {

		for (String instruction : instructions.split("")) {
			traiteInstruction(Instruction.valueOf(instruction));
		}
		return this;
	}

	private void traiteInstruction(Instruction instruction) {

		switch (instruction) {
		case A:
			avance();
			break;

		case D:
			pivoteDroit();
			break;

		case G:
			pivoteGauche();
			break;

		default:
			break;
		}
	}

	private void pivoteDroit() {
		switch (this.orientation) {
		case N:
			this.orientation = Orientation.E;
			break;

		case E:
			this.orientation = Orientation.S;
			break;

		case S:
			this.orientation = Orientation.W;
			break;

		case W:
			this.orientation = Orientation.N;
			break;
		}

	}

	private void pivoteGauche() {
		switch (this.orientation) {
		case N:
			this.orientation = Orientation.W;
			break;

		case W:
			this.orientation = Orientation.S;
			break;

		case S:
			this.orientation = Orientation.E;
			break;

		case E:
			this.orientation = Orientation.N;
			break;

		default:
			break;
		}

	}

	private void avance() {

		int xCoinSuperieurDroit = this.coinSuperieurDroitPelouse.getX();
		int yCoinSuperieurDroit = this.coinSuperieurDroitPelouse.getY();

		int positionApresMouvement;

		switch (this.orientation) {
		case N:
			positionApresMouvement = this.positionActuelle.getY() + 1;
			if (peutAvancer(yCoinSuperieurDroit, positionApresMouvement)) {
				this.positionActuelle.setY(positionApresMouvement);
			}
			break;

		case E:
			positionApresMouvement = this.positionActuelle.getX() + 1;
			if (peutAvancer(xCoinSuperieurDroit, positionApresMouvement)) {
				this.positionActuelle.setX(positionApresMouvement);
			}
			break;

		case W:
			positionApresMouvement = this.positionActuelle.getX() - 1;
			if (peutAvancer(xCoinSuperieurDroit, positionApresMouvement)) {
				this.positionActuelle.setX(positionApresMouvement);
			}
			break;

		case S:
			positionApresMouvement = this.positionActuelle.getY() - 1;
			if (peutAvancer(yCoinSuperieurDroit, positionApresMouvement)) {
				this.positionActuelle.setY(positionApresMouvement);
			}
			break;

		default:
			break;
		}
	}

	public static boolean peutAvancer(int coinSuperieurDroit, int positionApresMouvement) {
		return positionApresMouvement >= 0 && positionApresMouvement <= coinSuperieurDroit;
	}
	
	public Position getPositionActuelle() {
		return positionActuelle;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	@Override
	public String toString() {
		return positionActuelle + " " + orientation;
	}

}

enum Orientation {
	N, E, W, S
}

enum Instruction {
	A, D, G
}
