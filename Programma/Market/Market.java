package Market;

import Toy.Toy;
import Filewriter.Filewriter;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class Market {
    private List<Toy> listToy;
    private Scanner scanner;
    private Filewriter filewriter;
    private ArrayList<Integer> contToys;
    private ArrayList<Double> probabilits;
    private ArrayDeque<Toy> queue;
    private ArrayList<Double> kef;

    private Integer countDraw;

    public Market(){
        listToy = new ArrayList<>();
        scanner = new Scanner(System.in);
        filewriter = new Filewriter();
        contToys = new ArrayList<Integer>();
        probabilits = new ArrayList<Double>();
        queue = new ArrayDeque<>();
        kef = new ArrayList<Double>();
    }

    public void addToy(){
        System.out.println("Введите название игрушки:");
        String name = scanner.nextLine();
        System.out.println("Ведите количество данной игрушки");
        Integer count = Integer.parseInt(scanner.nextLine());
        Integer id = this.listToy.size()+1;
        Toy newToy = new Toy(id, name, count);
        listToy.add(newToy);
        distributor();
        contToysMaker();
    }

    /**
     * Метод распределения вероятностей при добавлении новой игрушки
     */
    private void distributor(){
        if (this.listToy.size() == 1) {
            this.probabilits.add(1.0);
            this.kef.add(1.0);
        }
        if (this.listToy.size() == 2) {
            this.probabilits.set(0,0.5);
            this.probabilits.add(0.5);
            this.kef.add(1.0);
        }
        if (this.listToy.size() > 2) {
            this.kef.add(1.0);
            this.probabilits.add(1.0/this.listToy.size());
            for (int i=0; i < this.probabilits.size(); i++){
                this.probabilits.set(i,((1.0/this.listToy.size())*this.kef.get(i)));
            } 
        }
        setProbabilityToys();
    }

    /**
     * Метод заполняющий массив contToys, для эмулирования розыгрыша игрушек с заданными вероятностями!
     */
    private void contToysMaker(){
        this.contToys.clear();
        Double minVer = this.probabilits.get(0);
        for (int i=1; i<this.probabilits.size(); i++){
            if (this.probabilits.get(i) < minVer) minVer = this.probabilits.get(i);  
            }

        Double kef = 1/minVer;
        for (int i=0; i<this.probabilits.size(); i++){
            int count = (int)(this.probabilits.get(i)*kef)+1;
            this.contToys.add(count);
            }
        int n = 0;
        for (Integer integer : contToys) {
            n += integer;
        }
        this.countDraw = n;
    }

    /**
     * Ручной Метод задания конкретной вероятности конкретной игрушке
     */
    public void setProbability(){
        System.out.println("Введите id игрушки, вероятность выпадения которой, хотите задать:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите вероятность которую хотите задать:");
        double ver = Double.parseDouble(scanner.nextLine());
        double oldver = this.probabilits.get(id);
        this.probabilits.set(id, ver);
        this.kef.set(id,(1/(oldver/ver)));
        double delta = (ver - oldver)/(this.probabilits.size()-1);
        for (int i=0; i<this.probabilits.size(); i++){
            if (i == id) continue;
            else this.probabilits.set(i,(this.probabilits.get(i)-delta));
        }
        setProbabilityToys();
        contToysMaker();

    }
    
    //Метод присвоения вероятностей внутрь игрушкам
    private void setProbabilityToys(){
        for (int i=0; i < this.listToy.size(); i++){
            this.listToy.get(i).set_probability(this.probabilits.get(i));
        }
    }
    
    //Розыгрыш
    public void drawToys(){
        Toy prize = draw();
        //prize.set_one_count();
        this.queue.add(prize);

    }
    
    /**
     * Выпадение игрушки
     */
    private Toy draw(){
        if (this.countDraw <= 0) contToysMaker();
        System.out.println("ok1");
        int index = generIndex();
        System.out.println("Ok2");
        Toy result = this.listToy.get(index);
        this.contToys.set(index,this.contToys.get(index)-1);
        this.listToy.get(index).count_();
        if (this.listToy.get(index).get_count()==0){
            this.listToy.remove(index);
            this.contToys.remove(index);
            this.probabilits.remove(index);
            this.kef.remove(index);
            distributor2();
            setProbabilityToys();
            contToysMaker();
        }
        this.countDraw--;
        return result;
    }

    private int generIndex(){
        boolean flag = true;
        int index = 0;
        while (flag) {
            index = (int) (Math.random() * (this.listToy.size()));
            if ((this.contToys.get(index) != 0)&&(this.listToy.get(index).get_count()>0)) flag = false; 
        }
        return index;
    }

    private void distributor2(){
        for (int i=0; i < this.probabilits.size(); i++){
                this.probabilits.set(i,((1.0/this.listToy.size())*this.kef.get(i)));
            } 
    }

    /**
     * Метод "выдачи" (сохранение в файл) игрушки 
     */
    public void issuance(){
        StringBuilder sb = new StringBuilder();
        while (queue.peekFirst()!=null) {
            sb.append(queue.pollFirst().get_name()+'\n');
        }
        filewriter.work(sb);
    }

    public void printProb(){
        for (Double ver : this.probabilits) {
            System.out.print(ver + " ");
        }
        System.out.println("\n");
    }

    public void printCount(){
        for (int count : this.contToys) {
            System.out.print(count + " ");
        }
        System.out.println("\n");
    }

    public void printCountToys(){
        for (Toy toy : this.listToy) {
            System.out.print(toy.get_count() + " ");
        }
        System.out.println("\n");
    }
}
