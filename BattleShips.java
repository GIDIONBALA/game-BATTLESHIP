import java.util.Scanner;

public class BattleShips {
    public static int numRows = 10;
    public static int numCols = 10;
    public static int kapalAmerika;
    public static int kapalChina;
    public static String[][] grid = new String[numRows][numCols];
    public static int[][] missedGuesses = new int[numRows][numCols];

    public static void main(String[] args){
        System.out.println("--- selamat datang di game BATTLESHIP ---");
        System.out.println("saat ini, laut masih kosong\n");

        //Step 1 – membuat map map
        membuatPetaLaut();

        //Step 2 – menyebarkan kapal amerika
        menyebarKapalAmerika();

        //Step 3 - menyebarkan kapal china
        menyebarKapalChina();

        //Step 4 perang
        do {
            Battle();
        }while(BattleShips.kapalAmerika != 0 && BattleShips.kapalChina != 0);

        //Step 5 - Game over
        gameOver();
    }

    public static void membuatPetaLaut(){
        //First section of Ocean Map
        System.out.print("  ");
        for(int i = 0; i < numCols; i++)
            System.out.print(i);
        System.out.println();

        //Middle section of Ocean Map
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = " ";
                if (j == 0)
                    System.out.print(i + "|" + grid[i][j]);
                else if (j == grid[i].length - 1)
                    System.out.print(grid[i][j] + "|" + i);
                else
                    System.out.print(grid[i][j]);
            }
            System.out.println();
        }

        //Last section of Ocean Map
        System.out.print("  ");
        for(int i = 0; i < numCols; i++)
            System.out.print(i);
        System.out.println();
    }

    public static void menyebarKapalAmerika(){
        Scanner input = new Scanner(System.in);

        System.out.println("\nsebar kapal :");
        //Deploying five ships for player
        BattleShips.kapalAmerika = 5;
        for (int i = 1; i <= BattleShips.kapalAmerika; ) {
            System.out.print("masukkan koordinat x kapal " + i +" :");
            int x = input.nextInt();
            System.out.print("masukkan koordinat y kapal " + i + " :");
            int y = input.nextInt();

            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (grid[x][y] == " "))
            {
                grid[x][y] =   "@";
                i++;
            }
            else if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && grid[x][y] == "@")
                System.out.println("You can't place two or more ships on the same location");
            else if((x < 0 || x >= numRows) || (y < 0 || y >= numCols))
                System.out.println("You can't place ships outside the " + numRows + " by " + numCols + " grid");
        }
        tampilanMapLaut();
    }

    public static void menyebarKapalChina(){
        System.out.println("\nComputer is deploying ships");
        //Deploying five ships for computer
        BattleShips.kapalChina = 5;
        for (int i = 1; i <= BattleShips.kapalChina; ) {
            int x = (int)(Math.random() * 10);
            int y = (int)(Math.random() * 10);

            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (grid[x][y] == " "))
            {
                grid[x][y] =   "x";
                System.out.println(i + ". MENYEBAR kapal");
                i++;
            }
        }
        tampilanMapLaut();
    }

    public static void Battle(){
        giliranAmerika();
        giliranChina();

        tampilanMapLaut();

        System.out.println();
        System.out.println("Kapal Amerika : " + BattleShips.kapalAmerika + " | kapal China : " + BattleShips.kapalChina);
        System.out.println();
    }

    public static void giliranAmerika(){
        System.out.println("\ngiliran Amerika untuk memenbak");
        int x = -1, y = -1;
        do {
            Scanner input = new Scanner(System.in);
            System.out.print("Masukkan koordinat X yang mau di tembak : ");
            x = input.nextInt();
            System.out.print("Masukkan koordinat Y yang mau di tembak : ");
            y = input.nextInt();

            if ((x >= 0 && x < numRows) && (y >= 0 && y < numCols)) //valid guess
            {
                if (grid[x][y] == "x") //if computer ship is already there; computer loses ship
                {
                    System.out.println("Boom! Amerika menenggelamkan Kapal!");
                    grid[x][y] = "!"; //Hit mark
                    --BattleShips.kapalChina;
                }
                else if (grid[x][y] == "@") {
                    System.out.println("astaga, Amerika menenggelamkan kapalnya sendiri wkwkw");
                    grid[x][y] = "x";
                    --BattleShips.kapalAmerika;
                    ++BattleShips.kapalChina;
                }
                else if (grid[x][y] == " ") {
                    System.out.println("Maaf, Amerika salah sasaran");
                    grid[x][y] = "-";
                }
            }
            else if ((x < 0 || x >= numRows) || (y < 0 || y >= numCols))  //invalid guess
                System.out.println("You can't place ships outside the " + numRows + " by " + numCols + " grid");
        }while((x < 0 || x >= numRows) || (y < 0 || y >= numCols));  //keep re-prompting till valid guess
    }

    public static void giliranChina(){
        System.out.println("\nkapal China");
        //Guess co-ordinates
        int x = -1, y = -1;
        do {
            x = (int)(Math.random() * 10);
            y = (int)(Math.random() * 10);

            if ((x >= 0 && x < numRows) && (y >= 0 && y < numCols)) //valid guess
            {
                if (grid[x][y] == "@") //if player ship is already there; player loses ship
                {
                    System.out.println("China menenggelamkan salah satu kapal");
                    grid[x][y] = "x";
                    --BattleShips.kapalAmerika;
                    ++BattleShips.kapalChina;
                }
                else if (grid[x][y] == "x") {
                    System.out.println("China menenggelamkan salah satu kapalnya sendiri");
                    grid[x][y] = "!";
                }
                else if (grid[x][y] == " ") {
                    System.out.println("China salah sasaran");
                    //Saving missed guesses for computer
                    if(missedGuesses[x][y] != 1)
                        missedGuesses[x][y] = 1;
                }
            }
        }while((x < 0 || x >= numRows) || (y < 0 || y >= numCols));  //keep re-prompting till valid guess
    }

    public static void gameOver(){
        System.out.println("kapal Amerika: " + BattleShips.kapalAmerika + " | Kapal China: " + BattleShips.kapalChina);
        if(BattleShips.kapalAmerika > 0 && BattleShips.kapalChina <= 0)
            System.out.println("Hore ! Amerika menang dalam battle ");
        else
            System.out.println("hore , China menang dalam battle ");
        System.out.println();
    }

    public static void tampilanMapLaut(){
        System.out.println();
        //First section of Ocean Map
        System.out.print("  ");
        for(int i = 0; i < numCols; i++)
            System.out.print(i);
        System.out.println();

        //Middle section of Ocean Map
        for(int x = 0; x < grid.length; x++) {
            System.out.print(x + "|");

            for (int y = 0; y < grid[x].length; y++){
                System.out.print(grid[x][y]);
            }

            System.out.println("|" + x);
        }

        //Last section of Ocean Map
        System.out.print("  ");
        for(int i = 0; i < numCols; i++)
            System.out.print(i);
        System.out.println();
    }
}



