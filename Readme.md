Trabalho Prático - UC Engenharia de Software

Definição do Problema Contexto: No cenário de plataformas de e-commerce, a retenção de clientes e o aumento de conversão de vendas dependem diretamente de campanhas de marketing eficientes, como a distribuição de cupons de desconto.
Problema Identificado: O processo atual de validação e aplicação de cupons era acoplado diretamente ao fluxo de checkout, gerando falhas quando regras complexas de negócio (cupons expirados, cupons de primeira compra, ou cumulativos) precisavam ser validadas simultaneamente.

Usuários Envolvidos: Compradores (Clientes finais do e-commerce) e Administradores do Sistema (Gestores de Marketing).

Solução Proposta: Um microsserviço isolado de validação de cupons (Back-end em Java) que centraliza as regras de negócio, garantindo escalabilidade, facilidade de manutenção e aplicando boas práticas de Engenharia de Software.

Levantamento e Análise de Requisitos (Abordagem Ágil) Atores Envolvidos Cliente: Deseja aplicar um cupom para obter desconto no carrinho.
Sistema de Checkout: Consome a API de cupons para processar o valor final da compra.

Backlog de Funcionalidades (User Stories) US01 - Validação de Cupom Ativo: Como Sistema de Checkout, quero validar se um cupom existe e está dentro do prazo de validade, para evitar o uso de descontos expirados.

US02 - Cálculo de Desconto Fixo: Como Cliente, quero que um cupom de valor fixo (ex: R$ 20,00) seja deduzido do meu saldo total.

US03 - Cálculo de Desconto Percentual: Como Cliente, quero que um cupom de valor percentual (ex: 10%) seja aplicado sobre o valor total da compra.

Arquitetura e Engenharia de Software Princípios SOLID Aplicados SRP (Single Responsibility Principle): A classe CalculadoraDesconto possui a única responsabilidade de aplicar as regras de cálculo, enquanto a entidade Cupom apenas armazena os dados.
OCP (Open/Closed Principle): A introdução de novos tipos de cupons (ex: Frete Grátis) não exige a modificação do código existente, bastando criar uma nova implementação da interface RegraDesconto.

DIP (Dependency Inversion Principle): O serviço de checkout depende da abstração (RegraDesconto) e não de implementações concretas.

Padrão de Projeto Utilizado Strategy (Comportamental): Utilizado para alternar dinamicamente o algoritmo de cálculo de desconto (Fixo ou Percentual) baseado no tipo de cupom fornecido em tempo de execução.