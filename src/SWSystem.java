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
        double weightedSum = 0.0;
        double totalWeight = 0.0;

        for (QualityDimension d : dimensions) {
            weightedSum += d.calculateDimensionScore() * d.getWeight();
            totalWeight += d.getWeight();
        }

        if (totalWeight == 0) return 0.0;

        return Math.round((weightedSum / totalWeight) * 10.0) / 10.0;
    }

    public String getOverallLabel() {
        double score = calculateOverallScore();

        if (score >= 4.5) return "Excellent Quality";
        if (score >= 3.5) return "Good Quality";
        if (score >= 2.5) return "Needs Improvement";
        return "Poor Quality";
    }

    public QualityDimension findWeakestDimension() {
        if (dimensions.isEmpty()) return null;

        QualityDimension weakest = dimensions.get(0);

        for (QualityDimension d : dimensions) {
            if (d.calculateDimensionScore() < weakest.calculateDimensionScore()) {
                weakest = d;
            }
        }

        return weakest;
    }

    public void printReport() {
        System.out.println("\n========================================");
        System.out.println("SOFTWARE QUALITY EVALUATION REPORT");
        System.out.println("System: " + systemName + " v" + version + " (" + category + ")");
        System.out.println("========================================");

        for (QualityDimension d : dimensions) {
            System.out.println("\n--- " + d.getName() + " [" + d.getIsoCode() + "] ---");

            for (Criterion c : d.getCriteria()) {
                System.out.printf("%s: %.2f %s -> Score: %.1f%n",
                        c.getName(),
                        c.getMeasuredValue(),
                        c.getUnit(),
                        c.calculateScore());
            }

            System.out.printf("Dimension Score: %.1f/5 [%s]%n",
                    d.calculateDimensionScore(),
                    d.getQualityLabel());
        }

        System.out.println("\n========================================");
        System.out.printf("OVERALL QUALITY SCORE: %.1f/5 [%s]%n",
                calculateOverallScore(),
                getOverallLabel());

        QualityDimension weakest = findWeakestDimension();
        if (weakest != null) {
            System.out.println("Weakest Dimension: " + weakest.getName());
            System.out.printf("Gap: %.1f%n", weakest.calculateQualityGap());
        }
        System.out.println("========================================");
    }
}