import javax.swing.*;

public class JogoPA extends JPanel {

    public JogoPA() {
        setLayout(null);

        // Adiciona a imagem de fundo
        ImageIcon imagem = new ImageIcon("imagens/Fotos editadas/05 - Módulo de Comunicação - tela de início.jpg");
        JLabel labelImagem = new JLabel(imagem);
        labelImagem.setBounds(0, 0, imagem.getIconWidth(), imagem.getIconHeight());
        add(labelImagem);
    }
}