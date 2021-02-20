package redes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class MainUDPServer {
	@SuppressWarnings("deprecation")
	public static void main(String args[]) {
	DatagramSocket aSocket = null;
	MulticastSocket mSocket = null;
	String message;
	String comandos[]=null;
	Integer indiceEscolhido=null;
	TratamentoDeRequisicao requisicoes;

	try {

		aSocket = new DatagramSocket(6789);
		requisicoes = new TratamentoDeRequisicao();
		System.out.println("Servidor: ouvindo porta UDP/6789.");
		byte[] buffer = new byte[1000];
		

		while (true) {
			DatagramPacket request = new DatagramPacket(buffer, buffer.length);
			aSocket.receive(request); 
			message = new String(request.getData()).trim();
			System.out.println("mesangem recebida : "+message);
			buffer= new byte[1000];
		if(message.contains(":")) {
			comandos = message.split(":");
			indiceEscolhido= Integer.parseInt(comandos[0]);
		}
		else {
			indiceEscolhido = Integer.parseInt(message);
		}
			switch(indiceEscolhido) {
			case 1 :
				message = requisicoes.CriarSala(comandos[1]);
			break;
			case 2 :
				message=requisicoes.listarSalas();
			break;
			case 3 :
				message = requisicoes.entrarSala(comandos[1], comandos[2]);
			break;
			case 4 :
				mSocket = new MulticastSocket(6790);
				InetAddress groupIp = null;
				groupIp=InetAddress.getByName(comandos[1]);
				String menssagemChat ="\n";
				String nome= comandos[2];
				menssagemChat=comandos[3];
				message=nome+":"+menssagemChat;
				byte[] m=message.getBytes();
				mSocket.joinGroup(groupIp);
				DatagramPacket messageOut = new DatagramPacket(m, message.length(), groupIp, 6790);
				mSocket.send(messageOut);
			break;
		
			default:
				System.out.println("Alternativa não existente");
				
			}
			
			byte[] m = message.getBytes();

			
			if(indiceEscolhido!=4) {
			DatagramPacket reply = new DatagramPacket(m, message.length(), request.getAddress(),
					request.getPort());
			aSocket.send(reply);
			}
		} 
	} catch (SocketException e) {
		System.out.println("Socket: " + e.getMessage());
	} catch (IOException e) {
		System.out.println("IO: " + e.getMessage());
	} finally {
		if (aSocket != null)
			aSocket.close();
	}
}
}
