# Music Show

![Status](https://img.shields.io/badge/status-conclu%C3%ADdo-green)
![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)

Um projeto acadêmico desenvolvido para a disciplina de Programação Web I. Trata-se de uma aplicação web completa para o cadastro e gerenciamento de eventos musicais, utilizando o ecossistema Spring Boot.

## 📜 Descrição

O **Music Show** é um sistema que permite aos usuários se registrarem e autenticarem para gerenciar eventos. Uma vez logado, o usuário pode visualizar, adicionar, editar e remover eventos da plataforma. O projeto foi construído seguindo a arquitetura MVC (Model-View-Controller) e utiliza Thymeleaf para a renderização das páginas web do lado do servidor.

## ✨ Funcionalidades

-   **Autenticação de Usuários:** Sistema de registro e login seguro utilizando Spring Security.
-   **Gerenciamento de Eventos (CRUD):**
    -   **Create:** Adicionar novos eventos musicais ao sistema.
    -   **Read:** Listar todos os eventos cadastrados.
    -   **Update:** Editar informações de eventos existentes.
    -   **Delete:** Remover eventos da plataforma.
-   **Interface Web Responsiva:** Páginas construídas com HTML, CSS e Thymeleaf para uma experiência de usuário fluida.

## 🛠️ Tecnologias Utilizadas

Este projeto foi construído com as seguintes tecnologias:

-   **Backend:**
    -   Java 17
    -   Spring Boot
    -   Spring Web (para a construção de aplicações web)
    -   Spring Data JPA (para persistência de dados)
    -   Spring Security (para autenticação e autorização)
    -   Hibernate (como implementação da JPA)
    -   Maven (para gerenciamento de dependências)

-   **Frontend:**
    -   Thymeleaf (motor de templates para renderização no servidor)
    -   HTML5
    -   CSS3

-   **Banco de Dados:**
    -   MySQL

## 🚀 Como Executar o Projeto

Para executar este projeto localmente, siga os passos abaixo.

### Pré-requisitos

-   [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior.
-   [Apache Maven](https://maven.apache.org/download.cgi) instalado.
-   Um servidor de banco de dados [MySQL](https://dev.mysql.com/downloads/mysql/) ativo.

### Passos

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/WezleyRenan/ProjetoWeb1.git](https://github.com/WezleyRenan/ProjetoWeb1.git)
    cd ProjetoWeb1/ProjetoWeb
    ```

2.  **Configure o banco de dados:**
    -   Crie um banco de dados no seu servidor MySQL. Por exemplo: `music_show_db`.
    -   Abra o arquivo `src/main/resources/application.properties`.
    -   Altere as propriedades `spring.datasource.url`, `spring.datasource.username` e `spring.datasource.password` com as informações do seu banco de dados local.

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/music_show_db?useSSL=false
    spring.datasource.username=seu_usuario_mysql
    spring.datasource.password=sua_senha_mysql
    spring.jpa.hibernate.ddl-auto=update
    ```

3.  **Execute a aplicação:**
    -   Utilize o Maven para iniciar o servidor Spring Boot.
    ```bash
    mvn spring-boot:run
    ```

4.  **Acesse a aplicação:**
    -   Abra seu navegador e acesse `http://localhost:8080`.
    -   Você será direcionado para a página de registro ou login.

## 👤 Autor

**Wezley Renan Macedo da Silva**

-   **GitHub:** [WezleyRenan](https://github.com/WezleyRenan)
