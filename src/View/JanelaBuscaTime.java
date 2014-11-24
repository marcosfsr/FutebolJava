package View;

import Control.Controle;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author cefas
 */
public class JanelaBuscaTime extends JFrame implements ActionListener{
    
    JTextArea texto;
    JButton selecionar, todos;
    JComboBox box;
    JPanel p, p1, p2;
    Controle c;
    
    
    public void BuscaTime(Controle x){
        c=x;
        p = new JPanel();
        p1 = new JPanel(new GridLayout(1,3));
        p2 = new JPanel();
        
        texto = new JTextArea(15 ,30);
        selecionar = new JButton("Selecionar");
        selecionar.addActionListener(this);
        todos = new JButton("Exibir todos"); 
        todos.addActionListener(this);
        
        box = new JComboBox();        
        for(int i=0; i<c.getIdt()-1; i++){
            box.addItem(c.RetornaTime(i).getId()+" "+c.RetornaTime(i).getNome());
        }
        
        p1.add(box);        
        p1.add(selecionar);
        p1.add(todos);
        
        p2.add(texto);
        
        p.add(p1);
        p.add(p2);
        
        add(p);
        
        setTitle("Buscar Time");
        setSize(400,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==todos){
            texto.setText(c.ListarTimes());
        }else if(ae.getSource()==selecionar){
            String[] v;
            v=((String)box.getSelectedItem()).split(" ");
            texto.setText(c.BuscaTime(Integer.parseInt(v[0])-1));
        }
    }
}
