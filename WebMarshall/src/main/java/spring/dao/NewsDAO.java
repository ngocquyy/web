package spring.dao;

import java.util.List;

import spring.entity.News;


public interface NewsDAO {
	public List<News> getListNews(Integer offset, Integer maxResult) ;
	public Long getTotalNews();
	public News getNewsById(Integer newsId) ;
	public News insertNews(News ns) ;
	public void updateNews(News ns) ;
	public News deleteNews(Integer newsId) ;
}
