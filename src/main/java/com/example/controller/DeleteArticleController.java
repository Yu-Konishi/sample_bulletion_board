package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

@Controller
@RequestMapping("/delete")
public class DeleteArticleController {

	/**
	 * 記事のリポジトリ
	 */
	@Autowired
	private ArticleRepository articleRepository;

	/**
	 * コメントのリポジトリ
	 */
	@Autowired
	private CommentRepository commentRepository;

	/**
	 * 記事とそれに関連するコメントを削除.
	 * 
	 * @param articleId ID
	 * @return 掲示板画面
	 */
	@RequestMapping("")
	public String delete(Integer articleId) {
		commentRepository.deleteByArticleId(articleId);
		articleRepository.findByarticleId(articleId);
		return "redirect:bulletin-board";
	}

}
