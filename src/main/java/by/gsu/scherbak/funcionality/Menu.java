package by.gsu.scherbak.funcionality;


import by.gsu.scherbak.ORM.CRUD;
import by.gsu.scherbak.tables.Captain;
import by.gsu.scherbak.tables.Ship;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/*
 * Class for application's main menu
 * It exists only to provide user a choice of what to do
 * It SHOULD NOT contain any ORM logic!
 *
 * @version      1.0 23.05.2025
 * @author       Scherbak Andrey
 * */
public class Menu {
    private static Integer userChoice;                         //Variable for user choices in menu
    private static Scanner scanner = new Scanner(System.in);

    /*Main menu method*/
    public static void showMenu(){
        Integer tableChoice;        //Variable for chosen table

        System.out.println("\nThis is a main menu for a program");

        while(true){
            System.out.println("\nAvailable options:" +
                    "\n1 - Add a new record to the table." +
                    "\n2 - Update an existing record in the table" +
                    "\n3 - Read records from the table" +
                    "\n4 - Delete records from the table" +
                    "\n5 - Selection with Criteria API" +
                    "\n6 - Exit");

            userChoice = scanner.nextInt();
            scanner.nextLine();

            //Closing program
            if(userChoice == 6){
                return;
            }

            //Choosing the table
            System.out.println("Available tables: " +
                    "\n1 - Ships" +
                    "\n2 - Captains" +
                    "\nChoose the available table: ");

            tableChoice = scanner.nextInt();
            scanner.nextLine();

            switch(userChoice){
                //Creating a record
                case 1:
                    formObject(tableChoice);
                    break;
                //Updating a record
                case 2:
                    updateRecord(tableChoice);
                    break;
                //Reading records
                case 3:
                    readRecords(tableChoice);
                    break;
                //Deleting a record
                case 4:
                    deleteRecords(tableChoice);
                    break;
                //Showing menu for criteria selections
                case 5:
                    criteriaSelectionMenu(tableChoice);
                    break;
                default:
                    System.out.println("Invalid input...");
            }
        }
    }

    /*Method for creating an object*/
    public static void formObject(Integer tableChoice){
        Captain captain = new Captain();            //Variable for Captain map class
        Ship ship = new Ship();                     //Variable for Ship map class
        String userInput;                           //Variable for user input

        /*forming and object depending on tableChoice*/
        switch(tableChoice){
            //Table: Ship
            case 1:
                System.out.println("Enter a name for the ship: ");
                userInput = scanner.nextLine();
                ship.setShipName(userInput);

                System.out.println("Enter a ship tonnage: ");
                userInput = scanner.nextLine();
                ship.setTonnage(Integer.parseInt(userInput));

                System.out.println("Enter a ship's construction date(YYYY-MM-DD) : ");
                userInput = scanner.nextLine();
                ship.setConstructionDate(LocalDate.parse(userInput));

                System.out.println("Enter the Captain's id for the ship " +
                        "(or type L to see the list of available captains): ");
                userInput = scanner.nextLine();

                switch(userInput){
                    case "L":
                        CRUD.readFromTable(2);
                        System.out.println("Enter the Captain's id for the ship: ");
                        userInput = scanner.nextLine();
                    default:
                        captain = CRUD.getRecordById(Integer.parseInt(userInput), Captain.class);
                        ship.setCaptain(captain);
                }

                CRUD.saveRecord(ship);
                System.out.println("The record was successfully added");
                break;
            //Table: Captain_ship
            case 2:
                System.out.println("Enter a name for a captain: ");
                userInput = scanner.nextLine();
                captain.setName(userInput);

                System.out.println("Enter a surname for a captain: ");
                userInput = scanner.nextLine();
                captain.setSurname(userInput);

                System.out.println("Enter a last name for a captain: ");
                userInput = scanner.nextLine();
                captain.setLastname(userInput);

                System.out.println("Enter a captain's age: ");
                userInput = scanner.nextLine();
                captain.setAge(Integer.parseInt(userInput));

                System.out.println("Enter a captain's experience: ");
                userInput = scanner.nextLine();
                captain.setExperience(Integer.parseInt(userInput));

                System.out.println("Enter a captain's birthdate(YYYY-MM-DD): ");
                userInput = scanner.nextLine();
                captain.setBirthDate(LocalDate.parse(userInput));

                CRUD.saveRecord(captain);
                System.out.println("The record was successfully added");
                break;
            default:
                System.out.println("Invalid input...");
        }
    }

