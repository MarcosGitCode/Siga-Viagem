import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TelaFinal extends JPanel {

    private Image imagemFundo;
    private JLabel lblPontuacao;

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

        JLabel lblMensagem = new JLabel("Pontuação total:");
        lblMensagem.setFont(new Font("Arial", Font.BOLD, 40));
        lblMensagem.setForeground(Color.WHITE);
        lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensagem.setBounds(390, 250, 500, 50);

        lblPontuacao = new JLabel("0");
        lblPontuacao.setFont(new Font("Arial", Font.BOLD, 60));
        lblPontuacao.setForeground(Color.WHITE);
        lblPontuacao.setHorizontalAlignment(SwingConstants.CENTER);
        lblPontuacao.setBounds(390, 320, 500, 70);

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

        add(lblMensagem);
        add(lblPontuacao);
        add(botaoMenu);
    }

    // Chame este método antes de mostrar a tela final!
    public void atualizarPontuacao() {
        MetroviarioDAO dao = new MetroviarioDAO();
        int pontuacaoTotal = dao.mostrarPontuacaoUser(UsuarioLogado.getRegistro());
        lblPontuacao.setText(String.valueOf(pontuacaoTotal));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, 1280, 856, this);
        }
    }
}
