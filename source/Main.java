package converter;

public class Main {

	public static void main(String[] args) {

		FileRead fr = new FileRead();

		WriterXML wx = new WriterXML();

		fr.read();

		wx.setFile("test.xml");

		try {
			wx.writeStart();
			wx.createStartElement("people");

			for (int i = 0; i < fr.getNumberOfItems(); i++) {

				wx.createStartElement("person");

				wx.createNode("firstName", fr.itemList.get(i).firstName);
				wx.createNode("lastName", fr.itemList.get(i).lastName);

				if (fr.itemList.get(i).ad) {
					wx.createStartElement("address");
					wx.createNode("street", fr.itemList.get(i).address);
					wx.createNode("city", fr.itemList.get(i).city);
					wx.createNode("zipCode", fr.itemList.get(i).zipCode);
					wx.createEndElement("address");
				}

				if (fr.itemList.get(i).ph) {
					wx.createStartElement("phone");
					wx.createNode("mobile", fr.itemList.get(i).mobileNumber);
					wx.createNode("landline", fr.itemList.get(i).landlineNumber);
					wx.createEndElement("phone");
				}

				for (int j = 0; j < fr.itemList.get(i).famList.size(); j++) {

					wx.createStartElement("family");
					wx.createNode("name", fr.itemList.get(i).famList.get(j).name);
					wx.createNode("yearOfBirth", fr.itemList.get(i).famList.get(j).yearOfBirth);
					
					if (fr.itemList.get(i).famList.get(j).ad) {
						wx.createStartElement("address");
						wx.createNode("street", fr.itemList.get(i).famList.get(j).address);
						wx.createNode("city", fr.itemList.get(i).famList.get(j).city);
						wx.createNode("zipCode", fr.itemList.get(i).famList.get(j).zipCode);
						wx.createEndElement("address");
					}
					
					if (fr.itemList.get(i).famList.get(j).ph) {
						wx.createStartElement("phone");
						wx.createNode("mobile", fr.itemList.get(i).mobileNumber);
						wx.createNode("landline", fr.itemList.get(i).landlineNumber);
						wx.createEndElement("phone");
					}

					wx.createEndElement("family");
				}

				wx.createEndElement("person");
			}

			wx.createEndElement("people");
			wx.writeEnd();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
