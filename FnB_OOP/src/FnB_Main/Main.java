package FnB_Main;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Utility.Utility;
// FOOD AND BEVERAGE_OOP OBJECT

public class Main {
	
	Scanner sc = new Scanner(System.in);
	
	
	
    void mainMenu() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("|                        BurgerQueen                          |\n");
        System.out.println("---------------------------------------------------------------");
        System.out.println("|              Aplikasi Pesan Makanan dan Minuman             |");
        System.out.println("---------------------------------------------------------------");
        System.out.println("1. Manajemen data pelanggan");
        System.out.println("2. Katalog makanan dan minuman");
        System.out.println("3. Top up saldo");
        System.out.println("4. Pesan dan pembayaran");
        System.out.println("5. Exit");
        System.out.print("Choice >> ");
    }
    
    
    void ManajemenData(Customer cust) {
    	
    	int pilihanManajemenData;
		String name, address, namaToUpdate, newAddress;
		double initialBalance=0;
		boolean check=true,found;
		while(check) {
			System.out.println("1. Tambah data baru");
			System.out.println("2. Perbarui data");
			System.out.println("3. Hapus data");
			System.out.println("4. Lihat semua data");
			System.out.println("5. Back");
			System.out.print("Choice>> ");
			pilihanManajemenData = sc.nextInt(); sc.nextLine();
			switch (pilihanManajemenData) {
				case 1:
					System.out.print("Masukkan nama: ");
					name = sc.nextLine();
					System.out.print("Masukkan alamat: ");
					address = sc.nextLine();
//					System.out.print("Masukkan saldo awal: ");
//					initialBalance = sc.nextDouble();
					try {
						cust.addCustomer(name, address, initialBalance);
						System.out.println("Data pelanggan berhasil ditambahkan.");
					} catch (SQLException e) {
						System.out.println("Error: " + e.getMessage());
					}
					break;

				case 2:
					System.out.print("Masukkan nama yang akan diperbarui: ");
					namaToUpdate = sc.nextLine();
					found = false;
					try {
						Customer existingCustomer = cust.getCustomerByName(namaToUpdate);
						if (existingCustomer != null) {
							found = true;
							System.out.print("Masukkan alamat baru: ");
							newAddress = sc.nextLine();
							try {
								cust.updateCustomer(namaToUpdate, newAddress);
								System.out.println("Data pelanggan berhasil diperbarui.");
							} catch (SQLException e) {
								System.out.println("Error updating customer: " + e.getMessage());
							}
						}
					} catch (Exception e) {
						System.out.println("Error searching for customer: " + e.getMessage());
					}

					if (!found) {
						System.out.println("Data pelanggan tidak ditemukan.");
					}
					break;

				case 3:
					System.out.print("Masukkan nama pelanggan yang akan dihapus: ");
					String customerNameToDelete = sc.nextLine();
					try {
						cust.deleteCustomer(customerNameToDelete);
						System.out.println("Data pelanggan berhasil dihapus.");
					} catch (SQLException e) {
						System.out.println("Error: " + e.getMessage());
					}
					break;

				case 4:
					try {
						cust.viewAllCustomers();
					} catch (SQLException e) {
						System.out.println("Error viewing customers: " + e.getMessage());
					}
					break;
				case 5:
					check=false;

				default:
					System.out.println("\nInvalid Input!, please input again");
					break;
			}
			Utility.Cls();
		}
		
    }
    
	void handleTopUpBalance(CustomerManagement customerManagement) {
	    System.out.print("Masukkan nama pelanggan untuk top up saldo: ");
	    String customerName = sc.nextLine();
	    System.out.print("Masukkan jumlah saldo yang ingin ditambahkan: ");
	    double topUpAmount = sc.nextDouble();
	    sc.nextLine();

	    try {
	        customerManagement.topUpBalance(customerName, topUpAmount);
	    } catch (Exception e) {
	        System.out.println("Error topping up balance: " + e.getMessage());
	    }
	}
    String login(String username, String password) {
		return "";
        // Logic to verify login credentials
    }

    boolean register(String username, String password, String name) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Cek apakah username sudah ada
            String checkUserQuery = "SELECT username FROM cashiers WHERE username = ?";
            try (PreparedStatement checkUserStmt = connection.prepareStatement(checkUserQuery)) {
                checkUserStmt.setString(1, username);
                ResultSet resultSet = checkUserStmt.executeQuery();
                if (resultSet.next()) {
                    System.out.println("Username already exists.");
                    return false;
                }
            }

            // Jika username belum ada, lakukan pendaftaran
            String query = "INSERT INTO cashiers (username, password, cashier_name) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, name);

                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    String attemptLogin() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        return login(username, password);
    }

    void attemptRegistration() throws SQLException{
    	
    	String username,password,name;
    	do {
            System.out.print("Enter username: ");
            username = sc.nextLine();
            System.out.print("Enter password: ");
            password = sc.nextLine();
            System.out.print("Enter full name: ");
            name = sc.nextLine();

            if (register(username, password, name)) {
                System.out.println("Registration successful. Please log in.");
            } else {
                System.out.println("Registration failed. User may already exist.");
            }
            System.out.println(register(username, password, name));
    	}while(!register(username, password, name));
    }
	
	public Main() throws SQLException {
		// Start
		Connection connection = DatabaseConnection.getConnection();
		System.out.println("Test");
		boolean check=true;
        while (check) {
            System.out.println("Welcome to the Cashier System!");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Please select an option: ");
            int option = sc.nextInt();
            sc.nextLine(); 

            switch (option) {
                case 1:
                	String cashiername;
                	cashiername=attemptLogin();
                    if (cashiername!=null) {
                        check=false;
                    }
                    break;
                case 2:
                    attemptRegistration();
                        check=false;                    	
                    break;
                case 3:
                    System.out.println("Exiting system...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
		
		
		int choice;
		Customer cust = new Customer();
		do {
			mainMenu();
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice){
				case 1:
					ManajemenData(cust);
					break;
				
				case 2:
					// katalog makanan dan minuman
					System.out.println("test");
					break;

				case 3:
//					handleTopUpBalance(cust);
					break;
				
				case 4: 
					// pesan dan pembayaran berdasarkan katalog makanan dan saldo yang dimiliki oleh user
					System.out.println("test");
					break;
				
				case 5:
					break;

				default:
					System.out.println("\nInvalid Input!, please input again");
					break;
			}
		} while (choice != 5);

		// End
		System.out.println("\nThank you for coming!");
	}

	public static void main(String[] args) throws SQLException {
		new Main();
	}

}
