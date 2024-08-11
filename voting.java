import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class OnlineVotingSystem {

    static HashSet<Candidate> candidates = new HashSet<>();
    static HashSet<Voter> voters = new HashSet<>();
    static HashSet<String> aadhaarlist=new HashSet<>();
    static boolean electionsEnded = false;
    static int totalRegisteredVoters = 0;
    static int totalVotesCast = 0;
    static HashSet<Voter> voterHasVoted = new HashSet<>();

    static class Candidate {
        String name;
        int age;
        String party;
        String education;
        String experience;
        int votes;

        Candidate(String name, int age, String party, String education, String experience) {
            this.name = name;
            this.age = age;
            this.party = party;
            this.education = education;
            this.experience = experience;
            this.votes = 0;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Candidate candidate = (Candidate) obj;
            return name.equals(candidate.name);
        }
    }

    static class Voter {
        String name;
        int age;
        String password;
        String aadhaar;

        Voter(String name, int age, String password, String aadhaar) {
            this.name = name;
            this.age = age;
            this.password = password;
            this.aadhaar = aadhaar;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Voter voter = (Voter) obj;
            return name.equals(voter.name);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (!electionsEnded) {
            System.out.println("\n** ONLINE VOTING SYSTEM **");
            System.out.println("1. CANDIDATE REGISTER");
            System.out.println("2. VOTER REGISTER");
            System.out.println("3. CAST VOTE");
            System.out.println("4. ADMINISTRATOR LOGIN");
            System.out.println("5. REAL-TIME UPDATES");
            System.out.println("6. VOTER EDUCATION");
            System.out.println("7. EXIT");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    candidateRegister(scanner);
                    break;

                case 2:
                    voterRegister(scanner);
                    break;

                case 3:
                    if (!voters.isEmpty()) {
                        castVote(scanner);
                    } else {
                        System.out.println("NO VOTERS REGISTERED. FINISH VOTER REGISTRATION FIRST.");
                    }
                    break;

                case 4:
                    administratorLogin(scanner);
                    break;

                case 5:
                    if (!candidates.isEmpty() && !voters.isEmpty()) {
                        realTimeUpdates();
                    } else {
                        System.out.println("FINISH CANDIDATE AND VOTER REGISTRATION FIRST.");
                    }
                    break;

                case 6:
                    voterEducation();
                    break;

                case 7:
                    electionsEnded = true;
                    break;

                default:
                    System.out.println("INVALID CHOICE. PLEASE TRY AGAIN.");
            }
        }

        scanner.close();
    }

    private static void candidateRegister(Scanner scanner) {
        System.out.println("\n** CANDIDATE REGISTRATION **");
        System.out.print("Enter the number of candidates to register: ");
        int numCandidates = scanner.nextInt();

        for (int i = 0; i < numCandidates; i++) {
            System.out.println("\nCANDIDATE " + (i + 1) + ":");
            System.out.print("Enter candidate name: ");
            String name = scanner.next();
            System.out.print("Enter party name: ");
            String party = scanner.next();
            System.out.print("Enter candidate age: ");
            int age = scanner.nextInt();
            System.out.print("Enter candidate experience: ");
            String experience = scanner.next();
            System.out.print("Enter candidate education: ");
            String education = scanner.next();

            Candidate candidate = new Candidate(name, age, party, education, experience);

            if (candidates.add(candidate)) {
                System.out.println("CANDIDATE REGISTERED SUCCESSFULLY.");
            } else {
                System.out.println("CANDIDATE ALREADY REGISTERED.");
            }
        }
    }

    private static void voterRegister(Scanner scanner) {
        System.out.println("\n** VOTER REGISTRATION **");
        System.out.print("Enter the number of voters to register: ");
        int numVoters = scanner.nextInt();

        for (int i = 0; i < numVoters; i++) {
            System.out.println("\nVOTER " + (i + 1) + ":");
            System.out.print("Enter voter name: ");
            String name = scanner.next();
            System.out.print("Enter voter age: ");
            int age = scanner.nextInt();

            if (age < 18) {
                System.out.println("NOT ELIGIBLE TO VOTE.");
                continue;
            }

            System.out.print("Create a password: ");
            String password = scanner.next();
            System.out.print("Enter Aadhaar card number: ");
            String aadhaar = scanner.next();
            if(aadhaarlist.contains(aadhaar)){
                System.out.println("AADHAAR ALREADY USED. VOTER NOT REGISTERED.");
            }
            else{
            Voter voter = new Voter(name, age, password, aadhaar);

            if (voters.add(voter) && validateAadhaar(aadhaar)) {
                totalRegisteredVoters++;
                aadhaarlist.add(aadhaar);
                System.out.println("VOTER REGISTERED SUCCESSFULLY.");
            } else if (!validateAadhaar(aadhaar)) {
                System.out.println("WRONG AADHAAR FORMAT. PLEASE ENTER A 12-DIGIT AADHAAR NUMBER.");
                }
            }
        }
    }

    private static boolean validateAadhaar(String aadhaar) {
        return aadhaar.matches("\\d{12}");
    }

    private static void castVote(Scanner scanner) {
        if(candidates.isEmpty()){
            System.out.println("FINISH CANDIDATE REGISTRATION FIRST.");
        }
        else{
        Voter currentVoter = getCurrentVoter(scanner);
        if(currentVoter==null) {
            System.out.println("VOTER NOT REGISTERED");
        }
        else{
        System.out.println("\n** CAST YOUR VOTE **");
        System.out.println("AVAILABLE CANDIDATES:");

        for (Candidate candidate : candidates) {
            System.out.println(candidate.name + " - " + candidate.party);
        }

        System.out.print("Enter the name of the candidate you want to vote for: ");
        String candidateName = scanner.next();


        if (currentVoter != null && !currentVoterHasVoted(currentVoter)) {
            Candidate selectedCandidate = findCandidate(candidateName);

            if (selectedCandidate != null) {
                selectedCandidate.votes++;
                System.out.println("VOTE CAST SUCCESSFULLY FOR " + selectedCandidate.name);
                totalVotesCast++;
                voterHasVoted.add(currentVoter);
            } else {
                System.out.println("INVALID CANDIDATE NAME. VOTE NOT CAST.");
            }
        }else if (currentVoter != null) {
            System.out.println("VOTING ALREADY DONE. CANNOT VOTE AGAIN.");
        }
    }
    }
    }
    
    private static boolean authenticateVoter(String name, String password) {
        Voter voter = new Voter(name, 0, password, "");

        
        return voters.contains(voter) && voters.stream().anyMatch(v -> v.equals(voter) && v.password.equals(password));
    }
    
    private static Voter getCurrentVoter(Scanner scanner) {
        System.out.print("Enter voter name: ");
        String name = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        if (authenticateVoter(name, password)) {
            return new Voter(name, 0, password, "");
        } else {
            System.out.println("INCORRECT PASSWORD. TRY AGAIN.");
            return null;
        }
    }

    private static boolean currentVoterHasVoted(Voter voter) {
        
        return voter != null && voters.contains(voter) && voterHasVoted.contains(voter);
    }

    private static Candidate findCandidate(String candidateName) {
        for (Candidate candidate : candidates) {
            if (candidate.name.equals(candidateName)) {
                return candidate;
            }
        }
        return null;
    }

    private static void realTimeUpdates() {
        System.out.println("\n** REAL-TIME UPDATES **");
        System.out.println("CURRENT VOTE COUNTS:");

        for (Candidate candidate : candidates) {
            System.out.println(candidate.name + " - " + candidate.votes + " VOTES");
        }
    }

    private static void administratorLogin(Scanner scanner) {
        System.out.println("\n** ADMINISTRATOR LOGIN **");
        System.out.print("Enter administrator password: ");
        String adminPassword = scanner.next();

        if ("password".equals(adminPassword)) {
            System.out.println("LOGIN SUCCESSFUL. ADMINISTRATOR OPTIONS:");

            System.out.println("1. END ELECTIONS AND DECLARE RESULTS");
            System.out.println("2. SHOW VOTER TURNOUT");
            System.out.println("3. RESET SYSTEM");
            System.out.print("Enter your choice: ");
            int adminChoice = scanner.nextInt();

            switch (adminChoice) {
                case 1:
                    if (!candidates.isEmpty() && !voters.isEmpty()) {
                        endElectionsAndDeclareResults();
                    } else {
                        System.out.println("FINISH CANDIDATE AND VOTER REGISTRATION FIRST.");
                    }
                    break;

                case 2:
                    showVoterTurnout();
                    break;

                case 3:
                    resetSystem();
                    break;

                default:
                    System.out.println("INVALID CHOICE.");
            }
        } else {
            System.out.println("WRONG PASSWORD. TRY AGAIN.");
        }
    }

    private static void showVoterTurnout() {
        System.out.println("\n** VOTER TURNOUT **");
        if (totalRegisteredVoters == 0) {
            System.out.println("NO REGISTERED VOTERS YET.");
        } else {
            double voterTurnoutPercentage = ((double) totalVotesCast / totalRegisteredVoters) * 100;
            System.out.println("TOTAL REGISTERED VOTERS: " + totalRegisteredVoters);
            System.out.println("TOTAL VOTES CAST: " + totalVotesCast);
            System.out.printf("VOTER TURNOUT: %.2f%%\n", voterTurnoutPercentage);
        }
    }

    private static void endElectionsAndDeclareResults() {
    System.out.println("\n** ELECTIONS ENDED **");
    List<Candidate> winners = findWinners();

    if (!winners.isEmpty()) {
        if (winners.size() == 1) {
            Candidate winner = winners.get(0);
            System.out.println("WINNER: " + winner.name + " FROM " + winner.party + " WITH " + winner.votes + " VOTES.");
        } else {
            System.out.println("TIE OCCURRED BETWEEN THE FOLLOWING CANDIDATES:");
            for (Candidate tiedCandidate : winners) {
                System.out.println(tiedCandidate.name + " FROM " + tiedCandidate.party + " WITH " + tiedCandidate.votes + " VOTES.");
            }
        }
        System.out.println("\nVOTE COUNT SUMMARY:");
        displayVoteCountSummary();
    } else {
        System.out.println("NO WINNER. ELECTIONS TIED.");
    }
}

private static void displayVoteCountSummary() {
    for (Candidate candidate : candidates) {
        System.out.print(candidate.name + ": " + candidate.votes + " votes | ");
        displayVoteCountBar(candidate.votes);
        System.out.println();
    }
}

private static void displayVoteCountBar(int votes) {
    
    for (int i = 0; i < votes; i++) {
        System.out.print('*');
    }
}

private static List<Candidate> findWinners() {
    List<Candidate> winners = new ArrayList<>();
    int maxVotes = 0;

    for (Candidate candidate : candidates) {
        if (candidate.votes > maxVotes) {
            maxVotes = candidate.votes;
            winners.clear();
            winners.add(candidate);
        } else if (candidate.votes == maxVotes) {
            winners.add(candidate); 
        }
    }

    return winners;
}

    private static void resetSystem() {
        System.out.println("\n** RESETTING SYSTEM **");
        candidates.clear();
        voters.clear();
        electionsEnded = false;
        System.out.println("SYSTEM RESET COMPLETE.");
    }

    private static void voterEducation() {
        System.out.println("\n** VOTER EDUCATION **");
        System.out.println("UNDERSTANDING THE IMPORTANCE OF VOTING IS CRUCIAL FOR A THRIVING DEMOCRACY.");
        System.out.println("CHECK OUT THIS VIDEO FOR MORE INFORMATION: https://www.youtube.com/watch?v=GrG7zBUDiqQ ");
    }
}
