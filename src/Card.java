
public class Card {
    final int number;
    final boolean representPicture;
    final String shape;

    public int getNumber(){
        return number;
    }

    public String getShape(){
        return shape;
    }

    public boolean getRepresentPicture(){
        return representPicture;
    }

    public int compare (Card other){
        if (this.number < other.number) return -1;
        else if (this.number > other.number) return 1;
        else return 0;
    }

    @Override
    public String toString (){
        if (!this.representPicture){
            return (this.number + " of " + this.shape);
        }

        else if (this.number == 11){
                return ("Jack of " + this.shape);
            }
        else if (this.number == 12){
            return ("Queen of " + this.shape);
        }

        else if (this.number == 13){
            return ("King of " + this.shape);
        }

        else{
            return ("Ace of " + this.shape);
        }
    }

    public Card(int number, String shape){
        this.number = number;
        this.representPicture = (2 > number || number > 10);
        this.shape = shape;
    }

    //clone builder
    public Card(Card other){
        this.number = other.number;
        this.representPicture = other.representPicture;
        this.shape = other.shape;
    }
}
