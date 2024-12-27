# JavaFX Banking Application

## **Project Overview**
This is a full-stack **banking application** on desktop, written in **Java** and built using **JavaFX** for UI development and **SQLite** for data persistence.  
The application enables users to interact with various banking functionalities, such as viewing accounts, transferring funds, and managing clients.
The implementation follows the **MVC (Model-View-Controller)** design pattern to ensure clean and maintainable code.

---

## **Features**

### **Client Features:**
- **Secure Login:** Clients can log in securely using unique credentials.
- **Dashboard View:** View account summaries, recent transactions, and quick links to other functionalities.
- **Transactions History** Detailed history of transactions with timestamps and descriptions.
- **Money Transfer:** Seamless transfer of funds between accounts.
- **Account Summary:** View details of all the user's accounts.

### **Admin Features:**
- **Admin Login:** Secure login for administrators to manage the system.
- **Client Management:** Create, view and manage client profiles and account types.
- **Deposit Management:** Add funds to client accounts via an admin-controlled deposit feature
- **View All Clients:** Detailed client list with interactive views.

### **General Highlights:**
- **Modular Design:** Organized project structure with distinct controllers, models, and views.
- **Data Persistence:** SQLite database integration for storing and retrieving user, transaction, and account data.
- **UI Customization:** CSS-based styling for a consistent and visually appealing interface.
- **Error Handling:** Informative messages and validations for better user experience.

---

## **Screenshots**


---

## **Technologies Used**
- **Java**: Core programming language.
- **JavaFX**: UI development framework.
- **FXML**: Layout design for JavaFX applications.
- **CSS**: Styling the UI components.
- - **SQLite**: Lightweight database for data storage.

---

## **Project Structure**

```plaintext
Banking-Application/
├── src/
│   ├── controllers/  # Contains all controller classes for handling UI events
│   ├── models/       # Contains data models and logic
│   ├── views/        # Contains FXML files for UI layouts
│   └── resources/    # CSS, images, and icons
│       ├── fxml/     # FXML layouts
│       ├── styles/   # Stylesheets
│       └── images/   # Image assets
└── database.sql      # SQLite database schema
```

---

## **Database Schema**
The SQLite database consists of the following tables:
- **admins**: Stores admin credentials.
- **clients**: Stores client details.
- **CheckingAccounts**: Stores checking account information for each client.
- **SavingsAccounts**: Stores savings account information for each client.
- **transactions**: Logs all transactions made by clients.

---

## **Future Improvements**
- Implement password encryption for enhanced security.
- Add testing frameworks for unit and integration tests.
- Extend functionalities like bill payments and loan management.
- Implement UI responsiveness for different screen sizes.
