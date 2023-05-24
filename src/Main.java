import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaLogin TelaLogin = new TelaLogin();
            TelaLogin.setVisible(true);
        });
    }
}
