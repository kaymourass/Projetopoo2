import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JButton botaoLogin;
    private JButton botaoIrCadastro;

    public TelaLogin() {
        setTitle("Tela de Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(4, 2));

        JLabel labelUsuario = new JLabel("Usuário:");
        campoUsuario = new JTextField();
        JLabel labelSenha = new JLabel("Senha:");
        campoSenha = new JPasswordField();
        botaoLogin = new JButton("Login");
        botaoIrCadastro = new JButton("Ir para Tela de Cadastro 1");

        painel.add(labelUsuario);
        painel.add(campoUsuario);
        painel.add(labelSenha);
        painel.add(campoSenha);
        painel.add(new JLabel());
        painel.add(botaoLogin);
        painel.add(new JLabel());
        painel.add(botaoIrCadastro);

        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String senha = new String(campoSenha.getPassword());

                if (validarLogin(usuario, senha)) {
                    JOptionPane.showMessageDialog(TelaLogin.this, "Login bem-sucedido!");
                    
                } else {
                    JOptionPane.showMessageDialog(TelaLogin.this, "Usuário ou senha inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botaoIrCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CadastroTela1 telaCadastro1 = new CadastroTela1();
                telaCadastro1.setVisible(true);
                dispose();
            }
        });

        add(painel);
    }

    private boolean validarLogin(String usuario, String senha) {
       
        return usuario.equals("admin") && senha.equals("123456");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TelaLogin telaLogin = new TelaLogin();
                telaLogin.setVisible(true);
            }
        });
    }
}
