package jp.thotta.oml.client;

import junit.framework.TestCase;

public class OmlClientTest extends TestCase {
  String host = "localhost";
  OmlClient tr;
  OmlClient pr;

  public void testTrainPredict() {
    try {
      tr = OmlClient.createTrainBatchConnection(host);
      assertTrue(tr.configure(1, "ma", "binary"));
      assertTrue(tr.train("positive", "ももももももももものうち"));
      assertTrue(tr.train("negative", "隣の客はよく柿食う客だ"));
      assertTrue(tr.train("negative", "隣の客はよく柿食う客だ"));
      assertTrue(tr.train("negative", "隣の客はよく柿食う客だ"));
      assertTrue(tr.train("negative", "隣の客はよく柿食う客だ"));
    } catch(Exception e) {
      e.printStackTrace();
      System.exit(1);
    } finally {
      try {
        tr.close();
      } catch(Exception e) {
        e.printStackTrace();
        System.exit(1);
      }
    }

    try {
      pr = OmlClient.createPredictBatchConnection(host);
      assertTrue(pr.configure(1, "ma", "binary"));
      String label1 = pr.predict("すもももももももものうち");
      String label2 = pr.predict("隣の客はよく牡蠣食う客だ");
      assertEquals(label1, "positive");
      assertEquals(label2, "negative");
    } catch(Exception e) {
      e.printStackTrace();
      System.exit(1);
    } finally {
      try {
        pr.close();
      } catch(Exception e) {
        e.printStackTrace();
        System.exit(1);
      }
    }
  }
}
