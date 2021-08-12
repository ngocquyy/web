package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.NewsDAO;
import spring.entity.News;

@Service
public class NewsServiceImpl implements NewsService{
	@Autowired
	private NewsDAO newsDAO;
	
	@Override
	public List<News> getListNews(Integer offset, Integer maxResult) {
		// TODO Auto-generated method stub
		return newsDAO.getListNews(offset, maxResult);
	}

	@Override
	public Long getTotalNews() {
		// TODO Auto-generated method stub
		return newsDAO.getTotalNews();
	}

	@Override
	public News getNewsById(Integer newsId) {
		// TODO Auto-generated method stub
		return newsDAO.getNewsById(newsId);
	}

	@Override
	public News insertNews(News ns) {
		// TODO Auto-generated method stub
		return newsDAO.insertNews(ns);
	}

	@Override
	public void updateNews(News ns) {
		// TODO Auto-generated method stub
		newsDAO.updateNews(ns);
	}

	@Override
	public News deleteNews(Integer newsId) {
		// TODO Auto-generated method stub
		return newsDAO.deleteNews(newsId);
	}

}
