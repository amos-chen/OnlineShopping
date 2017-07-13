package com.taotao.dto;

/**
 * Created by chenlunwei on 2017/7/9.
 */
public class Item {
	private String id;
	private String title;
	private String sellPoint;
	private long price;
	private String image;
	private String categoryName;
	private String description;
	private String[] images;

	@Override
	public String toString() {
		return "Item{" +
				"id='" + id + '\'' +
				", title='" + title + '\'' +
				", sellPoint='" + sellPoint + '\'' +
				", price=" + price +
				", image='" + image + '\'' +
				", categoryName='" + categoryName + '\'' +
				", description='" + description + '\'' +
				'}';
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getImages(){
		if(image!=null && image!=""){
			return image.split(";");
		}
		return null;
	}
	public void setImages(String[] images) {
		this.images = images;
	}
}
