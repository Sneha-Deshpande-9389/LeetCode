package LeetCode;

import javafx.util.Pair;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class SnakeGame {
    int ht, wd, foodIndex;
    int[][] food;
    HashMap<Pair<Integer, Integer>, Boolean> snakeCellMap;
    Deque<Pair<Integer, Integer>> snakePath;

    public SnakeGame(int width, int height, int[][] food) {
        this.ht = height;
        this.wd = width;
        this.food = food;
        this.foodIndex = 0;
        snakeCellMap = new HashMap<>();
        snakeCellMap.put(new Pair(0, 0), true);

        snakePath = new LinkedList<>();
        snakePath.offer(new Pair(0,0));
    }

    public int move(String direction) {
        Pair<Integer, Integer> currHeadCell = snakePath.peekFirst();
        int newHeadRow = currHeadCell.getKey();
        int newHeadCol = currHeadCell.getValue();

        switch(direction) {
            case "U" :
                newHeadRow--;
                break;
            case "D" :
                newHeadRow++;
                break;
            case "R" :
                newHeadCol++;
                break;
            case "L" :
                newHeadCol--;
                break;
        }
        Pair<Integer, Integer> newHeadCell = new Pair(newHeadRow, newHeadCol);
        boolean isInColBoundary, isInRowBoundary, doesBitesItself;

        isInColBoundary = newHeadCol >= this.wd || newHeadCol < 0;
        isInRowBoundary = newHeadRow >= this.ht || newHeadRow < 0;

        Pair<Integer, Integer> currTail = snakePath.peekLast();
        doesBitesItself = snakeCellMap.containsKey(newHeadCell) && !(newHeadRow == currTail.getKey() && newHeadCol == currTail.getValue());
        if(isInColBoundary || isInRowBoundary || doesBitesItself) {
            return -1;
        }

        if(this.foodIndex < this.food.length &&
                this.food[this.foodIndex][0] == newHeadRow &&
                this.food[this.foodIndex][1] == newHeadCol) {
            this.foodIndex++;
        } else {
            snakeCellMap.remove(currTail);
            snakePath.pollLast();
        }

        this.snakePath.addFirst(newHeadCell);
        this.snakeCellMap.put(newHeadCell, true);

        return this.snakePath.size() -1 ;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */