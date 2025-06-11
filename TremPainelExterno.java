import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TremPainelExterno extends JPanel {

    private Image imagemFundo;
    private boolean pontosAdicionados = false; // Adicionado para controlar a pontuação
    private String mensagemTemporaria;
    private long mensagemFim;

    public TremPainelExterno(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null);

        // Botão para voltar
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
            if (EstadoJogo.painelExternoFechado == false) {
                mensagemTemporaria = "Você não fechou o painel! (-3 pontos)";
                EstadoJogo.pontosPerdidos += 3; 
                System.out.println("Pontos perdidos: " + EstadoJogo.pontosPerdidos);
                mensagemFim = System.currentTimeMillis() + 3000;
                repaint();
                // Aguarda 3 segundos antes de trocar de tela
                new javax.swing.Timer(3000, ev -> {
                    layout.show(painelPrincipal, "TremPortaAberta");
                }) {{
                    setRepeats(false);
                    start();
                }};
            } else {
                layout.show(painelPrincipal, "TremPortaAberta");
            }
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

        // Botão no centro da tela
        JButton botaoPainelExterno = new JButton();
        botaoPainelExterno.setBounds(410, 300, 400, 300);
        botaoPainelExterno.setOpaque(false);
        botaoPainelExterno.setContentAreaFilled(false);
        botaoPainelExterno.setBorderPainted(false);
        botaoPainelExterno.addActionListener(e -> {
            System.out.println("Botão do painel externo clicado!");
            
            // Atualiza a imagem de fundo
            try {
                imagemFundo = ImageIO.read(new File("imagens/Fotos editadas/18 - Painel externo aberto.jpg"));
                repaint(); // Redesenha o painel para refletir a nova imagem
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            // Remove o botão da tela
            remove(botaoPainelExterno);
            revalidate(); // Atualiza o layout
            repaint();    // Redesenha o painel
            
            JButton botaoFecharPainel = new JButton("Fechar Painel");
            botaoFecharPainel.setBounds(580, 150, 200, 60);
            botaoFecharPainel.setFont(new Font("Arial", Font.PLAIN, 20));
            botaoFecharPainel.setForeground(Color.white);
            botaoFecharPainel.setBackground(Color.RED);
            botaoFecharPainel.setContentAreaFilled(true);
            botaoFecharPainel.setOpaque(true);
            botaoFecharPainel.setBorderPainted(false);
            botaoFecharPainel.setLayout(null);
            botaoFecharPainel.addActionListener(es -> {
                System.out.println("Botão FecharPainel clicado!");
                try {
                imagemFundo = ImageIO.read(new File("imagens/Fotos editadas/17 - Painel externo fechado.jpg"));
                remove(botaoFecharPainel);
                EstadoJogo.painelExternoFechado = true; 
                repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        });
        add(botaoFecharPainel);
            // Adiciona o botão central
            JButton botaoCentral = new JButton();
            int tamanho = 100;
            botaoCentral.setBounds(590 - (tamanho / 2), 560 - (tamanho / 2), tamanho, tamanho);
            botaoCentral.setContentAreaFilled(false);
            botaoCentral.setBorderPainted(false);
            botaoCentral.setFocusPainted(false);
            botaoCentral.setOpaque(false);
            botaoCentral.addActionListener(event -> {
                System.out.println("Botão central clicado!");

                // Adiciona 3 pontos ao usuário
                if (!pontosAdicionados) {
                    MetroviarioDAO dao = new MetroviarioDAO();
                    dao.adicionarPontuacao(UsuarioLogado.getRegistro(), 3); // <-- aqui!
                    pontosAdicionados = true;
                    EstadoJogo.luzesApagadas = true;
                    mensagemTemporaria = "Você ganhou 3 pontos!";
                    mensagemFim = System.currentTimeMillis() + 3000; // 3 segundos

                    System.out.println("3 pontos adicionados para: " + UsuarioLogado.getRegistro());
                    repaint();
                }

                // Atualiza a imagem de fundo
                try {
                    imagemFundo = ImageIO.read(new File("imagens/Fotos editadas/19 - Painel externo porta 3 isolada.jpg"));
                    repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                // Remove o botão da tela
                EstadoJogo.luzesApagadas = true;
                remove(botaoCentral);
                revalidate();
                repaint();
            });
            add(botaoCentral);
            revalidate(); // Atualiza o layout novamente
            repaint();  
            JButton botaoIsolamento = new JButton();
            int tamanhos = 100;
            botaoIsolamento.setBounds(350, 560 - (tamanhos / 2), 650, 100);
            botaoIsolamento.setContentAreaFilled(false);
            botaoIsolamento.setBorderPainted(false);
            botaoIsolamento.setFocusPainted(false);
            botaoIsolamento.setOpaque(false);
            botaoIsolamento.setBackground(Color.RED);
            botaoIsolamento.addActionListener(event -> {
                EstadoJogo.pontosPerdidos += 3;
                mensagemTemporaria = "Você perdeu 3 pontos por isolar a porta errada!";
                repaint();
            });
            add(botaoIsolamento);
        });
        add(botaoPainelExterno);

        // Carrega a imagem de fundo
        try {
            BufferedImage imagemOriginal = ImageIO.read(new File("imagens/Fotos editadas/17 - Painel externo fechado.jpg"));
            imagemFundo = imagemOriginal.getScaledInstance(1280, 856, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
            imagemFundo = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenha a imagem de fundo
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, 1280, 856, this);
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