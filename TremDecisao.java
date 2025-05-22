import java.awt.*;
import javax.swing.*;

public class TremDecisao extends JPanel {

    private Image imagemFundo;
    private Image imgCinturao;
    private Image imgFita;

    private JButton botao1;
    private JButton botao2;

    public TremDecisao() {
        // Carrega a imagem de fundo
        imagemFundo = new ImageIcon("imagens/Fotos editadas/decisao.jpg").getImage();
        imgCinturao = new ImageIcon("imagens/Fotos editadas/ItensCinturao.png").getImage();
        imgFita = new ImageIcon("imagens/Fotos editadas/ItensFita.png").getImage();
        setLayout(null); // Define layout absoluto

        // Botão 1 (aparece apenas se o jogador tiver a fita)
        botao1 = new JButton("Opção 1");
        botao1.setBounds(100, 300, 200, 300); // Posição e tamanho
        botao1.addActionListener(e -> System.out.println("Botão 1 clicado!"));
        add(botao1);

        // Botão 2 (aparece apenas se o jogador tiver o cinturão)
        botao2 = new JButton("Opção 2");
        botao2.setBounds(400, 300, 720, 300); // Posição e tamanho
        botao2.addActionListener(e -> System.out.println("Botão 2 clicado!"));
        add(botao2);
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag) {
            // Atualiza a visibilidade dos botões com base no estado atual
            botao1.setVisible(InventarioUI.isPegouFita()); // Botão 1 aparece se tiver a fita
            botao2.setVisible(InventarioUI.isPegouCinturao()); // Botão 2 aparece se tiver o cinturão
        }
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
