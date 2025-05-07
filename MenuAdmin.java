import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

public class MenuAdmin extends JPanel {
    private final JLabel lblTitulo, lblUsuario, lblSenha;
    private final JTextField txtUsuario;
    private final JPasswordField txtSenha;
    private final JButton btnAdicionar, btnRemover, btnAlterar, btnVoltar;
    private final JTextArea txtAreaUsuarios;
    private final HashMap<String, String> usuarios;

    public MenuAdmin(CardLayout layout, JPanel painelPrincipal) {
        setLayout(null);

        usuarios = new HashMap<>(); // Simula um banco de dados de usuários

        lblTitulo = new JLabel("Administração de Usuários", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setBounds(390, 20, 500, 40);
        add(lblTitulo);

        lblUsuario = new JLabel("Usuário:");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 18));
        lblUsuario.setBounds(390, 80, 100, 30);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 18));
        txtUsuario.setBounds(500, 80, 300, 30);
        add(txtUsuario);

        lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Arial", Font.PLAIN, 18));
        lblSenha.setBounds(390, 130, 100, 30);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setFont(new Font("Arial", Font.PLAIN, 18));
        txtSenha.setBounds(500, 130, 300, 30);
        add(txtSenha);

        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(390, 180, 130, 40);
        btnAdicionar.addActionListener(e -> adicionarUsuario());
        add(btnAdicionar);

        btnRemover = new JButton("Remover");
        btnRemover.setBounds(530, 180, 130, 40);
        btnRemover.addActionListener(e -> removerUsuario());
        add(btnRemover);

        btnAlterar = new JButton("Alterar Senha");
        btnAlterar.setBounds(670, 180, 130, 40);
        btnAlterar.addActionListener(e -> alterarSenha());
        add(btnAlterar);

        txtAreaUsuarios = new JTextArea();
        txtAreaUsuarios.setEditable(false);
        txtAreaUsuarios.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(txtAreaUsuarios);
        scrollPane.setBounds(390, 240, 500, 200);
        add(scrollPane);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(490, 460, 300, 50);
        btnVoltar.addActionListener(e -> layout.show(painelPrincipal, "Menu"));
        add(btnVoltar);

        atualizarListaUsuarios();
    }

    private void adicionarUsuario() {
        String usuario = txtUsuario.getText().trim();
        String senha = new String(txtSenha.getPassword()).trim();

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Usuário e senha não podem estar vazios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (usuarios.containsKey(usuario)) {
            JOptionPane.showMessageDialog(this, "Usuário já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        usuarios.put(usuario, senha);
        JOptionPane.showMessageDialog(this, "Usuário adicionado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        atualizarListaUsuarios();
    }

    private void removerUsuario() {
        String usuario = txtUsuario.getText().trim();

        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o usuário a ser removido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!usuarios.containsKey(usuario)) {
            JOptionPane.showMessageDialog(this, "Usuário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        usuarios.remove(usuario);
        JOptionPane.showMessageDialog(this, "Usuário removido com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        atualizarListaUsuarios();
    }

    private void alterarSenha() {
        String usuario = txtUsuario.getText().trim();
        String senha = new String(txtSenha.getPassword()).trim();

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Usuário e nova senha não podem estar vazios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!usuarios.containsKey(usuario)) {
            JOptionPane.showMessageDialog(this, "Usuário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        usuarios.put(usuario, senha);
        JOptionPane.showMessageDialog(this, "Senha alterada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        atualizarListaUsuarios();
    }

    private void atualizarListaUsuarios() {
        StringBuilder sb = new StringBuilder("Usuários cadastrados:\n");
        usuarios.forEach((usuario, senha) -> sb.append("- ").append(usuario).append("\n"));
        txtAreaUsuarios.setText(sb.toString());
    }
}
