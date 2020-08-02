/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conta_banco;


import javax.swing.JOptionPane;
        
public class Conta_Banco {

    String n_banco = "";
    int n_agencia = 0;
    int n_conta = 0;
    double v_conta = 1200.00;
    double v_saque = 0;
    double v_deposito = 0;
    
    void saque(){
        this.n_banco = JOptionPane.showInputDialog("Digite o nome do banco:\n");
        this.n_agencia = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da agência:\n"));
        this.n_conta = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta:\n"));
        this.v_saque = Integer.parseInt(JOptionPane.showInputDialog("Valor a retirar:\n"));
        
        double saque = this.v_conta - this.v_saque;
        
        if(saque > this.v_conta){
            JOptionPane.showMessageDialog(null, "Saque do banco do "+ this.n_banco +" realizado com sucesso!\n"
                    + "O valor retirado foi R$ "+ this.v_saque + "\n"
                    + "Seu saldo atual é R$ "+ saque +"\n"
                    + "OBS: Sua conta encontra-se no vermelho!!!");
        }
        else {
            JOptionPane.showMessageDialog(null, "Saque do banco do "+ this.n_banco +" realizado com sucesso!\n"
                    + "O valor retirado foi R$"+ this.v_saque + "\n"
                    + "Seu saldo atual é R$"+ saque +"\n");
        }        
    }
    
    void depositar(){
        this.n_banco = JOptionPane.showInputDialog("Digite o nome do banco:\n");
        this.n_agencia = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da agência:\n"));
        this.n_conta = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta:\n"));
        this.v_deposito = Integer.parseInt(JOptionPane.showInputDialog("Valor a depositar:\n"));
        
        double depositar = this.v_conta + this.v_deposito;
        
        
        JOptionPane.showMessageDialog(null, "Deposito no banco do "+ this.n_banco +" realizado com sucesso!!"
                + "Seu saldo atual é de R$ "+ depositar);
        
    }
    
    void transferir(){
        this.n_banco = JOptionPane.showInputDialog("Digite o nome do banco:\n");
        this.n_agencia = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da agência:\n"));
        this.n_conta = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta:\n"));
        this.v_deposito = Integer.parseInt(JOptionPane.showInputDialog("Valor a depositar:\n"));
        
        
        if("Brasil".equals(this.n_banco) || "brasil".equals(this.n_banco)){
            double brasil = this.v_deposito + ((this.v_deposito * 5) / 100);
            JOptionPane.showMessageDialog(null, "O valor transferido foi R$" + brasil + "\n");
        }
        else if ("Bradesco".equals(this.n_banco) || "bradesco".equals(this.n_banco)){
            double bradesco = this.v_deposito + ((this.v_deposito * 3) / 100);
            JOptionPane.showMessageDialog(null, "O valor transferido foi R$" + bradesco + "\n");
        }
        else if("Santander".equals(this.n_banco) || "santander".equals(this.n_banco)){
            double santander = this.v_deposito + ((this.v_deposito * 2) / 100);
            JOptionPane.showMessageDialog(null, "O valor transferido foi R$" + santander + "\n");
        }        
    }
    
    void cartao(){
        this.n_banco = JOptionPane.showInputDialog("Digite o nome do banco:\n");
        this.n_agencia = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da agência:\n"));
        this.n_conta = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta:\n"));
        int temp = Integer.parseInt(JOptionPane.showInputDialog("Digite o tempo da sua conta nesse banco:\n"));
        
        if("Brasil".equals(this.n_banco) || "brasil".equals(this.n_banco)){
            double c_brasil = 0.0;
            if(temp >= 5){
                c_brasil = this.v_conta + ((this.v_conta * 30) / 100);
                JOptionPane.showMessageDialog(null, "Seu novo limite é de R$" + c_brasil + "\n");
            }
            else {
                c_brasil = this.v_conta + ((this.v_conta * 20) / 100);
                JOptionPane.showMessageDialog(null, "Seu novo limite é de R$" + c_brasil + "\n");
            }
            
        }
        else if ("Bradesco".equals(this.n_banco) || "bradesco".equals(this.n_banco)){
            double c_brades = 0.0;
            if(temp >= 5){
                c_brades = this.v_conta + ((this.v_conta * 25) / 100);
                JOptionPane.showMessageDialog(null, "Seu novo limite é de R$" + c_brades + "\n");
            }
            else {
                c_brades = this.v_conta + ((this.v_conta * 18) / 100);
                JOptionPane.showMessageDialog(null, "Seu novo limite é de R$" + c_brades + "\n");
            }
        }
        else if("Santander".equals(this.n_banco) || "santander".equals(this.n_banco)){
            double c_sant = 0.0;
            if(temp >= 5){
                c_sant = this.v_conta + ((this.v_conta * 20) / 100);
                JOptionPane.showMessageDialog(null, "Seu novo limite é de R$" + c_sant + "\n");
            }
            else {
                c_sant = this.v_conta + ((this.v_conta * 15) / 100);
                JOptionPane.showMessageDialog(null, "Seu novo limite é de R$" + c_sant + "\n");
            }
        }        
        
        
    }
    
    public static void main(String[] args) {
        Conta_Banco banco = new Conta_Banco();
        boolean cont = true;
        int opc = 0;
        do{
            opc = Integer.parseInt(JOptionPane.showInputDialog("Escolha a opção:\n"
                    + "1 - Saque\n"
                    + "2 - Depósito\n"
                    + "3 - Transferência\n"
                    + "4 - Solicitar novo cartão\n"
                    + "5 - Sair"));
            
            switch(opc){
                case 1:
                    banco.saque();
                break;
                case 2:
                    banco.depositar();
                break;
                case 3:
                    banco.transferir();
                break;
                case 4:
                    banco.cartao();
                break;
                case 5:
                    cont = false;
                    JOptionPane.showMessageDialog(null, "Sistema finalizado!!");
                break;
            }
            
        }while(cont);
        
    }
    
}
