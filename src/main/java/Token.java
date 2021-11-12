public class Token{
    private int position;

    public void setPosition(int position){
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void updatePosition(){
        /*
        if (die.getFaceValue() + currentPlayer.getToken().getPosition() > BOARD_SIZE) {
            currentPlayer.getToken().setPosition(die.getFaceValue() + currentPlayer.getToken().getPosition() - BOARD_SIZE);
            currentPlayer.getAccount().setBalance(currentPlayer.getAccount().getBalance() + MOVING_PAST_START);
        }
        else
            currentPlayer.getToken().setPosition(die.getFaceValue() + currentPlayer.getToken().getPosition());

         */
    }
}
