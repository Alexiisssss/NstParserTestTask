package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NstParser {
    public static void main(String[] args) {
        String inputFile = "src/main/resources/input.txt";
        String outputFile = "src/main/resources/output.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))
        ) {

            String line;
            int lineNumber = 0;


            Pattern pattern = Pattern.compile("NStr\\((.*?)\\)");

            while ((line = br.readLine()) != null) {
                lineNumber++;

                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String nstr = matcher.group(1);

                    String[] splitTranslations = nstr.split(";");

                    for (String translation : splitTranslations) {
                        String[] partsSplits = translation.split("=", 2);
                        if (partsSplits.length == 2) {
                            String translationName = partsSplits[0].trim();
                            String translationValue = partsSplits[1].trim().replaceAll("^['\"]|['\"]$", "");

                            bw.write(lineNumber + ": " + translationName + " : " + translationValue);
                            bw.newLine();
                        }
                    }
                }
            }
            System.out.println("Файл обработан согласно условию. Результат сохранен в " + outputFile);
        } catch (Exception e) {
            System.err.println("Ошибка обработки файла" + e);
        }
    }
}
