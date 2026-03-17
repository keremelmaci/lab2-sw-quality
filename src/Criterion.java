public class Criterion {
    private String name;
    private double weight;
    private String direction; // "higher" or "lower"
    private double minValue;
    private double maxValue;
    private String unit;
    private double measuredValue;
    private boolean measuredAssigned;

    public Criterion(String name, double weight, String direction,
                     double minValue, double maxValue, String unit) {
        this.name = name;
        this.weight = weight;
        this.direction = direction;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.unit = unit;
        this.measuredAssigned = false;
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

    public boolean isMeasuredAssigned() {
        return measuredAssigned;
    }

    public void setMeasuredValue(double measuredValue) {
        this.measuredValue = measuredValue;
        this.measuredAssigned = true;
    }

    public double calculateScore() {
        if (!measuredAssigned) {
            return 0.0;
        }

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

        // clamp between 1 and 5
        if (score < 1) score = 1;
        if (score > 5) score = 5;

        // round to nearest 0.5
        score = Math.round(score * 2.0) / 2.0;

        return score;
    }

    @Override
    public String toString() {
        String directionText = direction.equalsIgnoreCase("higher")
                ? "Higher is better"
                : "Lower is better";

        return String.format("%s: %.1f %s -> Score: %.1f (%s)",
                name, measuredValue, unit, calculateScore(), directionText);
    }
}