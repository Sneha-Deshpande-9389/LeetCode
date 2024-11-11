package LeetCode;

import java.util.*;
import java.util.stream.Collectors;

class HeapNode implements Comparable<HeapNode> {
    Integer rating;
    String name;

    public HeapNode(int rating, String name) {
        this.rating = rating;
        this.name = name;
    }

    @Override
    public int compareTo(HeapNode o) {
        return this.rating.compareTo(o.rating);
    }
}
public class MovieRecommendation {
    public static void main(String[] args) {
        Map<String, Integer> movieRatingMap = new HashMap<>();
        movieRatingMap.put("MovieA", 6);
        movieRatingMap.put("MovieB", 7);
        movieRatingMap.put("MovieC", 8);
        movieRatingMap.put("MovieD", 9);
        System.out.println(new MovieRecommendation().findRecommendations(new String[][] {{"MovieA","MovieB"},
                        {"MovieB", "MovieC"}},
                movieRatingMap, 1, "MovieA").toString());

        System.out.println(new MovieRecommendation().findRecommendations(new String[][] {{"MovieA","MovieB"},
                        {"MovieA", "MovieC"}},
                movieRatingMap, 2, "MovieA").toString());
    }
    public List<String> findRecommendations(String[][] similarities, Map<String, Integer> movieRatingMap,
                                            int k, String movieSrc) {

        //dfs- O(E * V)
        //maxHeap (E * logE)
        //maxHeap = O(E)
        //1.adj map, visited, maxHeap
        //2.dfs

        PriorityQueue<HeapNode> minHeap = new PriorityQueue<>();//ToDo define heapNode

        int v = movieRatingMap.size();
        Map<String, List<String>> graph = new HashMap<>();
        for(String movie: movieRatingMap.keySet()) {
            graph.putIfAbsent(movie, new ArrayList<>());
        }
        for(String[] edge : similarities) {
            List<String> arr = graph.get(edge[0]);
            arr.add(edge[1]);
            graph.put(edge[0], arr);
        }
/*
a-b
a-c
b-c
a-b
*/
        List<String> visited = new ArrayList<>();
        dfs(movieSrc, visited, minHeap, k, graph, movieRatingMap);
        List<String> res = new ArrayList<>();
        minHeap.stream().forEach(heapNode -> res.add(heapNode.name));
        return res;
    }
    private void dfs(String movieSrc, List<String> visited, PriorityQueue<HeapNode> minHeap, int k,
             Map<String, List<String>> graph,  Map<String, Integer> movieRatingMap) {
        if(visited.contains(movieSrc)) {
            return;
        }
        visited.add(movieSrc);
        for(String connectedMovie : graph.get(movieSrc)) {
            minHeap.add(new HeapNode(movieRatingMap.get(connectedMovie), connectedMovie));
            if(minHeap.size() > k) {
                minHeap.poll();
            }
            dfs(connectedMovie, visited, minHeap, k, graph, movieRatingMap);
        }
    }
}
