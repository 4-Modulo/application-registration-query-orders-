# 🛠 Projeto: Aplicação de Cadastro e Consulta de Pedidos

## 📌 Objetivo
Criar uma API serverless para gerenciar pedidos de uma loja, permitindo:
1. Criar um pedido (endpoint de cadastro).
2. Consultar pedidos (endpoint de listagem).
3. Obter detalhes de um pedido específico.
4. Atualizar o status do pedido.
5. Deletar um pedido.

A arquitetura será serverless, podendo utilizar AWS Lambda, Azure Functions ou Google Run Functions, persistindo os dados em um banco de sua escolha.

---

## 📌 Estrutura dos Dados (Campos do Pedido)
Cada pedido pode ter os seguintes campos:

| Campo            | Tipo            | Descrição                                  |
|-----------------|-----------------|--------------------------------------------|
| id              | String (UUID)    | Identificador único do pedido             |
| cliente         | String           | Nome do cliente que fez o pedido         |
| email           | String           | Email do cliente                          |
| itens          | Array de objetos | Lista dos produtos do pedido             |
| total           | Número (float)   | Valor total do pedido                     |
| status          | String (enum)    | Status do pedido (PENDENTE, PROCESSANDO, ENVIADO, CANCELADO) |
| data_criacao    | Timestamp        | Data e hora do pedido                     |
| data_atualizacao | Timestamp       | Última modificação do pedido              |

### Exemplo JSON de um pedido:
```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "cliente": "João Silva",
  "email": "joao@email.com",
  "itens": [
    { "produto": "Café Expresso", "quantidade": 2, "preco": 5.00 },
    { "produto": "Pão de Queijo", "quantidade": 1, "preco": 3.50 }
  ],
  "total": 13.50,
  "status": "PENDENTE",
  "data_criacao": "2025-02-23T12:00:00Z",
  "data_atualizacao": "2025-02-23T12:00:00Z"
}
```

---

## 🔗 Endpoints da API

### 1️⃣ Criar Pedido
- **Rota:** `POST /pedidos`
- **Descrição:** Cadastra um novo pedido.
- **Entrada (body JSON):**
```json
{
  "cliente": "João Silva",
  "email": "joao@email.com",
  "itens": [
    { "produto": "Café Expresso", "quantidade": 2, "preco": 5.00 }
  ]
}
```
- **Resposta (201 Created):**
```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "status": "PENDENTE",
  "total": 10.00,
  "data_criacao": "2025-02-23T12:00:00Z"
}
```

---

### 2️⃣ Listar Todos os Pedidos
- **Rota:** `GET /pedidos`
- **Descrição:** Retorna todos os pedidos cadastrados.
- **Resposta (200 OK):**
```json
[
  {
    "id": "123e4567-e89b-12d3-a456-426614174000",
    "cliente": "João Silva",
    "total": 10.00,
    "status": "PENDENTE"
  },
  {
    "id": "b4567d89-e12a-34c5-d678-876543210000",
    "cliente": "Maria Souza",
    "total": 22.50,
    "status": "ENVIADO"
  }
]
```

---

### 3️⃣ Obter Detalhes de um Pedido
- **Rota:** `GET /pedidos/{id}`
- **Descrição:** Retorna os detalhes completos de um pedido específico.
- **Exemplo:** `GET /pedidos/123e4567-e89b-12d3-a456-426614174000`
- **Resposta (200 OK):**
```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "cliente": "João Silva",
  "email": "joao@email.com",
  "itens": [
    { "produto": "Café Expresso", "quantidade": 2, "preco": 5.00 }
  ],
  "total": 10.00,
  "status": "PENDENTE",
  "data_criacao": "2025-02-23T12:00:00Z",
  "data_atualizacao": "2025-02-23T12:00:00Z"
}
```

---

## 🚀 Tecnologias Utilizadas
- **Backend:** AWS Lambda / Azure Functions / Google Run Functions
- **Banco de Dados:** A definir (DynamoDB, PostgreSQL, etc.)
- **Linguagem:** A definir (Node.js, Python, Go, etc.)
