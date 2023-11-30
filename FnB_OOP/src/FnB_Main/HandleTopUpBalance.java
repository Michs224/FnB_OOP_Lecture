package FnB_Main;
import java.util.Scanner;

public class HandleTopUpBalance {
    public void handleTopUpBalance(CustomerManagement customerManagement, Scanner sc) {
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
    
}
