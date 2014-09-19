package simulator.network.components.physical;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import simulator.network.components.physical.PhysicalNode;
import simulator.network.components.virtual.VirtualLink;
import simulator.network.components.virtual.VirtualNode;

public class PhysicalLinkTest extends TestCase {

  private PhysicalNode node1;
  private PhysicalNode node2;
  private PhysicalLink physicalLink;

  public PhysicalLinkTest(String testName) {
    super(testName);
    node1 = new PhysicalNode(1, 50);
    node2 = new PhysicalNode(2, 50);
    physicalLink = new PhysicalLink("1:2", node1, node2, 10.1, 40, 100);
  }

  public static Test suite() {
    return new TestSuite(PhysicalLinkTest.class);
  }

  public void testGetId() {
    assertEquals("1:2", physicalLink.getId());
  }

  public void testGetSourceNode() {
    assertEquals(node1, physicalLink.getSourceNode());
  }

  public void testGetDestinyNode() {
    assertEquals(node2, physicalLink.getDestinyNode());
  }

  public void testGetBandwidthCapacity() {
    assertEquals(10.1, physicalLink.getBandwidthCapacity());
  }

  public void testGetBandwidthLoad() {
    assertEquals(0.0, physicalLink.getBandwidthLoad());
  }

  public void testGetDelay() {
    assertEquals(40, physicalLink.getDelay());
  }

  public void testGetCost() {
    assertEquals(100, physicalLink.getCost());
  }

  public void testGetAvailability() {
    assertNotNull(physicalLink.getAvailability());
  }

  public void testSourceNodeAttachment() {
    assertTrue(node1.getAttachedLinks().contains(physicalLink));
  }

  public void testDestinyNodeAttachment() {
    assertTrue(node2.getAttachedLinks().contains(physicalLink));
  }

  public void testNodeAttachedToSourceNode() {
    assertEquals(node2, physicalLink.getNodeAttachedTo(node1));
  }

  public void testNodeAttachedToDestinyNode() {
    assertEquals(node1, physicalLink.getNodeAttachedTo(node2));
  }

  public void testGetRemainingBandwidth() {
    double load = 2.05;
    physicalLink.setBandwidthLoad(load);
    assertEquals(physicalLink.getBandwidthCapacity() - load,
      physicalLink.getRemainingBandwidth());
  }

  public void testCanHostWhenItCan() {
    VirtualNode virtualNode1 = new VirtualNode(1, 100);
    VirtualNode virtualNode2 = new VirtualNode(2, 200);
    VirtualLink virtualLink = new VirtualLink("1:2", virtualNode1, virtualNode2, 10, 30);
    assertTrue(physicalLink.canHost(virtualLink));
  }

  public void testCanHostWhenItCannot() {
    VirtualNode virtualNode1 = new VirtualNode(1, 100);
    VirtualNode virtualNode2 = new VirtualNode(2, 200);
    VirtualLink virtualLink = new VirtualLink("1:2", virtualNode1, virtualNode2, 20, 30);
    assertFalse(physicalLink.canHost(virtualLink));
  }
}
