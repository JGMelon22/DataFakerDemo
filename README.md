# Data Faker Demo Deploy
Deploying a Data Faker Spring Application at Render

<h2>Tools</h2>
- H2 (in memory database) <br /> 
- Insomnia (api debug) <br /> 
- Swagger (api debug) <br /> 
- Java 17 <br /> 
- Docker (To deploy a Dockerfile at Render) <br /><br />

<div style="display: inline_block" class="flex-container"><br>
        <img align="center" alt="Java" height="4%" width="4%" <img
                src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" />
        <img align="center" alt="Spring" height="4%" width="4%" <img
                src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg" />
        <img align="center" alt="Docker" height="4%" width="4%" <img
                src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg" />
	<img align="center" alt="Linux" height="4%" width="4%" <img
                src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/linux/linux-original.svg" />
</div>

<h2>End points</h2>
<ul>
  <li>Get All People: <code>/api/person</code></li>
  <li>Seed Data (100 random people): <code>/api/seed-data</code></li>
  <li>Add Person: <code>/api/person</code>
    <ul>
      <li>Expected body: <code>{ "firstName": "string", "lastName": "string", "address": "string" }</code></li>
    </ul>
  </li>
  <li>Clear Database (delete all people from H2): <code>/api/person</code></li>
</ul>  

<h2> Image build 🚧</h2>
- <code>docker build -t my-spring-app</code><br />
- <code>docker run my-spring-app</code>

<h2>Class Diagram</h2>
<img src="https://github.com/JGMelon22/CarteiraClientes/assets/73988556/d0e6c671-1fd6-4811-a261-e177ef9e3abe" width="600" height="300"/> 