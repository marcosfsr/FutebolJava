package View;

import Control.Controle;
import Model.Jogador;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author cefas
 */
public class JanelaJogador extends JFrame implements ActionListener {

    JPanel p, p1, p2;
    JLabel lnome, lidade, lid, lid2, laltura, lpeso;
    JTextField nome, idade, altura, peso;
    JButton cadastrar, limpar;
    Controle c;
    int iX = 0;

    public void MontaJogador(Controle x) {
        c = x;
        p = new JPanel();
        p1 = new JPanel(new GridLayout(5, 2));
        p2 = new JPanel(new GridLayout(1, 2));

        cadastrar = new JButton("Cadastrar");
        cadastrar.addActionListener(this);
        limpar = new JButton("Limpar");
        limpar.addActionListener(this);

        lnome = new JLabel("Nome: ");
        lidade = new JLabel("Idade: ");
        laltura = new JLabel("Altura: ");
        lpeso = new JLabel("Peso: ");
        lid = new JLabel("Id: ");
        lid2 = new JLabel("" + c.getIdj());

        nome = new JTextField(15);
        idade = new JTextField(15);
        altura = new JTextField(15);
        peso = new JTextField(15);

        p1.add(lid);
        p1.add(lid2);
        p1.add(lnome);
        p1.add(nome);
        p1.add(lidade);
        p1.add(idade);
        p1.add(laltura);
        p1.add(altura);
        p1.add(lpeso);
        p1.add(peso);

        p2.add(cadastrar);
        p2.add(limpar);

        p.add(p1);
        p.add(p2);

        add(p);

        setSize(400, 200);
        setVisible(true);
        setTitle("Cadastro de Jogador");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private void Limpar() {
        nome.setText("");
        idade.setText("");
        altura.setText("");
        peso.setText("");
        lid2.setText("" + c.getIdj());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == cadastrar) {           
             Jogador j = new Jogador();
             j.setAltura(Float.parseFloat(altura.getText()));
             j.setIdade(Integer.parseInt(idade.getText()));
             j.setNome(nome.getText());
             j.setPeso(Float.parseFloat(peso.getText()));
             c.AddJogador(j);
             Limpar();
        } else if (ae.getSource() == limpar) {
            Limpar();
        }
    }

}
