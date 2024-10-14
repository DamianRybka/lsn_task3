package lsn.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class GraphComponents {

	public static long findNumberOfComponents(Map<Integer, List<Integer>> graph) {
		Set<Integer> visited = new HashSet<>();
		return graph.keySet().stream()
				.filter(vertex -> !visited.contains(vertex))
				.peek(vertex -> dfs(graph, vertex, visited))
				.count();
	}

	private static void dfs(Map<Integer, List<Integer>> graph, Integer vertex, Set<Integer> visited) {
		visited.add(vertex);
		graph.get(vertex).stream()
				.filter(neighbor -> !visited.contains(neighbor))
				.forEach(neighbor -> dfs(graph, neighbor, visited));
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();  // Number of edges
		scanner.nextLine();

		Map<Integer, List<Integer>> graph = new HashMap<>();

		for (int i = 0; i < n; i++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			scanner.nextLine();

			graph.putIfAbsent(u, new ArrayList<>());
			graph.putIfAbsent(v, new ArrayList<>());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}

		long numberOfComponents = findNumberOfComponents(graph);
		System.out.println("Number of separated graphs: " + numberOfComponents);

		scanner.close();
	}
}
