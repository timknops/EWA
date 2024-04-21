# Solar Sedum

This project was for our course 'Project EWA', in which we were assigned to make an enterprise web application for the company Solar Sedum.

_Due to the hosting being free, it is very slow. It should start in roughly ~30 seconds._

- Backend: https://back-end-hfne.onrender.com
- Frontend: https://front-end-8cvv.onrender.com

## Table of Contents

- [Solar Sedum](#solar-sedum)
  - [Table of Contents](#table-of-contents)
  - [Overview](#overview)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
    - [Backend](#backend)
    - [Frontend](#frontend)
    - [Configuration](#configuration)
    - [Usage](#usage)
    - [Techno (M\&T)](#techno-mt)
      - [Back-end](#back-end)
      - [Front-end](#front-end)
    - [Contacts](#contacts)

## Overview

Solar Sedum is a comprehensive web application developed with Spring Boot and Vue.js, integrating MySQL as the database. The application is tailored for Solar Sedum, offering functionality to monitor various aspects of their organization.

## Prerequisites

Before you begin, ensure you have the following prerequisites installed:

- [Java](https://www.java.com/en/download/)
- [Node.js](https://nodejs.org/)
- [MySQL](https://www.mysql.com/)

## Installation

### Backend

1. Clone the backend repository:

   ```
   git clone https://github.com/your-username/my-awesome-project-backend.git
   cd my-awesome-project-backend

   ```

2. Build and run the Spring Boot application:

   ```
   ./mvnw clean install
   ./mvnw spring-boot:run

   ```

3. The backend should now be running at http://localhost:8083.

### Frontend

1. Clone the frontend repository:

   ```
   git clone https://gitlab.fdmci.hva.nl/se-ewa/2023-2024-1/solar-4.git
   cd solar-4

   ```

2. Install dependencies and build the Vue.js application:

   ```
   npm install
   npm run build

   ```

3. Run the front-end application:
   ```
   npm run serve
   ```

### Configuration

- Backend: Check src/main/resources/ for database and other configurations.
- Frontend: src/appConfig.js to set the correct API base URL.

### Techno (M&T)

All classes and who made what.

#### Back-end

DTO:

- ProjectResourceDTO: Tim
- InventoryDTO: Julian
- InventoryProductDTO: Julian
- ItemDTO: Julian
- OrderRequestDTO: Julian
- OrderUpdateTestDTO: Julian
- DashboardDTO: Hanan

models:

- Email: Noa
- Project: Tim
- Resource: Tim
- Inventory: Julian
- Product: Julian
- Order: Julian
- Item: Julian
- User: Noa
- Warehouse: Wilco
- Team: Nashon

models/compositeKeys:

- ResourceKey: Tim
- InventoryKey: Julian
- ItemKey: Julian

models/wrappers:

- ProjectRequestWrapper: Tim

repositories:

- ProjectRepository: Tim
- ResourceRepository: Tim
- InventoryRepository: Julian
- ItemRepository: Julian
- OrderRepository: Julian

repositories/inMemoryRepositories:

- UserRepositoryMock: Noa
- TeamRepositoryMock: Nashon

repositories/jpaRepositories:

- ResourceRepositoryJpa: Tim
- ProjectRepositoryJpa: Tim
- InventoryRepositoryJpa: Julian
- ItemRepositoryJpa: Julian
- OrderRepositoryJpa: Julian
- ProductRepositoryJpa: Julian
- ProductRepositoryJpa: Julian
- UserRepositoryJpa: Noa
- WarehouseRepositoryJpa: Wilco
- DashboardRepositoryJpa: Hanan
- TeamRepositoryJpa: Nashon

rest:

- AuthenticationController: Noa
- EmailController: Noa
- ProjectController: Tim
- InventoryController: Julian
- OrderController: Julian
- ProductController: Julian
- UserController: Noa
- WarehouseController: Wilco
- DashboardController: Hanan & Wilco
- TeamController: Nashon

security:

- JWToken: Noa
- JWTRequestFilter: Noa

service:

- EmailService: Noa
- EmailLoginResetService: Noa


java/nl/solar/app:

- BackEndApplication: Tim, Julian, Noa
- WebConfig: Julian, Noa, Nashon

#### Front-end

front-end/src/...

components:

- ProjectsOverview: Tim
- ProjectSpecific: Tim
- ProductOverview: Julian
- InventoryOverview: Julian
- LoginPage: Noa
- LoginResetComponent: Noa
- OrderOverview: Julian
- UserOverview: Noa, Nashon
- WarehouseOverview: Wilco
- Dashboard: Hanan
- TeamOverview: Nashon

modal:

- ModalComponent: Julian
- modal/project: Tim
- modal/inventory: Julian
- modal/order: Julian
- modal/product: Julian
- modal/warehouse: Wilco
- modal/user: Noa
- modal/team: Nashon

table:

- TableButtons: Tim
- TableComponent: Tim
- TableFooter: Tim
- TableHeaderRow: Tim
- TableSortingIcons: Tim

utils:

- ErrorMessage: Tim
- SpinnerComponent: Julian
- ToastComponent: Julian
- WarehouseHeaderDisplay: Julian

front-end/service:

- emailAdaptor: Noa
- FetchInterceptor: Noa
- projectAdaptor: Tim
- orderAdaptor: Julian
- productAdaptor: Julian
- inventoryAdaptor: Julian
- SessionSbService: Noa
- userAdaptor: Noa
- warehouseAdaptor: Wilco
- DashboardAdaptor: Hanan
- teamAdaptor: Nashon

front-end/models:

- project: Tim
- warehouse: Wilco
- userLogin: Noa
- team: Nashon

router:

- index: Noa

### Contacts

- Tim Knops
- Hanan Ouardi
- Nashon Woldai
- Julian Kruithof
- Wilco van de Pol
- Noa de Greef
