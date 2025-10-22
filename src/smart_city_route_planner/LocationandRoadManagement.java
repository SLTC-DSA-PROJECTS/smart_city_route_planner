package smart_city_route_planner;


import java.util.Scanner;

public class LocationRoadManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Graphclass city = new Graphclass();

        while (true) {
            System.out.println("\n--- Location & Road Management ---");
            System.out.println("1. Add a new location");
            System.out.println("2. Remove a location");
            System.out.println("3. Add a road between locations");
            System.out.println("4. Remove a road");
            System.out.println("5. Display all connections");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter location name: ");
                    String loc = sc.nextLine().trim();
                    if (!loc.isEmpty()) {
                        city.addLocation(loc);
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
                    String loc1 = sc.nextLine().trim();
                    System.out.print("Enter second location: ");
                    String loc2 = sc.nextLine().trim();
                    city.addRoad(loc1, loc2);
                    break;

                case 4:
                    System.out.print("Enter first location: ");
                    String src = sc.nextLine().trim();
                    System.out.print("Enter second location: ");
                    String dest = sc.nextLine().trim();
                    city.removeRoad(src, dest);
                    break;

                case 5:
                    city.displayConnections();
                    break;

                case 6:
                    System.out.println("Exiting Location Manager...");
                    return;

                default:
                    System.out.println("Invalid choice! Please enter 1–6.");
            }
        }
    }
}