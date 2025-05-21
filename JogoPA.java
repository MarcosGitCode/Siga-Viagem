import javax.swing.*;
import java.awt.*;

public class JogoPA extends JPanel {

    public JogoPA(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null);

        
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

        
        ImageIcon imagem = new ImageIcon("imagens/Fotos editadas/05 - Módulo de Comunicação - tela de início.jpg");
        JLabel labelImagem = new JLabel(imagem);
        labelImagem.setBounds(0, 0, imagem.getIconWidth(), imagem.getIconHeight());
        add(labelImagem);
        setComponentZOrder(labelImagem, getComponentCount() - 1); // Garante que a imagem fique no fundo
    }
}

