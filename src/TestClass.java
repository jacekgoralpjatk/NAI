public class TestClass {

    public static void main(String[] args) {

        String text = "Some text with non-ascii characters like123 é and ä.";
        text = removeNonLatin(text).toLowerCase();

        System.out.println("Original text: " + text);
    }

    public static String removeNonLatin(String str) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                builder.append(c);
            }
        }
        return builder.toString().toLowerCase();
    }

}
