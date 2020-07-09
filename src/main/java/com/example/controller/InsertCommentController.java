package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Comment;
import com.example.form.CommentForm;
import com.example.repository.CommentRepository;

@Controller
@RequestMapping("/insertComment")
public class InsertCommentController {
	
	@Autowired
	private showBbsController showBbsController;
	
	/**
	 * コメントのリポジトリ
	 */
	@Autowired
	private CommentRepository commentRepository;

	/**
	 * 新たなコメントを投稿.
	 * 
	 * @param form   コメントのフォーム
	 * @param result エラーを格納
	 * @param model  リクエストスコープ
	 * @return 掲示板画面
	 */
	@RequestMapping("")
	public String insertComment(@Validated CommentForm form, BindingResult result,Integer articleId, Model model) {
		if (result.hasErrors()) {
			return showBbsController.index(model);
		}
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);
		comment.setArticleId(articleId);
		commentRepository.insert(comment);
		return "redirect:/article";
	}
	
}
