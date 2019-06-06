package ics4u.connect4;

import java.io.*;

public class Connect4 {

    private int[][] board;
    private int rows;
    private int columns;
    private int n;

    private static final int kEmpty = 0;
    private static final int kFirst = 1;
    private static final int kSecond = 2;

    private int player;

    private static final int kPlaying = 0;
    private static final int kWin = 1;
    private static final int kDraw = 2;

    private int state;

    public int[][] getBoard() {
        return board;
    }

    public Connect4() {
        init(6, 7, 4);
    }

    public Connect4(int rows, int columns, int n) {
        init(rows, columns, n);
    }

    public Connect4(File file) {
        assert file != null;
        try {
            deserializeFile(file);
        } catch (IOException e) {
            init(6, 7, 4);
        }
    }

    public int getState() {
        return state;
    }

    private void setState(int state) {
        this.state = state;
    }

    public int getPlayer() {
        return player;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getN() {
        return n;
    }

    public void init(int rows, int columns, int n) {
        this.rows = rows;
        this.columns = columns;
        this.n = n;
        board = new int[rows][];
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < rows; i++) {
            board[i] = new int[columns];
            for (int j = 0; j < columns; j++) {
                board[i][j] = 0;
            }
        }
        player = kFirst;
        setState(kPlaying);
    }

    boolean isValidMove(int column) {
        return board[rows - 1][column] == kEmpty;
    }

    void checkBoardFilled() {
        if (state != kPlaying) return;
        for (int i = 0; i < columns; i++) {
            if (isValidMove(i)) {
                return;
            }
        }
        setState(kDraw);
    }

    private void checkHorizontal(int row, int column) {
        if (state != kPlaying) return;
        int count = 1;
        int c = column;
        while (c > 0 && c < columns - 1 && board[row][c - 1] == player) c--;
        count += column - c;
        c = column;
        while (c > 0 && c < columns - 1 && board[row][c + 1] == player) c++;
        count += c - column;
        if (count >= n) setState(kWin);
    }

    private void checkVertical(int row, int column) {
        if (state != kPlaying) return;
        int count = 1;
        int r = row;
        while (r > 0 && r < rows - 1 && board[r - 1][column] == player) r--;
        count += row - r;
        r = row;
        while (r > 0 && r < rows - 1 && board[r + 1][column] == player) r++;
        count += r - row;
        if (count >= n) setState(kWin);
    }

    private void checkDiagonal(int row, int column) {
        if (state != kPlaying) return;
        int count = 1;
        int r = row;
        int c = column;
        while (r > 0 && r < rows && c > 0 && c < columns - 1 && board[r - 1][c - 1] == player) {
            r--;
            c--;
            count++;
        }
        r = row;
        c = column;
        while (r > 0 && r < rows && c > 0 && c < columns - 1 && board[r + 1][c + 1] == player) {
            r++;
            c++;
            count++;
        }
        if (count >= n) setState(kWin);
    }

    private void checkInverseDiagonal(int row, int column) {
        if (state != kPlaying) return;
        int count = 1;
        int r = row;
        int c = column;
        while (r > 0 && r < rows && c > 0 && c < columns - 1 && board[r + 1][c - 1] == player) {
            r++;
            c--;
            count++;
        }
        r = row;
        c = column;
        while (r > 0 && r < rows && c > 0 && c < columns - 1 && board[r - 1][c + 1] == player) {
            r--;
            c++;
            count++;
        }
        if (count >= n) setState(kWin);
    }

    void move(int column) {
        if (!isValidMove(column) || state != kPlaying) {
            throw new IllegalStateException();
        }
        int row = rows - 1;
        while (row > 0 && board[row - 1][column] == kEmpty) row--;
        board[row][column] = player;

        checkHorizontal(row, column);
        checkVertical(row, column);
        checkDiagonal(row, column);
        checkInverseDiagonal(row, column);
        checkBoardFilled();

        if (state == kPlaying) {
            switch (player) {
                case kFirst:
                    player = kSecond;
                    break;
                case kSecond:
                    player = kFirst;
                    break;
                default:
                    throw new IllegalStateException();
            }
        }
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer("board:\n");
        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < board[i].length; j++) {
                int it = board[i][j];
                if (it == kEmpty) buffer.append('*');
                else if (it == kFirst) buffer.append('r');
                else buffer.append('b');
                buffer.append(' ');
            }
            buffer.append("\n");
        }
        buffer.append("Game state: ");

        if (state == kPlaying) buffer.append("playing");
        else if (state == kWin) buffer.append("win");
        else buffer.append("tie");
        buffer.append('\n');

        buffer.append("player: ");
        if (player == kFirst) {
            buffer.append('r');
        } else if (player == kSecond) {
            buffer.append('b');
        }

        return buffer.toString();
    }

    String serialize() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(rows);
        buffer.append('\n');
        buffer.append(columns);
        buffer.append('\n');
        buffer.append(n);
        buffer.append('\n');
        buffer.append(player);
        buffer.append('\n');
        buffer.append(state);
        buffer.append('\n');
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                buffer.append(board[i][j]);
                buffer.append(',');
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }

    void serializeToFile(File file) throws IOException {
        String s = serialize();
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        writer.print(s);
        writer.close();
    }

    void deserializeFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        rows = parse(br.readLine());
        columns = parse(br.readLine());
        board = new int[rows][];
        n = parse(br.readLine());
        player = parse(br.readLine());
        state = parse(br.readLine());
        int row = 0;
        String line;
        while ((line = br.readLine()) != null && row < rows - 1) {
            String[] data = line.split(",");
            if (data.length != columns) throw new IllegalArgumentException("Data has wrong size");
            board[row] = new int[columns];
            for (int column = 0; column < columns; column++) {
                board[row][column] = parse(data[column]);
            }
            System.out.println();
        }
        if (row != rows - 1) throw new IllegalArgumentException("Data has wrong size");
        br.close();
    }


    static int parse(String s) {
        return Integer.parseInt(s);
    }
}
