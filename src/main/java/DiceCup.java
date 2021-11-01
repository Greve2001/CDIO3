
public class DiceCup {
    private final Die[] dice = new Die[2];

    public DiceCup() {
        dice[0] = new Die();
        dice[1] = new Die();
    }

    public DiceCup(int die) {
        dice[0] = new Die(die);
        dice[1] = new Die(die);
    }

    public DiceCup(int die1, int die2) {
        dice[0] = new Die(die1);
        dice[1] = new Die(die2);
    }

    public void rollDice() {
        dice[0].roll();
        dice[1].roll();
    }

    public int[] getFaceValues() {
        return new int[]{dice[0].getFaceValue(), dice[1].getFaceValue()};
    }
}
