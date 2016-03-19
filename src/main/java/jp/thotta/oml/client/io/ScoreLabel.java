package jp.thotta.oml.client.io;

/**
 * 回帰用のラベル.
 */
public class ScoreLabel implements Label {
  public static final String NULL_LABEL = "\001NULL-LABEL\001";
  Double score = null;

  public void parse(String labelText) {
    if(NULL_LABEL.equals(labelText)) {
      score = null;
    } else {
      score = Double.parseDouble(labelText);
    }
  }

  public String getText() {
    if(score != null) {
      return String.valueOf(score);
    } else {
      return NULL_LABEL;
    }
  }

  public int getLabelMode() {
    return LabelFactory.SCORE_MODE;
  }

  public String getLabel() {
    return null;
  }

  public void setScore(Double d) {
    this.score = d;
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
