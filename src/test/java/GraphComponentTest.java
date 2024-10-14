import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import lsn.task3.GraphComponents;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphComponentTest {

	@Test
	public void testNoEdges() {
		// given
		Map<Integer, List<Integer>> graph = new HashMap<>();

		// when
		long result = GraphComponents.findNumberOfComponents(graph);

		// then
		assertEquals(0, result, "There should be 0 components when no vertices are present.");
	}

	@Test
	public void testSingleConnectedComponent() {
		// given
		Map<Integer, List<Integer>> graph = new HashMap<>();
		graph.put(1, Arrays.asList(2));
		graph.put(2, Arrays.asList(1, 3));
		graph.put(3, Arrays.asList(2));

		// when
		long result = GraphComponents.findNumberOfComponents(graph);

		// then
		assertEquals(1, result, "The graph should have 1 connected component.");
	}

	@Test
	public void testMultipleComponents() {
		// given
		Map<Integer, List<Integer>> graph = new HashMap<>();
		graph.put(1, Arrays.asList(2));
		graph.put(2, Arrays.asList(1));
		graph.put(3, Arrays.asList(4));
		graph.put(4, Arrays.asList(3));

		// when
		long result = GraphComponents.findNumberOfComponents(graph);

		// then
		assertEquals(2, result, "The graph should have 2 connected components.");
	}

	@Test
	public void testDisconnectedNodes() {
		// given
		Map<Integer, List<Integer>> graph = new HashMap<>();
		graph.put(1, Arrays.asList(2));
		graph.put(2, Arrays.asList(1));
		graph.put(3, new ArrayList<>()); // Node 3 is disconnected

		// when
		long result = GraphComponents.findNumberOfComponents(graph);

		// then
		assertEquals(2, result, "The graph should have 2 connected components, one with 1-2 and one with 3.");
	}

	@Test
	public void testFullyDisconnectedNodes() {
		// given
		Map<Integer, List<Integer>> graph = new HashMap<>();
		graph.put(1, new ArrayList<>());
		graph.put(2, new ArrayList<>());
		graph.put(3, new ArrayList<>());

		// when
		long result = GraphComponents.findNumberOfComponents(graph);

		// then
		assertEquals(3, result, "The graph should have 3 connected components, one for each node.");
	}
}
