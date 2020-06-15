package com.zdq.hard;

/**
 * 37. 解数独
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * <p>
 * 一个数独的解法需遵循如下规则：
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 * <p>
 * Note:
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 *
 * @author ZDQ
 */
public class SolveSudoku37 {

    public static void main(String[] args) {
        String str = "[[\"5\",\"3\",\".\",\".\",\"7\",\".\",\".\",\".\",\".\"],[\"6\",\".\",\".\",\"1\",\"9\",\"5\",\".\",\".\",\".\"],[\".\",\"9\",\"8\",\".\",\".\",\".\",\".\",\"6\",\".\"],[\"8\",\".\",\".\",\".\",\"6\",\".\",\".\",\".\",\"3\"],[\"4\",\".\",\".\",\"8\",\".\",\"3\",\".\",\".\",\"1\"],[\"7\",\".\",\".\",\".\",\"2\",\".\",\".\",\".\",\"6\"],[\".\",\"6\",\".\",\".\",\".\",\".\",\"2\",\"8\",\".\"],[\".\",\".\",\".\",\"4\",\"1\",\"9\",\".\",\".\",\"5\"],[\".\",\".\",\".\",\".\",\"8\",\".\",\".\",\"7\",\"9\"]]";
        System.out.println(str.replace("[", "{").replace("]", "}")
                .replace("\"", "'"));
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        new SolveSudoku37().solveSudoku(board);
        for (char[] chars : board) {
            for (char ch : chars) {
                System.out.print((ch - '0' + "\t"));
            }
            System.out.println();
        }
        /*
         *  5	3	4	6	7	8	9	1	2
         *  6	7	2	1	9	5	3	4	8
         *  1	9	8	3	4	2	5	6	7
         *  8	5	9	7	6	1	4	2	3
         *  4	2	6	8	5	3	7	9	1
         *  7	1	3	9	2	4	8	5	6
         *  9	6	1	5	3	7	2	8	4
         *  2	8	7	4	1	9	6	3	5
         *  3	4	5	2	8	6	1	7	9
         */
    }


    final int scale = 9;
    int[] row = new int[scale], col = new int[scale];
    /**
     * ones数组表示0~2^9 - 1的整数中二进制表示中1的个数:如ones[7] = 3 ones[8] = 1
     * map数组表示2的整数次幂中二进制1所在位置（从0开始） 如 map[1] = 0,map[2] = 1, map[4] = 2
     */
    int[] ones = new int[1 << scale], map = new int[1 << scale];
    int[][] cell = new int[3][3];

    private void solveSudoku(char[][] board) {
        init();
        int cnt = fillState(board);
        dfs(cnt, board);
    }

