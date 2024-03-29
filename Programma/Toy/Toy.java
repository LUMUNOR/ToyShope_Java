package Toy;

public class Toy {
    private Integer id;
    private String name;
    private Integer count;
    private Double probability;

    public Toy(Integer id, String name, Integer count){
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public Double get_probability(){
        return this.probability;
    }

    public String get_name(){
        return this.name;
    }

    public Integer get_count(){
        return this.count;
    }
   
    public void set_one_count(){
        this.count = 1;
    }
    
    public void set_probability(Double ver){
        this.probability = ver;
    }

    public void count_(){
        this.count--;
    }
}