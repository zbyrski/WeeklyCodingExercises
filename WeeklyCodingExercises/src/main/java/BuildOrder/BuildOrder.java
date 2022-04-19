package BuildOrder;

import java.util.*;

public class BuildOrder {

    //toDoProjects is a hashmap for fast removal of projects after completion
    //also allows users to access objects by just knowing their name
    private Map<String, Project> toDoProjects;
    //finishedProjects if a list because elements need to be ordered
    private List<Project> finishedProjects;

    public BuildOrder(){
        toDoProjects = new HashMap<>();
        finishedProjects = new ArrayList<>();
    }

    public void addProject(String projectName){
        Project newProject = new Project(projectName);
        toDoProjects.put(projectName, newProject);
    }

    public void addDependency(String p1, String p2){
        Project project1 = toDoProjects.get(p1);
        Project project2 = toDoProjects.get(p2);

        //before adding a dependency, check for conflicts
        //a conflict is possible if two projects are dependent on each other, directly or indirectly
        if (hasDependency(project2, project1.getDependencies())) {
            System.out.println("Adding this as a dependency would create a conflict.  Operation aborted.");
            return;
        }else{
            project2.addDependency(project1);
        }
    }

    public void orderProjects(){
        //for each project in toDoProjects, go through a recursive method to complete projects and their dependencies
        for (Project project : toDoProjects.values()){
            scheduleProject(project);
        }

        printProjectOrder();
    }

    //recursive function to complete dependencies before completing the passed in project
    public void scheduleProject(Project p){
        //if the project is already completed, no work is necessary
        //current project being completed means all dependencies are also completed
        if (p.isScheduled()) return;

        List<Project> dependencies = p.getDependencies();
        if (dependencies != null) {
            for (Project project : dependencies) {
                scheduleProject(project);
            }
        }

        finishedProjects.add(p);
        p.schedule();
    }

    public void printProjectOrder(){
        for (int i = 0; i < finishedProjects.size(); i++){
            System.out.print(finishedProjects.get(i).getName());
            if (i < finishedProjects.size() - 1){
                System.out.print(", ");
            }
        }

        System.out.println();
    }

    //recursive method to check for dependency conflicts
    public boolean hasDependency(Project dependent, List<Project> dependencies) {
        if (dependencies == null) return false;
        if (dependencies.contains(dependent)) return true;

        for (Project project : dependencies){
            if (hasDependency(dependent, project.getDependencies())) return true;
        }

        return false;
    }
}
