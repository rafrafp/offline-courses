package view.utilities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPasswordField;

public class HintPasswordField extends JPasswordField {
	private static final long serialVersionUID = 6702300190778698003L;
	private String ph;

	public HintPasswordField(String ph) {
		this.ph = ph;
	}
	
	public HintPasswordField() {
		this.ph = null;
	}

	/**
	 * Gets text, returns placeholder if nothing specified
	 */
	@Override
	public String getText() {
		String text = String.valueOf(super.getPassword());

		if (text.trim().length() == 0 && ph != null) {
			text = ph;
		}

		return text;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void paintComponent(Graphics g) {
		  if (!isOpaque() && getBorder() instanceof RoundedBorder) {
		      Graphics2D g2 = (Graphics2D) g.create();
		      g2.setPaint(getBackground());
		      g2.fill(((RoundedBorder) getBorder()).getBorderShape(
		          0, 0, getWidth() - 1, getHeight() - 1));
		      g2.dispose();
		    }
		super.paintComponent(g);
		
		

		if (super.getText().length() > 0 || ph == null) {
			return;
		}
		
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(super.getDisabledTextColor());
		g2.drawString(ph, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
	}
	
	 @Override public void updateUI() {
		    super.updateUI();
		    setOpaque(false);
		    setBorder(new RoundedBorder());
		  };

}
