package io.github.aliazani.nonlinear.graph.directed;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

public class DirectedGraph<T extends Comparable<T>> {
    private final Map<T, DirectedGraphNode<T>> vertices;
    private final Map<DirectedGraphNode<T>, List<DirectedGraphNode<T>>> adjacencyList;

    public DirectedGraph() {
        vertices = new HashMap<>();
        adjacencyList = new HashMap<>();
    }

    public void addVertex(T value) {
        if (value == null) throw new IllegalArgumentException();

        DirectedGraphNode<T> node = new DirectedGraphNode<>(value);
        vertices.putIfAbsent(value, node);
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public T removeVertex(T value) {
        DirectedGraphNode<T> node = vertices.get(value);
        if (node == null) throw new IllegalArgumentException();

        adjacencyList.keySet().forEach(n -> adjacencyList.get(n).remove(node));
        adjacencyList.remove(node);
        vertices.remove(value);

        return node.getValue();
    }

    public void addEdge(T from, T to) {
        DirectedGraphNode<T> fromNode = vertices.get(from);
        DirectedGraphNode<T> toNode = vertices.get(to);
        if (fromNode == null || toNode == null) throw new IllegalArgumentException();

        adjacencyList.get(fromNode).add(toNode);
    }

    public void removeEdge(T from, T to) {
        DirectedGraphNode<T> fromNode = vertices.get(from);
        DirectedGraphNode<T> toNode = vertices.get(to);
        if (fromNode == null || toNode == null) throw new IllegalArgumentException();

        adjacencyList.get(fromNode).remove(toNode);
    }

    public List<T> findNeighbors(T value) {
        DirectedGraphNode<T> node = vertices.get(value);
        if (node == null) throw new IllegalArgumentException();

        return adjacencyList.get(node).stream().map(DirectedGraphNode::getValue).toList();
    }

    public boolean queryEdge(T from, T to) {
        DirectedGraphNode<T> fromNode = vertices.get(from);
        DirectedGraphNode<T> toNode = vertices.get(to);
        if (fromNode == null || toNode == null) throw new IllegalArgumentException();

        return adjacencyList.get(fromNode).contains(toNode);
    }

    public String traverseDepthFirst(T vertex) {
        DirectedGraphNode<T> node = vertices.get(vertex);
        if (node == null) throw new IllegalArgumentException();

        StringBuilder strBuilder = new StringBuilder();
        traverseDepthFirst(node, new HashSet<>(), strBuilder);
        strBuilder.setLength(strBuilder.length() - 2);

        return strBuilder.toString();
    }

    private void traverseDepthFirst(DirectedGraphNode<T> node, Set<DirectedGraphNode<T>> visited, StringBuilder strBuilder) {
        strBuilder.append(node.getValue()).append(", ");
        visited.add(node);

        adjacencyList.get(node).stream()
                .filter(n -> !visited.contains(n))
                .forEach(n -> traverseDepthFirst(n, visited, strBuilder));
    }

    public String traverseDepthFirst2(T vertex) {
        DirectedGraphNode<T> node = vertices.get(vertex);
        if (node == null) throw new IllegalArgumentException();

        Set<DirectedGraphNode<T>> visited = new HashSet<>();
        StringBuilder strBuilder = new StringBuilder();
        Deque<DirectedGraphNode<T>> stack = new ArrayDeque<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            DirectedGraphNode<T> current = stack.pop();
            if (visited.contains(current)) continue;

            visited.add(current);
            strBuilder.append(current.getValue()).append(", ");

            List<DirectedGraphNode<T>> unvisitedNeighbors = adjacencyList.get(current).stream()
                    .filter(n -> !visited.contains(n))
                    .toList();

            stack.addAll(unvisitedNeighbors);
        }
        strBuilder.setLength(strBuilder.length() - 2);

        return strBuilder.toString();
    }

    public String traverseBreadthFirst(T vertex) {
        DirectedGraphNode<T> node = vertices.get(vertex);
        if (node == null) throw new IllegalArgumentException();

        StringBuilder strBuilder = new StringBuilder();
        Set<DirectedGraphNode<T>> visited = new HashSet<>();
        Queue<DirectedGraphNode<T>> queue = new ArrayDeque<>();
        queue.offer(node);


        while (!queue.isEmpty()) {
            DirectedGraphNode<T> current = queue.poll();
            if (visited.contains(current)) continue;

            visited.add(current);
            strBuilder.append(current.getValue()).append(", ");

            List<DirectedGraphNode<T>> unvisitedNeighbors = adjacencyList.get(current).stream()
                    .filter(n -> !visited.contains(n))
                    .toList();

            queue.addAll(unvisitedNeighbors);
        }
        strBuilder.setLength(strBuilder.length() - 2);

        return strBuilder.toString();
    }

    public List<T> topologicalSort() {
        if (hasCycle()) throw new IllegalStateException();
        Deque<DirectedGraphNode<T>> stack = new ArrayDeque<>();
        Set<DirectedGraphNode<T>> visited = new HashSet<>();
        vertices.values()
                .forEach(n -> topologicalSort(n, visited, stack));

        return stack.stream().map(n -> stack.pop().getValue()).toList();
    }

    private void topologicalSort(DirectedGraphNode<T> node, Set<DirectedGraphNode<T>> visited,
                                 Deque<DirectedGraphNode<T>> stack) {
        if (visited.contains(node)) return;
        visited.add(node);

        adjacencyList.get(node)
                .forEach(neighbour -> topologicalSort(neighbour, visited, stack));

        stack.push(node);
    }

    public boolean hasCycle() {
        Set<DirectedGraphNode<T>> all = new HashSet<>(vertices.values());
        Set<DirectedGraphNode<T>> visiting = new HashSet<>();
        Set<DirectedGraphNode<T>> visited = new HashSet<>();

        return all.stream()
                .anyMatch(node -> hasCycle(node, visiting, visited));
    }

    private boolean hasCycle(DirectedGraphNode<T> node, Set<DirectedGraphNode<T>> visiting, Set<DirectedGraphNode<T>> visited) {
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
                                .map(DirectedGraphNode::getValue)
                                .toList()))
                .collect(Collectors.joining("\n"));
    }
}