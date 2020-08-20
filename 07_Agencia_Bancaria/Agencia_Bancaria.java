
package agencia_bancaria;

import java.util.ArrayList;
import java.util.Scanner;

class Operacao{
    public int indice;
    public String descricao;
    public float valor;
    public float saldo;
    
    public Operacao(int indice, String descricao, float valor, float saldo){
        this.indice = indice;
        this.descricao = descricao;
        this.valor = valor;
        this.saldo = saldo;
    }
    
    @Override
    public String toString(){
        return indice + ":  " + descricao + ":  " + valor + ":  " + saldo;
    }
    
    public int getIndice(){
        return this.indice;
    }
    
    public void setIndice(int indice){
        this.indice = indice;
    }
}

class Conta{
    private int nextId;
    private float saldo;
    private int numero;
    ArrayList<Operacao> extrato;
    
    public Conta(int numero){
        extrato = new ArrayList();
        this.numero = numero;
        this.saldo = 0;
        this.nextId = 1;
    }
    
    public void show(){
        System.out.println("conta:" + this.numero + " saldo: " + this.getSaldo());
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
    
    
    public boolean deposito(String nome,float saldo){
        if(saldo > 0){
            this.saldo += saldo;
            salvarOperacao(nome, saldo, this.saldo);
            return true;
        }else{
            System.out.println("fail: comando invalido");
        }return false;
    }
    
    public void saque(String nome,float valor){
        if(this.saldo > 0 && valor <= this.saldo){
            this.saldo -= valor;
            salvarOperacao(nome, -valor, this.saldo);
        }else if(valor > this.saldo){
            System.out.println("fail: saldo insuficiente");
        }else{
            System.out.println("fail: comando invalido");
        }
        
    }
    
    public boolean tarifa(String nome, float valor){
        this.saldo -= valor;
        salvarOperacao(nome, -valor, this.saldo);
        return true;
    }
    
    public void salvarOperacao(String descricao, float valor, float saldo){
        extrato.add(new Operacao(this.nextId, descricao, valor,saldo));
        this.nextId+=1;
    }
    
    public void extratoN(int n){
        int tam = extrato.size()-n;
        for(int i = tam; i <= extrato.size()-1;i++){
            if(extrato != null){
                int indice = extrato.get(i).indice;
                String des = extrato.get(i).descricao;
                float valor = extrato.get(i).valor;
                float n_saldo = extrato.get(i).saldo;
                System.out.println(indice + ":  " + des + ":    " + valor + ":  " + n_saldo);
            }
            
        }
    }
    
    public void extornar(String nome, int indice){
        int tam = extrato.size()-1;
        int n_ind = indice - 1;
        if(n_ind <= tam){
            if(extrato.get(n_ind).descricao.equals("tarifa")){
                float novo_valor = -extrato.get(n_ind).valor;
                 this.saldo+=novo_valor;
                salvarOperacao(nome, novo_valor, this.saldo);
            }
            else{
                System.out.println("fail: indice " + indice + " nao e tarifa");
            }
        }else{
            System.out.println("fail: indice " + indice + " invalido");
        }
    }
    
    @Override
    public String toString(){
        String out = "";
        out += "0:  abertura:   0.0:    0.0\n";        
        for(Operacao operacao: extrato){
            out += operacao + "\n";
            
            
        }
        
        return out;
    }
    
}

public class Agencia_Bancaria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       Conta conta = new Conta(0);
       while(true){
           String line = scan.nextLine();
           System.out.println("$ " + line);
           String ui[] = line.split(" ");
           
           if(ui[0].equals("end")){
               break;
               
           }else if(ui[0].equals("show")){
               conta.show();
               
           }else if(ui[0].equals("init")){
               conta = new Conta(Integer.parseInt(ui[1]));
               
           }else if(ui[0].equals("deposito")){
               conta.deposito(ui[0],Float.parseFloat(ui[1]));
               
           }else if(ui[0].equals("extrato")){
               System.out.println(conta);
               
           }else if(ui[0].equals("saque")){
               conta.saque(ui[0],Float.parseFloat(ui[1]));
               
           }else if(ui[0].equals("tarifa")){
               conta.tarifa(ui[0],Float.parseFloat(ui[1]));
               
           }else if(ui[0].equals("extratoN")){
               conta.extratoN(Integer.parseInt(ui[1]));
               
           }else if(ui[0].equals("extornar")){
               for(int i = 1; i < ui.length;i++){
                    conta.extornar(ui[0], Integer.parseInt(ui[i]));
               }
               
               
           }else{
               
               System.out.println("fail: comando invalido");
           }
       }
    }
    
}
