package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.WikiDto;

public class WikiDao extends Dao {
  
  /** このDaoが担当するテーブル物理名 */
  private static final String MY_TABLE = "wikis";
  
  /**
   * コンストラクタ
   * 自身が担当するテーブル物理名を設定
   */
  public WikiDao() {
    super();
    this.table = MY_TABLE;
  }
  
  /**
   * addメソッド
   * 引数のWikiDtoのフィールド値をDBのテーブルに追加する
   * @param dto 追加するWikiDto
   */
  protected void add(WikiDto dto) {
    String sql = String.format("insert into %s(title, body) values('%s', '%s');", table, dto.getTitle(), dto.getBody());
    executeUpdate(sql);
  }
  
  /**
   * addAllメソッド
   * 引数のWikiDtoのListにあるwikiを全てDBのテーブルに追加する
   * @param list 追加するWikiDtoのArrayList
   */
  public void addAll(List<WikiDto> list) {
    open();
    for(WikiDto item : list) {
      add(item);
    }
    close();
  }
  
  /**
   * mapメソッド
   * DBから取得したResultSetをWikiDtoにマッピングする
   * @param ResultSet DBから取得した実行結果オブジェクト
   * @return 各フィールド値を設定済みのWikiDtoのインスタンス
   */
  public static WikiDto map(ResultSet rs) {
    WikiDto wd = new WikiDto();
    try {
      wd.setId(rs.getInt("id"));
      wd.setTitle(rs.getString("title"));
      wd.setBody(rs.getString("body"));
    } catch(SQLException e) {
      e.printStackTrace();
    }
    return wd;
  }
  
  /**
   * getAllメソッド
   * DBよりwikiを全件取得し、WikiDtoのリストにして返す
   * @return wikiを全件持つWikiDtoのArrayList
   */
  public List<WikiDto> getAll() {
    // ArrayListの生成
    ArrayList<WikiDto> list = new ArrayList<WikiDto>();
    // DB接続を開く
    open();
    // 担当テーブルのレコードを全件取得
    ResultSet rs = selectAll();
    try {
      // 取得したレコード数分ループしてDtoにマッピングし、リストに追加
      while(rs.next()) {
        list.add(map(rs));
      }
    } catch(SQLException e) {
      e.printStackTrace();
    }
    // DB接続を閉じる
    close();

    return list;
  }

}
