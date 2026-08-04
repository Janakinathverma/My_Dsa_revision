import java.util.*;

public class Hashing {
    int n;
    int[] hash;

    Hashing(int n) {
        this.n = n;
        hash = new int[n];
        Arrays.fill(hash, Integer.MAX_VALUE);
    }

    // 1. Linear Probing
    void linearProbing() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter " + n + " elements:");

        for (int i = 0; i < n; i++) {
            int ele = sc.nextInt();
            int pos = Math.abs(ele) % n;

            while (hash[pos] != Integer.MAX_VALUE) {
                pos = (pos + 1) % n;
            }

            hash[pos] = ele;
        }
    }

    // 2. Random Probing
    void randomProbing() {
        Scanner sc = new Scanner(System.in);
        int R;
        do {
            R = 1 + (int)(Math.random() * (n - 1));
        } while (gcd(R, n) != 1);

        System.out.println("Selected Random Step (R): " + R);
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            int ele = sc.nextInt();
            int pos = Math.abs(ele) % n;

            while (hash[pos] != Integer.MAX_VALUE) {
                pos = (pos + R) % n;
            }

            hash[pos] = ele;
        }
    }

    // 3. Quadratic Probing: (hash1(key) + i^2) % n
    void quadraticProbing() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter " + n + " elements:");

        for (int k = 0; k < n; k++) {
            int ele = sc.nextInt();
            int basePos = Math.abs(ele) % n;
            int i = 0;
            int pos = basePos;

            // Jab tak space empty nahi milta, i ko increment karke quadratic jump karo
            while (hash[pos] != Integer.MAX_VALUE) {
                i++;
                pos = (basePos + i * i) % n;

                // Infinite loop safety
                if (i >= n) {
                    System.out.println("Cannot insert " + ele + " (Table full or probe cycle)");
                    break;
                }
            }

            if (hash[pos] == Integer.MAX_VALUE) {
                hash[pos] = ele;
            }
        }
    }

    // 4. Double Hashing: (hash1(key) + i * hash2(key)) % n
    // 4. Double Hashing: (h1(key) + i * h2(key)) % n
    void doubleHashing() {
        Scanner sc = new Scanner(System.in);

        // Secondary hash function ke liye Prime number (P < n)
        int prime = getLesserPrime(n);

        System.out.println("--- Double Hashing Setup ---");
        System.out.println("h1(key) = key % " + n);
        System.out.println("h2(key) = " + prime + " - (key % " + prime + ")\n");

        System.out.println("Enter " + n + " elements:");

        for (int k = 0; k < n; k++) {
            int ele = sc.nextInt();

            // 1. Primary Hash Function: h1(key)
            int h1 = Math.abs(ele) % n;

            // 2. Secondary Hash Function: h2(key)
            int h2 = prime - (Math.abs(ele) % prime);

            int i = 0;
            int pos = h1; // Initial position (when i = 0)

            // Collision resolution using h1 and h2
            while (hash[pos] != Integer.MAX_VALUE) {
                i++;
                // Formula: index = (h1(key) + i * h2(key)) % n
                pos = (h1 + i * h2) % n;

                // Infinite loop check
                if (i >= n) {
                    System.out.println("Cannot insert " + ele + " (Table full or probe cycle)");
                    break;
                }
            }

            if (hash[pos] == Integer.MAX_VALUE) {
                hash[pos] = ele;
                System.out.println("Inserted " + ele + " at index " + pos +
                        " [h1=" + h1 + ", h2=" + h2 + ", probes=" + i + "]");
            }
        }
    }

    // Helper: GCD for Random Probing
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // Helper: Find a prime number smaller than n for Double Hashing
    private int getLesserPrime(int n) {
        for (int i = n - 1; i >= 2; i--) {
            if (isPrime(i)) return i;
        }
        return 3; // Default fallback
    }

    private boolean isPrime(int val) {
        if (val <= 1) return false;
        for (int i = 2; i * i <= val; i++) {
            if (val % i == 0) return false;
        }
        return true;
    }

    void printHash() {
        System.out.println("\nHash Table:");
        for (int i = 0; i < n; i++) {
            if (hash[i] == Integer.MAX_VALUE) {
                System.out.print("EMPTY ");
            } else {
                System.out.print(hash[i] + " ");
            }
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of hashtable: ");
        int n = sc.nextInt();

        Hashing h = new Hashing(n);

        // h.linearProbing();
        // h.randomProbing();
        // h.quadraticProbing();
        h.doubleHashing();

        h.printHash();
    }
}