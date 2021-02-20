# chat-multicast-java-PHMSilva
chat-multicast-java-PHMSilva created by GitHub Classroom
O código gerado atende a maior parte dos requisitos, logo não foi possível terminar a tempo;
No trabalho utilizei o protocolo UDP, na comunicacao entre o servidor e o cliente;
Criei três classes : a classe MainUDPClient.java que realiza as solicitações, MainUDPServer.java que recebe e reposnde as requisicoes do cliente e a classe TratamentoDeRequisicao,
que possui funções de armazenar dados do cliente, salas;

O protocolo de comunicação utilizado foi o protocolo UDP, que no parametro da funcao send() por parte do cliente são colocados parâmetros como: O indice escolhido,
o corpo da mensagem contendo as informações nescessárias para a requisição.

Na parte de elaboração do chat foi feito o uso do Multicast utilizando o IP da sala que é retornado pelo servidor, com a utilização do metodo joingroup() da classe multicast
a conexao foi realizada;
