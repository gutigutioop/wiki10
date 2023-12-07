package constants;

/**
 * Constクラス
 * 定数を保持するクラス

 * @author　Say Consulting Group
 * @version　1.0.0
 */
public class Const {

  /** ウェルカムメッセージ */
  public static final String WELCOME_MESSAGE = "マイWikiアプリへようこそ！\n";

  /**  終了メッセージ */
  public static final String FAIRWELL_MESSAGE = "終了します。またね！";  

  /** 入力メッセージ */
  public static final String IMPUT_MESSAGE = "\n\nwikiの番号又は検索文字列を入力して下さい。\n0を入力すると終了します。\n\nwiki>";

  /** wikiが配置されているフォルダのパス */
  public static final String WIKI_PATH = "wikis";

  /** 一覧の一行に表示するwiki数 */
  public static final int INDEX_LENGTH = 4;
  
  /** DBの接続先*/
  public static final String DB_URI
    = "jdbc:mysql://localhost:3306/wiki?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true";
  
  /** DBへ接続するユーザー名*/
  public static final String DB_USER = "root";
  
  /** DBへ接続するパスワード */
  public static final String DB_PASS = "root";
}
