package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import store.Employee;

import datatype.Euro;
import datatype.Beverages;

public class GUI {

    private static Main main;

    public static void mainMenu() {
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.print("1.Cash account ");
        System.out.print("2.Depository ");
        System.out.print("3.Debtors ");
        System.out.print("4.Supply of small bags ");
        System.out.print("5.Sell small bags ");
        System.out.print("6.Payment ");
        System.out.print("7.Update Employee ");
        System.out.print("0.Close\n");

        int userInput = stringToInt(inputOutput("-------------------------------------------------------------------------------------------------------------------"));

        switch (userInput) {
            case 0:
                main.close();
            case 1:
                main.showCashAccount();
                mainMenu();
            case 2:
                main.showDepository();
                mainMenu();
            case 3:
                main.showDebtors();
                mainMenu();
            case 4:
                supplyMenu();
            case 5:
                purchaseMenu();
            case 6:
                paymentMenu();
            case 7:
                updateEmployee();
            default:
                System.out.println("\n Not valid key");
                mainMenu();
        }
    }

    private static void updateEmployee() {
        main.showAllEmployee();
        String employeeNumber = inputOutput("\nInsert the number of the employee: ");
        int id = stringToInt(employeeNumber);
        List<Employee> lst = main.getEmployeeRegister().readRegisterEmployeeDB();
        if ((id <= lst.size()) && (id > 0)) {
            String name = inputOutput("\nInsert the new name of the employee: ");
            String surname = inputOutput("\nInsert the new surname of the employee: ");
            main.getEmployeeRegister().updateEmployeeDB(id, name, surname);
            System.out.println("\nEmployee inserted correctly");
            mainMenu();
        } else {
            System.out.println("\nEmployee not in the database\n");
            updateEmployee();
        }

    }

    public static void supplyMenu() {
        String numeroScatole = inputOutput("\nInsert number of boxes: ");
        int numero = stringToInt(numeroScatole);

        String tipo = inputOutput("\nInsert the beverage (1:Coffee, 2:Arabic coffee, 3:Tea, 4:Lemon-tea, 5:Camomile-tea) ");
        int tipo_numero = stringToInt(tipo);
        Beverages t = null;
        switch (tipo_numero) {
            case 1:
                t = Beverages.CAFFE;
                break;
            case 2:
                t = Beverages.ARABICO;
                break;
            case 3:
                t = Beverages.THE;
            case 4:
                t = Beverages.THELIMONE;
                break;
            case 5:
                t = Beverages.CAMOMILLA;
                break;
            default:
                System.out.println("\nOperation aborted: a valid beverage was required");
                mainMenu();
        }
        main.showCashAccount();
        main.getRif().supply(t, numero);
        main.showDepository();
        mainMenu();
    }

    private static void purchaseMenu() {
        Boolean cash = true;
        Employee person = null;

        String aCreditoStringa = inputOutput("\nBuy on credit (yes/no): ");
        if (aCreditoStringa.equalsIgnoreCase("yes")) {
            cash = false;
            System.out.println("\nEmployees:");
            main.getEmployeeRegister().showEmployeesDB();
            String personaStringa = inputOutput("\nInsert the employee number (1 .. n)");
            int personaId = stringToInt(personaStringa);

            List<Employee> lst = main.getEmployeeRegister().readRegisterEmployeeDB();
            if ((personaId++ <= lst.size()) && (personaId > 0)) {
                for (Employee p : lst) {
                    if (p.getId() == personaId) {
                        person = p;
                    }
                }
            } else {
                System.out.println("\nEmployee not in the database");
                purchaseMenu();
            }
        }

        String tipo = inputOutput("\nInsert the beverage (1:Coffee, 2:Arabic coffee, 3:Tea, 4:Lemon-tea, 5:Camomile-tea) ");
        int tipo_numero = stringToInt(tipo);
        Beverages t = null;
        switch (tipo_numero) {
            case 1:
                t = Beverages.CAFFE;
                break;
            case 2:
                t = Beverages.ARABICO;
                break;
            case 3:
                t = Beverages.THE;
                break;
            case 4:
                t = Beverages.THELIMONE;
                break;
            case 5:
                t = Beverages.CAMOMILLA;
                break;
            default:
                System.out.println("\nOperation aborted: a valid beverage was required");
                mainMenu();
        }

        String numeroScatole = inputOutput("\nInsert number of small bags: ");
        int numero = stringToInt(numeroScatole);

        if (cash) {
            main.getAcq().purchaseVisitor(t, numero);
        } else {
            main.getAcq().purchaseEmployee(person, t, numero, true);
        }
        mainMenu();
    }

    private static void paymentMenu() {
        Employee persona = null;
        System.out.println("\nEmployees:");
        main.getEmployeeRegister().showEmployeesDB();
        String personaStringa = inputOutput("\nChoose the employee (1..n): ");
        int personaId = stringToInt(personaStringa);
        List<Employee> lst = main.getEmployeeRegister().readRegisterEmployeeDB();
        if ((personaId <= lst.size()) && (personaId > 0)) {
            for (Employee p : lst) {
                if (p.getId() == personaId) {
                    persona = p;
                }
            }
        } else {
            System.out.println("\nEmployee not in the database\n");
            paymentMenu();
        }
        String euroStringa = inputOutput("\nInsert the balance: ");
        double euro_double;
        try {
        	euro_double = Double.parseDouble(euroStringa);
        	Euro e = new Euro(euro_double);
            main.getPayment().pay(persona, e);
		} catch (NumberFormatException nfe) {
			System.out.println("\nOperation aborted: a number was required");
		}
        mainMenu();
    }

    public static String inputOutput(String message) {
        System.out.println(message);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String returnString = "";
        try {
            returnString = br.readLine();
        } catch (IOException e) {
            System.out.println("\nError");
            mainMenu();
        }
        return returnString;
    }

    private static int stringToInt(String value) {
        int returnInt = -1;
        try {
            returnInt = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.out.println("\nOperation aborted: an integer was required");
            mainMenu();
        }
        return returnInt;
    }

    public static void main(String[] args) {
        main = new Main();
        System.out.println("LaTazza\n");
        mainMenu();
    }
}
