package money.model;

import java.util.Date;

public class cls_MONEY_History {
	private int Id;
	private int IdAccount;
	private int Cate;
	private double Price;
	private String Desciption;
	private Date DateHistory;

	public cls_MONEY_History() {
		super();
	}

	public cls_MONEY_History(int Id, int IdAccount, int Cate, double Price,
			String Description, Date DateHistory) {
		super();
		this.Id = Id;
		this.IdAccount = IdAccount;
		this.Cate = Cate;
		this.Price = Price;
		this.Desciption = Description;
		this.DateHistory = DateHistory;

	}

	public cls_MONEY_History(int IdAccount, int Cate, double Price,
			String Description, Date DateHistory) {
		super();
		this.IdAccount = IdAccount;
		this.Cate = Cate;
		this.Price = Price;
		this.Desciption = Description;
		this.DateHistory = DateHistory;

	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getIdAccount() {
		return IdAccount;
	}

	public void setIdAccount(int idAccount) {
		IdAccount = idAccount;
	}

	public int getCate() {
		return Cate;
	}

	public void setCate(int cate) {
		Cate = cate;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public String getDesciption() {
		return Desciption;
	}

	public void setDesciption(String desciption) {
		Desciption = desciption;
	}

	public Date getDateHistory() {
		return DateHistory;
	}

	public void setDateHistory(Date dateHistory) {
		DateHistory = dateHistory;
	}
}
