import java.util.Random;
public class Hero {
    Random randomNum = new Random();
    private String name;
    private int hitPoints = 100;
    private int winTimeHero;
    private int winTimeOpponent;

    public Hero(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getHitPoints(){
        return hitPoints;
    }

    public String toString(){
        return "Hero{name='"+name+"', hitPoints="+hitPoints+"}";
    }

    public void attack(Hero opponent){
        double hitChance = randomNum.nextDouble(0.99);
        if (hitChance<0.5){
            opponent.hitPoints-=10;
        }
        else if (hitChance>=0.5){
            this.hitPoints-=10;
        }
    }

    public void senzuBean(){
        this.hitPoints = 100;
    }

    public void fightUntilTheDeathHelper(Hero opponent){
        while(this.hitPoints!=0 && opponent.hitPoints!= 0) {
            attack(opponent);
        }
        if (opponent.hitPoints == 0){
            winTimeHero+=1;
        }
        if (hitPoints == 0){
            winTimeOpponent+=1;
        }
    }

    public String fightUntilTheDeath(Hero opponent){
        this.senzuBean();
        opponent.senzuBean();
        fightUntilTheDeathHelper(opponent);
        return name+": "+hitPoints+opponent.name+": "+opponent.hitPoints;
    }

    public int[] nFightsToTheDeathHelper(Hero opponent, int n){
        for (int i = 1; i<=n; i++) {
            fightUntilTheDeathHelper(opponent);
            senzuBean();
            opponent.senzuBean();
        }
        return new int[]{winTimeHero,winTimeOpponent};

    }

    public String nFightsToTheDeath(Hero opponent, int n){
        int[] winTimes = nFightsToTheDeathHelper(opponent,n);
        if (winTimes[0]>winTimes[1]){
            return name+": "+winTimes[0]+" wins"+"\n"+opponent.name+": "+winTimes[1]+" wins"+"\n"+name+" wins!";
        }
        else if (winTimes[0]<winTimes[1]){
            return name+": "+winTimes[0]+" wins"+"\n"+opponent.name+": "+winTimes[1]+" wins"+"\n"+opponent.name+" wins!";
        }
        else{
            return name+": "+winTimes[0]+" wins"+"\n"+opponent.name+": "+winTimes[1]+" wins"+"\n"+"OMG! It was actually a draw!";
        }
    }

    public void dramaticFightToTheDeath(Hero opponent){
        winTimeHero = 0;
        winTimeOpponent = 0;
        while(hitPoints!=0 && opponent.hitPoints!=0){
            attack(opponent);
            System.out.println(name+": "+hitPoints+"     "+opponent.name+": "+opponent.hitPoints);
        }
        if (opponent.hitPoints == 0){
            System.out.println(name+" wins!");
        }
        if (hitPoints == 0){
            System.out.println(opponent.name+" wins!");
        }

    }
}
