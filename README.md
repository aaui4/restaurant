res















import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestaurantApp {

    static Scanner input = new Scanner(System.in);

    // Class to store order data
    static class OrderItem {
        String name;
        String size;
        List<String> additions;
        int quantity;
        int price;

        public OrderItem(String name, String size, List<String> additions, int quantity, int price) {
            this.name = name;
            this.size = size;
            this.additions = additions;
            this.quantity = quantity;
            this.price = price;
        }

        public int getTotal() {
            return price * quantity;
        }

        @Override
        public String toString() {
            String add = additions.isEmpty() ? "No additions" : String.join(" + ", additions);
            return name + " (" + size + ") + " + add + " ×" + quantity + " = " + getTotal() + " DZD";
        }
    }

    // Food menu
    static String[] menuItems = {
            "Chicken Shawarma", "Beef Shawarma", "Chicken Tacos", "Beef Tacos"
    };
    static int[] prices = {300, 350, 400, 450};

    static List<OrderItem> orderList = new ArrayList<>();

    public static void main(String[] args) {
        mainMenu();
    }

    // ============================
    //  Main Menu
    // ============================
    public static void mainMenu() {
        while (true) {
            System.out.println("=============================");
            System.out.println("Welcome to Al-Nakheel Restaurant");
            System.out.println("=============================");
            System.out.println("1. View Menu");
            System.out.println("2. Edit Current Order");
            System.out.println("3. Confirm Order & Print Receipt");
            System.out.println("4. Exit");
            System.out.println("---------------------------------");
            System.out.print("Please enter your choice: ");
            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    showMenu();
                    break;
                case "2":
                    editOrder();
                    break;
                case "3":
                    confirmOrder();
                    break;
                case "4":
                    System.out.println("Thank you for visiting Al-Nakheel Restaurant ");
                    return;
                default:
                    System.out.println(" Invalid option. Please try again.");
            }
        }
    }

    // ============================
    //  Show Menu (with categories)
    // ============================
    public static void showMenu() {
        System.out.println("\n==============================");
        System.out.println("             MENU");
        System.out.println("==============================");

        System.out.println("--------------------------------------------------");
        System.out.println("                 SHAWARMA");
        System.out.println("--------------------------------------------------");
        System.out.println("1. Chicken Shawarma - 300 DZD");
        System.out.println("2. Beef Shawarma - 350 DZD");

        System.out.println("--------------------------------------------------");
        System.out.println("                   TACOS");
        System.out.println("--------------------------------------------------");
        System.out.println("3. Chicken Tacos - 400 DZD");
        System.out.println("4. Beef Tacos - 450 DZD");

        System.out.println("--------------------------------------------------");
        System.out.println("0. Return to Main Menu");
        System.out.println("--------------------------------------------------");
        System.out.print("Select meal number: ");

        int choice = Integer.parseInt(input.nextLine());

        if (choice == 0) return;
        if (choice < 1 || choice > menuItems.length) {
            System.out.println(" Invalid number.");
            return;
        }

addToOrder(choice - 1);
    }

    // ============================
    // ➕ Add New Meal
    // ============================
    public static void addToOrder(int index) {
        String name = menuItems[index];
        int basePrice = prices[index];

        System.out.print("Enter quantity: ");
        int qty = Integer.parseInt(input.nextLine());

        System.out.println("Choose size:");
        System.out.println("1. Small\n2. Medium\n3. Large");
        System.out.print("Your choice: ");
        String sizeChoice = input.nextLine();
        String size = "Small";
        int price = basePrice;

        switch (sizeChoice) {
            case "2":
                size = "Medium";
                price += 50;
                break;
            case "3":
                size = "Large";
                price += 100;
                break;
            default:
                size = "Small";
        }

        System.out.println("Would you like to add:");
        System.out.println("1. Spicy Sauce");
        System.out.println("2. Mayonnaise");
        System.out.println("3. No additions");
        System.out.print("(You can type two numbers separated by space): ");
        String addChoice = input.nextLine();

        List<String> additions = new ArrayList<>();
        if (addChoice.contains("1")) additions.add("Spicy Sauce");
        if (addChoice.contains("2")) additions.add("Mayonnaise");

        OrderItem item = new OrderItem(name, size, additions, qty, price);
        orderList.add(item);

        System.out.println("\"" + name + " (" + size + ")\" added successfully (x" + qty + ").");
        System.out.println("---------------------------------");

        System.out.println("Would you like to:");
        System.out.println("1. Return to Menu");
        System.out.println("2. Return to Main Menu");
        System.out.print("Your choice: ");
        String next = input.nextLine();
        if (next.equals("1")) showMenu();
    }

    // ============================
    // 🧾 Edit Order
    // ============================
    public static void editOrder() {
        if (orderList.isEmpty()) {
            System.out.println(" No current order.");
            return;
        }

        showCurrentOrder();

        System.out.println("Options:");
        System.out.println("1. Delete an Item");
        System.out.println("2. Edit Quantity");
        System.out.println("3. Back");
        System.out.print("Your choice: ");
        String choice = input.nextLine();

        switch (choice) {
            case "1":
                System.out.print("Enter the number of the item to delete: ");
                int idx = Integer.parseInt(input.nextLine()) - 1;
                if (idx >= 0 && idx < orderList.size()) {
                    System.out.print("Confirm deletion? (1. Yes / 2. No): ");
                    String confirm = input.nextLine();
                    if (confirm.equals("1")) {
                        System.out.println(" \"" + orderList.get(idx).name + "\" deleted successfully.");
                        orderList.remove(idx);
                    }
                }
                break;
            case "2":
                System.out.print("Enter the number of the item to edit: ");
                int id = Integer.parseInt(input.nextLine()) - 1;
                if (id >= 0 && id < orderList.size()) {
                    System.out.print("Enter new quantity: ");
                    int newQty = Integer.parseInt(input.nextLine());
                    orderList.get(id).quantity = newQty;
                    System.out.println(" Quantity updated successfully.");
                }
                break;
            default:
                return;
        }
    }

    // ============================
    //  Confirm Order & Print Receipt
    // ============================
    public static void confirmOrder() {
        if (orderList.isEmpty()) {
            System.out.println(" No order to confirm.");
            return;
        }

        System.out.println("\n======= Al-Nakheel Restaurant=======");
        System.out.println("Order No: 001");
        System.out.println("----------------------------");
        showCurrentOrder();
        int total = getTotal();
        System.out.println("----------------------------");
        System.out.println("Total Amount: " + total + " DZD");
        System.out.println("----------------------------");
        System.out.print("Do you want to confirm the order? (1. Yes / 2. No): ");
        String confirm = input.nextLine();

        if (confirm.equals("1")) {
            System.out.println("\n Order confirmed.");
            System.out.println("Thank you for your order ");
            orderList.clear();
        }
    }

    // ============================
    //  Show Current Order
    // ============================
    public static void showCurrentOrder() {
        int i = 1;
        int total = 0;
        for (OrderItem item : orderList) {
            System.out.println(i + ". " + item);
            total += item.getTotal();
            i++;
        }
        System.out.println("----------------------------");
        System.out.println("Current Total: " + total + " DZD");
    }

    // ============================
    //  Calculate Total
    // ============================
    public static int getTotal() {
        int total = 0;
        for (OrderItem item : orderList) {
            total += item.getTotal();
        }
        return total;
    }
}
