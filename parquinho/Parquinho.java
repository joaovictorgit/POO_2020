
package parquinho;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Kid{
    private String nome;
    private int idade;

    public Kid(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
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
        return this.nome + ":" + this.idade + " ";
    }
}


class Trampoline{
    ArrayList <Kid> crianca;
    int capacidade;
    public Trampoline(int capacidade){
        crianca = new ArrayList<Kid>();
        for(int i = 0; i < capacidade; i++)
            crianca.add(null);
        this.capacidade = capacidade;
    }
    public void chegou(Kid kid){
        crianca.add(kid);
        
    }
    
    public void entrar(){
        
		int j = 0;
        crianca.remove(j);
        j++;
        
    }
    public String toString(){
        Collections.reverse(crianca);
        String out = "=> ";
        for(Kid kid : this.crianca){
            if(kid != null)
                out += kid + " ";   
            else 
                out += " ";
        }
        out += "=> []";
        Collections.reverse(crianca);
        return out;
    }
}

public class Parquinho {

    
    public static void main(String[] args) {
        Trampoline pula = new Trampoline(5);
        Scanner input = new Scanner(System.in);
        
        while(true){
            String line = input.nextLine();
            System.out.println("$" + line);
            String entrada[] = line.split(" ");
            if(entrada[0].equals("sair")){
                break;
            }else if(entrada[0].equals("chegou")){
                pula.chegou(new Kid((entrada[1]), Integer.parseInt(entrada[2])));
                
            }else if(entrada[0].equals("show")){
                System.out.println(pula);
            }else if(entrada[0].equals("entrar")){
                pula.entrar();
            }
            
        }
        
        
    }
    
}
