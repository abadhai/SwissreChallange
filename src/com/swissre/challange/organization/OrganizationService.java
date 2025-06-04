package com.swissre.challange.organization;

import java.util.List;

/**
 * Interface defining salary and reporting analytics methods.
 */
public interface OrganizationService {
    List<String> getManagersWithSalaryDeviation(boolean aboveAverage);
    List<String> getEmployeesWithLongReportingLine();
}
