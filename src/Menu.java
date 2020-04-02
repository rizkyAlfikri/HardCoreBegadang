public class Menu {
    private String nameFood;
    private int price;
    private int quantity;

    public Menu(String nameFood, int price) {
        this.nameFood = nameFood;
        this.price = price;
    }


    public Menu(String nameFood, int price, int quantity) {
        this.nameFood = nameFood;
        this.price = price;
        this.quantity = quantity;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int calculatePrice() {
        return quantity * price;
    }

}
