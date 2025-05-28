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
        botao1 = new JButton("Opção 1");
        botao1.setBounds(400, 300, 800, 300); // Posição e tamanho
        botao1.setOpaque(false); // Torna o botão transparente
        botao1.setContentAreaFilled(false); // Remove a área de fundo
        botao1.setBorderPainted(false); // Remove as bordas do botão
        botao1.addActionListener(e -> {
            System.out.println("Botão 1 clicado!");
            imgFita = null; // Apaga a imagem da fita
            repaint(); // Atualiza o painel para remover a imagem da fita

            // Aguarda 1 segundo e exibe a primeira imagem no topo
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    imgAdesivo = new ImageIcon("imagens/Fotos editadas/21 - Adesivo de porta isolada - 1 terço instalado.jpg").getImage();
                    repaint(); // Atualiza o painel para desenhar a nova imagem

                    // Aguarda 0,5 segundos e exibe a segunda imagem no topo
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            imgAdesivo = new ImageIcon("imagens/Fotos editadas/22 - Adesivo de porta isolada - 2 terços instalado.jpg").getImage();
                            repaint(); // Atualiza o painel para desenhar a segunda imagem

                            // Aguarda mais 0,5 segundos e exibe a terceira imagem no topo
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    imgAdesivo = new ImageIcon("imagens/Fotos editadas/Adesivo de porta isolada instalado.jpg").getImage();
                                    repaint(); // Atualiza o painel para desenhar a terceira imagem


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
        botao2 = new JButton("Opção 2");
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
            layout.show(painelPrincipal, "TremPortaAberta"); // Volta para o painel anterior
        });
        add(botaoVoltar); // Adiciona o botão ao painel
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag) {
            // Atualiza a visibilidade dos botões com base no estado atual
            botao1.setVisible(InventarioUI.isPegouFita()); // Botão 1 aparece se tiver a fita
            botao2.setVisible(InventarioUI.isPegouCinturao()); // Botão 2 aparece se tiver o cinturão
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenha a imagem de fundo
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
        }

        // Verifica se o jogador pegou o cinturão e desenha a imagem ocupando a tela inteira
        if (InventarioUI.isPegouCinturao()) {
            g.drawImage(imgCinturao, 0, 0, getWidth(), getHeight(), this);
        }

        // Verifica se o jogador pegou a fita e desenha a imagem ocupando a tela inteira
        if (InventarioUI.isPegouFita() && imgFita != null) {
            g.drawImage(imgFita, 0, 0, getWidth(), getHeight(), this);
        }

        // Desenha a nova imagem no topo, se existir
        if (imgAdesivo != null) {
            g.drawImage(imgAdesivo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
