package cinema;

public class CinemaSeat {
    private int row;
    private int column;
    private int price;

    public CinemaSeat() {}

    public CinemaSeat(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }

    public CinemaSeat(int row, int column) {

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
