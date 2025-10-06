import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;

public class MergeOverLappingIntervals {
    public static void main (String[] args) {
        /*
            Input: [[1,3],[2,6],[7,8],[8,10],[15,18]]
            Output: [[1,6],[7,10],[15,18]]
            Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
         */
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1,3));
        intervals.add(new Interval(2,6));
        intervals.add(new Interval(7,8));
        intervals.add(new Interval(8,10));
        intervals.add(new Interval(15,18));

        List<Interval> mergedIntervals = merge(intervals,
                (a, b) -> a.end >= b.start,
                (a, b) -> new Interval(a.start, Math.max(a.end, b.end)));
        for (Interval interval : mergedIntervals) {
            System.out.println("[" + interval.start + "," + interval.end + "]");
        }
    }
    private static List<Interval> merge(List<Interval> intervals,
                                        BiPredicate<Interval, Interval> overlap,
                                        BinaryOperator<Interval> merger) {
        if (intervals == null || intervals.isEmpty()) { return intervals; }
        intervals.sort(Comparator.comparingInt(interval -> interval.start));
        Interval current = intervals.getFirst();
        List<Interval> result = new ArrayList<>();
        for (int i =1; i < intervals.size(); i++) {
            Interval next = intervals.get(i);
            if (overlap.test(current, next)) {
                current = merger.apply(current, next);
            } else {
                result.add(current);
                current = next;
            }

        }
        result.add(current);
        return result;
    }
}

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}
