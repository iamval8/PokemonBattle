import java.util.Scanner;

public class Pokemon {
    private int health;
    private int strength;
    private int speed;
    private String name;
    private int direction;
    private static Scanner scan = new Scanner(System.in);
    private int x, y, z;

    public Pokemon(String name, int health, int strength, int speed) {
        this.health = health;
        this.strength = strength;
        this.speed = speed;
        this.name = name;
    }

    public int getX() //Gets the x-coordinate of the current player
    {
        return this.x;
    }

    public int gety() //Gets the y-coordinate of the current player
    {
        return this.y;
    }

    public int getZ() //Gets the z-coordinate of the current player
    {
        return this.z;
    }

    public int getHp() //Gets the current health of the player
    {
        return this.health;
    }

    public int getDirection() {
        return this.direction;
    }

    public void setHp(int hp)
    {
        if (hp < 0)
        {
            this.health = 0;
        } else {
            this.health = hp;
        }
    }

    public void move(int direction, int units)
    {
        if (direction == 1){
            this.x += units;
        } else if(direction ==2){
            this.x-= units;
        } else if (direction ==3){
            this.y += units;
        } else if(direction ==4){
            this.y -= units;
        }
    }

    public void setDirection(int direction)
    {
        if(direction > 0 && direction <7){
            this.direction = direction;
        }
    }

    private static int chance(int min, int max)
    {
        return (int)(Math.random()*(max-min +1)+min);
    }

    private void calculateBattleChance()
    {
        int chance = chance(50, 100);

        if (chance>50)
        {
            Pokemon enemy = new Pokemon("Pikachu",chance(0,100), chance(0,100),chance(0,100));
            System.out.println("You have ran into a " + enemy.name);
            System.out.println("Type 1 to fight, or 2 to run away!");

            int choice = scan.nextInt();
            if (choice == 2)
            {
                System.out.println("You decided to run away!");
                this.calculateMove();
            } else if (choice == 1) {
                System.out.println("You decided to fight!");
                battle(this, enemy);
                calculateMove();
            }
        } else {
            calculateMove();
        }

    }

    private void calculateMove()
    {
        System.out.println("\nWhich way would you like to move?");
        System.out.println("Forward: 1, Backward: 2, Left: 3, Right: 4, Quit: 5");
        int newDir = scan.nextInt();

        this.setDirection(newDir);
        move(this.direction, 1);

        if(direction ==1){
            System.out.println("\nYou have moved forward!");
        } else if (direction ==2) {
            System.out.println("\nYou have moved backward");
        } else if (direction ==3) {
            System.out.println("\nYou have moved left");
        } else if(direction==4) {
            System.out.println("\nYou have moved right");
        }else if (direction == 5){
            System.exit(0);
        }

        calculateBattleChance();
        System.out.println("\n");
    }

    public static void battle(Pokemon pokemon1, Pokemon pokemon2) {
        System.out.println(pokemon1.name + " begins the fight against " + pokemon2.name);


        while
        (pokemon1.health >= 1 || pokemon2.health >= 1)
        {
            //FORGOT THIS LINE
            pokemon2.health = pokemon2.health - pokemon1.strength; //THIS LINE
            System.out.println(pokemon1.name + " does " + pokemon1.strength + " damage to " +
                    pokemon2.name + " and " + pokemon2.name + " has " + pokemon2.health +
                    " health left.");

            if (pokemon2.health <= 0)
                break;

            pokemon1.health = pokemon1.health - pokemon2.strength;

            System.out.println(pokemon2.name + " does " + pokemon2.strength + " damage to " +
                    pokemon1.name + " and " + pokemon1.name + " has " + pokemon1.health +
                    " health left.");
        }

        if (pokemon1.health < 1) {
            System.out.println(pokemon1.name + " has lost the fight");
        } else {
            System.out.println(pokemon2.name + " has lost the fight");
        }
    }
    public void start()
    {
        this.calculateMove();
    }
}
