import java.awt.*;
import javax.swing.*;

public class MenuOpcoes extends JPanel {
    private final JLabel lblVolume;
    private final JSlider sliderVolume;
    private final JLabel lblTela;
    private final JButton btnTelaCheia;
    private final JButton btnModoJanela;
    private final JButton btnVoltar;
    private Image backgroundImage;

    public MenuOpcoes(CardLayout layout, JPanel painelPrincipal) {
        // Load the background image
        backgroundImage = new ImageIcon("imagens/metro_blur.jpg").getImage();
        setLayout(null);

        lblVolume = new JLabel("Volume:", SwingConstants.CENTER);
        lblVolume.setFont(new Font("Arial", Font.BOLD, 24));
        lblVolume.setForeground(Color.WHITE);
        lblVolume.setBounds(390, 100, 500, 40);
        add(lblVolume);

        sliderVolume = new JSlider(0, 100, 50);
        sliderVolume.setBounds(390, 160, 500, 40);
        sliderVolume.setOpaque(false);
        add(sliderVolume);

        lblTela = new JLabel("Tela:", SwingConstants.CENTER);
        lblTela.setFont(new Font("Arial", Font.BOLD, 24));
        lblTela.setForeground(Color.WHITE);
        lblTela.setBounds(390, 240, 500, 40);
        add(lblTela);

        btnTelaCheia = criarBotao("Tela Cheia");
        btnTelaCheia.setBounds(390, 300, 220, 60);
        btnTelaCheia.addActionListener(e -> alternarTelaCheia());
        add(btnTelaCheia);

        btnModoJanela = criarBotao("Modo Janela");
        btnModoJanela.setBounds(670, 300, 220, 60);
        btnModoJanela.addActionListener(e -> sairTelaCheia());
        add(btnModoJanela);

        btnVoltar = criarBotao("Voltar");
        btnVoltar.setBounds(490, 400, 300, 80);
        btnVoltar.addActionListener(e -> {
            System.out.println("Botão Voltar clicado!");
            layout.show(painelPrincipal, "Menu");
        });
        add(btnVoltar);
    }
    
    // Define the RoundedBorder class
    class RoundedBorder implements javax.swing.border.Border {
        private final int radius;
    
        RoundedBorder(int radius) {
            this.radius = radius;
        }
    
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }
    
        @Override
        public boolean isBorderOpaque() {
            return true;
        }
    
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    private void alternarTelaCheia() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            frame.dispose();
            frame.setUndecorated(true);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
            centralizarComponentes(frame.getWidth(), frame.getHeight());
        }
    }

    private void sairTelaCheia() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            frame.dispose();
            frame.setUndecorated(false);
            frame.setExtendedState(JFrame.NORMAL);
            frame.setSize(1280, 853);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            centralizarComponentes(frame.getWidth(), frame.getHeight());
        }
    }

    private void centralizarComponentes(int windowWidth, int windowHeight) {
        lblVolume.setBounds((windowWidth - 500) / 2, 100, 500, 40);
        sliderVolume.setBounds((windowWidth - 500) / 2, 160, 500, 40);
        lblTela.setBounds((windowWidth - 500) / 2, 240, 500, 40);
        btnTelaCheia.setBounds((windowWidth - 500) / 2, 300, 220, 60);
        btnModoJanela.setBounds((windowWidth - 500) / 2 + 280, 300, 220, 60);
        btnVoltar.setBounds((windowWidth - 300) / 2, windowHeight - 160, 300, 80);

        revalidate();
        repaint();
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setBackground(new Color(128, 128, 128, 128));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setContentAreaFilled(false);
        botao.setBorder(new RoundedBorder(20));
        return botao;
    }
}