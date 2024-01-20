package Toy;

public class Toy {
    private Integer id;
    private String name;
    private Integer count;
    private Double probability;

    public Toy(Integer id, String name, Integer count, Double probability){
        this.id = id;
        this.name = name;
        this.count = count;
        this.probability = probability;
    }

    public Double get_probability(){
        return this.probability;
    }

    public void count_(){
        this.count--;
    }
}