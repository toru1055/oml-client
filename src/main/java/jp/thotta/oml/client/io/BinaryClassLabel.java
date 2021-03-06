package jp.thotta.oml.client.io;

/**
 * ２クラス分類用のラベル.
 */
public class BinaryClassLabel implements Label {
  public static final String NULL_LABEL = "\001NULL-LABEL\001";
  public static String POSITIVE_LABEL = "positive";
  public static String NEGATIVE_LABEL = "negative";
  Boolean label = null;

  public void parse(String labelText) {
    if(!NULL_LABEL.equals(labelText)) {
      if(POSITIVE_LABEL.equals(labelText)) {
        label = true;
      } else {
        label = false;
      }
    } else {
      label = null;
    }
  }

  public String getText() {
    if(label == null) {
      return NULL_LABEL;
    }
    return label ? POSITIVE_LABEL : NEGATIVE_LABEL;
  }

  public int getLabelMode() {
    return LabelFactory.BINARY_MODE;
  }

  public void setLabel(boolean l) {
    this.label = l;
  }

  public String getLabel() {
    if(label == null) {
      return null;
    }
    if(label) {
      return POSITIVE_LABEL;
    } else {
      return NEGATIVE_LABEL;
    }
  }

  public Double getScore() {
    if(label == null) {
      return null;
    }
    if(label) {
      return 1.0;
    } else {
      return 0.0;
    }
  }

  public Boolean isPositive() {
    return label;
  }
}
