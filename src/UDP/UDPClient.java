package UDP;


// Le uma linha do teclado
// Envia o pacote (linha digitada) ao servidor

import java.io.*; // classes para input e output streams e
import java.net.*;// DatagramaSocket,InetAddress,DatagramaPacket
import java.util.Arrays;
class UDPClient {
   private static DataInputStream dataInputStream = null;

   public static void main(String args[]) throws Exception
   {
      // if (args.length < 2) {
      //    System.out.println("Usage: java UDPClient <server_ip> <port>");
      //    return;
      // }
      String serverAddr = args[0];
      int port = Integer.parseInt(args[1]);

      // declara socket cliente
      DatagramSocket clientSocket = new DatagramSocket();
      byte[] sendData = new byte[16*1024];

      FileInputStream f = new FileInputStream("../../lib/max.txt");

      // f.read(sendData)      
      
      InetAddress IPAddress = InetAddress.getByName(serverAddr);
      int i =0;
      while(f.available()!=0)
      {
                  sendData[i]=(byte)f.read();
                  i++;
      }
      System.out.println(sendData.length);
      int size = i;
      int n=0, aux =0;
      while(size>= 0)
      { 
         n=aux;
         aux+=512;
            System.out.println("Aux " + size);
         if(size>=512){
            System.out.println("Sending data to server...");
            DatagramPacket sendPacket = new DatagramPacket(Arrays.copyOfRange(sendData, n, aux), 512, IPAddress, port);
            clientSocket.send(sendPacket);
         }
         else{
            System.out.println("Final data");
            DatagramPacket sendPacket = new DatagramPacket(Arrays.copyOfRange(sendData, n, sendData.length), size, IPAddress, port);
            clientSocket.send(sendPacket);
         }
         size -=512;
      }   
      f.close();
      
      System.out.println(sendData[100]);
      // cria pacote com o dado, o endereco do server e porta do servidor
      //envia o pacote

      // fecha o cliente
      clientSocket.close();
   }
}
