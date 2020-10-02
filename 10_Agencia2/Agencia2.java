
package agencia2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

class Conta{
    int id;
    float saldo;
    String idCliente;
    String type;
    
    public Conta(int id, String idCliente){
        this.id = id;
        this.idCliente = idCliente;
        this.saldo = 0;
        this.type = "";
    }
    
    public void sacar(float value){
        if(this.type.equals("CC")){
            this.saldo -= value;
            
        }
        else if(this.type.equals("CP")){
            if(this.saldo-value >= 0){
                this.saldo -= value;
            }
            else{
                System.out.println("fail: saldo insuficiente");
            }
        }
    }
    
    public void depositar(float value){
        if(this.type.equals("CC")){
            this.saldo += value;
        }
        else{
            this.saldo += value;
        }
    }
    
    public void transferir(Conta other, float value){
        other.saldo += value;
    }
    
    public void atualizacaoMensal(){
        if(this.type.equals("CC")){
            this.saldo -= 20;
        }
        else{
            float novo = (this.saldo*1) / 100;
            this.saldo += novo;
        }
    }
    
    @Override
    public String toString(){
        return this.id + ":" + this.idCliente + ":" + this.saldo + "0:" + this.type;
    }
}

class Cliente {
    public String idCliente;
    //public int nextId = 0;
    public ArrayList<Conta> conta;
    public Cliente(String idCliente) {
        this.idCliente = idCliente;
        conta = new ArrayList<>();
    }
    
    
}

class ContaCorrente extends Conta{
    float tarifa_mensal = 20;
    public ContaCorrente(int id, String idCliente) {
        super(id, idCliente);
        this.type = "CC";
    }
    
    @Override
    public void atualizacaoMensal(){
        this.saldo -= tarifa_mensal;
        
    }
}

class ContaPoupanca extends Conta{
    float redimento;
    public ContaPoupanca(int id, String idCliente) {
        super(id, idCliente);
        this.type = "CP";
    }
    @Override
    public void atualizacaoMensal(){
        redimento =  (this.saldo*1) / 100;
        this.saldo += redimento;
        
    }
}

public class Agencia2 {
    TreeMap<Integer, Conta> contas;
    TreeMap<String,Cliente> clientes;
    int nextId;
    public Agencia2(){
        contas = new TreeMap<>();
        clientes = new TreeMap<>();
        this.nextId = 0;
    }
    public void AddCliente(String id){
        if(clientes.get(id) == null){
            Conta corrente = new ContaCorrente(this.nextId++, id);
            Conta poupanca = new ContaPoupanca(this.nextId++, id);
            Cliente cliente = new Cliente(id);
            
            clientes.put(id, cliente);
            contas.put(corrente.id, corrente);
            contas.put(poupanca.id, poupanca);
        }
    }
    
    public void depositar(int ind, float value){
        if(contas.containsKey(ind)){
            Conta conta = contas.get(ind);
            conta.depositar(value);
        }
        else{
            System.out.println("fail: indice inválido");
        }
    }
    
    public void sacar(int ind, float value){
        if(contas.containsKey(ind)){
            Conta conta = contas.get(ind);
            conta.sacar(value);
        }
        else{
            System.out.println("fail: indice inválido");
        }
    }
    
    public void tranferencia(int ind1, int ind2, float value){
        if(contas.containsKey(ind1) && contas.containsKey(ind2)){
            Conta c1 = contas.get(ind1);
            Conta c2 = contas.get(ind2);
            c1.sacar(value);
            c2.transferir(c2, value);
        }
        else{
            System.out.println("fail: conta nao encontrada");
        }
    }
    
    public void update(){
        for(Conta c : contas.values()){
            if(c.type.equals("CC")){
                ContaCorrente corrente = (ContaCorrente) contas.get(c.id);
                corrente.atualizacaoMensal();
            }
            else if(c.type.equals("CP")){
                ContaPoupanca poupanca = (ContaPoupanca) contas.get(c.id);
                poupanca.atualizacaoMensal();
            }
        }
    }
    
    @Override
    public String toString(){
        String out = "";
        for(Conta c : contas.values())
            out += c + "\n";
        return out;
    }
    
    public static void main(String[] args) {
        Agencia2 agencia = new Agencia2();
        Scanner scan = new Scanner(System.in);
        
        while(true){
            String line = scan.nextLine();
            System.out.println("$ " + line);
            String ui[] = line.split(" ");
            
            if(ui[0].equals("end")){
                break;
            }
            else if(ui[0].equals("addCli")){
                agencia.AddCliente(ui[1]);
            }else if(ui[0].equals("show")){
                System.out.println(agencia);
            }else if(ui[0].equals("deposito")){
                agencia.depositar(Integer.parseInt(ui[1]), Float.parseFloat(ui[2]));
            }else if(ui[0].equals("saque")){
                agencia.sacar(Integer.parseInt(ui[1]), Float.parseFloat(ui[2]));
            }else if(ui[0].equals("transf")){
                agencia.tranferencia(Integer.parseInt(ui[1]),Integer.parseInt(ui[2]), Float.parseFloat(ui[3]));
            }else if(ui[0].equals("update")){
                agencia.update();
            }else{
                System.out.println("fail: comando inválido");
            }
        }
        scan.close();
    }
    
}
