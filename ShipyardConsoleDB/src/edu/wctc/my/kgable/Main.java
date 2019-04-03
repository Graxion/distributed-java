package edu.wctc.my.kgable;

import java.sql.*;
import java.util.Scanner;

public class Main
{
    private static final String DB_URL = "jdbc:derby:ShipyardDB";
    private static Scanner scanner = new Scanner(System.in);
    private static String input;
    private static String outputFormat = "\n%7d | %-18s | %-13s | %-10d";
    private static String outputFormatQuantity = "\n%7d | %-18s | %-13s | %-8d | %-10d";

    public static void main(String[] args)
    {
        DatabaseBuilder.buildDB(DB_URL);

        mainMenu();
    }

    private static void mainMenu()
    {
        System.out.println("\nShipyardDB Cart Test");
        System.out.println("====================");
        System.out.println("1) View Catalog");
        System.out.println("2) Add Item to Cart");
        System.out.println("3) View Cart");
        System.out.println("4) Quit");
        System.out.print("\n>>> ");

        input = scanner.nextLine();

        try
        {
            switch (Integer.parseInt(input))
            {
                case 1:
                    viewCatalog();
                    break;
                case 2:
                    addItemToCart();
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("\nINVALID INPUT: " + input);
                    mainMenu();
                    break;
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("\nINVALID INPUT: " + input);
            mainMenu();
        }
    }

    private static void viewCatalog()
    {
        try
        {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT catalog.ModelID, catalog.Name, shipclass.Name AS ShipClass, catalog.BasePrice AS Price FROM Catalog AS catalog " +
                      "JOIN ShipClass AS shipclass " +
                      "ON catalog.ShipClass = shipclass.ClassID");

            System.out.println("\nModelID | Name               | ShipClass     | Price");
            System.out.print("---------------------------------------------------------");

            while (resultSet.next())
            {
                int modelID = resultSet.getInt("ModelID");
                String name = resultSet.getString("Name");
                String shipClass = resultSet.getString("ShipClass");
                int price = resultSet.getInt("Price");

                System.out.printf(outputFormat, modelID, name, shipClass, price);
            }

            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception e)
        {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.print("\n\nPress enter to continue.");
        scanner.nextLine();
        mainMenu();
    }

    private static void addItemToCart()
    {
        try
        {

            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();

            System.out.print("\nEnter the ModelID to add to cart, or 0 to cancel: ");

            input = scanner.nextLine();

            try
            {
                if (statement.execute("SELECT * FROM Catalog " +
                          "WHERE ModelID = " + Integer.parseInt(input)))
                {
                    System.out.print("\nEnter the quantity to add to cart, or 0 to cancel: ");

                    String inputQuantity = scanner.nextLine();;

                    try
                    {
                        if (Integer.parseInt(inputQuantity) != 0)
                        {
                            statement.execute("INSERT INTO Cart (CustomerID, ModelID, Quantity) " +
                                      "VALUES (1, " + Integer.parseInt(input) + ", " + Integer.parseInt(inputQuantity) + ")");

                            System.out.println("\nItem added to cart!");
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("\nINVALID INPUT: " + inputQuantity);
                        mainMenu();
                    }
                }
                else if (Integer.parseInt(input) != 0)
                {
                    System.out.println("\nModelID does not exist.");
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("\nINVALID INPUT: " + input);
                mainMenu();
            }

            statement.close();
            connection.close();
        }
        catch (Exception e)
        {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        mainMenu();
    }

    private static void viewCart()
    {
        try
        {
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT catalog.ModelID, catalog.Name, shipclass.Name AS ShipClass, cart.Quantity, (cart.Quantity * catalog.BasePrice) AS Price FROM Cart as cart " +
                      "JOIN Catalog AS catalog " +
                      "ON cart.ModelID = catalog.ModelID " +
                      "JOIN ShipClass AS shipclass " +
                      "ON catalog.ShipClass = shipclass.ClassID " +
                      "WHERE cart.CustomerID = 1");

            System.out.println("\nModelID | Name               | ShipClass     | Quantity | Price");
            System.out.print("--------------------------------------------------------------------");

            while (resultSet.next())
            {
                int modelID = resultSet.getInt("ModelID");
                String name = resultSet.getString("Name");
                String shipClass = resultSet.getString("ShipClass");
                int quantity = resultSet.getInt("Quantity");
                int price = resultSet.getInt("Price");

                System.out.printf(outputFormatQuantity, modelID, name, shipClass, quantity, price);
            }

            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception e)
        {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.print("\n\nPress enter to continue.");
        scanner.nextLine();
        mainMenu();
    }
}
