import javax.swing.*;

public class TremPes extends JPanel {

    public TremPes() {
        setLayout(null); // Define o layout absoluto para posicionar componentes

        // Carrega a imagem
        ImageIcon imagem = carregarImagem("imagens/Fotos editadas/16 - Dispositivos de emergência.jpg");

        // Adiciona a imagem ao painel
        JLabel labelImagem = new JLabel(imagem);
        labelImagem.setBounds(0, 0, 1280, 856); // Define o tamanho da imagem para cobrir toda a tela
        add(labelImagem);
    }

    private ImageIcon carregarImagem(String caminhoImagem) {
        // Carrega a imagem do caminho especificado
        return new ImageIcon(caminhoImagem);
    }
}
