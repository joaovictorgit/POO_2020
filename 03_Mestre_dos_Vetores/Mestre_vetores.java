package mestre_vetores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Mestre_vetores {
    static Scanner scan = new Scanner(System.in);
    
    
    
    public static void main(String[] args) {
        
    ArrayList<Integer> vet = new ArrayList<Integer>();

		while(true){
			String line = scan.nextLine();
			System.out.println("$" + line);
			String ui[] = line.split(" ");
			String cmd = ui[0];

			if(line.equals("end")){
				break;
			}else if(cmd.equals("show")){
				System.out.print("[ ");
				for(Integer value : vet)
					System.out.print(value + " ");
				System.out.print("]\n");
			}else if(cmd.equals("rshow")){
                            Collections.reverse(vet);
                            System.out.println(vet);
                            Collections.reverse(vet);
			}else if(cmd.equals("add")){
				for(int i = 1; i < ui.length; i++)
					vet.add(Integer.parseInt(ui[i]));
			}else if(cmd.equals("find")){
                                
                              int find = vet.indexOf(Integer.parseInt(ui[1]));
                              System.out.println(find);
                                                       
			}else if(cmd.equals("ins")){
                            int indice = Integer.parseInt(ui[1]);
                            int value = Integer.parseInt(ui[2]);
                            if(indice <= 0){
                                System.out.println("faii " + indice + " > 0");
                            }
                            else if(indice > ui.length){
                                vet.add(value);
                            }
                            else if(indice > 0 && indice < ui.length){
                                vet.add(indice, value);
                            }
                            
			}else if(cmd.equals("rmi")){
                            int indice = Integer.parseInt(ui[1]);
                            if (indice < 0){
                                System.out.println("fail");
                            }else{
                                vet.remove(indice);
                            }
                            
			}else if(cmd.equals("rma")){
                            vet.removeAll(Collections.singleton(Integer.parseInt(ui[1])));
                            
			}else if(cmd.equals("get")){
                            int indice = Integer.parseInt(ui[1]);
                            int _get = vet.get(indice);
                            System.out.print(_get + "\n");
                            
                        }else if(cmd.equals("set")){
                            int indice = Integer.parseInt(ui[1]);
                            int valor = Integer.parseInt(ui[2]);
                            vet.set(indice, valor);
                            
                        }else {
				System.out.print("fail: command not found\n");
			}
		}
	}
    
}
