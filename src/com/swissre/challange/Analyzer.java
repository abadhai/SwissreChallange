package com.swissre.challange;

import com.swissre.challange.organization.Organization;
import com.swissre.challange.organization.OrganizationService;
import com.swissre.challange.parser.CsvParser;
import com.swissre.challange.parser.Parser;
import com.swissre.challange.model.Employee;

import java.io.IOException;
import java.util.List;

public class Analyzer {
    public static void main(String[] args) throws IOException {

        Parser parser = new CsvParser();

        List<Employee> employees = parser.parse("employees.csv");

        OrganizationService organization = new Organization(employees);

        // Managers earning LESS than expected
        List<String> underpaidManagers = organization.getManagersWithSalaryDeviation(false);
        System.out.println("Managers earning LESS than expected:");
        if (underpaidManagers.isEmpty()) {
            System.out.println("None");
        } else {
            underpaidManagers.forEach(System.out::println);
        }

        // Managers earning MORE than expected
        List<String> overpaidManagers = organization.getManagersWithSalaryDeviation(true);
        System.out.println("\nManagers earning MORE than expected:");
        if (overpaidManagers.isEmpty()) {
            System.out.println("None");
        } else {
            overpaidManagers.forEach(System.out::println);
        }

        // Employees with long reporting line
        List<String> longLineEmployees = organization.getEmployeesWithLongReportingLine();
        System.out.println("\nEmployees with long reporting line:");
        if (longLineEmployees.isEmpty()) {
            System.out.println("None");
        } else {
            longLineEmployees.forEach(System.out::println);
        }

    }
}