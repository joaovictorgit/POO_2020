package parquinho;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Kid {
    private String nome;
    private int idade;
    
    public void Kid() {
       
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
    
    public String toString(){
        return nome + ":" + idade;
    }
    
}
class Trampoline{
    
    private int capacidade_max = 5;
    private float tempo;
    private float tempo_fechar = 12;
    private String pai;
	
	
    public void Trampoline(){
        
    }

    // Getters e Setters
    public int getCapacidade_max() {
        return capacidade_max;
    }

    public void setCapacidade_max(int capacidade_max) {
        this.capacidade_max = capacidade_max;
    }

    public float getTempo() {
        return tempo;
    }

    public void setTempo(float tempo) {
        this.tempo = tempo;
    }

    public float getTempo_fechar() {
        return tempo_fechar;
    }

    public void setTempo_fechar(float tempo_fechar) {
        this.tempo_fechar = tempo_fechar;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }
    
    
    
    
    
    Kid kid;
    
    ArrayList <String> kidsWaiting = new ArrayList();
    ArrayList <String> kidsPlaying = new ArrayList();
    
    public void chegou(String nome, String idade){
        kid = new Kid();
        int tam = kidsWaiting.size();
        kid.setNome(nome);
        kid.setIdade(Integer.parseInt(idade));
        kidsWaiting.add(kid.getNome() + kid.getIdade());
       
        
    }
    public void show(){
        int tam_waiting = kidsWaiting.size();
        
        for(int i = tam_waiting - 1; i >= 0; --i)
            System.out.print(kidsWaiting.get(i) + " ");
        System.out.print("=>");
        System.out.print("[ ");                        
        for (int j = 0; j < kidsPlaying.size() ; j++) {
            System.out.printf(kidsPlaying.get(j) + " ");
        }
        System.out.print("]\n");
        if(tam_waiting >= 1){
            System.out.println("A última criança entrará em " + (tam_waiting*2));
        }
    }
    
    /*@Override
    public String toString(){
        String out = "=>";
        for(String kid: kidsWaiting)
            out += kid + " ";
        out += "=> [";
        for(String kid: kidsPlaying)
            out += kid + " ";
        out += "]";
        return out;
    }*/
    
    public void entrar(){
        // Tamanho do ArrayList kidsPlaying
        int tam = kidsPlaying.size();
        if(getCapacidade_max() <= tam){
            System.out.println("Capacidade máxima atingida");
            
        }else if((tam < getCapacidade_max()) && getTempo() < 12){
            kidsPlaying.add(kidsWaiting.get(0));
            kidsWaiting.remove(kidsWaiting.get(0));
            System.out.println("Entrou no pula pula!");
            setTempo(getTempo()+2);
            System.out.println("O parque irá fechar em " + (12-getTempo()) + " minutos");
        }else if(getTempo() > 12){
            fechar();
            System.out.println("O parque já está fechando");
        }
        
    }
    
    public void sair(){
        // Tamanho do ArrayList kidsWaiting
        if(getTempo() < 12){
            int tam = kidsWaiting.size();
            kidsWaiting.add(tam, kidsPlaying.get(0));
            kidsPlaying.remove(kidsPlaying.get(0));
        }else if(getTempo() >= 12){
            fechar();
            System.out.println("O parque já está fechando");
        }
        
    }
  
    public void fechar(){
        kidsWaiting.removeAll(kidsWaiting);
        kidsPlaying.removeAll(kidsPlaying);
        System.out.println("O pula pula está fechado");
    }
    
    public void papai_chegou(String nome){
        setPai(nome);
        for(int i = 0; i < kidsWaiting.size(); i++){
            if(kidsWaiting.get(i).equals(getPai())){
                kidsWaiting.remove(i);
            }else{
                System.out.println("Criança não se encotra na fila");
            }
        }
        for(int i = 0; i < kidsPlaying.size(); i++){
            if(kidsPlaying.get(i).equals(getPai())){
                kidsPlaying.remove(i);
            }else{
                System.out.println("Criança não se encotra no pula pula");
            }
        }
        
    }
    
}
public class Parquinho {

    
    public static void main(String[] args) {
        
        //Kid kid = new Kid();
        Trampoline pula = new Trampoline();
        Scanner input = new Scanner(System.in);
        
        
        while(true){
            String line = input.nextLine();
            System.out.println("$" + line);
            String entrada[] = line.split(" ");
            if(entrada[0].equals("sair")){
                break;
            }else if(entrada[0].equals("chegou")){
                pula.chegou(entrada[1], entrada[2]);
                
            }else if(entrada[0].equals("show")){
                pula.show();
                //pula.toString();
            }else if(entrada[0].equals("in")){
                pula.entrar();
                
            }else if(entrada[0].equals("out")){
                pula.sair();
            }else if(entrada[0].equals("fechar")){
                pula.fechar();
            }else if(entrada[0].equals("papai")){
                pula.papai_chegou(entrada[1]);
            }else{
                System.out.println("Fail!");
            }
            
        }
        
        
    }
    
}
