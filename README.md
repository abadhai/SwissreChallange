# Employee Analyzer

A Java application to analyze employee salary and reporting structures from CSV data.

---

## Overview

This tool reads employee data from a CSV file and provides insights including:

- Managers who earn significantly more or less than their direct reports.
- Employees with long reporting chains (more than 4 levels away from the CEO).

---

## Project Structure

EmployeeAnalyzer/
├── src/
│   └── com.swissre.challange/
│       ├── model/
│       │   └── Employee.java
│       ├── organization/
│       │   ├── Organization.java
│       │   └── OrganizationService.java
│       ├── parser/
│       │   ├── CsvParser.java
│       │   └── Parser.java
│       └── Analyzer.java
├── employees.csv    ❌
├── README.md

---

## CSV File Format

The input CSV (`employees.csv`) should have this header and structure:

id,firstName,lastName,salary,managerId
1,John,Doe,200000,
2,Jane,Smith,120000,1
3,Bob,Brown,80000,2
4,Alice,Johnson,90000,2
...


- `managerId` is optional and left blank for the CEO.

---


---

## ▶️ How to Run (Using IntelliJ)

1. **Ensure `employees.csv` is placed under**:  
   `src/resources/employees.csv`

2. **Mark resources directory in IntelliJ**:
    - Right-click on `resources` folder →  
      `Mark Directory as → Resources Root`

3. **Set program argument**:
    - Go to `Run → Edit Configurations...`
    - Select `Application` → Click `+` to add new if needed
    - Set:
        - **Main class**: `com.swissre.challange.Analyzer`
        - **Program arguments**: `employees.csv`

4. **Run the app**:
    - Right-click on `Analyzer.java` → `Run 'Analyzer.main()'`

---

## 📊 Output Example
Managers earning LESS than expected:
Martin Chekov earns too little: 15000.0

Managers earning MORE than expected:
None

Employees with long reporting line:
None
