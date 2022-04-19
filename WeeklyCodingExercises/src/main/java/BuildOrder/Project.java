package BuildOrder;

import java.util.ArrayList;
import java.util.List;

//model class
public class Project {

    private boolean scheduled;
    private List<Project> dependencies;
    private String name;

    public Project(String name){
        this.name = name;
        scheduled = false;
    }

    public void addDependency(Project p){
        if (dependencies == null){
            dependencies = new ArrayList<>();
        }
        dependencies.add(p);
    }

    public List<Project> getDependencies(){
        return dependencies;
    }

    public boolean isScheduled() {
        return scheduled;
    }

    public void schedule() {
        this.scheduled = true;
    }

    public String getName() {
        return name;
    }

}
