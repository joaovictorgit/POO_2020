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
}
class Trampoline{
    
    private int capacidade_max = 5;
    public void Trampoline(){
        
    }

    

    public int getCapacidade_max() {
        return capacidade_max;
    }

    public void setCapacidade_max(int capacidade_max) {
        this.capacidade_max = capacidade_max;
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
        for(int i = kidsWaiting.size() - 1; i >= 0; --i)
            System.out.print(kidsWaiting.get(i) + " ");
            System.out.print("=>");
            System.out.print("[ ");                        
        for (int j = 0; j < kidsPlaying.size() ; j++) {
            System.out.printf(kidsPlaying.get(j) + " ");
        }
        System.out.print("]\n");
    }
    
    public void entrar(){
        // Tamanho do ArrayList kidsPlaying
        int tam = kidsPlaying.size();
        if(getCapacidade_max() <= tam){
            System.out.println("Capacidade máxima atingida");
        }else{
            kidsPlaying.add(kidsWaiting.get(0));
            kidsWaiting.remove(kidsWaiting.get(0));
            System.out.println("Entrou no pula pula!");
           
        }
        
    }
    
    public void sair(){
        // Tamanho do ArrayList kidsWaiting
        int tam = kidsWaiting.size();
        kidsWaiting.add(tam, kidsPlaying.get(0));
        kidsPlaying.remove(kidsPlaying.get(0));
    }
  
    public void fechar(){
        kidsWaiting.removeAll(kidsWaiting);
        kidsPlaying.removeAll(kidsPlaying);
        System.out.println("O pula pula está fechado");
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
                
            }else if(entrada[0].equals("in")){
                pula.entrar();
                
            }else if(entrada[0].equals("out")){
                pula.sair();
            }else if(entrada[0].equals("fechar")){
                pula.fechar();
            }else{
                System.out.println("Fail!");
            }
            
        }
        
        
    }
    
}
