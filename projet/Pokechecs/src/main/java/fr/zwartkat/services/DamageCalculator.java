package fr.zwartkat.services;

import fr.zwartkat.pokemon.IPokemon;
import fr.zwartkat.pokemon.Type;

public class DamageCalculator {
    public int calculateDamage(IPokemon attacker, IPokemon defender) {

        double efficiency = getEffectiveness(attacker,defender);
        return (int) (((((20*0.4+2)*attacker.getAtk()*50)/defender.getDef())/50*efficiency)*efficiency);
    }

    private double getEffectiveness(IPokemon attacker, IPokemon defender) {

        double efficiencyType1,efficiencyType2;

        int typeAtk1 = attacker.getType1();
        int typeAtk2 = attacker.getType2();

        int typeDef1 = defender.getType1();
        int typeDef2 = defender.getType2();

        if(typeAtk2 == Type.SANS){
            return Type.getEfficacite(typeAtk1, typeDef1) * Type.getEfficacite(typeAtk1, typeDef2);
        }

        efficiencyType1 = Type.getEfficacite(typeAtk1, typeDef1) * Type.getEfficacite(typeAtk1, typeDef2);
        efficiencyType2 = Type.getEfficacite(typeAtk2, typeDef2) * Type.getEfficacite(typeAtk2, typeDef2);
        return Math.max(efficiencyType1,efficiencyType2);

    }
}