    /*Method for updating the record*/
    public static void updateRecord(Integer tableChoice){
        Captain captain = new Captain();            //Variable for Captain map class
        Ship ship = new Ship();                     //Variable for Ship map class
        String userInput;                           //Variable for user input

        System.out.println("Enter an id of the record to update" +
                "(or L to see the table)");
        userInput = scanner.nextLine();

        if(Objects.equals(userInput, "L")){
            CRUD.readFromTable(tableChoice);
            System.out.println("Enter an id of the record to update");
            userInput = scanner.nextLine();
        }

        /*Updating an object depending on table choice*/
        switch(tableChoice){
            //Table: Ship
            case 1:
                ship = CRUD.getRecordById(Integer.parseInt(userInput), Ship.class);

                System.out.println("Enter a new name for the ship: ");
                userInput = scanner.nextLine();
                ship.setShipName(userInput);

                System.out.println("Enter a new ship tonnage: ");
                userInput = scanner.nextLine();
                ship.setTonnage(Integer.parseInt(userInput));

                System.out.println("Enter a new ship's construction date(YYYY-MM-DD) : ");
                userInput = scanner.nextLine();
                ship.setConstructionDate(LocalDate.parse(userInput));

                System.out.println("Enter a new Captain's id for the ship " +
                        "(or type L to see the list of available captains): ");
                userInput = scanner.nextLine();

                switch(userInput){
                    case "L":
                        CRUD.readFromTable(2);
                        System.out.println("Enter the Captain's id for the ship: ");
                        userInput = scanner.nextLine();
                    default:
                        captain = CRUD.getRecordById(Integer.parseInt(userInput), Captain.class);
                        ship.setCaptain(captain);
                }

                CRUD.saveRecord(ship);
                System.out.println("The record was successfully updated");
                break;
            //Table: Captain_ship
            case 2:
                captain = CRUD.getRecordById(Integer.parseInt(userInput), Captain.class);

                System.out.println("Enter a new name for a captain: ");
                userInput = scanner.nextLine();
                captain.setName(userInput);

                System.out.println("Enter a new surname for a captain: ");
                userInput = scanner.nextLine();
                captain.setSurname(userInput);

                System.out.println("Enter a new last name for a captain: ");
                userInput = scanner.nextLine();
                captain.setLastname(userInput);

                System.out.println("Enter a new captain's age: ");
                userInput = scanner.nextLine();
                captain.setAge(Integer.parseInt(userInput));

                System.out.println("Enter a new captain's experience: ");
                userInput = scanner.nextLine();
                captain.setExperience(Integer.parseInt(userInput));

                System.out.println("Enter a new captain's birthdate(YYYY-MM-DD): ");
                userInput = scanner.nextLine();
                captain.setBirthDate(LocalDate.parse(userInput));

                CRUD.saveRecord(captain);
                System.out.println("The record was successfully updated");
                break;
            default:
                System.out.println("Invalid input...");
        }
    }

    /*Method for reading records*/
    public static void readRecords(Integer tableChoice){
        CRUD.readFromTable(tableChoice);
    }

    /*Method for deleting records*/
    public static void deleteRecords(Integer tableChoice){
        Captain captain = new Captain();            //Variable for Captain map class
        Ship ship = new Ship();                     //Variable for Ship map class
        String userInput;                           //Variable for user input

        /*Deleting an object depending on table choice*/
        switch(tableChoice){
            //Table: Ship
            case 1:
                System.out.println("Enter an id of the ship from Ship table: ");
                userInput = scanner.nextLine();
                ship = CRUD.getRecordById(Integer.parseInt(userInput), Ship.class);
                CRUD.deleteRecord(ship);
                System.out.println("The record was succesfully deleted");
                break;
            //Table: Captain_ship
            case 2:
                System.out.println("Enter an id of the captain from Captain_ship table: ");
                userInput = scanner.nextLine();
                captain = CRUD.getRecordById(Integer.parseInt(userInput), Captain.class);
                CRUD.deleteRecord(captain);
                System.out.println("The record was succesfully deleted");
                break;
            default:
                System.out.println("Invalid input...");
        }
    }

    /*Method for showing menu with choosing selections for tables*/
    public static void criteriaSelectionMenu(Integer tableChoice){
        String userInput;                           //Variable for user input

        switch(tableChoice){
            //Selections with table Ship
            case 1:
                System.out.println("Selections for table Ship:" +
                        "\n1 - Select all ships with date bigger than (INPUT) date. " +
                        "\n2 - Select all ships whose captain is older than (INPUT) age.");

                userInput = scanner.nextLine();
                shipSelections(Integer.parseInt(userInput));
                break;
            //Selections with table Ship_captain
            case 2:
                System.out.println("Selections for table Captain_sihp:" +
                        "\n1 - Increase the experience age for captains older than 30" +
                        "\n2 - Delete captains by name");

                userInput = scanner.nextLine();
                captainSelections(Integer.parseInt(userInput));
                break;
        }
    }

    /*This method invokes Criteria API selection methods from CRUD by given arg for Captain table*/
    public static void captainSelections(Integer selectionNumber){
        String userInput;                           //Variable for user input
        List<Captain> captains;                     //List of returned ships

        switch(selectionNumber){
            case 1:
                System.out.println("Increasing age experience for all captains with age > 30");

                captains = CRUD.increaseCaptainExperience();
                break;
            case 2:
                System.out.println("Enter the name: ");
                userInput = scanner.nextLine();

                CRUD.deleteCaptainByName(userInput);

                System.out.println("The captain was deleted!");
                break;
        }
    }

    /*This method invokes Criteria API selection methods from CRUD by given arg for Ship table*/
    public static void shipSelections(Integer selectionNumber){
        String userInput;                           //Variable for user input
        List<Ship> ships;                           //List of returned ships

        switch(selectionNumber){
            //Select all ships with date bigger than userInput
            case 1:
                System.out.println("Enter the date (YYYY-MM-DD): ");
                userInput = scanner.nextLine();

                ships = CRUD.shipDateSelection(LocalDate.parse(userInput));
                ships.forEach(System.out::println);
                break;
            //Select all ships with captain older than userInput
            case 2:
                System.out.println("Enter the age: ");
                userInput = scanner.nextLine();

                ships = CRUD.shipCaptainAgeSelection(Integer.parseInt(userInput));
                ships.forEach(System.out::println);
                break;
        }
    }
}
