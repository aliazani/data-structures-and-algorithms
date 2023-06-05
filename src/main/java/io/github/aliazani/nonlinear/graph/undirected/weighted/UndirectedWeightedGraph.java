package io.github.aliazani.nonlinear.graph.undirected.weighted;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents an undirected weighted graph.
 *
 * @param <T> the type of nodes in the graph.
 */
public class UndirectedWeightedGraph<T extends Comparable<T>> {
    private final Map<T, WeightedGraphNode<T>> vertices;

    /**
     * Constructs an empty undirected weighted graph.
     */
    public UndirectedWeightedGraph() {
        vertices = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph.
     *
     * @param value the value of the vertex to add.
     * @throws IllegalArgumentException if the value is null.
     */
    public void addVertex(T value) {
        if (value == null) throw new IllegalArgumentException();

        vertices.putIfAbsent(value, new WeightedGraphNode<>(value));
    }

    /**
     * Adds an edge between two vertices in the graph.
     *
     * @param from   the value of the source vertex.
     * @param to     the value of the target vertex.
     * @param weight the weight of the edge.
     * @throws IllegalArgumentException if either of the vertices is not present in the graph.
     */
    public void addEdge(T from, T to, int weight) {
        WeightedGraphNode<T> fromNode = vertices.get(from);
        WeightedGraphNode<T> toNode = vertices.get(to);
        if (fromNode == null || toNode == null) throw new IllegalArgumentException();

        fromNode.addEdge(toNode, weight);
        toNode.addEdge(fromNode, weight);
    }

    /**
     * Gets the shortest path between two vertices in the graph using Dijkstra's algorithm.
     *
     * @param from the value of the source vertex.
     * @param to   the value of the target vertex.
     * @return the shortest path as a Path object.
     * @throws IllegalArgumentException if either of the vertices is not present in the graph.
     */
    public Path<T> getShortestPath(T from, T to) {
        WeightedGraphNode<T> fromNode = vertices.get(from);
        WeightedGraphNode<T> toNode = vertices.get(to);
        if (fromNode == null || toNode == null) throw new IllegalArgumentException();

        Map<WeightedGraphNode<T>, Integer> distances = new HashMap<>();
        vertices.values().forEach(n -> distances.put(n, Integer.MAX_VALUE));
        distances.replace(fromNode, 0);

        Map<WeightedGraphNode<T>, WeightedGraphNode<T>> previousNodes = new HashMap<>();

        PriorityQueue<NodeEntry<T>> priorityQueue =
                new PriorityQueue<>(Comparator.comparingInt(nodeEntry -> nodeEntry.priority));
        priorityQueue.offer(new NodeEntry<>(fromNode, 0));

        Set<WeightedGraphNode<T>> visited = new HashSet<>();

        Stream.generate(priorityQueue::poll)
                .takeWhile(Objects::nonNull)
                .map(NodeEntry::node)
                .forEach(current -> {
                    visited.add(current);

                    current.getEdges().values()
                            .stream()
                            .filter(edge -> !visited.contains(edge.to()))
                            .forEach(edge -> {
                                int newDistance = distances.get(current) + edge.weight();
                                if (newDistance < distances.get(edge.to())) {
                                    distances.replace(edge.to(), newDistance);
                                    previousNodes.put(edge.to(), current);
                                    priorityQueue.add(new NodeEntry<>(edge.to(), newDistance));
                                }
                            });
                });

        return buildPath(toNode, previousNodes);
    }

    private Path<T> buildPath(WeightedGraphNode<T> toNode, Map<WeightedGraphNode<T>, WeightedGraphNode<T>> previousNodes) {
        Deque<WeightedGraphNode<T>> stack = new ArrayDeque<>();
        stack.push(toNode);
        WeightedGraphNode<T> previous = previousNodes.get(toNode);

        while (previous != null) {
            stack.push(previous);
            previous = previousNodes.get(previous);
        }

        Path<T> path = new Path<>();

        if (stack.size() > 1)
            while (!stack.isEmpty()) path.addNode(stack.pop().getValue());

        return path;
    }


    /**
     * Checks if the graph contains cycles.
     *
     * @return true if cycles exist, false otherwise.
     */
    public boolean hasCycle() {
        HashSet<WeightedGraphNode<T>> visited = new HashSet<>();

        return vertices.values()
                .stream()
                .filter(n -> !visited.contains(n))
                .anyMatch(n -> hasCycle(n, null, visited));
    }

    private boolean hasCycle(WeightedGraphNode<T> node, WeightedGraphNode<T> parent,
                             Set<WeightedGraphNode<T>> visited) {
        visited.add(node);

        return node.getEdges()
                .values()
                .stream()
                .filter(edge -> (edge.to() != parent))
                .anyMatch(edge -> visited.contains(edge.to())
                        || hasCycle(edge.to(), node, visited));
    }

    /**
     * Gets the minimum spanning tree of the graph using Prim's algorithm.
     *
     * @return the minimum spanning tree as a new UndirectedWeightedGraph.
     * @throws IllegalStateException if the graph contains cycles.
     */
    public UndirectedWeightedGraph<T> getMinimumSpanningTree() {
        if (!hasCycle()) throw new IllegalStateException();

        UndirectedWeightedGraph<T> tree = new UndirectedWeightedGraph<>();

        WeightedGraphNode<T> initialVertex = getInitialVertex();

        if (initialVertex == null) return tree;

        tree.addVertex(initialVertex.getValue());
        PriorityQueue<WeightedGraphEdge<T>> edges = getSortedEdgesBasedOnTheirWeight(initialVertex);

        while (tree.vertices.size() < vertices.size()) {
            WeightedGraphEdge<T> minimumEdge = edges.remove();
            WeightedGraphNode<T> toNode = minimumEdge.to();

            if (containsVertex(tree, toNode)) continue;

            tree.addVertex(toNode.getValue());
            tree.addEdge(minimumEdge.from().getValue(), toNode.getValue(), minimumEdge.weight());

            addNonVisitedEdges(edges, toNode, tree);
        }

        return tree;
    }

    private WeightedGraphNode<T> getInitialVertex() {
        return vertices.values().stream().findFirst().orElse(null);
    }

    private PriorityQueue<WeightedGraphEdge<T>> getSortedEdgesBasedOnTheirWeight(WeightedGraphNode<T> vertex) {
        PriorityQueue<WeightedGraphEdge<T>> edges =
                new PriorityQueue<>(Comparator.comparingInt(WeightedGraphEdge::weight));
        edges.addAll(vertex.getEdges().values());

        return edges;
    }

    private boolean containsVertex(UndirectedWeightedGraph<T> tree, WeightedGraphNode<T> toNode) {
        return tree.vertices.containsKey(toNode.getValue());
    }

    private void addNonVisitedEdges(PriorityQueue<WeightedGraphEdge<T>> edges, WeightedGraphNode<T> vertex,
                                    UndirectedWeightedGraph<T> tree) {
        edges.addAll(vertex.getEdges().values().stream()
                .filter(edge -> !containsVertex(tree, edge.to()))
                .toList());
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return a string representation of the graph.
     */
    @Override
    public String toString() {
        return vertices.values().stream()
                .map(vertex -> "%s: [%s]".formatted(
                        vertex,
                        vertex.getEdges().values()
                                .stream()
                                .map(edge -> "(%s, %d)".formatted(edge.to(), edge.weight()))
                                .collect(Collectors.joining(", "))
                )).collect(Collectors.joining("\n"));
    }

    private record NodeEntry<T extends Comparable<T>>(WeightedGraphNode<T> node, int priority) {
    }
}