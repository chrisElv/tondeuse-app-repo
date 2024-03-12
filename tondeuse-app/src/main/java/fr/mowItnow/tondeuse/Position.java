package fr.mowItnow.tondeuse;

public class Position {

	private static final String COORDONNEES_DE_POSITION_INCORRECT = "Coordonnées de position incorrect!";

	private int x;
	private int y;

	private Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * <p>
	 * Construit une instance de {@link Position} à partir de ses coordonnées. Les
	 * coordonnées sont fournies sous la forme de 2 chiffres séparés par un espace.
	 * </p>
	 * 
	 * <p>
	 * Example: xY = 1 2 retournera une instance de {@link Position} avec x=1, y=2
	 * </p>
	 * 
	 * @param xY
	 * @return une instance de {@link Position}
	 */
	public static Position of(String xY) {

		String[] splitxY = xY.split(" ");

		int x = Integer.parseInt(splitxY[0]);
		int y = Integer.parseInt(splitxY[1]);

		return Position.of(x, y);

	}

	/**
	 * <p>
	 * Construit une instance de {@link Position} à partir de ses coordonnées x, y.
	 * </p>
	 * 
	 * @param x
	 * @param y
	 * @return une instance de {@link Position}
	 */
	public static Position of(int x, int y) {

		validateCoordonnee(x, y);

		return new Position(x, y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {

		validateCoordonnee(x);
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {

		validateCoordonnee(y);
		this.y = y;
	}

	private static void validateCoordonnee(int... cXycY) {

		for (int c : cXycY) {
			if (c < 0) {
				throw new IllegalArgumentException(COORDONNEES_DE_POSITION_INCORRECT +" : "+c );
			}
		}
	}

	@Override
	public String toString() {
		return x + " " + y;
	}

}
