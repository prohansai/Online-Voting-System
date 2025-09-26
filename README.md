# Online-Voting-System 🗳️

Online Voting System is a Java-based console application that simulates the process of conducting elections digitally. It allows candidates to register, voters to register and cast votes, and administrators to manage the election process, including declaring results and viewing voter turnout. The system is designed with security in mind, ensuring that each voter can only vote once, and Aadhaar numbers are used for unique voter identification.

---

## ✨ Features

* **Candidate Registration:** Candidates can register by providing their name, age, party affiliation, education, and experience. The system ensures that each candidate is registered only once.

* **Voter Registration:** Voters can register with their name, age, password, and Aadhaar number. Only individuals aged 18 and above can register. The system validates Aadhaar numbers to ensure they are 12 digits long and unique.

* **Voting Process:** Registered voters can log in and cast their vote. The system prevents duplicate voting by tracking which voters have already cast their ballot.

* **Administrator Functions:** An administrator can log in to end the election, declare results, view voter turnout, and reset the system for future elections.

* **Real-Time Updates:** The system provides real-time updates on the voting progress, showing the current vote count for each candidate.

* **Voter Education:** Includes a section that highlights the importance of voting and provides resources for further learning.

* **System Reset:** Administrators can clear all registered candidates, voters, and votes, making the system ready for a new election.

---

## 🛠️ Prerequisites

Before you begin, ensure you have the following installed on your system:
* **Java Development Kit (JDK):** Version 8 or higher. You can verify your installation by running `java -version` in your terminal.

---

## ⚙️ Installation and Running

To get a local copy up and running, follow these simple steps.

1.  **Clone the repository:**
    ```sh
    git clone [https://github.com/prohansai/Online-Voting-System.git](https://github.com/prohansai/Online-Voting-System.git)
    ```
2.  **Navigate to the project directory:**
    ```sh
    cd Online-Voting-System
    ```
3.  **Compile the Java code:**
    ```sh
    javac voting.java
    ```
4.  **Run the application:**
    ```sh
    java voting
    ```

---

## 🚀 Usage

1.  **Run the Program:** Start the program, and the main menu will provide options for candidate registration, voter registration, voting, administrator login, and more.

2.  **Register Candidates:** Select the option to register candidates and provide the required details.

3.  **Register Voters:** Select the option to register voters, ensuring the Aadhaar number is correctly formatted and unique.

4.  **Cast Votes:** Registered voters can log in and cast their vote for their preferred candidate.

5.  **Administrator Actions:** Administrators can end the election, declare the winner, view voter turnout, or reset the system.

6.  **View Real-Time Updates:** During the election, check the real-time updates on the voting progress.

7.  **Exit the Program:** The system can be exited at any time, with the option to resume later.

---

## ⚙️ How It Works

* **Data Storage:** The system uses `HashSet` to store candidates, voters, and Aadhaar numbers. This ensures that all entries are unique and prevents duplicate registrations.

* **User Interaction:** The program operates through a menu-driven interface where users can select options for registration, voting, and administration.

* **Security:** Voter authentication is handled through passwords, and Aadhaar numbers ensure the uniqueness of voter registrations. Additionally, once a voter has cast their vote, they cannot vote again.

---

## 💡 Future Improvements

This project serves as a solid foundation for a digital voting system. Future enhancements could include:

* **Database Integration:** Replace the `HashSet` with a persistent database like MySQL or PostgreSQL to store data permanently.
* **GUI Implementation:** Develop a graphical user interface using JavaFX or Swing to make the application more user-friendly.
* **Web Application:** Convert the project into a full-fledged web application using frameworks like Spring Boot and React/Angular.
* **Enhanced Security:** Implement more robust security features like data encryption and two-factor authentication (2FA).
