
package lapiseira;

import java.util.Scanner;

class Grafite{
    public float calibre;
    public String dureza;
    public int tamanho;
    
    public Grafite(float calibre, String dureza, int tamanho){
        this.calibre = calibre;
        this.dureza = dureza;
        this.tamanho = tamanho;
    }
    
    @Override
    public String toString(){
        return "grafite: [" + this.calibre + ":" + this.dureza + ":" + this.tamanho + "]";
    }
}

public class Lapiseira {
    public float calibre;
    Grafite grafite;
    
    public Lapiseira(float calibre){
        this.calibre = calibre;
        this.grafite = new Grafite(0, "", 0);
    }
    
    public boolean inserir(Grafite gra1){
        if(this.grafite.calibre != 0){
            System.out.println("fail: já existe um grafite");
            return false;
        }
        else if(gra1.calibre != this.calibre){
            System.out.println("fail: calibre incompatível");
            return false;
        }
        this.grafite = new Grafite(gra1.calibre, gra1.dureza, gra1.tamanho);
        return true;
    }
    
    public boolean remover(){
        if(this.grafite.calibre == 0){
            System.out.println("fail: não existe grafite");
            return false;
        }
        this.grafite = new Grafite(0, "", 0);
        return true;
    }
    
    public boolean escrever(int pressao){
        int novo_pressao = 2* pressao;
        this.grafite.tamanho -= novo_pressao;
        if(this.grafite.calibre == 0){
            System.out.println("fail: nao existe grafite");
            return false;
        }
        if(this.grafite.tamanho < 0){
            System.out.println("fail: folha ficou pela metade");
            
        }
        if(this.grafite.tamanho == 0 || this.grafite.tamanho < 0){
            remover();
            System.out.println("fail: grafite acabou");
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        String out = "calibre: " + calibre;
        if(this.grafite.calibre == 0)
            out += ", grafite: null";
        else
            out += ", " + this.grafite;
        return out;
    }
    public static void main(String[] args) {
        Lapiseira l = new Lapiseira(0);
        Scanner scan = new Scanner(System.in);
        
        while(true){
            String line = scan.nextLine();
            System.out.println("$" + line);
            String ui[] = line.split(" ");
            
            if(ui[0].equals("end")){
                break;
            }else if(ui[0].equals("init")){
                l = new Lapiseira(Float.parseFloat(ui[1]));
            }else if(ui[0].equals("inserir")){
                Grafite insert = new Grafite(Float.parseFloat(ui[1]), ui[2], Integer.parseInt(ui[3]));
                l.inserir(insert);
            }else if(ui[0].equals("remover")){
                l.remover();
            }else if(ui[0].equals("write")){
                l.escrever(Integer.parseInt(ui[1]));
            }else if(ui[0].equals("show")){
                System.out.println(l);
            }else{
                System.out.println("fail: comando inválido!");
            }
        }
    }
    
}