    private void init() {
        for (int i = 0; i < scale; i++) {
            row[i] = col[i] = (1 << scale) - 1;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cell[i][j] = (1 << scale) - 1;
            }
        }
        //以上2个循环把数组的数初始化为二进制表示9个1，即一开始所以格子都可填
        for (int i = 0; i < scale; i++) {
            map[1 << i] = i;
        }
        //统计0~2^9 - 1的整数中二进制表示中1的个数
        for (int i = 0; i < 1 << scale; i++) {
            int n = 0;
            for (int j = i; j != 0; j ^= lowBit(j)) {
                n++;
            }
            ones[i] = n;
        }
    }

    private int fillState(char[][] board) {
        //统计board数组空格('.')的个数
        int cnt = 0;
        for (int i = 0; i < scale; i++) {
            for (int j = 0; j < scale; j++) {
                if (board[i][j] != '.') {
                    int t = board[i][j] - '1';
                    //数独中 i,j位置为数组下标，修改row col cell数组中状态
                    changeState(i, j, t);
                } else {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private boolean dfs(int cnt, char[][] board) {
        if (cnt == 0) {
            return true;
        }
        int min = 10, x = 0, y = 0;
        //剪枝，即找出当前所以空格可填数字个数最少的位置 记为x y
        for (int i = 0; i < scale; i++) {
            for (int j = 0; j < scale; j++) {
                if (board[i][j] == '.') {
                    int k = ones[get(i, j)];
                    if (k < min) {
                        min = k;
                        x = i;
                        y = j;
                    }
                }
            }
        }
        //遍历当前 x y所有可选数字
        for (int i = get(x, y); i != 0; i ^= lowBit(i)) {
            int t = map[lowBit(i)];

            changeState(x, y, t);
            board[x][y] = (char) ('1' + t);

            if (dfs(cnt - 1, board)) {
                return true;
            }

            //恢复现场
            changeState(x, y, t);
            board[x][y] = '.';
        }
        return false;
    }

    private void changeState(int x, int y, int t) {
        row[x] ^= 1 << t;
        col[y] ^= 1 << t;
        cell[x / 3][y / 3] ^= 1 << t;
    }

    /**
     * 对维护数组x行,y列的3个集合(行、列、九宫格)进行&运算
     * 就可以获得可选数字的集合(因为我们用1标识可填数字)
     */
    private int get(int x, int y) {
        return row[x] & col[y] & cell[x / 3][y / 3];
    }

    /**
     * 计算给定十进制整数的二进制最右边的1所代表的十进制数值
     * 奇数：1
     * 偶数：二进制最右边的1所代表的十进制数值
     *
     * @param x 十进制整数
     * @return 右边的1所代表的十进制数值
     */
    int lowBit(int x) {
        return (~x + 1) & x;
    }


    /**
     * box size
     */
    int n = 3;
    /**
     * row size
     */
    int rowSize = n * n;

    int[][] rows = new int[rowSize][rowSize + 1];
    int[][] columns = new int[rowSize][rowSize + 1];
    int[][] boxes = new int[rowSize][rowSize + 1];

    char[][] board;

    boolean sudokuSolved = false;

    public boolean couldPlace(int d, int row, int col) {
    /*
    Check if one could place a number d in (row, col) cell
    */
        int idx = (row / n) * n + col / n;
        return rows[row][d] + columns[col][d] + boxes[idx][d] == 0;
    }

    public void placeNumber(int d, int row, int col) {
    /*
    Place a number d in (row, col) cell
    */
        int idx = (row / n) * n + col / n;

        rows[row][d]++;
        columns[col][d]++;
        boxes[idx][d]++;
        board[row][col] = (char) (d + '0');
    }

    public void removeNumber(int d, int row, int col) {
    /*
    Remove a number which didn't lead to a solution
    */
        int idx = (row / n) * n + col / n;
        rows[row][d]--;
        columns[col][d]--;
        boxes[idx][d]--;
        board[row][col] = '.';
    }

    public void placeNextNumbers(int row, int col) {
    /*
    Call backtrack function in recursion
    to continue to place numbers
    till the moment we have a solution
    */
        // if we're in the last cell
        // that means we have the solution
        if ((col == rowSize - 1) && (row == rowSize - 1)) {
            sudokuSolved = true;
        }
        // if not yet
        else {
            // if we're in the end of the row
            // go to the next row
            if (col == rowSize - 1) {
                backtrack(row + 1, 0);
            }
            // go to the next column
            else {
                backtrack(row, col + 1);
            }
        }
    }

    public void backtrack(int row, int col) {
    /*
    Backtracking
    */
        // if the cell is empty
        if (board[row][col] == '.') {
            // iterate over all numbers from 1 to 9
            for (int d = 1; d < 10; d++) {
                if (couldPlace(d, row, col)) {
                    placeNumber(d, row, col);
                    placeNextNumbers(row, col);
                    // if sudoku is solved, there is no need to backtrack
                    // since the single unique solution is promised
                    if (!sudokuSolved) {
                        removeNumber(d, row, col);
                    }
                }
            }
        } else {
            placeNextNumbers(row, col);
        }
    }

    /**
     * 官方题解
     *
     * @param board 9*9数独
     */
    public void solveSudoku2(char[][] board) {
        this.board = board;

        // init rows, columns and boxes
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int d = Character.getNumericValue(num);
                    placeNumber(d, i, j);
                }
            }
        }
        backtrack(0, 0);
    }
}
