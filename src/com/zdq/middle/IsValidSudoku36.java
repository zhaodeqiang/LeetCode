package com.zdq.middle;

import java.util.HashMap;
import java.util.Map;

/**
 * 36. 有效的数独
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * <p>
 * <p>
 * 上图是一个部分填充的有效的数独。
 * <p>
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * ["5","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * [
 * ["8","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * 输出: false
 * 解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
 * 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
 * 说明:
 * <p>
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 给定数独序列只包含数字 1-9 和字符 '.' 。
 * 给定数独永远是 9x9 形式的。
 *
 * @author ZDQ
 */
public class IsValidSudoku36 {
    public static void main(String[] args) {

        char[][] board = {
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(isValidSudoku(board));
    }


    /**
     * 三次循环
     *
     * @param board 九宫格数独
     * @return 是否是有效数独
     */
    private static boolean isValidSudoku(char[][] board) {
        Map<Character, Integer> map = new HashMap<>(16);
        //遍历每一行
        for (char[] row : board) {
            for (char cell : row) {
                if (cell - '0' >= 0 && cell - '0' <= 9) {
                    map.put(cell, map.getOrDefault(cell, 0) + 1);
                    if (map.getOrDefault(cell, 0) > 1) {
                        return false;
                    }
                }
            }
            map.clear();
        }
        //遍历每个九宫格
        int cal = 0;
        int i;
        int j;
        int a = 3;
        int b = 3;
        while (cal < 9) {
            for (i = a - 3; i < a; i++) {
                for (j = b - 3; j < b; j++) {
                    char ch = board[i][j];
                    if (ch - '0' >= 0 && ch - '0' <= 9) {
                        map.put(ch, map.getOrDefault(ch, 0) + 1);
                        if (map.getOrDefault(ch, 0) > 1) {
                            return false;
                        }
                    }
                }
            }
            cal++;
            //第一层循环遍历结束时，需要遍历同一层的第二、第三个九宫格
            b += 3;
            if (cal % 3 == 0) {
                b = 3;
                a += 3;
            }
            map.clear();
        }
        // 遍历每一列
        i = 0;
        while (i < 9) {
            for (j = 0; j < board.length; j++) {
                char ch = board[j][i];
                if (ch - '0' >= 0 && ch - '0' <= 9) {
                    map.put(ch, map.getOrDefault(ch, 0) + 1);
                    if (map.getOrDefault(ch, 0) > 1) {
                        return false;
                    }
                }
            }
            map.clear();
            i++;
        }
        return true;
    }


    /**
     * 使用hashmap遍历一次
     * O(1)
     *
     * @param board 九宫格数独
     * @return 数独是否有效
     */
    @SuppressWarnings("unchecked")
    private static boolean isValidSudoku1(char[][] board) {
        // init data
        HashMap<Character, Integer>[] rows = new HashMap[9];
        HashMap<Character, Integer>[] columns = new HashMap[9];
        HashMap<Character, Integer>[] boxes = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<>(16);
            columns[i] = new HashMap<>(16);
            boxes[i] = new HashMap<>(16);
        }
        // validate a board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int boxIndex = (i / 3) * 3 + j / 3;
                    // keep the current cell value
                    rows[i].put(num, rows[i].getOrDefault(num, 0) + 1);
                    columns[j].put(num, columns[j].getOrDefault(num, 0) + 1);
                    boxes[boxIndex].put(num, boxes[boxIndex].getOrDefault(num, 0) + 1);

                    // check if this value has been already seen before
                    if (rows[i].get(num) > 1 || columns[j].get(num) > 1 || boxes[boxIndex].get(num) > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 使用数组遍历一次
     * O(1)
     *
     * @param board 九宫格数独
     * @return 数独是否有效
     */
    public boolean isValidSudoku2(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] col = new int[9][9];
        int[][] boxes = new int[9][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int indexBox = (i / 3) * 3 + j / 3;
                    if (rows[i][num] == 1) {
                        return false;
                    } else {
                        rows[i][num] = 1;
                    }
                    if (col[j][num] == 1) {
                        return false;
                    } else {
                        col[j][num] = 1;
                    }
                    if (boxes[indexBox][num] == 1) {
                        return false;
                    } else {
                        boxes[indexBox][num] = 1;
                    }
                }
            }
        }
        return true;
    }


    /**
     * 遍历每个元素，检查当前数字在每行、每列、每个3×3方格中是否存在相同数字
     *
     * @param board 9*9数独
     * @return 是否为有效数独
     */
    public boolean isValidSudoku3(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    if (!isValidRow(board, i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isValidRow(char[][] board, int row, int col) {
        for (int i = col + 1; i < board[0].length; i++) {
            if (board[row][i] == board[row][col]) {
                return false;
            }
        }
        for (int i = row + 1; i < board.length; i++) {
            if (board[i][col] == board[row][col]) {
                return false;
            }
        }
        int s = row / 3, t = col / 3;
        for (int i = s * 3; i < 3 * s + 3; i++) {
            for (int j = t * 3; j < 3 * t + 3; j++) {
                if (i == row && j == col) {
                    continue;
                }
                if (board[i][j] == board[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }
}
