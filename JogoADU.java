import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JButton;

public class JogoADU extends BasePainelComBotao {

    public JogoADU(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/02 - ADU e sinaleiras.jpg", layout, painelPrincipal);
        setLayout(null);

         // Botão Voltar para Parte1
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

        // Adicione outros componentes específicos do JogoADU aqui
    }
}
