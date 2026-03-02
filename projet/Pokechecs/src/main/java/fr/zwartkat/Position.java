package fr.zwartkat;

public class Position {

    private int x, y;

    public Position(Position position){
        this.x = position.getX();
        this.y = position.getY();
    }

    public Position(int x, int y) {

        if(x < 0 || y < 0 || x > 8 || y > 8){
            throw new IllegalArgumentException("x and y must be between 0 and 8");
        }
        else{
            this.x = x;
            this.y = y;
        }
    }

    public Position(String chaine){

        char firstElement,secondElement;
        int elementX,elementY;

        if(chaine.length() != 2 ){
            throw new IllegalArgumentException("You must give 2 characters");
        }
        else{
            firstElement = chaine.charAt(0);
            secondElement = chaine.charAt(1);

            if(!Character.isLetter(firstElement) || !Character.isDigit(secondElement)){
                throw new IllegalArgumentException("First character must be a letter and second a integer");
            }

            firstElement = Character.toUpperCase(firstElement);
            elementX = (int)firstElement - 'A';
            elementY = (int)secondElement - '1';

            if(elementX < 0 || elementY < 0 || elementX > 9 || elementY > 9){
                throw new IllegalArgumentException("x values must be between A and I. y values between 1 and 9");
            }
            this.x = elementX;
            this.y = elementY;
        }
    }

    // Check if they are the same element
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position other = (Position) obj;
        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
    // Show the position
    public String toString(){
        return new String("" + (char)(this.x + 'A') + (this.y+1));
    }

    // Set the x value
    public void setX(int x){
        this.x = x;
    }

    // Set the y value
    public void setY(int y){
        this.y = y;
    }

    // Get the x value
    public int getX(){
        return this.x;
    }

    // Get the y value
    public int getY(){
        return this.y;
    }
}
