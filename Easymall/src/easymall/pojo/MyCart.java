package easymall.pojo;

public class MyCart {
	private Integer cartID;
	private String pid;
	private String name;
	private String imgurl;
	private Double price;
	private Integer num;
	
	public MyCart(){}
	
	public MyCart(Integer cartID, String pid, String name, String imgurl, Double price, Integer num) {
		super();
		this.cartID = cartID;
		this.pid = pid;
		this.name = name;
		this.imgurl = imgurl;
		this.price = price;
		this.num = num;
	}

	public Integer getCartID() {
		return cartID;
	}

	public void setCartID(Integer cartID) {
		this.cartID = cartID;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
