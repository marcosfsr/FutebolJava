package Model;

import java.util.ArrayList;

/**
 *
 * @author 010900
 */
public class Time {   
    String nome;
    ArrayList<Jogador> jogadores;
    int pontos, gs, gp, v, d ,e, id;   
    
    
    public Time(){
        jogadores = new ArrayList();
        pontos=gs=gp=v=d=e=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int idt) {
        this.id = idt;
    }
            

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getGs() {
        return gs;
    }

    public void setGs(int gs) {
        this.gs = gs;
    }

    public int getGp() {
        return gp;
    }

    public void setGp(int gp) {
        this.gp = gp;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }
    public void AddJogador(Jogador j){
        jogadores.add(j);
    } 
    
    public Jogador RetornaJogador(int id){
        return jogadores.get(id);
    }
    
    public void CalculaPontos(){
        pontos=v*3+e;
    }
    
    public boolean VerificaCamisa(int n){
        boolean teste=true;
        for(int i=0; i<jogadores.size();i++){
            if(n==jogadores.get(i).getNcamisa()){
                return false;
            }
        }
        return teste;
    }
    public String CalculaSaldo(){
        String aux;
        aux=String.valueOf(gp-gs);
        return aux;
    }
    
    public String CalculaMedia(){ //media sobre gols feitos
        String aux;
        aux=String.valueOf(gp/(v+d+e));
        return aux;   
    }    
    

    @Override
    public String toString() {
        CalculaPontos();  // chamando aqui para pegar o valor final de v (vitórias) que será usado para multiplicar por 3 e obter a pontuação do time (+ empates)
        String aux="";
        for(int i=0;i<5;i++){
            aux+=RetornaJogador(i).getNome()+"\n";
        }        
        return "Nome: " + nome + "\nPontos: " + pontos + "\nGols sofridos: " + gs + "\nGols feitos: " + gp + "\nVitórias: " + v + "\nDerrotas: " + d
                + "\nEmpates: " + e + "\n\njogadores:\n" +aux;
    }
    
    
    
}
