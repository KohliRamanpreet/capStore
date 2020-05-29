
	package com.capstore.model;

	import java.util.Set;

	import javax.persistence.CascadeType;
	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.FetchType;
	import javax.persistence.GeneratedValue;
	import javax.persistence.Id;
	import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
	import javax.persistence.OneToMany;
	import javax.persistence.Table;

	@Entity
	@Table(name = "product")
	public class Product {

		@Id
		@GeneratedValue
		@Column(name = "product_id")
		private int productId;
		
		 @Lob
		  private byte[] data;
		@Column(name = "product_name")
	    private String productName;
		
		@Column(name = "product_image")
	    private String productImage;
		
		@Column(name = "product_price")
	    private double productPrice;
		
		@Column(name = "product_rating")
	    private int productRating;
		
		@Column(name = "no_of_product_viewed")
	    private int noOfProductViewed;
		
		@Column(name = "product_brand")
	    private String productBrand;
		
		@Column(name = "no_of_products")
	    private int noOfProducts;
		
		@Column(name = "product_info")
	    private String productInfo;
		
		@Column(name = "discount")
		private int discount;
		
		@Column(name = "product_category")
	    private String productCategory;
		
		@Column(name = "product_activated")
	    private boolean productActivated;
		
		@Column(name = "status")
	    private boolean status;
		
		@Column(name = "featured")
	    private boolean featured;
		
		@Column(name = "discounted_price")
		private double discountedPrice;
	    
		public double getDiscountedPrice() {
			return discountedPrice;
		}
		public void setDiscountedPrice(double discountedPrice) {
			this.discountedPrice = discountedPrice;
		}
		public Product(String productName, String productImage, double productPrice, int productRating,
				int noOfProductViewed, String productBrand, int noOfProducts, String productInfo, int discount,
				String productCategory, boolean productActivated, boolean status, boolean featured) {
			super();
			this.productName = productName;
			this.productImage = productImage;
			this.productPrice = productPrice;
			this.productRating = productRating;
			this.noOfProductViewed = noOfProductViewed;
			this.productBrand = productBrand;
			this.noOfProducts = noOfProducts;
			this.productInfo = productInfo;
			this.discount = discount;
			this.productCategory = productCategory;
			this.productActivated = productActivated;
			this.status = status;
			this.featured = featured;
			
			
		}
		
		
		
		public int getProductId() {
			return productId;
		}
		public int getDiscount() {
			return discount;
		}
		public void setDiscount(int discount) {
			this.discount = discount;
		}
		public void setProductId(int productId) {
			this.productId = productId;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public String getProductImage() {
			return productImage;
		}
		public void setProductImage(String productImage) {
			this.productImage = productImage;
		}
		public double getProductPrice() {
			return productPrice;
		}
		public void setProductPrice(double productPrice) {
			this.productPrice = productPrice;
		}
		public int getProductRating() {
			return productRating;
		}
		public void setProductRating(int productRating) {
			this.productRating = productRating;
		}
		public int getNoOfProductViewed() {
			return noOfProductViewed;
		}
		public void setNoOfProductViewed(int noOfProductViewed) {
			this.noOfProductViewed = noOfProductViewed;
		}
		public String getProductBrand() {
			return productBrand;
		}
		public void setProductBrand(String productBrand) {
			this.productBrand = productBrand;
		}
		public int getNoOfProducts() {
			return noOfProducts;
		}
		public void setNoOfProducts(int noOfProducts) {
			this.noOfProducts = noOfProducts;
		}
		public String getProductInfo() {
			return productInfo;
		}
		public void setProductInfo(String productInfo) {
			this.productInfo = productInfo;
		}
		public String getProductCategory() {
			return productCategory;
		}
		public void setProductCategory(String productCategory) {
			this.productCategory = productCategory;
		}
		public boolean isProductActivated() {
			return productActivated;
		}
		public void setProductActivated(boolean productActivated) {
			this.productActivated = productActivated;
		}
		public boolean isStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
		}
		public boolean isFeatured() {
			return featured;
		}
		public void setFeatured(boolean featured) {
			this.featured = featured;
		}
		
		

		public Product() {
		}
		public Product(int productId, String productName, int productMerchantId) {
			super();
			this.productId = productId;
			this.productName = productName;
			
		}
		@Override
		public String toString() {
			return "Product [productId=" + productId + ", productName=" + productName + ", productImage=" + productImage
					+ ", productPrice=" + productPrice + ", productRating=" + productRating + ", noOfProductViewed="
					+ noOfProductViewed + ", productBrand=" + productBrand + ", noOfProducts=" + noOfProducts
					+ ", productInfo=" + productInfo + ", productCategory=" + productCategory + ", productActivated="
					+ productActivated + ", status=" + status + ", featured=" + featured + ", productMerchantId="
					 + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (featured ? 1231 : 1237);
			result = prime * result + noOfProductViewed;
			result = prime * result + noOfProducts;
			result = prime * result + (productActivated ? 1231 : 1237);
			result = prime * result + ((productBrand == null) ? 0 : productBrand.hashCode());
			result = prime * result + ((productCategory == null) ? 0 : productCategory.hashCode());
			result = prime * result + productId;
			result = prime * result + ((productImage == null) ? 0 : productImage.hashCode());
			result = prime * result + ((productInfo == null) ? 0 : productInfo.hashCode());
			
			result = prime * result + ((productName == null) ? 0 : productName.hashCode());
			long temp;
			temp = Double.doubleToLongBits(productPrice);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			result = prime * result + productRating;
			result = prime * result + (status ? 1231 : 1237);
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Product other = (Product) obj;
			if (featured != other.featured)
				return false;
			if (noOfProductViewed != other.noOfProductViewed)
				return false;
			if (noOfProducts != other.noOfProducts)
				return false;
			if (productActivated != other.productActivated)
				return false;
			if (productBrand == null) {
				if (other.productBrand != null)
					return false;
			} else if (!productBrand.equals(other.productBrand))
				return false;
			if (productCategory == null) {
				if (other.productCategory != null)
					return false;
			} else if (!productCategory.equals(other.productCategory))
				return false;
			if (productId != other.productId)
				return false;
			if (productImage == null) {
				if (other.productImage != null)
					return false;
			} else if (!productImage.equals(other.productImage))
				return false;
			if (productInfo == null) {
				if (other.productInfo != null)
					return false;
			} else if (!productInfo.equals(other.productInfo))
				return false;
			
			if (productName == null) {
				if (other.productName != null)
					return false;
			} else if (!productName.equals(other.productName))
				return false;
			if (Double.doubleToLongBits(productPrice) != Double.doubleToLongBits(other.productPrice))
				return false;
			if (productRating != other.productRating)
				return false;
			if (status != other.status)
				return false;
			return true;
		}
		
		

		
	}


