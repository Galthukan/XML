package converter;

import java.util.ArrayList;

public class Item {

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
	
	public boolean pr = false;
	public boolean ph = false;
	public boolean ad = false;

	public ArrayList<Family> famList = new ArrayList<Family>();

	class Family {

		// F
		public String name;
		public String yearOfBirth;

		// T
		public String mobileNumber;
		public String landlineNumber;

		// A
		public String address;
		public String city;
		public String zipCode;
		
		public boolean pr = false;
		public boolean ph = false;
		public boolean ad = false;
		
		public void familyPerson(String _name, String _yearOfBirth) {
			name = _name;
			yearOfBirth = _yearOfBirth;
		}
		
		public void familyPhone(String _mobileNumber, String _landlineNumber) {
			mobileNumber = _mobileNumber;
			landlineNumber = _landlineNumber;
		}
		
		public void familyAddress(String _address, String _city, String _zipCode) {
			address = _address;
			city = _city;
			zipCode = _zipCode;
		}
	}
	
	public void addFamilyMember() {

		famList.add(new Family());
	}

	public void clearFamilyList() {
		famList.clear();
	}

	public void printFamilyList() {
		System.out.println(famList.toString());
	}

	public void personItem(String _firstName, String _lastName) {
		this.firstName = _firstName;
		this.lastName = _lastName;
	}

	public void phoneItem(String _mobileNumber, String _landlineNumber) {
		this.mobileNumber = _mobileNumber;
		this.landlineNumber = _landlineNumber;
	}

	public void addressItem(String _address, String _city, String _zipCode) {
		this.address = _address;
		this.city = _city;
		this.zipCode = _zipCode;

	}
}
