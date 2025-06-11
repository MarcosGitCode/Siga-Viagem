import java.awt.*;
import javax.swing.*;

public class JogoDireita extends BasePainelComBotao {
    private String imagemAtual = "imagens/Fotos editadas/direita3.png";
    private final String imagemOriginal = "imagens/Fotos editadas/direita3.png";
    private final String imagemAlternativa = "imagens\\Fotos editadas\\07 - Coluna lateral direita - DIC, Derivação de Portas e Chave do CBTC.jpg";
    private boolean pontosAdicionados = false;
    private String mensagemTemporaria = "";
    private long mensagemFim = 0;
    private boolean pontosAdicionadosChaveInserida = false;

    public JogoDireita(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/direita3.png", layout, painelPrincipal);
        setLayout(null);

        // Botão seta direita
        // JButton botaoSetaDireita = new JButton("");
        // botaoSetaDireita.setBounds(900, 300, 290, 300);
        // botaoSetaDireita.setContentAreaFilled(false);
        // botaoSetaDireita.setBorderPainted(false);
        // botaoSetaDireita.setFocusPainted(false);
        // botaoSetaDireita.setOpaque(false);
        // botaoSetaDireita.setForeground(Color.WHITE);
        // botaoSetaDireita.setFont(new Font("Arial", Font.BOLD, 20));
        // botaoSetaDireita.addActionListener(e -> {
        //     System.out.println("Botão seta direita clicado!");
        //     layout.show(painelPrincipal, "JogoEsquerda");
        // });
        // add(botaoSetaDireita);

        // Botão invisível para coletar a chave
        JButton botaoVisivel = new JButton();
        botaoVisivel.setBounds(546, 390, 100, 100);
        botaoVisivel.setContentAreaFilled(false);
        botaoVisivel.setOpaque(false);
        botaoVisivel.setBorderPainted(false);
        botaoVisivel.setFocusPainted(false);
        botaoVisivel.setText("");
        botaoVisivel.addActionListener(e -> {
            System.out.println("Botão visível clicado!");

            if (imagemAtual.equals(imagemOriginal)) {
                imagemAtual = imagemAlternativa;
                Inventario.adicionar("Chave");

                // Verifica se todos os itens foram coletados
                if (!pontosAdicionados && Inventario.contem("Cinturão") && Inventario.contem("Fita")) {
                    MetroviarioDAO dao = new MetroviarioDAO();
                    dao.adicionarPontuacao(UsuarioLogado.getRegistro(), 3);
                    pontosAdicionados = true;
                    EstadoJogo.chaveColetada = true;
                    mensagemTemporaria = "Parabéns! Você coletou todos os itens! (+3 pontos)";
                    mensagemFim = System.currentTimeMillis() + 3000;
                    System.out.println("3 pontos adicionados para: " + UsuarioLogado.getRegistro());
                }
            }
            else {
                imagemAtual = imagemOriginal;
                Inventario.remover("Chave");
                EstadoJogo.chaveInserida = true; // Remove a chave do inventário ao devolver
            }

            repaint();
        });
        add(botaoVisivel);

        // Botão Voltar para Parte1
        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60);
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED);
        botaoVoltar.setContentAreaFilled(true);
        botaoVoltar.setOpaque(true);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.addActionListener(e -> {
            System.out.println("Botão voltar clicado!");
            layout.show(painelPrincipal, "Jogo");
        });
        add(botaoVoltar);

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
        
        // Botão para JogoDireitaChaveCBTC
        JButton chaveCBTC = new JButton();
        chaveCBTC.setBounds(550, 650, 75, 75);
        chaveCBTC.setContentAreaFilled(false);
        chaveCBTC.setOpaque(false);
        chaveCBTC.setBorderPainted(false);
        chaveCBTC.setFocusPainted(false);
        chaveCBTC.setText("");
        chaveCBTC.addActionListener(e -> {
            System.out.println("Novo botão clicado!");
            layout.show(painelPrincipal, "JogoDireitaChaveCBTC");
        });
        add(chaveCBTC);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Verifica se a chave foi inserida e ainda não ganhou ponto
        if(EstadoJogo.portaAdesivo == true && EstadoJogo.chaveInserida == true) {
            EstadoJogo.chaveInserida = true; // Marca que a chave foi inserida
            if (EstadoJogo.chaveInserida && !pontosAdicionadosChaveInserida) {
                MetroviarioDAO dao = new MetroviarioDAO();
                dao.adicionarPontuacao(UsuarioLogado.getRegistro(), 1);
                pontosAdicionadosChaveInserida = true;

                mensagemTemporaria = "Você ganhou 1 ponto!";
                mensagemFim = System.currentTimeMillis() + 3000;
                System.out.println("1 ponto adicionado para: " + UsuarioLogado.getRegistro());
                repaint();
            }
        }
        // Desenha a imagem de fundo atual
        Image imagemFundo = new ImageIcon(imagemAtual).getImage();
        g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);

        // Desenha o inventário
        InventarioUI.desenhar((Graphics2D) g, getWidth());

        // Desenha a mensagem temporária se existir
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
        } else if (System.currentTimeMillis() >= mensagemFim) {
            mensagemTemporaria = "";
        }
    }
}
