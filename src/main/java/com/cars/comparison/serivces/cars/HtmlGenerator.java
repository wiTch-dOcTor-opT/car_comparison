package com.cars.comparison.serivces.cars;

import java.util.List;

public class HtmlGenerator {
    public static String generateComparisonTable(String firstCarName, String secondCarName,
                                                 List<String> carAttributes, List<String> firstCarValues,
                                                 List<String> secondCarValues,
                                                 List<String> specificationsAttributes, List<String> specificationsFirstCarValues,
                                                 List<String> specificationsSecondCarValues,
                                                 List<String> featuresAttributes, List<String> featuresFirstCarValues,
                                                 List<String> featuresSecondCarValues) {
        StringBuilder table = new StringBuilder("<!DOCTYPE html><html><head><title>Car Comparison</title></head><body>");

        table.append("<h2>Car Comparison</h2>");
        table.append("<table border=\"1\">");
        table.append("<tr><th>Attribute</th><th style=\"text-align:center;\">").append(firstCarName).append("</th><th style=\"text-align:center;\">").append(secondCarName).append("</th></tr>");

        appendRows(table, carAttributes, firstCarValues, secondCarValues);
        appendRows(table, specificationsAttributes, specificationsFirstCarValues, specificationsSecondCarValues);
        appendRows(table, featuresAttributes, featuresFirstCarValues, featuresSecondCarValues);

        table.append("</table></body></html>");
        return table.toString();
    }

    public static String generateDifferencesTable(String firstCarName, String secondCarName,
                                                  List<String> carAttributes, List<String> firstCarValues,
                                                  List<String> secondCarValues,
                                                  List<String> specificationsAttributes, List<String> specificationsFirstCarValues,
                                                  List<String> specificationsSecondCarValues,
                                                  List<String> featuresAttributes, List<String> featuresFirstCarValues,
                                                  List<String> featuresSecondCarValues) {
        StringBuilder table = new StringBuilder("<!DOCTYPE html><html><head><title>Car Differences</title></head><body>");

        table.append("<h2>Car Differences</h2>");
        table.append("<table border=\"1\">");
        table.append("<tr><th>Attribute</th><th style=\"text-align:center;\">").append(firstCarName).append("</th><th style=\"text-align:center;\">").append(secondCarName).append("</th></tr>");

        appendDifferencesRows(table, carAttributes, firstCarValues, secondCarValues);
        appendDifferencesRows(table, specificationsAttributes, specificationsFirstCarValues, specificationsSecondCarValues);
        appendDifferencesRows(table, featuresAttributes, featuresFirstCarValues, featuresSecondCarValues);

        table.append("</table></body></html>");
        return table.toString();
    }

    private static void appendRows(StringBuilder table, List<String> attributes, List<String> firstCarValues, List<String> secondCarValues) {
        for (int i = 0; i < attributes.size(); i++) {
            String attribute = attributes.get(i);
            String firstCarValue = firstCarValues.get(i);
            String secondCarValue = secondCarValues.get(i);
            table.append("<tr><td>").append(attribute).append("</td><td style=\"text-align:center;\">").append(getDisplayValue(firstCarValue)).append("</td><td style=\"text-align:center;\">").append(getDisplayValue(secondCarValue)).append("</td></tr>");
        }
    }

    private static String getDisplayValue(String value) {
        if (value != null && (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false"))) {
            return value.equalsIgnoreCase("true") ? "&#x2714;" : "&#x2718;";
        }
        return value;
    }

    public static String generateErrorMessage(String message) {
        return "<!DOCTYPE html><html><head><title>Error</title></head><body>" + message + "</body></html>";
    }

    private static void appendDifferencesRows(StringBuilder table, List<String> attributes, List<String> firstCarValues, List<String> secondCarValues) {
        for (int i = 0; i < attributes.size(); i++) {
            String attribute = attributes.get(i);
            String firstCarValue = firstCarValues.get(i);
            String secondCarValue = secondCarValues.get(i);
            if (!firstCarValue.equals(secondCarValue)) {
                table.append("<tr><td>").append(attribute).append("</td><td style=\"text-align:center;\">").append(firstCarValue).append("</td><td style=\"text-align:center;\">").append(secondCarValue).append("</td></tr>");
            }
        }
    }
}






