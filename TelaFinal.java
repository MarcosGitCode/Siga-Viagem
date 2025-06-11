import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TelaFinal extends JPanel {

    private Image imagemFundo;
    private String mensagemTemporaria = "";
    public TelaFinal(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null);

        // Carrega a imagem de fundo uma vez
        try {
            BufferedImage img = ImageIO.read(new File("imagens\\Fotos editadas\\fim de jogo.jpg"));
            imagemFundo = img.getScaledInstance(1280, 856, Image.SCALE_SMOOTH);
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
            imagemFundo = null;
        }
        
        JButton botaoMenu = new JButton("Menu");
        int xBotao = (1280 - 300) / 2;
        botaoMenu.setBounds(xBotao, 650, 300, 70);
        botaoMenu.setFont(new Font("Arial", Font.BOLD, 18));
        botaoMenu.setBackground(new Color(128, 128, 128, 128));
        botaoMenu.setForeground(Color.WHITE);
        botaoMenu.setFocusPainted(false);
        botaoMenu.setContentAreaFilled(false);
        botaoMenu.setBorder(new RoundedBorder(20));
        botaoMenu.addActionListener(e -> {
            System.out.println("Botão Menu clicado!");
            layout.show(painelPrincipal, "Menu");
        });
        add(botaoMenu);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenha a imagem de fundo
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, 1280, 856, this);
        }
        // Desenha o inventário no canto superior direito
        InventarioUI.desenhar((Graphics2D) g, getWidth());

        // Desenha a mensagem temporária, se houver
        if (mensagemTemporaria != null && !mensagemTemporaria.isEmpty()) {
            g.setFont(new Font("Arial", Font.BOLD, 32));
            int x = getWidth() / 2 - g.getFontMetrics().stringWidth(mensagemTemporaria) / 2;
            int y = 100;
            // Desenha a borda preta (várias vezes ao redor)
            g.setColor(Color.BLACK);
            for (int dx = -2; dx <= 2; dx++) {
                for (int dy = -2; dy <= 2; dy++) {
                    if (dx != 0 || dy != 0) {
                        g.drawString(mensagemTemporaria, x + dx, y + dy);
                    }
                }
            }
            // Desenha o texto branco por cima
            g.setColor(Color.WHITE);
            g.drawString(mensagemTemporaria, x, y);
        }
    }
}
