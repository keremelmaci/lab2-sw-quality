import java.util.ArrayList;

public class SWSystem {
    private String systemName;
    private String category;
    private String version;
    private ArrayList<QualityDimension> dimensions;

    public SWSystem(String systemName, String category, String version) {
        this.systemName = systemName;
        this.category = category;
        this.version = version;
        this.dimensions = new ArrayList<>();
    }

    public String getSystemName() {
        return systemName;
    }

    public String getCategory() {
        return category;
    }

    public String getVersion() {
        return version;
    }

    public ArrayList<QualityDimension> getDimensions() {
        return dimensions;
    }

    public void addDimension(QualityDimension dimension) {
        dimensions.add(dimension);
    }

    public double calculateOverallScore() {
        if (dimensions.isEmpty()) {
            return 0.0;
        }

        double weightedSum = 0.0;
        double totalWeight = 0.0;

        for (QualityDimension d : dimensions) {
            weightedSum += d.calculateDimensionScore() * d.getWeight();
            totalWeight += d.getWeight();
        }

        if (totalWeight == 0) {
            return 0.0;
        }

        double score = weightedSum / totalWeight;
        return Math.round(score * 10.0) / 10.0;
    }

    public String getOverallLabel() {
        double score = calculateOverallScore();

        if (score >= 4.5) return "Excellent Quality";
        if (score >= 3.5) return "Good Quality";
        if (score >= 2.5) return "Needs Improvement";
        return "Poor Quality";
    }

    public QualityDimension findWeakestDimension() {
        if (dimensions.isEmpty()) {
            return null;
        }

        QualityDimension weakest = dimensions.get(0);

        for (QualityDimension d : dimensions) {
            if (d.calculateDimensionScore() < weakest.calculateDimensionScore()) {
                weakest = d;
            }
        }

        return weakest;
    }

    public void printReport() {
        System.out.println("========================================");
        System.out.println("SOFTWARE QUALITY EVALUATION REPORT (ISO/IEC 25010)");
        System.out.println("System: " + systemName + " v" + version + " (" + category + ")");
        System.out.println("========================================");

        for (QualityDimension d : dimensions) {
            System.out.println("--- " + d.getName() + " [" + d.getIsoCode() + "] (Weight: " + (int) d.getWeight() + ") ---");

            for (Criterion c : d.getCriteria()) {
                String directionText = c.getDirection().equalsIgnoreCase("higher")
                        ? "Higher is better"
                        : "Lower is better";

                System.out.printf("%s: %.1f %s -> Score: %.1f (%s)%n",
                        c.getName(),
                        c.getMeasuredValue(),
                        c.getUnit(),
                        c.calculateScore(),
                        directionText);
            }

            System.out.printf(">> Dimension Score: %.1f/5 [%s]%n",
                    d.calculateDimensionScore(),
                    d.getQualityLabel());
        }

        System.out.println("========================================");
        System.out.printf("OVERALL QUALITY SCORE: %.1f/5 [%s]%n",
                calculateOverallScore(),
                getOverallLabel());
        System.out.println("========================================");

        QualityDimension weakest = findWeakestDimension();
        if (weakest != null) {
            System.out.println("GAP ANALYSIS (ISO/IEC 25010)");
            System.out.println("========================================");
            System.out.println("Weakest Characteristic : " + weakest.getName() + " [" + weakest.getIsoCode() + "]");
            System.out.printf("Score: %.1f/5 | Gap: %.1f%n",
                    weakest.calculateDimensionScore(),
                    weakest.calculateQualityGap());
            System.out.println("Level: " + weakest.getQualityLabel());
            System.out.println(">> This characteristic requires the most improvement.");
            System.out.println("========================================");
        }
    }
}