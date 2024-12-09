package view.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookDTO {
    private StringProperty author;

    public void setAuthor(String author) {
        authorProperty().set(author);
    }

    public String getAuthor(){
        return authorProperty().get();
    }

    public StringProperty authorProperty(){
        if (author == null){
            author = new SimpleStringProperty(this, "author");
        }
        return author;
    }

    private StringProperty title;

    public void setTitle(String title){
        titleProperty().set(title);
    }

    public String getTitle(){
        return titleProperty().get();
    }

    public StringProperty titleProperty(){
        if (title == null){
            title = new SimpleStringProperty(this, "title");
        }
        return title;
    }

    private StringProperty stock;

    public void setStock(String stock){
        stockProperty().set(stock);
    }

    public String getStock(){
        return stockProperty().get();
    }
    public StringProperty stockProperty(){
        if (stock == null){
            stock = new SimpleStringProperty(this, "stock");
        }
        return stock;
    }
}