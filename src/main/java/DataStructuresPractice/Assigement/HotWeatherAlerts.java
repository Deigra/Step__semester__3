package DataStructuresPractice.Assigement;

public class HotWeatherAlerts {

    static int countAlerts(int[] readings, int k, int threshold) {
        long target = (long) k * threshold;
        long sum = 0;
        int count = 0;
        for (int i = 0; i < readings.length; i++) {
            sum += readings[i];                   // new reading enters
            if (i >= k) sum -= readings[i - k];   // old reading leaves
            if (i >= k - 1 && sum >= target) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countAlerts(new int[]{2, 2, 2, 2, 5, 5, 5, 8}, 3, 4)); // 3
    }
}
// Recalculating every block: Time O(n * k), Space O(1)
// Sliding window: Time O(n), Space O(1)