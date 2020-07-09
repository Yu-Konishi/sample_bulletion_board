package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 記事情報を受け取るフォームクラス.
 * 
 * @author hikaru.tsuda
 *
 */
public class ArticleForm {
	
	/**	投稿者名 */
	@NotBlank(message="名前を入力してください")
	@Size(min=1, max=50, message="名前は50文字以下で入力してください")
	private String name;
	/**	投稿内容 */
	@NotBlank(message="内容を入力してください")
	private String content;
	
	@Override
	public String toString() {
		return "ArticleForm [name=" + name + ", content=" + content + "]";
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
