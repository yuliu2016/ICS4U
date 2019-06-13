package ics4u.connect4.evan;

import java.awt.geom.Point2D;

class Board {
    private int n;
    private int[][] grid;

    /**
     * @param n the number of pieces in a line that you need to win
     * @param x the length of the board across axis 1. the width of the board
     * @param y the length of the board across axis 0. the height of the board
     */
    Board(int n, int x, int y) {
        grid = new int[y][x];
        for (int i = 0; i < y; i++)
            for (int j = 0; j < x; j++)
                grid[i][j] = 0;
        this.n = n;
    }

    /**
     * @return the grid
     * -1: tie
     * 0: no piece
     * 1: player 1
     * 2: player 2
     */
    int[][] getGrid() {
        return grid;
    }

    /**
     * @return the width of the board. the length of the grid in axis 0
     */
    int getWidth() {
        return grid.length;
    }

    /**
     * @return the height of the board. the length of the grid in axis 1
     */
    int getHeight() {
        return grid[0].length;
    }

    /**
     * @param player the player who is placing the piece
     * @param x      the x coordinate where the piece is trying to be placed at
     * @return whether the placement was successful
     */
    Point2D place(int player, int x) {
        for (int y = 0; y < grid[0].length && x >= 0 && x <= grid[0].length; y++)
            if (grid[y][x] == 0) {
                grid[y][x] = player;
                return new Point2D.Double(x, y);
            }
        return null;
    }

    /**
     * @return the result of the check:
     * -1: tie
     * 0: no winner
     * 1: player 1 wins
     * 2: player 2 wins
     */
    int check() {
        StringBuffer gridStrBuff = new StringBuffer();
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                gridStrBuff.append(grid[i][j]);
        String gridStr = gridStrBuff.toString();

        String[] player1Checks = new String[]{
                t("1", this.grid[0].length, this.n),
                t("1", 1, this.n),
                t("1", this.grid[0].length - 1, this.n),
                t("1", this.grid[0].length + 1, this.n)
        };

        for (int i = 0; i < player1Checks.length; i++)
            if (gridStr.matches(player1Checks[i]))
                return 1;
            else if (gridStr.matches(player1Checks[i].replace('1', '2')))
                return 2;
        if (isFull()) return -1;
        else return 0;
    }

    private boolean isFull() {
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == 0)
                    return false;
        return true;
    }

    /**
     * @param str     the string that is searched for
     * @param spacing the distance between each #str
     * @param count   the number of repeats
     * @return an uncompiled Pattern that searches for #str that is repeated #count times, and is spaced apart by #spacing
     */
    private String t(String str, int spacing, int count) {
        return ".*" + str + "(.{" + (spacing - 1) + "}" + str + "){" + (count - 1) + "}.*";
    }

    /**
     * @return the board as it should be displayed to the user
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = 0; j < grid[0].length; j++)
                stringBuffer.append(grid[i][j]);
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }

    void reset() {
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                grid[i][j] = 0;
    }
}
