package AB8;

import java.util.*;

/**
 * Represents a hierarchical system consisting of more than one subsystem (i.e., implying more
 * than one body).
 */
//
// TODO: Complete this class definition. You can use the Java-Collection-Framework.
//       You can define further classes and methods for the implementation.
//
public class MultiBody implements HierarchicalSystem //TODO: activate clause.
{


    //TODO: private variables and methods if needed.
    //TODO: missing parts of this class.
    private List<AB8.HierarchicalSystem> subsystems;

    /**
     * Initializes this system with more than one subsystem.
     *
     * @param subsystems an array of components of this system (bodies or subsystems),
     *           subsystems != null && subsystems.length > 1.
     *           Refer to Java Varargs documentation for more details:
     *           https://docs.oracle.com/javase/8/docs/technotes/guides/language/varargs.html
     */
    public MultiBody(HierarchicalSystem... subsystems) {

        this.subsystems = Arrays.asList(subsystems);

    }

    @Override
    public String getName() {
        return "MultiBody";
    }

    @Override
    public double getMass() {
        return subsystems.stream().mapToDouble(AB8.HierarchicalSystem::getMass).sum();
    }

    @Override
    public AB8.Vector3 getCenter() {
        double totalMass = getMass();
        AB8.Vector3 weightedSum = new AB8.Vector3(0, 0, 0);
        for (AB8.HierarchicalSystem subsystem : subsystems) {
            weightedSum = weightedSum.plus(subsystem.getCenter().times(subsystem.getMass()));
        }
        return weightedSum.times(1.0 / totalMass);
    }

    @Override
    public double getRadius() {
        double maxDistance = 0;
        Vector3 center = getCenter();
        for (AB8.HierarchicalSystem subsystem : subsystems) {
            double distance = center.distanceTo(subsystem.getCenter()) + subsystem.getRadius();
            if (distance > maxDistance) {
                maxDistance = distance;
            }
        }
        return maxDistance;
    }

    @Override
    public int getNumberOfBodies() {
        return subsystems.stream().mapToInt(AB8.HierarchicalSystem::getNumberOfBodies).sum();
    }

    @Override
    public Deque<AB8.Body> asOrderedList(SystemComparator comp) {
        List<AB8.Body> bodies = new ArrayList<>();
        for (AB8.HierarchicalSystem subsystem : subsystems) {
            bodies.addAll(subsystem.asOrderedList(comp));
        }
        bodies.sort(comp);
        return new ArrayDeque<>(bodies);
    }

    @Override
    public AB8.BodyIterator iterator() {
        return new AB8.MultiBody.MultiBodyIterator(subsystems.iterator());
    }

    private class MultiBodyIterator implements AB8.BodyIterator {
        private Deque<BodyIterator> stack = new ArrayDeque<>();

        public MultiBodyIterator(Iterator<AB8.HierarchicalSystem> subsystemsIterator) {
            while (subsystemsIterator.hasNext()) {
                stack.push(subsystemsIterator.next().iterator());
            }
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                if (stack.peek().hasNext()) {
                    return true;
                } else {
                    stack.pop();
                }
            }
            return false;
        }

        @Override
        public Body next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return stack.peek().next();
        }

        @Override
        public void remove() {

        }
    }
}


// TODO: define further classes, if needed (either here or in a separate file).

