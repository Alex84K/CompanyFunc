package ait.employee.dao;

import ait.employee.model.Employee;
import ait.employee.model.SalesManager;

public class CompanyImpl implements Company {
    private Employee[] employees;
    private int size;

    public CompanyImpl(int capacity) {
        employees = new Employee[capacity];
    }

    public boolean addEmployee(Employee employee) {
        //TODO throw RuntimeException if //employee == null

            if(employee == null) {
                throw new RuntimeException("ERROR!");
            }

        if ( size == employees.length || findEmployee(employee.getId()) != null) {
            return false;
        }
//        employees[size] = employee;
//        size++;
        employees[size++] = employee;
        return true;
    }

    public Employee removeEmployee(int id) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getId() == id) {
                Employee victim = employees[i];
//                employees[i] = employees[size - 1];
//                employees[size - 1] = null;
//                size--;
                employees[i] = employees[--size];
                employees[size] = null;
                return victim;
            }
        }
        return null;
    }

    public Employee findEmployee(int id) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getId() == id) {
                return employees[i];
            }
        }
        return null;
    }

    public double totalSalary() {
        double res = 0;
        for (int i = 0; i < size; i++) {
            res += employees[i].calcSalary();
        }
        return res;
    }

    public int quantity() {
        return size;
    }

    public double avgSalary() {
        return totalSalary() / size;
    }

    public double totalSales() {
        double res = 0;
        for (int i = 0; i < size; i++) {
            if (employees[i] instanceof SalesManager) {
                SalesManager salesManager = (SalesManager) employees[i];
                res += salesManager.getSalesValue();
            }
        }
        return res;
    }

    public void printEmployees() {
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }

    public Employee[] findEmployeesHoursGreaterThan(int hours) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (employees[i].getHours() > hours) {
                count++;
            }
        }
        Employee[] res = new Employee[count];
        for (int i = 0, j = 0; j < res.length; i++) {
            if (employees[i].getHours() > hours) {
                res[j++] = employees[i];
//                j++;
            }
        }
        return res;
    }

    public Employee[] findEmployeesSalaryRange(int minSalary, int maxSalary) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (employees[i].calcSalary() >= minSalary && employees[i].calcSalary() < maxSalary) {
                count++;
            }
        }
        Employee[] res = new Employee[count];
        for (int i = 0, j = 0; j < res.length; i++) {
            if (employees[i].calcSalary() >= minSalary && employees[i].calcSalary() < maxSalary) {
                res[j++] = employees[i];
            }
        }
        return res;
    }
}
