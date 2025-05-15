import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JogoDDU extends BasePainelComBotao {

    private JLabel imagemLabel;
    private String caminhoImagemAtual = "imagens/Fotos editadas/03 - DDU com fachada aberta.jpg";

    public JogoDDU(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/Fotos editadas/03 - DDU com fachada aberta.jpg", layout, painelPrincipal);
        setLayout(null); // Layout absoluto

        // Criação do JLabel para exibir a imagem
        imagemLabel = new JLabel();
        add(imagemLabel);
        
        // Criação dos botões com novos textos
        JButton btnPrepara = criarBotao("Prepara");
        JButton btnFe = criarBotao("Fe");
        JButton btnInfopass = criarBotao("Infopass");

        // Configuração das posições dos botões
        btnPrepara.setBounds(340, 590, 100, 40);
        btnFe.setBounds(497, 595, 100, 40);
        btnInfopass.setBounds(545, 601, 100, 40);
        add(btnPrepara);
        add(btnFe);
        add(btnInfopass);

        // Ações dos botões com caminhos de imagem
        btnPrepara.addActionListener(e -> alterarImagem("imagens/Fotos editadas/03 - DDU com fachada aberta.jpg"));
        btnFe.addActionListener(e -> alterarImagem("imagens/Fotos editadas/DDU - tela FE- lista de emergência.jpg"));
        btnInfopass.addActionListener(e -> alterarImagem("imagens/Fotos editadas/DDU - tela INFOPASS.jpg"));

        // Listener para redimensionamento: atualiza os limites e re-renderiza a imagem
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                imagemLabel.setBounds(0, 0, getWidth(), getHeight());
                atualizarImagemAtual();
            }
        });
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

    // Método para alterar a imagem utilizando o caminho informado
    private void alterarImagem(String caminhoImagem) {
        System.out.println("Tentando carregar: " + caminhoImagem);
        this.caminhoImagemAtual = caminhoImagem;
        atualizarImagemAtual();
    }

    // Atualiza a imagem no JLabel, redimensionando-a conforme as dimensões atuais do painel
    private void atualizarImagemAtual() {
        ImageIcon imagem = new ImageIcon(caminhoImagemAtual);

        if (imagem.getIconWidth() == -1) {
            System.out.println("Erro: imagem não encontrada!");
            imagemLabel.setIcon(null);
            imagemLabel.setText("Imagem não encontrada");
            return;
        }

        // Redimensiona a imagem para ocupar toda a área do painel
        Image imagemRedimensionada = imagem.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        imagemLabel.setIcon(new ImageIcon(imagemRedimensionada));
        imagemLabel.setText(null); // Remove o texto, se houver
    }
}
