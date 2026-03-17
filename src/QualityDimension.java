import java.util.ArrayList;

public class QualityDimension {
    private String name;
    private String isoCode;
    private double weight;
    private ArrayList<Criterion> criteria;

    public QualityDimension(String name, String isoCode, double weight) {
        this.name = name;
        this.isoCode = isoCode;
        this.weight = weight;
        this.criteria = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public double getWeight() {
        return weight;
    }

    public ArrayList<Criterion> getCriteria() {
        return criteria;
    }

    public void addCriterion(Criterion criterion) {
        criteria.add(criterion);
    }

    public double calculateDimensionScore() {
        if (criteria.isEmpty()) {
            return 0.0;
        }

        double weightedSum = 0.0;
        double totalWeight = 0.0;

        for (Criterion c : criteria) {
            weightedSum += c.calculateScore() * c.getWeight();
            totalWeight += c.getWeight();
        }

        if (totalWeight == 0) {
            return 0.0;
        }

        double score = weightedSum / totalWeight;
        return Math.round(score * 10.0) / 10.0;
    }

    public double calculateQualityGap() {
        return Math.round((5.0 - calculateDimensionScore()) * 10.0) / 10.0;
    }

    public String getQualityLabel() {
        double score = calculateDimensionScore();

        if (score >= 4.5) return "Excellent Quality";
        if (score >= 3.5) return "Good Quality";
        if (score >= 2.5) return "Needs Improvement";
        return "Poor Quality";
    }

    @Override
    public String toString() {
        return name + " [" + isoCode + "]";
    }
}