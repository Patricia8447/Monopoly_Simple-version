import java.util.Scanner;

/**
 * If the owner owns only one train station, the rent is 500
 * If the owner owns two of them, the rent is 1000
 * If the owner owns three of them, the rent is 2000
 * If the owner owns four of them, the rent is 4000
 */
public class TrainStationPropertyCell extends PropertyCell {
    public static final int TRAIN_STATION_COST = 500;

    public static final TrainStationPropertyCell[] TRAIN_STATION_ARRAY = {
            new TrainStationPropertyCell("Kowloon"),
            new TrainStationPropertyCell("Mongkok"),
            new TrainStationPropertyCell("Central"),
            new TrainStationPropertyCell("Shatin")
    };

    /**
     * Constructor
     * @param name
     * //@param baseCost
     */
    public TrainStationPropertyCell(String name) {
        super(name, TRAIN_STATION_COST);
        }

        @Override
        public void event(Player p, Cell[] cells) {
            Scanner in = new Scanner(System.in);
            if(owner == null) {
                if (p.getMoney() < TRAIN_STATION_COST) {
                   return;
                }
                    System.out.println("Do you want to buy this train station for $" + TRAIN_STATION_COST + "?(y/n)");
                    String train = in.next();
                    switch (train) {
                        case "y":
                            owner = p;
                            p.charge(-TRAIN_STATION_COST);
                            break;
                        case "n":
                            break;
                    }
                } else if (owner != p ) {
                    System.out.println("The station is owned by " + owner.getName() + " . He/She has  " + getCountStation(p)
                            + " station(s) and you need to pay the rent!");
                    int temp = getRent(p);
                    p.charge(-temp);
                    owner.charge(temp);
                    System.out.println(p.getName() + " has paid " + owner.getName() + " $" + temp);
            }
            else
                return;
        }

        @Override
        public String toString() {
        String result = "";
        if(owner != null) {
            result = getName() + " owned by " + owner.getName() + ": " + TRAIN_STATION_COST;
        }else if(owner == null) {
            result = getName() + " owned by " + "---: " + TRAIN_STATION_COST;
        }
        return result;
    }

        @Override
        public int getRent(Player p){
        int TSRent = 0;
        switch(getCountStation(p)){
            case 1:
                TSRent = 500;
                break;
            case 2:
                TSRent = 1000;
                break;
            case 3:
                TSRent = 2000;
                break;
            case 4:
                TSRent = 4000;
                break;
        }
        return TSRent;

    }

        public int getCountStation(Player p){
        int k = 0;
        int s = 0;
        int e = 0;
        int result = 0;

        for(int i = 0 ; i < TRAIN_STATION_ARRAY.length; i++) {
            if (TRAIN_STATION_ARRAY[i].getOwner() != null) {
                if(TRAIN_STATION_ARRAY[i].getOwner().getName().equals("Kevin"))
                    k++;
                if (TRAIN_STATION_ARRAY[i].getOwner().getName().equals("Sandy"))
                    s++;
                if (TRAIN_STATION_ARRAY[i].getOwner().getName().equals("Emily"))
                    e++;
            }
        }

            switch(p.getName()){
                case "Kevin":
                    result = k;
                    break;
                case "Sandy":
                    result = s;
                    break;
                case "Emily":
                    result = e;
                    break;
            }
       return result;
    }
}
