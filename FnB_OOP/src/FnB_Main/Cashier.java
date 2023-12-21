package FnB_Main;

public class Cashier {
    private int id;
    private String name;

    // Constructor
    public Cashier(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter dan Setter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
