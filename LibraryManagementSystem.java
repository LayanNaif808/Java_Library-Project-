import java.util.ArrayList;
import java.util.Scanner;

// ==========================================
// 1. BOOK CLASS
// ==========================================
class Book {
    private String title;
    private String author;
    private int id;

    public Book(String title, String author, int id) {
        this.title = title;
        this.author = author;
        this.id = id;
    }

    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getId() { return id; }

    // Display book details
    public void displayBook() {
        System.out.println("ID: " + id + " | Title: " + title + " | Author: " + author);
    }
}

// ==========================================
// 2. MAIN SYSTEM WITH MENU & TRY-CATCH
// ==========================================
public class LibraryManagementSystem {
    public static void main(String[] args) {
        ArrayList<Book> library = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add a Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Search Book by ID");
            System.out.println("4. Delete a Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            // Try-Catch to handle invalid menu choice
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear scanner buffer
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number between 1 and 5.");
                scanner.nextLine(); // Clear scanner buffer
                choice = 0; // Reset choice to continue loop
                continue;
            }

            switch (choice) {
                case 1: // ADD BOOK
                    try {
                        System.out.print("Enter Book Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Book Author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter Book ID (Numbers only): ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        library.add(new Book(title, author, id));
                        System.out.println("Book added successfully.");
                    } catch (Exception e) {
                        System.out.println("Error: Book ID must be a number. Failed to add book.");
                        scanner.nextLine(); // Clear scanner buffer
                    }
                    break;

                case 2: // DISPLAY ALL BOOKS
                    System.out.println("\n--- Books List ---");
                    if (library.isEmpty()) {
                        System.out.println("The library is empty.");
                    } else {
                        for (Book b : library) {
                            b.displayBook();
                        }
                    }
                    break;

                case 3: // SEARCH BOOK BY ID
                    try {
                        System.out.print("Enter Book ID to search: ");
                        int searchId = scanner.nextInt();
                        scanner.nextLine();
                        
                        boolean found = false;
                        for (Book b : library) {
                            if (b.getId() == searchId) {
                                System.out.println("Book Found:");
                                b.displayBook();
                                found = true;
                                break;
                            }
                        }
                        if (!found) System.out.println("No book found with ID: " + searchId);
                    } catch (Exception e) {
                        System.out.println("Error: ID must be a number.");
                        scanner.nextLine();
                    }
                    break;

                case 4: // DELETE BOOK BY ID
                    try {
                        System.out.print("Enter Book ID to delete: ");
                        int deleteId = scanner.nextInt();
                        scanner.nextLine();

                        boolean removed = library.removeIf(b -> b.getId() == deleteId);
                        if (removed) {
                            System.out.println("Book deleted successfully.");
                        } else {
                            System.out.println("No book found with ID: " + deleteId);
                        }
                    } catch (Exception e) {
                        System.out.println("Error: ID must be a number.");
                        scanner.nextLine();
                    }
                    break;

                case 5: // EXIT
                    System.out.println("Exiting system. Thank you!");
                    break;

                default:
                    if (choice != 0) {
                        System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                    }
            }
        } while (choice != 5);

        scanner.close();
    }
}

