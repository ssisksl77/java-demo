package algorithm.inflearn.a.stringarray;

public class LicenseKeyFormatting {
    public static void main(String[] args) {
        String S = "5F3Z-2e-9-w";
//		String S = "2-5g-3-J";
        int k = 4;
        licenseKeyFormatting(S, k);
    }

    private static void licenseKeyFormatting(String str, int k) {
        String newStr = str.replace("-", "");
        newStr = newStr.toUpperCase();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < newStr.length();i++) {
            sb.append(newStr.charAt(i));
        }

        for (int i = k; i < newStr.length(); i+=k) {
            sb.insert(newStr.length()-i,'-');
        }
        System.out.println(sb.toString() );
    }
}
