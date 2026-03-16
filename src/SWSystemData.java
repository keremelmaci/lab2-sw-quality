public class SWSystemData {

    public static SWSystem createShopSphere() {
        SWSystem system = new SWSystem("ShopSphere", "Web", "3.2.1");

        QualityDimension fs = new QualityDimension("Functional Suitability", "QC.FS", 25);
        fs.addCriterion(new Criterion("Functional Completeness Ratio", 50, "higher", 0, 100, "%"));
        fs.addCriterion(new Criterion("Functional Correctness Ratio", 50, "higher", 0, 100, "%"));

        QualityDimension re = new QualityDimension("Reliability", "QC.RE", 25);
        re.addCriterion(new Criterion("Availability Ratio", 50, "higher", 95, 100, "%"));
        re.addCriterion(new Criterion("Defect Density", 50, "lower", 0, 20, "defect/KLOC"));

        QualityDimension pe = new QualityDimension("Performance Efficiency", "QC.PE", 25);
        pe.addCriterion(new Criterion("Response Time", 50, "lower", 100, 500, "ms"));
        pe.addCriterion(new Criterion("CPU Utilisation Ratio", 50, "lower", 10, 80, "%"));

        QualityDimension ma = new QualityDimension("Maintainability", "QC.MA", 25);
        ma.addCriterion(new Criterion("Test Coverage Ratio", 50, "higher", 0, 100, "%"));
        ma.addCriterion(new Criterion("Cyclomatic Complexity", 50, "lower", 1, 20, "score"));

        system.addDimension(fs);
        system.addDimension(re);
        system.addDimension(pe);
        system.addDimension(ma);

        return system;
    }
}