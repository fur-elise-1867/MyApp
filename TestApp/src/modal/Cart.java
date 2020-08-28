package modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	@Id
	private int id;

	@Column(name = "idMember")
	private int idMember;
	@ManyToOne
	@JoinColumn(name = "id", updatable = false, insertable = false)
	private Account account;

	@Column(name = "idProduct")
	private int idProduct;
	@ManyToOne
	@JoinColumn(name = "id", updatable = false, insertable = false)
	private Product product;

	private String statusCart;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdMember() {
		return idMember;
	}

	public void setIdMember(int idMember) {
		this.idMember = idMember;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getStatusCart() {
		return statusCart;
	}

	public void setStatusCart(String statusCart) {
		this.statusCart = statusCart;
	}

	

}
