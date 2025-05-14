import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TremPortaAberta extends JPanel {

    private JLabel labelImagem;

    public TremPortaAberta(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null); // Define o layout absoluto para posicionar os botões

        // Carrega a imagem inicial
        ImageIcon imagem = carregarImagem("imagens/Fotos editadas/14 - Porta aberta - externo.jpg");

        // Adiciona a imagem inicial ao painel
        labelImagem = new JLabel(imagem);
        labelImagem.setBounds(0, 0, 1280, 856); // Define o tamanho da imagem para cobrir toda a tela
        add(labelImagem);

        // Cria o botão para a metade superior da tela
        JButton botaoSuperior = new JButton();
        botaoSuperior.setBounds(0, 0, 1280, 628); // Ocupa a metade superior
        botaoSuperior.setOpaque(false); // Torna o botão transparente
        botaoSuperior.setContentAreaFilled(false); // Remove a área de fundo
        botaoSuperior.setBorderPainted(false); // Remove as bordas
        botaoSuperior.addActionListener(e -> trocarImagem("imagens/Fotos editadas/14 - Porta aberta - externo.jpg"));
        add(botaoSuperior);

        // Cria o botão para a metade inferior da tela
        JButton botaoInferior = new JButton();
        botaoInferior.setBounds(500, 650, 300, 300); // Ocupa a metade inferior
        botaoInferior.setOpaque(false); // Torna o botão transparente
        botaoInferior.setContentAreaFilled(false); // Remove a área de fundo
        botaoInferior.setBorderPainted(false); // Remove as bordas
        botaoInferior.addActionListener(e -> trocarImagem("imagens/Fotos editadas/pes_olhando.jpg"));
        add(botaoInferior);

        // Adiciona o botão de voltar
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(20, 20, 100, 50); // Define o tamanho e posição do botão de voltar
        botaoVoltar.addActionListener(e -> layout.show(painelPrincipal, "Menu")); // Volta para o painel "Menu"
        add(botaoVoltar);
    }

    private void trocarImagem(String caminhoImagem) {
        // Carrega e redimensiona a nova imagem
        ImageIcon novaImagem = carregarImagem(caminhoImagem);

        // Atualiza o JLabel com a nova imagem
        labelImagem.setIcon(novaImagem);
        revalidate();
        repaint();
    }

    private ImageIcon carregarImagem(String caminhoImagem) {
        try {
            // Carrega a imagem como BufferedImage
            BufferedImage imagemOriginal = ImageIO.read(new File(caminhoImagem));

            // Redimensiona a imagem usando Graphics2D
            BufferedImage imagemRedimensionada = new BufferedImage(1280, 856, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = imagemRedimensionada.createGraphics();
            g2d.drawImage(imagemOriginal, 0, 0, 1280, 856, null);
            g2d.dispose();

            return new ImageIcon(imagemRedimensionada);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
