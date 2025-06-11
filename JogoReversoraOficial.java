import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class JogoReversoraOficial extends BasePainelComBotao {

    private boolean imagemAlternada = false; // Controle para alternar entre as imagens
    private BufferedImage imagemNeutro; // Imagem inicial
    private BufferedImage imagemFrente; // Imagem alternada
    private BufferedImage imagemAtual; // Imagem atualmente exibida
    private String registroUsuario; // Registro do usuário para pontuação
    private SequenciaDecisoes sequencia;
    private int pontosAcumulados = 0;
    private boolean pontosAdicionadosChaveNeutro = false; // Controle para evitar adicionar pontos repetidos
    private String mensagemTemporaria = "";
    private long mensagemFim = 0;
    private boolean pontosAdicionadosChaveFrente = false; // Controle para evitar adicionar pontos repetidos
    private TelaFinal telaFinal; // Adiciona a referência para TelaFinal

    public JogoReversoraOficial(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/Reversora em neutro.jpg", layout, painelPrincipal);

        try {
            // Carrega as imagens apenas uma vez
            imagemNeutro = ImageIO.read(new File("imagens/Fotos editadas/Reversora em neutro.jpg"));
            imagemFrente = ImageIO.read(new File("imagens/Fotos editadas/08 - Reversora em frente.jpg"));
            imagemAtual = imagemNeutro; // Define a imagem inicial
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Botão Voltar para Parte1
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
            layout.show(painelPrincipal, "Jogo"); // Volta para o painel anterior
        });
        add(botaoVoltar); // Adiciona o botão ao painel

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
        add(botaoMenu);
        
        JButton botaoFrente = new JButton("");
        botaoFrente.setBounds(570, 330, 200, 60); // Define a posição e o tamanho do botão
        botaoFrente.setFont(new Font("Arial", Font.PLAIN, 20)); // Define a fonte do texto
        botaoFrente.setForeground(Color.BLACK);
        botaoFrente.setBackground(Color.GREEN); // Define a cor do texto
        botaoFrente.setContentAreaFilled(false); // Remove o fundo visível
        botaoFrente.setOpaque(false); // Garante que o botão seja transparente
        botaoFrente.setBorderPainted(false); // Remove as bordas do botão
        botaoFrente.addActionListener(e -> {
            System.out.println("Botão Frente clicado!");
            try {
                imagemNeutro = ImageIO.read(new File("imagens/Fotos editadas/Reversora em neutro.jpg"));
                imagemFrente = ImageIO.read(new File("imagens/Fotos editadas/08 - Reversora em frente.jpg"));
                imagemAtual = imagemFrente;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
                MetroviarioDAO dao = new MetroviarioDAO();
                dao.adicionarPontuacao(UsuarioLogado.getRegistro(), 1);
                pontosAdicionadosChaveFrente = true;
                EstadoJogo.reversoraFrente = true; // Define o estado da reversora como frente
                mensagemTemporaria = "Você ganhou 1 ponto!";
                mensagemFim = System.currentTimeMillis() + 3000;
                System.out.println("1 ponto adicionado para: " + UsuarioLogado.getRegistro());
                repaint();
            repaint(); // <-- Adicione esta linha
        });
        add(botaoFrente); // Adiciona o botão ao painel
        
        JButton botaoNeutro = new JButton("");
        botaoNeutro.setBounds(780, 400, 60, 200); // Define a posição e o tamanho do botão
        botaoNeutro.setFont(new Font("Arial", Font.PLAIN, 20)); // Define a fonte do texto
        botaoNeutro.setForeground(Color.BLACK);
        botaoNeutro.setBackground(Color.CYAN); // Define a cor do texto
        botaoNeutro.setContentAreaFilled(false); // Remove o fundo visível
        botaoNeutro.setOpaque(false); // Garante que o botão seja transparente
        botaoNeutro.setBorderPainted(false); // Remove as bordas do botão
        botaoNeutro.addActionListener(e -> {
            System.out.println("Botão Neutro clicado!");
            EstadoJogo.reversoraNeutra = true; // Define o estado da reversora como neutro
            try {
                imagemNeutro = ImageIO.read(new File("imagens/Fotos editadas/Reversora em neutro.jpg"));
                imagemFrente = ImageIO.read(new File("imagens/Fotos editadas/08 - Reversora em frente.jpg"));
                imagemAtual = imagemNeutro;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (EstadoJogo.reversoraNeutra == true && EstadoJogo.chaveCBTCAM == true && !pontosAdicionadosChaveNeutro) {
                MetroviarioDAO dao = new MetroviarioDAO();
                dao.adicionarPontuacao(UsuarioLogado.getRegistro(), 1);
                pontosAdicionadosChaveNeutro = true;

                mensagemTemporaria = "Você ganhou 1 ponto!";
                mensagemFim = System.currentTimeMillis() + 3000;
                System.out.println("1 ponto adicionado para: " + UsuarioLogado.getRegistro());
                repaint();
                // Aguarda 3 segundos antes de trocar para TelaFinal
                new javax.swing.Timer(3000, ev -> {
                    telaFinal.atualizarPontuacao();
                    layout.show(painelPrincipal, "TelaFinal");
                }) {{
                    setRepeats(false);
                    start();
                }};
            } else if (EstadoJogo.reversoraNeutra == true && EstadoJogo.chaveCBTCAM == false) {
                MetroviarioDAO dao = new MetroviarioDAO();
                mensagemTemporaria = "Você precisa da chave CBTC em AM! (-1 ponto)";
                dao.adicionarPontuacao(UsuarioLogado.getRegistro(), -1);
                EstadoJogo.pontosPerdidos += 1;
                System.out.println("Pontos perdidos: " + EstadoJogo.pontosPerdidos);
                mensagemFim = System.currentTimeMillis() + 3000;
            }
            repaint();

            
        });
        add(botaoNeutro); // Adiciona o botão ao painel
        telaFinal = new TelaFinal(layout, painelPrincipal); // Inicializa telaFinal
        painelPrincipal.add(telaFinal, "TelaFinal");
}
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagemAtual != null) {
            g.drawImage(imagemAtual, 0, 0, getWidth(), getHeight(), this);
        }
        // Desenha o inventário no canto superior direito
        InventarioUI.desenhar((Graphics2D) g, getWidth());

        // Desenha a mensagem temporária, se houver
        if (mensagemTemporaria != null && !mensagemTemporaria.isEmpty()) {
            g.setFont(new Font("Arial", Font.BOLD, 32));
            int x = getWidth() / 2 - g.getFontMetrics().stringWidth(mensagemTemporaria) / 2;
            int y = 100;
            // Desenha a borda preta (várias vezes ao redor)
            g.setColor(Color.BLACK);
            for (int dx = -2; dx <= 2; dx++) {
                for (int dy = -2; dy <= 2; dy++) {
                    if (dx != 0 || dy != 0) {
                        g.drawString(mensagemTemporaria, x + dx, y + dy);
                    }
                }
            }
            // Desenha o texto branco por cima
            g.setColor(Color.WHITE);
            g.drawString(mensagemTemporaria, x, y);
        }
    }
}
