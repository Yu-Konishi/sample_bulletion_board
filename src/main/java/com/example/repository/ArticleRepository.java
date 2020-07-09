package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

/**
 * articlesテーブルを操作するレポジトリクラス.
 * 
 * @author hikaru.tsuda
 *
 */
@Repository
public class ArticleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final ResultSetExtractor<List<Article>> ARTICLE_ROW_MAPPER = (rs) -> {
		List<Article> articleList = new ArrayList<>();
		List<Comment> commentList = null;
		int preId = -1;//行の位置を表す変数

		while (rs.next()) {
			if (preId != rs.getInt("a_id")) { // 前の行の記事IDと一致しなければ新しく記事をつくる
				Article article = new Article();
				preId = rs.getInt("a_id"); //行の位置を更新
				article.setId(rs.getInt("a_id"));
				article.setName(rs.getString("a_name"));
				article.setContent(rs.getString("a_content"));
				commentList = new ArrayList<>();
				article.setCommentList(commentList);
				articleList.add(article);
			}
			if (rs.getInt("com_id") != 0) {//コメントが存在すればコメントオブジェクトを生成
				Comment comment = new Comment();
				comment.setId(rs.getInt("com_id"));
				comment.setName(rs.getString("com_name"));
				comment.setContent(rs.getString("com_content"));
				commentList.add(comment);
			}
		}
		return articleList;
	};

	/**
	 * 全件検索メソッド.
	 * 
	 * @return 全記事情報リスト(id降順)
	 */
//	public List<Article> findAll() {
//		String sql = "SELECT id,name,content FROM articles ORDER by id DESC;";
//		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
//		return articleList;
//	}

	public List<Article> findAll() {
		String sql = "SELECT a.id a_id, a.name a_name, a.content a_content, c.id com_id, c.name com_name, c.content com_content, c.article_id article_id"
				+ " FROM articles a LEFT OUTER JOIN comments c ON a.id = c.article_id ORDER BY a.id;";
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		return articleList;
	}

	/**
	 * 記事の新規挿入メソッド.
	 * 
	 * @param article 記事情報
	 */
	public void insert(Article article) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		String sql = "INSERT INTO articles (name, content) VALUES ( :name, :content);";
		template.update(sql, param);
	}

	/**
	 * IDから記事の削除.
	 * 
	 * @param id ID
	 */
	public void findByarticleId(Integer id) {
		String sql = "DELETE FROM articles WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}

}
