import javax.swing.*;
import java.awt.*;

public class TremEmergencia extends JPanel {

    public TremEmergencia(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null);

        // Teste de carregamento da imagem
        String caminho = "imagens/Fotos editadas/16 - Dispositivos de emergência.jpg";
        ImageIcon imagemOriginal = new ImageIcon(caminho);

        // Redimensionar a imagem ao tamanho da janela
        Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(1280, 856, Image.SCALE_SMOOTH);
        ImageIcon imagem = new ImageIcon(imagemRedimensionada);

        JLabel labelImagem = new JLabel(imagem);
        labelImagem.setBounds(0, 0, 1280, 856);
        add(labelImagem);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(20, 20, 100, 50);
        botaoVoltar.addActionListener(e -> layout.show(painelPrincipal, "PortaAberta"));
        add(botaoVoltar);
    }
}
