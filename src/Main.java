import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, ArrayList<SWSystem>> allSystems = SWSystemData.getAllSystems();

        ArrayList<SWSystem> webSystems = allSystems.get("Web");
        if (webSystems == null || webSystems.isEmpty()) {
            System.out.println("No Web systems found.");
            return;
        }

        SWSystem shopSphere = null;
        for (SWSystem system : webSystems) {
            if (system.getSystemName().equals("ShopSphere")) {
                shopSphere = system;
                break;
            }
        }

        if (shopSphere == null) {
            System.out.println("ShopSphere system not found.");
            return;
        }

        for (QualityDimension d : shopSphere.getDimensions()) {
            switch (d.getIsoCode()) {
                case "QC.FS":
                    d.getCriteria().get(0).setMeasuredValue(94);   // Functional Completeness Ratio
                    d.getCriteria().get(1).setMeasuredValue(91);   // Functional Correctness Ratio
                    break;

                case "QC.RE":
                    d.getCriteria().get(0).setMeasuredValue(99.2); // Availability Ratio
                    d.getCriteria().get(1).setMeasuredValue(2.1);  // Defect Density
                    break;

                case "QC.PE":
                    d.getCriteria().get(0).setMeasuredValue(220);  // Response Time
                    d.getCriteria().get(1).setMeasuredValue(38);   // CPU Utilisation Ratio
                    break;

                case "QC.MA":
                    d.getCriteria().get(0).setMeasuredValue(72);   // Test Coverage Ratio
                    d.getCriteria().get(1).setMeasuredValue(8.5);  // Cyclomatic Complexity
                    break;
            }
        }

        shopSphere.printReport();
    }
}