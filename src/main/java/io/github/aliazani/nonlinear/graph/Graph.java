package io.github.aliazani.nonlinear.graph;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Graph<T extends Comparable<T>> {
    private final Map<T, GraphNode<T>> vertices;
    private final Map<GraphNode<T>, List<GraphNode<T>>> adjacencyList;

    public Graph() {
        vertices = new HashMap<>();
        adjacencyList = new HashMap<>();
    }

    public void addVertex(T value) {
        if (value == null) throw new IllegalArgumentException();

        GraphNode<T> node = new GraphNode<>(value);
        vertices.putIfAbsent(value, node);
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public T removeVertex(T value) {
        GraphNode<T> node = vertices.get(value);
        if (node == null) throw new IllegalArgumentException();

        adjacencyList.keySet().forEach(n -> adjacencyList.get(n).remove(node));
        adjacencyList.remove(node);
        vertices.remove(value);

        return node.getValue();
    }

    public void addEdge(T from, T to) {
        GraphNode<T> fromNode = vertices.get(from);
        GraphNode<T> toNode = vertices.get(to);
        if (fromNode == null || toNode == null) throw new IllegalArgumentException();

        adjacencyList.get(fromNode).add(toNode);
    }

    public void removeEdge(T from, T to) {
        GraphNode<T> fromNode = vertices.get(from);
        GraphNode<T> toNode = vertices.get(to);
        if (fromNode == null || toNode == null) throw new IllegalArgumentException();

        adjacencyList.get(fromNode).remove(toNode);
    }

    public List<T> findNeighbors(T value) {
        GraphNode<T> node = vertices.get(value);
        if (node == null) throw new IllegalArgumentException();

        return adjacencyList.get(node).stream().map(GraphNode::getValue).toList();
    }

    public boolean queryEdge(T from, T to) {
        GraphNode<T> fromNode = vertices.get(from);
        GraphNode<T> toNode = vertices.get(to);
        if (fromNode == null || toNode == null) throw new IllegalArgumentException();

        return adjacencyList.get(fromNode).contains(toNode);
    }

    public String traverseDepthFirst(T vertex) {
        GraphNode<T> node = vertices.get(vertex);
        if (node == null) throw new IllegalArgumentException();

        StringBuilder strBuilder = new StringBuilder();
        traverseDepthFirst(node, new HashSet<>(), strBuilder);
        strBuilder.setLength(strBuilder.length() - 2);

        return strBuilder.toString();
    }

    private void traverseDepthFirst(GraphNode<T> node, Set<GraphNode<T>> visited, StringBuilder strBuilder) {
        strBuilder.append(node.getValue()).append(", ");
        visited.add(node);

        adjacencyList.get(node).stream()
                .filter(n -> !visited.contains(n))
                .forEach(n -> traverseDepthFirst(n, visited, strBuilder));
    }

    public String traverseDepthFirst2(T vertex) {
        GraphNode<T> node = vertices.get(vertex);
        if (node == null) throw new IllegalArgumentException();

        Set<GraphNode<T>> visited = new HashSet<>();
        StringBuilder strBuilder = new StringBuilder();
        Deque<GraphNode<T>> stack = new ArrayDeque<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            GraphNode<T> current = stack.pop();
            if (visited.contains(current)) continue;

            visited.add(current);
            strBuilder.append(current.getValue()).append(", ");

            List<GraphNode<T>> unvisitedNeighbors = adjacencyList.get(current).stream()
                    .filter(n -> !visited.contains(n))
                    .toList();

            stack.addAll(unvisitedNeighbors);
        }
        strBuilder.setLength(strBuilder.length() - 2);

        return strBuilder.toString();
    }

    public String traverseBreadthFirst(T vertex) {
        GraphNode<T> node = vertices.get(vertex);
        if (node == null) throw new IllegalArgumentException();

        StringBuilder strBuilder = new StringBuilder();
        Set<GraphNode<T>> visited = new HashSet<>();
        Queue<GraphNode<T>> queue = new ArrayDeque<>();
        queue.offer(node);


        while (!queue.isEmpty()) {
            GraphNode<T> current = queue.poll();
            if (visited.contains(current)) continue;

            visited.add(current);
            strBuilder.append(current.getValue()).append(", ");

            List<GraphNode<T>> unvisitedNeighbors = adjacencyList.get(current).stream()
                    .filter(n -> !visited.contains(n))
                    .toList();

            queue.addAll(unvisitedNeighbors);
        }
        strBuilder.setLength(strBuilder.length() - 2);

        return strBuilder.toString();
    }

    public List<T> topologicalSort() {
        if (hasCycle()) throw new IllegalStateException();
        Deque<GraphNode<T>> stack = new ArrayDeque<>();
        Set<GraphNode<T>> visited = new HashSet<>();
        vertices.values()
                .forEach(n -> topologicalSort(n, visited, stack));

        return stack.stream().map(n -> stack.pop().getValue()).toList();
    }

    private void topologicalSort(GraphNode<T> node, Set<GraphNode<T>> visited,
                                 Deque<GraphNode<T>> stack) {
        if (visited.contains(node)) return;
        visited.add(node);

        adjacencyList.get(node)
                .forEach(neighbour -> topologicalSort(neighbour, visited, stack));

        stack.push(node);
    }

    public boolean hasCycle() {
        Set<GraphNode<T>> all = new HashSet<>(vertices.values());
        Set<GraphNode<T>> visiting = new HashSet<>();
        Set<GraphNode<T>> visited = new HashSet<>();

        return all.stream()
                .anyMatch(node -> hasCycle(node, visiting, visited));
    }

    private boolean hasCycle(GraphNode<T> node, Set<GraphNode<T>> visiting, Set<GraphNode<T>> visited) {
        visiting.add(node);

        boolean hasCycle = adjacencyList.get(node).stream()
                .anyMatch(neighbour -> visiting.contains(neighbour)
                        || (!visited.contains(neighbour) && hasCycle(neighbour, visiting, visited)));

        visiting.remove(node);
        visited.add(node);

        return hasCycle;
    }

    @Override
    public String toString() {
        return adjacencyList.entrySet().stream()
                .sorted(Comparator.comparing(entry -> entry.getKey().getValue()))
                .map(entry -> MessageFormat.format("{0}: {1}"
                        , entry.getKey().getValue(), entry.getValue().stream()
                                .map(GraphNode::getValue)
                                .toList()))
                .collect(Collectors.joining("\n"));
    }
}