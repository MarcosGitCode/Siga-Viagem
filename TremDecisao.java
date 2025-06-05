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
    private Image imgAdesivo; // Nova imagem para aparecer no topo

    private JButton botao1;
    private JButton botao2;

    private boolean portafechada = false; // Inicialmente, a porta está aberta

    public TremDecisao(CardLayout layout, JPanel painelPrincipal) {
        this.layout = layout;
        this.painelPrincipal = painelPrincipal;
        // Carrega a imagem de fundo
        imagemFundo = new ImageIcon("imagens/Fotos editadas/decisao.jpg").getImage();
        imgCinturao = new ImageIcon("imagens/Fotos editadas/ItensCinturao.png").getImage();
        imgFita = new ImageIcon("imagens/Fotos editadas/ItensFita.png").getImage();
        imgAdesivo = null; // Inicialmente, a imagem do adesivo é nula
        setLayout(null); // Define layout absoluto

        // Botão 1 (aparece apenas se o jogador tiver a fita)
        botao1 = new JButton("");
        botao1.setOpaque(false); // Torna o botão transparente
        botao1.setContentAreaFilled(false); // Remove a área de fundo
        botao1.setBorderPainted(false); // Remove as bordas do botão
        botao1.addActionListener(e -> {
            System.out.println("Botão 1 clicado!");
            Inventario.remover("Fita"); // Remove a fita do inventário
            repaint(); // Atualiza o painel

            // Inicia a sequência de frames:
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
                                    System.out.println("Adesivo instalado!");
                                    // Aguarda mais 0,5 segundos, apaga os botões e leva o jogador ao painel Parte1
                                    new Timer().schedule(new TimerTask() {
                                        @Override
                                        public void run() {
                                            System.out.println("Apagando os botões...");
                                            botao1.setVisible(false); // Torna o botão 1 invisível
                                            botao1.setEnabled(false); // Desativa o botão 1
                                            botao2.setVisible(false); // Torna o botão 2 invisível
                                            botao2.setEnabled(false); // Desativa o botão 2
                                            portafechada = true; // Marca que a porta foi fechada
                                            System.out.println("Levando o jogador ao painel Parte1...");
                                            layout.show(painelPrincipal, "Parte1"); // Leva o jogador ao painel Parte1
                                        }
                                    }, 500); // 500ms = 0,5 segundos

                                }
                            }, 500); // 500ms = 0,5 segundos
                        }
                    }, 500); // 500ms = 0,5 segundos
                }
            }, 1000); // 1000ms = 1 segundo
        });
        botao1.setVisible(false); // Deixa o botão invisível
        add(botao1);

        // Botão 2 (aparece apenas se o jogador tiver o cinturão)
        botao2 = new JButton("");
        botao2.setBounds(100, 300, 300, 300); // Posição e tamanho
        botao2.addActionListener(e -> System.out.println("Botão 2 clicado!"));
        botao2.setOpaque(false); // Torna o botão transparente
        botao2.setContentAreaFilled(false); // Remove a área de fundo
        botao2.setBorderPainted(false); // Remove as bordas do botão
        botao2.setVisible(false); // Deixa o botão invisível
        add(botao2);

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
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag) {
            botao1.setVisible(Inventario.contem("Fita")); // Corrigido!
            botao2.setVisible(Inventario.contem("Cinturão"));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenha a imagem de fundo
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
        }

        // Desenha o cinturão grande no centro se estiver no inventário
        if (Inventario.contem("Cinturão")) {
            Image imgCinturaoGrande = new ImageIcon("imagens/Fotos editadas/ItensCinturao2.png").getImage();
            int largura = 300, altura = 120;
            int x = (getWidth() - largura) / 2;
            int y = (getHeight() / 2) - altura - 20;
            g.drawImage(imgCinturaoGrande, x, y, largura, altura, this);
        }

        // Desenha a fita grande no centro se estiver no inventário
        if (Inventario.contem("Fita")) {
            Image imgFitaGrande = new ImageIcon("imagens/Fotos editadas/ItensFita2.png").getImage();
            int largura = 300, altura = 120;
            int x = (getWidth() - largura) / 2;
            int y = (getHeight() / 2) + 20;
            g.drawImage(imgFitaGrande, x, y, largura, altura, this);
        }

        // (restante do seu código para sobrepor imagens grandes)
        if (imgAdesivo != null) {
            g.drawImage(imgAdesivo, 0, 0, getWidth(), getHeight(), this);
        }

        if (botao1 != null && Inventario.contem("Fita")) {
            int largura = 300, altura = 120;
            int x = (getWidth() - largura) / 2;
            int y = (getHeight() / 2) + 20;
            botao1.setBounds(x, y, largura, altura);
        }
    }
}