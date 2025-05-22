import java.awt.*;
import javax.swing.*;

public class JogoPA extends JPanel {

    private Image imagemFundo;

    public JogoPA(CardLayout layout, JPanel painelPrincipal) {
        // Carrega a imagem de fundo
        imagemFundo = new ImageIcon("imagens/Fotos editadas/05 - Módulo de Comunicação - tela de início.jpg").getImage();
        setLayout(null);

        // Botão Voltar
        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60);
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED);
        botaoVoltar.setContentAreaFilled(true);
        botaoVoltar.setOpaque(true);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.addActionListener(e -> {
            layout.show(painelPrincipal, "Jogo");
        });
        add(botaoVoltar);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenha a imagem de fundo cobrindo toda a tela
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

