import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroTela1 extends JFrame {
    private JTextField campoNome;
    private JTextField campoIdade;
    private JTextField campoEmail;
    private JTextField campoTelefone;
    private JButton botaoProximaTela;

    public CadastroTela1() {
        setTitle("Tela de Cadastro 1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 2));

        JLabel labelNome = new JLabel("Nome:");
        campoNome = new JTextField();
        JLabel labelIdade = new JLabel("Idade:");
        campoIdade = new JTextField();
        JLabel labelEmail = new JLabel("Email:");
        campoEmail = new JTextField();
        JLabel labelTelefone = new JLabel("Telefone:");
        campoTelefone = new JTextField();
        botaoProximaTela = new JButton("Próxima Tela");

        painel.add(labelNome);
        painel.add(campoNome);
        painel.add(labelIdade);
        painel.add(campoIdade);
        painel.add(labelEmail);
        painel.add(campoEmail);
        painel.add(labelTelefone);
        painel.add(campoTelefone);
        painel.add(new JLabel());
        painel.add(botaoProximaTela);

        botaoProximaTela.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarDados();
                CadastroTela2 tela2 = new CadastroTela2();
                tela2.setVisible(true);
                dispose();
            }
        });

        add(painel);
    }

    private void cadastrarDados() {
        String nome = campoNome.getText();
        String idadeText = campoIdade.getText();
        String email = campoEmail.getText();
        String telefone = campoTelefone.getText();

        if (idadeText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite a idade.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int idade = Integer.parseInt(idadeText);

            Connection conexao = ConexaoBD.obterConexao();
            String sql = "INSERT INTO tabela1 (nome, idade, email, telefone) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setInt(2, idade);
            statement.setString(3, email);
            statement.setString(4, telefone);
            statement.executeUpdate();
            statement.close();
            conexao.close();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "A idade deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CadastroTela1 tela1 = new CadastroTela1();
                tela1.setVisible(true);
            }
        });
    }
}
