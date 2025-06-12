import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MenuPlacar extends PainelComImagem {
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private String registroUsuario;
    private boolean isAdmin;

    public MenuPlacar(CardLayout layout, JPanel painelPrincipal) {
        super("imagens/metro_blur.jpg");
        setLayout(null);
        criarTelaLogin(layout, painelPrincipal);
    }

    private void criarTelaLogin(CardLayout layout, JPanel painelPrincipal) {
        JLabel lblRegistro = new JLabel("Registro:");
        lblRegistro.setFont(new Font("Arial", Font.BOLD, 24));
        lblRegistro.setForeground(Color.WHITE);
        add(lblRegistro);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Arial", Font.BOLD, 24));
        lblSenha.setForeground(Color.WHITE);
        add(lblSenha);

        JTextField txtRegistro = new JTextField();
        txtRegistro.setFont(new Font("Arial", Font.PLAIN, 18));
        add(txtRegistro);

        JPasswordField txtSenha = new JPasswordField();
        txtSenha.setFont(new Font("Arial", Font.PLAIN, 18));
        add(txtSenha);

        JCheckBox chkMostrarSenha = new JCheckBox("Mostrar senha");
        chkMostrarSenha.setFont(new Font("Arial", Font.PLAIN, 16));
        chkMostrarSenha.setForeground(Color.WHITE);
        chkMostrarSenha.setOpaque(false);
        chkMostrarSenha.addActionListener(e -> {
            if (chkMostrarSenha.isSelected()) {
                txtSenha.setEchoChar((char) 0);
            } else {
                txtSenha.setEchoChar('•');
            }
        });
        add(chkMostrarSenha);

        JButton btnEntrar = criarBotao("Visualizar Placar");
        btnEntrar.addActionListener(e -> {
            String registro = txtRegistro.getText().trim();
            String senha = new String(txtSenha.getPassword()).trim();

            MetroviarioDAO dao = new MetroviarioDAO();

            isAdmin = dao.verificarAdmin(registro, senha);

            if (isAdmin) {
                registroUsuario = registro;
                mostrarPlacar(layout, painelPrincipal);
            }
            else if (dao.verificarMetroviario(registro, senha)) {
                isAdmin = false;
                registroUsuario = registro;
                mostrarPlacar(layout, painelPrincipal);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Registro ou senha inválidos!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        add(btnEntrar);

        JButton btnVoltar = criarBotao("Voltar");
        btnVoltar.addActionListener(e -> layout.show(painelPrincipal, "Menu"));
        add(btnVoltar);

        int w = 1280;
        int h = 853;
        int compW = 500;
        int compH = 40;
        int x = (w - compW) / 2;

        lblRegistro.setBounds(x, h / 2 - 150, compW, compH);
        txtRegistro.setBounds(x, h / 2 - 100, compW, compH);
        lblSenha.setBounds(x, h / 2 - 40, compW, compH);
        txtSenha.setBounds(x, h / 2 + 10, compW, compH);
        chkMostrarSenha.setBounds(x, h / 2 + 55, 200, 30);
        btnEntrar.setBounds(x, h / 2 + 100, 220, 60);
        btnVoltar.setBounds(x + 280, h / 2 + 100, 220, 60);
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setBackground(new Color(128, 128, 128, 128));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setContentAreaFilled(false);
        botao.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
        return botao;
    }

    private void mostrarPlacar(CardLayout layout, JPanel painelPrincipal) {
        removeAll();

        String[] colunas = { "Posição", "Nome", "Registro", "Pontuação" };
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela = new JTable(modeloTabela);
        configurarEstiloTabela();

        MetroviarioDAO dao = new MetroviarioDAO();
        List<Metroviario> registros;

        if (isAdmin) {
            registros = dao.listarRanking();
        } else {
            registros = dao.listarPontuacoesJogador(registroUsuario);
        }

        int posicao = 1;
        for (Metroviario m : registros) {
            modeloTabela.addRow(new Object[] {
                    posicao++,
                    m.getNome(),
                    m.getRegistro(),
                    m.getPontuacaoTotal()
            });
        }

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBounds(240, 150, 800, 500);
        add(scrollPane);

        JButton btnVoltar = criarBotao("Voltar ao Menu");
        btnVoltar.setBounds(540, 700, 200, 50);
        btnVoltar.addActionListener(e -> layout.show(painelPrincipal, "Menu"));
        add(btnVoltar);

        revalidate();
        repaint();
    }

    private void configurarEstiloTabela() {
        tabela.setFont(new Font("Arial", Font.PLAIN, 16));
        tabela.setRowHeight(30);
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tabela.setOpaque(false);
        tabela.setShowGrid(true);
        tabela.setGridColor(Color.WHITE);
        tabela.setIntercellSpacing(new Dimension(1, 1));
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.getTableHeader().setResizingAllowed(false);
        int[] larguras = { 100, 300, 200, 200 };
        for (int i = 0; i < larguras.length; i++) {
            tabela.getColumnModel().getColumn(i).setPreferredWidth(larguras[i]);
            tabela.getColumnModel().getColumn(i).setMinWidth(larguras[i]);
            tabela.getColumnModel().getColumn(i).setMaxWidth(larguras[i]);
        }
    }
}
