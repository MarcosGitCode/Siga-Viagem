import java.awt.*;
import javax.swing.*;
import java.util.List;

public class MenuAdmin extends JPanel {
    private final JLabel lblTitulo, lblNome, lblEmail, lblRegistro, lblSenha;
    private final JTextField txtNome, txtEmail, txtRegistro;
    private final JPasswordField txtSenha;
    private final JCheckBox chkMostrarSenha;
    private final JButton btnAdicionar, btnRemover, btnAlterar, btnVoltar;
    private final JTextArea txtAreaUsuarios;
    private final MetroviarioDAO dao;

    public MenuAdmin(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null);

        dao = new MetroviarioDAO();

        lblTitulo = new JLabel("Administração de Usuários", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setBounds(390, 20, 500, 40);
        add(lblTitulo);

        lblNome = new JLabel("Nome:");
        lblNome.setFont(new Font("Arial", Font.PLAIN, 18));
        lblNome.setBounds(390, 80, 100, 30);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setFont(new Font("Arial", Font.PLAIN, 18));
        txtNome.setBounds(500, 80, 300, 30);
        add(txtNome);

        lblEmail = new JLabel("E-mail:");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 18));
        lblEmail.setBounds(390, 110, 100, 30);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Arial", Font.PLAIN, 18));
        txtEmail.setBounds(500, 110, 300, 30);
        add(txtEmail);

        lblRegistro = new JLabel("Registro:");
        lblRegistro.setFont(new Font("Arial", Font.PLAIN, 18));
        lblRegistro.setBounds(390, 140, 100, 30);
        add(lblRegistro);

        txtRegistro = new JTextField();
        txtRegistro.setFont(new Font("Arial", Font.PLAIN, 18));
        txtRegistro.setBounds(500, 140, 300, 30);
        add(txtRegistro);

        lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Arial", Font.PLAIN, 18));
        lblSenha.setBounds(390, 170, 100, 30);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setFont(new Font("Arial", Font.PLAIN, 18));
        txtSenha.setBounds(500, 170, 300, 30);
        add(txtSenha);

        chkMostrarSenha = new JCheckBox("Mostrar senha");
        chkMostrarSenha.setFont(new Font("Arial", Font.PLAIN, 14));
        chkMostrarSenha.setBounds(810, 170, 130, 30);
        chkMostrarSenha.addActionListener(e -> {
            if (chkMostrarSenha.isSelected()) {
                txtSenha.setEchoChar((char) 0);
            } else {
                txtSenha.setEchoChar('•');
            }
        });
        add(chkMostrarSenha);

        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(390, 210, 130, 40);
        btnAdicionar.addActionListener(e -> adicionarUsuario());
        add(btnAdicionar);

        btnRemover = new JButton("Remover");
        btnRemover.setBounds(530, 210, 130, 40);
        btnRemover.addActionListener(e -> removerUsuario());
        add(btnRemover);

        btnAlterar = new JButton("Alterar Senha");
        btnAlterar.setBounds(670, 210, 130, 40);
        btnAlterar.addActionListener(e -> alterarSenha());
        add(btnAlterar);

        txtAreaUsuarios = new JTextArea();
        txtAreaUsuarios.setEditable(false);
        txtAreaUsuarios.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(txtAreaUsuarios);
        scrollPane.setBounds(390, 270, 500, 200);
        add(scrollPane);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(490, 490, 300, 50);
        btnVoltar.addActionListener(e -> layout.show(painelPrincipal, "Menu"));
        add(btnVoltar);

        atualizarListaUsuarios();
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

        // Validação do e-mail
        if (!email.endsWith("@metrosp.com.br")) {
            JOptionPane.showMessageDialog(this, "O e-mail deve ser do domínio @metrosp.com.br", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validação do registro (R12345-6)
        if (!registro.matches("^R\\d{5}-\\d$")) {
            JOptionPane.showMessageDialog(this, "O registro deve estar no formato R12345-6.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verifica se já existe
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
