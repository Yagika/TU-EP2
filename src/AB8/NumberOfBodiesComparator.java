package AB8;

public interface NumberOfBodiesComparator extends SystemComparator{
    default int compare(HierarchicalSystem s1, HierarchicalSystem s2) {
        double num1 = s1.getNumberOfBodies();
        double num2 = s2.getNumberOfBodies();
        return Double.compare(num1, num2);
    }
}
