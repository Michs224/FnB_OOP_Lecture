package FnB_Main;

import java.util.Scanner;
import java.util.Vector;
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
        System.out.println("|                        BurgerQueen                          |");
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
    
    
    void validateName1(String name) throws IllegalArgumentException {
        if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("\nNama tidak valid. Harus berupa huruf dan tidak boleh kosong.");
        }
    }
    void validateAddress(String address) throws IllegalArgumentException {
        if (address.isEmpty()) {
            throw new IllegalArgumentException("\nAlamat tidak boleh kosong.");
        }
    }
    void validatePhone(String phone) throws IllegalArgumentException {
        if (!phone.matches("08\\d{10}") || phone.length() != 12) {
            throw new IllegalArgumentException("\nNomor telepon tidak valid. Harus diawali '08' dan panjang 12 karakter.");
        }
    }		
	
    void validatePassword(String password) {
        if (password.isEmpty()) {
            throw new IllegalArgumentException("Password tidak boleh kosong!");
        }
        if (!password.matches(".*[a-zA-Z].*")) {
            throw new IllegalArgumentException("Password harus mengandung setidaknya satu huruf.");
        }
        if (!password.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("Password harus mengandung setidaknya satu angka.");
        }
        if (!password.matches(".*[!@#$%^&*()_?].*")) {
            throw new IllegalArgumentException("Password harus mengandung setidaknya satu simbol.");
        }
        if (password.matches(".*\\s.*")) {
            throw new IllegalArgumentException("Password tidak boleh mengandung spasi.");
        }
    }

	
	void validateName(String nama){
		if (nama.isEmpty()){
			throw new IllegalArgumentException("Nama tidak boleh kosong!");
		}
		else if(!nama.matches("^[a-zA-Z ]+$")) {
			throw new IllegalArgumentException("Nama tidak boleh mengandung angka atau simbol");
		}
		
	}
	
	
    Cashier login(String username, String password) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT cashier_id, cashier_name FROM cashiers WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Cashier(
                            resultSet.getInt("cashier_id"),
                            resultSet.getString("cashier_name")
                        );
                    }
                }
            }
        }
        return null;
    }


    boolean register(String username, String password, String name) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Cek apakah username sudah ada
            String checkUserQuery = "SELECT username FROM cashiers WHERE username = ?";
            try (PreparedStatement checkUserStmt = connection.prepareStatement(checkUserQuery)) {
                checkUserStmt.setString(1, username);
                ResultSet resultSet = checkUserStmt.executeQuery();
                if (resultSet.next()) {
//                    System.out.println("Username already exists.");
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

    
    Cashier attemptLogin() throws SQLException {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        String password=null;
		boolean passwordValid=false;
        
        do {
        	try {
                System.out.print("Enter password: ");
                password = sc.nextLine().trim();
                validatePassword(password);
                passwordValid=true;
        	}catch(IllegalArgumentException e) {
    			System.out.println("\n"+e.toString());
    			Utility.PressEnter();
    			Utility.Cls();
    			UILogReg();
                System.out.print("1\nEnter username: "+username+"\n");
        	}

        }while(!passwordValid);

        Cashier cashier = login(username, password);
        if (cashier != null) {
            System.out.println("\nLogin successful. Welcome, " + cashier.getName() + "!\n");
        } else {
            System.out.println("\nLogin failed. Invalid username or password.\n");
        }
        Utility.PressEnter();
        Utility.Cls();
		return cashier;

    }
   

    void attemptRegistration() throws SQLException {
        String username=null, password = null, name=null;
        boolean isRegistered = false;
        while (!isRegistered) {
            System.out.print("Enter username: ");
            username = sc.nextLine();
            
    		boolean passwordValid=false;
            
            do {
            	try {
                    System.out.print("Enter password: ");
                    password = sc.nextLine().trim();
                    validatePassword(password);
                    passwordValid=true;
            	}catch(IllegalArgumentException e) {
        			System.out.println("\n"+e.toString()+"\n");
        			Utility.PressEnter();
        			Utility.Cls();
        			UILogReg();
                    System.out.print("2\nEnter username: "+username+"\n");
            	}

            }while(!passwordValid);
            
            boolean nameValid=false;
            do {
            	try {
                    System.out.print("Enter full name: ");
                    name = sc.nextLine().trim();
                    validateName1(name);
                    nameValid=true;
            	}catch(IllegalArgumentException e) {
        			System.out.println("\n"+e.toString()+"\n");
        			Utility.PressEnter();
        			Utility.Cls();
        			UILogReg();
                    System.out.print("2\nEnter username: "+username+"\n");
                    System.out.print("2\nEnter password: "+password+"\n");
            	}

            }while(!nameValid);
            
            isRegistered = register(username, password, name);
            if (isRegistered) {
                System.out.println("\nRegistration successful. Please log in.\n");
            } else {
                System.out.println("\nRegistration failed. User may already exist.\n");
            }
            Utility.PressEnter();
            Utility.Cls();
        }
    }
    
    
    
    
    void ManajemenData(Customer cust) {
        int pilihanManajemenData;
        String name = "", address = "", phone = "", newAddress;
        double initialBalance;
        boolean check = true, found, nameValid = false;

        while(check) {
        	System.out.println("--------------------------------");
        	System.out.println("|         ManajemenData        |");
        	System.out.println("--------------------------------");
            System.out.println("1. Tambah data baru");
            System.out.println("2. Perbarui data");
            System.out.println("3. Hapus data");
            System.out.println("4. Lihat semua data");
            System.out.println("5. Back");
            System.out.print("Choice >> ");

            if (!sc.hasNextInt()) {
                System.out.println("\nInvalid input, please enter a number.\n");
                sc.nextLine();
                Utility.PressEnter();
                continue;
            }

            pilihanManajemenData = sc.nextInt(); 
            sc.nextLine();

            switch (pilihanManajemenData) {
            case 1:
                boolean addressValid = false, phoneValid = false;

                do {
                    try {
                        System.out.print("Masukkan nama: ");
                        name = sc.nextLine().trim();
                        validateName1(name);
                        nameValid = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("\n" + e.getMessage() + "\n");
                        
                    }
                } while (!nameValid);

                do {
                    try {
                        System.out.print("Masukkan alamat: ");
                        address = sc.nextLine().trim();
                        validateAddress(address);
                        addressValid = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("\n" + e.getMessage() + "\n");
                        
                      
                    }
                } while (!addressValid);

                do {
                    try {
                        System.out.print("Masukkan nomor telepon: ");
                        phone = sc.nextLine().trim();
                        validatePhone(phone);

                        Customer existingCustomer = cust.getCustomerByPhone(phone);
                        if (existingCustomer != null) {
                            throw new IllegalArgumentException("\nNomor telepon sudah digunakan oleh pelanggan lain.\n");
                        }

                        phoneValid = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("\n" + e.getMessage() + "\n");
                    } catch (SQLException e) {
                        System.out.println("\nError accessing database: " + e.getMessage() + "\n");
                    }
                } while (!phoneValid);

                initialBalance = 0;
                try {
                    cust.addCustomer(name, address, initialBalance, phone);
                    System.out.println("\nData pelanggan berhasil ditambahkan.\n");
                    Utility.PressEnter();
                } catch (SQLException e) {
                    System.out.println("\nError: " + e.getMessage());
                }
                break;

            case 2:
                phoneValid = false;
                String phoneToUpdate = null;  
                Customer existingCustomer = null;

                while (!phoneValid) {
                    try {
                        System.out.print("Masukkan nomor telepon yang akan diperbarui: ");
                        phoneToUpdate = sc.nextLine().trim();
                        validatePhone(phoneToUpdate);

                        existingCustomer = cust.getCustomerByPhone(phoneToUpdate);
                        if (existingCustomer == null) {
                            throw new IllegalArgumentException("\nNomor telepon tidak ditemukan.\n");
                        }

                        phoneValid = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("\n" + e.getMessage() + "\n");
                    } catch (SQLException e) {
                        System.out.println("\nError accessing database: " + e.getMessage() + "\n");
                    }
                }
                
             
                found = false;
                nameValid = false;
    
                try {
                    if (existingCustomer != null) {
                        found = true;
                        System.out.println("1. Ubah Alamat");
                        System.out.println("2. Ubah Nama");
                        System.out.println("3. Ubah Saldo");
                        System.out.print("Pilihan: ");
                        int updateChoice = sc.nextInt();
                        sc.nextLine(); // consume the remaining newline

                        switch (updateChoice) {
                            case 1:
                                System.out.println("Alamat lama: " + existingCustomer.getAddress());
                                System.out.print("Masukkan alamat baru: ");
                                newAddress = sc.nextLine();
                                if (!newAddress.equals(existingCustomer.getAddress())) {
                                    System.out.println("Konfirmasi perubahan alamat dari '" + existingCustomer.getAddress() + "' ke '" + newAddress + "'? (y/n)");
                                    if (sc.nextLine().trim().equalsIgnoreCase("y")) {
                                        cust.updateCustomerAddress(phoneToUpdate, newAddress);
                                        System.out.println("\nAlamat diperbarui.\n");
                                        Utility.PressEnter();
                                    }
                                }
                                break;
                            case 2:
                                String newName;
                                nameValid = false; 
                                System.out.println("Nama lama: " + existingCustomer.getName());

                                while (!nameValid) {
                                    try {
                                        System.out.print("Masukkan nama baru: ");
                                        newName = sc.nextLine().trim();

                                        validateName1(newName); 

                                        if (!newName.equals(existingCustomer.getName())) {
                                            System.out.println("Konfirmasi perubahan nama dari '" + existingCustomer.getName() + "' ke '" + newName + "'? (y/n)");
                                            String confirmation = sc.nextLine().trim();
                                            if (confirmation.equalsIgnoreCase("y")) {
                                                cust.updateCustomerName(phoneToUpdate, newName);
                                                System.out.println("\nNama diperbarui.\n");
                                                Utility.PressEnter();
                                                nameValid = true;
                                            } else if (confirmation.equalsIgnoreCase("n")) {
                                                System.out.println("\nPerubahan nama dibatalkan.\n");
                                                Utility.PressEnter();
                                                nameValid = true;
                                            } else {
                                                System.out.println("Masukkan 'y' untuk ya atau 'n' untuk tidak.");
                                            }
                                        } else {
                                            System.out.println("\nNama baru sama dengan nama lama. Tidak ada perubahan yang dilakukan.\n");
                                            nameValid = true;
                                        }
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("\n" + e.getMessage() + "\n");
                                    } catch (SQLException e) {
                                        System.out.println("\nError updating customer: " + e.getMessage()+"\n");
                                        
                                    }
                                }
                                break;


                            case 3:
                                System.out.println("Saldo lama: " + existingCustomer.getBalance());
                                System.out.print("Masukkan saldo baru: ");
                                double newBalance = sc.nextDouble(); sc.nextLine(); 
                                if (newBalance != existingCustomer.getBalance()) {
                                    System.out.println("Konfirmasi perubahan saldo dari '" + existingCustomer.getBalance() + "' ke '" + newBalance + "'? (y/n)");
                                    if (sc.nextLine().trim().equalsIgnoreCase("y")) {
                                        cust.updateCustomerBalance(phoneToUpdate, newBalance);
                                        System.out.println("\nSaldo diperbarui.\n");
                                        Utility.PressEnter();
                                    }
                                }
                                break;
                            default:
                                System.out.println("Pilihan tidak valid.");
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("\nError updating customer: " + e.getMessage()+"\n");
                } catch (Exception e) {
                    System.out.println("\nError searching for customer: " + e.getMessage()+"\n");
                }

                if (!found) {
                    System.out.println("\nData pelanggan tidak ditemukan.\n");
                    Utility.PressEnter();
                }
                break;
              

            case 3:
                phoneValid = false;
                String phoneToDelete = "";

                while (!phoneValid) {
                    try {
                        System.out.print("Masukkan nomor telepon pelanggan yang akan dihapus: ");
                        phoneToDelete = sc.nextLine().trim();
                        validatePhone(phoneToDelete);

                        Customer customerToDelete = cust.getCustomerByPhone(phoneToDelete);
                        if (customerToDelete == null) {
                            throw new IllegalArgumentException("\nPelanggan dengan nomor telepon tersebut tidak ditemukan.\n");
                        }

                        System.out.println("Apakah Anda yakin ingin menghapus data pelanggan berikut?");
                        System.out.println("Nama: " + customerToDelete.getName());
                        System.out.println("Alamat: " + customerToDelete.getAddress());
                        System.out.println("Saldo: " + customerToDelete.getBalance());
                        System.out.print("Konfirmasi penghapusan (y/n): ");
                        
                        String confirmation = sc.nextLine().trim();
                        if (confirmation.equalsIgnoreCase("y")) {
                            cust.deleteCustomer(phoneToDelete);
                            System.out.println("\nData pelanggan berhasil dihapus.\n");
                            Utility.PressEnter();
                        } else {
                            System.out.println("\nPenghapusan dibatalkan.\n");
                            Utility.PressEnter();
                        }
                        phoneValid = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("\n" + e.getMessage() + "\n");
                    } catch (SQLException e) {
                        System.out.println("\nError: " + e.getMessage() + "\n");
                    }
                }
                break;


            case 4:
                try {
                    cust.viewAllCustomers();
                    
                } catch (SQLException e) {
                    System.out.println("\nError viewing customers: " + e.getMessage()+"\n");
                  
                }
                Utility.PressEnter();
                break;

            case 5:
                check = false;
                break;

            default:
                System.out.println("\nInvalid Input!, please input again\n");
                break;
            }
            Utility.Cls();
        }
    }
    
    
    void UILogReg() {
    	System.out.println("===============================================");
        System.out.println("|------- Welcome to the Cashier System! ------|");
    	System.out.println("===============================================");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Please select an option: ");
    }
    
    void FoodMenu() {
    	String fid="Food Id",fn="Food Name",fc="Food Category";
    	String pr="Price";
        System.out.println("==========================================================================================");
        System.out.println("|                                       Foods                                            |");
        System.out.println("==========================================================================================");
        System.out.printf("| %-2s | %-10s | %-29s | %-20s | %-12s |\n","No.",fid,fn,fc,pr);
        System.out.println("==========================================================================================");
    }
    
    void BeverageMenu() {
    	String bid="Beverage Id",bn="Beverage Name",bt="Beverage Type",carb="Carbonated";
    	String pr="Price";
        System.out.println("==========================================================================================");
        System.out.println("|                                     Beverages                                          |");
        System.out.println("==========================================================================================");
        System.out.printf("| %-2s | %-11s | %-20s | %-15s | %-10s | %-12s |\n","No.",bid,bn,bt,carb,pr);
        System.out.println("==========================================================================================");
    }
    
    void CatalogMenu() {
        Food food = new Food();
        Beverage beverage = new Beverage();
        try {
        	FoodMenu();
            boolean foodExists = food.printMenu();
            
            BeverageMenu();
            boolean beverageExists = beverage.printMenu();

            if (!foodExists){
                System.out.println("|           Tidak ada makanan yang tersedia.          |");
            }
            if (!beverageExists){
                System.out.println("|           Tidak ada minuman yang tersedia.          |");
            }
            System.out.println("==========================================================================================\n");
        } catch (SQLException e) {
            System.out.println("\nError saat mencetak katalog: " + e.getMessage()+"\n");
        }
    }
    
       
    
    void handleTopUpBalance(CustomerManagement customerManagement) {
        boolean phoneValid = false;
        String phoneToTopUp= "";
        Customer existingCustomer = null;

        while (!phoneValid) {
            try {
                System.out.print("Masukkan nomor telepon pelanggan untuk top up saldo: ");
                phoneToTopUp = sc.nextLine().trim();
                validatePhone(phoneToTopUp);

                existingCustomer = customerManagement.getCustomerByPhone(phoneToTopUp);
                if (existingCustomer == null) {
                    throw new IllegalArgumentException("\nPelanggan dengan nomor telepon tersebut tidak ditemukan.\n");
                }

                phoneValid = true;
            } catch (IllegalArgumentException e) {
                System.out.println("\n" + e.getMessage() + "\n");
            } catch (SQLException e) {
                System.out.println("\nError accessing database: " + e.getMessage() + "\n");
            }
        }
       
        System.out.println("Nama Customer: " + existingCustomer.getName());
        double oldBalance = existingCustomer.getBalance();
        System.out.println("Saldo lama: " + oldBalance);
     
        

        System.out.print("Masukkan jumlah saldo yang ingin ditambahkan: ");
        double topUpAmount = sc.nextDouble();
        sc.nextLine();

        double newBalance = oldBalance + topUpAmount;
        System.out.println("Saldo baru akan menjadi: " + newBalance);
        System.out.print("Konfirmasi top up (y/n): ");
        String confirmation = sc.nextLine().trim();

        if (confirmation.equalsIgnoreCase("y")) {
            try {
                customerManagement.topUpBalance(phoneToTopUp, topUpAmount);
                System.out.println("\nSaldo berhasil ditambahkan.\n");
            } catch (Exception e) {
                System.out.println("\nError topping up balance: " + e.getMessage()+"\n");
            }
        } else {
            System.out.println("\nTop up dibatalkan.\n");
        }
        Utility.PressEnter();
    }
	
	
    Customer authenticateCustomer() {
        System.out.print("Masukkan nomor telepon pelanggan: ");
        String phone = sc.nextLine();
        Customer customer = null;

        try {
            customer = new Customer().getCustomerByPhone(phone);
            if (customer == null) {
                System.out.println("\nPelanggan tidak ditemukan.\n");
            }
        } catch (SQLException e) {
            System.out.println("\nError saat mengambil data pelanggan: " + e.getMessage()+"\n");
        }

        return customer;
    }
    
    
    
    void processOrder(Cashier currentCashier) {
    	Customer currentCustomer =authenticateCustomer();
        Order order = new Order();
        Vector<Menu> orderedItems = new Vector<>();
        boolean ordering = true;
        while (ordering) {
            System.out.println("Pilih item untuk dipesan:");
            System.out.println("1. Makanan");
            System.out.println("2. Minuman");
            System.out.println("3. Selesai memesan");
            System.out.print("Pilihan Anda: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    try {
                        Menu selectedFood = selectFoodItem();
                        if (selectedFood != null) {
                            orderedItems.add(selectedFood);
                        }
                    } catch (SQLException e) {
                        System.out.println("\nError saat mengambil daftar makanan: " + e.getMessage()+"\n");
                    }
                    break;
                case 2:
                    try {
                        Menu selectedBeverage = selectBeverageItem();
                        if (selectedBeverage != null) {
                            orderedItems.add(selectedBeverage);
                        }
                    } catch (SQLException e) {
                        System.out.println("\nError saat mengambil daftar minuman: " + e.getMessage()+"\n");
                    }
                    break;
                case 3:
                    ordering = false;
                    break;
                default:
                    System.out.println("\nPilihan tidak valid.\n");
                    break;
            }
        }

        if (!orderedItems.isEmpty()) {
            double totalAmount = order.calculateTotalAmount(orderedItems);
            if (currentCustomer.getBalance() >= totalAmount) {
                try {
                    order.placeOrder(currentCustomer, orderedItems, currentCashier);
                    order.makePayment(currentCustomer, totalAmount);
                    printInvoice(currentCustomer, orderedItems, totalAmount, currentCashier);
                } catch (SQLException e) {
                    System.out.println("\nError: " + e.getMessage()+"\n");
                }
            } else {
                System.out.println("\nSaldo tidak mencukupi untuk melakukan pembayaran.\n");
            }
        } else {
            System.out.println("\nTidak ada item yang dipesan.\n");
        }
    }

    Menu selectFoodItem() throws SQLException {
        Food food = new Food();
        Vector<Menu> menuItems = food.getMenuItems();
    	FoodMenu();

        int index = 1;
        for (Menu item : menuItems) {
            Food specificFood = (Food) item; // Casting ke Food
            System.out.printf("| %-3d | %-10s | %-29s | %-20s | %-12s |\n",index,
            		specificFood.getItemID(), specificFood.getItemName(), specificFood.getFoodCategory(), specificFood.getPrice());
//            System.out.println(index + ". ID: " + specificFood.getItemID() + ", " + specificFood.getItemName() + ", Kategori: " + specificFood.getFoodCategory() + ", Harga: " + specificFood.getPrice());
            index++;
        }
        System.out.println("==========================================================================================");
        System.out.println("Pilih Makanan:");
        System.out.println("0. Kembali");
        System.out.print("Pilihan Anda: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice > 0 && choice <= menuItems.size()) {
            return menuItems.get(choice - 1); // Tidak perlu casting di sini karena mengembalikan Menu
        }
        return null;
    }


    Menu selectBeverageItem() throws SQLException {
        Beverage beverage = new Beverage();
        Vector<Menu> menuItems = beverage.getMenuItems();
        BeverageMenu();
        int index = 1;
        for (Menu item : menuItems) {
            Beverage specificBeverage = (Beverage) item; // Casting ke Beverage
            System.out.printf("| %-3d | %-11s | %-20s | %-15s | %-10s | %-12s |\n",index,
            		specificBeverage.getItemID(), specificBeverage.getItemName(), specificBeverage.getBeverageType(),
                    (specificBeverage.isCarbonated() ? "Ya" : "Tidak"), specificBeverage.getPrice());
//            System.out.println(index + ". ID: " + specificBeverage.getItemID() + ", " + specificBeverage.getItemName() + ", Tipe: " + specificBeverage.getBeverageType() + ", Berkarbonasi: " + (specificBeverage.isCarbonated() ? "Ya" : "Tidak") + ", Harga: " + specificBeverage.getPrice());
            index++;
        }
        System.out.println("==========================================================================================");
        System.out.println("Pilih Minuman:");
        System.out.println("0. Kembali");
        System.out.print("Pilihan Anda: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice > 0 && choice <= menuItems.size()) {
            return menuItems.get(choice - 1); // Tidak perlu casting di sini karena mengembalikan Menu
        }
        return null;
    }


    void printInvoice(Customer customer, Vector<Menu> orderedItems, double totalAmount, Cashier cashier) {
        System.out.println("\n========= Invoice =========");
        System.out.println("Pelanggan : " + customer.getName()+" (ID: "+customer.getId()+")");
        System.out.println("Kasir     : " + cashier.getName() + " (ID: " + cashier.getId() + ")");
        System.out.println("Item yang dipesan:");
        for (Menu item : orderedItems) {
            System.out.println("- " + item.getItemName() + ": " + item.getPrice());
        }
        System.out.println("Total Pembayaran: " + totalAmount);
        System.out.println("=============================\n");
    }

	
	public Main() throws SQLException {
		// Start

		boolean check=true;
		Cashier cashier = null;
        while (check) {
    		UILogReg();
            int option = sc.nextInt();
            sc.nextLine(); 

            switch (option) {
                case 1:
                	cashier=attemptLogin();
                	if (cashier!=null) {
                		check=false;
                	}
                    break;
                case 2:
                    attemptRegistration();
                    break;
                case 3:
                    System.out.println("Exiting system...");
                    System.exit(0);
                default:
                    System.out.println("\nInvalid option. Please try again.\n");
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
					CatalogMenu();
			        Utility.PressEnter();
			        Utility.Cls();
					break;

				case 3:
					handleTopUpBalance(cust);
			        Utility.Cls();
					break;
				
				case 4: 
					processOrder(cashier);
			        Utility.PressEnter();
			        Utility.Cls();
					break;
				
				case 5:
					break;

				default:
					System.out.println("\nInvalid Input!, please input again\n");
					Utility.PressEnter();
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
