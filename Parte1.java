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
        
        // Tocar o som de alarme
        

        // Layout geral com BorderLayout
        var bl = new BorderLayout();
        setLayout(bl);
        
        // Painel para os botões
        painelBotoes = new JPanel();
        painelBotoes.setLayout(null); // Layout absoluto para posicionar os botões
        painelBotoes.setOpaque(false); // Garantir que o fundo do painel seja transparente
        add(painelBotoes, BorderLayout.CENTER);

        // Criar botões
        criarBotaoVisivel(painelBotoes, 40, 260, 300, 220, Color.RED, e -> {
            System.out.println("Botão DDU clicado!");
            layout.show(painelPrincipal, "JogoDDU"); // Alterna para o painel JogoDDU
        });

        criarBotaoVisivel(painelBotoes, 400, 400, 50, 60, Color.BLUE, e -> {
            System.out.println("Botão chave reversora clicado!");
            layout.show(painelPrincipal, "JogoReversora"); // Alterna para o painel JogoReversora
        });

        criarBotaoVisivel(painelBotoes, 450, 240, 330, 220, Color.PINK, e -> {
            System.out.println("Botão ADU clicado!");
            layout.show(painelPrincipal, "JogoADU"); // Alterna para o painel JogoADU
        });

        criarBotaoVisivel(painelBotoes, 700, 470, 70, 130, Color.GREEN, e -> {
            System.out.println("Botão alavanca de comando clicado!");
            layout.show(painelPrincipal, "JogoAlavanca"); // Alterna para o painel JogoAlavanca
        });

        criarBotaoVisivel(painelBotoes, 900, 230, 290, 250, Color.YELLOW, e -> {
            System.out.println("Botão VDU clicado!");
            layout.show(painelPrincipal, "JogoVDU"); // Alterna para o painel JogoVDU
        });

        criarBotaoVisivel(painelBotoes, 900, 500, 290, 250, Color.RED, e -> {
            System.out.println("Botao seta direita clicado!");
            layout.show(painelPrincipal, "JogoDireita"); // Alterna para o painel JogoDireita
        });

        criarBotaoVisivel(painelBotoes, 40, 500, 290, 250, Color.RED, e -> {
            System.out.println("Botão seta esquerda clicado!");
            layout.show(painelPrincipal, "JogoEsquerdaOficial"); // Alterna para o painel JogoEsquerda
        });

        criarBotaoVisivel(painelBotoes, 322,331, 80, 80, Color.ORANGE, e -> {
            System.out.println("Botão PA clicado!");
            layout.show(painelPrincipal, "JogoPA"); // Alterna para o painel JogoPA
        });

        painelPrincipal.add(new JogoDDU(layout, painelPrincipal), "JogoDDU");
        painelPrincipal.add(new JogoADU(layout, painelPrincipal), "JogoADU");
        painelPrincipal.add(new JogoReversora(layout, painelPrincipal), "JogoReversora");
        painelPrincipal.add(new JogoVDU(layout, painelPrincipal), "JogoVDU");
        painelPrincipal.add(new JogoDireita(layout, painelPrincipal), "JogoDireita");
        painelPrincipal.add(new JogoEsquerda(layout, painelPrincipal), "JogoEsquerda");
        painelPrincipal.add(new JogoAlavanca(layout, painelPrincipal), "JogoAlavanca");
        painelPrincipal.add(new JogoEsquerdaOficial(layout, painelPrincipal), "JogoEsquerdaOficial");
        painelPrincipal.add(new JogoDireitaChave(layout, painelPrincipal), "JogoDireitaChave"); // Adicionado conforme sugestão
        painelPrincipal.add(new JogoPA(), "JogoPA");
    }
    

     public void tocarSomAlarme() {
        try {
            File arquivoSom = new File("sons/alarme.wav"); // Certifique-se de criar uma pasta 'sons' e adicionar um arquivo de alarme
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(arquivoSom);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            MenuOpcoes.setAudioClip(clip);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Erro ao tocar som: " + e.getMessage());
        }
    }

    // Método para criar botões visíveis
    private void criarBotaoVisivel(JPanel painel, int x, int y, int largura, int altura, Color cor, ActionListener acao) {
        JButton botao = new JButton();
        botao.setBounds(x, y, largura, altura); // Posicionar o botão
        botao.setContentAreaFilled(false); // Torna o fundo do botão transparente
        botao.setFocusPainted(false); // Remove o destaque ao focar no botão
        botao.setBorderPainted(false); // Remove as bordas do botão
        botao.addActionListener(acao); // Define a ação para o botão
        painel.add(botao); // Adiciona o botão ao painel
    }
    @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // Desenha o inventário no canto superior direito
    InventarioUI.desenhar((Graphics2D) g, getWidth());
}
}
