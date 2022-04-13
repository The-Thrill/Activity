package Udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import Utils.LogUtils;

public class UdpClient {
   private static final String TAG = "UdpClient";
   private String mServerIp = "192.168.1.5";
   private InetAddress inetAddress;
   private int mServerPort = 7777;
   private DatagramSocket socket;
   private Scanner scanner;

   public UdpClient() {
      try {
         inetAddress = InetAddress.getByName(mServerIp);
         socket = new DatagramSocket();
         scanner = new Scanner(System.in);
         scanner.useDelimiter("\n");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void start() {
      while (true) {
         try {
            String clientMsg = scanner.next();
            byte[] clientMsgBytes = clientMsg.getBytes();
            DatagramPacket clientPacket = new DatagramPacket(
                    clientMsgBytes,
                    clientMsg.length(),
                    inetAddress,
                    mServerPort);
            socket.send(clientPacket);

            byte[] bytes = new byte[1024];
            DatagramPacket serverMsgPacket = new DatagramPacket(bytes, bytes.length);
            socket.receive(serverMsgPacket);

            String serverMsg = new String(serverMsgPacket.getData(), 0, serverMsgPacket.getLength());
            LogUtils.i(TAG,  "serverMsg = " + serverMsg);

         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   public static void main(String[] args) {
      new UdpClient().start();
   }
}
