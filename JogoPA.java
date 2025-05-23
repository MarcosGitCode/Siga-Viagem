import java.awt.*;
import javax.swing.*;

public class JogoPA extends JPanel {

    private Image imagemFundo;

    public JogoPA(CardLayout layout, JPanel painelPrincipal) {
        // Carrega a imagem de fundo
        imagemFundo = new ImageIcon("imagens/Fotos editadas/05 - Módulo de Comunicação - tela de início.jpg").getImage();
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
            layout.show(painelPrincipal, "Jogo");
        });
        add(botaoVoltar);

        // Botão Emitir PA
        JButton botaoEmitirPA = new JButton();
        botaoEmitirPA.setBounds(195, 180, 75, 75); // Define a posição e o tamanho do botão
        botaoEmitirPA.setFont(new Font("Arial", Font.BOLD, 16));
        botaoEmitirPA.setText(""); // Remove o texto do botão
        botaoEmitirPA.setContentAreaFilled(false); // Remove o preenchimento do botão
        botaoEmitirPA.setOpaque(false); // Torna o botão transparente
        botaoEmitirPA.setBorderPainted(false); // Remove a borda do botão
        botaoEmitirPA.addActionListener(e -> {
            System.out.println("Botão Emitir PA clicado!");

            // Cria um SwingWorker para gerenciar a troca de imagens
            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    // Exibe a imagem "Módulo de Comunicação - Gongo" por 2 segundos
                    imagemFundo = new ImageIcon("imagens/Fotos editadas/Módulo de Comunicação - Gongo.jpg").getImage();
                    repaint(); // Atualiza a tela
                    Thread.sleep(2000); // Aguarda 2 segundos

                    // Exibe a imagem "05B Módulo de Comunicação - Microfone aberto PA ao vivo" por 5 segundos
                    imagemFundo = new ImageIcon("imagens/Fotos editadas/05B Módulo de Comunicação - Microfone aberto PA ao vivo.jpg").getImage();
                    repaint(); // Atualiza a tela
                    Thread.sleep(5000); // Aguarda 5 segundos

                    return null;
                }

                @Override
                protected void done() {
                    // Retorna para a imagem inicial
                    imagemFundo = new ImageIcon("imagens/Fotos editadas/05 - Módulo de Comunicação - tela de início.jpg").getImage();
                    repaint(); // Atualiza a tela para exibir a imagem inicial
                }
            }.execute();
        });
        add(botaoEmitirPA);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenha a imagem de fundo cobrindo toda a tela
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
