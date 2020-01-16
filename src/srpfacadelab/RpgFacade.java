package srpfacadelab;

public class RpgFacade {
    
    PlayerActions PA;
    InventoryManagement IM; 
    RpgPlayer player;

    public RpgFacade(IGameEngine gameEngine, RpgPlayer player){
        PA = new PlayerActions(gameEngine, player);
        IM = new InventoryManagement(gameEngine, player);
    }

    public void useItem(Item item) {
        PA.useItem(item);
    }

    public boolean pickUpItem(Item item) {
        IM.pickUpItem(item);
    }

    public void takeDamage(int damage) {
        IM.takeDamage(damage);
    }

}