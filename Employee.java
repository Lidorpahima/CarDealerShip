/**
 * Represents an employee in a car dealership.
 * Lidor Pahima
 * ID 206735961
 */
package carDealership;

import java.util.InputMismatchException;

public class Employee implements Comparable<Employee> {
    String name;
    int id;
    int sales;

    /**
     * Constructs an Employee object with the specified name, ID, and sales.
     *
     * @param name  the name of the employee
     * @param id    the ID of the employee
     * @param sales the sales of the employee
     */
    public Employee(String name, int id, int sales) {
        try {
            if (!(name.matches(".*\\d.*"))) {
                this.name = name;
            } else {
                throw new IllegalArgumentException("Name cannot contain numbers!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            if (id < 1000000000 || id > 99999999) {
                this.id = id;
            } else {
                throw new InputMismatchException("ID number must contain 9 numbers!");
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        try {
            if (sales >= 0) {
                this.sales = sales;
            } else {
                throw new InputMismatchException("Sales cannot be negative!");
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Sets the sales for the employee and updates the sales document.
     *
     * @param car the car that was sold
     */
    public void setSales(Car car) {
        Car newsell = new Car();
        newsell.SoldCars(car);
        this.sales++;
    }

    /**
     * Calculates the sum of the employee's salary.
     *
     * @return the sum of the employee's salary
     */
    public int sumSalary() {
        int base = 6000;
        int saleBase = 100;
        return (base + saleBase * this.sales);
    }

    /**
     * Returns a string representation of the employee.
     *
     * @return a string representation of the employee
     */
    @Override
    public String toString() {
        return "Name:" + name + " ID:" + id + " Sales:" + sales + " Salary:" + sumSalary();
    }

    /**
     * Compares the employee to another employee based on their sales.
     *
     * @param other the other employee to compare to
     * @return 0 if the sales are equal, 1 if this employee has higher sales, -1 if this employee has lower sales
     */
    public int compareTo(Employee other) {
        if (this.sales == other.sales) {
            return 0;
        } else if (this.sales > other.sales) {
            return 1;
        } else if (this.sales < other.sales) {
            return -1;
        }
        return 0;
    }

    public Employee() {
    }

    /**
     * Checks if the employee data is valid.
     *
     * @param employee the employee to validate
     * @return true if the employee data is valid, false otherwise
     */
    public static boolean isDataValidE(Employee employee) {
        Employee temp = new Employee();
        temp = employee;
        return (employee.id < 1000000000 && employee.id > 99999999 && employee.sales >= 0 && employee.name != null && (!employee.name.matches(".*\\d.*")));
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getSales() {
        return sales;
    }

    public int getId() {
        return id;
    }
}
