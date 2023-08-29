package com.amdocs.petmanagementsystem.main;

import com.amdocs.petmanagementsystem.exceptions.InvalidPriceException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import com.amdocs.petmanagementsystem.dao.JDBCConnection;
import com.amdocs.petmanagementsystem.dao.petData;
import com.amdocs.petmanagementsystem.model.Pet;

public class Main {
	public static void main(String[] args) throws IOException {
		
		System.out.println(JDBCConnection.getConnection());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc= new Scanner(System.in);
		petData pd = new petData();
		Pet pet = new Pet();
		Boolean f= true;
		while(f) {
			System.out.println("1. Add a Pet");
			System.out.println("2. Display all pets");
			System.out.println("3. Update a Pet");
			System.out.println("4. Delete a Pet");
			System.out.println("5. Search a Pet By Id");
			System.out.println("6. Search a Pet Price");
			System.out.println("7. Count pets if the category is:");
			System.out.println("8. Count pets By Vaccination Status:");
			System.out.println("9. Exit");
			
			int choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case 1:
				
//				System.out.print("Enter Pet ID: ");
//				pet.setPetId(sc.nextInt());		
				
			    System.out.print("Enter Pet Category: ");
			    pet.setPetCat(br.readLine());
			    
			    System.out.print("Enter Pet Type: ");
			    pet.setPetType(br.readLine());
			    
			    System.out.print("Enter Pet Color: ");
			    pet.setPetColor(br.readLine());
			    
			    System.out.print("Enter Pet age: ");
			    pet.setPetAge(sc.nextInt());
			    
			    System.out.print("Enter pet price: ");
			    Double price1=sc.nextDouble();
			    
			    try {
		            if (price1 <=0 || price1>=500000) {
		                throw new InvalidPriceException("Invalid Price");
		            }
		        } catch (InvalidPriceException e) {
		            System.out.println("Price Should be greater than 0 or less than 500000 :"+ e.getMessage());
		            break;
		        }
			    
			    pet.setPetPrice(price1);
			    
			    System.out.print("Enter pet Vaccination: ");
			    pet.setPetVac(sc.nextBoolean());
			    
			    System.out.print("Enter pet Food habit: ");
			    pet.setPetFh(br.readLine());
			    
			    pd.insertPet(pet);
			    break;
			case 2:

				List<Pet> allPets = pd.getAllPets();

				for (Pet pets : allPets) {
				    System.out.println("Pet ID: " + pets.getPetId());
				    System.out.println("Pet Category: " + pets.getPetCat());
				    System.out.println("Pet Type: " + pets.getPetType());
				    System.out.println("Pet Color: " + pets.getPetColor());
				    System.out.println("Pet Age: " + pets.getPetAge());
				    System.out.println("Pet Price: " + pets.getPetPrice());
				    System.out.println("Pet Vaccination: " + pets.getPetVac());
				    System.out.println("Pet Food Habit: " + pets.getPetFh());
				   
				    System.out.println("--------------------------");
				}
				break;
			case 3:
  				
  				int id, price;
  				boolean vaccination_status;
  				System.out.print("Enter Pet ID that you want to update: ");
  				id=sc.nextInt();
  				System.out.print("Enter Vaccination Status: ");
  				vaccination_status=sc.nextBoolean();
  				System.out.print("Enter Price: ");
  				price=sc.nextInt();
  				pd.updatePet(id, vaccination_status, price);
  				break;
  				
			case 4:
				System.out.print("Enter Pet ID that you want to Delete: ");
				pd.deletePet(sc.nextInt());
  				
  				break;
			case 5:
				System.out.print("Enter Pet ID which you want to search: ");
				List<Pet> petDet = pd.search(sc.nextInt());

				for (Pet pets : petDet) {
				    System.out.println("Pet ID: " + pets.getPetId());
				    System.out.println("Pet Category: " + pets.getPetCat());
				    System.out.println("Pet Type: " + pets.getPetType());
				    System.out.println("Pet Color: " + pets.getPetColor());
				    System.out.println("Pet Age: " + pets.getPetAge());
				    System.out.println("Pet Price: " + pets.getPetPrice());
				    System.out.println("Pet Vaccination: " + pets.getPetVac());
				    System.out.println("Pet Foor Habit: " + pets.getPetFh());
				   
				    System.out.println("--------------------------");
				}
				break;
			case 6:
				int upper, lower;
				System.out.println("Enter Pet price range which you want to search: ");
				System.out.print("Enter lower price: ");
				lower=sc.nextInt();
				System.out.print("Enter upper price: ");
				upper=sc.nextInt();
				List<Pet> petDet2 = pd.searchByPrice(lower, upper);
				
				for (Pet pets : petDet2) {
				    System.out.println("Pet ID: " + pets.getPetId());
				    System.out.println("Pet Category: " + pets.getPetCat());
				    System.out.println("Pet Type: " + pets.getPetType());
				    System.out.println("Pet Color: " + pets.getPetColor());
				    System.out.println("Pet Age: " + pets.getPetAge());
				    System.out.println("Pet Price: " + pets.getPetPrice());
				    System.out.println("Pet Vaccination: " + pets.getPetVac());
				    System.out.println("Pet Foor Habit: " + pets.getPetFh());
				   
				    System.out.println("--------------------------");
				}
				break;
				
			case 7:
				System.out.print("Enter Pet category: ");
				int count=pd.countPetsByCategory(sc.nextLine());
				System.out.println("Count is: "+ count);
			case 8:
				System.out.print("Enter Pet Vaccination Status: ");
				int count1=pd.countPetsByVaccinationStatus(sc.nextBoolean());
				System.out.println("Count is: "+ count1);
				break;
			case 9:
				f=false;
				break;
			    
			}
			
			
		}
		sc.close();
	}
}
