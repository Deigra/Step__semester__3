package DataStructuresPractice.Assigement;

public class TicketPriceSlot {

    static int findSlot(int[] prices, int newPrice) {
        int low = 0, high = prices.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (prices[mid] == newPrice) return mid;
            if (prices[mid] < newPrice) low = mid + 1;
            else high = mid - 1;
        }
        return low;   // insertion position
    }

    public static void main(String[] args) {
        int[] p = {120, 150, 200, 260};
        System.out.println(findSlot(p, 150)); // 1
        System.out.println(findSlot(p, 210)); // 3
        System.out.println(findSlot(p, 300)); // 4
    }
}
// Linear scan: Time O(n), Space O(1)
// Binary search: Time O(log n), Space O(1)