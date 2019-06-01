# Desafio Dextra

2° VERSÃO: atomic-burguer
Server-side: Projeto Web service desenvolvido pela IDE Eclipse com as tecnologias Java8 e SpringBoot, usando o Maven para automação de compilação e gerencia das dependências.
Client-side: Desenvolvido em HTML, CSS e JavaScript(jQuery para chamada da API via Web service do server-side).

Instruções para executar a aplicação:
1) Após clonar o repositório, no diretório do projeto executar o comando: mvn clean install

2) Na pasta target do diretório do projeto, executar o comando java -jar atomic-burguer-0.0.1-SNAPSHOT.jar

3) Na pasta front do diretório do projeto, executar o arquivo index.html

________________________________________________________________________________________________________________________________________

1° VERSÃO: WebService
Server-side: Projeto Web service desenvolvido pela IDE Eclipse com as tecnologias Java8 e JAX-RS empregando a arquitetura Rest, usando o Maven para automação de compilação e gerencia das dependências.
Client-side: Desenvolvido em HTML, CSS e JavaScript(jQuery para chamada da API via Web service do server-side).

Comportamento esperado da aplicação:
- É permitido escolher um lanche utilizando o combo box, automaticamente o valor do lanche será mostrado em "Total".
- Há possibilidade de adicionar ingredientes no lanche escolhido, pelos checkboxes dos ingredientes adicionais.
- Quando o ingrediente é selecionado, são exibida as quantidades disponíveis para o mesmo. 
- O botão "Finalizar pedido" é o resposável por calcular o valor total do pedido, exibindo o valor abaixo em "Total"

Cobertura de testes automatizados:
- Ferramentas utilizadas: JUnit e Mockito.
- Ao executar o start_server-side.bat o maven se encarrega em executar os testes da classe ServiceTest.java, construídos em JUnit com Mockito.
- Também é possível executar os testes pela IDE Eclipse, executando a ServiceTest.java pelo JUnit.

## Descrição

Somos uma startup do ramo de alimentos e precisamos de uma aplicação web para gerir nosso negócio. Nossa especialidade é a venda de lanches, de modo que alguns lanches são opções de cardápio e outros podem conter ingredientes personalizados.

A seguir, apresentamos a lista de ingredientes disponíveis:


INGREDIENTE           |   VALOR
:---------            | --------:
Alface                | R$ 0.40
Bacon                 | R$ 2,00
Hambúrguer de carne   | R$ 3,00
Ovo                   | R$ 0,80
Queijo                | R$ 1,50

Segue as opções de cardápio e seus respectivos ingredientes:


LANCHE        |   INGREDIENTES
:---------    | :--------------------------------------:
X-Bacon       | Bacon, hambúrguer de carne e queijo
X-Burger      | Hambúrguer de carne e queijo
X-Egg         | Ovo, hambúrguer de carne e queijo
X-Egg Bacon   | Ovo, bacon, hambúrguer de carne e queijo

O valor de cada opção do cardápio é dado pela soma dos ingredientes que compõe o lanche. Além destas opções, o cliente pode personalizar seu lanche e escolher os ingredientes que desejar. Nesse caso, o preço do lanche também será calculado pela soma dos ingredientes.

Existe uma <b>exceção</b> à regra para o cálculo de preço, quando o lanche pertencer à uma promoção. A seguir, apresentamos a lista de promoções e suas respectivas regras de negócio:

PROMOÇÃO        |  REGRA DE NEGÓCIO
:---------      | :--------------------------------------:
Light           | Se o lanche tem alface e não tem bacon, ganha 10% de desconto.
Muita carne     | A cada 3 porções de carne o cliente só paga 2. Se o lanche tiver 6 porções, ocliente pagará 4. Assim por diante...
Muito queijo    | A cada 3 porções de queijo o cliente só paga 2. Se o lanche tiver 6 porções, ocliente pagará 4. Assim por diante...
Inflação        | Os valores dos ingredientes são alterados com frequência e não gostaríamos que isso influenciasse nos testes automatizados.

## CRITÉRIOS DE COMPLETUDE

### O projeto deve ser entregue atendendo aos seguintes critérios:

- O server-side deve ser desenvolvido em Java, utilizando Maven para gerenciar as dependências.
- O client-side deve ser desenvolvido em HTML, CSS e JavaScript (apenas com jQuery, ou com algum framework se desejar)
- Deve possuir cobertura de testes automatizados para os seguintes pontos: Valor dos lanches de cardápio, regra para cálculo de preço e promoções.
- Não é necessário se preocupar com a autenticação dos usuários.
- Não é necessário persistir os dados em um banco, pode fazer armazenamento em memória.


## ENTREGÁVEIS

Você deve entregar um conjunto de artefatos, de acordo com o nível de complexidade que achar melhor. A seguir, os níveis de complexidade e seus respectivos entregáveis:

<b>Fácil:</b>
- [ ] Implementação dos requisitos;
- [ ] Instruções para executar.

<b>Médio:</b>
- [X] Implementação dos requisitos;
- [X] Relatório simples de justificativas para escolha do design de código;
- [X] Instruções para executar;

<b>Difícil:</b>
- [ ] Implementação dos requisitos;
- [ ] Relatório simples de justificativas para escolha do design de código;
- [ ] Os testes automatizados devem ser executados por algum modelo de integração contínua;
- [ ] O ambiente de execução da aplicação deve possuir um HTTP Proxying com nginx, redirecimendo as requisições da porta 80 para o server-side.
- [ ] Ambiente virtualizado em Docker com scripts para execução do projeto.
