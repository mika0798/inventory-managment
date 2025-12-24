<h1>Inventory Management System</h1>
<p>A full-stack inventory management application that enables users to manage stores, products, inventory, and process orders efficiently.</p>

<h2>Table of Contents</h2>
<ul>
  <li><a href="#introduction">Introduction</a></li>
  <li><a href="#gallery">Gallery</a></li>
  <li><a href="#technology-stack">Technology Stack</a></li>
  <li><a href="#project-structure">Project Structure</a></li>
  <li><a href="#database-erd">Database ERD</a></li>
  <li><a href="#pojo-erd">POJO ERD</a></li>
  <li><a href="#api-endpoints">API Endpoints</a></li>
  <li><a href="#getting-started">Getting Started</a></li>
  <li><a href="#features">Features</a></li>
</ul>

<h2 id="introduction">1. Introduction</h2>
<p>This Inventory Management System is designed to streamline inventory operations across multiple stores. It provides a comprehensive solution for:</p>
<ul>
  <li>Managing multiple stores and their inventory</li>
  <li>Handling product catalog and categorization</li>
  <li>Processing customer orders</li>
  <li>Tracking inventory levels and stock availability</li>
  <li>Managing customer reviews</li>
</ul>
<p>The system consists of a <strong>Spring Boot backend</strong> and a <strong>HTML/CSS/JavaScript frontend</strong>, communicating through RESTful APIs.</p>

<h2 id="gallery">2. Gallery</h2>
<div align="left">
<img src="https://github.com/mika0798/inventory-managment/blob/main/media/Add_product.png" width=50% >
<img src="https://github.com/mika0798/inventory-managment/blob/main/media/Add_store.png" width=50% >
<img src="https://github.com/mika0798/inventory-managment/blob/main/media/Edit_inventory.png" width=50% >
<img src="https://github.com/mika0798/inventory-managment/blob/main/media/Manage_inventory.png" width=50% >
<img src="https://github.com/mika0798/inventory-managment/blob/main/media/Manage_products.png" width=50% >
<img src="https://github.com/mika0798/inventory-managment/blob/main/media/Place_order.png" width=50% >
</div>

<h2 id="technology-stack">3. Technology Stack</h2>
<h3>Backend</h3>
<ul>
  <li><strong>Framework</strong>: Spring Boot 3.5.8</li>
  <li><strong>Java Version</strong>: 21</li>
  <li><strong>Build Tool</strong>: Maven</li>
  <li><strong>Database</strong>:
    <ul>
      <li>MySQL for relational data</li>
      <li>MongoDB for document storage (reviews)</li>
    </ul>
  </li>
  <li><strong>Dependencies</strong>:
    <ul>
      <li>Spring Data JPA/Hibernate</li>
      <li>Spring Data MongoDB</li>
      <li>Spring Web</li>
      <li>Spring Validation</li>
      <li>Spring Actuator</li>
      <li>Lombok</li>
      <li>SpringDoc OpenAPI (Swagger)</li>
    </ul>
  </li>
</ul>
<h3>Frontend</h3>
<ul>
  <li>HTML5</li>
  <li>CSS3</li>
  <li>JavaScript (Vanilla)</li>
  <li>Bootstrap 5.3.3</li>
</ul>

<h2 id="project-structure">4. Project Structure</h2>
<pre><code>inventory-managment/
├── back-end/                          # Spring Boot Backend
│   ├── pom.xml                       # Maven configuration
│   └── src/main/
│       ├── java/com/project/code/
│       │   ├── controller/           # REST Controllers
│       │   ├── service/              # Business Logic
│       │   ├── repository/           # Data Access Layer
│       │   ├── domain/               # Entity &amp; DTO classes
│       │   │   ├── entity/
│       │   │   └── dto/
│       │   ├── exception/            # Custom Exceptions
│       │   ├── config/               # Application Configuration
│       │   └── CodeApplication.java  # Main Application Class
│       └── resources/
│           ├── application.properties
│           ├── application-dev.properties
│           └── database/
├── front-end/                        # HTML/CSS/JS Frontend
│   ├── index.html                    # Main Dashboard
│   ├── script.js                     # JavaScript Logic
│   ├── frontend.css                  # Styling
│   ├── add-product.html              # Add Product Page
│   ├── edit-product.html             # Edit Product Page
│   ├── reviews.html                  # Reviews Page
│   └── images/                       # Assets
└── media/                            # Documentation Images
</code></pre>

<h2 id="database-erd">5. Database ERD</h2>
<img src="https://github.com/mika0798/inventory-managment/blob/main/media/DatabaseERD.png" width=60% alt="Database ERD">

<h2 id="pojo-erd">6. POJO ERD</h2>
<img src="https://github.com/mika0798/inventory-managment/blob/main/media/POJOERD.png" width=60% alt="POJO ERD">

<h2 id="api-endpoints">7. API Endpoints</h2>
<img src="https://github.com/mika0798/inventory-managment/blob/main/media/Endpoints.png" width=60% alt="API Endpoints">
