package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * コメントのフォーム.
 * 
 * @author yu.konishi
 *
 */
public class CommentForm {
	/**
	 * ID
	 */
	private String articleId;
	/**
	 * 名前
	 */
	@NotBlank(message="名前を入力してください")
	@Size(min=1, max=50, message="名前は50文字以下で入力してください")
	private String name;
	/**
	 * 内容
	 */
	@NotBlank(message="内容を入力してください")
	private String content;

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
