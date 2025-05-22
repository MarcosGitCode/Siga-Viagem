import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class JogoDireitaChaveCBTC extends BasePainelComBotao {

    private JLabel imagemChaveCBTCRM;
    private JLabel imagemChaveCBTCAM;

    public JogoDireitaChaveCBTC(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/09 - Chave do CBTC - MCS.jpg", layout, painelPrincipal);

        // Botão Voltar para Parte1
        JButton botaoVoltar = new JButton("<");
        botaoVoltar.setBounds(10, 10, 60, 60); // Define a posição e o tamanho do botão
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20)); // Define a fonte do texto
        botaoVoltar.setForeground(Color.BLACK);
        botaoVoltar.setBackground(Color.RED); // Define a cor do texto
        botaoVoltar.setContentAreaFilled(true); // Fundo visível
        botaoVoltar.setOpaque(true); // Garante que o fundo seja opaco
        botaoVoltar.setBorderPainted(false); // Remove as bordas do botão
        botaoVoltar.addActionListener(e -> {
            System.out.println("Botão voltar clicado!");
            layout.show(painelPrincipal, "JogoDireita"); // Volta para o painel anterior
        });
        add(botaoVoltar); // Adiciona o botão ao painel

        // Botão chaveCBTCRM
        JButton chaveCBTCRM = new JButton();
        chaveCBTCRM.setBounds(700, 400, 75, 75); // Posição e tamanho do botão
        chaveCBTCRM.setContentAreaFilled(false); // Remove a área de preenchimento
        chaveCBTCRM.setOpaque(false); // Torna o botão completamente transparente
        chaveCBTCRM.setBorderPainted(false); // Remove as bordas do botão
        chaveCBTCRM.setFocusPainted(false); // Remove o foco visual
        chaveCBTCRM.addActionListener(e -> {
            System.out.println("Botão chaveCBTCRM clicado!");
            mostrarImagemChaveCBTCRM(); // Exibe a imagem sobreposta
        });
        add(chaveCBTCRM); // Adiciona o botão ao painel

        // Botão chaveCBTCAM
        JButton chaveCBTCAM = new JButton();
        chaveCBTCAM.setBounds(495, 385, 75, 75); // Posição e tamanho do botão
        chaveCBTCAM.setContentAreaFilled(false); // Remove a área de preenchimento
        chaveCBTCAM.setOpaque(false); // Torna o botão completamente transparente
        chaveCBTCAM.setBorderPainted(false); // Remove as bordas do botão
        chaveCBTCAM.setFocusPainted(false); // Remove o foco visual
        chaveCBTCAM.addActionListener(e -> {
            System.out.println("Botão chaveCBTCAM clicado!");
            mostrarImagemChaveCBTCAM(); // Exibe a nova imagem
        });
        add(chaveCBTCAM); // Adiciona o botão ao painel

        // Botão chaveCBTCMCS
        JButton chaveCBTCMCS = new JButton();
        chaveCBTCMCS.setBounds(600, 370, 75, 75); // Posição e tamanho do botão
        chaveCBTCMCS.setContentAreaFilled(false); // Remove a área de preenchimento
        chaveCBTCMCS.setOpaque(false); // Torna o botão completamente transparente
        chaveCBTCMCS.setBorderPainted(false); // Remove as bordas do botão
        chaveCBTCMCS.setFocusPainted(false); // Remove o foco visual
        chaveCBTCMCS.addActionListener(e -> {
            System.out.println("Botão chaveCBTCMCS clicado!");
            mostrarImagemPrincipal(); // Retorna para a imagem principal
        });
        add(chaveCBTCMCS); // Adiciona o botão ao painel

        // Configura a imagem sobreposta para chaveCBTCRM
        imagemChaveCBTCRM = new JLabel();
        imagemChaveCBTCRM.setVisible(false); // Inicialmente invisível
        add(imagemChaveCBTCRM); // Adiciona a imagem ao painel

        // Configura a imagem sobreposta para chaveCBTCAM
        imagemChaveCBTCAM = new JLabel();
        imagemChaveCBTCAM.setVisible(false); // Inicialmente invisível
        add(imagemChaveCBTCAM); // Adiciona a imagem ao painel

        setLayout(null); // Define layout absoluto para posicionamento manual
    }

    private void mostrarImagemChaveCBTCRM() {
        int largura = getWidth();
        int altura = getHeight();

        if (largura > 0 && altura > 0) {
            ImageIcon imagemOriginal = new ImageIcon("imagens/Fotos editadas/Chave do CBTC - RM.jpg");
            Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            imagemChaveCBTCRM.setIcon(new ImageIcon(imagemRedimensionada)); // Atualiza o ícone com a imagem redimensionada
            imagemChaveCBTCRM.setBounds(0, 0, largura, altura); // Ajusta para preencher a tela inteira
        }

        imagemChaveCBTCRM.setVisible(true); // Torna a imagem visível
        imagemChaveCBTCAM.setVisible(false); // Oculta a outra imagem
        repaint(); // Atualiza o painel para exibir a imagem
    }

    private void mostrarImagemChaveCBTCAM() {
        int largura = getWidth();
        int altura = getHeight();

        if (largura > 0 && altura > 0) {
            ImageIcon imagemOriginal = new ImageIcon("imagens/Fotos editadas/Chave do CBTC - AM.jpg");
            Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            imagemChaveCBTCAM.setIcon(new ImageIcon(imagemRedimensionada)); // Atualiza o ícone com a imagem redimensionada
            imagemChaveCBTCAM.setBounds(0, 0, largura, altura); // Ajusta para preencher a tela inteira
        }

        imagemChaveCBTCAM.setVisible(true); // Torna a imagem visível
        imagemChaveCBTCRM.setVisible(false); // Oculta a outra imagem
        repaint(); // Atualiza o painel para exibir a imagem
    }

    private void mostrarImagemPrincipal() {
        int largura = getWidth();
        int altura = getHeight();

        if (largura > 0 && altura > 0) {
            ImageIcon imagemOriginal = new ImageIcon("imagens/Fotos editadas/09 - Chave do CBTC - MCS.jpg");
            Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            JLabel imagemPrincipal = new JLabel(new ImageIcon(imagemRedimensionada));
            imagemPrincipal.setBounds(0, 0, largura, altura); // Ajusta para preencher a tela inteira
            add(imagemPrincipal); // Adiciona a imagem ao painel
            imagemPrincipal.setVisible(true); // Torna a imagem principal visível
        }

        // Oculta outras imagens
        if (imagemChaveCBTCRM != null) imagemChaveCBTCRM.setVisible(false);
        if (imagemChaveCBTCAM != null) imagemChaveCBTCAM.setVisible(false);

        repaint(); // Atualiza o painel para exibir a imagem
    }
}