package fr.zwartkat.services;

import fr.zwartkat.Piece;

public class CombatResult {
    private int damageToDefender,damageToAttacker;
    private boolean attackerKo, defenderKo;

    public CombatResult(int damageToAttacker, int damageToDefender, boolean attackerKo, boolean defenderKo){
        this.damageToAttacker = damageToAttacker;
        this.damageToDefender = damageToDefender;
        this.attackerKo = attackerKo;
        this.defenderKo = defenderKo;
    }

    public int getDamageToAttacker() {
        return damageToAttacker;
    }

    public int getDamageToDefender() {
        return damageToDefender;
    }

    public boolean isAttackerKo() {
        return attackerKo;
    }

    public boolean isDefenderKo() {
        return defenderKo;
    }
}
