package net.thumbtack.school.introduction;

import java.math.*;

public class FirstSteps {

    public int sum(int x, int y) {
        return x + y;
    }

    public int mul(int x, int y) {
        return x * y;
    }

    public int div(int x, int y) {
        if (y != 0) {
            return x / y;
        } else
            return 0;
    }

    public int mod(int x, int y) {
        if (y != 0) {
            return x % y;
        } else
            return 0;
    }

    public boolean isEqual(int x, int y) {
        return x == y;

    }

    public boolean isGreater(int x, int y) {
        return x > y;

    }

    public boolean isInsideRect(int xLeft, int yTop, int xRight, int yBottom, int x, int y) {
        if (xLeft < xRight && yTop < yBottom) {
            return (x >= xLeft && x <= xRight && y >= yTop && y <= yBottom);

        } else
            return false;

    }

    public int sum(int[] array) {
        int s = 0;

        if (array.length > 0) {
            for (int i : array) {
                s = s + i;
            }
            return s;

        } else return 0;


    }

    public int mul(int[] array) {
        int m = 1;
        int length = array.length;
        if (length > 0) {
            for (int i : array) {
                m = m * i;
            }
            return m;

        } else return 0;


    }

    public int min(int[] array) {
        if (array.length > 0) {
            int a = array[0];
            for (int i : array) {
                if (a > i) {
                    a = i;
                }
            }
            return a;
        } else return Integer.MAX_VALUE;
    }

    public int max(int[] array) {
        if (array.length > 0) {
            int a = array[0];
            for (int i : array) {
                if (a < i) {
                    a = i;
                }
            }
            return a;
        } else return Integer.MIN_VALUE;
    }

    public double average(int[] array) {
        if (array.length > 0) {
            double a = 0;
            for (int i : array) {
                a = a + i;
            }
            return a / array.length;

        } else return 0;
    }

    public boolean isSortedDescendant(int[] array) {
        if (array.length > 1) {
            int check = 1;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] <= array[i + 1]) {
                    check = 0;
                    break;
                }
            }
            return  (check == 1);

        } else return true;
    }

    public void cube(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] * array[i] * array[i];
        }
    }

    public boolean find(int[] array, int value) {


        for (int i : array) {

            if (value == i) {

                return true;
            }


        }
        return false;

    }

    public void reverse(int[] array) {
        int tmp;
        for (int i = 0; i < array.length / 2; i++) {
            tmp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = tmp;
        }
    }

    public boolean isPalindrome(int[] array) {

        for (int i = 0; i < array.length / 2; i++) {
            if (array[i] != array[array.length - 1 - i]) {

                return false;

            }
        }

        return true;

    }

    public int sum(int[][] matrix) {
        int a = 0;
        for (int i[] : matrix) {
            for (int j : i) {
                a = a + j;
            }
        }
        return a;
    }

    public int max(int[][] matrix) {
        int check = 0;
        if (matrix.length == 0) {
            return Integer.MIN_VALUE;
        } else {
            for (int i[] : matrix) {
                if (i.length == 0) {
                    check ++;
                }
            }
            if (check == matrix.length) {
                return Integer.MIN_VALUE;
            }
        }

        int a = matrix[0][0];
        for (int i[] : matrix) {
            for (int j : i) {
                if (a < j) {
                    a = j;

                }
            }
        }
        return a;
    }

    public int diagonalMax(int[][] matrix) {

        if (matrix.length == 0) {
            return Integer.MIN_VALUE;
        }
        int check = 0;
        for (int i []:matrix) {
            if (i.length == 0) {
                check ++;
            }
        }
        if (check == matrix.length) {
            return Integer.MIN_VALUE;
        }

        int a = matrix[0][0];
        for (int i = 0; i < matrix.length; i++) {
            if (a < matrix[i][i]) {
                a = matrix[i][i];
            }
        }
        return a;
    }

    public boolean isSortedDescendant(int[][] matrix) {
        if (matrix.length > 1) {

            for (int i[]:  matrix) {
                if (!isSortedDescendant(i))
                    return false;
            }
            return true;
        } else return true;
    }

}






