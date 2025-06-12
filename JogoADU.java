import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;

public class JogoADU extends BasePainelComBotao {

    public JogoADU(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/02 - ADU e sinaleiras.jpg", layout, painelPrincipal);
        setLayout(null);

        // Botão Voltar
        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60);
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED);
        botaoVoltar.setContentAreaFilled(true);
        botaoVoltar.setOpaque(true);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.addActionListener(e -> {
            SomUtils.tocarSom("/sons/click.wav"); // Caminho relativo ao classpath
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
        // Outros componentes do jogo aqui...
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // limpa o painel antes de desenhar, evita sobreposição
        // desenha o inventário no canto sup dir da tela, usando largura p/ posicionar certinho
        InventarioUI.desenhar((Graphics2D) g, getWidth());
    }
}
