import java.awt.*;
import javax.swing.*;

public abstract class BasePainelComBotao extends PainelComImagem {

    public BasePainelComBotao(String caminhoImagem, CardLayout layout, JPanel painelPrincipal) {
        super(caminhoImagem); // seta a img de fundo
        setLayout(null); // usa layout absoluto p/ facilitar pos dos bts
    }
}