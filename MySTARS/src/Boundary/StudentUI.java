package Boundary;

import java.util.InputMismatchException;
import java.util.Scanner;

import Model.*;

public class StudentUI {
	public static void studentMenu(Student student) {
		int choice = 0;
		boolean validInput = false;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("1. Add Course");
			System.out.println("2. Drop Course");
			System.out.println("3. Check/Print Courses Registered");
			System.out.println("4. Check Vacancies Available");
			System.out.println("5. Change Index Number of Course");
			System.out.println("6. Swap Index Number With Another Student");
			System.out.println("7. Logout");
			
			do {
				try {
					choice = sc.nextInt();
				if (choice >= 1) {
					validInput = true;
					sc.nextLine();
					}
				} catch (InputMismatchException e) {
					System.out.println("Enter a valid integer!");
					sc.nextLine();
				}
			} while (!validInput);
			validInput = false;

			switch (choice) {
			case 1:
				break;
			case 2:
				break;
			case 3:

				break;
			case 4:

				break;
			case 5:

				break;
			case 6:

				break;
			case 7:
				System.out.println("Loged out successfully");
				break;
			default:
				System.out.println("");
				break;
			}
		} while (choice > 0 && choice < 7);
		
	}
}
