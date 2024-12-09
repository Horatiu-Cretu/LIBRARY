package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import mapper.BookMapper;
import service.book.BookService;
import view.BookView;
import view.model.BookDTO;
import view.model.builder.BookDTOBuilder;

public class BookController {
    private final BookView bookView;
    private final BookService bookService;

    public BookController(BookView bookView, BookService bookService){
        this.bookView = bookView;
        this.bookService = bookService;

        this.bookView.addSaveButtonListener(new SaveButtonListener());
        this.bookView.addDeleteButtonListener(new DeleteButtonListener());
        this.bookView.addSellButtonListener(new SellButtonListener());

    }

    private class SaveButtonListener implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            String title = bookView.getTitle();
            String author = bookView.getAuthor();
            String stock = bookView.getStock();

            if (title.isEmpty() || author.isEmpty() || stock.isEmpty()){
                bookView.addDisplayAlertMessage("Save Error", "Problem at Author or Title fields", "Can not have an empty Title or Author field.");
            } else {
                BookDTO bookDTO = new BookDTOBuilder()
                        .setTitle(title)
                        .setAuthor(author)
                        .setStock(stock)
                        .build();
                boolean savedBook = bookService.save(BookMapper.convertBookDTOToBook(bookDTO));

                if (savedBook){
                    bookView.addDisplayAlertMessage("Save Successful", "Book Added", "Book was successfully added to the database.");
                    bookView.addBookToObservableList(bookDTO);
                } else {
                    bookView.addDisplayAlertMessage("Save Error", "Problem at adding Book", "There was a problem at adding the book to the database. Please try again!");
                }
            }
        }
    }

    private class DeleteButtonListener implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            BookDTO bookDTO = (BookDTO) bookView.getBookTableView().getSelectionModel().getSelectedItem();
            if (bookDTO != null){
                boolean deletionSuccessful = bookService.delete(BookMapper.convertBookDTOToBook(bookDTO));

                if (deletionSuccessful){
                    bookView.addDisplayAlertMessage("Delete Successful", "Book Deleted", "Book was successfully deleted from the database.");
                    bookView.removeBookFromObservableList(bookDTO);
                } else {
                    bookView.addDisplayAlertMessage("Delete Error", "Problem at deleting book", "There was a problem with the database. Please try again!");
                }
            } else {
                bookView.addDisplayAlertMessage("Delete Error", "Problem at deleting book", "You must select a book before pressing the delete button.");
            }
        }
    }

    private class SellButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            BookDTO bookDTO = (BookDTO) bookView.getBookTableView().getSelectionModel().getSelectedItem();

            if (bookDTO != null) {
                int currentStock = Integer.parseInt(bookDTO.getStock());

                if (currentStock > 0) {
                    boolean sellSuccessful = bookService.sellBook(BookMapper.convertBookDTOToBook(bookDTO));

                    if (sellSuccessful) {
                        int updatedStock = currentStock - 1;
                        bookDTO.setStock(String.valueOf(updatedStock));
                        updateBookInObservableList(bookDTO, bookView.getBookTableView().getItems());

                        bookView.addDisplayAlertMessage(
                                "Sell Successful",
                                "Book Sold Successfully",
                                "Stock updated in the database."
                        );
                    } else {
                        bookView.addDisplayAlertMessage(
                                "Sell Error",
                                "Stock Update Failed",
                                "Unable to update the stock. Please try again."
                        );
                    }
                } else {
                    // If stock is empty, delete the book from the database and UI
                    bookService.delete(BookMapper.convertBookDTOToBook(bookDTO));
                    bookView.removeBookFromObservableList(bookDTO);

                    bookView.addDisplayAlertMessage(
                            "Sell Successful",
                            "Book Sold",
                            "Stock was empty. Book removed from database."
                    );
                }
            }
        }
    }
    public void updateBookInObservableList(BookDTO updatedBookDTO, ObservableList<BookDTO> booksObservableList) {
        int index = -1;
        for (int i = 0; i < booksObservableList.size(); i++) {
            if (booksObservableList.get(i).getAuthor().equals(updatedBookDTO.getAuthor())
                        && booksObservableList.get(i).getTitle().equals(updatedBookDTO.getTitle())) {
                index = i;
                break;
            }
        }


        if (index >= 0) {
            booksObservableList.set(index, updatedBookDTO);
        }
    }



}


