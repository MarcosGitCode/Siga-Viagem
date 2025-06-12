import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

public class Parte1 extends PainelComImagem {
    private JPanel painelBotoes;

    public Parte1(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/01 - Painel.jpg");

        BorderLayout bl = new BorderLayout();
        setLayout(bl);

        painelBotoes = new JPanel();
        painelBotoes.setLayout(null);
        painelBotoes.setOpaque(false);
        add(painelBotoes, BorderLayout.CENTER);

        criarBotaoVisivel(painelBotoes, 40, 260, 300, 220, Color.RED, e -> {
            System.out.println("Botão DDU clicado!");
            layout.show(painelPrincipal, "JogoDDU");
        });

        criarBotaoVisivel(painelBotoes, 400, 400, 50, 60, Color.BLUE, e -> {
            System.out.println("Botão chave reversora clicado!");
            layout.show(painelPrincipal, "JogoReversoraOficial");
        });

        criarBotaoVisivel(painelBotoes, 450, 240, 330, 220, Color.PINK, e -> {
            System.out.println("Botão ADU clicado!");
            layout.show(painelPrincipal, "JogoADU");
        });

        criarBotaoVisivel(painelBotoes, 700, 470, 70, 130, Color.GREEN, e -> {
            System.out.println("Botão alavanca de comando clicado!");
            layout.show(painelPrincipal, "JogoAlavanca");
        });

        criarBotaoVisivel(painelBotoes, 900, 230, 290, 250, Color.YELLOW, e -> {
            System.out.println("Botão VDU clicado!");
            layout.show(painelPrincipal, "JogoVDU");
        });

        criarBotaoVisivel(painelBotoes, 900, 500, 290, 250, Color.RED, e -> {
            System.out.println("Botao seta direita clicado!");
            layout.show(painelPrincipal, "JogoDireita");
        });

        criarBotaoVisivel(painelBotoes, 40, 500, 290, 250, Color.RED, e -> {
            System.out.println("Botão seta esquerda clicado!");
            layout.show(painelPrincipal, "JogoEsquerdaOficial");
        });

        criarBotaoVisivel(painelBotoes, 322,331, 80, 80, Color.ORANGE, e -> {
            System.out.println("Botão PA clicado!");
            layout.show(painelPrincipal, "JogoPA");
        });

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
            System.out.println("Botão Menu clicado!");
            layout.show(painelPrincipal, "Menu");
        });
        painelBotoes.add(botaoMenu);

        painelPrincipal.add(new JogoDDU(layout, painelPrincipal), "JogoDDU");
        painelPrincipal.add(new JogoADU(layout, painelPrincipal), "JogoADU");
        painelPrincipal.add(new JogoReversoraOficial(layout, painelPrincipal), "JogoReversoraOficial");
        painelPrincipal.add(new JogoVDU(layout, painelPrincipal), "JogoVDU");
        painelPrincipal.add(new JogoDireita(layout, painelPrincipal), "JogoDireita");
        painelPrincipal.add(new JogoEsquerda(layout, painelPrincipal), "JogoEsquerda");
        painelPrincipal.add(new JogoAlavanca(layout, painelPrincipal), "JogoAlavanca");
        painelPrincipal.add(new JogoEsquerdaOficial(layout, painelPrincipal), "JogoEsquerdaOficial");
        painelPrincipal.add(new JogoDireita(layout, painelPrincipal), "JogoDireita");
        painelPrincipal.add(new JogoPA(layout, painelPrincipal), "JogoPA");
    }

    public void tocarSomAlarme() {
        try {
            File arquivoSom = new File("sons/alarme.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(arquivoSom);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            MenuOpcoes.setAudioClip(clip);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Erro ao tocar som: " + e.getMessage());
        }
    }

    private void criarBotaoVisivel(JPanel painel, int x, int y, int largura, int altura, Color cor, ActionListener acao) {
        JButton botao = new JButton();
        botao.setBounds(x, y, largura, altura);
        botao.setContentAreaFilled(false);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.addActionListener(acao);
        painel.add(botao);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        InventarioUI.desenhar((Graphics2D) g, getWidth());
    }
}
