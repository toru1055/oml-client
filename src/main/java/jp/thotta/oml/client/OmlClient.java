package jp.thotta.oml.client;

import java.net.Socket;
import java.io.IOException;
import java.net.UnknownHostException;
import jp.thotta.oml.client.io.*;

public class OmlClient {
  String host;
  int port;
  Socket socket;
  SocketCommunication comm;
  String labelModeText;

  public OmlClient(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public void connect() throws IOException, UnknownHostException {
    this.socket = new Socket(host, port);
    this.comm = new SocketCommunication(this.socket);
  }

  public void close() throws IOException {
    this.socket.close();
  }

  public boolean configure(int modelId, String parserType, String labelMode) throws IOException {
    if(parserType != null) {
      comm.sendModelId(modelId);
      comm.sendParserType(parserType);
      boolean status = comm.recvStatus();
      String recvLabelMode = comm.recvLabelMode();
      if(status && labelMode.equals(recvLabelMode)) {
        this.labelModeText = labelMode;
        return true;
      }
    }
    return false;
  }

  public boolean train(String label, String features) throws IOException {
    comm.sendLabel(label);
    comm.sendFeatures(features);
    return comm.recvStatus();
  }

  public String predict(String features) throws IOException {
    comm.sendFeatures(features);
    return comm.recvLabel();
  }

  public Label predictLabel(String features) throws IOException {
    comm.sendFeatures(features);
    String labelText = comm.recvLabel();
    Label label = LabelFactory.createLabel(this.labelModeText);
    label.parse(labelText);
    return label;
  }

  public static OmlClient createTrainBatchConnection(String host)
    throws IOException, UnknownHostException {
    OmlClient client = new OmlClient(host, SocketCommunication.TRAIN_BATCH_PORT);
    client.connect();
    return client;
  }

  public static OmlClient createPredictBatchConnection(String host)
    throws IOException, UnknownHostException {
    OmlClient client = new OmlClient(host, SocketCommunication.PREDICT_BATCH_PORT);
    client.connect();
    return client;
  }
}
