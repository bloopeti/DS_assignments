package model;

import java.io.*;

public class DVD implements Serializable {
    private String title;
    private int year;
    private double price;

    public DVD() {
    }

    public DVD(String title, int year, double price) {
        this.title = title;
        this.year = year;
        this.price = price;
    }

    public DVD(byte[] data) {
        try {
            DVD dvd = new DVD();
            dvd = deserialize(data);
            this.title = dvd.title;
            this.year = dvd.year;
            this.price = dvd.price;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "DVD{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }

    public byte[] serialize() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(this);
        return byteArrayOutputStream.toByteArray();
    }

    private DVD deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return (DVD) objectInputStream.readObject();
    }
}