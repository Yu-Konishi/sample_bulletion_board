package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;

/**
 * 記事を新規投稿するコントローラー.
 * 
 * @author hikaru.tsuda
 *
 */
@RequestMapping("/insert")
@Controller
public class InsertArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private showBbsController showBbsController;
	
	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}
	
	/**
	 * 記事を新規投稿するメソッド.
	 * 
	 * @param form 
	 * @param result
	 * @param model
	 * @return 掲示板画面にフォワード
	 */
	@RequestMapping("")
	public String insertArticle(@Validated ArticleForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return showBbsController.index(model);
		}
		
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		articleRepository.insert(article);
		
		return  "redirect:/bulletion-board";
	}

}
