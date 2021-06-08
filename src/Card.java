
public class Card {
    final int number;
    final boolean representPicture;
    final String shape;

    public int compare (Card other){
        return Integer.compare(this.number, other.number);
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
