import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class JogoAlavanca extends BasePainelComBotao {

    private boolean imagemAlternada = false; // controla se tá na img1 ou img2
    private BufferedImage imagem1; // imagem inicial
    private BufferedImage imagem2; // imagem alternada
    private BufferedImage imagemAtual; // imagem q tá sendo exibida

    public JogoAlavanca(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/alavanca de comando 3.jpg", layout, painelPrincipal);

        try {
            // carrega as imagens só uma vez p/ economizar recurso
            imagem1 = ImageIO.read(new File("imagens/Fotos editadas/alavanca de comando 3.jpg"));
            imagem2 = ImageIO.read(new File("imagens/Fotos editadas/alavanca de comando 2.jpg"));
            imagemAtual = imagem1; // começa mostrando a primeira imagem
        } catch (IOException e) {
            e.printStackTrace();
        }

        // botao p/ voltar pra tela Parte1
        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60); // pos e tam do bt
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20)); // fonte do texto
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED); // cor do bt
        botaoVoltar.setContentAreaFilled(true); // deixa fundo visível
        botaoVoltar.setOpaque(true); // garante opacidade
        botaoVoltar.setBorderPainted(false); // tira borda
        botaoVoltar.addActionListener(e -> {
            System.out.println("botão voltar clicado!");
            layout.show(painelPrincipal, "Jogo"); // volta pra tela anterior
        });
        add(botaoVoltar); // add btn no painel

        // btn p/ ir pro menu principal
        JButton botaoMenu = new JButton("Menu");
        botaoMenu.setBounds(580, 10, 100, 60);
        botaoMenu.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoMenu.setForeground(Color.white);
        botaoMenu.setBackground(Color.BLACK);
        botaoMenu.setContentAreaFilled(true);
        botaoMenu.setOpaque(true);
        botaoMenu.setBorderPainted(false);
        botaoMenu.setLayout(null);
        botaoMenu.addActionListener(e -> {
            System.out.println("botão menu clicado!");
            layout.show(painelPrincipal, "Menu");
        });
        add(botaoMenu);

        // mouse click alterna entre as imagens
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imagemAlternada = !imagemAlternada; // troca o estado

                // muda a img conforme o estado
                if (imagemAlternada) {
                    imagemAtual = imagem2;
                } else {
                    imagemAtual = imagem1;
                }
                repaint(); // redesenha o painel p/ mostrar a nova img
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // limpa antes de desenhar, evita sobreposição
        if (imagemAtual != null) {
            g.drawImage(imagemAtual, 0, 0, getWidth(), getHeight(), this);
        }
        // desenha inventário no canto sup dir, usa largura p/ alinhar certinho
        InventarioUI.desenhar((Graphics2D) g, getWidth());
    }
}
