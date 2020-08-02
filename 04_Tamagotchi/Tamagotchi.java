package tamagotchi;
import java.util.Scanner;

class Pet {
    int energia_max = 20;
    int saciedade_max = 10;
    int limpeza_max = 15;
    private int energia;
    private int saciedade;
    private int limpeza;
    int diamantes;
    int idade;
    boolean vivo = true;
    

    public Pet(int energia, int saciedade, int limpeza){
        this.setEnergia(energia);
        this.setSaciedade(saciedade);
        this.setLimpeza(limpeza);
    }
    
    public Integer getEnergia(){
        return this.energia;
    }
    public void setEnergia(int energia){
        this.energia = energia;
    }
    public Integer getSaciedade(){
        return this.saciedade;
    }
    public void setSaciedade(int saciedade){
        this.saciedade = saciedade;
    }
    public Integer getLimpeza(){
        return this.limpeza;
    }
    public void setLimpeza(int limpeza){
        this.limpeza = limpeza;
    }
    
}


public class Tamagotchi {
    Pet pet;
    
    void init(Pet pet){
        if(this.pet == null){
            this.pet = pet;
        }
        else{
            System.out.println("Ja existe um tamagotchi");
        }
    }
    void show(){
        if(this.pet == null){
            System.out.println("nenhum tamagotchi vivo");
        }
        else if(this.pet.getEnergia() <= 0 || this.pet.getSaciedade() <= 0 || this.pet.getLimpeza() <= 0){
            this.pet.vivo = false;
            System.out.println("Tamagotchi morreu\n");
        }
        else{
            System.out.print("E: " + this.pet.getEnergia()+ "/"+this.pet.energia_max);
            System.out.print(", S: "+ this.pet.getSaciedade()+ "/"+ this.pet.saciedade_max);
            System.out.print(", L: "+ this.pet.getLimpeza()+ "/"+ this.pet.limpeza_max);
            System.out.print(", D: " + this.pet.diamantes + ", I: " + this.pet.idade + "\n");
        }   
    }
    
    void play(){
        if(this.pet == null){
            System.out.println("Nenhum tamagotchi foi criado");
        }
        else if(this.pet.getEnergia() <= 0){
            this.pet.vivo = false;
            System.out.println("Tamagotchi morreu de fraqueza\n");
        } 
        else if(this.pet.getSaciedade() <= 0){
            this.pet.vivo = false;
            System.out.println("Tamagotchi morreu de fome\n");
        }
        else if(this.pet.getLimpeza() <= 0){
            this.pet.vivo = false;
            System.out.println("Tamagotchi morreu de sujeiroa\n");
        }
        else{
            this.pet.setEnergia((this.pet.getEnergia() - 2));
            this.pet.setSaciedade(this.pet.getSaciedade() - 1);
            this.pet.setLimpeza(this.pet.getLimpeza() - 3);
            this.pet.diamantes += 1;
            this.pet.idade += 1;
        }

    }
    void eat(){
        if(this.pet == null){
            System.out.println("Nenhum tamagotchi foi criado");
        }
        else if(this.pet.getEnergia() <= 0 || this.pet.getSaciedade() <= 0 || this.pet.getLimpeza() <= 0){
            this.pet.vivo = false;
            System.out.println("Tamagotchi morreu\n");
        }
        else{
            this.pet.setEnergia((this.pet.getEnergia() - 1));
            if(this.pet.getSaciedade() +4 > this.pet.saciedade_max)
                this.pet.setSaciedade(this.pet.saciedade_max);
            else 
                this.pet.setSaciedade(this.pet.getSaciedade() + 4);
            this.pet.setLimpeza(this.pet.getLimpeza() - 2);
            this.pet.diamantes += 0;
            this.pet.idade += 1;
        }
        
    }
    
    void sleep(){
        int v_energia = this.pet.energia_max - this.pet.getEnergia();
        if(this.pet == null){
            System.out.println("Nenhum tamagotchi foi criado");
        }
        else if(this.pet.getEnergia() <= 0 || this.pet.getSaciedade() <= 0 || this.pet.getLimpeza() <= 0){
            this.pet.vivo = false;
            System.out.println("Tamagotchi morreu\n");
        }
        else{
            if(v_energia < 5){
                System.out.println("Tamagotchi nao esta com sono");
            }
            else if(v_energia >= 5){
                int valor = this.pet.energia_max - this.pet.getEnergia();
                this.pet.setEnergia(this.pet.getEnergia()+valor);
                this.pet.setSaciedade(this.pet.getSaciedade()-1);
                this.pet.idade += valor;
            }
        }
    }
    void clean(){
        if(this.pet == null){
            System.out.println("Nenhum tamagotchi foi criado");
        }
        else if(this.pet.getEnergia() <= 0 || this.pet.getSaciedade() <= 0 || this.pet.getLimpeza() <= 0){
            this.pet.vivo = false;
            System.out.println("Tamagotchi morreu\n");
        } 
        else{
            int valor = this.pet.limpeza_max - this.pet.getLimpeza();
            this.pet.setEnergia(this.pet.getEnergia() - 3);
            this.pet.setSaciedade(this.pet.getSaciedade() - 1);
            this.pet.setLimpeza(this.pet.getLimpeza() + valor);
            this.pet.idade += 2;
        }
    }
    
    
    
    public static void main(String[] args) {
        Tamagotchi tama = new Tamagotchi();
        Scanner input = new Scanner(System.in);
        
        while(true){
         String line = input.nextLine();
		 System.out.println("$" + line);
         String entrada[] = line.split(" ");
         
         if(entrada[0].equals("end")){
             break;
         }
         else if(entrada[0].equals("init")){
             Pet pet = new Pet(Integer.parseInt(entrada[1]),Integer.parseInt(entrada[2]), Integer.parseInt(entrada[3]));
             tama.init(pet);
         }
         else if(entrada[0].equals("show")){
             tama.show();
         }
         else if(entrada[0].equals("play")){
             tama.play();
         }
         else if(entrada[0].equals("eat")){
             tama.eat();
         }
         else if(entrada[0].equals("sleep")){
             tama.sleep();
         }
         else if(entrada[0].equals("clean")){
             tama.clean();
         }
         else{
             System.out.println("Comando inválido");
         }
        }
    }

   
    
}

