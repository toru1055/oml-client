package jp.thotta.oml.client.io;

/**
 * 多クラス分類用のラベル.
 */
public class MultiClassLabel implements Label {
  public static final String NULL_LABEL = "\001NULL-LABEL\001";
  String label = null;

  public void parse(String labelText) {
    if(NULL_LABEL.equals(labelText)) {
      label = null;
    } else {
      label = labelText;
    }
  }

  public String getText() {
    if(label != null) {
      return label;
    } else {
      return NULL_LABEL;
    }
  }

  public int getLabelMode() {
    return LabelFactory.MULTI_MODE;
  }

  public String getLabel() {
    return label;
  }

  public Double getScore() {
    return null;
  }

  public Boolean isPositive() {
    return null;
  }
}
