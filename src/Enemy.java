
import java.awt.*;
import java.awt.image.*;
import java.util.*;

/**
 *
 * @author Bruno O
 */
public class Enemy extends Element {

	private BufferedImage img;
	private static boolean obstacle;

	/**
	 *
	 * @param locationImg
	 */
	public Enemy(String locationImg) {
		super();
		img = JogoCorrida.getImg(locationImg);
		super.setSize(img.getWidth(), img.getHeight());
		obstacle = true;
	}

	/**
	 *
	 */
	public void setObstacle() {
		obstacle = false;
	}

	/**
	 *
	 * @return
	 */
	public boolean isObstacle() {
		return obstacle;
	}

	/**
	 *
	 * @param road
	 * @return
	 */
	public int randomPos(Road road) {
		int pos_x = road.x;

		Random r = new Random();

		return pos_x + r.nextInt(road.width) - width;
	}

	/**
	 * Caso os inimigos tenham chegado no final da tela entao gerara uma nova
	 * posicao
	 *
	 * @param road
	 * @return
	 */
	public boolean move(Road road, Player p) {

		if (!super.move(road, p)) {
			obstacle = true;
			int pos_x = randomPos(road);
			super.setLocation(pos_x, 0);
			return true;
		}
		return false;
	}

	/**
	 * Responsavel por mover o inimigo
	 *
	 * @param road Passando a rua como paramero
	 */
//	public void move(Road road) {
//		int sizeRoadY = road.height;
//
//		int delta = Crossover.getDelta();
//		super.translate(0, delta);
//
//		/**
//		 * Caso o inimigo tenha saido da tela ele vai reaparecer em uma nova posicao
//		 */
//		if (y + height > sizeRoadY) {
//			obstacle = true;
//			int pos_x = randomPos(road);
//			super.setLocation(pos_x, 0);
//		}
//	}
	/**
	 *
	 * @param g
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(img, x, y, null);
	}

}
