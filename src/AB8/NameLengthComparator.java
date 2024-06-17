package AB8;

public interface NameLengthComparator extends SystemComparator{
    default int compare(HierarchicalSystem s1, HierarchicalSystem s2) {
        double len1 = s1.getName().length();
        double len2 = s2.getName().length();
        return Double.compare(len1, len2);
    }
}
