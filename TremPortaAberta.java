import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TremPortaAberta extends JPanel {

    private Image imagemFundo;
    private JButton botaoFecharPorta;
    private JButton botaoChecarLuzes;
    private boolean portafechada = false;

    private boolean pontosAdicionados = false;
    private boolean pontosAdicionadosInferior = false;
    private boolean pontosAdicionadosChecarLuzes = false;
    private boolean pontosAdicionadosChecarLuzes2 = false;
    private boolean pontosAdicionadosFecharPorta = false;
    private String mensagemTemporaria = "";
    private long mensagemFim = 0;

    public TremPortaAberta(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null);

        atualizarImagemFundo();

        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60);
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED);
        botaoVoltar.setContentAreaFilled(true);
        botaoVoltar.setOpaque(true);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.addActionListener(e -> {
            layout.show(painelPrincipal, "Trem1");
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
            layout.show(painelPrincipal, "Menu");
        });
        add(botaoMenu);

        JButton botaoInferior = new JButton();
        botaoInferior.setBounds(500, 650, 300, 300);
        botaoInferior.setOpaque(false);
        botaoInferior.setContentAreaFilled(false);
        botaoInferior.setBorderPainted(false);
        botaoInferior.addActionListener(e -> {
            if (!pontosAdicionadosInferior) {
                MetroviarioDAO dao = new MetroviarioDAO();
                dao.adicionarPontuacao(UsuarioLogado.getRegistro(), 1);
                pontosAdicionadosInferior = true;
                mensagemTemporaria = "Você ganhou 1 ponto!";
                mensagemFim = System.currentTimeMillis() + 3000;
                repaint();
            }
            layout.show(painelPrincipal, "PesOlhando");
        });
        add(botaoInferior);

        JButton botaoEmergencia = new JButton("Emergência");
        botaoEmergencia.setFont(new Font("Arial", Font.PLAIN, 18));
        botaoEmergencia.setForeground(Color.WHITE);
        botaoEmergencia.setBackground(new Color(220, 53, 69));
        botaoEmergencia.setFocusPainted(false);
        botaoEmergencia.setBounds(300, 400, 180, 200);
        botaoEmergencia.setVisible(false);
        botaoEmergencia.addActionListener(e -> {
            layout.show(painelPrincipal, "TremEmergencia");
        });
        add(botaoEmergencia);

        JButton botaoSetaCima = new JButton("↑");
        botaoSetaCima.setBounds(610, 575, 80, 50);
        botaoSetaCima.addActionListener(e -> {
            if (!pontosAdicionados) {
                MetroviarioDAO dao = new MetroviarioDAO();
                dao.adicionarPontuacao(UsuarioLogado.getRegistro(), 1);
                pontosAdicionados = true;
                EstadoJogo.verificarEmergencia = true;
                mensagemTemporaria = "Você ganhou 1 ponto!";
                mensagemFim = System.currentTimeMillis() + 3000;
                repaint();
            }
            layout.show(painelPrincipal, "TremEmergencia");
        });
        botaoSetaCima.setOpaque(false);
        botaoSetaCima.setContentAreaFilled(false);
        botaoSetaCima.setBorderPainted(false);
        add(botaoSetaCima);

        JButton botaoSetaDireita = new JButton();
        botaoSetaDireita.setBounds(1150, 680, 80, 40);
        botaoSetaDireita.setOpaque(false);
        botaoSetaDireita.setContentAreaFilled(false);
        botaoSetaDireita.setBorderPainted(false);
        botaoSetaDireita.addActionListener(e -> {
            layout.show(painelPrincipal, "TremPainelExterno");
        });
        add(botaoSetaDireita);

        botaoFecharPorta = new JButton("Fechar porta");
        botaoFecharPorta.setBounds(200, 100, 200, 50);
        botaoFecharPorta.setFont(new Font("Arial", Font.BOLD, 18));
        botaoFecharPorta.setForeground(Color.WHITE);
        botaoFecharPorta.setBackground(new Color(255, 0, 0));
        botaoFecharPorta.setFocusPainted(false);
        botaoFecharPorta.setBorderPainted(false);
        botaoFecharPorta.setOpaque(true);
        botaoFecharPorta.addActionListener(e -> {
            if (!pontosAdicionadosFecharPorta) {
                MetroviarioDAO dao = new MetroviarioDAO();
                dao.adicionarPontuacao(UsuarioLogado.getRegistro(), 2);
                pontosAdicionadosFecharPorta = true;
                mensagemTemporaria = "Você ganhou 2 pontos!";
                mensagemFim = System.currentTimeMillis() + 3000;
                repaint();
            }
            portafechada = true;
            atualizarImagemFundo();
            removerBotaoFecharPorta();
            removerBotaoChecarLuzes();
            layout.show(painelPrincipal, "TremPortaFechada");
        });
        add(botaoFecharPorta);

        botaoChecarLuzes = new JButton("Checar luzes");
        botaoChecarLuzes.setBounds(800, 100, 200, 50);
        botaoChecarLuzes.setFont(new Font("Arial", Font.BOLD, 18));
        botaoChecarLuzes.setForeground(Color.WHITE);
        botaoChecarLuzes.setBackground(new Color(255, 0, 0));
        botaoChecarLuzes.setFocusPainted(false);
        botaoChecarLuzes.setBorderPainted(false);
        botaoChecarLuzes.setOpaque(true);
        botaoChecarLuzes.addActionListener(e -> {
            if (!pontosAdicionadosChecarLuzes) {
                MetroviarioDAO dao = new MetroviarioDAO();
                dao.adicionarPontuacao(UsuarioLogado.getRegistro(), 2);
                pontosAdicionadosChecarLuzes = true;
                mensagemTemporaria = "Você ganhou 2 pontos!";
                mensagemFim = System.currentTimeMillis() + 3000;
                repaint();
            }
            atualizarImagemFundo();
            if (EstadoJogo.luzesApagadas) {
                layout.show(painelPrincipal, "TremLuzesApagadas");
            } else {
                layout.show(painelPrincipal, "TremLuzesAcesas");
            }
        });
        add(botaoChecarLuzes);

        painelPrincipal.add(new TremDecisao(layout, painelPrincipal), "TremDecisao");
        painelPrincipal.add(new TremLuzesAcesas(layout, painelPrincipal), "TremLuzesAcesas");

        JButton botaoChecarLuzes2 = new JButton("Checar luzes");
        botaoChecarLuzes2.setBounds(800, 100, 200, 50);
        botaoChecarLuzes2.setFont(new Font("Arial", Font.BOLD, 18));
        botaoChecarLuzes2.setForeground(Color.WHITE);
        botaoChecarLuzes2.setBackground(new Color(255, 0, 0));
        botaoChecarLuzes2.setFocusPainted(false);
        botaoChecarLuzes2.setBorderPainted(false);
        botaoChecarLuzes2.setOpaque(true);
        botaoChecarLuzes2.addActionListener(e -> {
            System.out.println("Botão 'checar luzes' clicado!");

            // Adiciona 2 pontos ao usuário, se ainda não adicionou
            if (EstadoJogo.luzesApagadas == true && !pontosAdicionadosChecarLuzes2) {
                MetroviarioDAO dao = new MetroviarioDAO();
                dao.adicionarPontuacao(UsuarioLogado.getRegistro(), 2);
                pontosAdicionadosChecarLuzes2 = true;

                mensagemTemporaria = "Você ganhou 2 pontos!";
                mensagemFim = System.currentTimeMillis() + 3000; // 3 segundos

                System.out.println("2 pontos adicionados para: " + UsuarioLogado.getRegistro());
                repaint();
            }

            atualizarImagemFundo(); // Update the background image
            layout.show(painelPrincipal, "TremLuzesApagadas"); // Switch to TremLuzesApagadas panel
        });
        add(botaoChecarLuzes2);

        painelPrincipal.add(new TremDecisao(layout, painelPrincipal), "TremDecisao");
        painelPrincipal.add(new TremLuzesApagadas(layout, painelPrincipal), "TremLuzesApagadas");
    }

    // Method to update the background image based on portafechada
    private void atualizarImagemFundo() {
        String caminhoImagem = portafechada
                ? "imagens/Fotos editadas/Adesivo de porta isolada instalado.jpg"
                : "imagens/Fotos editadas/14 - Porta aberta - externo (2).jpg";
        try {
            BufferedImage img = ImageIO.read(new File(caminhoImagem));
            imagemFundo = img.getScaledInstance(1280, 856, Image.SCALE_SMOOTH);
            repaint(); // Redraw the panel with the new image
        } catch (IOException e) {
            e.printStackTrace();
            imagemFundo = null;
        }
    }

    // Method to remove only the "Fechar porta" button
    private void removerBotaoFecharPorta() {
        if (botaoFecharPorta != null) {
            remove(botaoFecharPorta); // Remove the button from the panel
            botaoFecharPorta = null; // Clear the reference
            repaint(); // Redraw the panel
        }
    }

    private void removerBotaoChecarLuzes() {
        if (botaoChecarLuzes != null) {
            remove(botaoChecarLuzes); // Remove the button from the panel
            botaoChecarLuzes = null; // Clear the reference
            repaint(); // Redraw the panel
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, 1280, 856, this);
        }

        // Draw temporary message if it exists
        if (!mensagemTemporaria.isEmpty() && System.currentTimeMillis() < mensagemFim) {
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.setColor(Color.WHITE);
            FontMetrics fm = g.getFontMetrics();
            int msgWidth = fm.stringWidth(mensagemTemporaria);
            int x = (getWidth() - msgWidth) / 2;
            g.drawString(mensagemTemporaria, x, 100);
        }

        // Draw the inventory
        InventarioUI.desenhar((Graphics2D) g, getWidth());
    }
}