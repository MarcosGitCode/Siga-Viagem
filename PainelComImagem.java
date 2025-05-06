import java.awt.*;
import javax.swing.*;

public class PainelComImagem extends JPanel {
    private Image backgroundImage;

    public PainelComImagem(String imagePath) {
        this.backgroundImage = new ImageIcon(imagePath).getImage();
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}