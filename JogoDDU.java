import java.awt.*;
import javax.swing.*;

public class JogoDDU extends BasePainelComBotao {

    private JLabel imagemLabel;

    public JogoDDU(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/03 - DDU com fachada aberta.jpg", layout, painelPrincipal);
        setLayout(null); // Layout absoluto

        // Criação dos botões com novos textos
        JButton btnPrepara = criarBotao("Prepara");
        JButton btnFe = criarBotao("Fe");
        JButton btnInfopass = criarBotao("Infopass");
        JButton btnManut = criarBotao("Manut");

        // Configuração das posições
        btnPrepara.setBounds(340, 590, 50, 50);
        btnFe.setBounds(497,595, 50, 50);
        btnInfopass.setBounds(545, 601, 50, 50);
        //btnManut.setBounds(650, 600, 50, 50);

        // Adiciona os botões ao painel
        add(btnPrepara);
        add(btnFe);
        add(btnInfopass);
        add(btnManut);

        // Ações dos botões com caminhos de imagem
        btnPrepara.addActionListener(e -> alterarImagem("imagens/Fotos editadas/03 - DDU com fachada aberta.jpg"));
        btnFe.addActionListener(e -> alterarImagem("imagens/Fotos editadas/DDU - tela FE- lista de emergência.jpg"));
        btnInfopass.addActionListener(e -> alterarImagem("imagens/Fotos editadas/DDU - tela INFOPASS.jpg"));
        btnManut.addActionListener(e -> alterarImagem("imagens/Fotos editadas/manut.jpg"));
    }

    // Criação padrão de botões estilizados
    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setBackground(new Color(70, 130, 180)); // Azul claro
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return botao;
    }

    // Método para alterar imagem
    private void alterarImagem(String caminhoImagem) {
        System.out.println("Tentando carregar: " + caminhoImagem);
        ImageIcon imagem = new ImageIcon(caminhoImagem);

        if (imagem.getIconWidth() == -1) {
            System.out.println("Erro: imagem não encontrada!");
            imagemLabel.setIcon(null);
            imagemLabel.setText("Imagem não encontrada");
            return;
        }

        Image imagemRedimensionada = imagem.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        imagemLabel.setIcon(new ImageIcon(imagemRedimensionada));
        imagemLabel.setText(null); // Remove texto se houver
    }
}
