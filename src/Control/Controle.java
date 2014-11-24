package Control;

import Model.Jogador;
import Model.Partida;
import Model.Time;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author 010900
 */
public class Controle {
    
    ArrayList<Jogador> jogadores;
    ArrayList<Time> times;
    ArrayList<Partida> partidas;
    int idt, idj, idp;
    
    public Controle(){
        jogadores = new ArrayList();
        times = new ArrayList();
        partidas = new ArrayList();
        idt=idj=idp=1;        
    }

    public int getIdt() {
        return idt;
    }

    public void setIdt(int idt) {
        this.idt = idt;
    }

    public int getIdj() {
        return idj;
    }

    public void setIdj(int idj) {
        this.idj = idj;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public void AddJogador(Jogador j){
        j.setId(idj);
        jogadores.add(j);
        idj++;
    }
    
    public void AddTime(Time t){
        t.setId(idt);
        times.add(t);
        idt++;
    }
    
    public void AddPartida(Partida p){
        p.setId(idp);
        partidas.add(p);
        idp++;
    }
    
    public Jogador RetornaJogador(int id){
        return jogadores.get(id);
    }
    
    public String BuscaJogador(int id){
        String aux;
        aux = jogadores.get(id).toString();
        return aux;
    }
    
    // método para pegar apenas nome e gols dos jogadores
    public String BuscaGolsJ(int id){
        String aux;
        aux = "O jogador "+jogadores.get(id).getNome();
        aux+=" fez "+jogadores.get(id).getSaldogols()+ " gols em "+jogadores.get(id).getNpartidas()+" partidas.";
        return aux;
    }
    
    // inserir time no jogador
    public void InserirTime(int id, Time t){
        jogadores.get(id).setTime(t);
    }
    
    public String BuscaTime(int id){
        String aux;
        aux = times.get(id).toString();
        return aux;
    }
    
    // método para pegar apenas nome e gols dos times
    public String BuscaGolsT(int id){
        String aux;
        aux ="O time "+times.get(id).getNome();
        aux+=" possui saldo de "+times.get(id).CalculaSaldo()+" gols";
        return aux;
    }
    
    // verifica se há ao menos 5 jogadores sem time
    public boolean VerificaDisponibilidade(){
        int contSim=0;
        for(int i=0; i<jogadores.size();i++){
            if(jogadores.get(i).getTime()==null){
                contSim++;
                if(contSim==5)
                    return true; // há 5 ou mais jogadores disponíveis
            }
        }
        return false; // há menos de 5 jogadores disponíveis
    }
    
    public Time RetornaTime(int id){
        return times.get(id);
    }
    
    public void Gol(int x){
        jogadores.get(x).AddGol();
    }
    
    // verifica se há jogadores para listar
    public boolean temJogador(){
        if(jogadores.isEmpty())
            return false;
        return true;
    }
    
    // verifica se há times cadsatrados para listar
    public boolean temTime(){
        if(times.isEmpty())
            return false;
        return true;
    }
    
    // verifica se há partidas cadastradas
    public boolean temPartida(){
        if(partidas.isEmpty())
            return false;
        return true;
    }
    
    public String ListarTimes(){
        String t="Lista de Times\n\n";
        if(times.isEmpty()){
            t = "lista de Times Vazia!";
        }else{
            for(int i =0; i<times.size(); i++){
                t+=times.get(i).getNome()+"\n";
            }
        }
        return t;
    }
    
    public String ListarJogadores(){
        String j="Lista de Jogadores:\n\n";
        if(jogadores.isEmpty()){
            j+= "lista Vazia!";
            return j;
        }else{
            for(int i =0; i<jogadores.size(); i++){
                j+=jogadores.get(i).getNome()+"\n";
            }
        }
        return j;
    }
    
    public String ListarPartidas(){
        String p="Lista de Partidas\n\n";
        if(partidas.isEmpty()){
            p = "Lista de Partidas Vazia!";
        }else{
            for(int i =0; i<partidas.size(); i++){
                p+=partidas.get(i).getTime1().getNome()+" "+partidas.get(i).getGtime1()+" X "+partidas.get(i).getGtime2()+" "+partidas.get(i).getTime2().getNome()+"\n";
            }
        }
        return p;
    }
    
    //metodo para encontrar o artilheriro
    public String Artilheiro(){
        String aux;
        if(jogadores.isEmpty()){
            aux="Nenhum jogador encontrado.";
            return aux;
        }
        
        Jogador j = new Jogador();
        j.setSaldogols(0);
        for(int i=0; i<jogadores.size(); i++){
            if(jogadores.get(i).getSaldogols()>j.getSaldogols()){
                j=jogadores.get(i);
            }
        }
        if(j.getSaldogols()==0){
            aux="Nenhum jogador marcou gol.";
            return aux;
        }else{
            aux=(j.toString());
            return aux;
        }
    }
    
    public Partida RetornaPartida(int id){
        return partidas.get(id);
    }
    
    public String BuscaPartida(int id){
        String aux;
        aux = partidas.get(id).toString();
        if(partidas.get(id).getGols().isEmpty()){
            return aux;
        }else{
            aux+="\nJogadores que marcaram:\n";
            for(int i=0; i<partidas.get(id).getGols().size(); i++){
                aux+=RetornaJogador((partidas.get(id).getGols().get(i))-1).getNome()+"\n";
            }
        }
        return aux;
    }
    
    public String MediaJ(int id){
        if(jogadores.get(id).getSaldogols()==0){
            String aux="O jogador selecionado ainda não marcou gols.";
            return aux;
        }
        String aux = "O jogador "+jogadores.get(id).getNome()+" possui uma media de ";
        aux += String.valueOf(jogadores.get(id).getMediagols())+" gols por partida.";
        return aux;
    }
    
    public String MediaT(int id){
        String aux = "O time "+times.get(id).getNome()+" possui uma media de ";
        aux += String.valueOf(times.get(id).CalculaMedia())+" gols por partida.";
        return aux;
    }
    
    
    
}
