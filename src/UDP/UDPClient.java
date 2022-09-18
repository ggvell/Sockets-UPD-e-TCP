package UDP;


// Le uma linha do teclado
// Envia o pacote (linha digitada) ao servidor

import java.io.*; // classes para input e output streams e
import java.net.*;// DatagramaSocket,InetAddress,DatagramaPacket

class UDPClient {
   private static DataInputStream dataInputStream = null;

   public static void main(String args[]) throws Exception
   {
      if (args.length < 2) {
         System.out.println("Usage: java UDPClient <server_ip> <port>");
         return;
      }
      String serverAddr = args[0];
      int port = Integer.parseInt(args[1]);

      // declara socket cliente
      DatagramSocket clientSocket = new DatagramSocket(2000);
      byte[] sendData = new byte[1024];

      FileInputStream f=new FileInputStream("../../lib/min.txt");
      // obtem endereco IP do servidor com o DNS
      InetAddress IPAddress = InetAddress.getByName(serverAddr);
      int i =0;
      while(f.read(sendData) != -1)
      {
                  sendData[i]=(byte)f.read();
                  i++;
      }                    
      f.close();

      // cria pacote com o dado, o endereco do server e porta do servidor
      DatagramPacket sendPacket = new DatagramPacket(sendData, i, IPAddress, port);
      //envia o pacote
      clientSocket.send(sendPacket);

      // fecha o cliente
      clientSocket.close();
   }
}
