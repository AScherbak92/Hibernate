package by.gsu.scherbak;

import by.gsu.scherbak.ORM.CRUD;
import by.gsu.scherbak.funcionality.Menu;

/*
 * Main class for application
 *
 * @version      1.1 23.05.2025
 * @author       Scherbak Andrey
 * */
public class App 
{
    public static void main( String[] args )
    {
        CRUD.initializeEmf();
        Menu.showMenu();
        CRUD.closeEmf();
    }
}
