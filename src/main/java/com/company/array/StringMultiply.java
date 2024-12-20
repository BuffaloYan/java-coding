package com.company.array;

import org.junit.jupiter.api.Test;

public class StringMultiply {
    public String multiply(String num1, String num2) {
        // keep longer number in num1
        if (num2.length() > num1.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }

        /*
        *   12345
          +   678
        *
        */
        String product = "";
        StringBuilder sb = new StringBuilder();
        for (int i=num2.length()-1; i>=0; i--) {
            int n2 = num2.charAt(i)-'0';
            if (n2 == 0) {
                num1 += "0";
                continue;
            }

            int advance = 0;
            sb.setLength(0);
            for (int j=num1.length()-1; j>=0; j--) {
                int n1 = num1.charAt(j)-'0';
                int p = n1 * n2 + advance;
                advance = p/10;
                p = p%10;
                sb.append(p);
            }

            if (advance > 0) {
                sb.append(advance);
            }

            product = sum(product, sb.reverse().toString());
            num1 += "0";

        }

        if (product.length() == 0) {
            product = "0";
        }
        return product;
    }

    private String sum(String num1, String num2) {
        // keep longer number in num1
        if (num2.length() > num1.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }

        if (num2.length() == 0) {
            return num1;
        }
        /*
        *   12345
          +   678
        *
        */
        int advance = 0;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<num2.length(); i++) {
            int sum = num2.charAt(num2.length()-1-i)-'0' + num1.charAt(num1.length()-1-i)-'0' + advance;
            advance = sum/10;
            sum = sum%10;


            sb.append(sum);
        }

        int i = num1.length() - num2.length() -1;
        while (i >= 0) {
            int sum = num1.charAt(i)-'0' + advance;
            advance = sum/10;
            sum = sum%10;

            sb.append(sum);
            i--;
        }

        if (advance > 0) {
            sb.append(advance);
        }

        return sb.reverse().toString();
    }

    @Test
    public void testSum() {
        StringMultiply multiply = new StringMultiply();

        String s1 = multiply.multiply("123456789", "7654321");
        String s2 = multiply.multiply("123456789", "80000000");

        String sum = multiply.sum(s1, s2);
        System.out.println(sum);

    }
}
