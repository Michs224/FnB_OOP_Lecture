package Utility;

import java.util.Scanner;

public class Utility {
	
		static Scanner input= new Scanner(System.in);
	
		public static void Cls() {
			for(int i=0;i<25;i++) {
				System.out.print("\n");
			}
			System.out.print("\033[H\033[2J");
            System.out.flush();
		}
		public static void PressEnter() {
			System.out.print("Press enter to continue...");
			input.nextLine();
		}

}
