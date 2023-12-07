package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import constants.Const;

/**
 * Daoクラス - 各daoの基底クラス
 * データベースへの接続、切断、クエリ実行など各dao共通の機能を実装する
 * @author　Say Consulting Group
 * @version　1.0.0
 */
public class Dao {

  /** DB接続オブジェクト */
  protected Connection con;
  /** SQL実行用オブジェクト */
  protected PreparedStatement stm;
  /** 対象テーブル物理名 */
  protected String table;
  
  /**
   * コンストラクタ
   * DB接続用のフィールドを初期化する
   */
  public Dao() {
    this.con = null;
    this.stm = null;
  }

  /**
   * データベースへの接続を開始する
   * 
   * @param sql
   * @param dao
   */
  protected void open() {
    try {
      this.con = DriverManager.getConnection(Const.DB_URI, Const.DB_USER, Const.DB_PASS);
      System.out.println("データベースへの接続に成功しました。");
    } catch (SQLException e) {
      System.err.println("データベースへの接続に失敗しました。");
      System.err.println(e);
    }
  }

  /**
   * DBとの接続を切断する
   * 各オブジェクトを再初期化する
   */
  protected void close() {
    try {
      if (this.stm != null) {
        this.stm.close();
        this.stm = null;
      }
      if (this.con != null) {
        this.con.close();
        this.con = null;
      }
      System.out.println("データベース接続の切断に成功しました。");
    } catch (SQLException e) {
      System.err.println("データベース接続の切断に失敗しました。");
      System.err.println(e);
    }
  }
  
  /**
   * executeUpdateメソッド
   * DBテーブルの更新sqlを実行する
   * @param sql 更新を実行するsql
   */
  public void executeUpdate(String sql) {
    try {
      this.stm = this.con.prepareStatement(sql);
      // 実行SQLの表示
      System.out.println("SQL:" + this.stm.toString());
      this.stm.executeUpdate();
      System.out.println("更新SQLの実行に成功しました。");
    } catch (SQLException e) {
      System.err.println("更新SQLの実行に失敗しました。");
      System.err.println(e);
    }
  }
 
  /**
   * clearメソッド
   * 担当テーブルをtruncate(全件削除)する
   */
  public void clear() {
    open();
    String sql = String.format("truncate %s;", this.table);
    executeUpdate(sql);
    close();
  }

  /**
   * 検索用SQLを実行する
   * 
   * @param sql 実行するsql
   * @return ResultSet 実行結果オブジェクト
   */
  protected ResultSet executeQuery(String sql) {
    ResultSet rs = null;
    try {
      this.stm = this.con.prepareStatement(sql);
      // 実行SQLの表示
      System.out.println("SQL:" + this.stm.toString());
      rs = this.stm.executeQuery();
      System.out.println("SQL実行結果の取得に成功しました。");
    } catch (SQLException e) {
      System.err.println("SQL実行結果の取得に失敗しました。");
      System.err.println(e);
    }
    return rs;
  }
  
  /**
   * selectAllメソッド
   * 対象テーブルのレコードを全権取得する
   * @return ResultSet 対象テーブルの全レコードを保持する実行結果オブジェクト
   */
  protected ResultSet selectAll() {
    String q = String.format("select * from %s;", this.table);
    ResultSet rs = this.executeQuery(q);
    return rs;
  }
}
