
package junkfood;

import java.util.ArrayList;
import java.util.Scanner;

class Produto{
    public int ind;
    public String nome;
    public int q_unidade;
    public float preco;
    
    
    public Produto(int ind, String nome, int q_unidade, float preco){
        this.ind = ind;
        this.nome = nome;
        this.q_unidade = q_unidade;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQ_unidade() {
        return q_unidade;
    }

    public void setQ_unidade(int q_unidade) {
        this.q_unidade = q_unidade;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getInd() {
        return ind;
    }

    public void setInd(int ind) {
        this.ind = ind;
    }
    
    
    
    public String toString(){
        return nome + " : " + q_unidade + " U : "+ preco + "0 RS";
    }
    
}

class Maquina{
    private int espirais;
    private int q_max;
    private float saldo;
    
    private ArrayList <Produto> prod;
    
    public Maquina(int esperais, int q_max){
        this.espirais = esperais;
        this.q_max = q_max;
        prod = new ArrayList();
        for(int i = 0; i < this.espirais; i++){
            prod.add(null);
        }
    }

    
    public int getEspirais() {
        return espirais;
    }

    public void setEspirais(int espirais) {
        this.espirais = espirais;
    }

    public int getQ_max() {
        return q_max;
    }

    public void setQ_max(int q_max) {
        this.q_max = q_max;
    }
    
    public Float getSaldo(){
        return saldo;
    }
    
    public void setSaldo(float saldo){
        this.saldo = saldo;
    }
    
    
    
    
    boolean set(int i, String nome, int capacidade, float preco){
        if(i < 0 || i >= getEspirais()){
            System.out.println("fail: indice nao existe");
            return false;
        }
        if(prod.get(i) != null){
            System.out.println("fail: limite excedido");
            return false;
        }
        
        prod.set(i,new Produto(i, nome, capacidade, preco));
        return true;
        
        
    }
    
    
    public void limpar(int indice){
        if(prod.get(indice) != null){
            prod.set(indice, null);
        }else if(indice > getEspirais()-1){
            System.out.println("Indice não existe");
        }else{
            System.out.println("Produto não existe na posição " + indice);
        }
    
    }
    
    
    public void dinheiro(float dinheiro){
        setSaldo(getSaldo() + dinheiro);
    }
    
    public void comprar(int indice){
        int tam = getEspirais()-1;
        
        for(Produto p1: prod){
            if(p1 != null){
                if(p1.ind == indice){
                    if(this.saldo > p1.preco &&  p1.q_unidade > 0){
                        System.out.println("voce comprou um " + p1.getNome());
                        setSaldo(this.saldo - p1.preco);
                        p1.setQ_unidade(p1.getQ_unidade()-1);
                        break;
                    }
                    else if(this.saldo > 0 && p1.q_unidade == 0){
                        System.out.println("fail: espiral sem produtos");
                        break;
                    }
                    else if(this.saldo < p1.preco && (this.saldo > 0 || this.saldo == 0) && p1.q_unidade > 0){
                        System.out.println("fail: saldo insuficiente");
                        break;
                    }
                    
                }
                else if(indice > tam){
                        System.out.println("fail: indice nao existe");
                        break;
                }
            }
            
        }
        
    }
    
    public void troco(){
        System.out.println("voce recebeu " + getSaldo() + " RS");
        setSaldo(0);
    }
     
    @Override
    public String toString(){
        
        String out = "saldo: " +getSaldo() + "0\n";
        for(Produto prod : prod){
            if(prod == null){
                out += "[ empty : 0 U : 0.00 RS ]\n";
            }else{
                out += "[ "+prod + "]\n";
            }
        }
        return out;
    }
    
}

public class JunkFood {

   
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Maquina maq = new Maquina(0,0);
        
        while(true){
            String line = scan.nextLine();
            System.out.println("$" + line);
            String input[] = line.split(" ");
            
            if(input[0].equals("end")){
                break;
            }else if(input[0].equals("show")){
                System.out.println(maq);
                
            }else if(input[0].equals("init")){
                maq = new Maquina(Integer.parseInt(input[1]),Integer.parseInt(input[2]));
                
            }else if(input[0].equals("set")){
                maq.set(Integer.parseInt(input[1]), input[2], Integer.parseInt(input[3]), Float.parseFloat(input[4]));
                
            }else if(input[0].equals("limpar")){
                maq.limpar(Integer.parseInt(input[1]));
                
            }else if(input[0].equals("dinheiro")){
                maq.dinheiro(Float.parseFloat(input[1]));
                
            }else if(input[0].equals("comprar")){
                maq.comprar(Integer.parseInt(input[1]));
            }else if(input[0].equals("troco")){
                maq.troco();
            }else{
                System.out.println("Fail: comando inválido");
            }
        }
        
    }
    
}
