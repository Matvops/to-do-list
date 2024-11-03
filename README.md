<h1 align="center">TO DO LIST 📋</h1>


<div align="center">
    <img src="https://img.shields.io/badge/languages-2-green" alt="Languages">
    <img src="https://img.shields.io/badge/Java-99.7%25-orange" alt="Java">
    <img src="https://img.shields.io/badge/made%20by-Matvops-blue" alt="Made by Matvops">
    <img src="https://img.shields.io/badge/last%20commit-03%20Nov%202024-blueviolet" alt="Last Commit">
    <a href="https://www.linkedin.com/in/matheus-cadenassi-799125321/" target="_blank">
        <img src="https://img.shields.io/badge/LinkedIn-Matheus%20Cadenassi-blue" alt="LinkedIn">
    </a>
</div> 

<hr> <br>

<p align="center">
    <img src="https://github.com/user-attachments/assets/7388ef1e-a298-42b2-8e0d-c889e8be5721" style="display: block; margin: auto;">
</p>

<br> <br> <hr>

<h2>Indice 🔗</h2>
<ol>
    <li><a href="#sobre">SOBRE O PROJETO</a></li>
    <li><a href="#tecnologias">TECNOLOGIAS UTILIZADAS</a></li>
    <li><a href="#baixar">COMO BAIXAR O PROJETO</a></li>
    <li><a href="#usar">COMO USAR A APLICAÇÃO</a></li>
    <li><a href="#futuro">FUTURAS IMPLEMENTAÇÕES</a></li>
</ol>

<br> <hr> 

<h2 id="sobre">Sobre o projeto 🚀</h2>

<p>Este projeto é uma aplicação de To-Do List desenvolvida 
como parte do meu portfólio, com o objetivo de demonstrar minhas habilidades, 
usando tecnologias amplamente utilizadas no mercado. A aplicação permite o 
gerenciamento de tarefas, com funcionalidades como listar, adicionar, editar, concluir e 
remover tarefas. O projeto também inclui testes unitários e de integração, garantindo a 
confiabilidade das funcionalidades essenciais.  Para facilitar a interação com a API, 
utilizei o Swagger como documentação da API REST.</p>

<br> <hr> 

<h2 id="tecnologias">Tecnologias 👨‍💻</h2>

<ol>
    <li>
        LINGUAGENS
        <ul>
            <strong>
                <li><a href="https://www.java.com/pt-BR/">Java</a></li>
                <li><a href="https://aws.amazon.com/pt/what-is/sql/#:~:text=A%20Linguagem%20de%20consulta%20estruturada,performance%20do%20banco%20de%20dados.">SQL</a></li>
            </strong>
        </ul>
    </li>
    <br>
    <li>
        FRAMEWORKS E FERRAMENTAS
        <ul>
            <strong>
                <li><a href="https://www.docker.com">Docker</a></li>
                <li><a href="https://www.red-gate.com/products/flyway/community/">Flyway</a></li>
                <li><a href="https://hibernate.org">Hibernate</a></li>
                <li><a href="https://spring.io/projects/spring-data-jpa">JPA</a></li>
                <li><a href="https://junit.org/junit5/">JUnit5</a></li>
                <li><a href="https://mapstruct.org/">MapStruct</a></li>
                   <li><a href="https://maven.apache.org">Maven</a></li>
                <li><a href="https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html">Mockito</a></li>
                <li><a href="https://rest-assured.io/">REST Assured</a></li>
                <li><a href="https://spring.io/projects/spring-boot">Spring Boot</a></li>
                <li><a href="https://swagger.io">Swagger</a></li>
                <li><a href="https://testcontainers.com">Testcontainers</a></li>
            </strong>
        </ul>
    </li>
    <br>
    <li>
        BANCO DE DADOS
        <ul>
            <strong>
                <li><a href="https://www.mysql.com">MySQL</a></li>
            </strong>
        </ul>
    </li>
    <br>
    <li>
        PADRÃO DE PROJETOS
        <ul>
            <strong>
                <li><a href="https://www.ibm.com/br-pt/topics/rest-apis">API RestFul</a></li>
                <li><a href="https://coodesh.com/blog/dicionario/o-que-e-arquitetura-mvc/">MVC</a></li>
            </strong>
        </ul>
    </li>
    <br>
    <li>
        IDE
        <ul>
            <strong>
                <li><a href="https://www.jetbrains.com/pt-br/idea/">Intellij</a></li>
            </strong>
        </ul>
    </li>
