package LLD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

interface IGoal {
    int id = 0;

    void update(IGoal goal);
}

class GoalService {
    HashMap<Integer, IGoal> goalList = new HashMap<>();
    int i = 0;

    void addGoals(IGoal goal) {
        goalList.put(i++, goal);
    }

    void removeGoal(IGoal goal) {
        goalList.remove(goal.id);
    }

    void updateGoal(IGoal goal) {
        if(goalList.containsKey(goal.id)) {
            goalList.get(goal.id).update(goal);
        }
    }

    public static void main(String[] args) {

    }
}


class CaloriesGoal implements IGoal {
    private int caloriesTarget;

    public CaloriesGoal(int caloriesTarget) {
        this.caloriesTarget = caloriesTarget;
    }

    @Override
    public void update(IGoal goal) {
        if(goal instanceof CaloriesGoal){
            CaloriesGoal update = (CaloriesGoal)goal;
            this.caloriesTarget = update.caloriesTarget;
        }
    }

    public int getCaloriesTarget() {
        return caloriesTarget;
    }

    public void setCaloriesTarget(int caloriesTarget) {
        this.caloriesTarget = caloriesTarget;
    }

    @Override
    public String toString() {
        return "CaloriesGoal{" +
                "caloriesTarget=" + caloriesTarget +
                '}';
    }
}

class StepsGoal implements IGoal {
    private int stepsTarget;

    public StepsGoal(int stepsTarget) {
        this.stepsTarget = stepsTarget;
    }

    @Override
    public void update(IGoal goal) {
        if(goal instanceof StepsGoal){
            StepsGoal update = (StepsGoal)goal;
            this.stepsTarget = update.stepsTarget;
        }
    }

    public int getStepsTarget() {
        return stepsTarget;
    }

    public void setStepsTarget(int stepsTarget) {
        this.stepsTarget = stepsTarget;
    }

    @Override
    public String toString() {
        return "StepsGoal{" +
                "stepsTarget=" + stepsTarget +
                '}';
    }
}

class WorkOutGoal implements IGoal {
    private int workOutTarget;

    public WorkOutGoal(int workOutTarget) {
        this.workOutTarget = workOutTarget;
    }

    @Override
    public void update(IGoal goal) {
        if(WorkOutGoal.class.isInstance(goal)){
            WorkOutGoal update = (WorkOutGoal)goal;
            this.workOutTarget = update.workOutTarget;
        }
    }

    public int getWorkOutTarget() {
        return workOutTarget;
    }

    public void setWorkOutTarget(int workOutTarget) {
        this.workOutTarget = workOutTarget;
    }

    @Override
    public String toString() {
        return "WorkOutGoal{" +
                "workOutTarget=" + workOutTarget +
                '}';
    }
}


