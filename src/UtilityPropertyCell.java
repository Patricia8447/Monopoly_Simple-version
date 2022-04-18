import java.util.Scanner;

/**
* The rent of UtilityPropertyCell is computed by the number of steps that the player rolled multiply by x
* x = 100, if both property are owned by the same player
* x = 10, if the owner of this property cell owns only one property cell.
*/
public class UtilityPropertyCell extends PropertyCell {
    public static final int UTILITY_PROPERTY_COST = 500;
    public static final UtilityPropertyCell LIBRARY_UTILITY_CELL = new UtilityPropertyCell("Library", UTILITY_PROPERTY_COST);
    public static final UtilityPropertyCell CANTEEN_UTILITY_CELL = new UtilityPropertyCell("Canteen", UTILITY_PROPERTY_COST);

    /**
     * Constructor
     *
     * @param name
     * @param baseCost
     */
    public UtilityPropertyCell(String name, int baseCost) {
        super(name, baseCost);
    }

    @Override
    public void event(Player p, Cell[] cells) {
        Scanner in = new Scanner(System.in);
        if (owner == null) {
            if (p.getMoney() < UTILITY_PROPERTY_COST) {
                System.out.println("You do not have enough money to buy this utility. ");
                return;
            }
                System.out.println("Do you want to buy this utility for $" + UTILITY_PROPERTY_COST+ " ?(y/n)");
                String utility = in.next();
                switch (utility) {
                    case "y":
                        p.charge(UtilityPropertyCell.UTILITY_PROPERTY_COST);
                        owner = p;
                        break;
                    case "n":
                        break;
                }
            }
        else if(owner != p && p.isInPark() == false) {
            int utilityRent = 0;
            int count = 0;
            //clacualte the rent
            if(LIBRARY_UTILITY_CELL.owner == owner || CANTEEN_UTILITY_CELL.owner == owner) {
                utilityRent = 10 * p.getLastMove();
                count++;
            }else {
                utilityRent = 100 * p.getLastMove();
                count++;
            }
                System.out.println("The utility is owned by " + owner.getName() + " . The owner has " + count
                        + " utility and you need to pay the rent!");
                p.charge(-utilityRent);
                owner.charge(utilityRent);
                System.out.println(p.getName() + " has paid " + owner.getName() + " $" + utilityRent);
            }
        else
            return;
    }

    @Override
    public String toString() {
        String result = "";
        if(owner != null) {
            result = getName() + " owned by " + owner.getName() + ": " + UTILITY_PROPERTY_COST;
        }else if(owner == null) {
            result = getName() + " owned by " + "---: " + UTILITY_PROPERTY_COST;
        }
        return result;
    }
}
