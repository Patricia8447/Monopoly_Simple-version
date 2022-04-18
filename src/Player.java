import java.util.concurrent.ThreadLocalRandom;

public class Player {
    private final String name;
    private int money;
    private int position;
    private int lastMove;
    private boolean inPark;
    private boolean inJail;


    /**
     * Constructor
     * 
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        money = 10000;
    }

    /**
     * A player roll a dice and change its position
     */
    public void roll() {

        //to chance the status of the player who is already in park
        if(isInPark() == true){
            setInPark(false);
        }

        //to change the status of the player who is already in the jail
        if(isInJail() == true) {
            inJail = false;
        } else{
            //only when the player is not in teh jail will these lines be executed
           int dice = ThreadLocalRandom.current().nextInt(1, 7);
            //update the position
            position = getPosition() + dice;
            lastMove = dice;

            while(dice == 6){
                System.out.println("We roll again for you");
                dice = ThreadLocalRandom.current().nextInt(1, 7);
                //update the position
                position = getPosition() + dice;
                lastMove = dice;
            }

            //when passing by the home cell, player can earn $2000
            if (position - lastMove < Gameboard.HOME_POSITON) {
                charge(2000);
            }
            System.out.println(name + ", this is your turn. We roll for you. \n" + "It is ---- " + lastMove);
        }
    }

    /**
     * Return true if the player is in Park 
     *
     * @return true if the player is in Park
     */
    public boolean isInPark() {
       return inPark;
    }

    /**
     * Set the player in a park. This will only be called when a player
     * move to the cell Car Park. 
     *
     * @param inPark true if the player is set in the park
     */
    public void setInPark(boolean inPark) { 
        this.inPark = inPark;
     }

    /**
     * Return true if the player is in Jail. It should return false
     * if the player visits Jail (i.e., rolls a dice and moves to the cell Jail)
     *
     * @return true if the player is in Jail
     */
    public boolean isInJail() {
        return inJail;
     }

     /**
     * Put the player into Jail directly without passing Home (i.e., no 2000)
     */
    public void putToJail() {
        position = Gameboard.JAIL_POSITION;
    }

    /**
     * Get the value of dice that the player has just rolled.
     *
     * @return the value of dice
     */
    public int getLastMove() {
        return lastMove;
    }

    /**
     * Get the amount of money that the player has
     *
     * @return the amount of money the player has
     */
    public int getMoney() {
        return money;
     }

     /**
      * Return the name of the player
      *
      * @return the name of the player
      */
    public String getName() {
        return name;
     }

     /**
      * return the current position of the player
      * 
      * @return the position of the player
      */
    public int getPosition() {
        if(position < Gameboard.CELL_SIZE)
            return position;
        else
            //to make sure the array will not out of bound
            return position - Gameboard.CELL_SIZE;
     }

     /**
      * charge certain amount of dollar from the player.
      * 
      * @param money The amount being charged
      */
    public void charge(int money) {
        int remain = this.money + money;
        this.money = remain;
     }
}
