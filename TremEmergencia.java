import javax.swing.*;
import java.awt.*;

public class TremEmergencia extends JPanel {

    public TremEmergencia(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null);

        // Teste de carregamento da imagem
        String caminho = "imagens/Fotos editadas/16 - Dispositivos de emergência.jpg";
        ImageIcon imagemOriginal = new ImageIcon(caminho);

        // Redimensionar a imagem ao tamanho da janela
        Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(1280, 856, Image.SCALE_SMOOTH);
        ImageIcon imagem = new ImageIcon(imagemRedimensionada);

        JLabel labelImagem = new JLabel(imagem);
        labelImagem.setBounds(0, 0, 1280, 856);
        add(labelImagem);

                // Botão Voltar para JogoEsquerda
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
    }

