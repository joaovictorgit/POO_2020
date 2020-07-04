
package bicicleta;

import java.util.Scanner;

class Pessoa{
    String nome;
    int idade;
    public Pessoa(String nome, int idade){
        this.nome = nome;
        this.idade = idade;
    }
}

public class Bicicleta {
    Pessoa pessoa;
    int quilometros = 0;
    public Bicicleta(int quilometros){
        this.quilometros = quilometros;
    }
            
    void subir(Pessoa pessoa){
        if(this.pessoa == null){
            this.pessoa = pessoa;
        }
        else{
            System.out.println("Já tem gente na bike");
        }
    }
    
    void pedalar(int temp_pedalar){
        if(this.pessoa == null){
            System.out.println("Nao tem ninguem na bicicleta");
        }
        else if(this.pessoa.idade < 5 && temp_pedalar > 10){
            System.out.println("Bike com rodinha\n");
            System.out.println("Seu tempo de pedalada eh de " + temp_pedalar + "\n");
        }
        else if(this.pessoa.idade > 10 && temp_pedalar > 30) {
            System.out.println(this.pessoa.nome + " esta ficando cansado");
        }
        else if(this.pessoa.idade > 10 && (temp_pedalar > 10 && temp_pedalar < 30)){
            System.out.println(this.pessoa.nome + " esta de boa.");
        }
    }
    
    void descer(){
        if(this.pessoa != null){
            System.out.println(this.pessoa.nome + " desceu da bike");
            this.pessoa = null;
        }
        else{
            System.out.println("Nao tem ninguem na bike");
        }
    }
    
    
    void quilometragem(){
        if(this.pessoa == null){
            this.pessoa = pessoa;
        }
        else if(this.quilometros < 20){
            System.out.println(this.pessoa.nome + " acelera essa monarc");
        }
        else if(this.quilometros > 20){
            System.out.println(this.pessoa.nome + " freia meu chapa");
        }
    }
    
    
    
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite os km/h: ");
        int km = input.nextInt();
        Bicicleta bike = new Bicicleta(km);
        
        while(true){
            String linha = input.nextLine();
            String entrada[] = linha.split(" ");
            if(entrada[0].equals("sair")){
                break;
            }
            else if(entrada[0].equals("subir")){
                Pessoa pessoa01 = new Pessoa(entrada[1], Integer.parseInt(entrada[2]));
                bike.subir(pessoa01);
            }
            else if(entrada[0].equals("pedalar")){
                int tempo = Integer.parseInt(entrada[1]);
                bike.pedalar(tempo);
            }
            else if(entrada[0].equals("quilometragem")){
                bike.quilometragem();
            }
            else if(entrada[0].equals("descer")){
                bike.descer();
            }
        }
        
    }
    
}
