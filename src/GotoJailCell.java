class GotoJailCell extends FunctionCell {
    public GotoJailCell() {
        super("Go To Jail");
    }

    public void event(Player p, Cell[] cells) {
        p.putToJail();
        System.out.println(p.getName() + ", go to jail now!");
        }
    }

