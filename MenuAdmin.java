import java.awt.*;
import java.util.List;
import javax.swing.*;

public class MenuAdmin extends JPanel {
    private final JLabel lblTitulo, lblNome, lblEmail, lblRegistro, lblSenha;
    private final JTextField txtNome, txtEmail, txtRegistro;
    private final JPasswordField txtSenha;
    private final JCheckBox chkMostrarSenha;
    private final JButton btnAdicionar, btnRemover, btnAlterar, btnVoltar, btnJogar;
    private final JTextArea txtAreaUsuarios;
    private final MetroviarioDAO dao;
    private final Image imagemFundo;
    private boolean mostrandoAdministradores = false;
    private final JComboBox<String> comboLista;

    public MenuAdmin(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null);

        imagemFundo = new ImageIcon("imagens/imagem admin.png").getImage();

        dao = new MetroviarioDAO();

        lblTitulo = new JLabel("Administração de Usuários", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setBounds(390, 20, 500, 40);
        lblTitulo.setForeground(new Color(0, 0, 0, 0));
        add(lblTitulo);

        lblNome = new JLabel("Nome:");
        lblNome.setFont(new Font("Arial", Font.PLAIN, 18));
        lblNome.setBounds(390, 80, 100, 30);
        lblNome.setForeground(new Color(0, 0, 0, 0));
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setFont(new Font("Arial", Font.PLAIN, 18));
        txtNome.setBounds(490, 80, 400, 30);
        txtNome.setForeground(Color.BLACK);
        txtNome.setBackground(Color.WHITE);
        add(txtNome);

        lblEmail = new JLabel("E-mail:");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 18));
        lblEmail.setBounds(390, 110, 100, 30);
        lblEmail.setForeground(new Color(0, 0, 0, 0));
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Arial", Font.PLAIN, 18));
        txtEmail.setBounds(490, 110, 400, 30);
        txtEmail.setForeground(Color.BLACK);
        txtEmail.setBackground(Color.WHITE);
        add(txtEmail);

        lblRegistro = new JLabel("Registro:");
        lblRegistro.setFont(new Font("Arial", Font.PLAIN, 18));
        lblRegistro.setBounds(390, 140, 100, 30);
        lblRegistro.setForeground(new Color(0, 0, 0, 0));
        add(lblRegistro);

        txtRegistro = new JTextField();
        txtRegistro.setFont(new Font("Arial", Font.PLAIN, 18));
        txtRegistro.setBounds(490, 140, 400, 30);
        txtRegistro.setForeground(Color.BLACK);
        txtRegistro.setBackground(Color.WHITE);
        add(txtRegistro);

        lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Arial", Font.PLAIN, 18));
        lblSenha.setBounds(390, 170, 100, 30);
        lblSenha.setForeground(new Color(0, 0, 0, 0));
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setFont(new Font("Arial", Font.PLAIN, 18));
        txtSenha.setBounds(490, 170, 400, 30);
        txtSenha.setForeground(Color.BLACK);
        txtSenha.setBackground(Color.WHITE);
        add(txtSenha);

        chkMostrarSenha = new JCheckBox("Mostrar senha");
        chkMostrarSenha.setFont(new Font("Arial", Font.PLAIN, 14));
        chkMostrarSenha.setBounds(900, 170, 150, 30); // Volta para posição original (exemplo: ao lado do campo senha)
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

        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(390, 210, 160, 40);
        btnAdicionar.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String email = txtEmail.getText().trim();
            String registro = txtRegistro.getText().trim();
            String senha = new String(txtSenha.getPassword()).trim();

            if (nome.isEmpty() || email.isEmpty() || registro.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int resposta = JOptionPane.showConfirmDialog(
                this,
                "O usuário cadastrado é um administrador?",
                "Tipo de usuário",
                JOptionPane.YES_NO_OPTION
            );

            if (resposta == JOptionPane.YES_OPTION) {
                try (java.sql.Connection conn = Conexao.conectar();
                     java.sql.PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO administradores (nome, email, registro, senha) VALUES (?, ?, ?, ?)")) {
                    stmt.setString(1, nome);
                    stmt.setString(2, email);
                    stmt.setString(3, registro);
                    stmt.setString(4, senha);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Administrador cadastrado com sucesso!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Erro ao cadastrar administrador.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                Metroviario novo = new Metroviario(nome, email, registro, senha, 0);
                MetroviarioDAO dao = new MetroviarioDAO();
                dao.inserir(novo);
                JOptionPane.showMessageDialog(this, "Metroviário cadastrado com sucesso!");
            }

            atualizarListaUsuarios();
        });
        add(btnAdicionar);

        btnRemover = new JButton("Remover");
        btnRemover.setBounds(560, 210, 160, 40);
        btnRemover.addActionListener(e -> removerUsuario());
        add(btnRemover);

        btnAlterar = new JButton("Alterar Senha");
        btnAlterar.setBounds(730, 210, 160, 40);
        btnAlterar.addActionListener(e -> alterarSenha());
        add(btnAlterar);

        txtAreaUsuarios = new JTextArea();
        txtAreaUsuarios.setEditable(false);
        txtAreaUsuarios.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(txtAreaUsuarios);
        scrollPane.setBounds(390, 270, 500, 200);
        add(scrollPane);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Arial", Font.PLAIN, 18));
        btnVoltar.setBounds(390, 490, 245, 50);
        btnVoltar.addActionListener(e -> layout.show(painelPrincipal, "Menu"));
        add(btnVoltar);

        btnJogar = new JButton("Jogar");
        btnJogar.setFont(new Font("Arial", Font.PLAIN, 18));
        btnJogar.setBounds(645, 490, 245, 50);
        btnJogar.addActionListener(e -> layout.show(painelPrincipal, "Jogo"));
        add(btnJogar);

        comboLista = new JComboBox<>(new String[]{"Metroviários", "Administradores"});
        comboLista.setFont(new Font("Arial", Font.PLAIN, 16));
        comboLista.setBounds(900, 270, 200, 40); // Volta para largura menor e posição original
        comboLista.addActionListener(e -> {
            mostrandoAdministradores = comboLista.getSelectedIndex() == 1;
            atualizarListaUsuarios();
        });
        add(comboLista);

        atualizarListaUsuarios();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagemFundo != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        }

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setFont(lblTitulo.getFont());
        // Centraliza o título
        FontMetrics fm = g2.getFontMetrics(lblTitulo.getFont());
        int tituloWidth = fm.stringWidth(lblTitulo.getText());
        int xTitulo = (getWidth() - tituloWidth) / 2;
        int yTitulo = lblTitulo.getY() + lblTitulo.getHeight() - 10;
        drawOutlinedText(g2, lblTitulo.getText(), xTitulo, yTitulo, Color.WHITE, Color.BLACK, 3);

        g2.setFont(lblNome.getFont());
        drawOutlinedText(g2, lblNome.getText(), lblNome.getX(), lblNome.getY() + lblNome.getHeight() - 8, Color.WHITE, Color.BLACK, 2);
        drawOutlinedText(g2, lblEmail.getText(), lblEmail.getX(), lblEmail.getY() + lblEmail.getHeight() - 8, Color.WHITE, Color.BLACK, 2);
        drawOutlinedText(g2, lblRegistro.getText(), lblRegistro.getX(), lblRegistro.getY() + lblRegistro.getHeight() - 8, Color.WHITE, Color.BLACK, 2);
        drawOutlinedText(g2, lblSenha.getText(), lblSenha.getX(), lblSenha.getY() + lblSenha.getHeight() - 8, Color.WHITE, Color.BLACK, 2);
        g2.dispose();
    }

    private void drawOutlinedText(Graphics2D g2, String text, int x, int y, Color fill, Color outline, int thickness) {
        g2.setColor(outline);
        for (int dx = -thickness; dx <= thickness; dx++) {
            for (int dy = -thickness; dy <= thickness; dy++) {
                if (dx != 0 || dy != 0) {
                    g2.drawString(text, x + dx, y + dy);
                }
            }
        }
        g2.setColor(fill);
        g2.drawString(text, x, y);
    }

    private void adicionarUsuario() {
        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();
        String registro = txtRegistro.getText().trim();
        String senha = new String(txtSenha.getPassword()).trim();

        if (nome.isEmpty() || email.isEmpty() || registro.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!email.endsWith("@metrosp.com.br")) {
            JOptionPane.showMessageDialog(this, "O e-mail deve ser do domínio @metrosp.com.br", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!registro.matches("^R\\d{5}-\\d$")) {
            JOptionPane.showMessageDialog(this, "O registro deve estar no formato R12345-6.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Metroviario> lista = dao.listarTodos();
        for (Metroviario m : lista) {
            if (m.getNome().equals(nome)) {
                JOptionPane.showMessageDialog(this, "Nome já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (m.getEmail().equals(email)) {
                JOptionPane.showMessageDialog(this, "E-mail já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (m.getRegistro().equals(registro)) {
                JOptionPane.showMessageDialog(this, "Registro já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        try {
            Metroviario novo = new Metroviario(nome, email, senha, registro, 0);
            dao.inserir(novo);
            JOptionPane.showMessageDialog(this, "Usuário adicionado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            atualizarListaUsuarios();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao adicionar usuário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerUsuario() {
        String nome = txtNome.getText().trim();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o nome a ser removido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Metroviario> lista = dao.listarTodos();
        Metroviario encontrado = null;
        for (Metroviario m : lista) {
            if (m.getNome().equals(nome)) {
                encontrado = m;
                break;
            }
        }

        if (encontrado == null) {
            JOptionPane.showMessageDialog(this, "Nome não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        dao.deletar(encontrado.getId());
        JOptionPane.showMessageDialog(this, "Usuário removido com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        atualizarListaUsuarios();
    }

    private void alterarSenha() {
        String nome = txtNome.getText().trim();
        String senha = new String(txtSenha.getPassword()).trim();

        if (nome.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome e nova senha não podem estar vazios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Metroviario> lista = dao.listarTodos();
        Metroviario encontrado = null;
        for (Metroviario m : lista) {
            if (m.getNome().equals(nome)) {
                encontrado = m;
                break;
            }
        }

        if (encontrado == null) {
            JOptionPane.showMessageDialog(this, "Nome não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        encontrado.setSenha(senha);
        dao.atualizar(encontrado);
        JOptionPane.showMessageDialog(this, "Senha alterada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        atualizarListaUsuarios();
    }

    private void atualizarListaUsuarios() {
        if (mostrandoAdministradores) {
            StringBuilder sb = new StringBuilder("Administradores cadastrados:\n");
            try (java.sql.Connection conn = Conexao.conectar();
                 java.sql.PreparedStatement stmt = conn.prepareStatement("SELECT nome, email, registro FROM administradores");
                 java.sql.ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    sb.append("- ")
                      .append(rs.getString("nome"))
                      .append(" | ").append(rs.getString("email"))
                      .append(" | ").append(rs.getString("registro"))
                      .append("\n");
                }
            } catch (Exception ex) {
                sb.append("Erro ao buscar administradores.");
            }
            txtAreaUsuarios.setText(sb.toString());
        } else {
            List<Metroviario> lista = dao.listarTodos();
            StringBuilder sb = new StringBuilder("Usuários cadastrados:\n");
            for (Metroviario m : lista) {
                sb.append("- ").append(m.getNome())
                  .append(" | ").append(m.getEmail())
                  .append(" | ").append(m.getRegistro())
                  .append("\n");
            }
            txtAreaUsuarios.setText(sb.toString());
        }
    }
}
