import java.awt.*;
import javax.swing.*;

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

        
        // Adiciona a imagem de fundo redimensionada
        ImageIcon imagemOriginal = new ImageIcon("imagens/Fotos editadas/05 - Módulo de Comunicação - tela de início.jpg");
        Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH); // Ajuste o tamanho conforme necessário
        ImageIcon imagem = new ImageIcon(imagemRedimensionada);

        JLabel labelImagem = new JLabel(imagem);
        labelImagem.setBounds(0, 0, 1280, 720); // Ajuste o tamanho conforme necessário
        add(labelImagem);
    }
}

