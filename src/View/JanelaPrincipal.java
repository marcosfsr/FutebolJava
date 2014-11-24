package View;

import Control.Controle;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Micro
 */
public class JanelaPrincipal extends JFrame implements ActionListener {
    JMenuBar menu;
    JMenu cadastrar, buscar, estatisticas, saldogols, mediagols;
    JMenuItem cjogador, ctime, cpartida, bjogador, btime, bpartida, saldot, saldoj, artilheiro, mediagolsj, mediagolst;
    JPanel painel;
    Controle c;

    public void MontaPrincipal() {
        c = new Controle();
        menu = new JMenuBar();
        cadastrar = new JMenu("Cadastrar");
        buscar = new JMenu("Buscar");
        estatisticas = new JMenu("Estatísticas");
        saldogols = new JMenu("Saldo de gols");
        saldogols.addActionListener(this);
        mediagols = new JMenu("Média de gols");
        mediagols.addActionListener(this);
        cjogador = new JMenuItem("Jogador");
        cjogador.addActionListener(this);
        ctime = new JMenuItem("Time");
        ctime.addActionListener(this);
        cpartida = new JMenuItem("Partida");
        cpartida.addActionListener(this);
        bjogador = new JMenuItem("Jogador");
        bjogador.addActionListener(this);
        btime = new JMenuItem("Time");
        btime.addActionListener(this);
        bpartida = new JMenuItem("Partida");
        bpartida.addActionListener(this);
        saldot = new JMenuItem("Time");
        saldot.addActionListener(this);
        saldoj = new JMenuItem("Jogador");
        saldoj.addActionListener(this);
        artilheiro = new JMenuItem("Artilheiro");
        artilheiro.addActionListener(this);
        mediagolsj = new JMenuItem("Jogador");
        mediagolsj.addActionListener(this);
        mediagolst = new JMenuItem("Time");
        mediagolst.addActionListener(this);

        painel = new JPanel(new BorderLayout());

        cadastrar.add(cjogador);
        cadastrar.add(ctime);
        cadastrar.add(cpartida);

        buscar.add(bjogador);
        buscar.add(btime);
        buscar.add(bpartida);

        saldogols.add(saldoj);
        saldogols.add(saldot);

        mediagols.add(mediagolsj);
        mediagols.add(mediagolst);

        estatisticas.add(saldogols);
        estatisticas.add(artilheiro);
        estatisticas.add(mediagols);

        menu.add(cadastrar);
        menu.add(buscar);
        menu.add(estatisticas);

        painel.add(menu, BorderLayout.PAGE_START);
        add(painel);
        setTitle("Cadastro Futebol");
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cjogador) {
            JanelaJogador jg = new JanelaJogador();
            jg.MontaJogador(c);
        } else if (e.getSource() == ctime) {
            if (c.VerificaDisponibilidade() == false) { // o método verificadisponibilidade retorna true ou false, se true entra no if
                JOptionPane.showMessageDialog(null, "Não há jogadores suficientes para criar um time.");
            } else {
                JanelaTime jt = new JanelaTime();
                jt.MontaTime(c);
            }
        } else if (e.getSource() == cpartida) {
            if (c.getIdt() < 2) {
                JOptionPane.showMessageDialog(null, "São necessários 2 times para realizar uma partida");
            } else {
                JanelaPartida jp = new JanelaPartida();
                jp.MontaPartida(c);
            }
        } else if (e.getSource() == bjogador) { // chama janela buscaJogador
            if (c.temJogador()) {  // verifica se há jogadores cadastrados para abrir ou não a janela de busca
                JanelaBuscaJogador bj = new JanelaBuscaJogador();
                bj.BuscaJogador(c);
            } else {
                JOptionPane.showMessageDialog(null, "Não jogadores cadastrados.");
            }

        } else if (e.getSource() == btime) { // chama janela buscaTime
            if (c.temTime()) { // verifica se há times cadastrados para abrir ou não a janela de busca
                JanelaBuscaTime bt = new JanelaBuscaTime();
                bt.BuscaTime(c);
            } else {
                JOptionPane.showMessageDialog(null, "Não há times cadastrados.");
            }

        } else if (e.getSource() == bpartida) { // chama a janela buscaPartida
            if (c.temPartida()) { // verifica se há partidas cadastradas
                JanelaBuscaPartida bp = new JanelaBuscaPartida();
                bp.BuscaPartida(c);
            } else {
                JOptionPane.showMessageDialog(null, "Não há partidas cadastradas");
            }
        } else if (e.getSource() == artilheiro) {
            JOptionPane.showMessageDialog(null, c.Artilheiro());
        }else if(e.getSource() == saldoj){
            JanelaSaldoGols js = new JanelaSaldoGols(1);
            js.BuscaSaldoGols(c);
        }else if(e.getSource() == saldot){
            JanelaSaldoGols js = new JanelaSaldoGols(2);
            js.BuscaSaldoGols(c);
        }else if(e.getSource() == mediagolsj){
            JanelaMediaGols jm = new JanelaMediaGols(1);
            jm.BuscaMediaGols(c);
        }else if(e.getSource() == mediagolst){
            JanelaMediaGols jm = new JanelaMediaGols(2);
            jm.BuscaMediaGols(c);
        }
    }

}
