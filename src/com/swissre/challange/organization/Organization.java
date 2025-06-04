package com.swissre.challange.organization;

import com.swissre.challange.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implements business logic to analyze employee salary and reporting structure.
 */

public class Organization implements OrganizationService {
    private final Map<Integer, Employee> employeeMap;
    private final Map<Integer, List<Employee>> managerToEmployees;
    private final Employee ceo;

    public Organization(List<Employee> employees) {
        if (employees == null) {
            throw new IllegalArgumentException("Employees list cannot be null");
        }

        employeeMap = employees.stream()
                .collect(Collectors.toMap(Employee::getId, Function.identity()));

        managerToEmployees = employees.stream()
                .filter(e -> e.getManagerId() != null)
                .collect(Collectors.groupingBy(Employee::getManagerId));

        ceo = employees.stream()
                .filter(e -> e.getManagerId() == null)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No CEO found (employee without manager)"));
    }

    @Override
    public List<String> getManagersWithSalaryDeviation(boolean aboveAverage) {
        return managerToEmployees.entrySet().stream()
                .map(entry -> {
                    Employee manager = employeeMap.get(entry.getKey());
                    List<Employee> subs = entry.getValue();

                    double avgSubSalary = subs.stream()
                            .mapToDouble(Employee::getSalary)
                            .average()
                            .orElse(0);

                    double lowerBound = avgSubSalary * 1.2;
                    double upperBound = avgSubSalary * 1.5;

                    if (aboveAverage && manager.getSalary() > upperBound) {
                        return manager.getFullName() + " earns too much: " + String.format("%.2f", (manager.getSalary() - upperBound));
                    } else if (!aboveAverage && manager.getSalary() < lowerBound) {
                        return manager.getFullName() + " earns too little: " + String.format("%.2f", (lowerBound - manager.getSalary()));
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getEmployeesWithLongReportingLine() {
        return employeeMap.values().stream()
                .map(e -> Map.entry(e, getDepthToCEO(e)))
                .filter(entry -> entry.getValue() > 4)
                .map(entry -> entry.getKey().getFullName() + " has too long reporting line: " + entry.getValue())
                .collect(Collectors.toList());
    }

    /**
     * Returns number of levels between employee and CEO.
     */
    private int getDepthToCEO(Employee e) {
        int depth = 0;
        Employee current = e;
        while (current.getManagerId() != null) {
            current = employeeMap.get(current.getManagerId());
            depth++;
            if (current == null) {
                // Defensive: manager not found in map
                break;
            }
        }
        return depth;
    }
}
