import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SWSystem system = SWSystemData.createShopSphere();

        System.out.println("=== SOFTWARE QUALITY EVALUATION SYSTEM ===");
        System.out.println("System: " + system.getSystemName() + " v" + system.getVersion());
        System.out.println("Category: " + system.getCategory());

        for (QualityDimension dimension : system.getDimensions()) {
            System.out.println("\n--- Enter values for " + dimension.getName() + " ---");

            for (Criterion criterion : dimension.getCriteria()) {
                double value;

                while (true) {
                    System.out.print(
                            criterion.getName() +
                                    " (" + criterion.getUnit() +
                                    ", min=" + criterion.getMinValue() +
                                    ", max=" + criterion.getMaxValue() +
                                    ", " + criterion.getDirection() + " is better): "
                    );

                    if (scanner.hasNextDouble()) {
                        value = scanner.nextDouble();
                        criterion.setMeasuredValue(value);
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a numeric value.");
                        scanner.next();
                    }
                }
            }
        }

        system.printReport();
        scanner.close();
    }
}