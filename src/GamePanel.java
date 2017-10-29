package islandsofviolence;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public final class GamePanel extends JPanel {

	static int minimapBlockSize = 3;
	final static int squares = 75;
	final static int screenheight;

	/**
	 * The following static block is used courtesy of stack overflow creative
	 * commons liscence
	 * http://stackoverflow.com/questions/3680221/how-can-i-get-the-monitor-size-in-java
	 */
	static {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		//screenheight = (int) height + 2;
screenheight=768;		
//screenlength = (int) width + 2;
	screenlength=768;
	}
	static final int screenlength;
	final static int middle = screenlength / 2;
		final static int horizon = screenheight / 2;
	final static int pixelSize = screenheight / Texture.size;
	public static final Color SKY_BLUE = new Color(84, 232, 255);
	public static final int maxRenderDistance = 25;

	public GamePanel() throws IOException {

		this.setBackground(SKY_BLUE);

	}

	@Override

	public Dimension getPreferredSize() {

		return new Dimension(screenlength, screenheight);

	}

	public static final double fov = 1;

	/**
	 *
	 * updates graphic
	 *
	 * @param board
	 *
	 */
	public final Color minimap = new Color(55, 0, 255);
	public final Color exit = new Color(224, 224, 36);
	public static boolean mapFound = false;

	public void minimap(Graphics g) {
		//g.drawImage(wall, 0,0, this);
		g.setColor(minimap);
		for (int i = 0; i < MazeRunner.map.length; i++) {
			for (int j = 0; j < MazeRunner.map[0].length; j++) {
				if (mapFound || MazeRunner.visited[i][j]) {
					switch (MazeRunner.map[i][j]) {
						case 0:
							g.setColor(minimap);
							break;
						case 3:
							g.setColor(Color.red);
							break;
						case 6:
							g.setColor(exit);
							break;
						default:
							g.setColor(Color.green);
							break;
					}
				} else {
					g.setColor(Color.gray);
				}
				g.fillRect(i * minimapBlockSize, j * minimapBlockSize, minimapBlockSize, minimapBlockSize);
			}
		}
		g.setColor(Color.red);
		g.fillOval((int) (MazeRunner.p.x * minimapBlockSize) - minimapBlockSize / 2, (int) (MazeRunner.p.y * minimapBlockSize) - minimapBlockSize / 2, minimapBlockSize, minimapBlockSize);
		g.setColor(Color.yellow);
		g.drawLine((int) ((MazeRunner.p.x * minimapBlockSize)), (int) (MazeRunner.p.y * minimapBlockSize), (int) (MazeRunner.p.x * minimapBlockSize + Math.cos(MazeRunner.p.angle) * minimapBlockSize / 2), (int) (MazeRunner.p.y * minimapBlockSize - Math.sin(MazeRunner.p.angle) * minimapBlockSize / 2));
		g.setColor(Color.black);
		g.drawString(Game.time + ":00 " + (Game.isAM ? "AM" : "PM"), screenlength - 60, 10);

	}
	final static double[] anglesOff;

	static {
		anglesOff = new double[screenlength];
		for (int i = 0; i < screenlength; i++) {
			anglesOff[i] = Math.atan(((middle - i) / 1000.0) / (fov));
		}
//        for(double i:anglesOff){
//            System.out.println(i);
//        }
	}
	final static double[] fishEyeCorrection;

	static {
		fishEyeCorrection = new double[screenlength];
		for (int i = 0; i < screenlength; i++) {
			fishEyeCorrection[i] = Math.cos(anglesOff[i]);
		}
	}
	public static BufferedImage wall;

	static {
		try {
			wall = ImageIO.read(new File("cobbleWall2.png"));
		} catch (IOException ex) {

			Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	private final static Texture wallT;

	static {
		wallT = new Texture(wall);
	}
	public static BufferedImage enemy;

	static {
		try {
			enemy = ImageIO.read(new File("zombie.png"));
		} catch (IOException ex) {

			Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	private final static Texture enemyT;

	static {

		//System.out.println(enemy);
		enemyT = new Texture(enemy);
	}
	public static BufferedImage door;

	static {
		try {
			door = ImageIO.read(new File("door.png"));
		} catch (Exception ex) {
		}
	}
	public static final Texture doorT = new Texture(door);
	public static BufferedImage gun;

	static {
		try {
			gun = ImageIO.read(new File("gun.png"));
		} catch (Exception ex) {
			gun = null;
		}
	}
	public static BufferedImage gunWithBoom;

	static {
		try {
			gunWithBoom = ImageIO.read(new File("gunWithBoom.png"));
		} catch (Exception ex) {

		}
	}
	public static final Color DARK_GREEN = new Color(13, 130, 47);
	public static final Color DARK_BLUE = new Color(43, 141, 173);

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(DARK_GREEN);
		g.fillRect(0, horizon, screenlength, horizon);
		g.setColor(Game.mobSpawningRate > 5 ? DARK_BLUE : SKY_BLUE);
		g.fillRect(0, 0, screenlength, horizon);
		for (int i = 0; i < screenlength; i++) {
			double rayX = MazeRunner.p.x - 0 * Math.cos(MazeRunner.p.angle);
			double rayY = MazeRunner.p.y + 0 * Math.sin(MazeRunner.p.angle);
			double startX = rayX;
			double startY = rayY;
			double angle = MazeRunner.p.angle + anglesOff[i];
			for (double rayDist = 0; rayDist < maxRenderDistance; rayDist += getReasonableStep(rayX, rayY, angle)) {
				if (!Greaver.isValid((int) rayX, (int) rayY)) {
					break;
				}
				rayX = (startX + Math.cos(angle) * (rayDist));
				rayY = (startY - Math.sin(angle) * (rayDist));
				MazeRunner.visited[(int) rayX][(int) rayY] = true;
				int block = MazeRunner.map[(int) rayX][(int) rayY];
				if (block != 1) {
					//rayDist+=1;
					int lineHeight = (int) (screenheight / (rayDist) / fishEyeCorrection[i] / (1.2));
					if (lineHeight > screenheight) {
						lineHeight = screenheight;
//kludge
					}
					Texture textured;
					switch (block) {
						default:
						case 0:
							textured = wallT;
							break;
						case 3:
							textured = enemyT;
							break;
						case 6:
							textured = doorT;
							break;
					}
					Color[] texture = textured.texture[(int) ((lineHeight * Texture.size) / screenheight) - 1][pixelLineOntexture(rayX, rayY)];
					int start = horizon - lineHeight / 2;
					for (int p = 0; p < texture.length; p++) {
						g.setColor(texture[p]);
						g.drawLine(i, start + pixelSize * p, i, start + pixelSize * p + pixelSize);
					}
//g.setColor(Color.black);
					//g.drawLine(i, horizon-lineHeight/2, i, horizon+lineHeight/2);
					//System.out.println("Break");
					break;
				}
			}
		}

		g.drawImage(Controller.firing.get() ? gunWithBoom : gun, middle - 300, screenheight - 480, null);

		minimap(g);
	}
	/**
	 * Some arbitrarily small value, the smaller the better
	 */
	static final double e = .00001;

	/**
	 * gets a reasonable step ahead for the ray to make it more efficient
	 *
	 * @param rayX
	 * @param rayY
	 * @param angle
	 * @return
	 */
	public static double getReasonableStep(double rayX, double rayY, double angle) {
//        if(true)
//            return .01;
		angle %= 2 * Math.PI;
		angle += 2 * Math.PI;
		angle %= 2 * Math.PI;
		double yResidue;

		if (angle > Math.PI) {
			yResidue = Math.ceil(rayY + e) - rayY;
		} else {
			yResidue = rayY - Math.floor(rayY - e);
		}

		double xResidue;
		if (angle < Math.PI / 2 || angle > 3 * Math.PI / 2) {
			xResidue = Math.ceil(rayX + e) - rayX;
		} else {
			xResidue = rayX - Math.floor(rayX - e);
		}
		double actualYDist = Math.abs(yResidue / Math.sin(angle));
		double actualXDist = Math.abs(xResidue / Math.cos(angle));
		return actualYDist < actualXDist ? actualYDist + .005 : actualXDist + .005;
	}

	private int pixelLineOntexture(double rayX, double rayY) {
		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		if (Math.abs((1 - ((rayY - .01) % 1))) <= .02) {
			return (int) ((rayX % 1) * Texture.size);
		} else {
			return (int) (((rayY % 1)) * Texture.size);
		}
	}
}
