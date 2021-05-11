package net.thumbtack.school.matrix;

import java.util.*;

public class MatrixNonSimilarRows {

    private int[][] matrix;

    public MatrixNonSimilarRows(int[][] matrix) {
        this.matrix = matrix;
    }

    public Set<int[]> getNonSimilarRows() {

        Set<int[]> set = new LinkedHashSet<>();
        boolean[] check = new boolean[matrix.length];

        for (int i = 0; i < matrix.length - 1; i++) {
            if (check[i] ) {
                continue;
            }

            Set<Integer> s1 = new TreeSet<>();
            for (int q : matrix[i]) {
                s1.add(q);
            }

            for (int j = i + 1; j < matrix.length; j++) {

                boolean equal = true;
                if (check[j] ) {
                    continue;
                }
                Set<Integer> s2 = new TreeSet<>();
                for (int q : matrix[j]) {
                    s2.add(q);
                }

                if (!s1.equals(s2)) {
                    equal = false;

                }
                if (equal) {
                    check[j] = true;
                }
            }
        }
        for (int i = 0; i < check.length; i++) {
            if (!check[i]) {
                set.add(matrix[i]);
            }
        }

        return set;

    }


}

