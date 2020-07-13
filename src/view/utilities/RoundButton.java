package view.utilities;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

public class RoundButton extends JButton {

	private static final long serialVersionUID = -3005231172270681237L;

	@Override
	protected void paintComponent(Graphics g) {
		if (!isOpaque() && getBorder() instanceof RoundedBorder) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setPaint(getBackground());
			g2.fill(((RoundedBorder) getBorder()).getBorderShape(0, 0, getWidth() - 1, getHeight() - 1));
			g2.dispose();
		}
		super.paintComponent(g);
	}

	@Override
	public void updateUI() {
		super.updateUI();
		setOpaque(false);
		setBorder(new RoundedBorder());
	};
}
