package simulator.io;

import junit.framework.TestCase;

import simulator.network.components.physical.PhysicalNode;
import simulator.simulation.Request;

public class OptFIVNMPReaderTest extends TestCase {

  private OptFIVNMPReader reader;
  private Request request;

  protected void setUp() {
    reader = new OptFIVNMPReader("src/test/resources/OptFI/VNMPs/eu_20_0_prob");
    request = reader.getVirtualNetworkRequests().get(0);
  }

  public void testNumberOfSubstrateNodes() {
    assertEquals(20, reader.getSubstrateNetwork().getAmountNodes());
  }

  public void testNumberOfSubstrateLinks() {
    assertEquals(22, reader.getSubstrateNetwork().getAmountLinks());
  }

  public void testGetVirtualNetworkRequests() {
    assertEquals(40, reader.getVirtualNetworkRequests().size());
  }

  public void testFirstVirtualNetworkRequestNullity() {
    assertNotNull(request);
  }

  public void testFirstVirtualNetworkRequestNumberOfNodes() {
    assertEquals(5, request.getAmountNodes());
  }

  public void testFirstVirtualNetworkRequestNumberOfLinks() {
    assertEquals(4, request.getAmountLinks());
  }
}
