package UDP;

// Recebe um pacote de algum cliente
// Separa o dado, o endereco IP e a porta deste cliente
// Imprime o dado na tela

import java.io.*;
import java.net.*;

class UDPServer {
   public static void main(String args[])  throws Exception
      {
         // if (args.length < 1) {
         //    System.out.println("Usage: java UDPServer <port>");
         //    return;
         // }

         int port = Integer.parseInt(args[0]);

         // cria socket do servidor com a porta 2000
         DatagramSocket serverSocket = new DatagramSocket(2000);
         int remoteServerPort = serverSocket.getLocalPort();
         System.out.println("server running on port : " + remoteServerPort);
               byte buffer[]=new byte[16*1024];
               FileOutputStream f=new FileOutputStream("arqSaida.txt");
               while(true)
               {
                           DatagramPacket dp=new DatagramPacket(buffer,buffer.length);
                           serverSocket.receive(dp);
                           System.out.println(buffer[100]);  
                           f.write(buffer,0,dp.getLength());                           
               }
      }
}
