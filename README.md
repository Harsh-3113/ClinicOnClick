# 🏥 ClinicOnClick - Healthcare Management System

A comprehensive healthcare management system built with Spring Boot and modern web technologies, designed to streamline patient care, doctor appointments, and pharmacy services.

## ✨ Features

### 🏠 **Home & Navigation**
- Modern, responsive design with Bootstrap 5
- Interactive navigation with smooth animations
- Hero section with call-to-action buttons
- Feature highlights and statistics

### 👥 **User Management**
- Multi-role user system (Admin, Doctor, Patient, Pharmacist)
- Secure authentication and authorization
- User profile management
- Role-based access control

### 👨‍⚕️ **Doctor Management**
- Doctor profiles with specializations
- Availability scheduling
- Professional credentials and experience
- Appointment management

### 📅 **Appointment System**
- Easy appointment booking
- Real-time availability checking
- Appointment status tracking
- Patient-doctor communication

### 💊 **Pharmacy Services**
- Medicine catalog management
- Prescription requirements
- Stock quantity tracking
- Price management

### 🔒 **Security Features**
- Spring Security integration
- JWT token authentication
- Password encryption
- Role-based permissions

## 🛠️ Technology Stack

### Backend
- **Spring Boot 3.2.0** - Main framework
- **Spring Data JPA** - Data persistence
- **Spring Security** - Authentication & authorization
- **H2 Database** - In-memory database (can be changed to MySQL/PostgreSQL)
- **Maven** - Dependency management

### Frontend
- **Bootstrap 5** - CSS framework
- **Font Awesome** - Icons
- **Vanilla JavaScript** - Interactive functionality
- **Thymeleaf** - Server-side templating

### Development Tools
- **Java 17** - Programming language
- **Maven** - Build tool
- **H2 Console** - Database management

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- Modern web browser

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd ClinicOnClick
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   - Main application: http://localhost:8080
   - H2 Database Console: http://localhost:8080/h2-console
     - JDBC URL: `jdbc:h2:mem:cliniconclick`
     - Username: `sa`
     - Password: `password`

## 🔑 Default Login Credentials

The application comes with pre-configured sample data:

| Role | Username | Password |
|------|----------|----------|
| **Admin** | `admin` | `admin123` |
| **Doctor** | `dr.smith` | `doctor123` |
| **Patient** | `john.doe` | `patient123` |
| **Pharmacy** | `pharmacy.main` | `pharmacy123` |

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/cliniconclick/
│   │   ├── config/          # Configuration classes
│   │   ├── controller/      # REST controllers
│   │   ├── entity/          # JPA entities
│   │   ├── repository/      # Data access layer
│   │   └── service/         # Business logic
│   ├── resources/
│   │   ├── static/          # Static assets (CSS, JS, images)
│   │   ├── templates/       # Thymeleaf templates
│   │   └── application.properties
│   └── webapp/              # Web resources
└── test/                    # Test files
```

## 🎨 Customization

### Styling
- Modify `src/main/resources/static/css/style.css` for custom styles
- Update color variables in CSS root for theme changes
- Customize Bootstrap components as needed

### Functionality
- Add new entities in the `entity` package
- Create corresponding repositories and services
- Implement new controllers for additional features

### Database
- Change database from H2 to MySQL/PostgreSQL in `application.properties`
- Update connection details and dependencies accordingly

## 🔧 Configuration

### Application Properties
Key configuration options in `application.properties`:

```properties
# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:h2:mem:cliniconclick
spring.datasource.username=sa
spring.datasource.password=password

# JPA Configuration
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# Security Configuration
jwt.secret=cliniconclicksecretkey2024
jwt.expiration=86400000
```

## 📱 Responsive Design

The application is fully responsive and works on:
- Desktop computers
- Tablets
- Mobile phones
- All modern web browsers

## 🚀 Deployment

### Local Development
```bash
mvn spring-boot:run
```

### Production Build
```bash
mvn clean package
java -jar target/clinic-on-click-0.0.1-SNAPSHOT.jar
```

### Docker (Optional)
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/clinic-on-click-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
```

## 🧪 Testing

Run tests with:
```bash
mvn test
```

## 📊 Sample Data

The application automatically creates sample data including:
- 6 users (Admin, 2 Doctors, 2 Patients, 1 Pharmacist)
- 2 doctor profiles with specializations
- 1 pharmacy with 3 medicines
- 2 sample appointments

## 🔍 API Endpoints

### Public Endpoints
- `GET /` - Home page
- `GET /about` - About page
- `GET /contact` - Contact page

### Protected Endpoints
- `GET /dashboard` - User dashboard
- `GET /appointments` - Appointment management
- `GET /doctors` - Doctor listings
- `GET /pharmacy` - Pharmacy services

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
3. Add tests if applicable
4. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Support

For support and questions:
- Create an issue in the repository
- Check the documentation
- Review the code comments

## 🎯 Roadmap

Future enhancements planned:
- [ ] Real-time chat system
- [ ] Video consultation integration
- [ ] Payment gateway integration
- [ ] Mobile app development
- [ ] Advanced reporting and analytics
- [ ] Integration with external healthcare systems

---

**Built with ❤️ using Spring Boot and modern web technologies**

*ClinicOnClick - Your trusted healthcare companion*
