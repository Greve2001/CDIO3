import java.awt.*;

public final class GUI {
    private Board board;

    public GUI() {
        this.board = new Board();
    }


    public void showMessage(String msg) {
        this.board.showMessage(msg);
    }

    public String getUserString(String msg) {
        return this.board.getUserString(msg);
    }

    public String getUserButtonPressed(String msg, String... buttons) {
        for (int i = 0; i < buttons.length; ++i) {
            buttons[i] = buttons[i].replace("\n", "<BR>");
        }

        return this.board.getUserButtonPressed(msg, buttons);
    }

    public String getUserSelection(String msg, String... options) {
        if (options != null && options.length != 0) {
            msg = msg.replace("\n", "<BR>");

            for (int i = 0; i < options.length; ++i) {
                options[i] = options[i].replace("\n", "<BR>");
            }

            return this.board.getUserSelection(msg, options).toString();
        } else {
            throw new NullPointerException("You must supply at least one option!");
        }
    }

    public boolean addPlayer(Player player) {
        return this.board.addPlayer(player);
    }

    public void setDie(int faceValue) {
        this.board.setDie(faceValue);
    }

    public void displayChanceCard(String txt) {
        this.board.displayChanceCard(txt);
    }

    public void setChanceCard(String txt) {
        this.board.setChanceCard(txt);
    }

    public void displayChanceCard() {
        this.board.displayChanceCard();
    }

    public Square[] getFields() {
        return this.board.getSquars();
    }

    public void close() {
        this.board.close();
    }
}
