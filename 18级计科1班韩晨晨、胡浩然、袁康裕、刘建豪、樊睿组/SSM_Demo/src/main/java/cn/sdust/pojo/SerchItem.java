package cn.sdust.pojo;

public class SerchItem {
    private String bookName;
    private String type;
    private String writer;
    private double minValue;
    private double maxValue;

    public SerchItem() {

    }

    public SerchItem(String bookName, String type, String writer, double minValue, double maxValue) {
        this.bookName = bookName;
        this.type = type;
        this.writer = writer;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }
}
