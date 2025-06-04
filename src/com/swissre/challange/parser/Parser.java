package com.swissre.challange.parser;

import com.swissre.challange.model.Employee;

import java.io.IOException;
import java.util.List;

public interface Parser {
    List<Employee> parse(String filePath) throws IOException;
}
