# API SGP

API REST desenvolvida para gerenciamento de dados do sistema **SGP**.  
O projeto tem como objetivo disponibilizar endpoints para manipulação e consulta de informações através de uma arquitetura de serviços.

## 📌 Sobre o projeto

A **API SGP** foi criada para fornecer uma interface de comunicação entre aplicações clientes e o sistema de gerenciamento SGP, permitindo:

- Criação de registros
- Consulta de dados
- Atualização de informações
- Exclusão de registros

A API segue os princípios de **REST** e retorna dados estruturados em **JSON**.

---

## 🚀 Tecnologias utilizadas

Este projeto utiliza as seguintes tecnologias:

- Node.js
- Express
- JavaScript / TypeScript
- Banco de dados (ex: MySQL / PostgreSQL / MongoDB)
- Docker (opcional)
- Git e GitHub

---

## 📂 Estrutura do projeto

```bash
api-sgp
│
├── src
│   ├── controllers   # Controladores das rotas
│   ├── models        # Modelos de dados
│   ├── routes        # Definição das rotas da API
│   ├── services      # Regras de negócio
│   └── app.js        # Configuração da aplicação
│
├── package.json
├── README.md
└── .env.example
