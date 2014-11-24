package Model;

/**
 *
 * @author 010900
 */
public class Jogador {
    
    String nome;
    int idade, saldogols, npartidas, ncamisa, id;
    float altura, peso, mediagols;
    boolean temTime;
    Time time;
    
    public Jogador(){
        time = null;
        temTime = false;
    }

    public void setTime(Time time) {
        this.time = time;
        temTime = true;
    }

    public Time getTime() {
        return time;
    }
    
    

    public int getNcamisa() {
        return ncamisa;
    }

    public void setNcamisa(int ncamisa) {
        this.ncamisa = ncamisa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getSaldogols() {
        return saldogols;
    }

    public void setSaldogols(int saldogols) {
        this.saldogols = saldogols;
    }

    public int getNpartidas() {
        return npartidas;
    }

    public void setNpartidas(int npartidas) {
        this.npartidas = npartidas;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getMediagols() {
        CalculaMedia();
        return mediagols;
    }

    public void setMediagols(float mediagols) {
        this.mediagols = mediagols;
    }
    
    public void CalculaMedia(){
        mediagols = (float)saldogols/npartidas;
    }
    
    public void AddGol(){
        saldogols++;
    }

    @Override
    public String toString() {
        if (temTime==true){        
        return "Nome: " + nome + "\nIdade: " + idade +"\nAltura: " + altura + "\nPeso=" + peso + "\nSaldo de gols: " + saldogols + "\nPartidas Jogadas: " + npartidas + ""
                + "\nNúmero da camisa: " + ncamisa  + "\nMedia de gols por partida: " + mediagols + ""
                + "\nTime: "+time.getNome();
        }else{
            return "Nome: " + nome + "\nIdade: " + idade +"\nAltura: " + altura + "\nPeso=" + peso + "\nSaldo de gols: " + saldogols + "\nPartidas Jogadas: " + npartidas + ""
                + "\nNúmero da camisa: " + ncamisa  + "\nMedia de gols por partida: " + mediagols;
        }
    }
        
     
        
}
