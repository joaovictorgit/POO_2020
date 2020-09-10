
package agenda02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;


class Fone{
    public String id;
    public String number;
    
    public Fone(String id, String number){
        this.id = id;
        this.number = number;
    }
    
    public String toString(){
        return id + ":" + number;
    }
    
    public boolean validar(String number){
        String validos = "0123456789().-";
        for(int i = 0; i < number.length();i++){
            char c = number.charAt(i);
            if(validos.indexOf(c) == -1){
                return false;
            }
        }
        return true;
    }
    
}


class Contato /*implements Comparable*/{
    private String name;
    private ArrayList<Fone> fones;
    
    public Contato(String name){
        this.name = name;
        fones = new ArrayList<>();
    }
    
    public boolean addFone(String id, String number){
        Fone fone = new Fone(id,number);
        if(fone.validar(number)){
            fones.add(new Fone(id,number));
            return true;
        }
        else{
            System.out.println("fail: fone invalido");
        }
        return false;
    }
    
    public void rmFone(int indice){
        for(int i = 0; i < fones.size(); i++){
            Fone fone = fones.get(i);
            if(i == indice){
                fones.remove(fones.get(i));
            }
        }
        //fones.remove(indice);
    }
    
    public String getName(){
        return this.name;
    }
    
    public int comparaContato(Contato um, Contato dois){
        return um.name.compareTo(dois.name);
    }
    /*
    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
    @Override
    public String toString(){
        String out =  "- " + this.name;
        if(fones.size() > 0)
            out += " ";
        for(int i = 0; i < fones.size(); i++){
            Fone fone = fones.get(i);
            out += "[" + i + ":"+ fone + "]";
        }return out;
    }
}

public class Agenda02 {
    ArrayList<Contato> contato;
    
    public Agenda02(){
        contato = new ArrayList<>();
    }
    
    private int findContato(String nome){
        Contato cont = getContato(nome);
        if(cont == null)
            return 0;
        return 1;
    }
    
    Contato getContato(String name){
        for(Contato cont: contato){
            if(cont.getName().equals(name)){
                return cont;
            }
        }
        return null;
    }
    public boolean initContato(String nome){
        Contato cont = getContato(nome);
        if(cont != null) return false;
        this.contato.add(new Contato(nome));
        return true;
    }
    
    public void addContato(String name, String id, String number){
        Contato cont = getContato(name);
        if(cont == null){
            cont = new Contato(name);
            cont.addFone(id, number);
        }
        else if(cont != null){
            cont.addFone(id, number);    
        }
        //Collections.sort(this.contato);
        Collections.sort(contato, (Contato one, Contato two) -> one.getName().compareTo(two.getName()));
    }
    
    public boolean rmContato(String name){
        Contato cont = getContato(name);
        if(cont == null){
            return false;
        }
        contato.remove(cont);
        return true;
    }
    
    public void rmFoneindice(String name, int indice){
        Contato contato = getContato(name);
        if(contato != null)
            contato.rmFone(indice);
    }
    
    public void search(String label){
        ArrayList<Contato> pesquisa = new ArrayList<>();
        for(Contato cont : contato){
            if(cont.toString().indexOf(label) != -1)
                pesquisa.add(cont);
        }
        
        for(int i = 0; i < pesquisa.size(); i++){
            System.out.println(pesquisa.get(i));
        }
        
        /*for(int i = 0; i < contato.size();i++){
            if(contato.get(i).getName().indexOf(label) != -1){
                System.out.println(contato.get(i));
            }
            
        }
        */
    }
    
    public ArrayList<Contato> getContatos(){
        return contato;
    }
    
    
    public void show(){
        for(int i = 0; i < contato.size(); i++){
            System.out.println(contato.get(i));
        }
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Agenda02 agenda = new Agenda02();
        
        while(true){
            String line = scan.nextLine();
            System.out.println("$" + line);
            String ui[] = line.split(" ");
            
            if(ui[0].equals("end")){
                break;
            }else if(ui[0].equals("add")){ 
                agenda.initContato(ui[1]);
                for(int i = 2; i < ui.length; i++){
                    String partes[] = ui[i].split(":");
                    agenda.addContato(ui[1], partes[0], partes[1]);
                }
            
            }else if(ui[0].equals("agenda")){ 
                agenda.show();
            }else if(ui[0].equals("rmContato")){ 
                agenda.rmContato(ui[1]);
            }else if(ui[0].equals("rmFone")){ 
                agenda.rmFoneindice(ui[1], Integer.parseInt(ui[2]));
            }else if(ui[0].equals("search")){ 
                agenda.search(ui[1]);
            }else{
                System.out.println("fail: comando inválido");
            }
            
        }
    }
    
}
