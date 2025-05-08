import java.awt.*;
import javax.swing.*;

public abstract class BasePainelComBotao extends PainelComImagem {

    public BasePainelComBotao(String caminhoImagem, CardLayout layout, JPanel painelPrincipal) {
        super(caminhoImagem); // Define a imagem de fundo
        setLayout(null); // Layout absoluto para posicionar o botão universal

        // Adicionar botão de configurações universal
        JButton botaoConfiguracoes = new JButton("⚙");
        botaoConfiguracoes.setBounds(10, 10, 40, 40); // Define o tamanho e a posição do botão
        botaoConfiguracoes.setContentAreaFilled(true); // Torna o fundo visível temporariamente
        botaoConfiguracoes.setBackground(Color.LIGHT_GRAY); // Cor de fundo temporária
        botaoConfiguracoes.setFocusPainted(false); // Remove o destaque ao focar
        botaoConfiguracoes.setBorderPainted(true); // Exibe as bordas temporariamente
        botaoConfiguracoes.addActionListener(e -> {
            System.out.println("Botão de configurações clicado!");
            layout.show(painelPrincipal, "Parte1"); // Volta para o painel principal (Parte1)
        });
        add(botaoConfiguracoes); // Adiciona o botão ao painel
        setComponentZOrder(botaoConfiguracoes, 0); // Garante que o botão fique no topo
    }
}