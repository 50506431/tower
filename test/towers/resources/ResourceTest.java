package towers.resources;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests that a Resource clamps correctly on construction and spending,
 * and regenerates on the right schedule.
 */
public class ResourceTest {

    /** Calls tick on the resource the given number of times. */
    private void advance(Resource resource, int times) {
        for (int i = 0; i < times; i++) {
            resource.tick(null);
        }
    }

    /** A starting value inside the bounds is kept as-is. */
    @Test
    public void testInitialInRange() {
        assertEquals(4, new Resource(4, 0, 9).getCurrent());
    }

    /** A starting value below the minimum is raised to the minimum. */
    @Test
    public void testInitialBelowMinClamped() {
        assertEquals(2, new Resource(-1, 2, 6).getCurrent());
    }

    /** A starting value above the maximum is lowered to the maximum. */
    @Test
    public void testInitialAboveMaxClamped() {
        assertEquals(9, new Resource(20, 0, 9).getCurrent());
    }

    /** Clamping works with custom bounds too. */
    @Test
    public void testInitialUsesCustomBounds() {
        assertEquals(5, new Resource(3, 5, 8).getCurrent());
        assertEquals(8, new Resource(100, 5, 8).getCurrent());
        assertEquals(7, new Resource(7, 5, 8).getCurrent());
    }

    /** Spending takes the cost off the current value. */
    @Test
    public void testSpendReducesCurrent() {
        Resource resource = new Resource(4, 0, 9);
        resource.spend(2);
        assertEquals(2, resource.getCurrent());
    }

    /** Spending down to the minimum lands exactly on the minimum. */
    @Test
    public void testSpendToMin() {
        Resource resource = new Resource(4, 0, 9);
        resource.spend(4);
        assertEquals(0, resource.getCurrent());
    }

    /** Spending more than is available clamps back up to the minimum. */
    @Test
    public void testSpendClampsBelowMin() {
        Resource resource = new Resource(5, 2, 6);
        resource.spend(5);
        assertEquals(2, resource.getCurrent());
    }

    /** Spending with a negative minimum can leave a negative value. */
    @Test
    public void testSpendWithNegativeMin() {
        Resource resource = new Resource(4, -2, 6);
        resource.spend(5);
        assertEquals(-1, resource.getCurrent());
    }

    /** No regeneration happens before the interval has elapsed. */
    @Test
    public void testTickNoChangeBeforeInterval() {
        Resource resource = new Resource(5, 2, 7);
        advance(resource, Resource.RESOURCE_INTERVAL - 1);
        assertEquals(5, resource.getCurrent());
    }

    /** One resource is added once the interval has elapsed. */
    @Test
    public void testTickIncreasesAtInterval() {
        Resource resource = new Resource(5, 2, 7);
        advance(resource, Resource.RESOURCE_INTERVAL);
        assertEquals(6, resource.getCurrent());
    }

    /** Regeneration happens once per interval, not just once. */
    @Test
    public void testTickIncreasesEachInterval() {
        Resource resource = new Resource(4, 0, 9);
        advance(resource, Resource.RESOURCE_INTERVAL * 2);
        assertEquals(6, resource.getCurrent());
    }

    /** Regeneration clamps at the maximum rather than going past it. */
    @Test
    public void testTickClampsAtMax() {
        Resource resource = new Resource(9, 0, 9);
        advance(resource, Resource.RESOURCE_INTERVAL);
        assertEquals(9, resource.getCurrent());
    }
}
