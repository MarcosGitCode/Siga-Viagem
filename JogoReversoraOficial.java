import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class JogoReversoraOficial extends BasePainelComBotao {

    private boolean imagemAlternada = false;
    private BufferedImage imagemNeutro;
    private BufferedImage imagemFrente;
    private BufferedImage imagemAtual;
    private String registroUsuario;
    private SequenciaDecisoes sequencia;
    private int pontosAcumulados = 0;
    private boolean pontosAdicionadosChaveNeutro = false;
    private String mensagemTemporaria = "";
    private long mensagemFim = 0;
    private boolean pontosAdicionadosChaveFrente = false;
    private TelaFinal telaFinal;

    public JogoReversoraOficial(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/Reversora em neutro.jpg", layout, painelPrincipal);

        try {
            imagemNeutro = ImageIO.read(new File("imagens/Fotos editadas/Reversora em neutro.jpg"));
            imagemFrente = ImageIO.read(new File("imagens/Fotos editadas/08 - Reversora em frente.jpg"));
            imagemAtual = imagemNeutro;
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        JButton botaoFrente = new JButton("");
        botaoFrente.setBounds(570, 330, 200, 60);
        botaoFrente.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoFrente.setForeground(Color.BLACK);
        botaoFrente.setBackground(Color.GREEN);
        botaoFrente.setContentAreaFilled(false);
        botaoFrente.setOpaque(false);
        botaoFrente.setBorderPainted(false);
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
            EstadoJogo.reversoraFrente = true;
            mensagemTemporaria = "Você ganhou 1 ponto!";
            mensagemFim = System.currentTimeMillis() + 3000;
            System.out.println("1 ponto adicionado para: " + UsuarioLogado.getRegistro());
            repaint();
            repaint();
        });
        add(botaoFrente);

        JButton botaoNeutro = new JButton("");
        botaoNeutro.setBounds(780, 400, 60, 200);
        botaoNeutro.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoNeutro.setForeground(Color.BLACK);
        botaoNeutro.setBackground(Color.CYAN);
        botaoNeutro.setContentAreaFilled(false);
        botaoNeutro.setOpaque(false);
        botaoNeutro.setBorderPainted(false);
        botaoNeutro.addActionListener(e -> {
            System.out.println("Botão Neutro clicado!");
            EstadoJogo.reversoraNeutra = true;
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
        add(botaoNeutro);
        telaFinal = new TelaFinal(layout, painelPrincipal);
        painelPrincipal.add(telaFinal, "TelaFinal");
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagemAtual != null) {
            g.drawImage(imagemAtual, 0, 0, getWidth(), getHeight(), this);
        }

        InventarioUI.desenhar((Graphics2D) g, getWidth());

        if (mensagemTemporaria != null && !mensagemTemporaria.isEmpty()) {
            g.setFont(new Font("Arial", Font.BOLD, 32));
            int x = getWidth() / 2 - g.getFontMetrics().stringWidth(mensagemTemporaria) / 2;
            int y = 100;

            g.setColor(Color.BLACK);
            for (int dx = -2; dx <= 2; dx++) {
                for (int dy = -2; dy <= 2; dy++) {
                    if (dx != 0 || dy != 0) {
                        g.drawString(mensagemTemporaria, x + dx, y + dy);
                    }
                }
            }

            g.setColor(Color.WHITE);
            g.drawString(mensagemTemporaria, x, y);
        }
    }
}
