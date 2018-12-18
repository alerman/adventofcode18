package alerman.advent.day15;

import java.util.UUID;

public abstract class Combatant {
    private int hitPoints = 200;
    private int attackDamage = 3;
    private UUID id = java.util.UUID.randomUUID();

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public abstract char getCombatantType();
}
