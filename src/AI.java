/**
 * Created by Chris on 2/1/16.
 */
public class AI {

   float birdY_weight;
   float nearestPipeDistance_weight;
   float nearestPipeHeight_weight;
   int threshold;

   public AI(){

   }

   public boolean jump(int birdX, int birdY, int nearestPipeDistance, int nearestPipeHeight){
      boolean triggered = false;

      if(birdY > (nearestPipeHeight -400 - 50))
         triggered = true;

      if(birdY > App.HEIGHT-160) triggered = true;

      if(triggered) {
         return true;
      } else {
         return false;
      }
   }

  public void wrapUp(int fitness){
     return;
  }

}
