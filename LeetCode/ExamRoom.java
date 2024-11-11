package LeetCode;

import java.util.TreeSet;

public class ExamRoom {

    public static void main(String[] args) {
        ExamRoom room = new ExamRoom(10);
        System.out.print(room.seat() + " " + room.seat()
        + " " + room.seat()
        + " " + room.seat() + " ");

        room.leave(4);

        System.out.print(room.seat());
    }

    int capacity = 0;
    TreeSet<Integer> seats;

    public ExamRoom(int n) {
        capacity = n;
        seats = new TreeSet<>();
    }

    public int seat() {
        int seatAllotted = 0;

        if (seats.size() > 0) {
            Integer prevSeat = null;
            int maxDistance = seats.first();
            for (Integer currSelection : seats) {
                if (prevSeat != null) {
                    int currDistance = (currSelection - prevSeat) / 2;
                    if (maxDistance < currDistance) {
                        maxDistance = currDistance;
                        seatAllotted = prevSeat + maxDistance;
                    }
                }
                prevSeat = currSelection;
            }
            if (maxDistance < capacity - 1 - seats.last()) {
                seatAllotted = capacity - 1;
            }
        }
        seats.add(seatAllotted);
        return seatAllotted;
    }

    public void leave(int p) {
        seats.remove(p);
    }
}