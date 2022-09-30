package cinema;

import java.util.UUID;

public class CinemaSeat {
    private int row;
    private int column;
    private int price;
    boolean purchased;
    String token;

    public CinemaSeat() {}

    public CinemaSeat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4? 10 : 8;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
