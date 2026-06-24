# Trabalho Prático - UC Engenharia de Software

## 🚀 Novas Funcionalidades:
* **Além do microsserviço de cupons, o sistema evoluiu para uma aplicação desktop completa, oferecendo uma experiência de gestão de checkout e estoque:**

## 🖥️ Interface Gráfica (Swing)
Foi desenvolvido uma interface desktop intuitiva para facilitar a operação do sistema:

<img width="626" height="367" alt="image" src="https://github.com/user-attachments/assets/0243759e-522d-4e96-9a40-fac7c6b95077" />
<br>

Gerenciador de Catálogo: Adicione, visualize e remova produtos do catálogo de forma simples e rápida.

<img width="296" height="369" alt="image" src="https://github.com/user-attachments/assets/c9cadfef-4cfb-43c7-a307-b25f4ccae3b0" />
<br>

<img width="509" height="230" alt="image" src="https://github.com/user-attachments/assets/0fbf1eb3-6c2d-4ba6-8b71-360521c1c35d" />
<br>

<img width="516" height="244" alt="image" src="https://github.com/user-attachments/assets/a9edbc0c-808d-40b3-897f-32d085a76308" />
<br>

Simulador de Descontos e Pagamentos: Interface integrada que permite calcular descontos e aplicar formas de pagamento (PIX, Débito, Dinheiro e Crédito) com cálculo automático de juros.


<img width="404" height="100" alt="image" src="https://github.com/user-attachments/assets/06aa78e9-9e29-4b7c-8b35-1ad0b6014e82" />
<br>

<img width="402" height="148" alt="image" src="https://github.com/user-attachments/assets/bc76cc38-7fc5-41d9-a2e0-7d8893f21ff7" />
<br>

<img width="343" height="151" alt="image" src="https://github.com/user-attachments/assets/fe645565-495c-4ac4-bad2-2ead6d937274" />
<br>

## 💾 Persistência de Dados
Catálogo Persistente: Agora, todos os produtos são salvos em um arquivo produtos.json. Isso garante que suas informações não sejam perdidas ao encerrar o sistema.


<img width="430" height="209" alt="image" src="https://github.com/user-attachments/assets/0443e72d-73f4-4bb4-9b98-89f46300e562" />
<br>

## 💳 Motor de Pagamento (Padrões de Projeto)
Strategy: Implementamos estratégias de pagamento isoladas, permitindo o cálculo de juros (crédito) ou pagamentos à vista de forma desacoplada.


<img width="396" height="288" alt="image" src="https://github.com/user-attachments/assets/2efdd49c-8a00-4323-83fc-4746e2547b83" />
<br>

Factory: Centralizamos a criação das regras de desconto e métodos de pagamento, facilitando a expansão do sistema para futuras formas de pagamento (ex: boletos, carteiras digitais).

## Instruções para Execução da Aplicação

Este projeto foi desenvolvido utilizando o padrão de gerenciamento de dependências do **Maven** e o **Java 23 (OpenJDK)**. Siga os passos abaixo para clonar, compilar e rodar os testes da aplicação em sua máquina local.

### Pré-requisitos
Antes de iniciar, certifique-se de ter instalado em sua máquina:
* **Java JDK 23** ou superior.
* **Apache Maven 3.9.x** ou superior.
* Variável de ambiente `JAVA_HOME` configurada apontando para a raiz do seu JDK (sem a pasta `\bin` no final).

