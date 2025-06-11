import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class JogoItens extends JPanel {

    private Image imagemFundo;
    private Image sobreposicaoCinturao;
    private Image sobreposicaoFita;
    private boolean mostrarCinturao = true;
    private boolean mostrarFita = true;

    // Mensagem temporária
    private String mensagemTemporaria = "";
    private long mensagemFim = 0;

    public JogoItens(CardLayout layout, JPanel painelPrincipal) {
        // Carrega a imagem de fundo
        imagemFundo = new ImageIcon("imagens/Fotos editadas/fundoItens.png").getImage();
        // Carrega as sobreposições
        sobreposicaoCinturao = new ImageIcon("imagens/Fotos editadas/ItensCinturao.png").getImage();
        sobreposicaoFita = new ImageIcon("imagens/Fotos editadas/ItensFita.png").getImage();

        setLayout(null);

        // Botão de voltar
        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60);
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED);
        botaoVoltar.setContentAreaFilled(true);
        botaoVoltar.setOpaque(true);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.addActionListener(e -> {
            layout.show(painelPrincipal, "JogoEsquerdaOficial");
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
        
        // Botão quadrado 1 (Cinturão)
        JButton botaoQuadrado1 = new JButton();
        botaoQuadrado1.setBounds(100, 250, 200, 400);
        botaoQuadrado1.setContentAreaFilled(false);
        botaoQuadrado1.setOpaque(false);
        botaoQuadrado1.setBorderPainted(false);
        botaoQuadrado1.addActionListener(e -> {
            if (!Inventario.contem("Cinturão")) {
                Inventario.adicionar("Cinturão");
                mostrarCinturao = false;
                mostrarMensagemTemporaria("Cinturão adicionado ao inventário");
                repaint();
            }
        });
        add(botaoQuadrado1);

        // Botão quadrado 2 (Fita)
        JButton botaoQuadrado2 = new JButton();
        botaoQuadrado2.setBounds(380, 300, 800, 300);
        botaoQuadrado2.setContentAreaFilled(false);
        botaoQuadrado2.setOpaque(false);
        botaoQuadrado2.setBorderPainted(false);
        botaoQuadrado2.addActionListener(e -> {
            if (!Inventario.contem("Fita")) {
                Inventario.adicionar("Fita");
                mostrarFita = false;
                mostrarMensagemTemporaria("Fita adicionada ao inventário");
                repaint();
            }
        });
        add(botaoQuadrado2);
    }

    private void mostrarMensagemTemporaria(String mensagem) {
        mensagemTemporaria = mensagem;
        mensagemFim = System.currentTimeMillis() + 5000;
        repaint();
        // Timer para esconder a mensagem após 5 segundos
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mensagemTemporaria = "";
                repaint();
            }
        }, 5000);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Desenha a imagem de fundo
        if (imagemFundo != null) {
            g2d.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
        }

        // Desenha a sobreposição do cinto, se permitido
        if (mostrarCinturao && sobreposicaoCinturao != null) {
            g2d.drawImage(sobreposicaoCinturao, 0, 0, 1280, 853, this);
        }

        // Desenha a sobreposição da fita, se permitido
        if (mostrarFita && sobreposicaoFita != null) {
            g2d.drawImage(sobreposicaoFita, 0, 0, 1280, 853, this);
        }

        // Inventário no canto superior direito
        InventarioUI.desenhar(g2d, getWidth());
    }
}
