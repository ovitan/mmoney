package money.model;

public class cls_MONEY_Account {
	private int Id;
	private String Name;
	private double Price;

	public cls_MONEY_Account() {
		super();
	}

	public cls_MONEY_Account(int Id, String Name, double Price) {
		super();
		this.Id = Id;
		this.Name = Name;
		this.Price = Price;
	}

	public cls_MONEY_Account(String Name, double Price) {
		super();
		this.Name = Name;
		this.Price = Price;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

}
