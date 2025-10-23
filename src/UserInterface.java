import java.util.Scanner;

public class UserInterface  {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Graph city = new Graph(false,false);
        AVLTree tree = new AVLTree();

        while (true) {
            System.out.println("\n--- Smart City Route Planner ---");
            System.out.println("1. Add a new location");
            System.out.println("2. Remove a location");
            System.out.println("3. Add a road between locations");
            System.out.println("4. Remove a road");
            System.out.println("5. Display all connections");
            System.out.println("6. Display all locations (AVL Tree)");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number between 1–8.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter location name: ");
                    String loc = sc.nextLine().trim();
                    if (!loc.isEmpty()) {
                        city.addLocation(loc);
                        tree.insert(loc);
                    } else {
                        System.out.println("Location name cannot be empty!");
                    }
                    break;

                case 2:
                    System.out.print("Enter location to remove: ");
                    String removeLoc = sc.nextLine().trim();
                    city.removeLocation(removeLoc);
                    break;

                case 3:
                    System.out.print("Enter first location: ");
                    String src = sc.nextLine().trim();
                    System.out.print("Enter second location: ");
                    String dest = sc.nextLine().trim();
                    city.addRoad(src, dest);
                    break;

                case 4:
                    System.out.print("Enter first location: ");
                    String r1 = sc.nextLine().trim();
                    System.out.print("Enter second location: ");
                    String r2 = sc.nextLine().trim();
                    city.removeRoad(r1, r2);
                    break;

                case 5:
                    city.printAllConnections();
                    break;

                case 6:
                    tree.display();
                    break;

                case 7:
                   System.out.println("Thank you for using Smart City Route Planner!");
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}