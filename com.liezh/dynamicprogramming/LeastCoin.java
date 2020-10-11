package dynamicprogramming;

import java.util.Arrays;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 */
public class LeastCoin {

    public int coinChange(int[] coins, int amount) {

        // results 记录所有阶段的结果，最多求解阶段只能时总额那么多所以初始化长度为amount-1
        int[] results = new int[amount + 1];
        // 初始值，因为初始时,金额等于零时所需硬币数一定为零
        results[0] = 0;
        // 最优解就是每个阶段产生结果的结合的累加，而且是最小值
        // 由于每个阶段都有边界条件，但是我们不可能每个可能都列出来，所以使用循环，在每次运算转移方程时判断
        for (int stage = 1; stage <= amount; ++stage) {
            // 首先假设当前阶段是无穷大
            results[stage] = Integer.MAX_VALUE;
            // 状态转移函数 f(x) = min{f(x-coins[j]) + 1}
            for (int j = 0; j < coins.length; ++j) {
                // 如果当前阶段的金额小于面额，下标会为负数
                // 如果但钱结果已经为最大值那个要方式+1，否则由于补码，导致无穷大会变为0影响结果
                if (stage > coins[j] && results[stage - coins[j]] != Integer.MAX_VALUE) {
                    // 尝试每种硬币计算后，当前阶段的运算结果，最小数就是当前阶段的最优解
                    results[stage] = Math.min(results[stage - coins[j]] + 1, results[stage]);
                }
            }
        }
        if (results[amount] == Integer.MAX_VALUE) {
            return -1;
        }
        System.out.println(Arrays.toString(results));
        return results[amount];
    }

    public static void main(String[] args) {
        LeastCoin leastCoin = new LeastCoin();
        int[] coins = {2, 5, 7};
        leastCoin.coinChange(coins, 27);
    }

}
