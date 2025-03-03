package com.example.demo.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JsonFixer {

    public static void fixJson() {
        String inputFile = "Medicine_Details.json"; // Input file in resources folder
        String outputFile = "fixedMedicine.json"; // Output fixed file

        try {
            // Load the input JSON file from resources folder
            InputStream inputStream = new ClassPathResource(inputFile).getInputStream();

            ObjectMapper objectMapper = new ObjectMapper();
            // Read the JSON data into a List of Maps
            List<Map<String, Object>> data = objectMapper.readValue(inputStream, new TypeReference<>() {});

            // Iterate through each item and fix the keys and values
            for (Map<String, Object> item : data) {
                Map<String, Object> fixedItem = new HashMap<>();
                for (Map.Entry<String, Object> entry : item.entrySet()) {
                    String fixedKey = entry.getKey().trim().replace(" ", "_").toLowerCase();

                    // Remove the percentage symbol for review fields
                    if (fixedKey.equals("poor_review_%")) {
                        fixedKey = "poor_review";  // Remove '%'
                    } else if (fixedKey.equals("excellent_review_%")) {
                        fixedKey = "excellent_review";  // Remove '%'
                    } else if (fixedKey.equals("average_review_%")) {
                        fixedKey = "average_review";  // Remove '%'
                    }

                    Object fixedValue = entry.getValue();

                    // Clean up string values by removing unnecessary escaped characters and spaces
                    if (fixedValue instanceof String) {
                        fixedValue = ((String) fixedValue).replace("\\/", "/").trim();
                    }

                    // Add the cleaned item to the fixed map
                    fixedItem.put(fixedKey, fixedValue);
                }
                item.clear(); // Clear the original item
                item.putAll(fixedItem); // Put the corrected fields back into the item
            }

            // Save the fixed JSON into a new file
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFile), data);
            System.out.println("✅ Fixed JSON saved to: " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
