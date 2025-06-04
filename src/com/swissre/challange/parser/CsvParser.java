package com.swissre.challange.parser;

import com.swissre.challange.model.Employee;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CSV implementation of the Parser interface.
 */
public class CsvParser implements Parser {

    @Override
    public List<Employee> parse(String filePath) throws IOException {
        // Load from classpath
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new FileNotFoundException("Could not find resource: " + filePath);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines()
                    .skip(1) // Skip header line
                    .map(line -> line.split(","))
                    .map(parts -> new Employee(
                            Integer.parseInt(parts[0].trim()),
                            parts[1].trim(),
                            parts[2].trim(),
                            Double.parseDouble(parts[3].trim()),
                            parts.length == 5 && !parts[4].isBlank() ? Integer.parseInt(parts[4].trim()) : null
                    ))
                    .collect(Collectors.toList());
        }
    }

    // Added to allow parsing from lines for tests
    public List<Employee> parseLines(List<String> lines) {
        return lines.stream()
                .map(line -> line.split(","))
                .map(parts -> new Employee(
                        Integer.parseInt(parts[0].trim()),
                        parts[1].trim(),
                        parts[2].trim(),
                        Double.parseDouble(parts[3].trim()),
                        parts.length == 5 ? Integer.parseInt(parts[4].trim()) : null))
                .collect(Collectors.toList());
    }
}
