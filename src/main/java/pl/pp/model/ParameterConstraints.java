package pl.pp.model;

/**
 * Created by dpp on 12/23/16.
 */

//TODO: Add parameter label for chart

public enum ParameterConstraints {
    SYSTOLIC_PRESS("systolic_press", 40, 220),
    DIASTOLIC_PRESS("diastolic_press", 30, 120),
    HEART_RATE("heart_rate", 60, 100),
    TEMPERATURE("temperature", 34, 40),
    SATURATION("saturation", 0, 100),
    GLUCOSE("glucose", 50, 380),
    CHOLESTEROL_LDL("cholesterol", 90, 200),
    ALCOHOL("alcohol", 0, 0.05),
    SKIN_RESISTANCE("skin_resistance", 0.2, 0.45),
    STEP_RATE("step_rate", 0, 0),
    LATITUDE("gps_position_latitude", -90, 90),
    LONGITUDE("gps_position_longitude", -180, 180)
    ;

    ParameterConstraints(String parameterName, double minAcceptedValue, double maxAcceptedValue) {
        this.parameterName = parameterName;
        this.minAcceptedValue = minAcceptedValue;
        this.maxAcceptedValue = maxAcceptedValue;
    }

    public String getParameterName() {
        return parameterName;
    }

    public double getMinAcceptedValue() {
        return minAcceptedValue;
    }

    public double getMaxAcceptedValue() {
        return maxAcceptedValue;
    }

    public static ParameterConstraints getParameterByName(String parameterName) {
        switch (parameterName)
        {
            case "systolic_press":
                return ParameterConstraints.SYSTOLIC_PRESS;
            case "diastolic_press":
                return ParameterConstraints.DIASTOLIC_PRESS;
            case "heart_rate":
                return ParameterConstraints.HEART_RATE;
            case "temperature":
                return ParameterConstraints.TEMPERATURE;
            case "saturation":
                return ParameterConstraints.SATURATION;
            case "glucose":
                return ParameterConstraints.GLUCOSE;
            case "cholesterol":
                return ParameterConstraints.CHOLESTEROL_LDL;
            case "alcohol":
                return ParameterConstraints.ALCOHOL;
            case "skin_resistance":
                return ParameterConstraints.SKIN_RESISTANCE;
            case "step_rate":
                return ParameterConstraints.STEP_RATE;
            case "gps_position_latitude":
                return ParameterConstraints.LATITUDE;
            case "gps_position_longitude":
                return ParameterConstraints.LONGITUDE;
            default:
                return SYSTOLIC_PRESS;
        }
    }

    private String parameterName;
    private double minAcceptedValue;
    private double maxAcceptedValue;
}
