package redes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TratamentoDeRequisicao {
	
	Map <Integer, Sala> salas = new HashMap<Integer,Sala>();
	Integer indiceSala=0;
	Integer IDMulticast =5;
	
	
	String CriarSala(String nomeSala){
		IDMulticast++;
		String enderecoSala = "228."+Integer.toString(IDMulticast)+".6"+".7";
		salas.put(indiceSala, new Sala(nomeSala,enderecoSala));
		//salas.get(indiceSala).adicionarMembro(nomeAdministrador);
		indiceSala++;
		return enderecoSala;
	}
	
	String listarSalas() {
		String lista="";
		String dados;
		for(int i=0;i<salas.size();i++) {
			dados="Nome: "+salas.get(i).Nome+" IP: "+salas.get(i).enderecoIp;
			lista+=dados.trim()+"\r\n";
			dados=null;
		}
		return lista;
	}
	
	String entrarSala(String ipSala,String nomeUsuario) {
		String resposta=null;
		for(int i=0;i<salas.size();i++) {
			if(salas.get(i).enderecoIp.equals(ipSala)) {
				salas.get(i).adicionarMembro(nomeUsuario);
				//membros=salas.get(i).listarMembros();
				
			}
		}
		
		resposta=ipSala+":"+nomeUsuario;
		
		return resposta;
		
	}
	
	
	
	
}

 class Sala{
	 
	 String Nome;
	 String enderecoIp;
	 ArrayList<String> membros = new ArrayList<String>();
	 
	 Sala(String nome, String endereco){
		this.Nome = nome;
		this.enderecoIp = endereco;
	 }
	 
	 
	 void adicionarMembro(String nome) {
		 membros.add(nome);
	 }
	 
	 String listarMembros() {
		 String listaDeMembros ="\n";
		 for(int i=0;i<membros.size();i++) {
			 listaDeMembros+=membros.get(i)+"\n";
			 
		}
		 return listaDeMembros;
	 }
	 
	 
	
}
