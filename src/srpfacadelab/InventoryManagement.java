package srpfacadelab;

import java.util.List;
import java.util.ArrayList;

public class InventoryManagement {

    IGameEngine gameEngine; 
    RpgPlayer player;

    public InventoryManagement(IGameEngine gameEngine, RpgPlayer player){
        this.gameEngine = gameEngine;
        this.player = player;
    }

    private void calculateStats() {
        for (Item i: player.inventory) {
            player.setArmour(i.getArmour() + player.getArmour());
        }
    }

    public void takeDamage(int damage) {
        if (player.carryingCapacity/2 > calculateInventoryWeight()){
            damage = (int)(0.75 * damage);
        }
        if (damage < player.getArmour()) {
            gameEngine.playSpecialEffect("parry");
        }

        int damageToDeal = damage - player.getArmour();
        // health -= damageToDeal;
        player.setHealth(player.getHealth()-damageToDeal);

        gameEngine.playSpecialEffect("lots_of_gore");
    }

    private boolean checkIfItemExistsInInventory(Item item) {
        for (Item i: inventory) {
            if (i.getId() == item.getId())
                return true;
        }
        return false;
    }

    private int calculateInventoryWeight() {
        int sum=0;
        for (Item i: player.inventory) {
            sum += i.getWeight();
        }
        return sum;
    }

    public boolean pickUpItem(Item item) {
        int weight = calculateInventoryWeight();
        if (weight + item.getWeight() > player.carryingCapacity)
            return false;

        if (item.isUnique() && checkIfItemExistsInInventory(item))
            return false;

        
        // Don't pick up items that give health, just consume them.
        if (item.getHeal() > 0) {
            player.setHealth(player.getHealth() + item.getHeal());

            if (player.getHealth() > player.getMaxHealth())
                player.setHealth(player.getMaxHealth());

            if (item.getHeal() > 500) {
                gameEngine.playSpecialEffect("green_swirly");
            }

            return true;
        }

        if (item.isRare()){
            if(item.isUnique())
                gameEngine.playSpecialEffect("blue_swirly");
            else
                gameEngine.playSpecialEffect("cool_swirly_particles");
        }
            

        player.inventory.add(item);

        calculateStats();

        return true;
    }
    
}