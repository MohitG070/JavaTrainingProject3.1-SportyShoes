# Sporty Shoes E-commerce Portal

A Full Stack e-commerce application prototype for Sporty Shoes.

## Project Objective

To build an end-to-end e-commerce platform for Sporty Shoes with features such as product management, customer signup/login, order placement along with admin operation privilages.

## Technology

Backend - Java (Spring Boot)
Frontend - HTML + Thymeleaf
Database - MySQL
Other Tools - Maven, Spring Data JPA, Git

## Architecture

- `com.main`: Entry point of the application
- `com.bean`: Entity classes (Admin, Customer, Product, Orders)
- `com.repository`: JPA Repositories (DAO layer)
- `com.service`: Business logic
- `com.controller`: Web Controllers handling routes and views

## Features

### Admin Functionalities
- Secure Admin login
- Change admin password
- Add / Update / Delete products
- View customers & search them
- View purchase reports (by category / by date)
- View all orders

### Customer Functionalities
- Signup and login
- View all available products
- Place orders
- Logout functionality
