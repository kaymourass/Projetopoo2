import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroTela2 extends JFrame {
    private JTextField campoProduto;
    private JTextField campoQuantidade;
    private JTextField campoPreco;
    private JButton botaoTelaAnterior;

    public CadastroTela2() {
        setTitle("Tela de Cadastro 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(4, 2));

        JLabel labelProduto = new JLabel("Produto:");
        campoProduto = new JTextField();
        JLabel labelQuantidade = new JLabel("Quantidade:");
        campoQuantidade = new JTextField();
        JLabel labelPreco = new JLabel("Preço:");
        campoPreco = new JTextField();
        botaoTelaAnterior = new JButton("Voltar para Tela de Cadastro 1");

        painel.add(labelProduto);
        painel.add(campoProduto);
        painel.add(labelQuantidade);
        painel.add(campoQuantidade);
        painel.add(labelPreco);
        painel.add(campoPreco);
        painel.add(new JLabel());
        painel.add(botaoTelaAnterior);

        botaoTelaAnterior.addActionListener(e -> {
            cadastrarDados();
            CadastroTela1 tela1 = new CadastroTela1();
            tela1.setVisible(true);
            dispose();
        });

        add(painel);
    }

    private void cadastrarDados() {
        String produto = campoProduto.getText();
        String quantidadeText = campoQuantidade.getText();
        String precoText = campoPreco.getText();

        if (quantidadeText.isEmpty() || precoText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int quantidade = Integer.parseInt(quantidadeText);
            float preco = Float.parseFloat(precoText);

            Connection conexao = ConexaoBD.obterConexao();
            String sql = "INSERT INTO tabela2 (produto, quantidade, preco) VALUES (?, ?, ?)";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, produto);
            statement.setInt(2, quantidade);
            statement.setFloat(3, preco);
            statement.executeUpdate();
            statement.close();
            conexao.close();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valores inválidos para quantidade ou preço.", "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
