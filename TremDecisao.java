import java.awt.*;
import javax.swing.*;

public class TremDecisao extends JPanel {

    private Image imagemFundo;
    private Image imgCinturao;
    private Image imgFita;

    public TremDecisao() {
        // Carrega a imagem de fundo
        imagemFundo = new ImageIcon("imagens/Fotos editadas/decisao.jpg").getImage();
        imgCinturao = new ImageIcon("imagens/Fotos editadas/ItensCinturao.png").getImage();
        imgFita = new ImageIcon("imagens/Fotos editadas/ItensFita.png").getImage();
        setLayout(null); // Define layout absoluto

        // Botão 1
        JButton botao1 = new JButton("Opção 1");
        botao1.setBounds(100, 300, 200, 300); // Posição e tamanho
        botao1.addActionListener(e -> System.out.println("Botão 1 clicado!"));
        add(botao1);

        // Botão 2
        JButton botao2 = new JButton("Opção 2");
        botao2.setBounds(400, 300, 720, 300); // Posição e tamanho
        botao2.addActionListener(e -> System.out.println("Botão 2 clicado!"));
        add(botao2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenha a imagem de fundo
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
        }

        // Verifica se o jogador pegou o cinturão e desenha a imagem ocupando a tela inteira
        if (InventarioUI.isPegouCinturao()) {
            g.drawImage(imgCinturao, 0, 0, getWidth(), getHeight(), this);
        }

        // Verifica se o jogador pegou a fita e desenha a imagem ocupando a tela inteira
        if (InventarioUI.isPegouFita()) {
            g.drawImage(imgFita, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
