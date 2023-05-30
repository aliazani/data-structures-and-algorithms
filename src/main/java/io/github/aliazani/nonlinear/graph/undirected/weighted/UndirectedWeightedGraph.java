package io.github.aliazani.nonlinear.graph.undirected.weighted;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UndirectedWeightedGraph<T extends Comparable<T>> {
    private final Map<T, WeightedGraphNode<T>> vertices;

    public UndirectedWeightedGraph() {
        vertices = new HashMap<>();
    }

    public void addVertex(T value) {
        if (value == null) throw new IllegalArgumentException();

        vertices.putIfAbsent(value, new WeightedGraphNode<>(value));
    }

    public void addEdge(T from, T to, int weight) {
        WeightedGraphNode<T> fromNode = vertices.get(from);
        WeightedGraphNode<T> toNode = vertices.get(to);
        if (fromNode == null || toNode == null) throw new IllegalArgumentException();

        fromNode.addEdge(toNode, weight);
        toNode.addEdge(fromNode, weight);
    }

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