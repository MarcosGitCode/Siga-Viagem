import java.awt.*;
import java.util.Set;

public class InventarioUI {
    public static void desenhar(Graphics2D g2d, int larguraPainel) {
        Set<String> itens = Inventario.getItens();
        int larguraBox = 300 + (itens.size() * 100);
        int x = larguraPainel - larguraBox - 20;
        int y = 10;

        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRoundRect(x, y, larguraBox, 60, 20, 20);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        g2d.drawString("Inventário:", x + 15, y + 38);

        int itemX = x + 120;
        for (String item : itens) {
            g2d.drawString(item, itemX, y + 38);
            itemX += 100;
        }
    }
}
