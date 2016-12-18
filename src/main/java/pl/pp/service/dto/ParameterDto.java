package pl.pp.service.dto;

/**
 * Created by dpp on 12/18/16.
 */
public class ParameterDto {

    private String parameterType;

    private double paremeterValue;

    public ParameterDto(String parameterType, double paremeterValue) {
        this.parameterType = parameterType;
        this.paremeterValue = paremeterValue;
    }

    public String getParameterType() {
        return parameterType;
    }

    public double getParemeterValue() {
        return paremeterValue;
    }
}
