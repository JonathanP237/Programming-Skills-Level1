import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ejercicio1 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        Player listPlayer[] = new Player[5];
        listPlayer[0] = new Player(8, "Bruno Fernandes", 5, 6, 9, 10, 3);
        listPlayer[1] = new Player(11, "Rasmus Hojlund", 12, 8, 2, 6, 2);
        listPlayer[2] = new Player(5, "Harry Maguire", 1, 5, 1, 7, 9);
        listPlayer[3] = new Player(17, "Alejandro Garnacho", 7, 8, 9, 6, 0);
        listPlayer[4] = new Player(7, "Mason Mount", 2, 6, 4, 8, 1);

        menu(listPlayer);
    }

    private static void menu(Player[] listPlayer) {
        int option = 0;
        do {
            System.out.println("\n1-Player Review\n2-Compare two players");
            System.out.println("3-Top goal scorer\n4-Fastest player");
            System.out.println("5-Most assists player\n6-Highest passing accuracy player");
            System.out.println("7-Most defensive involvements player\n0-Exit");
            System.out.println("\nEnter option: ");
            option = validarEntero();
            switch (option) {
                case 1:
                    playerReview(listPlayer);
                    break;
                case 2:
                    comparePlayer(listPlayer);
                    break;
                case 3:
                    System.out.println("\nTop goal scorer: " + theMost(listPlayer, "goals"));
                    break;
                case 4:
                    System.out.println("\nFastest player: " + theMost(listPlayer, "speed"));
                    break;
                case 5:
                    System.out.println("\nMost assists player: " + theMost(listPlayer, "assists"));
                    break;
                case 6:
                    System.out.println("\nHighest passing accuracy player: " + theMost(listPlayer, "accuracy"));
                    break;
                case 7:
                    System.out.println("\nMost defensive involvements player: " + theMost(listPlayer, "defensive"));
                    break;
                case 0:
                    System.out.println("\nBye");
                    break;
                default:
                    break;
            }
        } while (option != 0);
    }

    public static String theMost(Player[] listPlayer, String statType) {
        Player bestPlayer = null;
        int highestStat = 0;
        for (Player player : listPlayer) {
            int playerStat = 0;
            switch (statType) {
                case "goals":
                    playerStat = player.getGoals();
                    break;
                case "assists":
                    playerStat = player.getAssists();
                    break;
                case "speed":
                    playerStat = player.getSpeed();
                    break;
                case "accuracy":
                    playerStat = player.getPassAccuracy();
                    break;
                case "defensive":
                    playerStat = player.getDefInvolv();
                    break;
                // Agrega aquí más casos para otras estadísticas
            }

            if (playerStat > highestStat) {
                bestPlayer = player;
                highestStat = playerStat;
            }
        }
        return bestPlayer.getNamePlayer();
    }

    private static void comparePlayer(Player[] listPlayer) {
        int jersey1 = 0;
        int jersey2 = 0;
        do {
            System.out.println("\nEnter the jersey number of the first player");
            jersey1 = validarEntero();
            Player player1 = validatePlayer(listPlayer, jersey1);
            System.out.println("\nEnter the jersey number of the second player");
            jersey2 = validarEntero();
            Player player2 = validatePlayer(listPlayer, jersey2);
            if (player1 != null && player2 != null) {
                System.out.println("\nName: " + player1.getNamePlayer() + "\t\tName: " + player2.getNamePlayer());
                System.out.println("Jersey: " + player1.getJersey() + "\t\t\tJersey: " + player2.getJersey());
                System.out.println("Goals: " + player1.getGoals() + "\t\t\tGoals: " + player2.getGoals());
                System.out.println("Speed: " + player1.getSpeed() + "\t\t\tSpeed: " + player2.getSpeed());
                System.out.println("Assists: " + player1.getAssists() + "\t\t\tAssists: " + player2.getAssists());
                System.out.println("Pass. accuracy: " + player1.getPassAccuracy() + "\t\tPass. accuracy: "
                        + player2.getPassAccuracy());
                System.out.println("Def. involvement: " + player1.getDefInvolv() + "\t\tDef. involvement: "
                        + player2.getDefInvolv());
            } else {
                System.out.println("\nError try again");
            }
            System.out.println("\n0 to exit or other number to another comparation");
            jersey1 = validarEntero();
        } while (jersey1 != 0);
        System.out.println("\nBack to main");
    }

    private static void playerReview(Player[] listPlayer) {
        int jersey = 0;
        do {
            System.out.println("\nEnter the jersey number or 0 to exit");
            jersey = validarEntero();
            Player player = validatePlayer(listPlayer, jersey);
            if (jersey == 0) {
                System.out.println("\nBack to main");
            } else if (player != null) {
                System.out.println("\nName: " + player.getNamePlayer());
                System.out.println("Jersey: " + player.getJersey());
                System.out.println("Goals: " + player.getGoals());
                System.out.println("Speed: " + player.getSpeed());
                System.out.println("Assists: " + player.getAssists());
                System.out.println("Passing accuracy: " + player.getPassAccuracy());
                System.out.println("Defensive involvement: " + player.getDefInvolv());
            }
        } while (jersey != 0);
    }

    public static Player validatePlayer(Player[] listPlayer, int jersey) {
        Player player = new Player();
        player = null;
        for (int i = 0; i < listPlayer.length; i++) {
            if (listPlayer[i].getJersey() == jersey) {
                player = listPlayer[i];
                break;
            }
        }
        if (player == null)
            System.out.println("Player not found.");
        return player;
    }

    public static int validarEntero() {
        int entero = 0;
        try {
            entero = Integer.parseInt(in.readLine());
        } catch (Exception e) {
            entero = -1;
        }
        return entero;
    }
}

class Player {
    private int jersey;
    private String namePlayer;
    private int goals;
    private int speed;
    private int assists;
    private int passAccuracy;
    private int defInvolv;

    public Player() {
    }

    public Player(int jersey, String namePlayer, int goals, int speed, int assists, int passAccuracy, int defInvolv) {
        this.jersey = jersey;
        this.namePlayer = namePlayer;
        this.goals = goals;
        this.speed = speed;
        this.assists = assists;
        this.passAccuracy = passAccuracy;
        this.defInvolv = defInvolv;
    }

    public int getJersey() {
        return this.jersey;
    }

    public String getNamePlayer() {
        return this.namePlayer;
    }

    public int getGoals() {
        return this.goals;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getAssists() {
        return this.assists;
    }

    public int getPassAccuracy() {
        return this.passAccuracy;
    }

    public int getDefInvolv() {
        return this.defInvolv;
    }
}
