package View;

import Control.Controle;
import Model.Jogador;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Marcos
 */
public class JanelaSaldoGols extends JFrame implements ItemListener {

    int qual;
    JTextArea gols;
    JPanel p1, p2, pg;
    JComboBox box;
    JLabel escolha;
    Controle c;

    public JanelaSaldoGols(int x){
        qual = x;
    }
    
    

    public void inicializar() {
        escolha = new JLabel();
        gols = new JTextArea(15, 20);
        p1 = new JPanel(new GridLayout(1,2));
        p2 = new JPanel();
        pg = new JPanel(new BorderLayout());
        box = new JComboBox();
        box.addItemListener(this);

    }

    public void BuscaSaldoGols(Controle c) {
        inicializar();
        this.c = c;
        if (qual == 1) {
            escolha.setText("Escolha um jogador: ");
            for (int i = 0; i < c.getIdj() - 1; i++) {
                box.addItem(c.RetornaJogador(i).getId() + " " + c.RetornaJogador(i).getNome());
            }
        } else {
            escolha.setText("Escolha um time: ");
            for (int i = 0; i < c.getIdt() - 1; i++) {
                box.addItem(c.RetornaTime(i).getId() + " " + c.RetornaTime(i).getNome());
            }
        }
        p1.add(escolha);
        p1.add(box);
        p2.add(gols);
        pg.add(p1, BorderLayout.PAGE_START);
        pg.add(p2);
        add(pg);
        setTitle("Saldo de Gols");
        setVisible(true);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (qual == 1) { // caso seja busca por jogador [ 1 ]
            if (box != null) {
                String v[];
                v = ((String) box.getSelectedItem()).split(" ");
                gols.setText(c.BuscaGolsJ((Integer.parseInt(v[0]) - 1)));
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um jogador.");
            }
        } else { // se a busca não for por jogador [ 1 ] será por time [ 2 ]
            if (box != null) {
                String v[];
                v = ((String) box.getSelectedItem()).split(" ");
                gols.setText(c.BuscaGolsT((Integer.parseInt(v[0]) - 1)));
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um time.");
            }
        }
    }

}
