package com.example.form;

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
	private String id;
	/**
	 * 名前
	 */
	private String name;
	/**
	 * 内容
	 */
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
