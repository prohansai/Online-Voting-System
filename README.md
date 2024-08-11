# Online-Voting-System
Online Voting System is a Java-based console application that simulates the process of conducting elections digitally. It allows candidates to register, voters to register and cast votes, and administrators to manage the election process, including declaring results and viewing voter turnout. The system is designed with security in mind, ensuring that each voter can only vote once, and Aadhaar numbers are used for unique voter identification.

Features

Candidate Registration:

Candidates can register by providing their name, age, party affiliation, education, and experience.
The system ensures that each candidate is registered only once.

Voter Registration:

Voters can register with their name, age, password, and Aadhaar number.
Only individuals aged 18 and above can register to vote.
The system validates Aadhaar numbers to ensure they are 12 digits long and unique to each voter.

Voting Process:

Registered voters can log in and cast their vote for a candidate of their choice.
The system prevents duplicate voting by tracking which voters have already voted.

Administrator Functions:

An administrator can log in to end the election, declare results, view voter turnout, and reset the system for future elections.
The system displays real-time vote counts and provides a detailed vote summary at the end of the election.

Real-Time Updates:

The system provides real-time updates on the voting progress, showing the current vote count for each candidate.

Voter Education:

The system includes a voter education section that highlights the importance of voting and provides resources for further learning.

System Reset:

Administrators can reset the system, clearing all registered candidates, voters, and votes, making it ready for a new election.

How It Works

Data Storage: The system uses HashSet to store candidates, voters, and Aadhaar numbers. This ensures that all entries are unique, and duplicate registrations are prevented.

User Interaction: The program operates through a menu-driven interface, where users can select options for registration, voting, and administration.

Security: Voter authentication is handled through passwords, and Aadhaar numbers are used to ensure the uniqueness of voter registrations. Additionally, once a voter has cast their vote, they cannot vote again.

Usage

Run the Program:

Start the program, and the main menu will provide options for candidate registration, voter registration, voting, administrator login, real-time updates, voter education, and exiting the system.

Register Candidates:

Select the option to register candidates and provide the required details for each candidate.

Register Voters:

Select the option to register voters, ensuring that the Aadhaar number is correctly formatted and unique.

Cast Votes:

Registered voters can log in and cast their vote for their preferred candidate.

Administrator Actions:

Administrators can end the election, declare the winner, view voter turnout, or reset the system.

View Real-Time Updates:

During the election, real-time updates on the voting process are available.

Exit the Program:

The system can be exited at any time, with the option to resume later.
