public class Criterion {
    private String name;
    private double weight;
    private String direction; // "higher" or "lower"
    private double minValue;
    private double maxValue;
    private String unit;
    private double measuredValue;

    public Criterion(String name, double weight, String direction,
                     double minValue, double maxValue, String unit) {
        this.name = name;
        this.weight = weight;
        this.direction = direction;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.unit = unit;
        this.measuredValue = 0.0;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public String getDirection() {
        return direction;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public String getUnit() {
        return unit;
    }

    public double getMeasuredValue() {
        return measuredValue;
    }

    public void setMeasuredValue(double measuredValue) {
        this.measuredValue = measuredValue;
    }

    public double calculateScore() {
        double score;
        double range = maxValue - minValue;

        if (range == 0) {
            return 1.0;
        }

        if (direction.equalsIgnoreCase("higher")) {
            score = 1 + ((measuredValue - minValue) / range) * 4;
        } else {
            score = 5 - ((measuredValue - minValue) / range) * 4;
        }

        if (score < 1) score = 1;
        if (score > 5) score = 5;

        return Math.round(score * 2.0) / 2.0;
    }
}