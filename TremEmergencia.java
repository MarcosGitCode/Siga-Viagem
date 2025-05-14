import javax.swing.*;
import java.awt.*;

public class TremEmergencia extends JPanel {

    public TremEmergencia(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null);

        // Teste de carregamento da imagem
        String caminho = "imagens/Fotos editadas/16 - Dispositivos de emergência.jpg";
        ImageIcon imagem = new ImageIcon(caminho);
        System.out.println("Largura da imagem: " + imagem.getIconWidth()); // Deve ser >0

        JLabel labelImagem = new JLabel(imagem);
        labelImagem.setBounds(0, 0, 1280, 856);
        add(labelImagem);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(20, 20, 100, 50);
        botaoVoltar.addActionListener(e -> layout.show(painelPrincipal, "PortaAberta"));
        add(botaoVoltar);
    }
}
