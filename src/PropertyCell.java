import java.util.Scanner;

public class PropertyCell extends Cell {
    protected int baseCost;
    protected Player owner = null;
    int house = 0;

    /**
     * Return the owner of the property
     * @return owner
     */
    public Player getOwner() {
        return owner;
     }

     /**
      * Constructor
      */
    public PropertyCell(String name, int baseCost) {
        super(name);
        this.baseCost = baseCost;
    }

    /**
     * Return the cost of the property cell
     * @return the base cost of the property cell.
     */
    public int getCost() {
        return baseCost;
    }

    /**
     * Return number of houses built on this property cell.
     * @return the number of houses.
     */
    public int getHouse() {
        return house;
    }

    /**
     * Return a specific format of string. See the assignment webpage
     * for more details
     * @return the specific string of the property cell/
     */
    @Override
    public String toString() {
        String result = "";
        if(house == 0 && owner != null) {
            result = getName() + " owned by " + owner.getName() + ": " + getCost();
        }
       else if(house > 0 && owner != null) {
            result = getName() + " owned by " + owner.getName() + ": " + getCost() + " House" + ": " + house;
        }
        else if(owner == null) {
            result = getName() + " owned by " + "---: " + getCost() + " House" + ": " + house;
        }
        return result;
    }

    @Override
    public void event(Player p, Cell[] cells) {
        Scanner in = new Scanner(System.in);
        //to calculate the fee of building a house
        int pay = (int) (0.2 * baseCost);

        if(owner == null) {
            //first judge how much remaining money the player have
                    if (p.getMoney() < baseCost) {
                        System.out.println("You do not get enough money to own this property.");
                        return;
                    }
                        System.out.println("Do you want to buy this for $" + baseCost + " ?(y/n) ");
                        String choice = in.next();
                        switch (choice) {
                            case "y":
                                p.charge(-baseCost);
                                owner = p;
                                break;
                            case "n":
                                break;
                        }
                    }
        //the player reach the cell that the proerty is owned by himself
        else if ( owner != null && owner == p) {
                   if (p.getMoney() < pay) {
                        System.out.println("You don not have enough money to build a house here. ");
                        return;
                    }
                        System.out.println("Do you want to build a house for this land for $" + pay + " ?(y/n)");
                        String build = in.next();
                        switch (build) {
                            case "y":
                                house++;
                                p.charge(-pay);
                                break;
                            case "n":
                                break;
                        }
                    }
                else {
                    //player in park can not receive the rent
                    if(p.isInPark() == false){
                        System.out.println("This land is owned by " + owner.getName() + " and you need to pay the rent!");
                        int temp = getRent(p);
                        p.charge(-temp); // pay money
                        owner.charge(temp); //owner receive money
                        System.out.println(p.getName() + " has paid " + owner.getName() + " $" + temp);
                    }
                }
            }

    /**
     * Return the rent charged against this player. The formula for an ordinary cell is
     * baseCost * (1 + house * 0.5) rounding the nearest integer.
     *
     * //@param p - The player who is charged. p is irrelevant in this case.
     * @return The rent.
     */
    public int getRent(Player p) {
        int rent = (int)(baseCost * (1 + house * 0.5));
        return rent;
    }
}
