/**
 * Represents a car dealership.
 * Lidor Pahima
 */
package carDealership;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static carDealership.Car.isDataValidC;
import static carDealership.Employee.isDataValidE;

public class CarDealership {

    /**
     * The main entry point for the CarDealership program.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String fileCars = "C:\\Users\\lidor\\IdeaProjects\\carDealership\\src\\carDealership\\CarDealership.txt.txt";
        ArrayList<Car> cars = importCars(fileCars);
        String fileEmp = "C:\\Users\\lidor\\IdeaProjects\\carDealership\\src\\carDealership\\Employee.txt.txt";
        ArrayList<Employee> employee = importEmployee(fileEmp);


        Scanner input = new Scanner(System.in);
        Menu SelectedOption;
        do {
            System.out.println("what to do?\n " +
                    "   1.EMPLOYEE LIST\n" +
                    "    2.CAR LIST\n" +
                    "    3.SELL CAR\n" +
                    "    4.ADD CAR\n" +
                    "    5.END PROGRAM");

            int choice = input.nextInt();
            while(choice >= 6 || choice < 0 ){
                System.out.println("Please select in range 1-5");
                choice = input.nextInt();
            }
            if(choice == 5){
                try {
                    String fileName = "CarDealership.txt";
                    FileWriter CarDelership = new FileWriter(fileName);
                    for (Car car : cars) {
                        CarDelership.write(car.toString());
                        CarDelership.write(System.lineSeparator());
                    }CarDelership.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            SelectedOption = Menu.fromValue(choice);
            SelectedOption.Selected(employee, cars);

        }while(SelectedOption != Menu.endProgram );

    }

    /**
     * Imports the employee data from a file.
     *
     * @param File the file to import employee data from
     * @return an ArrayList of Employee objects
     */
    private static ArrayList<Employee> importEmployee(String File) {
        ArrayList<Employee> employees = new ArrayList<>();
        String line;
        BufferedReader read = null;
        try {
            read = new BufferedReader(new FileReader(File));
            while ((line = read.readLine()) != null) {
                String [] inPut = line.split(" ");
                String name = inPut[0];
                int id = Integer.parseInt(inPut[1]);
                int sales = Integer.parseInt(inPut[2]);

                Employee employee = new Employee(name,id,sales);
                if(isDataValidE(employee)) {
                    employees.add(employee);
                }
            }


        } catch (IOException e) {
            e.getMessage();
        }


        return employees;
    }

    /**
     * Imports the car data from a file.
     *
     * @param file the file to import car data from
     * @return an ArrayList of Car objects
     */
    private static ArrayList<Car> importCars(String file) {
        ArrayList<Car> cars = new ArrayList<>();
        String line;

        BufferedReader read = null;
        try {
            read = new BufferedReader(new FileReader(file));
            while ((line = read.readLine()) != null) {
                String inPut[] = line.split(" ");

                String car_Num = inPut[0];
                int modelYear = Integer.parseInt(inPut[1]);
                String Brand = inPut[2];
                int Mil = Integer.parseInt(inPut[3]);
                int  Price = Integer.parseInt(inPut[4]);
                Car car = new Car(car_Num,modelYear,Brand,Mil,Price);
                if(isDataValidC(car)) {
                    cars.add(car);
                }
            } }catch(IOException e){
            throw new RuntimeException(e);
        }

        return cars;
    }
}
