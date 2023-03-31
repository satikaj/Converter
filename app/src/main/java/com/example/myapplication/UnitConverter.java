package com.example.myapplication;

public class UnitConverter {
    private static final double[][] lengthConversionValues = {
            {1, 1/2.54, 1/30.48, 1/91.44, 1/100000.0, 1/160900.0},
            {2.54, 1, 1/12.0, 1/36.0, 1/39370.0, 1/63360.0},
            {30.48, 12, 1, 1/3.0, 1/3281.0, 1/5280.0},
            {91.44, 36, 3, 1, 1/1094.0, 1/1760.0},
            {100000, 39370, 3281, 1094, 1, 1/1.609},
            {160900, 63360, 5280, 1760, 1.609, 1}
    };

    private static final double[][] weightConversionValues = {
            {1, 1/1000.0, 1/453.6, 1/28.35, 1/907200.0},
            {1000, 1, 2.205, 35.274, 1/907.2},
            {453.6, 1/2.205, 1, 16, 1/2000.0},
            {28.3495, 1/35.274, 1/16.0, 1, 1/32000.0},
            {907185, 907.185, 2000, 32000, 1}
    };

    public static double convert(String unitType, int fromUnitIdx, int toUnitIdx, Double input) {
        double conversionValue = 0;
        if (unitType.equals("Length")) conversionValue = lengthConversionValues[fromUnitIdx][toUnitIdx];
        if (unitType.equals("Weight")) conversionValue = weightConversionValues[fromUnitIdx][toUnitIdx];
        return input * conversionValue;
    }

    public static double convertTemp(int fromUnitIdx, int toUnitIdx, double input) {
        final int CELSIUS = 0, FAHRENHEIT = 1, KELVIN = 2;
        if (fromUnitIdx == CELSIUS && toUnitIdx == CELSIUS) return input;
        if (fromUnitIdx == FAHRENHEIT && toUnitIdx == FAHRENHEIT) return input;
        if (fromUnitIdx == KELVIN && toUnitIdx == KELVIN) return input;
        if (fromUnitIdx == CELSIUS && toUnitIdx == FAHRENHEIT) return celsiusToFahrenheit(input);
        if (fromUnitIdx == FAHRENHEIT && toUnitIdx == CELSIUS) return fahrenheitToCelsius(input);
        if (fromUnitIdx == CELSIUS && toUnitIdx == KELVIN) return celsiusToKelvin(input);
        if (fromUnitIdx == KELVIN && toUnitIdx == CELSIUS) return kelvinToCelsius(input);
        if (fromUnitIdx == FAHRENHEIT && toUnitIdx == KELVIN) return celsiusToKelvin(fahrenheitToCelsius(input));
        if (fromUnitIdx == KELVIN && toUnitIdx == FAHRENHEIT) return kelvinToCelsius(celsiusToFahrenheit(input));
        return 0;
    }

    private static double celsiusToFahrenheit(double cel) {
        return (cel * 1.8) + 32;
    }

    private static double fahrenheitToCelsius(double fah) {
        return (fah - 32) / 1.8;
    }

    private static double celsiusToKelvin(double cel) {
        return cel + 273.15;
    }

    private static double kelvinToCelsius(double kel) {
        return kel - 273.15;
    }
}
