## 💻 Sobre o projeto

Voll.med é uma clínica médica fictícia que precisa de um aplicativo para gestão de consultas. O aplicativo deve possuir funcionalidades que permitam o cadastro de médicos e de pacientes, e também o agendamento e cancelamento de consultas.

Enquanto um time de desenvolvimento será responsável pelo aplicativo mobile, o nosso será responsável pelo desenvolvimento da API Rest desse projeto.

---

## ⚙️ Funcionalidades

- [x] CRUD de médicos;
- [x] CRUD de pacientes;
- [x] Agendamento de consultas(em breve);
- [x] Cancelamento de consultas(em breve);

---

## :1st_place_medal: Envolvimento do projeto
 - [x] Isolagem dos códigos nas regras de negócio na aplicação;
 - [x] Implementação dos princípios SOLID;
 - [x] Documentação da API que segue o padrão OpenAPI;
 - [x] Testagem automática testes na aplicação com Spring Boot 3;
 - [x] Realização de build da aplicação com Spring Boot 3;
 - [x] Utilização de  variáveis de ambiente;
 - [x] Atuação da aplicação para o deploy;
--- 

## 🎨 Layout

O layout da aplicação mobile está disponível neste link: <a href="https://www.figma.com/file/N4CgpJqsg7gjbKuDmra3EV/Voll.med">Figma</a>

---

## 📄 Documentação

A documentação das funcionalidades da aplicação pode ser acessada neste link: <a href="https://trello.com/b/O0lGCsKb/api-voll-med">Trello</a>

---

## 🛠 Tecnologias

As seguintes tecnologias foram utilizadas no desenvolvimento da API Rest do projeto:

- **[Java 17](https://www.oracle.com/java)**
- **[Spring Boot 3](https://spring.io/projects/spring-boot)**
- **[Maven](https://maven.apache.org)**
- **[MySQL](https://www.mysql.com)**
- **[Hibernate](https://hibernate.org)**
- **[Flyway](https://flywaydb.org)**
- **[Lombok](https://projectlombok.org)**

---

## 📝 Licença

Estrutura do projeto desenhada pela [Alura](https://www.alura.com.br) e construído por [Fernando Furtado](https://github.com/Fernando-EngComputacao/) em curso de Spring Boot.

---
## :bookmark: Certificação
° Certificado do Curso: **[Spring Boot 3: documente, teste e faça deploy](https://cursos.alura.com.br/certificate/6f077e71-f864-4681-9c54-47d6a02eba1a)**

---

## :arrows_clockwise: Adicionais:
#### Deploy
###### - Código para rodar em produção, no servidor:

    java -Dspring.profiles.active=<profile> -D<LocationNameVariable>=<value> -D<userNameVariable>=<value> -D<namePasswordVariable>=<value> -jar <file-name.jar>

Exemplo: 

    java -Dspring.profiles.active=prod -DDATASOURCE_URL=jdbc:mysql://localhost:vollmed_api -DDATASOURCE_USERNAME=root -DDATASOURCE_PASSWROD=root -jar code-0.0.1-SNAPSHOT.jar


#### Jar

###### - Para gerar o Jar no Maven, basta ir até **Maven>Lifecycle>package**. O Jar gerado pode ser encontrado na pasta **Target**>
___