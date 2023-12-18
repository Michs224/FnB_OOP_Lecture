package FnB_Main;

import FnB_Display.Display;
import java.util.Scanner;
import java.sql.SQLException;

// FOOD AND BEVERAGE_OOP OBJECT

public class Main {
	Scanner sc = new Scanner(System.in);
	public Main() {
		// Start
		int choice, pilihanManajemenData;
		String name, address, namaToUpdate, newAddress;
		double initialBalance;
		boolean found;

		Display display = new Display();
		CustomerManagement customerManagement = new Customer();
		HandleTopUpBalance handleTopUpBalance = new HandleTopUpBalance();

		do {
			display.mainMenu();
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
				case 1:
					System.out.println("1. Tambah data baru");
					System.out.println("2. Perbarui data");
					System.out.println("3. Hapus data");
					System.out.println("4. Lihat semua data");
					System.out.print("Input pilihan: ");
					pilihanManajemenData = sc.nextInt(); sc.nextLine();
					switch (pilihanManajemenData) {
						case 1:
							System.out.print("Masukkan nama: ");
							name = sc.nextLine();
							System.out.print("Masukkan alamat: ");
							address = sc.nextLine();
							System.out.print("Masukkan saldo awal: ");
							initialBalance = sc.nextDouble();
							try {
								customerManagement.addCustomer(name, address, initialBalance);
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
								Customer existingCustomer = CustomerManagement.getCustomerByName(namaToUpdate);
								if (existingCustomer != null) {
									found = true;
									System.out.print("Masukkan alamat baru: ");
									newAddress = sc.nextLine();
									try {
										customerManagement.updateCustomer(namaToUpdate, newAddress);
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
								customerManagement.deleteCustomer(customerNameToDelete);
								System.out.println("Data pelanggan berhasil dihapus.");
							} catch (SQLException e) {
								System.out.println("Error: " + e.getMessage());
							}
							break;

						case 4:
							try {
								((Customer) customerManagement).viewAllCustomers();
							} catch (SQLException e) {
								System.out.println("Error viewing customers: " + e.getMessage());
							}
							break;

						default:
							break;
					}
					break;
				
				case 2:
					// katalog makanan dan minuman
					System.out.println("test");

				case 3:
					handleTopUpBalance.handleTopUpBalance(customerManagement, sc);
				
				case 4: 
					// pesan dan pembayaran berdasarkan katalog makanan dan saldo yang dimiliki oleh user
					System.out.println("test");
				
				case 5:
					break;

				default:
					System.out.println("Inputan salah");
					break;
			}
		} while (choice != 5);

		// End
		System.out.println("\nThank you for coming!");
	}

	public static void main(String[] args) {
		new Main();
	}

}
