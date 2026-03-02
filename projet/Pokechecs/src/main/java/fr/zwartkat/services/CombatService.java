package fr.zwartkat.services;

import fr.zwartkat.pokemon.IPokemon;

public class CombatService {

    private DamageCalculator damageCalculator;

    public CombatService(DamageCalculator damageCalculator){
        this.damageCalculator = damageCalculator;
    }

    public CombatResult fight(IPokemon attacker, IPokemon defender){

        if(attacker.isKo() || defender.isKo()){
            throw new IllegalStateException("Fight impossible because one of concerned pokemons is already K.O.");
        }

        int damageToAttacker = 0;
        int damageToDefender = 0;

        boolean attackerFirst = attacker.getSpeed() >= defender.getSpeed();

        if(attackerFirst){
            damageToDefender = performAttack(attacker,defender);
            if(!defender.isKo()){
                damageToAttacker = performAttack(defender,attacker);
            }
        }
        else {
            damageToAttacker = performAttack(defender,attacker);
            if(!(attacker.isKo())){
                damageToDefender = performAttack(attacker,defender);
            }
        }

        return new CombatResult(damageToAttacker,damageToDefender,attacker.isKo(),defender.isKo());
    }

    private int performAttack(IPokemon attacker, IPokemon defender){
        int damage = damageCalculator.calculateDamage(attacker,defender);
        defender.takeDamage(damage);
        return damage;
    }
}
