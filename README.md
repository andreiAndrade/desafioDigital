# Projeto Sessão de Votação em Pauta

Aplicação que expõe uma API RESTful de criação de pauta, sessão e votação.

## Detalhes técnicos
* Configuração default de Banco de dados em memória com H2.
  - Para configurar um banco de dados é necessário descomentar as linhas 2, 3, 4, 7 e 10 do application.properties localizado em src/main/resources/application.properties. Além disso dever descomentado as linhas 41 até 45 e comentado as linhas 46 até 50 dom pom.xml localizado na raiz do projeto. Esta configuração é feita, pois dessa forma o banco de dados H2 é inativado e o banco de dados Postgres, hospedados no Heroku, passa a ser utilizado pela aplicação. A ideia de utilizar o H2 para desenvolvimento se dá pois a conexão e testes são muito mais rápidos. A senha utilizada para acessar o banco via aplicação deve ser solicitada ao responsável pelo repositório, esta que será inserida na linha 4 do application.properties.

* Para documentação da API foi utilizado o Swagger. Para acessar a documentação é necessário rodar a aplicação e acessar a url: http://localhost:8080/swagger-ui.html.

## Especificações do projeto

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação.
A partir disso, você precisa criar uma solução back-end para gerenciar essas sessões de votação.
Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API
REST:

[x] Cadastrar uma nova pauta;

[x] Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo
determinado na chamada de abertura ou 1 minuto por default);

[x] Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é
identificado por um id único e pode votar apenas uma vez por pauta);

[x] Contabilizar os votos e dar o resultado da votação na pauta.

Para fins de exercício, a segurança das interfaces pode ser abstraída e qualquer chamada para as interfaces
pode ser considerada como autorizada. A escolha da linguagem, frameworks e bibliotecas é livre (desde que
não infrinja direitos de uso).
É importante que as pautas e os votos sejam persistidos e que não sejam perdidos com o restart da aplicação.

### Tarefas bônus
As tarefas bônus não são obrigatórias, mas nos permitem avaliar outros conhecimentos que você possa ter.
A gente sempre sugere que o candidato pondere e apresente até onde consegue fazer, considerando o seu
nível de conhecimento e a qualidade da entrega.

[x] Tarefa Bônus 1 - Integração com sistemas externos
  - Integrar com um sistema que verifique, a partir do CPF do associado, se ele pode votar
    - GET https://user-info.herokuapp.com/users/{cpf}
    - Caso o CPF seja inválido, a API retornará o HTTP Status 404 (Not found). Você pode usar geradores de CPF para gerar CPFs válidos;
    - Caso o CPF seja válido, a API retornará se o usuário pode (ABLE_TO_VOTE) ou não pode (UNABLE_TO_VOTE) executar a operação.

[ ] Tarefa Bônus 2 - Mensageria e filas
  - O resultado da votação precisa ser informado para o restante da plataforma, isso deve ser feito preferencialmente através de mensageria. Quando a sessão de votação fechar, poste uma mensagem com o resultado da votação.

[ ] Tarefa Bônus 3 - Performance
  - Imagine que sua aplicação possa ser usada em cenários que existam centenas de milhares de votos. Ela deve se comportar de maneira performática nesses cenários;
  - Testes de performance são uma boa maneira de garantir e observar como sua aplicação se comporta.

[x] Tarefa Bônus 4 - Versionamento da API
  - Como você versionaria a API da sua aplicação? Que estratégia usar?
    - O versionamento da API foi feito através da abordagem por media type, ela consiste em informar a versão da api no cabeçalho da requisição, ou seja, quando surge um nova versão de um endpoint ele pode ser acessado adicionando o parâmetro "Accept" no cabeçalho com o valor "application/vnd.digital.app-{versao: v1, v2, v3, etc}+json". Desta forma a manutenção torna-se mais fácil, visto que o recurso é sempre o mesmo, o que difere é apenas uma propriedade da requisição que pode ser fácilmente alerada. Caso o cliente não defina a versão, ele acessará a última versão por padrão. Essa abordagem é muito perigosa visto que poderá haver alterar de payload e retorno.

### O que será analisado
Simplicidade no design da solução (evitar over engineering)
Organização do código
Arquitetura do projeto
Boas práticas de programação (manutenibilidade, legibilidade etc)
Possíveis bugs
Tratamento de erros e exceções
Explicação breve do porquê das escolhas tomadas durante o desenvolvimento da solução
Uso de testes automatizados e ferramentas de qualidade
Limpeza do código
Documentação do código e da API
Logs da aplicação
Mensagens e organização dos commits

## Pontos de atenção

* Tendo em vista a necessidade de definição de técnologia, arquitetura, padrões, etc, o tempo pode ter sido um pouco curto para se obter excelência no projeto.

## Pontos a melhorar

* Foram realizados poucos testes unitário, sendo eles apenas das requisições http. Pode-se evoluir nos testes em questão de: testes de banco de dados, testes especificos da camada de serviço e teste de performance.

* A decisão de não utilizar Javadoc foi feita com base no entendimento de que o código estava limpo e legível o suficiente para enteder a responsabilidade de cada método e clase do sistema. Ele poderia somar no fonte, mas no primeiro instante ele não se fez tão necessário.

* Poderia ser utilizado uma ferramente para qualidade de código como o SonarUI, para gerar relatórios de qualidade de código, pontos a melhorar, cobertura de testes, etc.

* A aplicação poderia ter sido hospedada no Heroku, assim como o banco de dados, para que tivesse disponibilidade de teste.

* Poderia ter sido utilizado ferramentas de integração contínua.

* Poderia ser utilizado para o gerenciamento de commits o Git flow para tornar a evolução do código mais segura.

* Poderia ter sido adicionado a biblioteca Lombok, que reduz muito código.

* A arquitetura utilizada poderia ter sido algo mais voltado a Clean Architecture, visando maior manutenibilidade do código.

## Pontos fortes

* Utilização do Swagger foi muito importante para utilização da API, visto que ele cria uma documentação bem completa para que mesmo que não conhece a API e o negócio possa utiliza-la.

* As entidade foram isoladas da camada de controller, fazendo com que a API ficasse mais clara, visto que os objetos utilizado na camada de controller para representar as entidades são mais resumidos e direcionado para seu propósito, como objetos de saída e entrada da API.

* A utilização da biblioteca ModelMapper ajuda a fazer o mapeamento do objetos de entrada e saída para as entidades de uma forma clara e simples.

* O uso do banco H2 foi muito importante durante o desenvolvimento, visto que o acesso era mais rápido e como ficara em memória, não corria risco de existir lixo no banco.
