package View;

import Control.Controle;
import Model.Jogador;
import Model.Time;
import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.split;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author cefas
 */
public class JanelaTime extends JFrame implements ActionListener {

    int i;
    ArrayList<Integer> vp = new ArrayList<>();
    Controle c;
    Time t = new Time();
    JTextField nome, camisa;
    JComboBox jogadores;
    JLabel lnome, ljogadores, lcamisa, lid, lid2;
    JPanel p, p1, p2;
    JButton addJogador, cadastrar, limpar;

    public void MontaTime(Controle x) {
        c = x;
        i = 0;
        p = new JPanel();
        p1 = new JPanel(new GridLayout(4, 2));
        p2 = new JPanel(new GridLayout(1, 3));
        nome = new JTextField(15);
        camisa = new JTextField(2);
        camisa.addActionListener(this);
        lid = new JLabel("ID: ");
        lid2 = new JLabel("" + c.getIdt());
        lnome = new JLabel("Nome do time: ");
        lcamisa = new JLabel("Número da camisa: ");
        ljogadores = new JLabel("Jogador: ");
        jogadores = new JComboBox();
        PreencheCombo();

        addJogador = new JButton("Adicionar jogador");
        addJogador.addActionListener(this);
        addJogador.setEnabled(false);
        cadastrar = new JButton("Cadastrar time");
        cadastrar.addActionListener(this);
        limpar = new JButton("Limpar");
        limpar.addActionListener(this);

        p1.add(lid);
        p1.add(lid2);
        p1.add(lnome);
        p1.add(nome);
        p1.add(ljogadores);
        p1.add(jogadores);
        p1.add(lcamisa);
        p1.add(camisa);

        p2.add(addJogador);
        p2.add(cadastrar);
        p2.add(limpar);

        p.add(p1);
        p.add(p2);
        add(p);

        setSize(400, 200);
        setVisible(true);
        setTitle("Cadastro de Time");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private void PreencheCombo() {
        for (int i = 0; i < c.getIdj() - 1; i++) {  // preenche o combo limitando o for com o último ID (último jogador)
            if (c.RetornaJogador(i).getTime() == null) {
                jogadores.addItem(c.RetornaJogador(i).getId() + " " + c.RetornaJogador(i).getNome());//lembrar que na lista é esse id -1;
            }
        }
    }

    private void Limpar() {
        nome.setText("");
        camisa.setText(null);
        lid2.setText("" + c.getIdt());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addJogador){   // adicionando jogador ao time
            addJogador.setEnabled(false);
            nome.setEditable(false);
            if(t.getJogadores().size()==5){
                    JOptionPane.showMessageDialog(null, "Time já completo!");
                    return;
               }
            if(jogadores.getSelectedItem()==null){
                    JOptionPane.showMessageDialog(null, "Selecione um jogador ou cadastre mais jogadores.");
                    return;
            }
            if (t.VerificaCamisa(Integer.parseInt(camisa.getText())) == true){ // verifica se o número da camisa não está em uso
                String[] v;
                v = ((String) jogadores.getSelectedItem()).split(" ");
                vp.add(Integer.parseInt(v[0]));
                c.RetornaJogador(vp.get(vp.size() - 1) - 1).setNcamisa(Integer.parseInt(camisa.getText()));//adicionando camisa ao vetor
                t.AddJogador(c.RetornaJogador(vp.get(vp.size() - 1) - 1));
                camisa.setText("");
                jogadores.removeItem(jogadores.getSelectedItem());// remove o jogador já escolhido, usando o index do item selecionado
            }else{
                JOptionPane.showMessageDialog(null, "O número de camisa informado ja está em uso!");
            }

        } else if (ae.getSource() == cadastrar) { // cadastrando time
            nome.setEditable(true);
            if (t.getJogadores().size() < 5) {
                JOptionPane.showMessageDialog(null, "Número de jogadores insuficiente (min. 5).");
            } else {
                t.setNome(nome.getText());
                c.AddTime(t);
                for (int i = 0; i < 5; i++) {
                    c.InserirTime(vp.get(i)-1, t);// passando id do jogador e o time dele para setar seu time na lista
                }
                vp.clear(); // limpando a lista que armazena o id dos jogadores que farão parte do time
                Limpar();
                t = new Time();
            }
        } else if (ae.getSource() == limpar) {
            Limpar();
        } else if(ae.getSource()==camisa){
            addJogador.setEnabled(true);
        }
    }

}
