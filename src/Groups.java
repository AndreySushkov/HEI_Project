import java.util.ArrayList;

public class Groups {
    ArrayList<Group> groups = new ArrayList<>();

    public void addGroup(String title) {
        Group newGroup = new Group(title);
        if (groups.isEmpty()) {
            newGroup.setId(1);
        } else {
            newGroup.setId(groups.get(groups.size() - 1).getId() + 1);
        }
        groups.add(newGroup);
    }

    public void removeGroup(Group group) {
        groups.remove(group);
    }

    public void updateGroup(Group updatedGroup, String updatedTitle) {
        if (!updatedTitle.equals("")) {
            updatedGroup.setTitle(updatedTitle);
        }
    }

    public void showGroups() {
        for (Group group : groups) {
            System.out.println(group.getId() + "\t" + group.getTitle());
        }
    }

    public void showGroupsWithCourses() {
        for (Group group : groups) {
            System.out.println(group.getId() + "\t" + group.getTitle());
            for (Course course : group.getCourses()) {
                System.out.println("\t" + course.getId() + "\t" + course.getTitle());
            }
        }
    }

    public Group findGroupById(int id) {
        for (Group group : groups) {
            if (group.getId() == id) {
                return group;
            }
        }
        return null;
    }

    public void setGroups(ArrayList<Group> groups) {this.groups = groups;}

    public ArrayList<Group> getGroups() {return this.groups;}
}
