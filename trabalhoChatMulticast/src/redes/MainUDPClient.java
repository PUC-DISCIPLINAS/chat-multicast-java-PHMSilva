package redes;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;
public class MainUDPClient {

	@SuppressWarnings({ "resource", "deprecation" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatagramSocket aSocket = null;// ira criar um ouvinte(listen) que ira disponibiliaz uma porta
		int serverPort = 6789; // porta que sera usada 
		String requisicao=null;
		String message; // mensagem que sera enviada
		Scanner entrada = new Scanner(System.in);
		Scanner entrada1 = new Scanner(System.in);
		String nomeSala=null;
		Integer indiceEscolhido= 6;
		//String alternativaEscolhida= null;
		String ipEscolhido= null;
		String mensagem=" ";
		
		
		try {
			System.out.println("Escolha um indice : ");
			System.out.println("1- Criar Sala ");
			System.out.println("2- Listar Salas ");
			System.out.println("3- Entrar na Sala ");
			System.out.println("0- Sair");
			indiceEscolhido= entrada1.nextInt();
			
			System.out.println("indice escolhido: "+indiceEscolhido);
			
			if(indiceEscolhido==1) {
				System.out.println("Escolha um nome para a sala");
				nomeSala= entrada.nextLine();
				requisicao=Integer.toString(indiceEscolhido)+":"+nomeSala.trim();
				System.out.println("dados : "+requisicao);
			}
			if(indiceEscolhido==2) {
				requisicao= Integer.toString(indiceEscolhido);
			}
			if(indiceEscolhido==3) {
				System.out.println("Informe o ID da sala : ");
				ipEscolhido= entrada.nextLine();
				System.out.println("Informe o seu nome");
				String nomeUsuario= entrada.nextLine();
				
				requisicao=Integer.toString(indiceEscolhido)+":"+ipEscolhido.trim()+":"+nomeUsuario.trim();
				nomeUsuario=null;
			}
			
			System.out.println("Informe o ip do servidor:");
			ipEscolhido = entrada.next();
			aSocket = new DatagramSocket();
			byte[] m = requisicao.getBytes();// oque sera enviado
			InetAddress aHost = InetAddress.getByName(ipEscolhido); //Para quem quero enviar ou seja aqui vai pegar o endereco (localhost, ou merda e etc)
			// na linha 23 onde cria o pacote que sera enviado que vai ter, a mensagem que esta no m e args, o endereco e a porta
			DatagramPacket request = new DatagramPacket(m, requisicao.length(), aHost, serverPort);
			aSocket.send(request);// aqui envia o pacote
			
			byte[] buffer = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			aSocket.receive(reply); // aqui é o bloqueante
			message = new String(reply.getData()).trim(); // mensagem respondida
			
			System.out.println("Resposta: " + message); // imprime na tela a mensagem recebida
			
			if(indiceEscolhido==3) {
				String [] params=null;
				params = message.split(":");
				MulticastSocket mSocket=null;
				mensagem = entrada1.nextLine();
				InetAddress groupIp = null;
				groupIp = InetAddress.getByName(params[0]);
				mSocket= new MulticastSocket(6790);
				mSocket.joinGroup(groupIp);
				mensagem = entrada1.nextLine();
				 ArrayList<String> mensagemChat = new ArrayList<String>();
				boolean online =true;
				while(online) {
					
				System.out.println("Enviar menssagem ? 4-sim 5-nao 0-Sair da sala");
				indiceEscolhido=entrada1.nextInt();
				entrada1.nextLine();
				if(indiceEscolhido == 0) online=false;
				else {
				if(indiceEscolhido==4) {
					System.out.println("escreva");
					mensagem = entrada1.nextLine();
					requisicao="4:"+params[0]+":"+""+params[1]+":"+mensagem.trim();
					aSocket = new DatagramSocket();
					m = requisicao.getBytes();
					request.setData(m);
					request.setLength(requisicao.length());
					aSocket.send(request);
					buffer = new byte[1000];	
				}
			
					DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
					mSocket.receive(messageIn);// recebe mensagem
					mensagemChat.add( new String(messageIn.getData()).trim());
					System.out.println("mensagens recebidas : \n ");
					for(int i=0; i< mensagemChat.size();i++) {
					 System.out.println("\n"+mensagemChat.get(i));
					}
					buffer = new byte[1000];
					
				}
			
				}
				
			}
			
			
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			//mSocket.leaveGroup(groupIp);
			if (aSocket != null && indiceEscolhido==0) {
				aSocket.close();
			}
		}
	}

}
