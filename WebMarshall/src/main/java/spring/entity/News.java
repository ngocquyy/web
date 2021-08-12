package spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "News")
public class News {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	@Column(name = "NewsId")
	private Integer newsId;
	@Column(name = "Title")
	private String title;
	@Column(name = "Images")
	private String images;
	@Column(name = "Content")
	private String content;
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}
	public News(Integer newsId, String title, String images, String content) {
		super();
		this.newsId = newsId;
		this.title = title;
		this.images = images;
		this.content = content;
	}
	
	
	
}
