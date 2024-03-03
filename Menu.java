/**
 * Represents the menu options in a car dealership program.
 * Lidor Pahima
 */
package carDealership;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.*;

import static carDealership.Car.isDataValidC;
import static carDealership.Employee.isDataValidE;

public enum Menu {

    EmployeeList(1),
    carList(2),
    sellCar(3),
    addCar(4),
    endProgram(5);

    private final int choice;
    private String brand;

    /**
     * Constructs a Menu option with the specified choice.
     *
     * @param choose the choice value
     */
    Menu(int choose) {
        this.choice = choose;
    }

    /**
     * Returns the Menu option based on the given value.
     *
     * @param value the value of the Menu option
     * @return the Menu option
     * @throws IllegalArgumentException if the value is not a valid Menu option
     */
    public static Menu fromValue(int value) {
        for (Menu menu : Menu.values()) {
            if (menu.choice == value) {
                return menu;
            }
        }
        throw new IllegalArgumentException("Invalid choice: " + value);
    }

    /**
     * Returns the choice value of the Menu option.
     *
     * @return the choice value
     */
    public int getChoice() {
        return choice;
    }

    /**
     * Executes the selected Menu option based on the chosen value.
     *
     * @param employees the list of employees
     * @param cars      the list of cars
     */
    public void Selected(ArrayList<Employee> employees, ArrayList<Car> cars) {
        switch (this) {
            case EmployeeList:
                EmployeeList(employees);
                break;
            case carList:
                CarList(cars);
                break;
            case sellCar:
                sellCar(cars, employees);
                break;
            case addCar:
                addCar(cars);
                break;
            case endProgram:
                choiceEndProgram();
                break;
        }
    }

    private void CarList(ArrayList<Car> cars) {
        System.out.println("Cars List:");
        for (Car car : cars) {
            System.out.println(car.toString());
        }
    }

    private void choiceEndProgram() {
        System.exit(0);
    }

    private void EmployeeList(ArrayList<Employee> employees) {
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee emp1, Employee emp2) {
                return Integer.compare(emp2.getSales(), emp1.getSales());
            }
        });
        System.out.println("Employee List:");
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }

    private void sellCar(ArrayList<Car> cars, ArrayList<Employee> employees) {
        Scanner input = new Scanner(System.in);
        System.out.println("Employee List:");
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
        System.out.println("Enter your ID");
        int i = 0;
        do {
            int ID = input.nextInt();
            for (Employee employee : employees) {
                if (employee.getId() == ID) {
                    i = 1;
                    int sales = employee.getSales();
                    employee.setSales(sales + 1);
                }
            }
            for (int j = i; j < 1; j++) {
                System.out.println("Wrong ID, try again!");
            }

        } while (i == 0);
        System.out.println("Cars List:");
        for (Car car : cars) {
            System.out.println(car.toString());

        }
        System.out.println("Enter car number that you're selling");
        Scanner inputs = new Scanner(System.in);
        int k = 0;
        do {
            String carNumber = inputs.nextLine();
            int foundIndex = -1;

            for (k = 0; k < cars.size(); k++) {
                if (cars.get(k).getCar_number().equals(carNumber)) {
                    foundIndex = k;
                    try (PrintWriter Sold = new PrintWriter(new FileWriter("Sold.txt", true))) {
                        Sold.println(cars.get(k).toString());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
            }

            if (foundIndex != -1) {
                cars.remove(foundIndex);
                k = 1;
            } else {
                System.out.println("Car not found, try again!");
            }

        } while (k == 0);
    }

    private void addCar(ArrayList<Car> car) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter car number");
        String carNum = input.nextLine();
        System.out.println("Enter model year");
        int modelyear = input.nextInt();
        System.out.println("Enter brand name");
        String brand = input.nextLine();
        System.out.println("Enter mileage");
        int mil = input.nextInt();
        System.out.println("Enter price");
        int price = input.nextInt();
        Car newCar = new Car(carNum, modelyear, brand, mil, price);
        if (isDataValidC(newCar)) {
            car.add(newCar);
        }
    }
}
