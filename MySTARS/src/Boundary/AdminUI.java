package Boundary;

import java.util.InputMismatchException;
import java.util.Scanner;

import Model.*;

public class AdminUI {
	public static void adminMenu(Admin admin) {
		int choice = 0;
		boolean validInput = false;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("1. Edit student access period");
			System.out.println("2. Add a student (name, matric number, gender, nationality, etc)");
			System.out.println("3. Add/Update a course (course code, school, its index numbers and vacancy)");
			System.out.println("4. Check available slot for an index number (vacancy in a class)");
			System.out.println("5. Print student list by index number.");
			System.out.println("6. Print student list by course (all students registered for the selected course)");
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

				break;
			default:
				System.out.println("");
				break;
			}
		} while (choice > 0 && choice < 7);
		
	}
}
