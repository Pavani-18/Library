package com.lib.intern;

import java.util.Scanner;

public class LibraryConsole {
    private LibraryManager libraryManager = new LibraryManager();
    private UserManager userManager = new UserManager();
    private Scanner scanner = new Scanner(System.in);
    private User loggedInUser;

    public void run() {
        while (true) {
            if (loggedInUser == null) {
                System.out.println("Welcome to the Library Management System");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        register();
                        break;
                    case 3:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } else {
                showUserMenu();
            }
        }
    }

    private void login() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        loggedInUser = userManager.authenticate(email, password);
        if (loggedInUser == null) {
            System.out.println("Invalid email or password. Try again.");
        } else {
            System.out.println("Login successful!");
        }
    }

    private void register() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Are you an admin? (y/n): ");
        boolean isAdmin = scanner.nextLine().equalsIgnoreCase("y");

        User user = new User(name, email, password, isAdmin);
        userManager.addUser(user);
        System.out.println("Registration successful!");
    }

    private void showUserMenu() {
        if (loggedInUser.isAdmin()) {
            showAdminMenu();
        } else {
            showNormalUserMenu();
        }
    }

    private void showAdminMenu() {
        System.out.println("\nAdmin Menu:");
        System.out.println("1. Add book");
        System.out.println("2. Remove book");
        System.out.println("3. List books");
        System.out.println("4. Logout");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                addBook();
                break;
            case 2:
                removeBook();
                break;
            case 3:
                libraryManager.listBooks();
                break;
            case 4:
                loggedInUser = null;
                System.out.println("Logged out.");
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    private void showNormalUserMenu() {
        System.out.println("\nUser Menu:");
        System.out.println("1. List books");
        System.out.println("2. Issue book");
        System.out.println("3. Return book");
        System.out.println("4. Logout");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                libraryManager.listBooks();
                break;
            case 2:
                issueBook();
                break;
            case 3:
                returnBook();
                break;
            case 4:
                loggedInUser = null;
                System.out.println("Logged out.");
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    private void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        Book book = new Book(title, author, isbn);
        libraryManager.addBook(book);
        System.out.println("Book added successfully.");
    }

    private void removeBook() {
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        libraryManager.removeBook(isbn);
        System.out.println("Book removed successfully.");
    }

    private void issueBook() {
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        libraryManager.issueBook(isbn, loggedInUser);
    }

    private void returnBook() {
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        libraryManager.returnBook(isbn);
    }

    public static void main(String[] args) {
        LibraryConsole console = new LibraryConsole();
        console.run();
    }
}
