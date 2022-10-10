import java.util.ArrayList;

public class Groups {
    ArrayList<Group> groups = new ArrayList<Group>();

    public void addGroup(String title) {
        Group newGroup = new Group(title);
        if (groups.isEmpty()) {
            newGroup.setId(1);
        } else {
            newGroup.setId(groups.get(groups.size() - 1).getId() + 1);
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
}
