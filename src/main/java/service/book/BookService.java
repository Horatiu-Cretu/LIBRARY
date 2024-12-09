package service.book;

import model.Book;
import view.model.BookDTO;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book findById(Long id);
    boolean save(Book book);
    boolean delete(Book book);
    int getAgeOfBook(Book book);
    int getAgeOfBook(Long id);
    void removeAll();
    boolean sellBook(Book book);
}
