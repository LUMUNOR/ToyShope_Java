package Market;

import Toy.Toy;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Market {
    private List<Toy> listToy;
    private Scanner scanner;
    
    public Market(){
        listToy = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addToy(){
        System.out.println("Введите название игрушки:");
        String name = scanner.nextLine();
        System.out.println("Ведите количество данной игрушки");
        Integer count = Integer.parseInt(scanner.nextLine());
        System.out.println("Ведите вероятность выпадения данной игрушки");
        Double probabilit = Double.parseDouble(scanner.nextLine());
        Integer id = this.listToy.size()+1;
        Toy newToy = new Toy(id, name, count, probabilit);
        listToy.add(newToy);
    }
    
    /**
     * Розыгрышь
     */
    public void draw(){

    }

    public void issuance (Toy toy){

    }
}