package jp.thotta.oml.client.io;

/**
 * 回帰用のラベル.
 */
public class ScoreLabel implements Label {
  Double score = null;

  public void parse(String labelText) {
    score = Double.parseDouble(labelText);
  }

  public String getText() {
    return String.valueOf(score);
  }

  public int getLabelMode() {
    return LabelFactory.SCORE_MODE;
  }

  public String getLabel() {
    return null;
  }

  public Double getScore() {
    return score;
  }

  public Boolean isPositive() {
    if(score == null) {
      return null;
    }
    return (score > 0.0);
  }
}
