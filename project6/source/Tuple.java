public class Tuple { 
  public final Integer x; 
  public final Integer y; 
  public Tuple(Integer x, Integer y) { 
    this.x = x; 
    this.y = y; 
  } 

  @Override
  public String toString() {
  	return Integer.toString(this.x) + " " + Integer.toString(this.y);
  }
} 