</ol>

<br> <hr> 

<h2 id="baixar">Como baixar o projeto 🐳🐈‍</h2>

<p> 🚨 Siga a ordem e complete um item por vez 🚨</p>

<h4>Pré-requisitos</h4>

<p>Instale o <a href="https://www.docker.com/products/docker-desktop">Docker</a></p>

<h4>Clonando o repositório</h4>

<p>No terminal use o seguinte comando:</p>

<pre>
    <code>git clone https://github.com/Matvops/to-do-list.git</code>
</pre>
<p>Isso irá baixar o projeto no seu computador.</p>


<h4>Acesse o diretório do projeto</h4>

<pre>
    <code>cd seu_repositorio</code>
</pre>

<h4>Excecute o Projeto com Docker</h4>

<p>No diretório do projeto execute: </p>

<pre>
    <code>docker-compose up --build</code>
</pre>
<p>Este comando irá iniciar todos os serviços necessários.</p>

<br> <hr> 

<h2 id="usar">Como usar a aplicação ✅</h2>

<br>
<h4>Com os serviços em execução</h4>


<p>Clique <strong><a href="http://localhost:80/swagger-ui/index.html#/">AQUI</a></strong></p>

<br>
<h4>Para executar uma requisição:</h4>

<img src="https://github.com/user-attachments/assets/970a1194-e428-4aba-b4a4-11f030a91f62">
<ol>
    <li>SELECIONE UM MÉTODO</li>
    <li>CLIQUE EM 'Try it out'</li>
    <li>INSIRA UM VALOR VÁLIDO NO CAMPO DO PARÂMETRO</li>
    <li>CLIQUE EM 'Execute'</li>
</ol>


<p>Valores aceitos para day (case-insensitive):</p>
<ul>
    <li>DOMINGO</li>
    <li>SEGUNDA</li>
    <li>TERÇA</li>
    <li>QUARTA</li>
    <li>QUINTA</li>
    <li>SEXTA</li>
    <li>SABADO</li>
</ul>

<p>Valores aceitos para id: 1 - 50</p>

<br>
<h4>Para <strong style="color: LightGreen">POST</strong> ou <strong style="color: Orange">PUT</strong></h4>

<img src="https://github.com/user-attachments/assets/12d87da5-26e0-44e0-8711-0f973873bc97">
<p>Basta alterar os campos NAME - COMPLETED - DAY - PRIORITY</p>

<p>Valores aceitos para DAY (case-insensitive):</p>
<ul>
    <li>DOMINGO</li>
    <li>SEGUNDA</li>
    <li>TERÇA</li>
    <li>QUARTA</li>
    <li>QUINTA</li>
    <li>SEXTA</li>
    <li>SABADO</li>
</ul>

<p>Valores aceitos para PRIORITY (case-insensitive):</p>
<ul>
    <li>BAIXA</li>
    <li>MODERADA</li>
    <li>ALTA</li>
</ul>


<p>Valores aceitos para COMPLETED (case-insensitive):</p>
<ul>
    <li>FALSE</li>
    <li>TRUE</li>
</ul>

<br> <hr>

<h2 id="futuro">FUTURAS IMPLEMENTAÇÕES ⏳</h2>

<ol>
    <li>
        <h4>FRONT-END COM ANGULAR</h4>
        <ul>
            <li>Planejo desenvolver um front-end para a aplicação utilizando TypeScript  e Angular.</li>
        </ul>
    </li>
    <li>
        <h4>NOVAS FUNCIONALIDADES</h4>
        <ul>
            <li>
                Criar uma funcionalidade que permita aos usuários integrar suas tarefas com um calendário e 
                visualizar tarefas agendadas.
            </li>
            <li>
                Implementar um sistema de notificações que informe os usuários sobre prazos de tarefas.
            </li>
        </ul>
    </li>
</ol>


<br> <hr>
<h3 align="center">Feito com ❤️ por Matvops
👋 <a href="https://www.linkedin.com/in/matheus-cadenassi-799125321/">entre em contato!</a> </h3>
