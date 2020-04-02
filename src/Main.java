import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    private static Scanner scanner;
    private static List<Menu> listMenu;
    private static List<Order> listOrder;
    private static List<Costumer> listCostumer;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        listOrder = new ArrayList<>();
        listMenu = new ArrayList<>();
        listMenu.addAll(addMenu());
        listCostumer = new ArrayList<>();
        showMainMenu();

    }

    private static void showMainMenu() {
        boolean state = true;
        String option;
        while (state) {
            System.out.println("===== Wellcome at MyRestaurant =====");
            System.out.println("Please select one of the menus below with numbers");
            System.out.println("1. Input Order");
            System.out.println("2. Proses Order");
            System.out.println("3. Costumer Data");
            System.out.println("4. Exit");
            option = scanner.nextLine();
            if (!isNumeric(option)) {
                System.out.println("The input you entered is invalid, please fill in :\n");
                continue;
            }

            switch (Integer.parseInt(option)) {
                case 1:
                    inputOrder();
                    break;

                case 2:
                    break;

                case 3: costumerData();
                    break;

                case 4:
                    state = false;
                    break;

                default:
                    System.out.println("The menu you selected is not valid, please enter the input again\n");
                    break;

            }
        }
    }

    private static void inputOrder() {
        List<Menu> menuList = new ArrayList<>();
        boolean state = true;
        String option;
        int qty;

        while (state) {
            System.out.println("===== Input Order Menu =====");
            showMenu();
            System.out.println("Please select one of the menus below with numbers");
            option = scanner.nextLine();
            if (!isNumeric(option)) {
                System.out.println("The input you entered is invalid, please fill in :\n");
                continue;
            }

            System.out.println("Insert quantity = ");
            qty = scanner.nextInt();
            switch (Integer.parseInt(option)) {
                case 1:
                    menuList.add(new Menu(listMenu.get(0).getNameFood(), listMenu.get(0).getPrice(), qty));
                    break;

                case 2:
                    menuList.add(new Menu(listMenu.get(1).getNameFood(), listMenu.get(1).getPrice(), qty));
                    break;

                case 3:
                    menuList.add(new Menu(listMenu.get(2).getNameFood(), listMenu.get(2).getPrice(), qty));
                    break;

                case 4:
                    menuList.add(new Menu(listMenu.get(3).getNameFood(), listMenu.get(3).getPrice(), qty));
                    break;

                case 5:
                    menuList.add(new Menu(listMenu.get(4).getNameFood(), listMenu.get(4).getPrice(), qty));
                    break;

                case 6:
                    state = false;
                    break;

                default:
                    System.out.println("The menu you selected is not valid, please enter the input again\n");
                    break;

            }

            System.out.println("Do you want to add a new menu again ? ");
            boolean answer = askMember();
            if (!answer) {
                System.out.println("There is a My Restaurant member card ?");
                boolean isMember = askMember();
                listOrder.add(new Order(menuList, isMember));
                state = false;
            }
        }
    }

    private static void costumerData() {
        boolean state = true;
        String option;

        while (state) {
            System.out.println("===== Costumer Data Menu =====");
            System.out.println("Please select one of the menus below with numbers");
            System.out.println("1. Add Costumer");
            System.out.println("2. Edit Costumer");
            System.out.println("3. Main Menu");


            option = scanner.nextLine();
            if (!isNumeric(option)) {
                System.out.println("The input you entered is invalid, please fill in :\n");
                continue;
            }

            switch (Integer.parseInt(option)) {
                case 1:
                    addCostumer();
                    break;

                case 2:
                    readDataFromFile();
                    break;

                case 3:
                    state = false;
                    break;

                default:
                    System.out.println("The menu you selected is not valid, please enter the input again\n");
                    break;

            }


        }
    }

    private static void addCostumer() {
        System.out.println("Enter ID Costumer");
        System.out.println("Enter 5 Digit Number : ");
        String nik = validateNik();

        System.out.println("Enter Name");
        System.out.println("Enter character without symbol and number : ");
        String name = validateName();

        listCostumer.add(new Costumer(nik, name));
        saveDataToFile(listCostumer);
        System.out.println("Creating costumer has been successful \n");
    }

    private static void saveDataToFile(List<Costumer> costumerList) {
        try {
            FileOutputStream fw = new FileOutputStream("F:\\NonAndroidProject\\HardCoreBegadang\\src\\Costumer.txt");
            BufferedOutputStream bufOutStream = new BufferedOutputStream(fw);
            for (Costumer costumer : costumerList) {
                String dataSave = costumer.getId() + "," + costumer.getName() + "," + costumer.getBonusPoint() +",";
                byte[] bytes = dataSave.getBytes();
                bufOutStream.write(bytes);
                bufOutStream.flush();
            }
            bufOutStream.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed Add Costumer Data");
        }
    }

    private static void readDataFromFile() {
        List<Costumer> list = new ArrayList<>();
        String name;
        String id;
        int point;
        try {
            FileInputStream fis = new FileInputStream("F:\\NonAndroidProject\\HardCoreBegadang\\src\\Costumer.txt");
            BufferedInputStream bufferedReader= new BufferedInputStream(fis);
            int byteStream;
            StringBuilder sperator = new StringBuilder();
            while ((byteStream = bufferedReader.read()) != -1) {
                sperator.append((char) byteStream);
            }

            List<String> arrayBuffer = new ArrayList<>(Arrays.asList(sperator.toString().split(",")));


            for (int i = 0; i < arrayBuffer.size(); i++) {

                System.out.println(i);
                if (i == 0) {
                    id = arrayBuffer.get(i).trim();
                    name = arrayBuffer.get(i + 1).trim();
                    point = Integer.parseInt(arrayBuffer.get(i + 2).trim());
                    list.add(new Costumer(id, name, point));
                    i+= 2;
                } else {
                    if (i % 3 == 0) {
                        id = arrayBuffer.get(i).trim();
                        name = arrayBuffer.get(i + 1).trim();
                        point = Integer.parseInt(arrayBuffer.get(i + 2).trim());
                        list.add(new Costumer(id, name, point));
                        i+= 2;
                    }
                }
                if ((i + 3) >= arrayBuffer.size()) {
                    break;
                }

            }

            for (Costumer costumer : list) {
                System.out.println(costumer.getName() + "\t" + costumer.getId() + "\t" + costumer.getBonusPoint());
            }

        } catch (IOException e) {
            System.out.println("Failed Read Costumer Data");
        }
    }

    private static String validateNik() {
        String checkedNik = scanner.nextLine();
        boolean state = true;

        while (state) {
            if (checkedNik.trim().length() == 5 && isNumeric(checkedNik)) {
                state = false;
            } else {
                System.out.println("The input you entered is invalid, please fill in :");
                checkedNik = scanner.nextLine().trim();
            }
        }

        return checkedNik;
    }

    private static String validateName() {
        boolean state = true;
        String checkedName = scanner.nextLine().trim();
        while (state) {
            if (checkedName.length() < 1 || checkedName.isBlank()) {
                System.out.println("The input you entered is invalid, please fill in :");
                checkedName = scanner.nextLine().trim();
            } else {
                state = false;
            }
        }

        return capitalizeWord(checkedName);
    }

    private static boolean askMember() {
        String checkedGender = scanner.nextLine().trim().toLowerCase();
        while (true) {
            if ((checkedGender.equals("yes"))) {
                return true;
            } else if ((checkedGender.equals("no"))) {
                return false;
            } else {
                System.out.println("The input you entered is invalid, please fill in :");
                checkedGender = scanner.nextLine().trim().toLowerCase();
            }
        }
    }

    private static void showMenu() {
        for (int i = 0; i < listMenu.size(); i++) {
            System.out.println(i + 1 + ". " + listMenu.get(i).getNameFood() + "\t" + listMenu.get(i).getPrice());
        }
        System.out.println("6. Exit");
    }

    private static String capitalizeWord(String word) {
        String[] words = word.split("\\s");
        StringBuilder capitalizeWord = new StringBuilder();
        for (String w : words) {
            String firstWord = w.substring(0, 1);
            String afterFirst = w.substring(1);
            capitalizeWord.append(firstWord.toUpperCase()).append(afterFirst).append(" ");
        }
        return capitalizeWord.toString().trim();
    }

    private static boolean isNumeric(final String number) {

        // null or empty
        if (number == null || number.length() == 0) {
            return false;
        }

        for (char c : number.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private static List<Menu> addMenu() {
        ArrayList<Menu> menuList = new ArrayList<>();
        menuList.add(new Menu("Fried Rice", 15000));
        menuList.add(new Menu("Mercon Chicken", 25000));
        menuList.add(new Menu("Sate", 35000));
        menuList.add(new Menu("Indomie", 25000));
        menuList.add(new Menu("Tea", 10000));

        return menuList;
    }
}
