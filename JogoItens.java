import java.awt.*;
import javax.swing.*;

public class JogoItens extends JPanel {

    private Image imagemFundo;
    private Image sobreposicaoCinturao;
    private Image sobreposicaoFita;
    private boolean mostrarCinturao = true; // Controle para exibir ou esconder a imagem do cinto
    private boolean mostrarFita = true;    // Controle para exibir ou esconder a imagem da fita

    public JogoItens(CardLayout layout, JPanel painelPrincipal) {
        // Carrega a imagem de fundo
        imagemFundo = new ImageIcon("imagens/Fotos editadas/fundoItens.png").getImage();
        // Carrega as sobreposições
        sobreposicaoCinturao = new ImageIcon("imagens/Fotos editadas/ItensCinturao.png").getImage();
        sobreposicaoFita = new ImageIcon("imagens/Fotos editadas/ItensFita.png").getImage();

        setLayout(null); // Define layout absoluto, se necessário

        // Botão de voltar
        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60); // Define a posição e o tamanho do botão
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20)); // Define a fonte do texto
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED); // Define a cor do texto
        botaoVoltar.setContentAreaFilled(true); // Remove o fundo visível
        botaoVoltar.setOpaque(true); // Garante que o botão seja transparente
        botaoVoltar.setBorderPainted(false); // Remove as bordas do botão
        botaoVoltar.addActionListener(e -> {
            System.out.println("Botão voltar clicado!");
            layout.show(painelPrincipal, "JogoEsquerdaOficial"); // Volta para o painel anterior
        });
        add(botaoVoltar); // Adiciona o botão ao painel

        // Botão quadrado 1
        JButton botaoQuadrado1 = new JButton();
        botaoQuadrado1.setBounds(100, 250, 200, 400); // Define a posição e o tamanho do botão
        botaoQuadrado1.setContentAreaFilled(false); // Remove o fundo visível
        botaoQuadrado1.setOpaque(false); // Garante que o botão seja transparente
        botaoQuadrado1.setBorderPainted(false); // Remove as bordas do botão
        botaoQuadrado1.addActionListener(e -> {
            System.out.println("Botão 1 clicado!");
            mostrarCinturao = false; // Esconde a imagem do cinto
            repaint(); // Atualiza o painel para refletir a mudança
        });
        add(botaoQuadrado1); // Adiciona o botão ao painel

        // Botão quadrado 2
        JButton botaoQuadrado2 = new JButton();
        botaoQuadrado2.setBounds(380, 300, 800, 300); // Define a posição e o tamanho do botão
        botaoQuadrado2.setContentAreaFilled(false); // Remove o fundo visível
        botaoQuadrado2.setOpaque(false); // Garante que o botão seja transparente
        botaoQuadrado2.setBorderPainted(false); // Remove as bordas do botão
        botaoQuadrado2.addActionListener(e -> {
            System.out.println("Botão 2 clicado!");
            mostrarFita = false; // Esconde a imagem da fita
            repaint(); // Atualiza o painel para refletir a mudança
        });
        add(botaoQuadrado2); // Adiciona o botão ao painel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Desenha a imagem de fundo
        if (imagemFundo != null) {
            g2d.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
        }

        // Desenha a sobreposição do cinto, se permitido
        if (mostrarCinturao && sobreposicaoCinturao != null) {
            g2d.drawImage(sobreposicaoCinturao, 0, 0, 1280, 853, this);
        }

        // Desenha a sobreposição da fita, se permitido
        if (mostrarFita && sobreposicaoFita != null) {
            g2d.drawImage(sobreposicaoFita, 0, 0, 1280, 853, this);
        }
    }
}
