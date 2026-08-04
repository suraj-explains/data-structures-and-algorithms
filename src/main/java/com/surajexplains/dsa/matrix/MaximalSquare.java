package com.surajexplains.dsa.matrix;

public class MaximalSquare {

    public static int maximalSquare(char[][] matrix) {
        int dp[][] = new int[matrix.length][matrix[0].length];
        int maxSide = 0;

        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){

                if (i==0 || j==0){
                    int val = matrix[i][j] - '0';
                    dp[i][j] = val;
                    maxSide = Math.max(maxSide,val);
                }

                else if(matrix[i][j] == '1'){
                    dp[i][j] = Math.min(dp[i][j-1] ,Math.min(dp[i-1][j],dp[i-1][j-1])) +1;
                    maxSide = Math.max(maxSide,dp[i][j]);
                }

            }
        }

        return maxSide * maxSide;
    }

}
