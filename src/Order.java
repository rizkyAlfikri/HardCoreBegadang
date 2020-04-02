import java.util.List;

public class Order {
    private Costumer costumer;
    private List<Menu> menuList;
    private double totalPrice;
    private boolean isMember;

    public Order(Costumer costumer) {
        this.costumer = costumer;
    }

    public Order(List<Menu> menuList, boolean isMember) {
        this.costumer = null;
        this.menuList = menuList;
        this.isMember = isMember;
        int subPrice = 0;
        for (Menu menu : menuList) {
            subPrice += menu.calculatePrice();
        }

        this.totalPrice = subPrice;
    }

    public Order() {
    }



    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        int subPrice = 0;
        for (Menu menu : menuList) {
            subPrice += menu.calculatePrice();
        }

        this.totalPrice = subPrice;
    }
}
