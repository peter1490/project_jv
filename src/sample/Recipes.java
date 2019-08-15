package sample;


import java.util.List;

public class Recipes {
    private int id;
    private String name;

    public Recipes() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Foods> getFoods(){
        return Controller.getFoodsForRecipe(this.id);
    }
}
