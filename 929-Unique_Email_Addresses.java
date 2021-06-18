//Time, Space O(n)

class Solution {
    public int numUniqueEmails(String[] emails) {
        int len = emails.length;
        Set<String> set = new HashSet<>();
        for (String email : emails){
            String[] full_name = email.split("@");
            String local_name = full_name[0].split("\\+")[0];
            local_name = local_name.replace(".", "");
            String name = local_name + "@" + full_name[1];
            set.add(name);
        }
        return set.size();
    }
}
