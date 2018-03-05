package converter;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileRead {

	private int numberOfItems = 0;
	private int numberOfFamilyMembers = 0;
	private int numberOfStrings = 0;
	private char firstChar;
	private String[] splitString;
	private boolean familyMember = false;

	private FileInputStream fstream;
	private DataInputStream in;
	private BufferedReader br;

	public ArrayList<Item> itemList = new ArrayList<Item>();

	// P
	public String firstName;
	public String lastName;

	// T
	public String mobileNumber;
	public String landlineNumber;

	// A
	public String address;
	public String city;
	public String zipCode;

	// F
	public String name;
	public String yearOfBirth;

	public void read() {
		try {
			fstream = new FileInputStream("testfile3.txt");
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			while ((strLine = br.readLine()) != null) {

				if (strLine.contains("|")) {
					splitString = strLine.split("\\|");
					numberOfStrings = splitString.length;

					System.out.println(numberOfStrings);

				} else {
					throw new IllegalArgumentException("String: " + strLine + "; does not contain |");
				}

				firstChar = splitString[0].charAt(0);

				switch (firstChar) {
				case 'P':

					familyMember = false;
					itemList.add(new Item());
					numberOfItems++;
					numberOfFamilyMembers = 0;

					firstName = splitString[1];
					lastName = splitString[2];

					itemList.get(numberOfItems - 1).personItem(this.firstName, this.lastName);

					break;

				case 'T':

					mobileNumber = splitString[1];
					landlineNumber = splitString[2];

					if (familyMember) {

						itemList.get(numberOfItems - 1).famList.get(numberOfFamilyMembers - 1).ph = true;
						itemList.get(numberOfItems - 1).famList.get(numberOfFamilyMembers - 1)
								.familyPhone(this.mobileNumber, this.landlineNumber);
					} else {
						
						itemList.get(numberOfItems - 1).phoneItem(this.mobileNumber, this.landlineNumber);
						itemList.get(numberOfItems - 1).ph = true;
					}

					break;

				case 'A':

					try {

						address = splitString[1];

					} catch (Exception e) {
						address = "";
						System.out.println("String :" + strLine + " ; does not contain: address");
					}

					try {
						city = splitString[2];

					} catch (Exception e) {
						city = "";
						System.out.println("String :" + strLine + " ; does not contain: city");
					}

					try {
						zipCode = splitString[3];

					} catch (Exception e) {
						zipCode = "";
						System.out.println("String :" + strLine + " ; does not contain: zipCode");
					}

					if (familyMember) {

						itemList.get(numberOfItems - 1).famList.get(numberOfFamilyMembers - 1)
								.familyAddress(this.address, this.city, this.zipCode);
						itemList.get(numberOfItems - 1).famList.get(numberOfFamilyMembers - 1).ad = true;
					} else {
						itemList.get(numberOfItems - 1).addressItem(this.address, this.city, this.zipCode);
						itemList.get(numberOfItems - 1).ad = true;
					}
					
					break;

				case 'F':

					familyMember = true;
					numberOfFamilyMembers++;
					name = splitString[1];
					yearOfBirth = splitString[2];

					itemList.get(numberOfItems - 1).addFamilyMember();
					itemList.get(numberOfItems - 1).famList.get(numberOfFamilyMembers - 1).familyPerson(this.name,
							this.yearOfBirth);

					break;

				default:
					break;
				}
			}

			System.out.println(itemList.size());

			for (int i = 0; i < itemList.size(); i++) {
				System.out.println(itemList.get(i).firstName);
				System.out.println(itemList.get(i).lastName);
				for (int j = 0; j < itemList.get(i).famList.size(); j++) {
					System.out.println(itemList.get(i).famList.get(j).name);
				}
			}

			fstream.close();
			in.close();
			br.close();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}
}
