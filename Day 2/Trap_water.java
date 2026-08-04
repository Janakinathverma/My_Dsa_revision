class Trap_water {
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;

        int n = height.length;
        int p[] = new int[n]; // Prefix Max
        int s[] = new int[n]; // Suffix Max
        int area = 0;

        // Single loop to calculate both Prefix and Suffix Max
        for (int i = 0; i < n; i++) {
            // Prefix Max (0 se i tak)
            p[i] = (i == 0) ? height[i] : Math.max(p[i - 1], height[i]);

            // Suffix Max (n-1 se j tak)
            int j = n - 1 - i;
            s[j] = (j == n - 1) ? height[j] : Math.max(s[j + 1], height[j]);
        }

        // Calculate Trapped Water
        for (int i = 0; i < n; i++) {
            area += Math.min(p[i], s[i]) - height[i];
        }

        return area;
    }
}