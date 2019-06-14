package ics4u.connect4;

import java.io.*;

public class Connect4 {

    static final int kEmpty = 0;
    static final int kFirst = 1;
    static final int kSecond = 2;

    static final int kPlaying = 0;
    static final int kWin = 1;
    static final int kDraw = 2;

    private int[][] board;
    private int rows;
    private int columns;
    private int n;
    private int player;
    private int state;

    private String firstPlayer;
    private int firstScore;

    private String secondPlayer;
    private int secondScore;

    private int draws;

    public Connect4(String firstPlayer, String secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        init(6, 7, 4);
    }

    public Connect4(File file) {
        if (file == null) {
            throw new NullPointerException("No file specified for connect four");
        }
        try {
            deserializeFile(file);
        } catch (IOException e) {
            this.firstPlayer = "RED";
            this.secondPlayer = "BLUE";
            init(6, 7, 4);
        }
    }

    static int readInt(BufferedReader br) throws IOException {
        return Integer.parseInt(br.readLine());
    }

    public int[][] getBoard() {
        return board;
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

    public int getState() {
        return state;
    }

    public String getFirstPlayer() {
        return firstPlayer;
    }

    public String getSecondPlayer() {
        return secondPlayer;
    }

    public String getPlayerName() {
        if (player == kFirst) {
            return firstPlayer;
        } else if (player == kSecond) {
            return secondPlayer;
        }
        throw new IllegalStateException();
    }

    public int getPlayer() {
        return player;
    }

    private void setWin() {
        if (player == kFirst) {
            firstScore++;
        } else if (player == kSecond) {
            secondScore++;
        }
        state = kWin;
    }

    private void setPlaying() {
        state = kPlaying;
    }

    private void setDraw() {
        draws++;
        state = kDraw;
    }

    public void init(int rows, int columns, int n) {
        this.rows = rows;
        this.columns = columns;
        this.n = n;
        board = new int[rows][];
        restartBoard();
    }

    public void restartBoard() {
        for (int i = 0; i < rows; i++) {
            board[i] = new int[columns];
            for (int j = 0; j < columns; j++) {
                board[i][j] = 0;
            }
        }
        player = kFirst;
        setPlaying();
    }

    boolean isValidMove(int column) {
        return board[rows - 1][column] == kEmpty;
    }

    private void checkBoardFilled() {
        if (state != kPlaying) return;
        for (int i = 0; i < columns; i++) {
            if (isValidMove(i)) {
                return;
            }
        }
        setDraw();
    }

    private void checkHorizontal(int row, int column) {
        if (state != kPlaying) return;
        int count = 1;
        int c = column;
        while (c > 0 && c < columns && board[row][c - 1] == player) c--;
        count += column - c;
        c = column;
        while (c >= 0 && c < columns - 1 && board[row][c + 1] == player) c++;
        count += c - column;
        if (count >= n) setWin();
    }

    private void checkVertical(int row, int column) {
        if (state != kPlaying) return;
        int count = 1;
        int r = row;
        while (r > 0 && r < rows && board[r - 1][column] == player) r--;
        count += row - r;
        r = row;
        while (r >= 0 && r < rows - 1 && board[r + 1][column] == player) r++;
        count += r - row;
        if (count >= n) setWin();
    }

    private void checkDiagonal(int row, int column) {
        if (state != kPlaying) return;
        int count = 1;
        int r = row;
        int c = column;
        while (r > 0 && r < rows && c > 0 && c < columns && board[r - 1][c - 1] == player) {
            r--;
            c--;
            count++;
        }
        r = row;
        c = column;
        while (r >= 0 && r < rows - 1 && c >= 0 && c < columns - 1 && board[r + 1][c + 1] == player) {
            r++;
            c++;
            count++;
        }
        if (count >= n) setWin();
    }

    private void checkInverseDiagonal(int row, int column) {
        if (state != kPlaying) return;
        int count = 1;
        int r = row;
        int c = column;
        while (r >= 0 && r < rows - 1 && c > 0 && c < columns && board[r + 1][c - 1] == player) {
            r++;
            c--;
            count++;
        }
        r = row;
        c = column;
        while (r > 0 && r < rows && c >= 0 && c < columns - 1 && board[r - 1][c + 1] == player) {
            r--;
            c++;
            count++;
        }
        if (count >= n) setWin();
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
            }
        }
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer("Current Board: \n");
        for (int i = 1; i <= columns; i++){
            buffer.append(i);
            buffer.append(' ');
        }
        buffer.append('\n');
        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < board[i].length; j++) {
                int it = board[i][j];
                if (it == kEmpty) buffer.append('.');
                else if (it == kFirst) buffer.append('r');
                else buffer.append('b');
                buffer.append(' ');
            }
            buffer.append("\n");
        }
        String player = getPlayerName();
        buffer.append("Game State: ");

        if (state == kPlaying) buffer.append(player).append("'s turn");
        else if (state == kWin) buffer.append(player).append(" won the game");
        else buffer.append("The game is a draw");
        buffer.append('\n')
                .append("Score: ")
                .append(firstPlayer)
                .append('-')
                .append(firstScore)
                .append('|')
                .append(secondPlayer)
                .append('-')
                .append(secondScore)
                .append("|Draw-")
                .append(draws)
                .append('\n');
        return buffer.toString();
    }

    String serialize() {
        StringBuffer buffer = new StringBuffer();
        buffer
                .append(firstPlayer).append('\n')
                .append(firstScore).append('\n')
                .append(secondPlayer).append('\n')
                .append(secondScore).append('\n')
                .append(draws).append('\n')
                .append(rows).append('\n')
                .append(columns).append('\n')
                .append(n).append('\n')
                .append(player).append('\n')
                .append(state).append('\n');
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
        firstPlayer = br.readLine();
        firstScore = readInt(br);
        secondPlayer = br.readLine();
        secondScore = readInt(br);
        draws = readInt(br);
        rows = readInt(br);
        columns = readInt(br);
        board = new int[rows][];
        n = readInt(br);
        player = readInt(br);
        state = readInt(br);
        int row = 0;
        String line;
        while ((line = br.readLine()) != null && row < rows - 1) {
            String[] data = line.split(",");
            if (data.length != columns) throw new IllegalArgumentException("Data has wrong size");
            board[row] = new int[columns];
            for (int column = 0; column < columns; column++) {
                board[row][column] = Integer.parseInt(data[column]);
            }
            System.out.println();
        }
        if (row != rows - 1) throw new IllegalArgumentException("Data has wrong size");
        br.close();
    }
}
