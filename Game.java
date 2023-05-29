import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import java.awt.Color;
import java.util.Random;
import enigma.console.TextAttributes;


public class Game {
    final String FILE_PATH = "highscore.txt";
    public enigma.console.Console cn = Enigma.getConsole("Welcome the Chain Game" );
    public TextMouseListener tmlis;
    public KeyListener klis;

    // ------ Standard variables for mouse and keyboard ------
    public int mousepr;          // mouse pressed?
    public int mousex, mousey;   // mouse text coords.
    public int keypr;   // key pressed?
    public int rkey;    // key   (for press/release)
    // ----------------------------------------------------
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);
    boolean gameOver = false;
    char[][] map = new char[21][33];
    SLL chainSelectedCoord = new SLL();
    SLL chainOfPoint = new SLL();
    MLL table = new MLL();
    Cursor player;
    int round = 1;
    double score = 0;
    int position = 5;

    Game() throws Exception {   // --- Contructor

        // ------ Standard code for mouse and keyboard ------ Do not change
        tmlis=new TextMouseListener() {
            public void mouseClicked(TextMouseEvent arg0) {}
            public void mousePressed(TextMouseEvent arg0) {
                if(mousepr==0) {
                    mousepr=1;
                    mousex=arg0.getX();
                    mousey=arg0.getY();
                }
            }
            public void mouseReleased(TextMouseEvent arg0) {}
        };
        cn.getTextWindow().addTextMouseListener(tmlis);

        klis=new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                if(keypr==0) {
                    keypr=1;
                    rkey=e.getKeyCode();
                }
            }
            public void keyReleased(KeyEvent e) {}
        };
        cn.getTextWindow().addKeyListener(klis);
        // ----------------------------------------------------


        System.out.print("Do u wanna play random seed or ur's? : R = Random    M = My own : ");
        String seedAnswer = scanner.next();

        if (seedAnswer.equalsIgnoreCase("m")){
            System.out.print("What is ur seed ? : ");
            int seed = scanner.nextInt();
            seedAnswer = Integer.toString(seed);
            random.setSeed(seed);
        }
        for (int i = 0; i < 70; i ++){
            for (int j = 0; j < 2; j++){
                cn.getTextWindow().output(i,j,' ');
            }
        }

        cn.getTextWindow().setCursorPosition(0,0);
        startPosituin();
        initializationMap();

        while(!gameOver)
        {
            int player_x = player.getX();
            int player_y = player.getY();
            printMap(map);

            cn.getTextWindow().setCursorPosition(35, 0);
            cn.getTextWindow().output("Board Seed :   " + seedAnswer);
            cn.getTextWindow().setCursorPosition(35, 1);
            cn.getTextWindow().output("Round      :   " + round);
            cn.getTextWindow().setCursorPosition(35, 2);
            cn.getTextWindow().output("Score      :   " + score);
            cn.getTextWindow().setCursorPosition(35, 3);
            cn.getTextWindow().output("---------------------------");
            cn.getTextWindow().setCursorPosition(35, 4);
            cn.getTextWindow().output("Table: ");

            if(keypr==1)
            {
                if(rkey==KeyEvent.VK_UP)
                {
                    if (Objects.equals(map[player_y - 1][player_x], ' '))
                    {
                        map[player_y - 1][player_x] = '_';
                        if(map[player_y][player_x] != '-' && map[player_y][player_x] != '|' && map[player_y][player_x] == '_')
                            map[player_y][player_x] = ' ';
                        player.setY(player_y - 1);
                    }
                    else if(Objects.equals(map[player_y - 1][player_x], '+'))
                    {
                        map[player_y - 1][player_x] = '+';
                        if(map[player_y][player_x] != '-' && map[player_y][player_x] != '|' && map[player_y][player_x] == '_')
                            map[player_y][player_x] = ' ';
                        player.setY(player_y - 1);
                    }
                    else
                    {
                        cn.getTextWindow().setCursorPosition(35, 2);
                    }
                }
                if(rkey==KeyEvent.VK_DOWN)
                {
                    if (Objects.equals(map[player_y + 1][player_x], ' '))
                    {
                        map[player_y + 1][player_x] = '_';
                        if(map[player_y][player_x] != '-' && map[player_y][player_x] != '|'&& map[player_y][player_x] == '_')
                            map[player_y][player_x] = ' ';
                        player.setY(player_y + 1);
                    }
                    else if(Objects.equals(map[player_y + 1][player_x], '+'))
                    {
                        map[player_y + 1][player_x] = '+';
                        if(map[player_y][player_x] != '-' && map[player_y][player_x] != '|' && map[player_y][player_x] == '_')
                            map[player_y][player_x] = ' ';
                        player.setY(player_y + 1);
                    }
                    else
                    {
                        cn.getTextWindow().setCursorPosition(2, 35);
                    }
                }
                if(rkey==KeyEvent.VK_RIGHT)
                {
                    if (Objects.equals(map[player_y][player_x + 1], ' '))
                    {
                        map[player_y][player_x + 1] = '_';
                        if(map[player_y][player_x] != '-' && map[player_y][player_x] != '|' && map[player_y][player_x] == '_' )
                            map[player_y][player_x] = ' ';
                        player.setX(player_x + 1);
                    }
                    else if(Objects.equals(map[player_y][player_x+1], '+'))
                    {
                        map[player_y][player_x + 1] = '+';
                        if(map[player_y][player_x] != '-' && map[player_y][player_x] != '|' && map[player_y][player_x] == '_')
                            map[player_y][player_x] = ' ';
                        player.setX(player_x + 1);
                    }
                    else
                    {
                        cn.getTextWindow().setCursorPosition(2, 35);
                    }
                }
                if(rkey==KeyEvent.VK_LEFT)
                {
                    if (Objects.equals(map[player_y][player_x - 1], ' ')){
                        map[player_y][player_x - 1] = '_';
                        if(map[player_y][player_x] != '-' && map[player_y][player_x] != '|'&& map[player_y][player_x] == '_')
                            map[player_y][player_x] = ' ';
                        player.setX(player_x - 1);
                    }
                    else if(Objects.equals(map[player_y][player_x-1], '+'))
                    {
                        map[player_y ][player_x-1] = '+';
                        if(map[player_y][player_x] != '-' && map[player_y][player_x] != '|' && map[player_y][player_x] == '_')
                            map[player_y][player_x] = ' ';
                        player.setX(player_x - 1);
                    }
                    else
                    {
                        cn.getTextWindow().setCursorPosition(2, 35);
                    }
                }
                if (rkey==KeyEvent.VK_E)
                {
                    DLL highScore = new DLL();
                    for (int i = 0; i < 70; i++) {
                        for (int j = 0; j < 30; j++) {
                            cn.getTextWindow().output(i, j, ' ');
                        }
                    }
                    cn.getTextWindow().setCursorPosition(1,0);
                    cn.getTextWindow().output("Please enter your name: ");
                    Scanner inputName =new Scanner(System.in);
                    String playerName = inputName.nextLine();
                    int integerScore = (int)score;
                    String formattedLine = String.format("%-15s %-15d", playerName, integerScore);
                    try {
                        PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true));
                        writer.println(formattedLine);
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        File file = new File(FILE_PATH);
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String line;
                        while ((line = br.readLine()) != null)
                        {
                            if(line.isBlank()){
                                continue;
                            }
                            String[] parts = line.split("(?<=\\D)(?=\\d)");
                            String name = parts[0].trim();

                            String score = parts[1];
                            Object data;
                            data = name + "," + score;
                            highScore.insertSorted(data);
                        }
                        br.close();
                    } catch (IOException e) {
                        System.out.println("Error reading file: " + e.getMessage());
                    }

                    cn.getTextWindow().setCursorPosition(0,0);
                    cn.getTextWindow().output(highScore.display());
                    break;
                }
                if(player_y % 2 != 0 || player_x % 2 != 0)
                {
                    if(rkey == KeyEvent.VK_SPACE)
                    {
                        TailCoordination coordination = new TailCoordination(player_x, player_y);
                        if(map[player_y][ player_x] == '+')
                        {
                            map[player_y][ player_x] = '_';
                            chainSelectedCoord.removeDuplicates();
                            chainSelectedCoord.deleteByCoordinates(player_x, player_y);

                        }
                        else{
                            map[player_y][ player_x] = '+';
                            chainSelectedCoord.insert(coordination);
                        }

                    }
                }
                if (rkey==KeyEvent.VK_ENTER)
                {
                    gameOver = checkingSequence(chainSelectedCoord);
                    gameOver = checkingPoints(chainOfPoint);
                    map = cleanMap(chainSelectedCoord, map);


                    String printChainString = printPoint(chainOfPoint);

                    if (!gameOver){
                        round += 1;
                        cn.getTextWindow().setCursorPosition(35, position);
                        cn.getTextWindow().output(printChainString);
                        chainSelectedCoord.head = null;
                        chainOfPoint.head = null;
                        position += 2;
                    }

                }

                keypr=0;    // last action
            }
            Thread.sleep(20);
        }



        cn.getTextWindow().setCursorPosition(15,21);
        cn.getTextWindow().output("Game Over");
    }
    void initializationMap() {

        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[i].length; j++)
            {
                if (i == 0 || i == map.length - 1) {
                    if (j == 0 || j == map[i].length - 1) {
                        map[i][j] = '+';
                    } else {
                        map[i][j] = '-';
                    }
                } else if (j == 0 || j == map[i].length - 1) {
                    map[i][j] = '|';
                } else {
                    map[i][j] = '!';
                }
            }
        }

        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++){
                if (i % 2 != 0 & j % 2 != 0 & map[i][j] == '!')
                {
                    int random_point = random.nextInt(1,5);

                    if (random_point == 1){
                        map[i][j] = '1';
                    }
                    else if (random_point == 2){
                        map[i][j] = '2';
                    }
                    else if (random_point == 3){
                        map[i][j] = '3';
                    }
                    else if (random_point == 4){
                        map[i][j] = '4';
                    }
                }
                else if(map[i][j] == '!'){
                    map[i][j] = ' ';
                }
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
    void startPosituin(){

        int x_position = random.nextInt(1, 16) * 2;
        int y_position = random.nextInt(1, 10) * 2;

        player = new Cursor(x_position, y_position);
    }
    public void printMap(char[][] map){
        for(int j = 0;j<21;j++){
            for(int i = 0;i<33;i++){
                if (map[j][i] == '-' | map[j][i] == '|'| map[j][i] == '+')
                {
                    cn.getTextWindow().output(i,j,map[j][i],new TextAttributes(Color.WHITE));
                }
                if(map[j][i] == '_')
                {
                    cn.getTextWindow().output(i,j,map[j][i],new TextAttributes(Color.CYAN));
                }
                if (map[j][i] == ' ')
                {
                    cn.getTextWindow().output(i,j,map[j][i],new TextAttributes(Color.WHITE));
                }
                if (map[j][i] == '1')
                {
                    cn.getTextWindow().output(i,j,map[j][i],new TextAttributes(Color.RED));
                }
                if (map[j][i] == '2')
                {
                    cn.getTextWindow().output(i,j,map[j][i],new TextAttributes(Color.RED));
                }
                if (map[j][i] == '3')
                {
                    cn.getTextWindow().output(i,j,map[j][i],new TextAttributes(Color.RED));
                }
                if (map[j][i] == '4')
                {
                    cn.getTextWindow().output(i,j,map[j][i],new TextAttributes(Color.RED));
                }
                if(map[j][i] == '.')
                {
                    cn.getTextWindow().output(i,j,map[j][i],new TextAttributes(Color.WHITE));
                }
            }
        }
    }
    boolean checkingSequence (SLL chainSelectedCoord){
        if (chainSelectedCoord.size() < 3){
            return gameOver = true;
        }
        else {
            NodeSLL currentNode = chainSelectedCoord.head;
            TailCoordination startCoord = (TailCoordination)currentNode.data;

            while (currentNode != null) {
                TailCoordination prevCoord = (TailCoordination)currentNode.data;
                int x1 = prevCoord.getX();
                int y1 = prevCoord.getY();

                currentNode = currentNode.next;

                TailCoordination nextCoord = (TailCoordination)currentNode.data;
                int x2 = nextCoord.getX();
                int y2 = nextCoord.getY();

                int dx = Math.abs(x2 - x1);
                int dy = Math.abs(y2 - y1);

                if ((dx != 2 && dy != 2) && (dx != 1 && dy != 1)){
                    return gameOver = true;
                }

                if ((x1 > x2) && x1 % 2 == 0){
                    chainOfPoint.insert(Character.getNumericValue(map[y1][x1+1]));
                } else if ((x1 < x2) && x1 % 2 == 0){
                    chainOfPoint.insert(Character.getNumericValue(map[y1][x1-1]));
                } else if (y1 > y2) {
                    chainOfPoint.insert(Character.getNumericValue(map[y1+1][x1]));
                } else if (y1 < y2) {
                    chainOfPoint.insert(Character.getNumericValue(map[y1-1][x1]));
                }

                if (currentNode.next == null){
                    TailCoordination lastCoord = (TailCoordination) currentNode.data;
                    x2 = lastCoord.getX();
                    y2 = lastCoord.getY();

                    int x3 = startCoord.getX();
                    int y3 = startCoord.getY();

                    dx = Math.abs(x3 - x2);
                    dy = Math.abs(y3 - y2);

                    if (dx == 2 && dy == 2){
                        return gameOver = true;
                    }

                    if ((x2 > x1) && x1 % 2 == 0){
                        chainOfPoint.insert(Character.getNumericValue(map[y2][x2-1]));
                    } else if ((x2 < x1) && x1 % 2 == 0){
                        chainOfPoint.insert(Character.getNumericValue(map[y2][x2+1]));
                    } else if (y2 > y1) {
                        chainOfPoint.insert(Character.getNumericValue(map[y2-1][x2]));
                    } else if (y2 < y1) {
                        chainOfPoint.insert(Character.getNumericValue(map[y2+1][x2]));
                    }

                    if ((x2 > x1) && x1 % 2 == 0){
                        chainOfPoint.insert(Character.getNumericValue(map[y2][x2+1]));
                    } else if ((x2 < x1) && x1 % 2 == 0){
                        chainOfPoint.insert(Character.getNumericValue(map[y2][x2-1]));
                    } else if (y2 > y1) {
                        chainOfPoint.insert(Character.getNumericValue(map[y2+1][x2]));
                    } else if (y2 < y1) {
                        chainOfPoint.insert(Character.getNumericValue(map[y2-1][x2]));
                    }
                    break;
                }
            }
        }
        return gameOver = false;
    }
    boolean checkingPoints(SLL chainOfPoint){
        NodeSLL currentNode = chainOfPoint.head;
        int next = 0;

        if (chainOfPoint.size() < 4){
            return gameOver = true;
        }

        while (currentNode.next != null) {
            int start = (int) currentNode.data;
            currentNode = currentNode.next;
            next = (int) currentNode.data;

            int dx = Math.abs(start - next);

            if (dx != 1) {
                return gameOver = true;
            }
        }
        score += Math.pow(chainOfPoint.size(), 2);
        return gameOver = false;
    }
    String printPoint(SLL chainOfPoint){
        String chainString = "";
        NodeSLL currpoints = chainOfPoint.head;
        if (chainOfPoint.size() < 4){
            return chainString;
        }
        while (currpoints.next != null){
            String point = currpoints.data.toString();
            chainString += point + " + ";

            currpoints = currpoints.next;
        }
        String point = currpoints.data.toString();
        chainString += point;
        return chainString;
    }
    char[][] cleanMap(SLL chainSelectedCoord, char[][] map){
        for(int j = 1;j<20;j++) {
            for (int i = 1; i < 32; i++) {
                if (map[j][i] == '+'){
                    map[j][i] = ' ';
                }
            }
        }
        NodeSLL currentNode = chainSelectedCoord.head;
        TailCoordination startCoord = (TailCoordination)currentNode.data;


        while (currentNode != null) {
            if (chainSelectedCoord.size() < 3){
                break;
            }
            TailCoordination prevCoord = (TailCoordination)currentNode.data;
            int x1 = prevCoord.getX();
            int y1 = prevCoord.getY();

            currentNode = currentNode.next;

            TailCoordination nextCoord = (TailCoordination)currentNode.data;
            int x2 = nextCoord.getX();
            int y2 = nextCoord.getY();

            if ((x1 > x2) && x1 % 2 == 0){
                map[y1][x1+1] = '.';
            } else if ((x1 < x2) && x1 % 2 == 0){
                map[y1][x1-1] = '.';
            } else if (y1 > y2) {
                map[y1+1][x1] = '.';
            } else if (y1 < y2) {
                map[y1-1][x1] = '.';
            }

            if (currentNode.next == null){
                TailCoordination lastCoord = (TailCoordination) currentNode.data;
                x2 = lastCoord.getX();
                y2 = lastCoord.getY();

                int x3 = startCoord.getX();
                int y3 = startCoord.getY();

                if ((x2 > x1) && x1 % 2 == 0){
                    map[y2][x2-1] = '.';
                } else if ((x2 < x1) && x1 % 2 == 0){
                    map[y2][x2+1] = '.';
                } else if (y2 > y1) {
                    map[y2-1][x2] = '.';
                } else if (y2 < y1) {
                    map[y2+1][x2] = '.';
                }

                if ((x2 > x1) && x1 % 2 == 0){
                    map[y2][x2+1] = '.';
                } else if ((x2 < x1) && x1 % 2 == 0){
                    map[y2][x2-1] = '.';
                } else if (y2 > y1) {
                    map[y2+1][x2] = '.';
                } else if (y2 < y1) {
                    map[y2-1][x2] = '.';
                }
                break;
            }
        }
        return map;
    }


}
