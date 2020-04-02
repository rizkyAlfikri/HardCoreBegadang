public class Costumer {
    private String id;
    private String name;
    private int bonusPoint;


    public Costumer(String id, String name) {
        this.id = id;
        this.name = name;
        this.bonusPoint = 0;
    }

    public Costumer(String id, String name, int bonusPoint) {
        this.id = id;
        this.name = name;
        this.bonusPoint = bonusPoint;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBonusPoint() {
        return bonusPoint;
    }

    public void setBonusPoint(int bonusPoint) {
        this.bonusPoint = bonusPoint;
    }
}
