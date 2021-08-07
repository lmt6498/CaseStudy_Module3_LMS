package modul;

public class Book {
private String callno,name,author,publisher,image;
private int quantity,issued;
public Book() {


}
public Book(String callno, String name, String author,String image, String publisher, int quantity) {
	super();
	this.callno = callno;
	this.name = name;
	this.author = author;
	this.image = image;
	this.publisher = publisher;
	this.quantity = quantity;
}

	public Book(String callno, String name, String author,String image ,String publisher, int quantity, int issued) {
		this.callno = callno;
		this.name = name;
		this.author = author;
		this.image = image;
		this.publisher = publisher;
		this.quantity = quantity;
		this.issued = issued;
	}

	public Book(String callno, String name, String author,String image,int quantity) {
		this.callno = callno;
		this.name = name;
		this.author = author;
		this.image = image;
		this.quantity = quantity;
	}

	public String getCallno() {
	return callno;
}
public void setCallno(String callno) {
	this.callno = callno;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public String getPublisher() {
	return publisher;
}
public void setPublisher(String publisher) {
	this.publisher = publisher;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public int getIssued() {
	return issued;
}
public void setIssued(int issued) {
	this.issued = issued;
}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
