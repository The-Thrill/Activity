package Udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import Utils.LogUtils;

public class UdpServer {

   private static final String TAG = "UdpServer";
   private InetAddress inetAddress;
    private int mPort = 7777;
    private DatagramSocket socket;

    private Scanner scanner;

    public UdpServer() {
        try {
            inetAddress = InetAddress.getLocalHost();
            socket = new DatagramSocket(mPort, inetAddress);

            scanner = new Scanner(System.in);
            scanner.useDelimiter("\n");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void start() {
       while (true) {
          try {
             byte[] bytes = new byte[1024];
             DatagramPacket receivePacket = new DatagramPacket(bytes,bytes.length);
             socket.receive(receivePacket);

             InetAddress address = receivePacket.getAddress();
             int port = receivePacket.getPort();
             String clientMsg = new String(receivePacket.getData(), 0, receivePacket.getLength());
             LogUtils.i(TAG, "address = " + address + ", port = " + port + ", clientMsg = " + clientMsg);

             String returnMag = scanner.next();
             byte[] returnMagBytes = returnMag.getBytes();
             DatagramPacket sendPacket = new DatagramPacket(returnMagBytes, returnMagBytes.length,receivePacket.getSocketAddress());
             socket.send(sendPacket);

          } catch (IOException e) {
             e.printStackTrace();
          }
       }
    }

    public static void main(String[] args) {
        new UdpServer().start();
    }
}
