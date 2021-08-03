//A sparse matrix can be represented as a sequence of rows, each of which is a sequence of (column-number, value) pairs of the nonzero values in the row.
//So let's create a non-zero array for A, and do multiplication on B.
//Time O(A.length * A[0].length + numNonZeroValues(A) * B[0].length)
public int[][] multiply(int[][] A, int[][] B) {
    int m = A.length, n = A[0].length, nB = B[0].length;
    int[][] result = new int[m][nB];

    List[] indexA = new List[m];
    for(int i = 0; i < m; i++) {
        List<Integer> numsA = new ArrayList<>();
        for(int j = 0; j < n; j++) {
            if(A[i][j] != 0){
                numsA.add(j); 
                numsA.add(A[i][j]);
            }
            }
            indexA[i] = numsA;
        }

        for(int i = 0; i < m; i++) {
            List<Integer> numsA = indexA[i];
            for(int p = 0; p < numsA.size() - 1; p += 2) {
                int colA = numsA.get(p);
                int valA = numsA.get(p + 1);
                for(int j = 0; j < nB; j ++) {
                    int valB = B[colA][j];
                    result[i][j] += valA * valB;
                }
            }
        }

        return result;   
    }

// brutal force that skips 0
public class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length, n = A[0].length, nB = B[0].length;
        int[][] C = new int[m][nB];

        for(int i = 0; i < m; i++) {
            for(int k = 0; k < n; k++) {
                if (A[i][k] != 0) {
                    for (int j = 0; j < nB; j++) {
                        if (B[k][j] != 0) C[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }
        return C;   
    }
}
