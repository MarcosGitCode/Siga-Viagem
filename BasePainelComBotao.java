import java.awt.*;
import javax.swing.*;

public abstract class BasePainelComBotao extends PainelComImagem {

    public BasePainelComBotao(String caminhoImagem, CardLayout layout, JPanel painelPrincipal) {
        super(caminhoImagem); // Define a imagem de fundo
        setLayout(null); // Layout absoluto para posicionar o botão universal
    }
}