import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class TremDecisao extends JPanel {

    private final CardLayout layout;
    private final JPanel painelPrincipal;
    private Image imagemFundo;
    private Image imgCinturao;
    private Image imgFita;
    private Image imgChave;
    private Image imgAdesivo;

    private JButton botao1;
    private JButton botao2;
    private JButton botao3;
    private boolean pontosAdicionadosChecarLuzes = false;
    private String mensagemTemporaria = "";
    private long mensagemFim = 0;

    private boolean portafechada = false;

    public TremDecisao(CardLayout layout, JPanel painelPrincipal) {
        this.layout = layout;
        this.painelPrincipal = painelPrincipal;
        imagemFundo = new ImageIcon("imagens/Fotos editadas/decisao.jpg").getImage();
        imgCinturao = new ImageIcon("imagens/Fotos editadas/ItensCinturao.png").getImage();
        imgFita = new ImageIcon("imagens/Fotos editadas/ItensFita.png").getImage();
        imgChave = new ImageIcon("imagens/Fotos editadas/ItensChave.png").getImage();
        imgAdesivo = null;
        setLayout(null);

        JButton botaoChecarLuzes = new JButton("");
        botaoChecarLuzes.setBounds(800, 100, 200, 50);
        botaoChecarLuzes.setBackground(new Color(255, 0, 0));
        botaoChecarLuzes.setFocusPainted(false);
        botaoChecarLuzes.setBorderPainted(false);
        botaoChecarLuzes.setOpaque(false);
        botaoChecarLuzes.addActionListener(e -> {
            layout.show(painelPrincipal, "TremLuzesApagadas");
            if (EstadoJogo.luzesApagadas == true && !pontosAdicionadosChecarLuzes) {
                MetroviarioDAO dao = new MetroviarioDAO();
                dao.adicionarPontuacao(UsuarioLogado.getRegistro(), 2);
                pontosAdicionadosChecarLuzes = true;

                mensagemTemporaria = "Você ganhou 2 pontos!";
                mensagemFim = System.currentTimeMillis() + 3000;

                System.out.println("2 pontos adicionados para: " + UsuarioLogado.getRegistro());
                repaint();
            }
        });
        add(botaoChecarLuzes);
        painelPrincipal.add(new TremLuzesApagadas(layout, painelPrincipal), "TremLuzesApagadas");
        botao1 = new JButton("");
        botao1.setOpaque(false);
        botao1.setContentAreaFilled(false);
        botao1.setBorderPainted(false);
        botao1.addActionListener(e -> {
            if (!portafechada) {
                MetroviarioDAO dao = new MetroviarioDAO();
                dao.adicionarPontuacao(UsuarioLogado.getRegistro(), 2);
                portafechada = true;
                JOptionPane.showMessageDialog(this, "Você ganhou 2 pontos!");
                System.out.println("2 pontos adicionados para: " + UsuarioLogado.getRegistro());
            }

            Inventario.remover("Fita");
            repaint();

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    imgAdesivo = new ImageIcon("imagens/Fotos editadas/21 - Adesivo de porta isolada - 1 terço instalado.jpg").getImage();
                    repaint();
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            imgAdesivo = new ImageIcon("imagens/Fotos editadas/22 - Adesivo de porta isolada - 2 terços instalado.jpg").getImage();
                            repaint();
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    imgAdesivo = new ImageIcon("imagens/Fotos editadas/Adesivo de porta isolada instalado.jpg").getImage();
                                    repaint();
                                    new Timer().schedule(new TimerTask() {
                                        @Override
                                        public void run() {
                                            botaoChecarLuzes.setText("Checar luzes");
                                            botaoChecarLuzes.setOpaque(true);
                                            botaoChecarLuzes.setFont(new Font("Arial", Font.BOLD, 18));
                                            botaoChecarLuzes.setForeground(Color.WHITE);
                                            botao1.setVisible(false);
                                            botao1.setEnabled(false);
                                            botao2.setVisible(false);
                                            botao2.setEnabled(false);
                                            botao3.setVisible(false);
                                            botao3.setEnabled(false);
                                            EstadoJogo.portaAdesivo = true;
                                            EstadoJogo.luzesApagadas = true;
                                            layout.show(painelPrincipal, "Parte1");
                                        }
                                    }, 500);
                                }
                            }, 500);
                        }
                    }, 500);
                }
            }, 1000);
        });
        botao1.setVisible(false);
        add(botao1);

        botao2 = new JButton("");
        botao2.setBounds(100, 300, 300, 300);
        botao2.addActionListener(e -> System.out.println("Botão 2 clicado!"));
        botao2.setOpaque(false);
        botao2.setContentAreaFilled(false);
        botao2.setBorderPainted(false);
        botao2.setVisible(false);
        add(botao2);

        botao3 = new JButton("");
        botao3.setBounds(540, 550, 200, 200);
        botao3.setOpaque(false);
        botao3.setContentAreaFilled(false);
        botao3.setBorderPainted(false);
        botao3.setVisible(false);
        botao3.addActionListener(e -> {
            MetroviarioDAO dao = new MetroviarioDAO();
            dao.adicionarPontuacao(UsuarioLogado.getRegistro(), -1);
            System.out.println("Botão 3 clicado!");
            EstadoJogo.pontosPerdidos += 1;
            mensagemTemporaria = "Você inseriu a chave no lugar errado! (-1 ponto)";
            repaint();
        });
        add(botao3);

        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60);
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED);
        botaoVoltar.setContentAreaFilled(true);
        botaoVoltar.setOpaque(true);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.addActionListener(e -> {
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
            layout.show(painelPrincipal, "Menu");
        });
        add(botaoMenu);
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag) {
            botao1.setVisible(Inventario.contem("Fita"));
            botao2.setVisible(Inventario.contem("Cinturão"));
            botao3.setVisible(Inventario.contem("Chave"));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
        }

        if (Inventario.contem("Cinturão")) {
            Image imgCinturaoGrande = new ImageIcon("imagens/Fotos editadas/ItensCinturao2.png").getImage();
            int largura = 300, altura = 120;
            int x = (getWidth() - largura) / 2;
            int y = (getHeight() / 2) - altura - 20;
            g.drawImage(imgCinturaoGrande, x, y, largura, altura, this);
        }

        if (Inventario.contem("Chave")) {
            Image imgChave = new ImageIcon("imagens/Fotos editadas/ItensChave.png").getImage();
            int largura = 300, altura = 120;
            int x = (getWidth() - largura) / 2;
            int y = (getHeight() / 2) + 160;
            g.drawImage(imgChave, x, y, largura, altura, this);
        }
        if (Inventario.contem("Fita")) {
            Image imgFitaGrande = new ImageIcon("imagens/Fotos editadas/ItensFita2.png").getImage();
            int largura = 300, altura = 120;
            int x = (getWidth() - largura) / 2;
            int y = (getHeight() / 2) + 20;
            g.drawImage(imgFitaGrande, x, y, largura, altura, this);
        }

        if (imgAdesivo != null) {
            g.drawImage(imgAdesivo, 0, 0, getWidth(), getHeight(), this);
        }

        if (botao1 != null && Inventario.contem("Fita")) {
            int largura = 300, altura = 120;
            int x = (getWidth() - largura) / 2;
            int y = (getHeight() / 2) + 20;
            botao1.setBounds(x, y, largura, altura);
        }

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