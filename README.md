# Bookstore Management Application

## Overview

The Bookstore Management Application allows users to manage customer information, book orders, and book inventory efficiently.

## Features

- Create, read, update, and delete customer information, order information, author information, etc.
- Manage book orders, including creation and retrieval
- Track book inventory and update stock levels

## Getting Started

To run the Bookstore Management Application on your local machine, follow the steps below.

### Prerequisites

- Java Development Kit (JDK)
- Database (PostgreSQL)
- IDE (e.g., Visual Studio Code)

### Installation

1. Clone the repository to your local machine.
2. Import the project into your preferred IDE.
3. Set up the database and update connection details in the code. To utilize the code properly, you should add the correct database name, alter the userName variable to your own username, and alter the password to your own password.
4. Run the application.

### Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

## Usage

After setting up the application, you can simply run it and utilize the ease of command line interface to utilize the functions.

## Transaction Management

To ensure data consistency, the application employs transaction management. When a customer places an order, the application inserts the order into the BookOrderInfo table and updates the Book table within the same transaction. The application also checks if there are enough books in inventory before inserting an order.
