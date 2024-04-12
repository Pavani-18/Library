package com.lib.intern;

import java.util.*;

public class LibraryManager {
    private List<Book> books = new ArrayList<>();
    private Map<String, User> issuedBooks = new HashMap<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
    }

    public void listBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void issueBook(String isbn, User user) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && book.isAvailable()) {
                book.setAvailable(false);
                issuedBooks.put(isbn, user);
                System.out.println("Book issued to " + user.getName());
                return;
            }
        }
        System.out.println("Book not available.");
    }

    public void returnBook(String isbn) {
        Book bookToReturn = null;
        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && !book.isAvailable()) {
                book.setAvailable(true);
                bookToReturn = book;
                issuedBooks.remove(isbn);
                break;
            }
        }
        if (bookToReturn != null) {
            System.out.println("Book returned: " + bookToReturn);
        } else {
            System.out.println("Invalid ISBN or book not issued.");
        }
    }
}

