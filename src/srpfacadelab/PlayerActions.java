package srpfacadelab;

public class PlayerActions {
    
    IGameEngine gameEngine; 
    RpgPlayer player;

    public PlayerActions(IGameEngine gameEngine, RpgPlayer player){
        this.gameEngine = gameEngine;
        this.player = player;
    }

    public void useItem(Item item) {
        if (item.getName().equals("Stink Bomb"))
        {
            List<IEnemy> enemies = gameEngine.getEnemiesNear(player);

            for (IEnemy enemy: enemies){
                enemy.takeDamage(100);
            }
        }
    }

    

    
}