### Passo a Passo

 **1 Clonar o Repositório:**
   Abra o seu terminal e execute o comando abaixo para clonar o projeto:
   git clone [https://github.com/fellipe0244/TrabalhoA3Lucas202601.git](https://github.com/fellipe0244/TrabalhoA3Lucas202601.git)

**2 Compilar o Projeto:**
    terminal > mvn clean compile

**3 ## Como utilizar a interface grafica: (CLI):**
* **Após compilar o projeto, abra a classe JanelaPrincipal**, e clique diretamente em run Java, ou Debug Java para abrir a Janela de interação. 
* **OBS: Se caso aparecer na parte superior em azul - Project: engenharia-software-ecommerce, é so clicar em cima para iniciar a aplicação.**

<img width="886" height="103" alt="image" src="https://github.com/user-attachments/assets/278e2458-4b1d-49eb-8655-9bbaa424ba1e" />
<br>
  
Os logs após as alterações são escritos no terminal de forma automatica.
O arquivo Json também é criado de forma automatica. 
    

**3 Executar testes automatizados**
    terminal > mvn test
    
## Como utilizar a interface grafica: 



## 1. Definição do Problema e Contexto
* **Cenário:** No cenário de plataformas de e-commerce, a retenção de clientes e o aumento de conversão de vendas dependem diretamente de campanhas de marketing eficientes, como a distribuição de cupons de desconto.
* **Problema Identificado:** O processo de validação e aplicação de cupons era acoplado diretamente ao fluxo de checkout, gerando falhas quando regras complexas de negócio (cupons expirados, cupons de primeira compra ou cumulativos) precisavam ser validadas simultaneamente.
* **Usuários Envolvidos:** Compradores (Clientes finais do e-commerce) e Administradores do Sistema (Gestores de Marketing).
* **Solução Proposta:** Um microsserviço isolado de validação de cupons (Back-end em Java) que centraliza as regras de negócio, garantindo escalabilidade, facilidade de manutenção e aplicando boas práticas de Engenharia de Software.

---

## 2. Levantamento e Análise de Requisitos (Abordagem Ágil)

### Atores Envolvidos
* **Cliente:** Deseja aplicar um cupom para obter desconto no carrinho.
* **Sistema de Checkout:** Consome a API de cupons para processar o valor final da compra.

### Backlog de Funcionalidades (User Stories)
* **US01 - Validação de Cupom Ativo:** Como Sistema de Checkout, quero validar se um cupom existe e está dentro do prazo de validade, para evitar o uso de descontos expirados.
* **US02 - Cálculo de Desconto Fixo:** Como Cliente, quero que um cupom de valor fixo (ex: R$ 20,00) seja deduzido do meu saldo total.
* **US03 - Cálculo de Desconto Percentual:** Como Cliente, quero que um cupom de valor percentual (ex: 10%) seja aplicado sobre o valor total da compra.

---

## 3. Arquitetura e Engenharia de Software

### Princípios SOLID Aplicados
* **SRP (Single Responsibility Principle):** A classe `CalculadoraDesconto` possui a única responsabilidade de orquestrar a aplicação das regras de cálculo, enquanto a entidade `Produto` gerencia os dados do item.
* **OCP (Open/Closed Principle):** A introdução de novos tipos de cupons (ex: Frete Grátis) não exige a modificação do código existente, bastando criar uma nova implementação da interface `RegraDesconto`.
* **DIP (Dependency Inversion Principle):** O serviço de checkout depende da abstração (`RegraDesconto`) e não de implementações concretas.

### Padrão de Projeto Utilizado
* **Strategy (Comportamental):** Utilizado para alternar dinamicamente o algoritmo de cálculo de desconto (Fixo ou Percentual) baseado no tipo de cupom fornecido em tempo de execução.
* **Simple Factory:** Utilizado na classe `DescontoFactory` para centralizar e isolar a lógica de criação das estratégias concretas, eliminando o acoplamento de instanciação nas camadas de execução.

---

## Modelagem da Solução (Diagrama de Classes)

## 4. Modelagem da Solução (Diagrama de Classes)

```mermaid

classDiagram
    %% Estrutura de Descontos (Strategy)
    class RegraDesconto {
        <<interface>>
        +calcularDesconto(valorTotal: double, fatorDesconto: double): double
    }
    class DescontoFixo {
        +calcularDesconto(valorTotal: double, fatorDesconto: double): double
    }
    class DescontoPercentual {
        +calcularDesconto(valorTotal: double, fatorDesconto: double): double
    }
    class DescontoFactory {
        <<static>>
        +criarRegra(tipo: TipoDesconto): RegraDesconto
    }
    class CalculadoraDesconto {
        -regraDesconto: RegraDesconto
        +aplicar(valorTotal: double, fatorDesconto: double): double
        +aplicarNoProduto(produto: Produto, fatorDesconto: double): double
    }

    %% Estrutura de Pagamentos (Strategy)
    class FormaPagamento {
        <<interface>>
        +aplicarPagamento(valorComDesconto: double): double
    }
    class PagamentoPadrao {
        +aplicarPagamento(valorComDesconto: double): double
    }
    class PagamentoCredito {
        -parcelas: int
        -JUROS: double
        +aplicarPagamento(valorComDesconto: double): double
    }
    class PagamentoFactory {
        <<static>>
        +criarPagamento(tipo: String, parcelas: int): FormaPagamento
    }

    %% Relações
    RegraDesconto <|.. DescontoFixo
    RegraDesconto <|.. DescontoPercentual
    FormaPagamento <|.. PagamentoPadrao
    FormaPagamento <|.. PagamentoCredito
    CalculadoraDesconto --> RegraDesconto

```
## Demonstração de Funcionamento (Logs da Aplicação)

Como a solução desenvolvida é um componente de back-end focado em regras de negócio acopláveis (Microsserviço de Cupons), o seu comportamento, regras de validação e execução prática são demonstrados por meio da suíte de testes automatizados. 

Ao executar o comando `mvn test`, a aplicação valida os seguintes cenários de negócio em tempo de execução:

1. **Cenário 1 (`deveAplicarDescontoFixoCorretamente`):** Valida se um cupom de valor fixo (ex: R$ 20,00) reduz corretamente o saldo total de uma compra de R$ 100,00, retornando o valor esperado de R$ 80,00.
2. **Cenário 2 (`deveGarantirQueDescontoFixoNaoDeixeValorNegativo`):** Regra de segurança que garante que se um cupom de R$ 50,00 for aplicado em uma compra de R$ 10,00, o sistema barra valores negativos e fixa o total do carrinho em R$ 0,00.
3. **Cenário 3 (`deveSalvarERemoverProdutoDoCatalogo`):** Persistência no Catálogo, modifica o catalogo.
4. **Cenário 4 (`deveCalcularJurosDeCreditoCorretamente`):** Calcula o Juros na forma de pagamento credito (ex: 6%), Valor final com 6% de juros: R$ 106.0
5. **Cenário 5 (`deveAplicarDescontoPercentualCorretamente`):** Valida a estratégia de porcentagem (ex: 10%), aplicando-a sobre uma compra de R$ 200,00 e garantindo o retorno correto de R$ 180,00.

### Log de saida do console, execução interativa (Classe JanelaPrincipal):

```text

         
[LOG] Carregando catálogo do JSON...
[LOG] Carregando catálogo do JSON...
[LOG] Salvando catálogo... Total de itens: 1
[LOG] Carregando catálogo do JSON...
[LOG] Produto salvo: Iphone 13 usado
[LOG] Carregando catálogo do JSON...
[LOG] Salvando catálogo... Total de itens: 0
[LOG] Carregando catálogo do JSON...
[LOG] Produto removido: Iphone 13 usado
[LOG] Carregando catálogo do JSON...
[LOG] Salvando catálogo... Total de itens: 1
[LOG] Carregando catálogo do JSON...
[LOG] Produto salvo: iphone 13
[INFO] [CalculadoraDesconto] Processing cupom para o produto: 'iphone 13'
[INFO] [CalculadoraDesconto] Iniciando processo de validação de cupom.
[INFO] [CalculadoraDesconto] Valor original do carrinho: R$ 2580,00
[INFO] [CalculadoraDesconto] Estratégia 'DescontoFixo' aplicada com sucesso.
[INFO] [CalculadoraDesconto] Valor final com desconto: R$ 2460,00
[INFO] [CalculadoraDesconto] Processo finalizado.

[LOG] Calculando: iphone 13 | Pagamento: DINHEIRO
[LOG] Total Final: R$ 2460,00
[LOG] Carregando catálogo do JSON...
[LOG] Salvando catálogo... Total de itens: 2
[LOG] Carregando catálogo do JSON...
[LOG] Produto salvo: fone de ouvido sem fio
[INFO] [CalculadoraDesconto] Processing cupom para o produto: 'fone de ouvido sem fio'
[INFO] [CalculadoraDesconto] Iniciando processo de validação de cupom.
[INFO] [CalculadoraDesconto] Valor original do carrinho: R$ 85,00
[INFO] [CalculadoraDesconto] Estratégia 'DescontoPercentual' aplicada com sucesso.
[INFO] [CalculadoraDesconto] Valor final com desconto: R$ 80,75
[INFO] [CalculadoraDesconto] Processo finalizado.

[LOG] Calculando: fone de ouvido sem fio | Pagamento: CREDITO
[LOG] Total Final: R$ 83,98

```
### Logs de Saida de Testes Automatizados (mvn test):

```text

=== Cenário 1: Cupom Fixo em Produto Real ===
[INFO] [CalculadoraDesconto] Processing cupom para o produto: 'iPhone 15 Pro Max'
[INFO] [CalculadoraDesconto] Iniciando processo de validação de cupom.
[INFO] [CalculadoraDesconto] Valor original do carrinho: R$ 7500,00
[INFO] [CalculadoraDesconto] Estratégia 'DescontoFixo' aplicada com sucesso.
[INFO] [CalculadoraDesconto] Valor final com desconto: R$ 7000,00
[INFO] [CalculadoraDesconto] Processo finalizado.


=== Cenário 2: Desconto Fixo Simples ===
[INFO] [CalculadoraDesconto] Iniciando processo de validação de cupom.
[INFO] [CalculadoraDesconto] Valor original do carrinho: R$ 100,00
[INFO] [CalculadoraDesconto] Estratégia 'DescontoFixo' aplicada com sucesso.
[INFO] [CalculadoraDesconto] Valor final com desconto: R$ 80,00
[INFO] [CalculadoraDesconto] Processo finalizado.


=== Cenário 3: Persistência no Catálogo (JSON) ===
[LOG] Carregando catálogo do JSON...
[LOG] Salvando catálogo... Total de itens: 1
[LOG] Carregando catálogo do JSON...
[LOG] Salvando catálogo... Total de itens: 0
[LOG] Carregando catálogo do JSON...
[LOG] Teste de persistência finalizado com sucesso.

=== Cenário 4: Cálculo de Juros no Crédito ===
[LOG] Valor final com 6% de juros: R$ 106.0

=== Cenário 5: Desconto Percentual Simples ===
[INFO] [CalculadoraDesconto] Iniciando processo de validação de cupom.
[INFO] [CalculadoraDesconto] Valor original do carrinho: R$ 200,00
[INFO] [CalculadoraDesconto] Estratégia 'DescontoPercentual' aplicada com sucesso.
[INFO] [CalculadoraDesconto] Valor final com desconto: R$ 180,00
[INFO] [CalculadoraDesconto] Processo finalizado.

[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.352 s -- in br.com.ecommerce.cupom.CalculadoraDescontoTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------

```


