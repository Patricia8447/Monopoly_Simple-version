import java.util.concurrent.ThreadLocalRandom;

/**
 * Got one of the following chances randomly:
 * 1. Roll again
 * 2. +1000
 * 3. -1000
 * 4. Move to Jail directly without getting the 2000.
 */
public class ChanceCell extends FunctionCell {

    private String chance;

    public ChanceCell(String chance) {
        super(chance);
        this.chance = chance;
    }

    @Override
    public void event(Player p, Cell[] cells) {
        //to generate the chance for player
        int rand = ThreadLocalRandom.current().nextInt(1,5);

        //decide which chance to use
        switch(rand){
            case 1:
                System.out.println(chance + " result: Roll again! ");
                p.roll();
                break;
            case 2:
                System.out.println(chance + " result: Add $1000! ");
                p.charge(1000);
                break;
            case 3:
                System.out.println(chance + " result: Deduct $1000! ");
                p.charge(-1000);
                break;
            case 4:
                System.out.println(chance + " result: Go to jail now! ");
                p.putToJail();
                break;
        }
    }
}