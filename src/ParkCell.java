import java.util.Scanner;

public class ParkCell extends FunctionCell {
    public ParkCell() {
        super( "Park");
    }

    @Override
    public void event(Player p, Cell[] cells) {
        //when the player reach the cell, the position will be considered as in the park, therefore, a boolean true should be in teh brace
        p.setInPark(true);
        System.out.println(p.getName() + " is in park!");
       }
    }


