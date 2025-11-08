# Microsserviços: Pedidos, Produtos e Pagamentos

- **serviceregistry** (Eureka Server) – porta 8761
- **gateway** (Spring Cloud Gateway) – porta 8080
- **msproduto** – Serviço de produtos (porta aleatória)
- **mspedido** – Serviço de pedidos (porta aleatória)
- **mspagamento** – Serviço de pagamentos (porta aleatória)

### Tecnologias utilizadas
- Spring Boot 3.5.7
- Spring Cloud 2025.0.0
- Java 21
- MySQL
- RabbitMQ
- Eureka (Service Discovery)
- OpenFeign (Comunicação síncrona)

### Bancos de dados
Cada microsserviço possui seu próprio banco de dados MySQL:
- `db_produto` - msproduto
- `db_pedido` - mspedido
- `db_pagamento` - mspagamento

### Fluxo Principal

1. **Criação do Pedido (POST /pedidos)**
   - O serviço de pedidos recebe uma lista de IDs de produtos
   - Realiza chamada síncrona ao msproduto para buscar detalhes dos produtos
   - Valida disponibilidade de estoque
   - Calcula o valor total do pedido
   - Persiste o pedido com status CRIADO
   - Envia mensagem assíncrona via RabbitMQ para o serviço de pagamentos

2. **Processamento do Pagamento**
   - O serviço de pagamentos consome a mensagem da fila `pagamentos.registrar`
   - Cria o pagamento com status CRIADO
   - Gera código aleatório para o pagamento (UUID)
   - Define expiração para 24 horas após criação
   - Atualiza status para CONFIRMADO
   - Realiza chamada síncrona ao mspedido informando confirmação

3. **Atualização do Pedido (Fluxo Extra)**
   - O serviço de pedidos recebe confirmação de pagamento
   - Atualiza status do pedido para CONFIRMADO
   - Realiza chamada síncrona ao msproduto para atualizar estoque

## Configuração RabbitMQ

### Exchange e Filas
- Exchange: `pagamento.exchange` (Direct)
- Fila processar: `pagamentos.registrar` (routing key: `pagamento.processar`)
- Fila confirmado: `pagamentos.confirmado` (routing key: `pagamento.confirmado`)

## Endpoints

### msproduto
- `GET /produtos/{id}` - Buscar produto por ID
- `POST /produtos/ids` - Buscar produtos por lista de IDs
- `PUT /produtos/atualizar-quantidade` - Atualizar quantidade de produtos

### mspedido
- `POST /pedidos` - Criar novo pedido (envia lista de IDs de produtos)
- `PUT /pedidos/atualizar-status` - Atualizar status do pedido

### mspagamento
- `POST /pagamentos/registrar` - Registrar pagamento manualmente

## Requisitos

- JDK 21+
- MySQL 8.0+
- RabbitMQ 3.x
- Maven 3.6+

## Executando o Sistema

### 1. Iniciar MySQL
Certifique-se de que o MySQL está rodando na porta 3306 com usuário `root` e senha `root`.

### 2. Iniciar RabbitMQ
```powershell
# RabbitMQ deve estar rodando em localhost:5672
# Usuário: guest / Senha: guest
```
### 3. Acessar o Eureka Dashboard
Abra o navegador em: `http://localhost:8761`