package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

/**
 * コメントのリポジトリ.
 * 
 * @author yu.konishi
 *
 */
@Repository
public class CommentRepository {

	/**
	 * コメントのオブジェクトを生成
	 */
	private final static RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {
		Comment comment = new Comment(rs.getInt("id"), 
									  rs.getString("name"), 
									  rs.getString("content"),
									  rs.getInt("article_id"));
		return comment;
	};

	/**
	 * JDBCを操作するテンプレート
	 */
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 投稿した記事のIDからコメントを取得.
	 * 
	 * @param articleId 投稿した記事のID
	 * @return 該当したコメント
	 */
	public List<Comment> findByArticleId(int articleId) {
		String sql = "SELECT id,name,content,article_id FROM comments WHERE article_id=:articleId ORDER BY id DESC;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		List<Comment> commentList = template.query(sql, param, COMMENT_ROW_MAPPER);
		return commentList;
	}

	/**
	 * 新たなコメントを挿入.
	 * 
	 * @param comment コメント
	 */
	public void insert(Comment comment) {
		String sql = "INSERT INTO comments(name,content,article_id) VALUES (:name,:content,:articleId);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", comment.getName())
				.addValue("content", comment.getContent()).addValue("articleId", comment.getArticleId());
		template.update(sql, param);
	}

	/**
	 * コメントを削除.
	 * 
	 * @param articleId 投稿した記事のID
	 */
	public void deleteByArticleId(int articleId) {
		String sql = "DELETE FROM comments WHERE article_id=:articleId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		template.update(sql, param);
	}

}
