import java.util.*;

public class EmployeeNode {
    public int value;
    public ArrayList<EmployeeNode> subEmployees;

    public EmployeeNode(int val) {
        this.value = val;
        this.subEmployees = new ArrayList<EmployeeNode>();
    }

    public static double max_tenure = 0.0;
    public static EmployeeNode topManager = null;
    public static EmployeeNode getBusiestNode(EmployeeNode president) {
        if (president == null) {
            return null;
        }
        dfs(president);
        return topManager;
    }

    public static int[] dfs(EmployeeNode node) {
        if (node == null)
            return new int[] {0, 0};

        int currValue = node.value;
        int count = 1;
        for (EmployeeNode subEmployee : node.subEmployees) {
            int[] childInfo = dfs(subEmployee);
            currValue += childInfo[0];
            count += childInfo[1];
        }

        if (count > 1 && currValue * 1.0 / count > max_tenure) {
            max_tenure = currValue * 1.0 / count;
            topManager = node;
        }
        return new int[] {currValue, count};
    }

    public static void main(String[] args) {
        EmployeeNode president = new EmployeeNode(20);
        EmployeeNode subManager1 = new EmployeeNode(12);
        EmployeeNode subManager2 = new EmployeeNode(18);
        subManager1.subEmployees.add(new EmployeeNode(11));
        subManager1.subEmployees.add(new EmployeeNode(2));
        subManager1.subEmployees.add(new EmployeeNode(3));
        subManager2.subEmployees.add(new EmployeeNode(15));
        subManager2.subEmployees.add(new EmployeeNode(8));
        president.subEmployees.add(subManager1);
        president.subEmployees.add(subManager2);
        System.out.println(getBusiestNode(president).value);
    }
}
