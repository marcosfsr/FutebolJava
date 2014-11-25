package View;

import Control.Controle;
import Model.Partida;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Micro
 */
public class JanelaPartida extends JFrame implements ActionListener, ItemListener {

    Controle c;
    Partida p;
    JLabel placar1, placar2, placar, time, jogador;
    JComboBox time1, time2, jogadort1, jogadort2;
    JButton golt1, golt2, cadastrar, limpar;
    JPanel pg, p1, p2, p3;
    ArrayList<Integer> gols;
    int i;

    public void MontaPartida(Controle x) {
        p = new Partida();
        i = 0;
        gols = new ArrayList();
        c = x;
        pg = new JPanel(new BorderLayout());
        p1 = new JPanel(new GridLayout(1, 3));
        p2 = new JPanel(new GridLayout(2, 3));
        p3 = new JPanel(new GridLayout(2, 2));

        golt1 = new JButton("Gol");
        golt1.addActionListener(this);
        golt2 = new JButton("Gol");
        golt2.addActionListener(this);
        cadastrar = new JButton("Finalizar partida");
        cadastrar.addActionListener(this);
        limpar = new JButton("Zerar placar");
        limpar.addActionListener(this);

        placar1 = new JLabel("0");
        placar2 = new JLabel("0");
        placar = new JLabel("X");
        time = new JLabel("Times: ");
        jogador = new JLabel("Jogadores: ");

        time1 = new JComboBox();
        time2 = new JComboBox();

        PreencheTime();

        time1.addItemListener(this);
        time2.addItemListener(this);
        jogadort1 = new JComboBox();
        jogadort2 = new JComboBox();

            
        p1.add(placar1);
        p1.add(placar);
        p1.add(placar2);

        p2.add(time);
        p2.add(time1);
        p2.add(time2);
        p2.add(jogador);
        p2.add(jogadort1);
        p2.add(jogadort2);

        p3.add(golt1);
        p3.add(golt2);
        p3.add(cadastrar);
        p3.add(limpar);

        pg.add(p1, BorderLayout.PAGE_START);
        pg.add(p2, BorderLayout.CENTER);
        pg.add(p3, BorderLayout.PAGE_END);

        add(pg);

        setTitle("Cadastro Partida");
        setSize(400, 160);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private void PreencheTime() {
        for (int i = 0; i < c.getIdt() - 1; i++) {
            time1.addItem(c.RetornaTime(i).getId() + " " + c.RetornaTime(i).getNome());
            time2.addItem(c.RetornaTime(i).getId() + " " + c.RetornaTime(i).getNome());
        }
    }

    private void PreencheJogador() {
        String[] t1;
        t1 = ((String) time1.getSelectedItem()).split(" ");
        for (int i = 0; i < (c.RetornaTime(time1.getSelectedIndex()).getJogadores().size()); i++) {
            jogadort1.addItem(c.RetornaTime(Integer.parseInt(t1[0]) - 1).getJogadores().get(i).getId() + " " + c.RetornaTime(Integer.parseInt(t1[0]) - 1).getJogadores().get(i).getNome());
        }
    }

    private void PreencheJogador2() {
        String[] t2;
        t2 = ((String) time2.getSelectedItem()).split(" ");
        for (int i = 0; i < (c.RetornaTime(time2.getSelectedIndex()).getJogadores().size()); i++) {
            jogadort2.addItem(c.RetornaTime(Integer.parseInt(t2[0]) - 1).getJogadores().get(i).getId() + " " + c.RetornaTime(Integer.parseInt(t2[0]) - 1).getJogadores().get(i).getNome());
        }
    }

    private void Limpar() {
        placar1.setText("0");
        placar2.setText("0");
        placar.setText("X");
        gols=new ArrayList();
    }

    public void actionPerformed(ActionEvent e) {
        if (time1.getSelectedIndex() == time2.getSelectedIndex()) {
            JOptionPane.showMessageDialog(null, "Não é possivel realizar uma partida com times iguais.");
            return;
        }
        if (e.getSource() == golt1) {
            time1.disable();
            time2.disable();
            placar1.setText(String.valueOf(Integer.parseInt(placar1.getText()) + 1));     //atualizando o placar1
            String[] v;
            v = ((String) jogadort1.getSelectedItem()).split(" ");
            gols.add(Integer.parseInt(v[0]));
        } else if (e.getSource() == golt2) {
            time1.disable();
            time2.disable();
            placar2.setText(String.valueOf(Integer.parseInt(placar2.getText()) + 1));    //atualizando o placar2
            String[] v;
            v = ((String) jogadort2.getSelectedItem()).split(" ");
            gols.add(Integer.parseInt(v[0]));
        } else if (e.getSource() == cadastrar) {
            String[] v1, v2;
            v1 = ((String) time1.getSelectedItem()).split(" ");
            v2 = ((String) time2.getSelectedItem()).split(" ");

            p.setTime1(c.RetornaTime(Integer.parseInt(v1[0]) - 1));  //setando os times da lista para a partida           
            p.setTime2(c.RetornaTime(Integer.parseInt(v2[0]) - 1));

            p.setGtime1(Integer.parseInt(placar1.getText()));   //colocando os gols do label na partida
            p.setGtime2(Integer.parseInt(placar2.getText()));

            p.getTime1().setGp(p.getTime1().getGp() + p.getGtime1());    //colocando gols sofridos GS e gols GP feitos nos times
            p.getTime1().setGs(p.getTime1().getGs() + p.getGtime2());
            p.getTime2().setGp(p.getTime2().getGp() + p.getGtime2());
            p.getTime2().setGs(p.getTime2().getGs() + p.getGtime1());

            for (int x = 0; x < gols.size(); x++) {  //colocando os gols nos jogadores
                c.Gol(gols.get(x)-1);
            }

            p.getTime1().CalculaPontos(); //atualizando os pontos do time
            p.getTime2().CalculaPontos();

            for (int y = 0; y < 5; y++) {
                //incrementando o numero de partidas jogadas em cada um dos jogadores
                p.getTime1().getJogadores().get(y).setNpartidas(p.getTime1().getJogadores().get(y).getNpartidas() + 1);
                p.getTime2().getJogadores().get(y).setNpartidas(p.getTime2().getJogadores().get(y).getNpartidas() + 1);
                //atualizando a media de gols de cada jogador
                p.getTime1().getJogadores().get(y).CalculaMedia();
                p.getTime2().getJogadores().get(y).CalculaMedia();
            }

            //atualizando empates vitorias e derrotas nos 2 times
            if (Integer.parseInt(placar1.getText()) == Integer.parseInt(placar2.getText())) {
                p.getTime1().setE(p.getTime1().getE() + 1);
                p.getTime2().setE(p.getTime2().getE() + 1);
                JOptionPane.showMessageDialog(null, "Empate!");
            } else if (Integer.parseInt(placar1.getText()) > Integer.parseInt(placar2.getText())) {
                p.getTime1().setV(p.getTime1().getV() + 1);
                p.getTime2().setD(p.getTime2().getD() + 1);
                JOptionPane.showMessageDialog(null, "Vitória do time " + p.getTime1().getNome());
            } else {
                p.getTime2().setV(p.getTime2().getV() + 1);
                p.getTime1().setD(p.getTime1().getD() + 1);
                JOptionPane.showMessageDialog(null, "Vitória do time " + p.getTime2().getNome());
            }
            p.setGols(gols);
            c.AddPartida(p);
            p = new Partida();
            Limpar();
            time1.enable();
            time2.enable();
        } else if (e.getSource() == limpar) {
            Limpar();
            time1.enable();
            time2.enable();
            p = new Partida();
        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == time1) {
            jogadort1.removeAllItems();  // limpando os combo para uma nova seleção
            PreencheJogador();
        } else if (e.getSource() == time2) {
            jogadort2.removeAllItems(); // limpando os combo para uma nova seleção
            PreencheJogador2();
        }

    }

}
