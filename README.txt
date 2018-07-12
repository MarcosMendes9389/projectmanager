############################################################
#							
#  	Gerenciador de Projetos    
#							
#############################################################
#								
# Autor: Marcos Mendes https://github.com/marcosmendes9389	
# 							
# Abril / 2018						
#							
#############################################################



######## Tecnologias utilizadas:

- java 8 (Oracle)
- Spring Boot 2.0.1.RELEASE
- Spring Batch 3.0.2.RELEASE
- MySQl (base de dado)


######## Configurações da base de dados

1 - O projeto foi desenvolvido utilizando a base de dados MySql

2 - No sistema estão definidas as seguintes configurações:
	 
drive =com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/projectmanager
username=root
password=root

Ou seja, pressupõe a existência de um banco de dados com o nome projectmanager,
 usuário "root" e senha "root" na máquina em que está sendo executado o sistema

Obs1: - Essas configurações padrões podem ser editadas no arquivo "projectmanager/src/main/resources/application.properties"

3 - Aplicar o script inicial que se encontra na pasta "projectmanager/src/main/resources/InitialSchema.sql". 

Obs2: A primeira linha do arquivo cria o banco de dados, caso tenha alterado o nome do banco de dados 
no passo 2, alterar também esta linha.



######## Configurações do Projeto - Passos para preparação e inicialização do sistema pelo Eclipse

1 - No Eclipse escolha File -> Import -> Maven -> Existing Maven Projects , clique em "Next". 
No campo "Root Directory" escolha a pasta principal do projeto (pasta "projectmanager") e depois "Finish".

Obs1: Este é um projeto maven desenvolvido na IDE Eclipse Neon (com java 8 oracle). 

Obs2: Clique no menu do Eclipse Project -> Clean -> Clean all projects, as vezes é necessário.
 
3 - A classe principal a ser executada é "Setting.java" do pacote br.com.projectmanager. Clique com o botão na classe e escolha  Run As-> java Application.
Pronto, o sistema irá iniciar e pronto para uso.



######### Resumo #########

1 - Configurar base de dados MySQL (usuário , senha e script inicial)

2 - Aplicação: importar projeto existente e executar classe Setting.java como java application.

3 - Acessar aplicação pelo link http://localhost/ .

########## Acesso à aplicação ###############

- A aplicação pode ser acessada pelo Endereço http://localhost/
- A porta foi alterada para 80 para facilitar o acesso do usuário, mas pode ser altarada no projeto no arquivo applicatio.properties


######### Funcionalidades ################

- Menu principal com 5 botões, são eles: Usuários, Projetos, Associar Projeto-Usuário, Importar Arquivo (Upload) e Importar Arquivo (Spring Batch) .


- Menu Usuário:
	
	1 - Botão "Lista de Usuário": Lista todos os usuários do sistema com suas informações;
	2 - Botão "Cadastrar Usuário": Cadasra usuário no sistema.


- Menu Projetos 
	
	1 - Botão "Lista de Projetos": Lista todos os projetos do sistema com suas informações. 
		1.1 - Ao lado de cada projeto terá o nome um botão para listar os uauŕios envolvidos com suas respectivas funções no projeto.
	2 - Botão "Cadastrar Projetos": Cadasra projeto no sistema.
	3 - Botão "Arquivar Projeto": Arquivar um projeto.

- Associar Projeto-Usuário

	1 - Associar Usuário a um projeto e Definir a função do usuário na projeto.

- Importar Arquivo (Spring Batch)

	1 - Inicia um Job do Spring Batch que importa o aquivo "projectmanager/src/main/resources/gm-challenge.csv". 
		Caso queira importar um novo arqivo via Spring Batch, basta renomear o arquivo para "gm-challenge.csv" 
		e colocar na pasta "projectmanager/src/main/resources". 
		O projeto já está em hot deploy, ao trocar o arquivo com a aplicação em execução, o sistema automaticamente reiniciará.

- Importar Arquivo (Upload)

	1 - Opção de escolher um arquivo para upload e importá-lo (importação via CSVParse, não Spring Batch).
	

OBS: O motivo de ter duas opções de importação é que via Spring Batch ainda não foi implementado o carregamento dinâmico do arquivo,
é necessário que esteja na pasta "resource"	 



